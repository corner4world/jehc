package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_kwords 关键词（敏感词） 
* 2016-10-08 15:03:41  邓纯杰
*/
public class Xt_Kwords extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_kwords_id;/**平台关键字编号**/
	private String xt_kwords_content;/**关键字内容**/
	private String xt_kwords_ctime;/**创建时间**/
	private String xt_kwords_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String xt_kwords_status;/**状态0正常1关闭**/
	public void setXt_kwords_id(String xt_kwords_id){
		this.xt_kwords_id=xt_kwords_id;
	}
	public String getXt_kwords_id(){
		return xt_kwords_id;
	}
	public void setXt_kwords_content(String xt_kwords_content){
		this.xt_kwords_content=xt_kwords_content;
	}
	public String getXt_kwords_content(){
		return xt_kwords_content;
	}
	public void setXt_kwords_ctime(String xt_kwords_ctime){
		this.xt_kwords_ctime=xt_kwords_ctime;
	}
	public String getXt_kwords_ctime(){
		return xt_kwords_ctime;
	}
	public void setXt_kwords_mtime(String xt_kwords_mtime){
		this.xt_kwords_mtime=xt_kwords_mtime;
	}
	public String getXt_kwords_mtime(){
		return xt_kwords_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_kwords_status(String xt_kwords_status){
		this.xt_kwords_status=xt_kwords_status;
	}
	public String getXt_kwords_status(){
		return xt_kwords_status;
	}
}
