package jehc.xtmodules.xtcore.interceptor.csrf;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.constant.PathConstant;

/**
 * CsrfHandler拦截
 * @author 邓纯杰
 *
 */
public class CsrfHandler extends HandlerInterceptorAdapter{
	@Autowired 
    private CsrfTokenRepository csrfTokenRepository;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 拦截操作
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        HandlerMethod handlerMethod = (HandlerMethod) handler;
	        // 非控制器请求直接跳出
	        if (!(handler instanceof HandlerMethod)) {
	            return true;
	        }
	        CsrfToken csrfToken = handlerMethod.getMethodAnnotation(CsrfToken.class);
	        // 判断是否含有@CsrfToken注解
	        if (null == csrfToken) {
	            return true;
	        }
	        // create、remove同时为true时异常
	        if (csrfToken.create() && csrfToken.remove()) {
	            logger.error("CsrfToken attr create and remove can Not at the same time to true!");
	            return renderError(request, response, Boolean.FALSE, "CsrfToken attr create and remove can Not at the same time to true!");
	        }
	        // 创建
	        if (csrfToken.create()) {
	            CsrfTokenBean token = csrfTokenRepository.generateToken(request);
	            csrfTokenRepository.saveToken(token, request, response);
	            // 缓存一个表单页面地址的url
	            csrfTokenRepository.cacheUrl(request, response);
	            request.setAttribute(token.getParameterName(), token);
	            return true;
	        }
	        // 判断是否ajax请求
	        boolean isAjax = CommonUtils.isAjaxReq(request);
	        // 校验，并且清除
	        CsrfTokenBean tokenBean = csrfTokenRepository.loadToken(request);
	        if (tokenBean == null) {
	            return renderError(request, response, isAjax, "CsrfToken is null!");
	        }
	        String actualToken = request.getHeader(tokenBean.getHeaderName());
	        if (actualToken == null) {
	            actualToken = request.getParameter(tokenBean.getParameterName());
	        }
	        if (!tokenBean.getToken().equals(actualToken)) {
	            return renderError(request, response, isAjax, "CsrfToken not eq!");
	        }
	        return true;
	    }
	    
	    private boolean renderError(HttpServletRequest request, HttpServletResponse response, boolean isAjax, String message) throws IOException, ServletException {
	        // 获取缓存的cacheUrl
	        String cachedUrl = csrfTokenRepository.getRemoveCacheUrl(request, response);
	        // ajax请求直接抛出异常,web统一处理异常
	        if (isAjax) {
	        	throw new ExceptionUtil(message);
	        }
	        // 非ajax CsrfToken校验异常，先清理token
	        csrfTokenRepository.saveToken(null, request, response);
	        logger.info("Csrf[redirectUrl]:\t" + cachedUrl);
	        response.sendRedirect(PathConstant.XT_ILLEGAL_JSP_PATH);
//	        request.getRequestDispatcher(PathConstant.XT_ILLEGAL_JSP_PATH).forward(request, response);  
	        return false;
	    }

	    /**
	     * 用于清理@CsrfToken保证只能请求成功一次
	     */
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
	        HandlerMethod handlerMethod = (HandlerMethod) handler;
	        // 非控制器请求直接跳出
	        if (!(handler instanceof HandlerMethod)) {
	            return;
	        }
	        CsrfToken csrfToken = handlerMethod.getMethodAnnotation(CsrfToken.class);
	        if (csrfToken == null || !csrfToken.remove()) {
	            return;
	        }
	        csrfTokenRepository.getRemoveCacheUrl(request, response);
	        csrfTokenRepository.saveToken(null, request, response);
	    }
}
