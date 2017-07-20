package jehc.xtmodules.xtcore.util.springutil;

import org.springframework.beans.BeansException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
/**
 * 获取Bean工具类
 * @author 邓纯杰
 *
 */
public class SpringUtil {
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
