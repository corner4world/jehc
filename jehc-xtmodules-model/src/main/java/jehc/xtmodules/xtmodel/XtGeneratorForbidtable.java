package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_generator_forbidtable 禁止使用代码生成器生成的表信息 
* 2016-09-26 10:55:48  邓纯杰
*/
public class XtGeneratorForbidtable extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_generator_f_id;/**主键编号**/
	private String xt_generator_f_table;/**表名**/
	private String xt_generator_f_ctime;/**创建时间**/
	private String xt_generator_f_mtime;/**修改时间**/
	private String xt_userinfo_id;/**用户编号**/
	private String xt_generator_f_remark;/**备注**/
	
	public String getXt_generator_f_ctime() {
		return xt_generator_f_ctime;
	}
	public String getXt_generator_f_id() {
		return xt_generator_f_id;
	}
	public void setXt_generator_f_id(String xt_generator_f_id) {
		this.xt_generator_f_id = xt_generator_f_id;
	}
	public void setXt_generator_f_ctime(String xt_generator_f_ctime) {
		this.xt_generator_f_ctime = xt_generator_f_ctime;
	}
	public String getXt_generator_f_mtime() {
		return xt_generator_f_mtime;
	}
	public void setXt_generator_f_mtime(String xt_generator_f_mtime) {
		this.xt_generator_f_mtime = xt_generator_f_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public String getXt_generator_f_table() {
		return xt_generator_f_table;
	}
	public void setXt_generator_f_table(String xt_generator_f_table) {
		this.xt_generator_f_table = xt_generator_f_table;
	}
	public String getXt_generator_f_remark() {
		return xt_generator_f_remark;
	}
	public void setXt_generator_f_remark(String xt_generator_f_remark) {
		this.xt_generator_f_remark = xt_generator_f_remark;
	}
	
}
