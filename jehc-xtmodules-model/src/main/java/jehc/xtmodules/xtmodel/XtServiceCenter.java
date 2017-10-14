package jehc.xtmodules.xtmodel;
import java.io.Serializable;
import java.util.List;

import jehc.xtmodules.xtcore.base.BaseEntity;
import jehc.xtmodules.xtmodel.XtServiceCenterParameter;

/**
* xt_service_center 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
public class XtServiceCenter extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_service_center_id;/**服务中心编号**/
	private String xt_service_center_location;/**服务地址**/
	private String xt_service_center_name;/**服务名称**/
	private String xt_service_center_remark;/**服务描述**/
	private String ctime;/**创建时间**/
	private String mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人编号**/
	private String xt_service_center_type;/**服务类型**/
	private String xt_service_center_status;/**状态0正常1禁用**/
	private List<XtServiceCenterParameter> xt_Service_Center_Parameter;/**服务中心参数**/
	private String xt_Service_Center_Parameter_removed_flag;/**服务中心参数移除标识**/
	public void setXt_service_center_id(String xt_service_center_id){
		this.xt_service_center_id=xt_service_center_id;
	}
	public String getXt_service_center_id(){
		return xt_service_center_id;
	}
	public void setXt_service_center_location(String xt_service_center_location){
		this.xt_service_center_location=xt_service_center_location;
	}
	public String getXt_service_center_location(){
		return xt_service_center_location;
	}
	public void setXt_service_center_name(String xt_service_center_name){
		this.xt_service_center_name=xt_service_center_name;
	}
	public String getXt_service_center_name(){
		return xt_service_center_name;
	}
	public void setXt_service_center_remark(String xt_service_center_remark){
		this.xt_service_center_remark=xt_service_center_remark;
	}
	public String getXt_service_center_remark(){
		return xt_service_center_remark;
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
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_service_center_type(String xt_service_center_type){
		this.xt_service_center_type=xt_service_center_type;
	}
	public String getXt_service_center_type(){
		return xt_service_center_type;
	}
	public void setXt_service_center_status(String xt_service_center_status){
		this.xt_service_center_status=xt_service_center_status;
	}
	public String getXt_service_center_status(){
		return xt_service_center_status;
	}
	public void setXt_Service_Center_Parameter(List<XtServiceCenterParameter> xt_Service_Center_Parameter){
		this.xt_Service_Center_Parameter=xt_Service_Center_Parameter;
	}
	public List<XtServiceCenterParameter> getXt_Service_Center_Parameter(){
		return xt_Service_Center_Parameter;
	}
	public void setXt_Service_Center_Parameter_removed_flag(String xt_Service_Center_Parameter_removed_flag){
		this.xt_Service_Center_Parameter_removed_flag=xt_Service_Center_Parameter_removed_flag;
	}
	public String getXt_Service_Center_Parameter_removed_flag(){
		return xt_Service_Center_Parameter_removed_flag;
	}
}
