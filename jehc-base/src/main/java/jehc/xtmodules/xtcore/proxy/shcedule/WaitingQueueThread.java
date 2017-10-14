package jehc.xtmodules.xtcore.proxy.shcedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class WaitingQueueThread  extends Thread{
    Logger logger = LoggerFactory.getLogger(WaitingQueueThread.class);

    private static final int sleepTime = 2000;

    /**
     * 构造函数
     * @param threadName
     */
    public WaitingQueueThread(String threadName) {
        this.setName(threadName);
    }

    /**
     * 跑线程
     */
    public void run() {
        logger.info("");
        while (true) {
            try {
            	 Thread.sleep(sleepTime);
            }
            catch (Exception e) {
                logger.error("");
            }
        }
    }
}
