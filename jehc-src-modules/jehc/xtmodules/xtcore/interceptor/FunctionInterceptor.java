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
// * 功能拦截器
// * @author邓纯杰
// *
// */
//public class FunctionInterceptor extends CommonUtils implements HandlerInterceptor{
//	/**
//	 * 前置通知
//	 */
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
//		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "").replaceFirst("/", "");  
//		HandlerMethod methodHandler=(HandlerMethod) handler;
//		//不需要登陆验证的URL（优先级最高第一步拦截）
//		AuthUneedLogin authUneedLogin=methodHandler.getMethodAnnotation(AuthUneedLogin.class);
//		//如果获取到方法是无需登录则放开 让其走
//		if(null != authUneedLogin){
//			return true;
//		}
//		//过滤公共功能
//		String XtFunctioninfoCommon = CommonUtils.getXtFunctioninfoCommonCache();
//		if(XtFunctioninfoCommon.indexOf(","+requestUrl+",") >= 0){
//			return true;
//		}
//		//如果超级管理员则放过所有功能
//		if(isAdmin()){
//			return true;
//		}
//		//非超级管理员则进行功能权限验证
//		String xt_functioninfoURL = (String)request.getSession(false).getAttribute("xt_functioninfoURL");
//		if(xt_functioninfoURL.indexOf(","+requestUrl+",")<0){
//			String head = request.getHeader("x-requested-with");
//			//XMLHttpRequest为异步 Ext.basex为同步 则Ajax拦截
//			if(null != head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head))) { 
//				response.setContentType("text/html;charset=utf-8");  
//	        	response.getWriter().print("{xt_pt_status:777}");
//	        	response.getWriter().flush();
//			}else{
//				//发送至拦截页面
//				response.sendRedirect(request.getContextPath()+"/index/have_no_permission.html");  
//			}
//			return false;  
//		}
//		return true;
//	}
//	/**
//	 * 后置通知
//	 */
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)throws Exception {
////		System.out.println("--------jinglai-----------"+arg2.toString());
////		System.out.println(arg3.toString());
//	}
//	/**
//	 * 
//	 */
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
//			Object arg2, ModelAndView arg3) throws Exception {
//	}
//}
