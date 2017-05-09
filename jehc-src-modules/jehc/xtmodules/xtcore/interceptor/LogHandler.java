package jehc.xtmodules.xtcore.interceptor;
//package xtCore.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.core.NamedThreadLocal;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import xtCore.util.Logback4jUtil;
//
//
///**
// * 日志拦截
// * @author邓纯杰
// *
// */
//public class LogHandler extends Logback4jUtil implements HandlerInterceptor{
//	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
//	/**
//	 * 前置通知
//	 */
//	public boolean preHandle(HttpServletRequest request, 
//			HttpServletResponse response,
//			Object arg2) throws Exception {
//		//1.开始时间  
//		long beginTime = System.currentTimeMillis();
//		//线程绑定变量（该数据只有当前请求的线程可见） 
//        startTimeThreadLocal.set(beginTime);		 
//		return true;
//	}
//	/**
//	 * 后置通知
//	 */
//	public void afterCompletion(HttpServletRequest request, 
//			HttpServletResponse response,
//			Object handler, 
//			Exception ex) throws Exception {
//		//得到线程绑定的局部变量（开始时间）
//		long beginTime = startTimeThreadLocal.get();
//		//结束时间  
//		long endTime = System.currentTimeMillis(); 	
//        //耗时
//        long usedTime = (endTime - beginTime);
//        //URI
//        String uri = request.getRequestURI().replace(request.getContextPath(), "");
//        //最大内存(单位m)
//        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;
//        //已分配内存
//        long totalMemory = Runtime.getRuntime().totalMemory()/1024/1024;
//        //已分配内存中的剩余空间
//        long freeMemory = Runtime.getRuntime().freeMemory()/1024/1024;
//        //最大可用内存
//        long useMemory= (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024;
//        StringBuilder sbuilder = new StringBuilder();
//        if(handler instanceof HandlerMethod){
//            HandlerMethod handlerMethod = (HandlerMethod)handler;
//            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//            for(MethodParameter methodParameter : methodParameters){
//            	sbuilder.append(methodParameter.getParameterName());
//            }
//        }
//        info("{拦截器日志处理类","{\"开始时间\":\""+beginTime+"\",计时结束\":\""+endTime+"\",\"耗时\":\""+usedTime+"\",\"最大内存(单位m)\":\""+maxMemory+"\",\"已分配内存\":\""+totalMemory+"\",\"已分配内存中的剩余空间\":\""+freeMemory+"\",\"最大可用内存\":\""+useMemory+"\",\"参数\":\""+sbuilder.toString()+"\",\"uri\":\""+uri+"\"}}");
//	}
//	/**
//	 * 环绕通知
//	 */
//	public void postHandle(HttpServletRequest request, 
//			HttpServletResponse response,
//			Object handler, 
//			ModelAndView modelAndView) throws Exception {
//		if (null != modelAndView){
//			modelAndView.getViewName();
//		}
//	}
//}
