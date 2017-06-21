package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_functioninfo 功能表 
* 2015-06-01 20:41:56  邓纯杰
*/
public class Xt_Functioninfo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_functioninfo_id;/**ID**/
	private String xt_functioninfoName;/**功能名称**/
	private String xt_functioninfoTitle;/**功能标题**/
	private String xt_functioninfoURL;/**功能权限URL**/
	private String xt_menuinfo_id;/**所属模块 外键**/
	private String xt_functioninfoMethod;/**方法名**/
	private int xt_functioninfoType;/**是否需要初始化对其拦截0不需要1需要**/
	private int xt_functioninfoIsAuthority;/**是否进行数据权限0是1否**/
	private String xt_menuinfo_title;/**模块名称**/
	private String xt_functioninfoStatus;/**是否可用0可用1禁用**/
	public void setXt_functioninfo_id(String xt_functioninfo_id){
		this.xt_functioninfo_id=xt_functioninfo_id;
	}
	public String getXt_functioninfo_id(){
		return xt_functioninfo_id;
	}
	public void setXt_functioninfoName(String xt_functioninfoName){
		this.xt_functioninfoName=xt_functioninfoName;
	}
	public String getXt_functioninfoName(){
		return xt_functioninfoName;
	}
	public void setXt_functioninfoTitle(String xt_functioninfoTitle){
		this.xt_functioninfoTitle=xt_functioninfoTitle;
	}
	public String getXt_functioninfoTitle(){
		return xt_functioninfoTitle;
	}
	public void setXt_functioninfoURL(String xt_functioninfoURL){
		this.xt_functioninfoURL=xt_functioninfoURL;
	}
	public String getXt_functioninfoURL(){
		return xt_functioninfoURL;
	}
	public void setXt_menuinfo_id(String xt_menuinfo_id){
		this.xt_menuinfo_id=xt_menuinfo_id;
	}
	public String getXt_menuinfo_id(){
		return xt_menuinfo_id;
	}
	public void setXt_functioninfoMethod(String xt_functioninfoMethod){
		this.xt_functioninfoMethod=xt_functioninfoMethod;
	}
	public String getXt_functioninfoMethod(){
		return xt_functioninfoMethod;
	}
	public void setXt_functioninfoType(int xt_functioninfoType){
		this.xt_functioninfoType=xt_functioninfoType;
	}
	public int getXt_functioninfoType(){
		return xt_functioninfoType;
	}
	public void setXt_functioninfoIsAuthority(int xt_functioninfoIsAuthority){
		this.xt_functioninfoIsAuthority=xt_functioninfoIsAuthority;
	}
	public int getXt_functioninfoIsAuthority(){
		return xt_functioninfoIsAuthority;
	}
	public String getXt_menuinfo_title() {
		return xt_menuinfo_title;
	}
	public void setXt_menuinfo_title(String xt_menuinfo_title) {
		this.xt_menuinfo_title = xt_menuinfo_title;
	}
	public String getXt_functioninfoStatus() {
		return xt_functioninfoStatus;
	}
	public void setXt_functioninfoStatus(String xt_functioninfoStatus) {
		this.xt_functioninfoStatus = xt_functioninfoStatus;
	}
}
