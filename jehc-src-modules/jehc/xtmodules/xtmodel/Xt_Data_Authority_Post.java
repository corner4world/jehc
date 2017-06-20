package jehc.xtmodules.xtmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;

/**
* xt_data_authority_post 数据权限按岗位设置 
* 2017-06-20 14:37:16  邓纯杰
*/
public class Xt_Data_Authority_Post extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_data_authority_post_id;/****/
	private String xtPID;/**拥有岗位ID**/
	private String xt_post_id;/**操作岗位ID**/
	private String xt_functioninfo_id;/**功能ID逗号分割**/
	private String xt_menuinfo_id;/**模块ID**/
	public void setXt_data_authority_post_id(String xt_data_authority_post_id){
		this.xt_data_authority_post_id=xt_data_authority_post_id;
	}
	public String getXt_data_authority_post_id(){
		return xt_data_authority_post_id;
	}
	public void setXtPID(String xtPID){
		this.xtPID=xtPID;
	}
	public String getXtPID(){
		return xtPID;
	}
	public void setXt_post_id(String xt_post_id){
		this.xt_post_id=xt_post_id;
	}
	public String getXt_post_id(){
		return xt_post_id;
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
