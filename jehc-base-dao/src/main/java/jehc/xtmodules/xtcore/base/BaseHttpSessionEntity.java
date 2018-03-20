package jehc.xtmodules.xtcore.base;

import java.io.Serializable;
import java.util.List;

import jehc.xtmodules.xtmodel.XtUserinfo;

/**
 * HttpSession
 * @author 邓纯杰
 *
 */
public class BaseHttpSessionEntity implements Serializable{
	private static final long serialVersionUID = -2879280824756599022L;

	private XtUserinfo XTUSERINFO;
	
	private String XT_ROLE_ID;
	
	private String XT_FUNCTIONINFOURL;
	
	private String XT_FUNCTIONINFOMETHOD;
	
	private List<String> SYSTEMUANDM;
	
	private String SESSION_ID;
	
	public BaseHttpSessionEntity(){
		
	}
	public BaseHttpSessionEntity(XtUserinfo XTUSERINFO){
		this.XTUSERINFO = XTUSERINFO;
	}
	public BaseHttpSessionEntity(XtUserinfo XTUSERINFO,String XT_ROLE_ID){
		this.XTUSERINFO = XTUSERINFO;
		this.XT_ROLE_ID = XT_ROLE_ID;
	}
	public BaseHttpSessionEntity(XtUserinfo XTUSERINFO,String XT_ROLE_ID,String XT_FUNCTIONINFOURL){
		this.XTUSERINFO = XTUSERINFO;
		this.XT_ROLE_ID = XT_ROLE_ID;
		this.XT_FUNCTIONINFOURL = XT_FUNCTIONINFOURL;
	}
	public BaseHttpSessionEntity(XtUserinfo XTUSERINFO,String XT_ROLE_ID,String XT_FUNCTIONINFOURL,String XT_FUNCTIONINFOMETHOD){
		this.XTUSERINFO = XTUSERINFO;
		this.XT_ROLE_ID = XT_ROLE_ID;
		this.XT_FUNCTIONINFOURL = XT_FUNCTIONINFOURL;
		this.XT_FUNCTIONINFOMETHOD = XT_FUNCTIONINFOMETHOD;
	}
	public BaseHttpSessionEntity(XtUserinfo XTUSERINFO,String XT_ROLE_ID,String XT_FUNCTIONINFOURL,String XT_FUNCTIONINFOMETHOD,List<String> SYSTEMUANDM){
		this.XTUSERINFO = XTUSERINFO;
		this.XT_ROLE_ID = XT_ROLE_ID;
		this.XT_FUNCTIONINFOURL = XT_FUNCTIONINFOURL;
		this.XT_FUNCTIONINFOMETHOD = XT_FUNCTIONINFOMETHOD;
		this.SYSTEMUANDM = SYSTEMUANDM;
	}
	public BaseHttpSessionEntity(XtUserinfo XTUSERINFO,String XT_ROLE_ID,String XT_FUNCTIONINFOURL,String XT_FUNCTIONINFOMETHOD,List<String> SYSTEMUANDM,String SESSION_ID){
		XTUSERINFO.setSessionId(SESSION_ID);
		this.XTUSERINFO = XTUSERINFO;
		this.XT_ROLE_ID = XT_ROLE_ID;
		this.XT_FUNCTIONINFOURL = XT_FUNCTIONINFOURL;
		this.XT_FUNCTIONINFOMETHOD = XT_FUNCTIONINFOMETHOD;
		this.SYSTEMUANDM = SYSTEMUANDM;
		this.SESSION_ID = SESSION_ID;
	}
	public XtUserinfo getXTUSERINFO() {
		return XTUSERINFO;
	}
	public void setXTUSERINFO(XtUserinfo xTUSERINFO) {
		XTUSERINFO = xTUSERINFO;
	}
	public String getXT_ROLE_ID() {
		return XT_ROLE_ID;
	}
	public void setXT_ROLE_ID(String xT_ROLE_ID) {
		XT_ROLE_ID = xT_ROLE_ID;
	}
	public String getXT_FUNCTIONINFOURL() {
		return XT_FUNCTIONINFOURL;
	}
	public void setXT_FUNCTIONINFOURL(String xT_FUNCTIONINFOURL) {
		XT_FUNCTIONINFOURL = xT_FUNCTIONINFOURL;
	}
	public String getXT_FUNCTIONINFOMETHOD() {
		return XT_FUNCTIONINFOMETHOD;
	}
	public void setXT_FUNCTIONINFOMETHOD(String xT_FUNCTIONINFOMETHOD) {
		XT_FUNCTIONINFOMETHOD = xT_FUNCTIONINFOMETHOD;
	}
	public List<String> getSYSTEMUANDM() {
		return SYSTEMUANDM;
	}
	public void setSYSTEMUANDM(List<String> sYSTEMUANDM) {
		SYSTEMUANDM = sYSTEMUANDM;
	}
	public String getSESSION_ID() {
		return SESSION_ID;
	}
	public void setSESSION_ID(String sESSION_ID) {
		SESSION_ID = sESSION_ID;
	}
	
}
