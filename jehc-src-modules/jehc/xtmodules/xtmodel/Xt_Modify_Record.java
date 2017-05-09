package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_modify_record 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
public class Xt_Modify_Record extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_modify_record_id;/**主键**/
	private String xt_modify_record_ctime;/**创建时间**/
	private String xt_modify_record_beforevalue;/**更变前值**/
	private String xt_modify_record_aftervalue;/**变更后值**/
	private String xt_modify_record_modules;/**模块**/
	private String xt_modify_record_field;/**字段**/
	private String xt_userinfo_id;/**创建人**/
	private String business_id;/**业务编号**/
	public void setXt_modify_record_id(String xt_modify_record_id){
		this.xt_modify_record_id=xt_modify_record_id;
	}
	public String getXt_modify_record_id(){
		return xt_modify_record_id;
	}
	public void setXt_modify_record_ctime(String xt_modify_record_ctime){
		this.xt_modify_record_ctime=xt_modify_record_ctime;
	}
	public String getXt_modify_record_ctime(){
		return xt_modify_record_ctime;
	}
	public void setXt_modify_record_beforevalue(String xt_modify_record_beforevalue){
		this.xt_modify_record_beforevalue=xt_modify_record_beforevalue;
	}
	public String getXt_modify_record_beforevalue(){
		return xt_modify_record_beforevalue;
	}
	public void setXt_modify_record_aftervalue(String xt_modify_record_aftervalue){
		this.xt_modify_record_aftervalue=xt_modify_record_aftervalue;
	}
	public String getXt_modify_record_aftervalue(){
		return xt_modify_record_aftervalue;
	}
	public void setXt_modify_record_modules(String xt_modify_record_modules){
		this.xt_modify_record_modules=xt_modify_record_modules;
	}
	public String getXt_modify_record_modules(){
		return xt_modify_record_modules;
	}
	public void setXt_modify_record_field(String xt_modify_record_field){
		this.xt_modify_record_field=xt_modify_record_field;
	}
	public String getXt_modify_record_field(){
		return xt_modify_record_field;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setBusiness_id(String business_id){
		this.business_id=business_id;
	}
	public String getBusiness_id(){
		return business_id;
	}
}
