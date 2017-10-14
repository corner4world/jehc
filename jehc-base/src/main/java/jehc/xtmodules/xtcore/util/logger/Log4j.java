package jehc.xtmodules.xtcore.util.logger;

import org.apache.log4j.PropertyConfigurator;
/**
 * Log4j动态修改配置文件并生效
 * @author 邓纯杰
 *
 */
public class Log4j {
	private static boolean isReload = true;  
	/** 
     * 装载log4j配置文件 
     * @DATE 2011-5-28 
     */  
    public static void load() {  
        String path = Log4j.class.getClass().getResource("/").getPath() + "sources/log4j.properties";  
        PropertyConfigurator.configureAndWatch(path,1000);//间隔特定时间，检测文件是否修改，自动重新读取配置  
    }  
  
    /**
     * 重新加载
     */
    @SuppressWarnings("unused")
	private static void reload() {  
        if (isReload) {  
            load();  
        }  
        isReload = false;  
    }  
  
    public void setReload(boolean flag) {  
        isReload = flag;  
    } 
}
