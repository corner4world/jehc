package jehc.xtmodules.xtweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 处理异常页面
 * @author邓纯杰
 *
 */
public class Xt_Error_PageController {
	/** 
	 * 请求异常 
	 * @return 
	 * @throws Exception 
	 * String 
	 */  
	@RequestMapping(value = "/error_404", produces = "text/html;charset=UTF-8")  
	@ResponseBody  
	public String error_404() throws Exception {   
	     return "{\"msg\":\"找不到页面\",\"xt_pt_status\":\"404\"}";  
	} 
	
	/** 
	 * 服务器异常 
	 * @return 
	 * String 
	 */  
	@RequestMapping(value ="/error_500", produces = "text/html;charset=UTF-8")  
	public String error_500(){     
		return "{\"msg\":\"服务器处理失败\",\"xt_pt_status\":\"500\"}";  
	}  
}
