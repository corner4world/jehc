package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_member 基础会员 
* 2016-01-08 22:35:33  邓纯杰
*/
public class BMember extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_member_id;/**会员编号**/
	private String b_member_name;/**会员名称**/
	private String b_member_tel;/**会员电话**/
	private int b_member_status;/**状态0正常1禁用**/
	private int b_member_level;/**等级**/
	private String b_member_address;/**会员详细地址**/
	private String xt_provinceID;/**省份**/
	private String xt_cityID;/**城市**/
	private String xt_districtID;/**区县**/
	private int b_member_sex;/**性别0男1女**/
	private String b_member_pwd;/**密码**/
	private String b_member_email;/**邮箱**/
	private String b_member_qq;/**qq账号**/
	private String b_member_wb;/**微博账号**/
	private int b_member_type;/**类型0普通会员1VIP会员**/
	private String xt_provinceName;/**省份**/
	private String xt_cityName;/**城市*/
	private String xt_districtName;/**区县**/
	private Date b_member_ctime;/**注册时间**/
	private Date b_member_mtime;/**修改时间**/
	public void setB_member_id(String b_member_id){
		this.b_member_id=b_member_id;
	}
	public String getB_member_id(){
		return b_member_id;
	}
	public void setB_member_name(String b_member_name){
		this.b_member_name=b_member_name;
	}
	public String getB_member_name(){
		return b_member_name;
	}
	public void setB_member_tel(String b_member_tel){
		this.b_member_tel=b_member_tel;
	}
	public String getB_member_tel(){
		return b_member_tel;
	}
	public void setB_member_level(int b_member_level){
		this.b_member_level=b_member_level;
	}
	public int getB_member_level(){
		return b_member_level;
	}
	public void setB_member_address(String b_member_address){
		this.b_member_address=b_member_address;
	}
	public String getB_member_address(){
		return b_member_address;
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
	
	public int getB_member_sex() {
		return b_member_sex;
	}
	public void setB_member_pwd(String b_member_pwd){
		this.b_member_pwd=b_member_pwd;
	}
	public String getB_member_pwd(){
		return b_member_pwd;
	}
	public void setB_member_email(String b_member_email){
		this.b_member_email=b_member_email;
	}
	public String getB_member_email(){
		return b_member_email;
	}
	public void setB_member_qq(String b_member_qq){
		this.b_member_qq=b_member_qq;
	}
	public String getB_member_qq(){
		return b_member_qq;
	}
	public void setB_member_wb(String b_member_wb){
		this.b_member_wb=b_member_wb;
	}
	public String getB_member_wb(){
		return b_member_wb;
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
	public int getB_member_status() {
		return b_member_status;
	}
	public void setB_member_status(int b_member_status) {
		this.b_member_status = b_member_status;
	}
	public int getB_member_type() {
		return b_member_type;
	}
	public void setB_member_type(int b_member_type) {
		this.b_member_type = b_member_type;
	}
	public Date getB_member_ctime() {
		return b_member_ctime;
	}
	public void setB_member_ctime(Date b_member_ctime) {
		this.b_member_ctime = b_member_ctime;
	}
	public Date getB_member_mtime() {
		return b_member_mtime;
	}
	public void setB_member_mtime(Date b_member_mtime) {
		this.b_member_mtime = b_member_mtime;
	}
	public void setB_member_sex(int b_member_sex) {
		this.b_member_sex = b_member_sex;
	}
	
}
