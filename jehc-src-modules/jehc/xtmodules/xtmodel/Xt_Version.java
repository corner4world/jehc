package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_version 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
public class Xt_Version extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_version_id;/**平台版本编号**/
	private String xt_version_name;/**版本名称**/
	private String xt_version_ctime;/**上传时间**/
	private String xt_version_mtime;/**修改时间**/
	private int xt_version_number;/**下载次数**/
	private String xt_userinfo_id;/**上传人**/
	private String xt_version_remark;/**版本描述**/
	private String xt_attachment_id;/**附件编号**/
	public void setXt_version_id(String xt_version_id){
		this.xt_version_id=xt_version_id;
	}
	public String getXt_version_id(){
		return xt_version_id;
	}
	public void setXt_version_name(String xt_version_name){
		this.xt_version_name=xt_version_name;
	}
	public String getXt_version_name(){
		return xt_version_name;
	}
	public void setXt_version_ctime(String xt_version_ctime){
		this.xt_version_ctime=xt_version_ctime;
	}
	public String getXt_version_ctime(){
		return xt_version_ctime;
	}
	public void setXt_version_mtime(String xt_version_mtime){
		this.xt_version_mtime=xt_version_mtime;
	}
	public String getXt_version_mtime(){
		return xt_version_mtime;
	}
	public void setXt_version_number(int xt_version_number){
		this.xt_version_number=xt_version_number;
	}
	public int getXt_version_number(){
		return xt_version_number;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_version_remark(String xt_version_remark){
		this.xt_version_remark=xt_version_remark;
	}
	public String getXt_version_remark(){
		return xt_version_remark;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
}
