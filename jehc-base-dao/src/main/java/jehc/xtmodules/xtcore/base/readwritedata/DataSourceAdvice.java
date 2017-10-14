package jehc.xtmodules.xtcore.base.readwritedata;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 配置AOP切面类，动态切换读/写数据库。
 */
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
	Logger log = LoggerFactory.getLogger(DataSourceAdvice.class);
	private String logInfo;
	// 需要切换到从库(读库)的方法名前缀
	private List<String> slaveMethods;
	/**
	 * service方法执行之前被调用.
	 */
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logInfo = String.format("拦截前before切入点:%s-->%s(),将数据源设置为:", target.getClass().getName(), method.getName());
		String methodName = method.getName();
		boolean hasSwitchedSlave = false;
		for (String slaveMethod : slaveMethods) {
			if (methodName.startsWith(slaveMethod)) {
				hasSwitchedSlave = true;
				JdbcContextHolder.setSlave();
				if (log.isDebugEnabled()) {
					log.info(logInfo + JdbcContextHolder.getDataSource());
				}
				break;
			}
		}
		if (!hasSwitchedSlave) {
			if (log.isDebugEnabled()) {
				log.info(logInfo + "将数据源设置为:" + JdbcContextHolder.MASTER_DATA_SOURCE);
			}
			JdbcContextHolder.setMaster();
		}
	}
	/**
	 * service方法执行完之后被调用.
	 */
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {}
	/**
	 * 抛出Exception之后被调用。
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 * @throws Throwable
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		logInfo = String.format("抛异常after throwing:%s类中%s方法,", target.getClass().getName(), method.getName());
		log.error(logInfo + "发生异常:" + ex.getMessage() + ",将数据源设置为:" + JdbcContextHolder.getDataSource());
		JdbcContextHolder.setSlave();
	}

	public List<String> getSlaveMethods() {
		return slaveMethods;
	}
	public void setSlaveMethods(List<String> slaveMethods) {
		this.slaveMethods = slaveMethods;
	}
}
