package jehc.xtmodules.xtcore.init;

/**
import java.io.File;
**/
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.util.MapUtils;
import jehc.xtmodules.xtcore.util.ReadProperties;

/**
 * 让服务器启动或停止时自动执行代码
 * @author 邓纯杰
 *
 */
public class InitSetup implements ServletContextListener{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 停止时执行的方法
	 */
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
	    sc.removeAttribute("syspath");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date())+"--->服务器容器销毁成功");
	}

	/**
	 * 启动时执行方法
	 */
	public void contextInitialized(ServletContextEvent event) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServletContext sc = event.getServletContext();
		try {
	        sc.setAttribute("syspath", getContextPath(sc));
	        logger.info(""+sdf.format(new Date())+"--->业务平台路径:"+getContextPath(sc));
	        logger.info(sdf.format(new Date())+"--->进入类加载");
	        logger.info(sdf.format(new Date())+"--->装载配置文件"); 
			Map<String, Object> map = ReadProperties.readProperties(event);
			MapUtils.setKvToServletContext(map, sc);		
			map = ReadProperties.readMessageProperties(event);
			MapUtils.setKvToServletContext(map, sc);
			logger.info(sdf.format(new Date())+"--->装载配置结束"); 
			
			logger.info(sdf.format(new Date())+"--->装载Config配置开始"); 
			map = ReadProperties.readConfigProperties(event);
			MapUtils.setKvToServletContext(map, sc);
			logger.info(sdf.format(new Date())+"--->装载Config配置结束"); 
		} catch (Exception e) {
			logger.error("启动容器服务出现异常{0}",e.getMessage());
		}
		logger.info(sdf.format(new Date())+"--->结束类加载"); 
	}
	private String getContextPath(ServletContext sc) {
        return sc.getContextPath();
    }
}
