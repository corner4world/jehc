package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_member_account_proprietary_his 基础专有账户充值记录 
* 2016-03-24 20:48:25  邓纯杰
*/
public class BMemberAccountProprietaryHis extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_member_account_proprietary_his_id;/**专有账户充值记录**/
	private String b_member_account_proprietary_id;/**专有账户编号**/
	private double b_member_account_proprietary_his_money;/**充值金额**/
	private String b_member_account_proprietary_his_ctime;/**充值时间**/
	private String b_member_account_proprietary_his_type;/**充值方式0在线支付1线下支付2其他方式**/
	private String b_member_account_proprietary_his_log;/**备注**/
	private String b_member_id;/**创建日期**/
	private String xt_userinfo_id;/**平台操作人**/
	public void setB_member_account_proprietary_his_id(String b_member_account_proprietary_his_id){
		this.b_member_account_proprietary_his_id=b_member_account_proprietary_his_id;
	}
	public String getB_member_account_proprietary_his_id(){
		return b_member_account_proprietary_his_id;
	}
	public void setB_member_account_proprietary_id(String b_member_account_proprietary_id){
		this.b_member_account_proprietary_id=b_member_account_proprietary_id;
	}
	public String getB_member_account_proprietary_id(){
		return b_member_account_proprietary_id;
	}
	public void setB_member_account_proprietary_his_money(double b_member_account_proprietary_his_money){
		this.b_member_account_proprietary_his_money=b_member_account_proprietary_his_money;
	}
	public double getB_member_account_proprietary_his_money(){
		return b_member_account_proprietary_his_money;
	}
	public void setB_member_account_proprietary_his_ctime(String b_member_account_proprietary_his_ctime){
		this.b_member_account_proprietary_his_ctime=b_member_account_proprietary_his_ctime;
	}
	public String getB_member_account_proprietary_his_ctime(){
		return b_member_account_proprietary_his_ctime;
	}
	public void setB_member_account_proprietary_his_type(String b_member_account_proprietary_his_type){
		this.b_member_account_proprietary_his_type=b_member_account_proprietary_his_type;
	}
	public String getB_member_account_proprietary_his_type(){
		return b_member_account_proprietary_his_type;
	}
	public void setB_member_account_proprietary_his_log(String b_member_account_proprietary_his_log){
		this.b_member_account_proprietary_his_log=b_member_account_proprietary_his_log;
	}
	public String getB_member_account_proprietary_his_log(){
		return b_member_account_proprietary_his_log;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
