package jehc.bmodules.bmodel;

import java.io.Serializable;

/**
* b_stock 基础库存 
* 2016-01-27 14:28:46  邓纯杰
*/
public class BStock extends BProductPrice implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_stock_id;/**库存编号**/
	private String b_seller_product_id;/**卖家产品编号**/
	private int b_stock_countable_sell;/**可卖数即商家所设置的数量**/
	private int b_stock_locks_number;/**锁定数即已卖数**/
	private String b_product_name;/**商品名称**/
	private String b_seller_name;/**商品卖家**/
	
	private String b_category_name;/**分类名称**/
	private String b_brand_name;/**品牌名称**/
	private String b_product_id;/**商品编号**/
	private String b_seller_id;/**卖家编号**/
	public void setB_stock_id(String b_stock_id){
		this.b_stock_id=b_stock_id;
	}
	public String getB_stock_id(){
		return b_stock_id;
	}
	public void setB_seller_product_id(String b_seller_product_id){
		this.b_seller_product_id=b_seller_product_id;
	}
	public String getB_seller_product_id(){
		return b_seller_product_id;
	}
	public void setB_stock_countable_sell(int b_stock_countable_sell){
		this.b_stock_countable_sell=b_stock_countable_sell;
	}
	public int getB_stock_countable_sell(){
		return b_stock_countable_sell;
	}
	public void setB_stock_locks_number(int b_stock_locks_number){
		this.b_stock_locks_number=b_stock_locks_number;
	}
	public int getB_stock_locks_number(){
		return b_stock_locks_number;
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
	public String getB_product_id() {
		return b_product_id;
	}
	public void setB_product_id(String b_product_id) {
		this.b_product_id = b_product_id;
	}
	public String getB_seller_id() {
		return b_seller_id;
	}
	public void setB_seller_id(String b_seller_id) {
		this.b_seller_id = b_seller_id;
	}
}
