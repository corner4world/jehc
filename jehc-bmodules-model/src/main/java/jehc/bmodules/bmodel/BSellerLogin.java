package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_seller_login 基础卖家登陆账号 
* 2016-02-18 17:17:12  邓纯杰
*/
public class BSellerLogin extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_seller_login_id;/**卖家账号编号**/
	private String b_seller_login_name;/**卖家登陆账号**/
	private String b_seller_login_pwd;/**卖家登陆密码**/
	private String b_seller_login_status;/**卖家状态0正常1禁用**/
	private String b_seller_id;/**卖家编号**/
	public void setB_seller_login_id(String b_seller_login_id){
		this.b_seller_login_id=b_seller_login_id;
	}
	public String getB_seller_login_id(){
		return b_seller_login_id;
	}
	public void setB_seller_login_name(String b_seller_login_name){
		this.b_seller_login_name=b_seller_login_name;
	}
	public String getB_seller_login_name(){
		return b_seller_login_name;
	}
	public void setB_seller_login_pwd(String b_seller_login_pwd){
		this.b_seller_login_pwd=b_seller_login_pwd;
	}
	public String getB_seller_login_pwd(){
		return b_seller_login_pwd;
	}
	public void setB_seller_login_status(String b_seller_login_status){
		this.b_seller_login_status=b_seller_login_status;
	}
	public String getB_seller_login_status(){
		return b_seller_login_status;
	}
	public String getB_seller_id() {
		return b_seller_id;
	}
	public void setB_seller_id(String b_seller_id) {
		this.b_seller_id = b_seller_id;
	}
}
