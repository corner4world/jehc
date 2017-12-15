package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_brand 基础-品牌 
* 2016-01-05 12:46:23  邓纯杰
*/
public class BBrand extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_brand_id;/**品牌编号**/
	private String b_brand_name;/**品牌名称**/
	private String b_brand_status;/**状态0可用1禁用**/
	private Date b_brand_ctime;/**创建时间**/
	private Date b_brand_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String b_brand_type;/**类型0国内1国外**/
	private int b_brand_soft;/**排序**/
	public void setB_brand_id(String b_brand_id){
		this.b_brand_id=b_brand_id;
	}
	public String getB_brand_id(){
		return b_brand_id;
	}
	public void setB_brand_name(String b_brand_name){
		this.b_brand_name=b_brand_name;
	}
	public String getB_brand_name(){
		return b_brand_name;
	}
	public void setB_brand_status(String b_brand_status){
		this.b_brand_status=b_brand_status;
	}
	public String getB_brand_status(){
		return b_brand_status;
	}
	public Date getB_brand_ctime() {
		return b_brand_ctime;
	}
	public void setB_brand_ctime(Date b_brand_ctime) {
		this.b_brand_ctime = b_brand_ctime;
	}
	public Date getB_brand_mtime() {
		return b_brand_mtime;
	}
	public void setB_brand_mtime(Date b_brand_mtime) {
		this.b_brand_mtime = b_brand_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setB_brand_type(String b_brand_type){
		this.b_brand_type=b_brand_type;
	}
	public String getB_brand_type(){
		return b_brand_type;
	}
	public int getB_brand_soft() {
		return b_brand_soft;
	}
	public void setB_brand_soft(int b_brand_soft) {
		this.b_brand_soft = b_brand_soft;
	}
}
