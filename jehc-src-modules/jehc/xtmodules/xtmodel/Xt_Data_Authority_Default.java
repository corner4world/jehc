package jehc.xtmodules.xtmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;

/**
* xt_data_authority_default 数权限初始化默认设置 
* 2017-06-20 14:38:52  邓纯杰
*/
public class Xt_Data_Authority_Default extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_data_authority_default_id;/**据数权限初始化默认设置ID自动增长列**/
	private String xt_data_authority_default_value;/**固定值1按人员默认初始化基本数据权限2按部门默认初始化基本数据权限3按岗位默认初始化基本数据权限4按部门启动上下级基本数据权限5按岗位启动上下级基本数据权限**/
	private String xt_menuinfo_id;/**块模ID编号**/
	private String xt_functioninfo_id;/**功能ID逗号分割**/
	public void setXt_data_authority_default_id(String xt_data_authority_default_id){
		this.xt_data_authority_default_id=xt_data_authority_default_id;
	}
	public String getXt_data_authority_default_id(){
		return xt_data_authority_default_id;
	}
	public void setXt_data_authority_default_value(String xt_data_authority_default_value){
		this.xt_data_authority_default_value=xt_data_authority_default_value;
	}
	public String getXt_data_authority_default_value(){
		return xt_data_authority_default_value;
	}
	public void setXt_menuinfo_id(String xt_menuinfo_id){
		this.xt_menuinfo_id=xt_menuinfo_id;
	}
	public String getXt_menuinfo_id(){
		return xt_menuinfo_id;
	}
	public void setXt_functioninfo_id(String xt_functioninfo_id){
		this.xt_functioninfo_id=xt_functioninfo_id;
	}
	public String getXt_functioninfo_id(){
		return xt_functioninfo_id;
	}
}
