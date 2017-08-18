package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_product_price 基础商品价格 
* 2016-03-18 20:18:13  邓纯杰
*/
public class BProductPrice extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_product_price_id;/**商品价格编号**/
	private double base_price;/**基价**/
	private double suggested_price;/**建议价格**/
	private String b_seller_product_id;/**卖家商品编号**/
	private double original_price;/**原价即低价**/
	public void setB_product_price_id(String b_product_price_id){
		this.b_product_price_id=b_product_price_id;
	}
	public String getB_product_price_id(){
		return b_product_price_id;
	}
	public void setBase_price(double base_price){
		this.base_price=base_price;
	}
	public double getBase_price(){
		return base_price;
	}
	public void setSuggested_price(double suggested_price){
		this.suggested_price=suggested_price;
	}
	public double getSuggested_price(){
		return suggested_price;
	}
	public void setB_seller_product_id(String b_seller_product_id){
		this.b_seller_product_id=b_seller_product_id;
	}
	public String getB_seller_product_id(){
		return b_seller_product_id;
	}
	public void setOriginal_price(double original_price){
		this.original_price=original_price;
	}
	public double getOriginal_price(){
		return original_price;
	}
}
