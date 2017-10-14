package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_seller_contacts 基础卖家联系人 
* 2016-02-18 17:11:48  邓纯杰
*/
public class BSellerContacts extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_seller_contacts_id;/**卖家明细编号**/
	private String b_seller_id;/**卖家编号**/
	private String b_seller_contacts_uname;/**卖家联系人**/
	private String b_seller_contacts_tel;/**卖家联系人电话**/
	private String b_seller_contacts_sex;/**卖家联系人性别**/
	private String b_seller_contacts_type;/**卖家联系人类型0普通员工1法人2经理3主管**/
	private String b_seller_contacts_turnover;/**是否离职0正常1已离职**/
	public void setB_seller_contacts_id(String b_seller_contacts_id){
		this.b_seller_contacts_id=b_seller_contacts_id;
	}
	public String getB_seller_contacts_id(){
		return b_seller_contacts_id;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
	public void setB_seller_contacts_uname(String b_seller_contacts_uname){
		this.b_seller_contacts_uname=b_seller_contacts_uname;
	}
	public String getB_seller_contacts_uname(){
		return b_seller_contacts_uname;
	}
	public void setB_seller_contacts_tel(String b_seller_contacts_tel){
		this.b_seller_contacts_tel=b_seller_contacts_tel;
	}
	public String getB_seller_contacts_tel(){
		return b_seller_contacts_tel;
	}
	public void setB_seller_contacts_sex(String b_seller_contacts_sex){
		this.b_seller_contacts_sex=b_seller_contacts_sex;
	}
	public String getB_seller_contacts_sex(){
		return b_seller_contacts_sex;
	}
	public void setB_seller_contacts_type(String b_seller_contacts_type){
		this.b_seller_contacts_type=b_seller_contacts_type;
	}
	public String getB_seller_contacts_type(){
		return b_seller_contacts_type;
	}
	public void setB_seller_contacts_turnover(String b_seller_contacts_turnover){
		this.b_seller_contacts_turnover=b_seller_contacts_turnover;
	}
	public String getB_seller_contacts_turnover(){
		return b_seller_contacts_turnover;
	}
}
