package jehc.lcmodules.lcmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* lc_status 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
public class Lc_Status extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lc_status_id;/**流程状态编号**/
	private String lc_status_name;/**状态名称**/
	private String lc_status_remark;/**备注**/
	private String lc_status_constant;/**流程常量**/
	private String lc_status_ctime;/**创建时间**/
	private String lc_status_mtime;/**修改时间**/
	private String xt_userinfo_id;/**操作人**/
	public void setLc_status_id(String lc_status_id){
		this.lc_status_id=lc_status_id;
	}
	public String getLc_status_id(){
		return lc_status_id;
	}
	public void setLc_status_name(String lc_status_name){
		this.lc_status_name=lc_status_name;
	}
	public String getLc_status_name(){
		return lc_status_name;
	}
	public void setLc_status_remark(String lc_status_remark){
		this.lc_status_remark=lc_status_remark;
	}
	public String getLc_status_remark(){
		return lc_status_remark;
	}
	public void setLc_status_constant(String lc_status_constant){
		this.lc_status_constant=lc_status_constant;
	}
	public String getLc_status_constant(){
		return lc_status_constant;
	}
	public void setLc_status_ctime(String lc_status_ctime){
		this.lc_status_ctime=lc_status_ctime;
	}
	public String getLc_status_ctime(){
		return lc_status_ctime;
	}
	public void setLc_status_mtime(String lc_status_mtime){
		this.lc_status_mtime=lc_status_mtime;
	}
	public String getLc_status_mtime(){
		return lc_status_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
