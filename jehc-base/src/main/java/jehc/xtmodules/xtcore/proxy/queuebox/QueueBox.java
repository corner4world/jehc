package jehc.xtmodules.xtcore.proxy.queuebox;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import jehc.xtmodules.xtcore.proxy.Tasks;
import jehc.xtmodules.xtcore.proxy.shcedule.WaitingQueueThread;


public class QueueBox {
	// 队列容量最大值
    private static final int FILE_QUEUE_SIZE = 10000;
    // 用户存放小组正在运行的语音通道队列
    private static Map<String, BlockingQueue<Tasks>> waitingQueueMap = new ConcurrentHashMap<String, BlockingQueue<Tasks>>();

    /**
     * 根据小组名称获取占用队列: <br>
     *
     * @param group
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static synchronized BlockingQueue<Tasks> getWaitingTaskQueue(String id) {
        BlockingQueue<Tasks> waitingQueue = waitingQueueMap.get(id);
        if (waitingQueue == null) {
            waitingQueue = new ArrayBlockingQueue<Tasks>(FILE_QUEUE_SIZE);
            waitingQueueMap.put(id, waitingQueue);
            // 运行队列守护线程（每个小组一个）
            WaitingQueueThread waitingQueueThread = new WaitingQueueThread(id+"-runningQueue");
            waitingQueueThread.start();
        }
        return waitingQueue;
    }
    
    /**
     * 
     * 在隊列中壓入新的任務: <br>
     *
     * @param task
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static synchronized void put(Tasks task){
    	BlockingQueue<Tasks> waitingQueue = getWaitingTaskQueue(task.getId());
    	waitingQueue.offer(task);
    }
    
    /**
     * 
     * 获取并删除待执行任务队列: <br>
     *
     * @param group
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static synchronized Tasks poolTask(String group){
    	BlockingQueue<Tasks> waitingQueue = getWaitingTaskQueue(group);
    	Tasks task =waitingQueue.poll();
    	return task;
    }
}