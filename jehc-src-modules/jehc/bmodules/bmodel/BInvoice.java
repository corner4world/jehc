package jehc.bmodules.bmodel;

import java.io.Serializable;

/**
* b_invoice 基础发票 
* 2016-02-22 14:35:28  邓纯杰
*/
public class BInvoice extends BCartOrderAddress implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_invoice_id;/**发票编号**/
	private String b_invoice_type;/**发票类型0普通发票1抬头发票**/
	private String b_invoice_name;/**发票名称**/
	private String b_invoice_company;/**发票公司**/
	private String b_invoice_num;/**发票号**/
	private String b_invoice_status;/**状态0正常1已取消**/
	private String b_member_id;/**会员编号**/
	private String b_invoice_bank;/**开户银行**/
	private String b_invoice_bank_num;/**银行账号**/
	private String b_invoice_tel;/**联系电话**/
	private String b_invoice_ctime;/**创建时间**/
	private String b_invoice_mtime;/**修改时间**/
	private String xt_provinceID;/**省份**/
	private String xt_cityID;/**城市**/
	private String xt_districtID;/**区县**/
	private String b_invoice_address;/**详细地址**/
	private String xt_provinceName;/**省份**/
	private String xt_cityName;/**城市*/
	private String xt_districtName;/**区县**/
	public void setB_invoice_id(String b_invoice_id){
		this.b_invoice_id=b_invoice_id;
	}
	public String getB_invoice_id(){
		return b_invoice_id;
	}
	public void setB_invoice_type(String b_invoice_type){
		this.b_invoice_type=b_invoice_type;
	}
	public String getB_invoice_type(){
		return b_invoice_type;
	}
	public void setB_invoice_name(String b_invoice_name){
		this.b_invoice_name=b_invoice_name;
	}
	public String getB_invoice_name(){
		return b_invoice_name;
	}
	public void setB_invoice_company(String b_invoice_company){
		this.b_invoice_company=b_invoice_company;
	}
	public String getB_invoice_company(){
		return b_invoice_company;
	}
	public void setB_invoice_num(String b_invoice_num){
		this.b_invoice_num=b_invoice_num;
	}
	public String getB_invoice_num(){
		return b_invoice_num;
	}
	public void setB_invoice_status(String b_invoice_status){
		this.b_invoice_status=b_invoice_status;
	}
	public String getB_invoice_status(){
		return b_invoice_status;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setB_invoice_bank(String b_invoice_bank){
		this.b_invoice_bank=b_invoice_bank;
	}
	public String getB_invoice_bank(){
		return b_invoice_bank;
	}
	public void setB_invoice_bank_num(String b_invoice_bank_num){
		this.b_invoice_bank_num=b_invoice_bank_num;
	}
	public String getB_invoice_bank_num(){
		return b_invoice_bank_num;
	}
	public void setB_invoice_tel(String b_invoice_tel){
		this.b_invoice_tel=b_invoice_tel;
	}
	public String getB_invoice_tel(){
		return b_invoice_tel;
	}
	public void setB_invoice_ctime(String b_invoice_ctime){
		this.b_invoice_ctime=b_invoice_ctime;
	}
	public String getB_invoice_ctime(){
		return b_invoice_ctime;
	}
	public void setB_invoice_mtime(String b_invoice_mtime){
		this.b_invoice_mtime=b_invoice_mtime;
	}
	public String getB_invoice_mtime(){
		return b_invoice_mtime;
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
	public void setB_invoice_address(String b_invoice_address){
		this.b_invoice_address=b_invoice_address;
	}
	public String getB_invoice_address(){
		return b_invoice_address;
	}
	public String getXt_provinceName() {
		return xt_provinceName;
	}
	public void setXt_provinceName(String xt_provinceName) {
		this.xt_provinceName = xt_provinceName;
	}
	public String getXt_cityName() {
		return xt_cityName;
	}
	public void setXt_cityName(String xt_cityName) {
		this.xt_cityName = xt_cityName;
	}
	public String getXt_districtName() {
		return xt_districtName;
	}
	public void setXt_districtName(String xt_districtName) {
		this.xt_districtName = xt_districtName;
	}
}
