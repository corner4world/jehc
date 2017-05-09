package jehc.xtmodules.xtcore.interceptor;
//package xtCore.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import xtCore.util.CommonUtils;
///**
// * IP拦截
// * @author邓纯杰
// *
// */
//public class IPInterceptor implements HandlerInterceptor{
//	/**
//	 * 前置通知
//	 */
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
//		if(validateIP(request)) {  
//			 //返回true,则这个方面调用后会接着调用postHandle(),afterCompletion()
//			return true; 
//		}else{
//			String head = request.getHeader("x-requested-with");
//			//XMLHttpRequest为异步 Ext.basex为同步
//			if(null !=head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head))) { 
//				response.setContentType("text/html;charset=utf-8");  
//	        	response.getWriter().print("{xt_pt_status:001}");
//	        	response.getWriter().flush();
//				 return false;
//			}
//		    request.getRequestDispatcher("/WEB-INF/view/pc/xt-view/xt-illegal/xt-illegal.jsp").forward(request, response);  
//			return false;  
//		}
//	}
//	
//	/**
//	 * 后置通知
//	 */
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)throws Exception {
//	}
//	
//	/**
//	 * 环绕通知
//	 */
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
//			Object arg2, ModelAndView arg3) throws Exception {
//	}
//	
//	/**
//	 * 判断IP是否合法 合法true 不合法false
//	 * @param request
//	 * @return
//	 */
//	public boolean validateIP(HttpServletRequest request){
//		String ip = request.getRemoteAddr();
//		boolean flag = CommonUtils.getXtIpFrozenCache(ip);
//		return flag;
//	}
//}
