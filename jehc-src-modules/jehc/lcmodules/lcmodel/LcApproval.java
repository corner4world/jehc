package jehc.lcmodules.lcmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* lc_approval 工作流批审表 
* 2017-01-08 17:06:33  邓纯杰
*/
public class LcApproval extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lc_approval_id;/**主键**/
	private String lc_status_id;/**批审状态**/
	private String lc_apply_id;/**申请编号**/
	private String lc_approval_remark;/**作流工审批内容**/
	private String taskId;/**activiti任务id**/
	private String lc_approval_time;/**审批时间**/
	private String xt_userinfo_id;/**批审人**/
	private String lc_status_name;
	public void setLc_approval_id(String lc_approval_id){
		this.lc_approval_id=lc_approval_id;
	}
	public String getLc_approval_id(){
		return lc_approval_id;
	}
	public void setLc_status_id(String lc_status_id){
		this.lc_status_id=lc_status_id;
	}
	public String getLc_status_id(){
		return lc_status_id;
	}
	public void setLc_apply_id(String lc_apply_id){
		this.lc_apply_id=lc_apply_id;
	}
	public String getLc_apply_id(){
		return lc_apply_id;
	}
	public void setLc_approval_remark(String lc_approval_remark){
		this.lc_approval_remark=lc_approval_remark;
	}
	public String getLc_approval_remark(){
		return lc_approval_remark;
	}
	public void setTaskId(String taskId){
		this.taskId=taskId;
	}
	public String getTaskId(){
		return taskId;
	}
	public void setLc_approval_time(String lc_approval_time){
		this.lc_approval_time=lc_approval_time;
	}
	public String getLc_approval_time(){
		return lc_approval_time;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public String getLc_status_name() {
		return lc_status_name;
	}
	public void setLc_status_name(String lc_status_name) {
		this.lc_status_name = lc_status_name;
	}
	
}
