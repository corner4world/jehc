package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_generator_forbidtable 禁止使用代码生成器生成的表信息 
* 2016-09-26 10:55:48  邓纯杰
*/
public class XtGeneratorForbidtable extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_generator_forbidtable_id;/**主键编号**/
	private String xt_generator_forbidtable_table;/**表名**/
	private String xt_generator_forbidtable_ctime;/**创建时间**/
	private String xt_generator_forbidtable_mtime;/**修改时间**/
	private String xt_userinfo_id;/**用户编号**/
	private String xt_generator_forbidtable_remark;/**备注**/
	public void setXt_generator_forbidtable_id(String xt_generator_forbidtable_id){
		this.xt_generator_forbidtable_id=xt_generator_forbidtable_id;
	}
	public String getXt_generator_forbidtable_id(){
		return xt_generator_forbidtable_id;
	}
	public void setXt_generator_forbidtable_table(String xt_generator_forbidtable_table){
		this.xt_generator_forbidtable_table=xt_generator_forbidtable_table;
	}
	public String getXt_generator_forbidtable_table(){
		return xt_generator_forbidtable_table;
	}
	public void setXt_generator_forbidtable_ctime(String xt_generator_forbidtable_ctime){
		this.xt_generator_forbidtable_ctime=xt_generator_forbidtable_ctime;
	}
	public String getXt_generator_forbidtable_ctime(){
		return xt_generator_forbidtable_ctime;
	}
	public void setXt_generator_forbidtable_mtime(String xt_generator_forbidtable_mtime){
		this.xt_generator_forbidtable_mtime=xt_generator_forbidtable_mtime;
	}
	public String getXt_generator_forbidtable_mtime(){
		return xt_generator_forbidtable_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_generator_forbidtable_remark(String xt_generator_forbidtable_remark){
		this.xt_generator_forbidtable_remark=xt_generator_forbidtable_remark;
	}
	public String getXt_generator_forbidtable_remark(){
		return xt_generator_forbidtable_remark;
	}
}
