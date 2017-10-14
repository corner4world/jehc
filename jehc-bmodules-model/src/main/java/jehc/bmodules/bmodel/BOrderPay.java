package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_order_pay 基础订单支付 
* 2016-03-22 16:47:52  邓纯杰
*/
public class BOrderPay extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_order_pay_id;/**订单支付编号**/
	private String b_order_id;/**订单编号**/
	private double b_order_pay_money = 0.00;/**支付金额**/
	private String b_member_id;/**支付人**/
	private String b_order_pay_ctime;/**创建时间**/
	private String b_order_pay_isall;/**是否全额支付0是1否**/
	private String xt_userinfo_id;/**平台创建人**/
	private String b_seller_id;/**卖家或商家操作者**/
	public void setB_order_pay_id(String b_order_pay_id){
		this.b_order_pay_id=b_order_pay_id;
	}
	public String getB_order_pay_id(){
		return b_order_pay_id;
	}
	public void setB_order_id(String b_order_id){
		this.b_order_id=b_order_id;
	}
	public String getB_order_id(){
		return b_order_id;
	}
	public void setB_order_pay_money(double b_order_pay_money){
		this.b_order_pay_money=b_order_pay_money;
	}
	public double getB_order_pay_money(){
		return b_order_pay_money;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setB_order_pay_ctime(String b_order_pay_ctime){
		this.b_order_pay_ctime=b_order_pay_ctime;
	}
	public String getB_order_pay_ctime(){
		return b_order_pay_ctime;
	}
	public void setB_order_pay_isall(String b_order_pay_isall){
		this.b_order_pay_isall=b_order_pay_isall;
	}
	public String getB_order_pay_isall(){
		return b_order_pay_isall;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
}
