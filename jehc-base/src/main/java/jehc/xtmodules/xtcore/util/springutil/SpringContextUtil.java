package jehc.xtmodules.xtcore.util.springutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * 
 * 项目名称：Activiti工作流业务平台 类名称：SpringContextUtils 创建人：邓纯杰 创建时间：Sep 13, 2011 9:39:23
 * AM 修改人：dengchunjie 修改时间：Sep 13, 2011 9:39:23 AM 修改备注：
 * 
 * @version
 *
 */
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	// 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}

	// 取得存储在静态变量中的ApplicationContext.
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	// 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	public static <T> T getBeans(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	// 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.如果有多个Bean符合Class, 取出第一个.
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();  
        Map<String, T> beanMap = applicationContext.getBeansOfType(clazz);  
        Collection<T> valueSet = beanMap.values();  
        List<T> valueList = new ArrayList<T>(valueSet);  
        return valueList.get(0) ; 
	}
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在spring.xml中定义applicationContext");
		}
	}
	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static Object getBean(String name, Class requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	@SuppressWarnings("unchecked")
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
}
