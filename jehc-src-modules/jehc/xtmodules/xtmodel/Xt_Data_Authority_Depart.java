package jehc.xtmodules.xtmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;

/**
* xt_data_authority_depart 数据权限按部门设置 
* 2017-06-20 14:36:19  邓纯杰
*/
public class Xt_Data_Authority_Depart extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_data_authority_depart_id;/**数据权限按部门设置**/
	private String xtDID;/**拥有部门ID**/
	private String xt_departinfo_id;/**操作部门ID**/
	private String xt_functioninfo_id;/**功能ID逗号分割**/
	private String xt_menuinfo_id;/**模块ID**/
	public void setXt_data_authority_depart_id(String xt_data_authority_depart_id){
		this.xt_data_authority_depart_id=xt_data_authority_depart_id;
	}
	public String getXt_data_authority_depart_id(){
		return xt_data_authority_depart_id;
	}
	public void setXtDID(String xtDID){
		this.xtDID=xtDID;
	}
	public String getXtDID(){
		return xtDID;
	}
	public void setXt_departinfo_id(String xt_departinfo_id){
		this.xt_departinfo_id=xt_departinfo_id;
	}
	public String getXt_departinfo_id(){
		return xt_departinfo_id;
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
}
