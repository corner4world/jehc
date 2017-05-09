package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_m_r 资源角色; InnoDB free: 6144 kB 
* 2015-08-04 16:27:46  邓纯杰
*/
public class Xt_M_R implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_m_r_id;/**资源角色主键**/
	private String xt_menuinfo_id;/**资源ID**/
	private String xt_role_id;/**角色ID**/
	public void setXt_m_r_id(String xt_m_r_id){
		this.xt_m_r_id=xt_m_r_id;
	}
	public String getXt_m_r_id(){
		return xt_m_r_id;
	}
	public void setXt_menuinfo_id(String xt_menuinfo_id){
		this.xt_menuinfo_id=xt_menuinfo_id;
	}
	public String getXt_menuinfo_id(){
		return xt_menuinfo_id;
	}
	public void setXt_role_id(String xt_role_id){
		this.xt_role_id=xt_role_id;
	}
	public String getXt_role_id(){
		return xt_role_id;
	}
}
