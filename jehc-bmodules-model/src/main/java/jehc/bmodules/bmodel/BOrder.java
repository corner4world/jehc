package jehc.bmodules.bmodel;

import java.io.Serializable;

/**
* b_order 基础订单 
* 2016-01-27 13:55:11  邓纯杰
*/
public class BOrder extends BInvoice implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_order_id;/**订单编号**/
	private String b_order_name;/**订单号名称**/
	private String b_order_key;/**订单号**/
	private String b_order_ctime;/**创建时间**/
	private String b_order_mtime;/**修改时间**/
	private double b_order_total_price;/**订单总金额**/
	private String b_order_from;/**来源0普通订单1团购订单2**/
	private String b_order_sessionid;/**SESSIONID号**/
	private String b_order_remark;/**订单备注**/
	private int b_order_total_number;/**购买总数**/
	private String b_invoice_id;/**发票编号**/
	private String b_cart_order_address_id;/**配送地址编号**/
	private String b_order_type;/**状态:0类型待支付订单1已支付订单2支付部分余额**/
	private String b_order_status;/**状态0正常订单1已作废订单**/
	private String b_member_id;/**会员编号**/
	private String b_member_name;/**会员名称**/
	public void setB_order_id(String b_order_id){
		this.b_order_id=b_order_id;
	}
	public String getB_order_id(){
		return b_order_id;
	}
	public void setB_order_name(String b_order_name){
		this.b_order_name=b_order_name;
	}
	public String getB_order_name(){
		return b_order_name;
	}
	public void setB_order_key(String b_order_key){
		this.b_order_key=b_order_key;
	}
	public String getB_order_key(){
		return b_order_key;
	}
	public void setB_order_ctime(String b_order_ctime){
		this.b_order_ctime=b_order_ctime;
	}
	public String getB_order_ctime(){
		return b_order_ctime;
	}
	public void setB_order_mtime(String b_order_mtime){
		this.b_order_mtime=b_order_mtime;
	}
	public String getB_order_mtime(){
		return b_order_mtime;
	}
	public void setB_order_total_price(double b_order_total_price){
		this.b_order_total_price=b_order_total_price;
	}
	public double getB_order_total_price(){
		return b_order_total_price;
	}
	public void setB_order_from(String b_order_from){
		this.b_order_from=b_order_from;
	}
	public String getB_order_from(){
		return b_order_from;
	}
	public void setB_order_sessionid(String b_order_sessionid){
		this.b_order_sessionid=b_order_sessionid;
	}
	public String getB_order_sessionid(){
		return b_order_sessionid;
	}
	public void setB_order_remark(String b_order_remark){
		this.b_order_remark=b_order_remark;
	}
	public String getB_order_remark(){
		return b_order_remark;
	}
	public void setB_order_total_number(int b_order_total_number){
		this.b_order_total_number=b_order_total_number;
	}
	public int getB_order_total_number(){
		return b_order_total_number;
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
	public void setB_order_type(String b_order_type){
		this.b_order_type=b_order_type;
	}
	public String getB_order_type(){
		return b_order_type;
	}
	public void setB_order_status(String b_order_status){
		this.b_order_status=b_order_status;
	}
	public String getB_order_status(){
		return b_order_status;
	}
	public String getB_member_id() {
		return b_member_id;
	}
	public void setB_member_id(String b_member_id) {
		this.b_member_id = b_member_id;
	}
	public String getB_member_name() {
		return b_member_name;
	}
	public void setB_member_name(String b_member_name) {
		this.b_member_name = b_member_name;
	}
}
