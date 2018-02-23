package jehc.xtmodules.xtcore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import jehc.xtmodules.xtcore.util.springutil.SpringContextUtil;
import jehc.xtmodules.xtdao.XtNumberDao;
import jehc.xtmodules.xtmodel.XtNumber;

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
    private static int startNumber=1000000;
    
    /**
     * 生成不重复的订单号
     * @return
     */
    public static String genNum() {  
        try {  
            String orderNum = "";  
            synchronized (lockObj) {  
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount >= maxPerMSECSize) {  
                    orderNumCount = 0L;  
                }  
                //组装订单号  
                String countStr=maxPerMSECSize +orderNumCount+"";  
                orderNum=nowLong+countStr.substring(1);  
                orderNumCount++;  
                System.out.println(orderNum + "--" + Thread.currentThread().getName());  
                return orderNum;
            }  
        } catch (Exception e) {  
            return null;
        }  
    }  
    
    
    public static String genNum(String modulesType) {  
        try {  
        	String orderNum = "";  
            synchronized (lockObj) {  
                //取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
                /**
                //计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount >= maxPerMSECSize) {  
                    orderNumCount = 0L;  
                }  
                //组装订单号  
                String countStr=maxPerMSECSize +orderNumCount+"";  
                orderNum=nowLong+countStr.substring(1);  
                orderNumCount++;  
                **/
                if(StringUtils.isEmpty(modulesType)){
                	return null;
                }
                XtNumber xtNumber = getXtNumberSingleByType(modulesType);
                XtNumberDao xtNumberDao = SpringContextUtil.getBeans("xtNumberDao");
                if(null == xtNumber){
                	xtNumber = new XtNumber();
                	xtNumber.setCreateTime(new Date());
                	xtNumber.setLastUpdateTime(new Date());
                	xtNumber.setVersion(1);
                	xtNumber.setModulesType(modulesType);
                	xtNumber.setLastvalue(startNumber);
                	xtNumber.setXt_number_id(UUID.toUUID());
                	if(xtNumberDao.addXtNumber(xtNumber) > 0){
                		xtNumber = getXtNumberSingleByType(modulesType);
                    	orderNum= ""+nowLong+xtNumber.getLastvalue();
                	}
                }else{
                	xtNumber.setLastvalue(xtNumber.getLastvalue()+1);
                	if(xtNumberDao.updateXtNumber(xtNumber) > 0){
                		orderNum= ""+nowLong+xtNumber.getLastvalue();
                	}
                }
                System.out.println(orderNum + "--" + Thread.currentThread().getName());  
                return orderNum;
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
            return null;
        }  
    }  
    
    public static XtNumber getXtNumberSingleByType(String modulesType){
    	XtNumberDao xtNumberDao = SpringContextUtil.getBeans("xtNumberDao");
    	return xtNumberDao.getXtNumberSingleByType(modulesType);
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
