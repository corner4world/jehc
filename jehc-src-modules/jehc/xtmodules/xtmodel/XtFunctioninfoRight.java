package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_functioninfo_right 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
public class XtFunctioninfoRight extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -5796313721658973312L;
	private String xt_functioninfo_right_id;/**分配表编号**/
	private String xt_functioninfo_id;/**功能编号**/
	private String xt_role_id;/**角色编号**/
	private String xt_menuinfo_id;/**菜单编号**/
	
	
	private String xt_functioninfoURL;/**扩展---功能权限URL**/
	private String xt_functioninfoMethod;/**扩展---方法名**/
	private int xt_functioninfoType;/**扩展---是否需要初始化对其拦截0不需要1需要**/
	private int xt_functioninfoIsAuthority;/**扩展---是否进行数据权限0是1否**/
	public void setXt_functioninfo_right_id(String xt_functioninfo_right_id){
		this.xt_functioninfo_right_id=xt_functioninfo_right_id;
	}
	public String getXt_functioninfo_right_id(){
		return xt_functioninfo_right_id;
	}
	public void setXt_functioninfo_id(String xt_functioninfo_id){
		this.xt_functioninfo_id=xt_functioninfo_id;
	}
	public String getXt_functioninfo_id(){
		return xt_functioninfo_id;
	}
	public void setXt_role_id(String xt_role_id){
		this.xt_role_id=xt_role_id;
	}
	public String getXt_role_id(){
		return xt_role_id;
	}
	public void setXt_menuinfo_id(String xt_menuinfo_id){
		this.xt_menuinfo_id=xt_menuinfo_id;
	}
	public String getXt_menuinfo_id(){
		return xt_menuinfo_id;
	}
	public String getXt_functioninfoURL() {
		return xt_functioninfoURL;
	}
	public void setXt_functioninfoURL(String xt_functioninfoURL) {
		this.xt_functioninfoURL = xt_functioninfoURL;
	}
	public String getXt_functioninfoMethod() {
		return xt_functioninfoMethod;
	}
	public void setXt_functioninfoMethod(String xt_functioninfoMethod) {
		this.xt_functioninfoMethod = xt_functioninfoMethod;
	}
	public int getXt_functioninfoType() {
		return xt_functioninfoType;
	}
	public void setXt_functioninfoType(int xt_functioninfoType) {
		this.xt_functioninfoType = xt_functioninfoType;
	}
	public int getXt_functioninfoIsAuthority() {
		return xt_functioninfoIsAuthority;
	}
	public void setXt_functioninfoIsAuthority(int xt_functioninfoIsAuthority) {
		this.xt_functioninfoIsAuthority = xt_functioninfoIsAuthority;
	}
}
