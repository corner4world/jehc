package jehc.xtmodules.xtcore.interceptor;
//package xtCore.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import xtCore.annotation.AuthUneedLogin;
//import xtCore.util.CommonUtils;
//
///**
// * 平台用户超时拦截
// * @author邓纯杰
// *
// */
//public class SessionTimeOutInterceptor implements HandlerInterceptor{
////	public String[] allowUrls;/**还没发现可以直接配置不拦截的资源，所以在代码里面来排除**/
////	public void setAllowUrls(String[] allowUrls) {
////		this.allowUrls = allowUrls;
////	}
//	/**
//	 * 前置通知
//	 */
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
//////		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
////		HandlerMethod methodHandler=(HandlerMethod) handler;
////		//不需要登陆验证的URL
////		AuthUneedLogin authUneedLogin=methodHandler.getMethodAnnotation(AuthUneedLogin.class);
////		//如果获取到方法是无需登录则放开 让其走
////		if(null != authUneedLogin){
////			return true;
////		}
////		/**请求到允许的URL 放行 已不采用该方法 直接使用注解拦截器做了，不过该方法也要会**/
//////		if(null != allowUrls && allowUrls.length>=1){
//////			for(String url : allowUrls) {  
//////				if(requestUrl.contains(url)) {  
//////					return true;  
//////				}  
//////			}
//////		}
////		if(null != CommonUtils.getXtU()) {  
////			 //返回true,则这个方面调用后会接着调用postHandle(),afterCompletion()
////			return true; 
////		}else{
////			//非法请求【超时请求】即这些请求需要登录后才能访问  
////		    //重定向到登录页面  
////			String head = request.getHeader("x-requested-with");
////			//XMLHttpRequest为异步 Ext.basex为同步
////			if(null != head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head))) { 
////				response.setContentType("text/html;charset=utf-8");  
////	        	response.getWriter().print("{xt_pt_status:888}");
////	        	response.getWriter().flush();
////			}else{
//////				 response.sendRedirect(request.getContextPath()+"/index/sessionout.html");  
////			}
////			return false;
////		}
//		return true;
//	}
//	/**
//	 * 后置通知
//	 */
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)throws Exception {
//	}
//	/**
//	 * 后置环绕通知
//	 */
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
//			Object arg2, ModelAndView arg3) throws Exception {
//	}
//
//}
