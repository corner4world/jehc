package jehc.xtmodules.xtcore.tag;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

	public int doStartTag() throws JspException { //在标签开始处出发该方法（the method starts at the beginning of the tag）
		if (this.getModules() != null) {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return BodyTagSupport.SKIP_BODY;//不执行
			}
			String btnList = (String)request.getSession(false).getAttribute(SessionConstant.XT_FUNCTIONINFOMETHOD);
			btnList = ","+btnList+",";
			if(btnList.indexOf(","+this.getModules()+",")<0){
				//不满足条件 
				return BodyTagSupport.SKIP_BODY;
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
}
