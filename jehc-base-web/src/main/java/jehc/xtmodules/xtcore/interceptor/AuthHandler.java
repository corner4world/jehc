package jehc.xtmodules.xtcore.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.annotation.AuthUneedLogin;
import jehc.xtmodules.xtcore.annotation.NeedLoginUnAuth;
import jehc.xtmodules.xtcore.base.BaseHttpSessionEntity;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.Logback4jUtil;
import jehc.xtmodules.xtcore.util.constant.PathConstant;
import jehc.xtmodules.xtcore.util.constant.SessionConstant;
import jehc.xtmodules.xtcore.util.constant.StatusConstant;
import jehc.xtmodules.xtmodel.XtUserinfo;

/**
 * 采用自定义注解做权限
 * 做互联网前端需要该方法（只要在方法名上加@AuthUneedLogin则表示无需登录或不加也表示无需登录，若加@AuthNeedLogin则表示需要登录才可以访问）
 * @author 邓纯杰
 *
 */
public class AuthHandler extends Logback4jUtil implements HandlerInterceptor {
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
	public String[] allowFunUrls;

	/** 还没发现可以直接配置不拦截的资源，所以在代码里面来排除* */
	public void setAllowFunUrls(String[] allowFunUrls) {
		this.allowFunUrls = allowFunUrls;
	}

