package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_data_authority 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
public class Xt_Data_Authority extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_data_authority_id;/**ID**/
	private String xtUID;/**被拥有人ID**/
	private String xt_userinfo_id;/**操作人ID（拥有者）**/
	private String xt_functioninfo_id;/**功能ID**/
	private String xt_menuinfo_id;/**菜单ID**/
	private String xt_data_authorityType;/**类型（1表示按人员进行设置【即系统默认权限】2表示按部门设置3表示按岗位设置4按人员默认初始化5按部门默认初始化6按岗位默认初始化7按部门上下级8按岗位上下级）**/
	public void setXt_data_authority_id(String xt_data_authority_id){
		this.xt_data_authority_id=xt_data_authority_id;
	}
	public String getXt_data_authority_id(){
		return xt_data_authority_id;
	}
	public void setXtUID(String xtUID){
		this.xtUID=xtUID;
	}
	public String getXtUID(){
		return xtUID;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_functioninfo_id(String xt_functioninfo_id){
		this.xt_functioninfo_id=xt_functioninfo_id;
	}
	public String getXt_functioninfo_id(){
		return xt_functioninfo_id;
	}
	public void setXt_menuinfo_id(String xt_menuinfo_id){
		this.xt_menuinfo_id=xt_menuinfo_id;
	}
	public String getXt_menuinfo_id(){
		return xt_menuinfo_id;
	}
	public void setXt_data_authorityType(String xt_data_authorityType){
		this.xt_data_authorityType=xt_data_authorityType;
	}
	public String getXt_data_authorityType(){
		return xt_data_authorityType;
	}
}
