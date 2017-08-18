package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_member_account_proprietary 基础专有账户 
* 2016-03-24 20:33:37  邓纯杰
*/
public class BMemberAccountProprietary extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_member_account_proprietary_id;/**专有账户编号**/
	private String b_member_id;/**会员编号**/
	private String b_member_account_id;/**会员账号编号**/
	private String b_member_account_proprietary_status;/**状态0正常1锁定2禁用**/
	private String b_member_account_proprietary_ctime;/**创建时间**/
	private double b_member_account_proprietary_money;/**金额**/
	private String xt_userinfo_id;/**操作人编号**/
	public void setB_member_account_proprietary_id(String b_member_account_proprietary_id){
		this.b_member_account_proprietary_id=b_member_account_proprietary_id;
	}
	public String getB_member_account_proprietary_id(){
		return b_member_account_proprietary_id;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setB_member_account_id(String b_member_account_id){
		this.b_member_account_id=b_member_account_id;
	}
	public String getB_member_account_id(){
		return b_member_account_id;
	}
	public void setB_member_account_proprietary_status(String b_member_account_proprietary_status){
		this.b_member_account_proprietary_status=b_member_account_proprietary_status;
	}
	public String getB_member_account_proprietary_status(){
		return b_member_account_proprietary_status;
	}
	public void setB_member_account_proprietary_ctime(String b_member_account_proprietary_ctime){
		this.b_member_account_proprietary_ctime=b_member_account_proprietary_ctime;
	}
	public String getB_member_account_proprietary_ctime(){
		return b_member_account_proprietary_ctime;
	}
	public void setB_member_account_proprietary_money(double b_member_account_proprietary_money){
		this.b_member_account_proprietary_money=b_member_account_proprietary_money;
	}
	public double getB_member_account_proprietary_money(){
		return b_member_account_proprietary_money;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
