package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_departinfo 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
public class XtDepartinfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_departinfo_id;/**序列号**/
	private String xt_company_id;/**公司ID**/
	private String xt_departinfo_parentId;/**部门父ID**/
	private String xt_departinfo_name;/**部门名称**/
	private String xt_departinfo_connectTelNo;/**联系电话**/
	private String xt_departinfo_mobileTelNo;/**移动电话**/
	private String xt_departinfo_faxes;/**传真**/
	private String xt_departinfo_desc;/**描述部门信息**/
	private String xt_departinfo_image;/**图片**/
	private int xt_departinfo_leaf;/**是否存在子叶0表示存在子节**/
	private int xt_departinfo_isdelete = 0;/**是否删除0未删除1已删除**/
	private String xt_departinfo_time;/**立成时间**/
	private String xt_departinfo_type;/**部门性质**/
	public void setXt_departinfo_id(String xt_departinfo_id){
		this.xt_departinfo_id=xt_departinfo_id;
	}
	public String getXt_departinfo_id(){
		return xt_departinfo_id;
	}
	public void setXt_company_id(String xt_company_id){
		this.xt_company_id=xt_company_id;
	}
	public String getXt_company_id(){
		return xt_company_id;
	}
	public void setXt_departinfo_parentId(String xt_departinfo_parentId){
		this.xt_departinfo_parentId=xt_departinfo_parentId;
	}
	public String getXt_departinfo_parentId(){
		return xt_departinfo_parentId;
	}
	public void setXt_departinfo_name(String xt_departinfo_name){
		this.xt_departinfo_name=xt_departinfo_name;
	}
	public String getXt_departinfo_name(){
		return xt_departinfo_name;
	}
	public void setXt_departinfo_connectTelNo(String xt_departinfo_connectTelNo){
		this.xt_departinfo_connectTelNo=xt_departinfo_connectTelNo;
	}
	public String getXt_departinfo_connectTelNo(){
		return xt_departinfo_connectTelNo;
	}
	public String getXt_departinfo_mobileTelNo() {
		return xt_departinfo_mobileTelNo;
	}
	public void setXt_departinfo_mobileTelNo(String xt_departinfo_mobileTelNo) {
		this.xt_departinfo_mobileTelNo = xt_departinfo_mobileTelNo;
	}
	public void setXt_departinfo_faxes(String xt_departinfo_faxes){
		this.xt_departinfo_faxes=xt_departinfo_faxes;
	}
	public String getXt_departinfo_faxes(){
		return xt_departinfo_faxes;
	}
	public void setXt_departinfo_desc(String xt_departinfo_desc){
		this.xt_departinfo_desc=xt_departinfo_desc;
	}
	public String getXt_departinfo_desc(){
		return xt_departinfo_desc;
	}
	public void setXt_departinfo_image(String xt_departinfo_image){
		this.xt_departinfo_image=xt_departinfo_image;
	}
	public String getXt_departinfo_image(){
		return xt_departinfo_image;
	}
	public void setXt_departinfo_leaf(int xt_departinfo_leaf){
		this.xt_departinfo_leaf=xt_departinfo_leaf;
	}
	public int getXt_departinfo_leaf(){
		return xt_departinfo_leaf;
	}
	public void setXt_departinfo_isdelete(int xt_departinfo_isdelete){
		this.xt_departinfo_isdelete=xt_departinfo_isdelete;
	}
	public int getXt_departinfo_isdelete(){
		return xt_departinfo_isdelete;
	}
	public void setXt_departinfo_time(String xt_departinfo_time){
		this.xt_departinfo_time=xt_departinfo_time;
	}
	public String getXt_departinfo_time(){
		return xt_departinfo_time;
	}
	public void setXt_departinfo_type(String xt_departinfo_type){
		this.xt_departinfo_type=xt_departinfo_type;
	}
	public String getXt_departinfo_type(){
		return xt_departinfo_type;
	}
}
