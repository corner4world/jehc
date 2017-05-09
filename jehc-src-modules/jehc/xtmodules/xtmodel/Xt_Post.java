package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_post 用户岗位表(xt_post) 
* 2015-05-13 16:48:11  邓纯杰
*/
public class Xt_Post implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_post_id;/**序列号**/
	private String xt_departinfo_id;/**部门编号**/
	private String xt_post_parentId;/**上级岗位**/
	private String xt_post_name;/**岗位名称**/
	private String xt_post_desc;/**岗位描述**/
	private int xt_post_maxNum;/**岗位最大人数**/
	private int xt_post_isLeaf;/**是否存在子级0表示存在**/
	private String xt_post_image;/**图片**/
	private int xt_post_grade;/**岗位级别**/
	private int xt_post_isdelete=0;/**是否删除0正常1删除**/
	private String xt_departinfo_name;/**部门名称**/
	public void setXt_post_id(String xt_post_id){
		this.xt_post_id=xt_post_id;
	}
	public String getXt_post_id(){
		return xt_post_id;
	}
	public void setXt_departinfo_id(String xt_departinfo_id){
		this.xt_departinfo_id=xt_departinfo_id;
	}
	public String getXt_departinfo_id(){
		return xt_departinfo_id;
	}
	public void setXt_post_parentId(String xt_post_parentId){
		this.xt_post_parentId=xt_post_parentId;
	}
	public String getXt_post_parentId(){
		return xt_post_parentId;
	}
	public void setXt_post_name(String xt_post_name){
		this.xt_post_name=xt_post_name;
	}
	public String getXt_post_name(){
		return xt_post_name;
	}
	public void setXt_post_desc(String xt_post_desc){
		this.xt_post_desc=xt_post_desc;
	}
	public String getXt_post_desc(){
		return xt_post_desc;
	}
	public void setXt_post_maxNum(int xt_post_maxNum){
		this.xt_post_maxNum=xt_post_maxNum;
	}
	public int getXt_post_maxNum(){
		return xt_post_maxNum;
	}
	public void setXt_post_isLeaf(int xt_post_isLeaf){
		this.xt_post_isLeaf=xt_post_isLeaf;
	}
	public int getXt_post_isLeaf(){
		return xt_post_isLeaf;
	}
	public void setXt_post_image(String xt_post_image){
		this.xt_post_image=xt_post_image;
	}
	public String getXt_post_image(){
		return xt_post_image;
	}
	public void setXt_post_grade(int xt_post_grade){
		this.xt_post_grade=xt_post_grade;
	}
	public int getXt_post_grade(){
		return xt_post_grade;
	}
	public String getXt_departinfo_name() {
		return xt_departinfo_name;
	}
	public void setXt_departinfo_name(String xt_departinfo_name) {
		this.xt_departinfo_name = xt_departinfo_name;
	}
	public int getXt_post_isdelete() {
		return xt_post_isdelete;
	}
	public void setXt_post_isdelete(int xt_post_isdelete) {
		this.xt_post_isdelete = xt_post_isdelete;
	}
}
