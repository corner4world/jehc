package jehc.lcmodules.lcmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* lc_deployment_his 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
public class LcDeploymentHis extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;/**主键**/
	private String lc_deployment_his_id;/**流程部署Id**/
	private String lc_deployment_his_name;/**名称**/
	private String lc_deployment_his_time;/**部署时间**/
	private String lc_deployment_his_tenantId;/**租户编号**/
	private String lc_process_id;/**流程编号**/
	private String xt_constant_id;
	private String lc_deployment_his_status;
	public String getLc_deployment_his_status() {
		return lc_deployment_his_status;
	}
	public void setLc_deployment_his_status(String lc_deployment_his_status) {
		this.lc_deployment_his_status = lc_deployment_his_status;
	}
	public String getXt_constant_id() {
		return xt_constant_id;
	}
	public void setXt_constant_id(String xt_constant_id) {
		this.xt_constant_id = xt_constant_id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setLc_deployment_his_id(String lc_deployment_his_id){
		this.lc_deployment_his_id=lc_deployment_his_id;
	}
	public String getLc_deployment_his_id(){
		return lc_deployment_his_id;
	}
	public void setLc_deployment_his_name(String lc_deployment_his_name){
		this.lc_deployment_his_name=lc_deployment_his_name;
	}
	public String getLc_deployment_his_name(){
		return lc_deployment_his_name;
	}
	public void setLc_deployment_his_time(String lc_deployment_his_time){
		this.lc_deployment_his_time=lc_deployment_his_time;
	}
	public String getLc_deployment_his_time(){
		return lc_deployment_his_time;
	}
	public void setLc_deployment_his_tenantId(String lc_deployment_his_tenantId){
		this.lc_deployment_his_tenantId=lc_deployment_his_tenantId;
	}
	public String getLc_deployment_his_tenantId(){
		return lc_deployment_his_tenantId;
	}
	public String getLc_process_id() {
		return lc_process_id;
	}
	public void setLc_process_id(String lc_process_id) {
		this.lc_process_id = lc_process_id;
	}
}