	/**
	 * 前置通知
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		//1.开始时间  
		long beginTime = System.currentTimeMillis();
		//线程绑定变量（该数据只有当前请求的线程可见） 
        startTimeThreadLocal.set(beginTime);
        ///////////////////拦截IP黑户开始（优先级最高）///////////////////////
		if(!validateIP(request)) {  
			if(!bDownLoad(request, response)){
				return false;
			}
			String head = request.getHeader("x-requested-with");
			//XMLHttpRequest为异步 Ext.basex为同步
			if((null != head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head)))) { 
				response.setContentType("text/html;charset=utf-8");  
	        	response.getWriter().print("{"+StatusConstant.XT_PT_STATUS+":"+StatusConstant.XT_PT_STATUS_VAL_001+"}");
	        	response.getWriter().flush();
			}else{
				request.getRequestDispatcher(PathConstant.XT_ILLEGAL_JSP_PATH).forward(request, response);  
			}
			return false; 
		}
		///////////////////拦截IP黑户结束///////////////////////
		
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "").replaceFirst("/", "");  
		HandlerMethod methodHandler=(HandlerMethod) handler;
		//不需要登陆验证的URL
		AuthUneedLogin authUneedLogin=methodHandler.getMethodAnnotation(AuthUneedLogin.class);
		//需要登录验证的URL（目前不使用该注解 若authUneedLogin不需要登录不满足 则其下所有方法必须默认为需登录条件）
//		AuthNeedLogin authNeedLogin=methodHandler.getMethodAnnotation(AuthNeedLogin.class); 
		//如果获取到方法是无需登录则放开 让其走（优先级第二）
		if(null != authUneedLogin){
			return true;
		}
		//过滤druid
		if(((request.getRequestURI().indexOf(("druid"))> 0 ) && null == CommonUtils.getXtU())){
			 request.getRequestDispatcher(PathConstant.XT_SESSION_JSP_PATH).forward(request, response);  
			 return false;
		}
		//需要登录但无需拦截验证URL
		NeedLoginUnAuth needLoginUnAuth = methodHandler.getMethodAnnotation(NeedLoginUnAuth.class);
		if(null != needLoginUnAuth){
			//如果注解为需要用户登录 则判断该用户是否登录 如果登录了 则放开其操作该方法的权限 否则不通过
			 if(null == CommonUtils.getXtU()){
				 return false;
			 }else{
				 return true;
			 }
		}
		//验证当前用户是否登录（优先级第三）
        if(null != CommonUtils.getXtU()){
        	//////////////////对功能进行拦截开始///////////////////
        	BaseHttpSessionEntity baseHttpSessionEntity = (BaseHttpSessionEntity) request.getSession(false).getAttribute(SessionConstant.BASE_HTTP_SESSION);
        	//过滤公共功能
    		String XtFunctioninfoCommon = CommonUtils.getXtFunctioninfoCommonCache();
			if(!StringUtils.isEmpty(XtFunctioninfoCommon) && XtFunctioninfoCommon.indexOf(","+requestUrl+",") >= 0){
    			return dataAuth(request, response, requestUrl,baseHttpSessionEntity);
    		}
    		//如果超级管理员则放过所有功能
    		if(isAdmin()){
    			return true;
    		}
    		//非超级管理员则进行功能权限验证
    		String xt_functioninfoURL = baseHttpSessionEntity.getXT_FUNCTIONINFOURL();
    		if(xt_functioninfoURL.indexOf(","+requestUrl+",")<0){
    			if(!bDownLoad(request, response)){
    				return false;
    			}
    			String head = request.getHeader("x-requested-with");
    			//XMLHttpRequest为异步 Ext.basex为同步 则Ajax拦截
    			if((null != head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head)))) { 
    				response.setContentType("text/html;charset=utf-8");  
    	        	response.getWriter().print("{"+StatusConstant.XT_PT_STATUS+":"+StatusConstant.XT_PT_STATUS_VAL_777+"}");
    	        	response.getWriter().flush();
    			}else{
    				//发送至拦截页面
    				request.getRequestDispatcher(PathConstant.XT_NO_ROLE_JSP_PATH).forward(request, response);  
    			}
    			return false;  
    		}else{
				return dataAuth(request, response, requestUrl,baseHttpSessionEntity);
    		}
    		//////////////////对功能进行拦截结束///////////////////
        }else{
        	//非法请求【超时请求】即这些请求需要登录后才能访问  
        	if(!bDownLoad(request, response)){
        		return false;
        	}
        	//重定向到登录页面  
			String head = request.getHeader("x-requested-with");
			//XMLHttpRequest为异步 Ext.basex为同步
			if((null != head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head)))) { 
				response.setContentType("text/html;charset=utf-8");  
	        	response.getWriter().print("{"+StatusConstant.XT_PT_STATUS+":"+StatusConstant.XT_PT_STATUS_VAL_888+"}");
	        	response.getWriter().flush();
			}else{
				request.getRequestDispatcher(PathConstant.XT_SESSION_JSP_PATH).forward(request, response);  
			}
        	return false;
        }
	}

	/**
	 * 后置通知
	 */
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception {
		//得到线程绑定的局部变量（开始时间）
		long beginTime = startTimeThreadLocal.get();
		//结束时间  
		long endTime = System.currentTimeMillis(); 	
        //耗时
        long usedTime = (endTime - beginTime);
        //URI
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        //最大内存(单位m)
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;
        //已分配内存
        long totalMemory = Runtime.getRuntime().totalMemory()/1024/1024;
        //已分配内存中的剩余空间
        long freeMemory = Runtime.getRuntime().freeMemory()/1024/1024;
        //最大可用内存
        long useMemory= (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024;
        StringBuilder sbuilder = new StringBuilder();
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            for(MethodParameter methodParameter : methodParameters){
            	sbuilder.append(methodParameter.getParameterName());
            }
        }
        info("{拦截器日志处理类","{\"开始时间\":\""+beginTime+"\",计时结束\":\""+endTime+"\",\"耗时\":\""+usedTime+"\",\"最大内存(单位m)\":\""+maxMemory+"\",\"已分配内存\":\""+totalMemory+"\",\"已分配内存中的剩余空间\":\""+freeMemory+"\",\"最大可用内存\":\""+useMemory+"\",\"参数\":\""+sbuilder.toString()+"\",\"uri\":\""+uri+"\"}}");
	}
	/**
	 * 环绕通知
	 */
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response,
			Object handler, 
			ModelAndView modelAndView) throws Exception {
		if (null != modelAndView){
			modelAndView.getViewName();
		}
	}
	
	/**
	 * 是否能够下载文件（jquery模式 非extjs模式）
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public boolean bDownLoad(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 String bdownflag = request.getParameter(StatusConstant.BDOWNFLAG);
		 if(!StringUtils.isEmpty(bdownflag)){
			response.setContentType("text/html;charset=utf-8");  
        	response.getWriter().print(StatusConstant.BDOWNFLAG_TEXT);
        	response.getWriter().flush();
        	return false;
		}
		 return true;
	}
	/**
	 * 判断IP是否合法 合法true 不合法false
	 * @param request
	 * @return
	 */
	public boolean validateIP(HttpServletRequest request){
		String ip = request.getRemoteAddr();
		boolean flag = CommonUtils.getXtIpFrozenCache(ip);
		return flag;
	}
	
	/**
	 * 处理数据权限
	 * @param request
	 * @param response
	 * @param requestUrl
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public boolean dataAuth(HttpServletRequest request,HttpServletResponse response,String requestUrl,BaseHttpSessionEntity baseHttpSessionEntity) throws IOException{
		String[] paramNames = (String[])request.getParameterValues("systemUID");//唯一标志systemUID
		List<String> systemUandM = baseHttpSessionEntity.getSYSTEMUANDM();
		List<String> sysUID = new ArrayList<String>();
		//如果系统唯一标志不为空 说明系统采用了数据权限
		if(null != paramNames){
			//参数组成的数组
			String systemUID = paramNames[0];
			String[] systemUIDarray = new String[]{};
			if(null != systemUID && !"".equals(systemUID)){
				systemUIDarray = systemUID.split(",");
			}
			if(null != systemUandM){
				int result = 0;
				for(String str:systemUandM){
					String[] sysUandMarray = new String[]{};
					if(!StringUtil.isEmpty(str)){
						sysUandMarray = str.split("#");
						if(null != sysUandMarray){
							//判断方法和参数都匹配
							if(("@"+sysUandMarray[1]+"@").indexOf("@"+requestUrl+"@") >= 0){
								for(int j = 0; j<systemUIDarray.length;j++){
									if(sysUandMarray[0].equals(systemUIDarray[j])){
										//如果相等
										result = result+1;
									}
								}
							}
						}
					}
				}
				//如果参数全部符合则进入方法
				if(result != systemUIDarray.length){
					//没有权限操作
					response.setContentType("text/html;charset=utf-8"); 
					response.getWriter().write("{success:false,msg:'您没有该操作权限,请与管理员联系!'}");
					return false;
				}
			}
		}else{
			//否则过滤当前操作是否数据权限查询拦截
			//说明可能是第一次初始化读取数据
			if(null != systemUandM){
				for(String str: systemUandM){
					String[] sysUandMarray = new String[]{};
					if(!StringUtil.isEmpty(str)){
						sysUandMarray = str.split("#");
						if(("@"+sysUandMarray[1]+"@").indexOf("@"+requestUrl+"@") >= 0){
							sysUID.add(sysUandMarray[0]);
						}
					}
				}
				request.setAttribute("sysUID", sysUID);//用户ID
			}
		}
		return true;
	}
}