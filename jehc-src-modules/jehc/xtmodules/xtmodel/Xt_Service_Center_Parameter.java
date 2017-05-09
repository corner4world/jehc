package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_service_center_parameter 服务中心参数 
* 2017-03-27 12:32:04  邓纯杰
*/
public class Xt_Service_Center_Parameter extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_service_center_parameter_id;/**服务参数编号**/
	private String xt_service_center_id;/**服务中心编号**/
	private String xt_service_center_parameter_name;/**参数名称**/
	private String xt_service_center_parameter_type;/**参数类型**/
	private String xt_service_center_parameter_remark;/**参数描述**/
	private String xt_userinfo_id;/**创&nbsp;&nbsp;建&nbsp;人**/
	private String xt_service_center_parameter_status;/**状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态**/
	private String ctime;/**创建时间**/
	private String mtime;/**修改时间**/
	private String attach_filetype;/**类型附件**/
	private String attach_fileconfig;/**配置附件**/
	public void setXt_service_center_parameter_id(String xt_service_center_parameter_id){
		this.xt_service_center_parameter_id=xt_service_center_parameter_id;
	}
	public String getXt_service_center_parameter_id(){
		return xt_service_center_parameter_id;
	}
	public void setXt_service_center_id(String xt_service_center_id){
		this.xt_service_center_id=xt_service_center_id;
	}
	public String getXt_service_center_id(){
		return xt_service_center_id;
	}
	public void setXt_service_center_parameter_name(String xt_service_center_parameter_name){
		this.xt_service_center_parameter_name=xt_service_center_parameter_name;
	}
	public String getXt_service_center_parameter_name(){
		return xt_service_center_parameter_name;
	}
	public void setXt_service_center_parameter_type(String xt_service_center_parameter_type){
		this.xt_service_center_parameter_type=xt_service_center_parameter_type;
	}
	public String getXt_service_center_parameter_type(){
		return xt_service_center_parameter_type;
	}
	public void setXt_service_center_parameter_remark(String xt_service_center_parameter_remark){
		this.xt_service_center_parameter_remark=xt_service_center_parameter_remark;
	}
	public String getXt_service_center_parameter_remark(){
		return xt_service_center_parameter_remark;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_service_center_parameter_status(String xt_service_center_parameter_status){
		this.xt_service_center_parameter_status=xt_service_center_parameter_status;
	}
	public String getXt_service_center_parameter_status(){
		return xt_service_center_parameter_status;
	}
	public void setCtime(String ctime){
		this.ctime=ctime;
	}
	public String getCtime(){
		return ctime;
	}
	public void setMtime(String mtime){
		this.mtime=mtime;
	}
	public String getMtime(){
		return mtime;
	}
	public void setAttach_filetype(String attach_filetype){
		this.attach_filetype=attach_filetype;
	}
	public String getAttach_filetype(){
		return attach_filetype;
	}
	public void setAttach_fileconfig(String attach_fileconfig){
		this.attach_fileconfig=attach_fileconfig;
	}
	public String getAttach_fileconfig(){
		return attach_fileconfig;
	}
}
