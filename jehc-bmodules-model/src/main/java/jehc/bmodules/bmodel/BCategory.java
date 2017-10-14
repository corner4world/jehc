package jehc.bmodules.bmodel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* b_category 基础-品类 
* 2016-01-04 21:24:03  邓纯杰
*/
public class BCategory extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String b_category_id;/**分类编号**/
	private String b_category_name;/**分类名称**/
	private String b_category_pid;/**分类父级编号**/
	private String b_category_path;/**分类深度**/
	private String b_category_ctime;/**创建时间**/
	private String b_category_mtime;/**修改时间**/
	private String b_category_status;/**0可用1禁用**/
	private String xt_userinfo_id;/**创建人**/
	private List<BCategory> bcategorys = new ArrayList<BCategory>();
	
	public List<BCategory> getBcategorys() {
		return bcategorys;
	}
	public void setBcategorys(List<BCategory> bcategorys) {
		this.bcategorys = bcategorys;
	}
	public void setB_category_id(String b_category_id){
		this.b_category_id=b_category_id;
	}
	public String getB_category_id(){
		return b_category_id;
	}
	public void setB_category_name(String b_category_name){
		this.b_category_name=b_category_name;
	}
	public String getB_category_name(){
		return b_category_name;
	}
	public void setB_category_pid(String b_category_pid){
		this.b_category_pid=b_category_pid;
	}
	public String getB_category_pid(){
		return b_category_pid;
	}
	public void setB_category_path(String b_category_path){
		this.b_category_path=b_category_path;
	}
	public String getB_category_path(){
		return b_category_path;
	}
	public void setB_category_ctime(String b_category_ctime){
		this.b_category_ctime=b_category_ctime;
	}
	public String getB_category_ctime(){
		return b_category_ctime;
	}
	public void setB_category_mtime(String b_category_mtime){
		this.b_category_mtime=b_category_mtime;
	}
	public String getB_category_mtime(){
		return b_category_mtime;
	}
	public void setB_category_status(String b_category_status){
		this.b_category_status=b_category_status;
	}
	public String getB_category_status(){
		return b_category_status;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
