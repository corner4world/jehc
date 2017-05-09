package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_company 系统公司信息表 
* 2015-05-12 22:59:42  邓纯杰
*/
public class Xt_Company implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_company_id;/**公司ID**/
	private String xt_company_parentId;/**上级公司ID**/
	private String xt_company_name;/**公司名称**/
	private String xt_company_tel;/**公司电话**/
	private String xt_company_remark;/**公司简介**/
	private String xt_company_userName;/**公司联系人**/
	private String xt_company_address;/**公司地址**/
	private String xt_company_type;/**公司性质**/
	private String xt_company_upTime;/**公司成立时间**/
	private int xt_company_isLeaf;/**是否为子叶0表示存在**/
	private String xt_company_images;/****/
	private String xt_company_ceo;/**公司总体负责人**/
	private int xt_company_isdelete;/**是否删除0表示未删除1表示已删除**/
	public void setXt_company_id(String xt_company_id){
		this.xt_company_id=xt_company_id;
	}
	public String getXt_company_id(){
		return xt_company_id;
	}
	public void setXt_company_parentId(String xt_company_parentId){
		this.xt_company_parentId=xt_company_parentId;
	}
	public String getXt_company_parentId(){
		return xt_company_parentId;
	}
	public void setXt_company_name(String xt_company_name){
		this.xt_company_name=xt_company_name;
	}
	public String getXt_company_name(){
		return xt_company_name;
	}
	public void setXt_company_tel(String xt_company_tel){
		this.xt_company_tel=xt_company_tel;
	}
	public String getXt_company_tel(){
		return xt_company_tel;
	}
	public void setXt_company_remark(String xt_company_remark){
		this.xt_company_remark=xt_company_remark;
	}
	public String getXt_company_remark(){
		return xt_company_remark;
	}
	public void setXt_company_userName(String xt_company_userName){
		this.xt_company_userName=xt_company_userName;
	}
	public String getXt_company_userName(){
		return xt_company_userName;
	}
	public void setXt_company_address(String xt_company_address){
		this.xt_company_address=xt_company_address;
	}
	public String getXt_company_address(){
		return xt_company_address;
	}
	public void setXt_company_type(String xt_company_type){
		this.xt_company_type=xt_company_type;
	}
	public String getXt_company_type(){
		return xt_company_type;
	}
	public void setXt_company_upTime(String xt_company_upTime){
		this.xt_company_upTime=xt_company_upTime;
	}
	public String getXt_company_upTime(){
		return xt_company_upTime;
	}
	public void setXt_company_isLeaf(int xt_company_isLeaf){
		this.xt_company_isLeaf=xt_company_isLeaf;
	}
	public int getXt_company_isLeaf(){
		return xt_company_isLeaf;
	}
	public void setXt_company_images(String xt_company_images){
		this.xt_company_images=xt_company_images;
	}
	public String getXt_company_images(){
		return xt_company_images;
	}
	public void setXt_company_ceo(String xt_company_ceo){
		this.xt_company_ceo=xt_company_ceo;
	}
	public String getXt_company_ceo(){
		return xt_company_ceo;
	}
	public void setXt_company_isdelete(int xt_company_isdelete){
		this.xt_company_isdelete=xt_company_isdelete;
	}
	public int getXt_company_isdelete(){
		return xt_company_isdelete;
	}
}
