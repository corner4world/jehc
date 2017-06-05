package jehc.xtmodules.xtcore.base;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sun.beans.editors.DoubleEditor;
import sun.beans.editors.FloatEditor;
import sun.beans.editors.LongEditor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.star.bridge.oleautomation.Decimal;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.binder.IntegerEditor;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
 * 
 * @author邓纯杰
 *
 */
public class BaseAction extends CommonUtils{
	
	/**
	 * 获取Request对象
	 * @return
	 */
    protected static HttpServletRequest getReq(){
    	return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
   
	/**
	 * 采用PageHelper分页方式一
	 * 输出带分页的JSON格式字符串
	 * @param jsonArray
	 */
	protected String outPageStr(List list,long total,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
        try{
    		String offset = request.getParameter("start");
    		String pageSize = request.getParameter("limit");
    		if(null != pageSize){
    			limit = new Integer(pageSize);
    		}
    		if(null != offset){
    			start = new Integer(offset);
    			/**Extjs分页无需对start处理
    			start = new Integer(offset);
    			if(start > 0){
    				start = (start-1)*limit;
    			}else{
    				start = 0;
    			}
    			**/
    		}
    		JSONArray jsonArray = JSONArray.fromObject(list);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+start+",limit:"+limit+",total:"+total+",items:"+jsonStr+"}";
            return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载分页列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 采用PageHelper分页方式二
	 * 输出带分页的JSON格式字符串
	 * @param jsonArray
	 */
	protected String outPageStr(Object object,long total,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
        try{
    		String offset = request.getParameter("start");
    		String pageSize = request.getParameter("limit");
    		if(null != pageSize){
    			limit = new Integer(pageSize);
    		}
    		if(null != offset){
    			start = new Integer(offset);
    			/**Extjs分页无需对start处理
    			start = new Integer(offset);
    			if(start > 0){
    				start = (start-1)*limit;
    			}else{
    				start = 0;
    			}
    			**/
    		}
    		JSONArray jsonArray = JSONArray.fromObject(object);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+start+",limit:"+limit+",total:"+total+",items:"+jsonStr+"}";
            return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载分页列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 采用PageHelper分页方式三
	 * 输出带分页的JSON格式字符串
	 * @param jsonArray
	 */
	protected String outPageStr(PageInfo page,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
        try{
    		String offset = request.getParameter("start");
    		String pageSize = request.getParameter("limit");
    		if(null != pageSize){
    			limit = new Integer(pageSize);
    		}
    		if(null != offset){
    			start = new Integer(offset);
    			/**Extjs分页无需对start处理
    			start = new Integer(offset);
    			if(start > 0){
    				start = (start-1)*limit;
    			}else{
    				start = 0;
    			}
    			**/
    		}
    		JSONArray jsonArray = JSONArray.fromObject(page.getList());  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+start+",limit:"+limit+",total:"+page.getTotal()+",items:"+jsonStr+"}";
    		return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载分页列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	
	/**
	 * 分页方式一
	 * 输出带分页的JSON格式字符串
	 * @param jsonArray
	 */
	protected String outPageStr(List list,int total,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
        try{
    		String offset = request.getParameter("start");
    		String pageSize = request.getParameter("limit");
    		if(null != pageSize){
    			limit = new Integer(pageSize);
    		}
    		if(null != offset){
    			start = new Integer(offset);
    			/**Extjs分页无需对start处理
    			start = new Integer(offset);
    			if(start > 0){
    				start = (start-1)*limit;
    			}else{
    				start = 0;
    			}
    			**/
    		}
    		JSONArray jsonArray = JSONArray.fromObject(list);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+start+",limit:"+limit+",total:"+total+",items:"+jsonStr+"}";
    		return jsonString;
        }catch(Exception e){ 
        	throw new ExceptionUtil("加载分页列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	
	/**
	 * 分页方式二
	 * 输出带分页的JSON格式字符串
	 * @param jsonArray
	 */
	protected String outPageStr(JSONArray jsonArray,int total,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
        try{
    		String offset = request.getParameter("start");
    		String pageSize = request.getParameter("limit");
    		if(null != pageSize){
    			limit = new Integer(pageSize);
    		}
    		if(null != offset){
    			start = new Integer(offset);
    			/**Extjs分页无需对start处理
    			start = new Integer(offset);
    			if(start > 0){
    				start = (start-1)*limit;
    			}else{
    				start = 0;
    			}
    			**/
    		}
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+start+",limit:"+limit+",total:"+total+",items:"+jsonStr+"}";
            return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载分页列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 分页方式三
	 * DataGrid
	 * 输出带分页的JSON格式字符串
	 * @param jsonArray
	 */
	protected String outPageStr(List list,int total,String items,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
        try{
    		String offset = request.getParameter("start");
    		String pageSize = request.getParameter("limit");
    		if(null != pageSize){
    			limit = new Integer(pageSize);
    		}
    		if(null != offset){
    			start = new Integer(offset);
    			/**Extjs分页无需对start处理
    			start = new Integer(offset);
    			if(start > 0){
    				start = (start-1)*limit;
    			}else{
    				start = 0;
    			}
    			**/
    		}
    		JSONArray jsonArray = JSONArray.fromObject(list);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+start+",limit:"+limit+",total:"+total+","+items+":"+jsonStr+"}";
    		return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载分页列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 输出添加删除修改结果JSON格式字符串
	 * @param flag
	 * @param msg
	 */
	protected String outAudStr(boolean flag,String msg){
		if(flag){
			return "{success:true,msg:'"+msg+"',responseFlag:true}";
		}else{
			return "{success:false,msg:'"+msg+"',responseFlag:false}";
		}
	}
	
	/**
	 * 输出添加删除修改结果JSON格式字符串
	 * @param flag
	 * @param msg
	 */
	protected String outAudStr(boolean flag){
		if(flag){
			return "{success:true,msg:'"+CommonUtils.getCacheStr("sys_operate_sucess")+"',responseFlag:true}";
		}else{
			return "{success:false,msg:'"+CommonUtils.getCacheStr("sys_operate_error")+"',responseFlag:false}";
		}
	}
	
	/**
	 * 输出添加删除修改结果JSON格式字符串
	 * @param flag
	 * @param msg
	 */
	protected String outAudStr(BaseResponse baseResponse){
		return "{sucess:"+baseResponse.isSucess()+",msg:'"+baseResponse.getMsg()+"',code:'"+baseResponse.getCode()+"',data:'"+baseResponse.getData()+"'}";
	}
	
	
	/**
	 * 方式一
	 * 输出树JSON格式字符串
	 * @param jsonArray
	 */
	protected String outRootStr(Object obj){
        try{  
        	if(null == obj){
				return "{sucess:true,items:''}";
			}
        	JSONArray jsonArray = JSONArray.fromObject(obj); 
            String jsonStr = jsonArray.toString();
     	    String jsonString = "{sucess:true,items:"+jsonStr+"}";
     	    return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载输出树JSON格式字符串列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 方式二
	 * 输出树JSON格式字符串
	 * @param jsonArray
	 */
	protected String outRootStr(JSONArray jsonArray){
        try{   
        	if(null == jsonArray){
				return "{sucess:true,items:''}";
			}
           String jsonStr = jsonArray.toString();
     	   String jsonString = "{sucess:true,items:"+jsonStr+"}";
     	   return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("加载输出树JSON格式字符串列表出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 方式一
	 * 输出查找对象的JSON格式字符串 或集合JSON格式字符串
	 * @param jsonArray
	 */
	protected String outItemsStr(Object obj){
		try{ 
			if(null == obj){
				return "{sucess:true,items:''}";
			}
			JSONArray jsonArray = JSONArray.fromObject(obj); 
			String jsonStr = jsonArray.toString();
	        String jsonString = "{sucess:true,items:"+jsonStr+"}";
	        return jsonString;
        }catch(Exception e){     
        	throw new ExceptionUtil("输出查找对象的JSON格式字符串 或集合JSON格式字符串出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 方式二
	 * 输出查找对象的JSON格式字符串 或集合JSON格式字符串
	 * @param jsonArray
	 */
	protected String outItemsStr(JSONArray jsonArray){
		try{ 
			if(null == jsonArray){
				return "{sucess:true,items:''}";
			}
			String jsonStr = jsonArray.toString();
	        String jsonString = "{sucess:true,items:"+jsonStr+"}";
	        return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("输出查找对象的JSON格式字符串 或集合JSON格式字符串出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	
	/**
	 * 方式三 E5 FORM加载写法
	 * 输出查找对象的JSON格式字符串 或集合JSON格式字符串
	 * @param obj
	 */
	protected String outDataStr(Object obj){
		try{ 
			if(null == obj){
				return "{sucess:true,data:''}";
			}
			JSONArray jsonArray = JSONArray.fromObject(obj); 
			String jsonStr = jsonArray.toString();
			jsonStr = jsonStr.substring(1,jsonStr.length()-1);
	        String jsonString = "{success:true,data:"+jsonStr+"}";
	        return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("输出查找对象的JSON格式字符串 或集合JSON格式字符串出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	
	/**
	 * 方式三 E5 FORM加载写法
	 * 输出查找对象的JSON格式字符串 或集合JSON格式字符串
	 * @param jsonArray
	 */
	protected String outDataStr(JSONArray jsonArray){
		try{ 
			if(null == jsonArray){
				return "{sucess:true,data:''}";
			}
			String jsonStr = jsonArray.toString();
			jsonStr = jsonStr.substring(1,jsonStr.length()-1);
	        String jsonString = "{success:true,data:"+jsonStr+"}";
	        return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("输出查找对象的JSON格式字符串 或集合JSON格式字符串出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	
	/**
	 * 输出TreeGrid JSON格式字符串
	 * @param jsonStr
	 */
	protected String outTreeGridStr(String jsonStr){
		return jsonStr;
	}
	
	/**
	 * 输出普通的字符串
	 * @param jsonStr
	 */
	protected String outStr(String jsonStr){
		return jsonStr;
	}
	
	/**
	 * E5 下拉框
	 * 输出查找对象的JSON格式字符串 或集合JSON格式字符串
	 * @param jsonArray
	 */
	protected String outComboDataStr(Object obj){
		try{ 
			if(null == obj){
				return "{sucess:true,items:''}";
			}
			JSONArray jsonArray = JSONArray.fromObject(obj); 
			String jsonStr = jsonArray.toString();
	        String jsonString = "{success:true,items:"+jsonStr+"}";
	        return jsonString;
        }catch(Exception e){      
        	throw new ExceptionUtil("输出查找对象的JSON格式字符串 或集合JSON格式字符串出现异常：原因【"+e.getCause()+"】 详细信息【"+e.getMessage()+"】");
        }
	}
	/**
	 * 封装共同的数据权限获取参数
	 * @param condition
	 */
	protected void commonSysUID(String key,Map<String, Object> condition,HttpServletRequest request){
		String sysUID = (String)request.getSession().getAttribute("sysUID");
		if(null != sysUID && !"".equals(sysUID)){
			condition.put(key,sysUID);
		}else{
			condition.put(key,"0");
		}
	}
	
	/**
	 * 封装共同分页参数
	 */
	protected void commonPager(Map<String, Object> condition,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
		String offset = request.getParameter("start");
		String pageSize = request.getParameter("limit");
		if(null != pageSize){
			limit = new Integer(pageSize);
		}
		if(null != offset){
			start = new Integer(offset);
			/**Extjs分页无需对start处理
			start = new Integer(offset);
			if(start > 0){
				start = (start-1)*limit;
			}else{
				start = 0;
			}
			**/
		}
		condition.put("offset", start);
		condition.put("pageSize", limit);
	}
	/**
	 * 封装共同分页参数
	 */
	protected void commonHPager(Map<String, Object> condition,HttpServletRequest request){
		Integer start = 0;
		Integer limit = 30;
		String offset = request.getParameter("start");
		String pageSize = request.getParameter("limit");
		if(null != pageSize){
			limit = new Integer(pageSize);
		}
		if(null != offset){
			start = new Integer(offset);
			/**Extjs分页无需对start处理
			start = new Integer(offset);
			if(start > 0){
				start = (start-1)*limit;
			}else{
				start = 0;
			}
			**/
		}
		PageHelper.offsetPage(start, limit);
	}
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
        binder.registerCustomEditor(int.class, new IntegerEditor());  
        binder.registerCustomEditor(long.class, new LongEditor());  
        binder.registerCustomEditor(double.class, new DoubleEditor());  
        binder.registerCustomEditor(float.class, new FloatEditor()); 
	}
}
