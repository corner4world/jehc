package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_u_r 用户角色分配表; InnoDB free: 6144 kB 
* 2015-08-04 16:25:07  邓纯杰
*/
public class XtUR implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_u_r_id;/**户用角色主键**/
	private String xt_userinfo_id;/**用户ID**/
	private String xt_roleinfo_id;/**角色ID**/
	private String xt_role_name;
	public String getXt_role_name() {
		return xt_role_name;
	}
	public void setXt_role_name(String xt_role_name) {
		this.xt_role_name = xt_role_name;
	}
	public void setXt_u_r_id(String xt_u_r_id){
		this.xt_u_r_id=xt_u_r_id;
	}
	public String getXt_u_r_id(){
		return xt_u_r_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_roleinfo_id(String xt_roleinfo_id){
		this.xt_roleinfo_id=xt_roleinfo_id;
	}
	public String getXt_roleinfo_id(){
		return xt_roleinfo_id;
	}
}
