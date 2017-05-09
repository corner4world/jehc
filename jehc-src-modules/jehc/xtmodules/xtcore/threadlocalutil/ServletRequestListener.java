package jehc.xtmodules.xtcore.threadlocalutil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听BaseController中ThreadLocal变量并销毁
 * @author 邓纯杰
 *
 */
public class ServletRequestListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		/**
		BaseAction.closeThreadLocal();
		ThreadLocalUtil.destroy();
		**/
	}

	public void contextInitialized(ServletContextEvent arg0) {
	}
}
