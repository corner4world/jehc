package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_seller_account 基础卖家账号 
* 2016-02-18 17:07:37  邓纯杰
*/
public class BSellerAccount extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_seller_account_id;/**卖家账户编号**/
	private String b_seller_id;/**卖家编号**/
	public void setB_seller_account_id(String b_seller_account_id){
		this.b_seller_account_id=b_seller_account_id;
	}
	public String getB_seller_account_id(){
		return b_seller_account_id;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
}
