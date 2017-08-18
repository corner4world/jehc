package jehc.bmodules.bmodel;

import java.io.Serializable;

/**
* b_cart 基础购物车 
* 2016-01-27 13:36:04  邓纯杰
*/
public class BCart extends BInvoice implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_cart_id;/**购物车编号**/
	private String b_member_id;/**会员编号**/
	private String b_cart_ctime;/**创建时间**/
	private String b_cart_mtime;/**修改时间**/
	private double b_cart_total_price;/**购买总价**/
	private String b_cart_orderkey;/**购物车订单号**/
	private String b_cart_from;/**来源0普通订单1团购订单2**/
	private String b_cart_sessionid;/**SESSIONID号**/
	private String b_cart_remark;/**备注**/
	private int b_cart_total_number;/**购买总数**/
	private String b_invoice_id;/**发票编号**/
	private String b_cart_order_address_id;/**配送地址编号**/
	public void setB_cart_id(String b_cart_id){
		this.b_cart_id=b_cart_id;
	}
	public String getB_cart_id(){
		return b_cart_id;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setB_cart_ctime(String b_cart_ctime){
		this.b_cart_ctime=b_cart_ctime;
	}
	public String getB_cart_ctime(){
		return b_cart_ctime;
	}
	public void setB_cart_mtime(String b_cart_mtime){
		this.b_cart_mtime=b_cart_mtime;
	}
	public String getB_cart_mtime(){
		return b_cart_mtime;
	}
	public void setB_cart_total_price(double b_cart_total_price){
		this.b_cart_total_price=b_cart_total_price;
	}
	public double getB_cart_total_price(){
		return b_cart_total_price;
	}
	public void setB_cart_orderkey(String b_cart_orderkey){
		this.b_cart_orderkey=b_cart_orderkey;
	}
	public String getB_cart_orderkey(){
		return b_cart_orderkey;
	}
	public void setB_cart_from(String b_cart_from){
		this.b_cart_from=b_cart_from;
	}
	public String getB_cart_from(){
		return b_cart_from;
	}
	public void setB_cart_sessionid(String b_cart_sessionid){
		this.b_cart_sessionid=b_cart_sessionid;
	}
	public String getB_cart_sessionid(){
		return b_cart_sessionid;
	}
	public void setB_cart_remark(String b_cart_remark){
		this.b_cart_remark=b_cart_remark;
	}
	public String getB_cart_remark(){
		return b_cart_remark;
	}
	public void setB_cart_total_number(int b_cart_total_number){
		this.b_cart_total_number=b_cart_total_number;
	}
	public int getB_cart_total_number(){
		return b_cart_total_number;
	}
	public void setB_invoice_id(String b_invoice_id){
		this.b_invoice_id=b_invoice_id;
	}
	public String getB_invoice_id(){
		return b_invoice_id;
	}
	public void setB_cart_order_address_id(String b_cart_order_address_id){
		this.b_cart_order_address_id=b_cart_order_address_id;
	}
	public String getB_cart_order_address_id(){
		return b_cart_order_address_id;
	}
}
