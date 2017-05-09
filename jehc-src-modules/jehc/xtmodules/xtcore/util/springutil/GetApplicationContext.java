package jehc.xtmodules.xtcore.util.springutil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
/**
 * 获取Bean工具类
 * @author 邓纯杰
 *
 */
public class GetApplicationContext {
	private static class ApplicationContextHolder {
		// 单例变量
		private static ApplicationContext AC = new FileSystemXmlApplicationContext("classpath:xtCore/sources/spring/spring.xml");
	}

	// 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	private GetApplicationContext() {
		
	}

	// 获取单例对象实例
	public static ApplicationContext getInstance() {
		if (ApplicationContextHolder.AC == null) {
			ApplicationContextHolder.AC = new FileSystemXmlApplicationContext("classpath:xtCore/sources/spring/spring.xml");
		}
		return ApplicationContextHolder.AC;
	}
	
	/**
	  * 获取对象   
	  * @param name
	  * @return Object 一个以所给名字注册的bean的实例
	  * @throws BeansException
	  */
	  public static Object getBean(String name) throws BeansException {
		  WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 
	    return wac.getBean(name);
	}
}
