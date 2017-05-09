package jehc.junitmodules.junit.pooljunit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import jehc.xtmodules.xtcore.base.BaseJunit;
/**
 * Spring线程池测试
 * @author 邓纯杰
 *
 */
public class SpringPool extends BaseJunit{
	@Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	//////////////////////////方法一返回值模拟//////////////////////
	/**
	 * 模拟发送
	 * @param id
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void send() throws InterruptedException, ExecutionException {  
        System.out.println("开始执行方法，连接池数量："+threadPoolTaskExecutor.getActiveCount());  
        for(int i = 0; i < 100; i++){
        	 Future<Object> f2 = threadPoolTaskExecutor.submit(new CustomerCallable(i));//有返回值并通过Future获取返回值
             System.out.println(f2.get());  
        }
    }  
	class CustomerCallable implements Callable<Object> {    
        private int id;    
        public CustomerCallable(int id) {    
            this.id = id;    
        }    
        public String call() throws Exception {    
            System.out.println("call()方法被调用！" + Thread.currentThread().getName());  
            return "结果是：" + id;  
        }   
	}
	
	
	/////////////////////////方法二无返回值模拟//////////////////////
	/**
	 * 无返回值
	 */
	@Test
	public void excute(){
		for(int i = 0; i < 100; i++){
			threadPoolTaskExecutor.execute(new ExcuteTask(i));
		}
	}
	class ExcuteTask implements Runnable{    
        private int id;    
        public ExcuteTask(int id) {    
            this.id = id;    
        }    
        public void run() {
            System.out.println("run方法被执行 "+Thread.currentThread().getName()+"任务id:"+id);
        }  
	}
}
