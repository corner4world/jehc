package jehc.xtmodules.xtcore.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import jehc.xtmodules.xtcore.base.BaseExceptionEntity;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.JsonUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.constant.PathConstant;
import jehc.xtmodules.xtcore.util.constant.StatusConstant;
import jehc.xtmodules.xtmodel.XtErrorLogs;
import jehc.xtmodules.xtservice.XtErrorLogsService;
import net.sf.json.JSONObject;

/**
 * 捕捉平台所有发生异常拦截
 * @author邓纯杰
 *
 */
public class ExceptionHandler extends SimpleMappingExceptionResolver implements HandlerExceptionResolver{
	@Autowired
	private XtErrorLogsService xt_Error_LogsService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 控制层异常拦截
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {  
        Map<String, Object> model = new HashMap<String, Object>();  
        model.put(StatusConstant.EX, ex); 
        /**
        System.out.println("--------------控制层进入异常发生------------------"); 
        System.out.println("异常类型--------------------"+ex);
        System.out.println("异常信息--------------------"+ex.getMessage());
        System.out.println("异常原因--------------------"+ex.getCause());
        **/
        XtErrorLogs xt_Error_Logs = new XtErrorLogs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        xt_Error_Logs.setXt_error_log_id(UUID.toUUID());
        xt_Error_Logs.setXt_error_logContent(ex.getMessage());
        xt_Error_Logs.setXt_error_logTime(sdf.format(new Date()));
        int i = 0;
        if(ex instanceof IOException){  
        	i = 1;
            //System.out.println("--------------IO异常------------------"); 
        }else if(ex instanceof SQLException){  
        	i = 2;
        	//System.out.println("--------------SQL异常------------------");  
        }else if(ex instanceof RuntimeException){
        	i = 3;
        	//System.out.println("--------------JAVA运行时发生异常------------------"); 
        }else if(ex instanceof NullPointerException) {  
        	i = 4;
        	//System.out.println("--------------空指针异常------------------"); 
        }else if(ex instanceof NumberFormatException){
        	i = 5;
        	//System.out.println("--------------数字格式化异常------------------"); 
        }
        xt_Error_Logs.setXt_error_logType(i);
        xt_Error_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
        xt_Error_LogsService.addXtErrorLogs(xt_Error_Logs);
        String head = request.getHeader("x-requested-with");
		//XMLHttpRequest为异步 Ext.basex为同步
		if(null != head && (head.equalsIgnoreCase("XMLHttpRequest")|| "Ext.basex".equalsIgnoreCase(head))) { 
            try {
            	PrintWriter writer = response.getWriter();
            	BaseExceptionEntity baseExceptionEntity = new BaseExceptionEntity(StatusConstant.XT_PT_STATUS_VAL_500, ex.getMessage());
            	JSONObject json = JsonUtil.toJsonObj(baseExceptionEntity);
            	writer.write(json.toString());
                writer.flush();
				logger.debug("异步请求捕捉到异常输出字符串结束");
            } catch (IOException e) {
            	logger.debug("异步请求捕捉到异常后发送输出操作出现异常，IOException:"+e.getMessage());
			}
            return null;
		}
        /**向数据库中插入数据**/
		logger.debug("同步请求捕捉到异常输出字符串结束");
        return new ModelAndView(PathConstant.XT_ERROR_JSP_PATH, model);  
    }  
}
