package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtmodel.XtAttachment;

/**
* b_product_img_default 基础商品默认图片 
* 2016-01-09 09:06:38  邓纯杰
*/
public class BProductImgDefault extends XtAttachment implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_product_img_default_id;/**商品默认图片编号**/
	private String b_product_img_name;/**商品图片名称**/
	private Date b_product_img_ctime;/**创建时间**/
	private Date b_product_img_mtime;/**修改时间**/
	private int b_product_img_type;/**图片类型:0大图片1小图片**/
	private int b_product_img_status;/**状态:0可用1禁用**/
	private String b_product_id;/**商品编号**/
	private String xt_attachment_id;/**附件编号**/
	private int b_product_img_width;/**商品图片宽度**/
	private int b_product_img_height;/**商品图片高度**/
	private String xt_userinfo_id;/**操作者**/
	private String b_product_img_remark;/**图片使用说明**/
	public void setB_product_img_default_id(String b_product_img_default_id){
		this.b_product_img_default_id=b_product_img_default_id;
	}
	public String getB_product_img_default_id(){
		return b_product_img_default_id;
	}
	public void setB_product_img_name(String b_product_img_name){
		this.b_product_img_name=b_product_img_name;
	}
	public String getB_product_img_name(){
		return b_product_img_name;
	}
	public Date getB_product_img_ctime() {
		return b_product_img_ctime;
	}
	public void setB_product_img_ctime(Date b_product_img_ctime) {
		this.b_product_img_ctime = b_product_img_ctime;
	}
	public Date getB_product_img_mtime() {
		return b_product_img_mtime;
	}
	public void setB_product_img_mtime(Date b_product_img_mtime) {
		this.b_product_img_mtime = b_product_img_mtime;
	}
	public int getB_product_img_type() {
		return b_product_img_type;
	}
	public void setB_product_img_type(int b_product_img_type) {
		this.b_product_img_type = b_product_img_type;
	}
	public int getB_product_img_status() {
		return b_product_img_status;
	}
	public void setB_product_img_status(int b_product_img_status) {
		this.b_product_img_status = b_product_img_status;
	}
	public void setB_product_id(String b_product_id){
		this.b_product_id=b_product_id;
	}
	public String getB_product_id(){
		return b_product_id;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
	public void setB_product_img_width(int b_product_img_width){
		this.b_product_img_width=b_product_img_width;
	}
	public int getB_product_img_width(){
		return b_product_img_width;
	}
	public void setB_product_img_height(int b_product_img_height){
		this.b_product_img_height=b_product_img_height;
	}
	public int getB_product_img_height(){
		return b_product_img_height;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_product_img_remark(String b_product_img_remark){
		this.b_product_img_remark=b_product_img_remark;
	}
	public String getB_product_img_remark(){
		return b_product_img_remark;
	}
}
