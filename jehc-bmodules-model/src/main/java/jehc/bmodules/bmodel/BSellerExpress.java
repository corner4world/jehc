package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_seller_express 基础卖家快递 
* 2016-02-18 17:14:52  邓纯杰
*/
public class BSellerExpress extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_seller_express_id;/**卖家快递编号**/
	private String b_seller_id;/**卖家编号**/
	private String b_seller_express_name;/**快递公司名称**/
	private String b_seller_express_status;/**状态0正常1关闭**/
	private String b_seller_express_ctime;/**创建时间**/
	private String b_seller_express_mtime;/**修改时间**/
	public void setB_seller_express_id(String b_seller_express_id){
		this.b_seller_express_id=b_seller_express_id;
	}
	public String getB_seller_express_id(){
		return b_seller_express_id;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
	public void setB_seller_express_name(String b_seller_express_name){
		this.b_seller_express_name=b_seller_express_name;
	}
	public String getB_seller_express_name(){
		return b_seller_express_name;
	}
	public void setB_seller_express_status(String b_seller_express_status){
		this.b_seller_express_status=b_seller_express_status;
	}
	public String getB_seller_express_status(){
		return b_seller_express_status;
	}
	public void setB_seller_express_ctime(String b_seller_express_ctime){
		this.b_seller_express_ctime=b_seller_express_ctime;
	}
	public String getB_seller_express_ctime(){
		return b_seller_express_ctime;
	}
	public void setB_seller_express_mtime(String b_seller_express_mtime){
		this.b_seller_express_mtime=b_seller_express_mtime;
	}
	public String getB_seller_express_mtime(){
		return b_seller_express_mtime;
	}
}
