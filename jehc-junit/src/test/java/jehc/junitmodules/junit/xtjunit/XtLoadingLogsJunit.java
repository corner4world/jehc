package jehc.junitmodules.junit.xtjunit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jehc.junitmodules.junit.base.BaseJunit;
import jehc.xtmodules.xtmodel.XtLoadinfo;
import jehc.xtmodules.xtservice.XtLoadinfoService;

/**
 * 线程池（主线程+多个子线程分段查询）示例
 * @author 邓纯杰
 *
 */
public class XtLoadingLogsJunit extends BaseJunit{
	@Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired
	private XtLoadinfoService xtLoadinfoService;
	//////////////////////////方法一返回值模拟//////////////////////
	/**
	 * 一个主线程+多个子线程实现分段查询
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void query() throws InterruptedException, ExecutionException {  
        int limit = 30;
        long begTime = System.currentTimeMillis(); 	
        List<XtLoadinfo> totalList = new ArrayList<XtLoadinfo>();
        List<Future<Object>> futureList = new ArrayList<Future<Object>>();
        for(int i = 1; i <= 253; i++){
        	Future<Object> future = threadPoolTaskExecutor.submit(new CustomerCallable(i,(i-1)*limit,limit));//提交任务至子线程处理，有返回值并通过Future获取返回值
        	futureList.add(future);
        	System.out.println("开始执行方法，连接池数量："+threadPoolTaskExecutor.getActiveCount()); 
        }        
        //循环调用
        while(true){
        	boolean isCompleted = true;
            for(Future<Object> future:futureList) {
            	isCompleted &= (future.isDone() || future.isCancelled());
            }
            if(isCompleted){
                //任务都执行完毕，跳出循环
                break;
            }
        }
        for(Future<Object> future:futureList) {
        	List<XtLoadinfo> list = (List<XtLoadinfo>)future.get();
        	totalList.addAll(list);
        }
        long endTime = System.currentTimeMillis(); 	
        System.out.println("---总数量:"+totalList.size()+"，运行总时间："+(endTime-begTime));
    }  
	
	/**
	 * 子线程
	 * @author邓纯杰
	 *
	 */
	class CustomerCallable implements Callable<Object> {   
		private int num;
        private int limit;    
        private int start;
        public CustomerCallable(int num,int start,int limit) {    
            this.limit = limit;    
            this.start = start;
            this.num = num;
        }    
        public List<XtLoadinfo> call() throws Exception {   
        	System.out.println("子线程 call()方法被调用！" + Thread.currentThread().getName()); 
        	long begTime = System.currentTimeMillis(); 	
            Map<String, Object> condition = new HashMap<String, Object>();
            PageHelper.offsetPage(start, limit);
            List<XtLoadinfo> list = xtLoadinfoService.getXtLoadinfoListByCondition(condition);
            PageInfo<XtLoadinfo> page = new PageInfo<XtLoadinfo>(list);
            long endTime = System.currentTimeMillis(); 	
            System.out.println("-------------第----------"+num+"---------次,----------------耗时:"+(endTime-begTime));  
            return page.getList();
        }   
	}
}
