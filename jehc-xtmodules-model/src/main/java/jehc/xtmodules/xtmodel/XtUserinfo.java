package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_userinfo 员工信息表 
* 2015-11-25 19:34:25  邓纯杰
*/
public class XtUserinfo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_userinfo_id;/**用户ID**/
	private String xt_company_id;/**公司ID外键**/
	private String xt_departinfo_id;/**所属部门外键，DepartInfo的DepartId**/
	private String xt_post_id;/**岗位**/
	private String xt_userinfo_name;/**用户名**/
	private String xt_userinfo_passWord;/**密码**/
	private String xt_userinfo_address;/**地址**/
	private String xt_userinfo_state;/**状态:数据字典**/
	private int xt_userinfo_isDelete;/**0-未删除，1-删除**/
	private String xt_userinfo_image;/**用户头像**/
	private int xt_userinfo_status;/**0-离线1-在线2-忙碌3-离开**/
	private String xt_userinfo_realName;/**真实姓名**/
	private String xt_userinfo_phone;/**联系电话**/
	private String xt_userinfo_card;/**身份证号码**/
	private String xt_userinfo_sex;/**性别:数据字典**/
	private String xt_userinfo_ismarried;/**是否已婚:数据字典**/
	private int xt_userinfo_isAdmin;/**0,普通用户 1,超级管理**/
	private String xt_userinfo_nation;/**名族:数据字典**/
	private String xt_userinfo_origo;/**籍贯**/
	private String xt_userinfo_pic;/**照片**/
	private String xt_userinfo_mobile;/**移动电话**/
	private String xt_userinfo_ortherTel;/**其他电话**/
	private String xt_userinfo_intime;/**入职时间**/
	private String xt_userinfo_outTime;/**离职时间**/
	private String xt_userinfo_contractTime;/**合同到期时间**/
	private String xt_userinfo_remark;/**备注**/
	private String xt_userinfo_birthday;/**生日**/
	private String xt_userinfo_qq;/**qq号码**/
	private String xt_userinfo_email;/**电子邮件**/
	private String xt_userinfo_politicalStatus;/**政治面貌:数据字典**/
	private String xt_userinfo_highestDegree;/**文化程度:数据字典**/
	private String xt_userinfo_schoolName;/**毕业学校**/
	private String xt_userinfo_workYear;/**工作年限:数据字典**/
	private String xt_departinfo_name;/**部门名称**/
	private String xt_post_name;/**岗位名称**/
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_company_id(String xt_company_id){
		this.xt_company_id=xt_company_id;
	}
	public String getXt_company_id(){
		return xt_company_id;
	}
	public void setXt_departinfo_id(String xt_departinfo_id){
		this.xt_departinfo_id=xt_departinfo_id;
	}
	public String getXt_departinfo_id(){
		return xt_departinfo_id;
	}
	public void setXt_post_id(String xt_post_id){
		this.xt_post_id=xt_post_id;
	}
	public String getXt_post_id(){
		return xt_post_id;
	}
	public void setXt_userinfo_name(String xt_userinfo_name){
		this.xt_userinfo_name=xt_userinfo_name;
	}
	public String getXt_userinfo_name(){
		return xt_userinfo_name;
	}
	public void setXt_userinfo_passWord(String xt_userinfo_passWord){
		this.xt_userinfo_passWord=xt_userinfo_passWord;
	}
	public String getXt_userinfo_passWord(){
		return xt_userinfo_passWord;
	}
	public void setXt_userinfo_address(String xt_userinfo_address){
		this.xt_userinfo_address=xt_userinfo_address;
	}
	public String getXt_userinfo_address(){
		return xt_userinfo_address;
	}
	public void setXt_userinfo_state(String xt_userinfo_state){
		this.xt_userinfo_state=xt_userinfo_state;
	}
	public String getXt_userinfo_state(){
		return xt_userinfo_state;
	}
	public void setXt_userinfo_isDelete(int xt_userinfo_isDelete){
		this.xt_userinfo_isDelete=xt_userinfo_isDelete;
	}
	public int getXt_userinfo_isDelete(){
		return xt_userinfo_isDelete;
	}
	public void setXt_userinfo_image(String xt_userinfo_image){
		this.xt_userinfo_image=xt_userinfo_image;
	}
	public String getXt_userinfo_image(){
		return xt_userinfo_image;
	}
	public void setXt_userinfo_status(int xt_userinfo_status){
		this.xt_userinfo_status=xt_userinfo_status;
	}
	public int getXt_userinfo_status(){
		return xt_userinfo_status;
	}
	public void setXt_userinfo_realName(String xt_userinfo_realName){
		this.xt_userinfo_realName=xt_userinfo_realName;
	}
	public String getXt_userinfo_realName(){
		return xt_userinfo_realName;
	}
	public void setXt_userinfo_phone(String xt_userinfo_phone){
		this.xt_userinfo_phone=xt_userinfo_phone;
	}
	public String getXt_userinfo_phone(){
		return xt_userinfo_phone;
	}
	public void setXt_userinfo_card(String xt_userinfo_card){
		this.xt_userinfo_card=xt_userinfo_card;
	}
	public String getXt_userinfo_card(){
		return xt_userinfo_card;
	}
	public void setXt_userinfo_sex(String xt_userinfo_sex){
		this.xt_userinfo_sex=xt_userinfo_sex;
	}
	public String getXt_userinfo_sex(){
		return xt_userinfo_sex;
	}
	public void setXt_userinfo_ismarried(String xt_userinfo_ismarried){
		this.xt_userinfo_ismarried=xt_userinfo_ismarried;
	}
	public String getXt_userinfo_ismarried(){
		return xt_userinfo_ismarried;
	}
	public void setXt_userinfo_isAdmin(int xt_userinfo_isAdmin){
		this.xt_userinfo_isAdmin=xt_userinfo_isAdmin;
	}
	public int getXt_userinfo_isAdmin(){
		return xt_userinfo_isAdmin;
	}
	public void setXt_userinfo_nation(String xt_userinfo_nation){
		this.xt_userinfo_nation=xt_userinfo_nation;
	}
	public String getXt_userinfo_nation(){
		return xt_userinfo_nation;
	}
	public void setXt_userinfo_origo(String xt_userinfo_origo){
		this.xt_userinfo_origo=xt_userinfo_origo;
	}
	public String getXt_userinfo_origo(){
		return xt_userinfo_origo;
	}
	public void setXt_userinfo_pic(String xt_userinfo_pic){
		this.xt_userinfo_pic=xt_userinfo_pic;
	}
	public String getXt_userinfo_pic(){
		return xt_userinfo_pic;
	}
	public void setXt_userinfo_mobile(String xt_userinfo_mobile){
		this.xt_userinfo_mobile=xt_userinfo_mobile;
	}
	public String getXt_userinfo_mobile(){
		return xt_userinfo_mobile;
	}
	public void setXt_userinfo_ortherTel(String xt_userinfo_ortherTel){
		this.xt_userinfo_ortherTel=xt_userinfo_ortherTel;
	}
	public String getXt_userinfo_ortherTel(){
		return xt_userinfo_ortherTel;
	}
	public void setXt_userinfo_intime(String xt_userinfo_intime){
		this.xt_userinfo_intime=xt_userinfo_intime;
	}
	public String getXt_userinfo_intime(){
		return xt_userinfo_intime;
	}
	public void setXt_userinfo_outTime(String xt_userinfo_outTime){
		this.xt_userinfo_outTime=xt_userinfo_outTime;
	}
	public String getXt_userinfo_outTime(){
		return xt_userinfo_outTime;
	}
	public void setXt_userinfo_contractTime(String xt_userinfo_contractTime){
		this.xt_userinfo_contractTime=xt_userinfo_contractTime;
	}
	public String getXt_userinfo_contractTime(){
		return xt_userinfo_contractTime;
	}
	public void setXt_userinfo_remark(String xt_userinfo_remark){
		this.xt_userinfo_remark=xt_userinfo_remark;
	}
	public String getXt_userinfo_remark(){
		return xt_userinfo_remark;
	}
	public void setXt_userinfo_birthday(String xt_userinfo_birthday){
		this.xt_userinfo_birthday=xt_userinfo_birthday;
	}
	public String getXt_userinfo_birthday(){
		return xt_userinfo_birthday;
	}
	public void setXt_userinfo_qq(String xt_userinfo_qq){
		this.xt_userinfo_qq=xt_userinfo_qq;
	}
	public String getXt_userinfo_qq(){
		return xt_userinfo_qq;
	}
	public void setXt_userinfo_email(String xt_userinfo_email){
		this.xt_userinfo_email=xt_userinfo_email;
	}
	public String getXt_userinfo_email(){
		return xt_userinfo_email;
	}
	public void setXt_userinfo_politicalStatus(String xt_userinfo_politicalStatus){
		this.xt_userinfo_politicalStatus=xt_userinfo_politicalStatus;
	}
	public String getXt_userinfo_politicalStatus(){
		return xt_userinfo_politicalStatus;
	}
	public void setXt_userinfo_highestDegree(String xt_userinfo_highestDegree){
		this.xt_userinfo_highestDegree=xt_userinfo_highestDegree;
	}
	public String getXt_userinfo_highestDegree(){
		return xt_userinfo_highestDegree;
	}
	public void setXt_userinfo_schoolName(String xt_userinfo_schoolName){
		this.xt_userinfo_schoolName=xt_userinfo_schoolName;
	}
	public String getXt_userinfo_schoolName(){
		return xt_userinfo_schoolName;
	}
	public void setXt_userinfo_workYear(String xt_userinfo_workYear){
		this.xt_userinfo_workYear=xt_userinfo_workYear;
	}
	public String getXt_userinfo_workYear(){
		return xt_userinfo_workYear;
	}
	public String getXt_departinfo_name() {
		return xt_departinfo_name;
	}
	public void setXt_departinfo_name(String xt_departinfo_name) {
		this.xt_departinfo_name = xt_departinfo_name;
	}
	public String getXt_post_name() {
		return xt_post_name;
	}
	public void setXt_post_name(String xt_post_name) {
		this.xt_post_name = xt_post_name;
	}
}
