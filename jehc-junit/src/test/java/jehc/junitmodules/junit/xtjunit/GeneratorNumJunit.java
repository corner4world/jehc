package jehc.junitmodules.junit.xtjunit;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import jehc.junitmodules.junit.base.BaseJunit;
import jehc.xtmodules.xtcore.util.GeneratorNum;

public class GeneratorNumJunit extends BaseJunit{
	@Test
	public void genNum(){
		// 测试多线程调用订单号生成工具  
        try {  
//        	for (int i = 1; i <= 5; i++) { 
//        		GeneratorNum.genNum("Order"); 
//        	}
//        	ExecutorService pool = Executors.newFixedThreadPool(5);
//        	for (int i = 1; i <= 500; i++) {
//				pool.execute(new Runnable() {
//					public void run() {
//						GeneratorNum.genNum("Order"); 
//					}
//				});
//			}
        	
          for (int i = 0; i < 200; i++) {  
              Thread t1 = new Thread(new Runnable() {  
                  public void run() {  
                  	GeneratorNum.genNum("Order"); 
                  }  
              }, "线程T1——" + i);  
              t1.start();  
  
              Thread t2 = new Thread(new Runnable() {  
                  public void run() {  
                  	GeneratorNum.genNum("Order"); 
                  }  
              }, "线程T2——" + i);  
              t2.start();  
          }  
          TimeUnit.SECONDS.sleep(100);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}
