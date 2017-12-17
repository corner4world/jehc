package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_warehouse 基础仓库 
* 2016-01-27 14:21:55  邓纯杰
*/
public class BWarehouse extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_warehouse_id;/**仓库编号**/
	private String b_warehouse_name;/**仓库名称**/
	private String b_warehouse_address;/**仓库详细地址**/
	private String xt_provinceID;/**所在省份区域**/
	private String xt_cityID;/**所在城市区域**/
	private String xt_districtID;/**所在区县区域**/
	private Date b_warehouse_ctime;/**创建时间**/
	private Date b_warehouse_mtime;/**修改时间**/
	private String xt_userinfo_id;/**操作者**/
	private int b_warehouse_type;/**仓库类型:0赠品1疵品2正品**/
	private String b_seller_id;/**商户编号**/
	private String xt_provinceName;/**省份**/
	private String xt_cityName;/**城市*/
	private String xt_districtName;/**区县**/
	private String b_seller_name;/**商户名称**/
	public void setB_warehouse_id(String b_warehouse_id){
		this.b_warehouse_id=b_warehouse_id;
	}
	public String getB_warehouse_id(){
		return b_warehouse_id;
	}
	public void setB_warehouse_name(String b_warehouse_name){
		this.b_warehouse_name=b_warehouse_name;
	}
	public String getB_warehouse_name(){
		return b_warehouse_name;
	}
	public void setB_warehouse_address(String b_warehouse_address){
		this.b_warehouse_address=b_warehouse_address;
	}
	public String getB_warehouse_address(){
		return b_warehouse_address;
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
	
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public Date getB_warehouse_ctime() {
		return b_warehouse_ctime;
	}
	public void setB_warehouse_ctime(Date b_warehouse_ctime) {
		this.b_warehouse_ctime = b_warehouse_ctime;
	}
	public Date getB_warehouse_mtime() {
		return b_warehouse_mtime;
	}
	public void setB_warehouse_mtime(Date b_warehouse_mtime) {
		this.b_warehouse_mtime = b_warehouse_mtime;
	}
	public int getB_warehouse_type() {
		return b_warehouse_type;
	}
	public void setB_warehouse_type(int b_warehouse_type) {
		this.b_warehouse_type = b_warehouse_type;
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
	public String getB_seller_id() {
		return b_seller_id;
	}
	public void setB_seller_id(String b_seller_id) {
		this.b_seller_id = b_seller_id;
	}
	public String getB_seller_name() {
		return b_seller_name;
	}
	public void setB_seller_name(String b_seller_name) {
		this.b_seller_name = b_seller_name;
	}
}
