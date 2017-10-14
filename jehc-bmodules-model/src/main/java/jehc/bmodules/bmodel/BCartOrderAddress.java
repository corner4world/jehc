package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_cart_order_address 基础购物车订单常用配送地址 
* 2016-02-22 21:17:24  邓纯杰
*/
public class BCartOrderAddress extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_cart_order_address_id;/**购物车订单常用地址**/
	private String b_cart_order_address_address;/**详细地址**/
	private String xt_provinceID;/**省份**/
	private String xt_cityID;/**城市**/
	private String xt_districtID;/**区县**/
	private String b_member_id;/**会员编号**/
	private String b_cart_order_address_status;/**状态0正常1已作废**/
	public void setB_cart_order_address_id(String b_cart_order_address_id){
		this.b_cart_order_address_id=b_cart_order_address_id;
	}
	public String getB_cart_order_address_id(){
		return b_cart_order_address_id;
	}
	public void setB_cart_order_address_address(String b_cart_order_address_address){
		this.b_cart_order_address_address=b_cart_order_address_address;
	}
	public String getB_cart_order_address_address(){
		return b_cart_order_address_address;
	}
	public void setXt_provinceID(String xt_provinceID){
		this.xt_provinceID=xt_provinceID;
	}
	public String getXt_provinceID(){
		return xt_provinceID;
	}
	public void setXt_cityID(String xt_cityID){
		this.xt_cityID=xt_cityID;
	}
	public String getXt_cityID(){
		return xt_cityID;
	}
	public void setXt_districtID(String xt_districtID){
		this.xt_districtID=xt_districtID;
	}
	public String getXt_districtID(){
		return xt_districtID;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setB_cart_order_address_status(String b_cart_order_address_status){
		this.b_cart_order_address_status=b_cart_order_address_status;
	}
	public String getB_cart_order_address_status(){
		return b_cart_order_address_status;
	}
}
