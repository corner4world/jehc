package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_platform 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
*/
public class Xt_Platform extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_platform_id;/**主键**/
	private String xt_platform_title;/****/
	private String xt_platform_status;/**状态0正常1关闭**/
	private String xt_userinfo_id;/**操作人**/
	private String xt_platform_remark;/**注备**/
	private String xt_platform_ctime;/**创建时间**/
	private String xt_platform_mtime;/**修改时间**/
	private String xt_Platform_FeedbackList;/**回复信息JSON格式**/
	public void setXt_platform_id(String xt_platform_id){
		this.xt_platform_id=xt_platform_id;
	}
	public String getXt_platform_id(){
		return xt_platform_id;
	}
	public void setXt_platform_title(String xt_platform_title){
		this.xt_platform_title=xt_platform_title;
	}
	public String getXt_platform_title(){
		return xt_platform_title;
	}
	public void setXt_platform_status(String xt_platform_status){
		this.xt_platform_status=xt_platform_status;
	}
	public String getXt_platform_status(){
		return xt_platform_status;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_platform_remark(String xt_platform_remark){
		this.xt_platform_remark=xt_platform_remark;
	}
	public String getXt_platform_remark(){
		return xt_platform_remark;
	}
	public void setXt_platform_ctime(String xt_platform_ctime){
		this.xt_platform_ctime=xt_platform_ctime;
	}
	public String getXt_platform_ctime(){
		return xt_platform_ctime;
	}
	public void setXt_platform_mtime(String xt_platform_mtime){
		this.xt_platform_mtime=xt_platform_mtime;
	}
	public String getXt_platform_mtime(){
		return xt_platform_mtime;
	}
	public String getXt_Platform_FeedbackList() {
		return xt_Platform_FeedbackList;
	}
	public void setXt_Platform_FeedbackList(String xt_Platform_FeedbackList) {
		this.xt_Platform_FeedbackList = xt_Platform_FeedbackList;
	}
}
