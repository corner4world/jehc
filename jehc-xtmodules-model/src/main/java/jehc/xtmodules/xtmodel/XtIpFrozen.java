package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_ip_frozen 平台IP冻结账户 
* 2016-02-29 10:41:23  邓纯杰
*/
public class XtIpFrozen extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_ip_frozen_id;/**IP冻结编号**/
	private String xt_ip_frozen_address;/**IP地址**/
	private String xt_ip_frozen_status;/**状态0正常1冻结2黑名单**/
	private String xt_ip_frozen_content;/**内容**/
	private String xt_ip_frozen_ctime;/**创建时间**/
	private String xt_ip_frozen_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	public void setXt_ip_frozen_id(String xt_ip_frozen_id){
		this.xt_ip_frozen_id=xt_ip_frozen_id;
	}
	public String getXt_ip_frozen_id(){
		return xt_ip_frozen_id;
	}
	public void setXt_ip_frozen_address(String xt_ip_frozen_address){
		this.xt_ip_frozen_address=xt_ip_frozen_address;
	}
	public String getXt_ip_frozen_address(){
		return xt_ip_frozen_address;
	}
	public void setXt_ip_frozen_status(String xt_ip_frozen_status){
		this.xt_ip_frozen_status=xt_ip_frozen_status;
	}
	public String getXt_ip_frozen_status(){
		return xt_ip_frozen_status;
	}
	public void setXt_ip_frozen_content(String xt_ip_frozen_content){
		this.xt_ip_frozen_content=xt_ip_frozen_content;
	}
	public String getXt_ip_frozen_content(){
		return xt_ip_frozen_content;
	}
	public void setXt_ip_frozen_ctime(String xt_ip_frozen_ctime){
		this.xt_ip_frozen_ctime=xt_ip_frozen_ctime;
	}
	public String getXt_ip_frozen_ctime(){
		return xt_ip_frozen_ctime;
	}
	public void setXt_ip_frozen_mtime(String xt_ip_frozen_mtime){
		this.xt_ip_frozen_mtime=xt_ip_frozen_mtime;
	}
	public String getXt_ip_frozen_mtime(){
		return xt_ip_frozen_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
