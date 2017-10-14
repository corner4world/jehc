package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_member_account 基础会员余额账户 
* 2016-03-24 20:30:14  邓纯杰
*/
public class BMemberAccount extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_member_account_id;/**会员余额账户编号**/
	private String b_member_account_status;/**状态0可用1禁用**/
	private String b_member_account_ctime;/**创建时间**/
	private String b_member_account_code;/**编码**/
	private String b_member_id;/**会员编号**/
	public void setB_member_account_id(String b_member_account_id){
		this.b_member_account_id=b_member_account_id;
	}
	public String getB_member_account_id(){
		return b_member_account_id;
	}
	public void setB_member_account_status(String b_member_account_status){
		this.b_member_account_status=b_member_account_status;
	}
	public String getB_member_account_status(){
		return b_member_account_status;
	}
	public void setB_member_account_ctime(String b_member_account_ctime){
		this.b_member_account_ctime=b_member_account_ctime;
	}
	public String getB_member_account_ctime(){
		return b_member_account_ctime;
	}
	public void setB_member_account_code(String b_member_account_code){
		this.b_member_account_code=b_member_account_code;
	}
	public String getB_member_account_code(){
		return b_member_account_code;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
}
