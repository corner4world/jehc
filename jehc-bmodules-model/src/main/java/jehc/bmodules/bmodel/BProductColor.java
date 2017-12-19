package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

/**
* b_product_color 基础商品商户所选颜色 
* 2016-07-02 16:54:11  邓纯杰
*/
public class BProductColor extends BSeller implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_product_color_id;/**编号**/
	private String b_product_color_name;/**名称**/
	private Date b_product_color_ctime;/**创建时间**/
	private Date b_product_color_mtime;/**修改时间**/
	private int b_product_color_width;/**图片宽度**/
	private int b_product_color_height;/**图片高度**/
	private String xt_userinfo_id;/**创建人**/
	private int b_product_color_sort;/**排序编号**/
	private int b_product_color_status;/**状态0正常1禁用**/
	private String xt_attachment_id;/**附件编号**/
	private String b_product_id;/**商品编号**/
	private String b_product_color_remark;/**备注**/
	private String b_seller_id;/**商家编号**/
	public void setB_product_color_id(String b_product_color_id){
		this.b_product_color_id=b_product_color_id;
	}
	public String getB_product_color_id(){
		return b_product_color_id;
	}
	public void setB_product_color_name(String b_product_color_name){
		this.b_product_color_name=b_product_color_name;
	}
	public String getB_product_color_name(){
		return b_product_color_name;
	}
	public void setB_product_color_width(int b_product_color_width){
		this.b_product_color_width=b_product_color_width;
	}
	public int getB_product_color_width(){
		return b_product_color_width;
	}
	public void setB_product_color_height(int b_product_color_height){
		this.b_product_color_height=b_product_color_height;
	}
	public int getB_product_color_height(){
		return b_product_color_height;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_product_color_sort(int b_product_color_sort){
		this.b_product_color_sort=b_product_color_sort;
	}
	public int getB_product_color_sort(){
		return b_product_color_sort;
	}
	public Date getB_product_color_ctime() {
		return b_product_color_ctime;
	}
	public void setB_product_color_ctime(Date b_product_color_ctime) {
		this.b_product_color_ctime = b_product_color_ctime;
	}
	public Date getB_product_color_mtime() {
		return b_product_color_mtime;
	}
	public void setB_product_color_mtime(Date b_product_color_mtime) {
		this.b_product_color_mtime = b_product_color_mtime;
	}
	public int getB_product_color_status() {
		return b_product_color_status;
	}
	public void setB_product_color_status(int b_product_color_status) {
		this.b_product_color_status = b_product_color_status;
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
	public void setB_product_color_remark(String b_product_color_remark){
		this.b_product_color_remark=b_product_color_remark;
	}
	public String getB_product_color_remark(){
		return b_product_color_remark;
	}
	public void setB_seller_id(String b_seller_id){
		this.b_seller_id=b_seller_id;
	}
	public String getB_seller_id(){
		return b_seller_id;
	}
}
