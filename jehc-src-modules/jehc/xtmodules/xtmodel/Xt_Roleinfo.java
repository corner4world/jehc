package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_roleinfo 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
public class Xt_Roleinfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_role_id;/**角色权限id**/
	private String xt_role_name;/**角色权限名称**/
	private String xt_role_desc;/**角色权限描述**/
	private int xt_role_type;/**0平台权限1业务权限**/
	private String xt_role_createTime;/**创建时间**/
	private String xt_role_updateTime;/**修改时间**/
	private int xt_role_isdelete;/**0未删除 1已删除**/
	public void setXt_role_id(String xt_role_id){
		this.xt_role_id=xt_role_id;
	}
	public String getXt_role_id(){
		return xt_role_id;
	}
	public void setXt_role_name(String xt_role_name){
		this.xt_role_name=xt_role_name;
	}
	public String getXt_role_name(){
		return xt_role_name;
	}
	public void setXt_role_desc(String xt_role_desc){
		this.xt_role_desc=xt_role_desc;
	}
	public String getXt_role_desc(){
		return xt_role_desc;
	}
	public void setXt_role_type(int xt_role_type){
		this.xt_role_type=xt_role_type;
	}
	public int getXt_role_type(){
		return xt_role_type;
	}
	public void setXt_role_createTime(String xt_role_createTime){
		this.xt_role_createTime=xt_role_createTime;
	}
	public String getXt_role_createTime(){
		return xt_role_createTime;
	}
	public void setXt_role_updateTime(String xt_role_updateTime){
		this.xt_role_updateTime=xt_role_updateTime;
	}
	public String getXt_role_updateTime(){
		return xt_role_updateTime;
	}
	public void setXt_role_isdelete(int xt_role_isdelete){
		this.xt_role_isdelete=xt_role_isdelete;
	}
	public int getXt_role_isdelete(){
		return xt_role_isdelete;
	}
}
