package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_appkey_appsecret xt_appkey_appsecret 
* 2016-08-28 14:48:17  邓纯杰
*/
public class Xt_Appkey_Appsecret extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_appkey_appsecret_id;/**主键**/
	private String xt_appkey;/**appkey**/
	private String xt_appsecret;/**appsecret**/
	private String xt_ctime;/**建创时间**/
	private String xt_mtime;/**改修时间**/
	private String xt_userinfo_id;/**创建人**/
	private String xt_appkey_appsecret_status;/**态状0正常1禁用**/
	private String xt_appkey_appsecret_name;/**名称**/
	private String xt_appkey_appsecret_remark;/**注备**/
	public void setXt_appkey_appsecret_id(String xt_appkey_appsecret_id){
		this.xt_appkey_appsecret_id=xt_appkey_appsecret_id;
	}
	public String getXt_appkey_appsecret_id(){
		return xt_appkey_appsecret_id;
	}
	public void setXt_appkey(String xt_appkey){
		this.xt_appkey=xt_appkey;
	}
	public String getXt_appkey(){
		return xt_appkey;
	}
	public void setXt_appsecret(String xt_appsecret){
		this.xt_appsecret=xt_appsecret;
	}
	public String getXt_appsecret(){
		return xt_appsecret;
	}
	public void setXt_ctime(String xt_ctime){
		this.xt_ctime=xt_ctime;
	}
	public String getXt_ctime(){
		return xt_ctime;
	}
	public void setXt_mtime(String xt_mtime){
		this.xt_mtime=xt_mtime;
	}
	public String getXt_mtime(){
		return xt_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_appkey_appsecret_status(String xt_appkey_appsecret_status){
		this.xt_appkey_appsecret_status=xt_appkey_appsecret_status;
	}
	public String getXt_appkey_appsecret_status(){
		return xt_appkey_appsecret_status;
	}
	public void setXt_appkey_appsecret_name(String xt_appkey_appsecret_name){
		this.xt_appkey_appsecret_name=xt_appkey_appsecret_name;
	}
	public String getXt_appkey_appsecret_name(){
		return xt_appkey_appsecret_name;
	}
	public void setXt_appkey_appsecret_remark(String xt_appkey_appsecret_remark){
		this.xt_appkey_appsecret_remark=xt_appkey_appsecret_remark;
	}
	public String getXt_appkey_appsecret_remark(){
		return xt_appkey_appsecret_remark;
	}
}
