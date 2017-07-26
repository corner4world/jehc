package jehc.lcmodules.lcmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* lc_apply 流程申请 
* 2017-01-08 14:55:10  邓纯杰
*/
public class LcApply extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lc_apply_id;/**流程实例发起编号（即申请编号）**/
	private String lc_apply_title;/**标题**/
	private String lc_apply_model_id;/**模块**/
	private String lc_apply_delete;/**是否删除0正常1删除**/
	private String processInstance_id;/**流程实例id（一个任务没有完成其编号是不变也会存在）**/
	private String processdefinitions_id;/**流程定义id编号**/
	private String lc_url;/**URL可缺省(可作为表单查看页面)**/
	private String xt_userinfo_id;/**提交人编号**/
	private String lc_apply_time;/**提交时间**/
	private String lc_apply_model_biz_id;/**业务编号**/
	private String lc_apply_isonlysaverecord;/**lc_apply_isonlysaverecord**/
	private String xt_constantRemark;/**常量备注**/
	private String xt_constantURL;/**常量URL地址**/
	public void setLc_apply_id(String lc_apply_id){
		this.lc_apply_id=lc_apply_id;
	}
	public String getLc_apply_id(){
		return lc_apply_id;
	}
	public void setLc_apply_title(String lc_apply_title){
		this.lc_apply_title=lc_apply_title;
	}
	public String getLc_apply_title(){
		return lc_apply_title;
	}
	public void setLc_apply_model_id(String lc_apply_model_id){
		this.lc_apply_model_id=lc_apply_model_id;
	}
	public String getLc_apply_model_id(){
		return lc_apply_model_id;
	}
	public void setLc_apply_delete(String lc_apply_delete){
		this.lc_apply_delete=lc_apply_delete;
	}
	public String getLc_apply_delete(){
		return lc_apply_delete;
	}
	public void setProcessInstance_id(String processInstance_id){
		this.processInstance_id=processInstance_id;
	}
	public String getProcessInstance_id(){
		return processInstance_id;
	}
	public void setProcessdefinitions_id(String processdefinitions_id){
		this.processdefinitions_id=processdefinitions_id;
	}
	public String getProcessdefinitions_id(){
		return processdefinitions_id;
	}
	public void setLc_url(String lc_url){
		this.lc_url=lc_url;
	}
	public String getLc_url(){
		return lc_url;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setLc_apply_time(String lc_apply_time){
		this.lc_apply_time=lc_apply_time;
	}
	public String getLc_apply_time(){
		return lc_apply_time;
	}
	public String getLc_apply_model_biz_id() {
		return lc_apply_model_biz_id;
	}
	public void setLc_apply_model_biz_id(String lc_apply_model_biz_id) {
		this.lc_apply_model_biz_id = lc_apply_model_biz_id;
	}
	public String getLc_apply_isonlysaverecord() {
		return lc_apply_isonlysaverecord;
	}
	public void setLc_apply_isonlysaverecord(String lc_apply_isonlysaverecord) {
		this.lc_apply_isonlysaverecord = lc_apply_isonlysaverecord;
	}
	public String getXt_constantRemark() {
		return xt_constantRemark;
	}
	public void setXt_constantRemark(String xt_constantRemark) {
		this.xt_constantRemark = xt_constantRemark;
	}
	public String getXt_constantURL() {
		return xt_constantURL;
	}
	public void setXt_constantURL(String xt_constantURL) {
		this.xt_constantURL = xt_constantURL;
	}
	
}
