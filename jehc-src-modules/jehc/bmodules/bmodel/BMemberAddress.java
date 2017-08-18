package jehc.bmodules.bmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_member_address 基础会员常用地址 
* 2016-02-22 16:44:23  邓纯杰
*/
public class BMemberAddress extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_member_address_id;/**会员常用地址编号**/
	private String b_member_id;/**会员编号**/
	private String xt_provinceID;/**省份编号**/
	private String xt_cityID;/**城市编号**/
	private String xt_districtID;/**区县编号**/
	private String postcode;/**邮编地址**/
	private String b_member_address_detail;/**详细地址**/
	private String b_member_address_ctime;/**创建时间**/
	private String b_member_address_mtime;/**修改时间**/
	private String b_member_address_status;/**是否设为常用收货地址0是1否**/
	private String b_member_address_isdel;/**是否删除0正常1已删除**/
	private String xt_provinceName;/**省份**/
	private String xt_cityName;/**城市*/
	private String xt_districtName;/**区县**/
	public void setB_member_address_id(String b_member_address_id){
		this.b_member_address_id=b_member_address_id;
	}
	public String getB_member_address_id(){
		return b_member_address_id;
	}
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
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
	public void setPostcode(String postcode){
		this.postcode=postcode;
	}
	public String getPostcode(){
		return postcode;
	}
	public void setB_member_address_detail(String b_member_address_detail){
		this.b_member_address_detail=b_member_address_detail;
	}
	public String getB_member_address_detail(){
		return b_member_address_detail;
	}
	public void setB_member_address_ctime(String b_member_address_ctime){
		this.b_member_address_ctime=b_member_address_ctime;
	}
	public String getB_member_address_ctime(){
		return b_member_address_ctime;
	}
	public void setB_member_address_mtime(String b_member_address_mtime){
		this.b_member_address_mtime=b_member_address_mtime;
	}
	public String getB_member_address_mtime(){
		return b_member_address_mtime;
	}
	public void setB_member_address_status(String b_member_address_status){
		this.b_member_address_status=b_member_address_status;
	}
	public String getB_member_address_status(){
		return b_member_address_status;
	}
	public void setB_member_address_isdel(String b_member_address_isdel){
		this.b_member_address_isdel=b_member_address_isdel;
	}
	public String getB_member_address_isdel(){
		return b_member_address_isdel;
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
