package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtmodel.XtAttachment;

/**
* b_product_color_default 基础商品默认颜色 
* 2016-01-19 15:38:05  邓纯杰
*/
public class BProductColorDefault  extends XtAttachment implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_product_color_default_id;/**编号**/
	private String b_product_color_default_name;/**名称**/
	private Date b_product_color_default_ctime;/**创建时间**/
	private Date b_product_color_default_mtime;/**修改时间**/
	private int b_product_color_default_width;/**图片宽度**/
	private int b_product_color_default_height;/**图片高度**/
	private String xt_userinfo_id;/**创建人**/
	private int b_product_color_default_sort;/**排序编号**/
	private int b_product_color_default_status;/**状态0正常1禁用**/
	private String xt_attachment_id;/**附件编号**/
	private String b_product_id;/**商品编号**/
	private String b_product_color_default_remark;/**备注**/
	public void setB_product_color_default_id(String b_product_color_default_id){
		this.b_product_color_default_id=b_product_color_default_id;
	}
	public String getB_product_color_default_id(){
		return b_product_color_default_id;
	}
	public void setB_product_color_default_name(String b_product_color_default_name){
		this.b_product_color_default_name=b_product_color_default_name;
	}
	public String getB_product_color_default_name(){
		return b_product_color_default_name;
	}
	public void setB_product_color_default_width(int b_product_color_default_width){
		this.b_product_color_default_width=b_product_color_default_width;
	}
	public int getB_product_color_default_width(){
		return b_product_color_default_width;
	}
	public void setB_product_color_default_height(int b_product_color_default_height){
		this.b_product_color_default_height=b_product_color_default_height;
	}
	public int getB_product_color_default_height(){
		return b_product_color_default_height;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_product_color_default_sort(int b_product_color_default_sort){
		this.b_product_color_default_sort=b_product_color_default_sort;
	}
	public int getB_product_color_default_sort(){
		return b_product_color_default_sort;
	}
	
	public Date getB_product_color_default_ctime() {
		return b_product_color_default_ctime;
	}
	public void setB_product_color_default_ctime(Date b_product_color_default_ctime) {
		this.b_product_color_default_ctime = b_product_color_default_ctime;
	}
	public Date getB_product_color_default_mtime() {
		return b_product_color_default_mtime;
	}
	public void setB_product_color_default_mtime(Date b_product_color_default_mtime) {
		this.b_product_color_default_mtime = b_product_color_default_mtime;
	}
	public int getB_product_color_default_status() {
		return b_product_color_default_status;
	}
	public void setB_product_color_default_status(int b_product_color_default_status) {
		this.b_product_color_default_status = b_product_color_default_status;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
	public void setB_product_id(String b_product_id){
		this.b_product_id=b_product_id;
	}
	public String getB_product_id(){
		return b_product_id;
	}
	public void setB_product_color_default_remark(String b_product_color_default_remark){
		this.b_product_color_default_remark=b_product_color_default_remark;
	}
	public String getB_product_color_default_remark(){
		return b_product_color_default_remark;
	}
}
