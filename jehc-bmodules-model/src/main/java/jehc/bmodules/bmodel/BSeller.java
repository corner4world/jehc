package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_seller 基础卖家 
* 2016-01-08 22:54:00  邓纯杰
*/
public class BSeller extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_seller_id;/**基础卖家编号**/
	private String b_seller_name;/**卖家名称**/
	private String b_seller_status;/**状态0正常1禁用**/
	private String b_seller_tel;/**卖家电话**/
	private int b_seller_level;/**卖家等级**/
	private String xt_userinfo_id;/**操作者**/
	private String b_seller_login_id;/**卖家登陆账户编号**/
	private String b_seller_bank;/**银行名称**/
	private String b_seller_bank_num;/**银行卡号**/
	private int b_seller_official;/**是否官方商店**/
	private String b_seller_address;/**地址**/
	private Date b_seller_ctime;/**创建时间**/
	private Date b_seller_mtime;/**修改时间**/
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
	public void setB_seller_name(String b_seller_name){
		this.b_seller_name=b_seller_name;
	}
	public String getB_seller_name(){
		return b_seller_name;
	}
	public void setB_seller_status(String b_seller_status){
		this.b_seller_status=b_seller_status;
	}
	public String getB_seller_status(){
		return b_seller_status;
	}
	public void setB_seller_tel(String b_seller_tel){
		this.b_seller_tel=b_seller_tel;
	}
	public String getB_seller_tel(){
		return b_seller_tel;
	}
	public void setB_seller_level(int b_seller_level){
		this.b_seller_level=b_seller_level;
	}
	public int getB_seller_level(){
		return b_seller_level;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_seller_login_id(String b_seller_login_id){
		this.b_seller_login_id=b_seller_login_id;
	}
	public String getB_seller_login_id(){
		return b_seller_login_id;
	}
	public void setB_seller_bank(String b_seller_bank){
		this.b_seller_bank=b_seller_bank;
	}
	public String getB_seller_bank(){
		return b_seller_bank;
	}
	public void setB_seller_bank_num(String b_seller_bank_num){
		this.b_seller_bank_num=b_seller_bank_num;
	}
	public String getB_seller_bank_num(){
		return b_seller_bank_num;
	}
	public int getB_seller_official() {
		return b_seller_official;
	}
	public void setB_seller_official(int b_seller_official) {
		this.b_seller_official = b_seller_official;
	}
	public String getB_seller_address() {
		return b_seller_address;
	}
	public void setB_seller_address(String b_seller_address) {
		this.b_seller_address = b_seller_address;
	}
	public Date getB_seller_ctime() {
		return b_seller_ctime;
	}
	public void setB_seller_ctime(Date b_seller_ctime) {
		this.b_seller_ctime = b_seller_ctime;
	}
	public Date getB_seller_mtime() {
		return b_seller_mtime;
	}
	public void setB_seller_mtime(Date b_seller_mtime) {
		this.b_seller_mtime = b_seller_mtime;
	}
	
}
