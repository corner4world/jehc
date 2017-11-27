package jehc.xtmodules.xtcore.tag;

import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.constant.SessionConstant;

/**
 * 自定义标签 按钮显示权限
 * 
 * @author邓纯杰
 *
 */
public class JehcPermissionTag extends BodyTagSupport {
	private String modules;
	private String id;
	private String systemUID;/**数据权限唯一标识**/
	StringWriter sw = new StringWriter();

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSystemUID() {
		return systemUID;
	}

	public void setSystemUID(String systemUID) {
		this.systemUID = systemUID;
	}

	/**
	 *	普通权限及数据权限统一处理
	 */
	public int doStartTag() throws JspException { //在标签开始处出发该方法（the method starts at the beginning of the tag）
		if (!StringUtils.isEmpty(this.getModules())) {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return BodyTagSupport.SKIP_BODY;//不执行
			}
			if(CommonUtils.isAdmin()){//放开超级管理员
				// 允许访问标签body（allow access to tag body）
				return BodyTagSupport.EVAL_BODY_INCLUDE;// 返回此则执行标签body中内容，SKIP_BODY则不执行（If you return this, execute the content in the tag body, and SKIP_BODY does not） 
			}
			if(!StringUtils.isEmpty(this.getSystemUID())){
				//如果数据权限标识不为空 则开始进行数据权限验证
				List<String> systemUandM = (List<String>)request.getSession(false).getAttribute(SessionConstant.SYSTEMUANDM);
				if(!dataAuth(systemUandM, systemUID, modules)){
					//不满足条件 
					return BodyTagSupport.SKIP_BODY;
				}
			}else{
				//普通按钮权限验证
				String btnList = (String)request.getSession(false).getAttribute(SessionConstant.XT_FUNCTIONINFOMETHOD);
				btnList = ","+btnList+",";
				if(btnList.indexOf(","+this.getModules()+",")<0){
					//不满足条件 
					return BodyTagSupport.SKIP_BODY;
				}
			}
			// 允许访问标签body（allow access to tag body）
			return BodyTagSupport.EVAL_BODY_INCLUDE;// 返回此则执行标签body中内容，SKIP_BODY则不执行（If you return this, execute the content in the tag body, and SKIP_BODY does not）
		} else {
			return BodyTagSupport.SKIP_BODY;
		}
		
	}

	public int doEndTag() throws JspException {
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}
	

	/**
	 * 处理数据权限
	 * @param systemUandM
	 * @param systemUID
	 * @param modules 方法名
	 * @return
	 */
	public boolean dataAuth(List<String> systemUandM,String systemUID,String modules){
		//如果系统唯一标志不为空 说明系统采用了数据权限
		//参数组成的数组
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
						if(("@"+sysUandMarray[1]+"@").indexOf("@"+modules+"@") >= 0){
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
				return false;
			}
		}
		return true;
	}
}
