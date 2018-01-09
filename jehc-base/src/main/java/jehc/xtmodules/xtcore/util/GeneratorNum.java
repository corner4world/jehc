package jehc.xtmodules.xtcore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 产生单号
 * @author 邓纯杰
 * 
 */
public class GeneratorNum {
	
	public static String generatorOrderID() {
		Date date = new Date();
		// 年+月+日+时+分+秒+毫秒
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddkkmmssSS");
		String sdate = format.format(date);
		Random random = new Random();
		int number = random.nextInt(999999999);
		String randomName = sdate + number;
		return randomName;
	}
	
	/** 
     * 锁对象，可以为任意对象 
     */  
    private static Object lockObj = "lockerOrder";  
    /** 
     * 订单号生成计数器 
     */  
    private static long orderNumCount = 0L;  
    /** 
     * 每毫秒生成订单号数量最大值 
     */  
    private static int maxPerMSECSize=1000;  
    
    /**
     * 生成不重复的订单号
     * @return
     */
    public static String genNum() {  
        try {  
            String finOrderNum = "";  
            synchronized (lockObj) {  
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount >= maxPerMSECSize) {  
                    orderNumCount = 0L;  
                }  
                //组装订单号  
                String countStr=maxPerMSECSize +orderNumCount+"";  
                finOrderNum=nowLong+countStr.substring(1);  
                orderNumCount++;  
                System.out.println(finOrderNum + "--" + Thread.currentThread().getName());  
                // Thread.sleep(1000);  
                return finOrderNum;
            }  
        } catch (Exception e) {  
            return null;
        }  
    }  
  
    public static void main(String[] args) {  
        // 测试多线程调用订单号生成工具  
        try {  
            for (int i = 0; i < 200; i++) {  
                Thread t1 = new Thread(new Runnable() {  
                    public void run() {  
                    	GeneratorNum makeOrder = new GeneratorNum();  
                        makeOrder.genNum();  
                    }  
                }, "线程T1——" + i);  
                t1.start();  
  
                Thread t2 = new Thread(new Runnable() {  
                    public void run() {  
                    	GeneratorNum makeOrder = new GeneratorNum();  
                        makeOrder.genNum();  
                    }  
                }, "线程T2——" + i);  
                t2.start();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
}
