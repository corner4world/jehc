package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_loadinfo 页面加载信息 
* 2015-05-14 16:24:20  邓纯杰
*/
public class XtLoadinfo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_loadinfo_id;/**ID**/
	private String xt_loadinfo_modules;/**载加模块**/
	private int xt_loadinfo_time;/**页面加载时间**/
	private String xt_loadinfo_begtime;/**载入时间**/
	private String xt_loadinfo_endtime;/**载入结束时间**/
	private String xt_userinfo_id;/**操作人ID**/
	public void setXt_loadinfo_id(String xt_loadinfo_id){
		this.xt_loadinfo_id=xt_loadinfo_id;
	}
	public String getXt_loadinfo_id(){
		return xt_loadinfo_id;
	}
	public void setXt_loadinfo_modules(String xt_loadinfo_modules){
		this.xt_loadinfo_modules=xt_loadinfo_modules;
	}
	public String getXt_loadinfo_modules(){
		return xt_loadinfo_modules;
	}
	public void setXt_loadinfo_time(int xt_loadinfo_time){
		this.xt_loadinfo_time=xt_loadinfo_time;
	}
	public int getXt_loadinfo_time(){
		return xt_loadinfo_time;
	}
	public void setXt_loadinfo_begtime(String xt_loadinfo_begtime){
		this.xt_loadinfo_begtime=xt_loadinfo_begtime;
	}
	public String getXt_loadinfo_begtime(){
		return xt_loadinfo_begtime;
	}
	public void setXt_loadinfo_endtime(String xt_loadinfo_endtime){
		this.xt_loadinfo_endtime=xt_loadinfo_endtime;
	}
	public String getXt_loadinfo_endtime(){
		return xt_loadinfo_endtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
