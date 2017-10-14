package jehc.xtmodules.xtcore.proxy.shcedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 检测线程
 * @author邓纯杰
 *
 */
public class CheckThread extends Thread{
	 Logger logger = LoggerFactory.getLogger(CheckThread.class);
	 private static final int sleepTime = 2000;
	 
	 /**
	  * 默认构造函数
	  * @param threadName
	  */
     public CheckThread(String threadName) {
        this.setName(threadName);
     }
	    
	 public void run() {
        logger.info("CheckThread[线程：" + this + "->" + this.getId() + "]");
        //用死循环检测
        for (;;) {
            try {
            	 Thread.sleep(sleepTime);
            }
            catch (Exception e) {
                logger.error("CheckThread[" + this + "->" + this.getId() + "]发生异常，线程关闭", e);
            }
        }
    }
}

