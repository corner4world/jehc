package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtmodel.XtAttachment;

/**
* b_recommend 基础推荐 
* 2016-01-10 11:24:05  邓纯杰
*/
public class BRecommend extends XtAttachment implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_recommend_id;/**推荐编号**/
	private String b_recommend_title;/**推荐标题**/
	private String b_recommend_url;/**链接地址**/
	private int b_recommend_sort;/**排序**/
	private String b_recommend_remark;/**备注**/
	private String xt_userinfo_id;/**创建人**/
	private int b_recommend_width;/**宽度**/
	private int b_recommend_height;/**高度**/
	private Date b_recommend_ctime;/**创建时间**/
	private Date b_recommend_mtime;/**修改时间**/
	private int b_recommend_status;/**状态0正常1禁用**/
	private String xt_attachment_id;/**附件编号**/
	public void setB_recommend_id(String b_recommend_id){
		this.b_recommend_id=b_recommend_id;
	}
	public String getB_recommend_id(){
		return b_recommend_id;
	}
	public void setB_recommend_title(String b_recommend_title){
		this.b_recommend_title=b_recommend_title;
	}
	public String getB_recommend_title(){
		return b_recommend_title;
	}
	public void setB_recommend_url(String b_recommend_url){
		this.b_recommend_url=b_recommend_url;
	}
	public String getB_recommend_url(){
		return b_recommend_url;
	}
	public void setB_recommend_sort(int b_recommend_sort){
		this.b_recommend_sort=b_recommend_sort;
	}
	public int getB_recommend_sort(){
		return b_recommend_sort;
	}
	public void setB_recommend_remark(String b_recommend_remark){
		this.b_recommend_remark=b_recommend_remark;
	}
	public String getB_recommend_remark(){
		return b_recommend_remark;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_recommend_width(int b_recommend_width){
		this.b_recommend_width=b_recommend_width;
	}
	public int getB_recommend_width(){
		return b_recommend_width;
	}
	public void setB_recommend_height(int b_recommend_height){
		this.b_recommend_height=b_recommend_height;
	}
	public int getB_recommend_height(){
		return b_recommend_height;
	}
	
	public Date getB_recommend_ctime() {
		return b_recommend_ctime;
	}
	public void setB_recommend_ctime(Date b_recommend_ctime) {
		this.b_recommend_ctime = b_recommend_ctime;
	}
	public Date getB_recommend_mtime() {
		return b_recommend_mtime;
	}
	public void setB_recommend_mtime(Date b_recommend_mtime) {
		this.b_recommend_mtime = b_recommend_mtime;
	}
	public int getB_recommend_status() {
		return b_recommend_status;
	}
	public void setB_recommend_status(int b_recommend_status) {
		this.b_recommend_status = b_recommend_status;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
}
