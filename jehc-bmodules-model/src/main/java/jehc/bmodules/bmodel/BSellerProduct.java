package jehc.bmodules.bmodel;

import java.io.Serializable;

/**
* b_seller_product 卖家商品 
* 2016-02-18 17:20:35  邓纯杰
*/
public class BSellerProduct extends BProductPrice implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_seller_product_id;/**卖家商品编号**/
	private String b_seller_id;/**卖家编号**/
	private String b_product_id;/**商品编号**/
	private String b_seller_product_status;/**1已关联1已取消**/
	
	private String b_product_name;/**产品名称**/
	private String b_category_name;/**分类名称**/
	private String b_brand_name;/**品牌名称**/
	private String b_seller_name;/**卖家、商户**/
	
	public void setB_seller_product_id(String b_seller_product_id){
		this.b_seller_product_id=b_seller_product_id;
	}
	public String getB_seller_product_id(){
		return b_seller_product_id;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
	public void setB_product_id(String b_product_id){
		this.b_product_id=b_product_id;
	}
	public String getB_product_id(){
		return b_product_id;
	}
	public void setB_seller_product_status(String b_seller_product_status){
		this.b_seller_product_status=b_seller_product_status;
	}
	public String getB_seller_product_status(){
		return b_seller_product_status;
	}
	public String getB_product_name() {
		return b_product_name;
	}
	public void setB_product_name(String b_product_name) {
		this.b_product_name = b_product_name;
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
	public String getB_seller_name() {
		return b_seller_name;
	}
	public void setB_seller_name(String b_seller_name) {
		this.b_seller_name = b_seller_name;
	}
}
