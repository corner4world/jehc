package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_order_detail 基础订单详细 
* 2016-01-27 13:59:04  邓纯杰
*/
public class BOrderDetail extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_order_detail_id;/**订单详细编号**/
	private String b_product_id;/**商品编号**/
	private String b_seller_id;/**卖家编号**/
	private int b_order_detail_number;/**购物数量**/
	private double b_order_detail_price;/**购买单价**/
	private double b_order_detail_discount;/**折扣**/
	private String b_order_detail_ctime;/**创建时间**/
	private String b_order_detail_mtime;/**修改时间**/
	private String b_order_id;/**订单编号**/
	
	private String b_product_name;/**商品名称**/
	private String b_seller_name;/**商品卖家**/
	private String b_category_name;/**分类名称**/
	private String b_brand_name;/**品牌名称**/
	private double b_order_detail_total_price/**总价格**/;
	public void setB_order_detail_id(String b_order_detail_id){
		this.b_order_detail_id=b_order_detail_id;
	}
	public String getB_order_detail_id(){
		return b_order_detail_id;
	}
	public void setB_product_id(String b_product_id){
		this.b_product_id=b_product_id;
	}
	public String getB_product_id(){
		return b_product_id;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
	public void setB_order_detail_number(int b_order_detail_number){
		this.b_order_detail_number=b_order_detail_number;
	}
	public int getB_order_detail_number(){
		return b_order_detail_number;
	}
	public void setB_order_detail_price(double b_order_detail_price){
		this.b_order_detail_price=b_order_detail_price;
	}
	public double getB_order_detail_price(){
		return b_order_detail_price;
	}
	public void setB_order_detail_discount(double b_order_detail_discount){
		this.b_order_detail_discount=b_order_detail_discount;
	}
	public double getB_order_detail_discount(){
		return b_order_detail_discount;
	}
	public void setB_order_detail_ctime(String b_order_detail_ctime){
		this.b_order_detail_ctime=b_order_detail_ctime;
	}
	public String getB_order_detail_ctime(){
		return b_order_detail_ctime;
	}
	public void setB_order_detail_mtime(String b_order_detail_mtime){
		this.b_order_detail_mtime=b_order_detail_mtime;
	}
	public String getB_order_detail_mtime(){
		return b_order_detail_mtime;
	}
	public String getB_order_id() {
		return b_order_id;
	}
	public void setB_order_id(String b_order_id) {
		this.b_order_id = b_order_id;
	}
	public String getB_product_name() {
		return b_product_name;
	}
	public void setB_product_name(String b_product_name) {
		this.b_product_name = b_product_name;
	}
	public String getB_seller_name() {
		return b_seller_name;
	}
	public void setB_seller_name(String b_seller_name) {
		this.b_seller_name = b_seller_name;
	}
	public String getB_category_name() {
		return b_category_name;
	}
	public void setB_category_name(String b_category_name) {
		this.b_category_name = b_category_name;
	}
	public String getB_brand_name() {
		return b_brand_name;
	}
	public void setB_brand_name(String b_brand_name) {
		this.b_brand_name = b_brand_name;
	}
	public double getB_order_detail_total_price() {
		return b_order_detail_total_price;
	}
	public void setB_order_detail_total_price(double b_order_detail_total_price) {
		this.b_order_detail_total_price = b_order_detail_total_price;
	}
}
