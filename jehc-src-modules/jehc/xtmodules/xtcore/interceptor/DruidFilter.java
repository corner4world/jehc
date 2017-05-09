package jehc.xtmodules.xtcore.interceptor;
//package xtCore.interceptor;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Druid过滤器
// * @author 邓纯杰
// *
// */
//public class DruidFilter implements Filter{
//
//	public void destroy() {
//		
//	}
//	public void doFilter(ServletRequest request, ServletResponse response,FilterChain arg2) throws IOException, ServletException {
//		//把ServletRequest和ServletResponse转换成真正的类型
//        HttpServletRequest req = (HttpServletRequest)request;
//        HttpServletResponse  rep = (HttpServletResponse)response;
//        HttpSession session = req.getSession(false);
//        if(null != session && ((req.getRequestURI().indexOf(("druid"))> 0 ) && null == session.getAttribute("xtUserinfo"))){
//            ((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/index/sessionout.html");
//        }else{
//        	arg2.doFilter(req, rep);
//        }
//	}
//	public void init(FilterConfig arg0) throws ServletException {
//		
//	}
//}
