package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_change_pwd 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
public class Xt_Change_Pwd extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_change_pwd_id;/**主键**/
	private String user_name;/**用户名称**/
	private String login_id;/**登录账号**/
	private String sex;/**性别**/
	private String phone;/**手机号码**/
	private String mail;/**邮箱地址**/
	private String address;/**居住地址**/
	private String xt_attachment_id;/**证件照片**/
	private String ctime;/**创建时间**/
	private int status;/**状态：0待审核2审核通过1审核未通过**/
	public void setXt_change_pwd_id(String xt_change_pwd_id){
		this.xt_change_pwd_id=xt_change_pwd_id;
	}
	public String getXt_change_pwd_id(){
		return xt_change_pwd_id;
	}
	public void setUser_name(String user_name){
		this.user_name=user_name;
	}
	public String getUser_name(){
		return user_name;
	}
	public void setLogin_id(String login_id){
		this.login_id=login_id;
	}
	public String getLogin_id(){
		return login_id;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public String getSex(){
		return sex;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setMail(String mail){
		this.mail=mail;
	}
	public String getMail(){
		return mail;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
	public void setCtime(String ctime){
		this.ctime=ctime;
	}
	public String getCtime(){
		return ctime;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public int getStatus(){
		return status;
	}
}
