package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_functioninfo_common 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
public class XtFunctioninfoCommon extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_functioninfo_common_id;/**公共功能编号**/
	private String xt_functioninfo_common_title;/**标题**/
	private String xt_functioninfo_common_url;/**功能地址**/
	private String xt_functioninfo_common_method;/**方法名称**/
	private String xt_functioninfo_common_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String xt_functioninfo_common_status;/**状态0启用1禁用**/
	private String xt_functioninfo_common_content;/**备注**/
	private String xt_functioninfo_common_ctime;/**创建时间**/
	public void setXt_functioninfo_common_id(String xt_functioninfo_common_id){
		this.xt_functioninfo_common_id=xt_functioninfo_common_id;
	}
	public String getXt_functioninfo_common_id(){
		return xt_functioninfo_common_id;
	}
	public void setXt_functioninfo_common_title(String xt_functioninfo_common_title){
		this.xt_functioninfo_common_title=xt_functioninfo_common_title;
	}
	public String getXt_functioninfo_common_title(){
		return xt_functioninfo_common_title;
	}
	public void setXt_functioninfo_common_url(String xt_functioninfo_common_url){
		this.xt_functioninfo_common_url=xt_functioninfo_common_url;
	}
	public String getXt_functioninfo_common_url(){
		return xt_functioninfo_common_url;
	}
	public void setXt_functioninfo_common_method(String xt_functioninfo_common_method){
		this.xt_functioninfo_common_method=xt_functioninfo_common_method;
	}
	public String getXt_functioninfo_common_method(){
		return xt_functioninfo_common_method;
	}
	public void setXt_functioninfo_common_mtime(String xt_functioninfo_common_mtime){
		this.xt_functioninfo_common_mtime=xt_functioninfo_common_mtime;
	}
	public String getXt_functioninfo_common_mtime(){
		return xt_functioninfo_common_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_functioninfo_common_status(String xt_functioninfo_common_status){
		this.xt_functioninfo_common_status=xt_functioninfo_common_status;
	}
	public String getXt_functioninfo_common_status(){
		return xt_functioninfo_common_status;
	}
	public void setXt_functioninfo_common_content(String xt_functioninfo_common_content){
		this.xt_functioninfo_common_content=xt_functioninfo_common_content;
	}
	public String getXt_functioninfo_common_content(){
		return xt_functioninfo_common_content;
	}
	public void setXt_functioninfo_common_ctime(String xt_functioninfo_common_ctime){
		this.xt_functioninfo_common_ctime=xt_functioninfo_common_ctime;
	}
	public String getXt_functioninfo_common_ctime(){
		return xt_functioninfo_common_ctime;
	}
}
