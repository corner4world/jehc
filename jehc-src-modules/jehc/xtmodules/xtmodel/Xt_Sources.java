package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_sources 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
public class Xt_Sources extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_sources_id;/**静态资源编号**/
	private String xt_sources_title;/**标题**/
	private String xt_sources_remark;/**描述**/
	private String xt_attachment_id;/**附件编号**/
	private String xt_sources_type;/**类型1css2img3js4doc5xls6csv7zip8rar9jar**/
	private String xt_sources_status;/**状态0正常1禁用**/
	private String xt_userinfo_id;/**操作人**/
	private String xt_sources_ctime;/**创建时间**/
	private String xt_sources_mtime;/**修改时间**/
	public void setXt_sources_id(String xt_sources_id){
		this.xt_sources_id=xt_sources_id;
	}
	public String getXt_sources_id(){
		return xt_sources_id;
	}
	public void setXt_sources_title(String xt_sources_title){
		this.xt_sources_title=xt_sources_title;
	}
	public String getXt_sources_title(){
		return xt_sources_title;
	}
	public void setXt_sources_remark(String xt_sources_remark){
		this.xt_sources_remark=xt_sources_remark;
	}
	public String getXt_sources_remark(){
		return xt_sources_remark;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
	public void setXt_sources_type(String xt_sources_type){
		this.xt_sources_type=xt_sources_type;
	}
	public String getXt_sources_type(){
		return xt_sources_type;
	}
	public void setXt_sources_status(String xt_sources_status){
		this.xt_sources_status=xt_sources_status;
	}
	public String getXt_sources_status(){
		return xt_sources_status;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_sources_ctime(String xt_sources_ctime){
		this.xt_sources_ctime=xt_sources_ctime;
	}
	public String getXt_sources_ctime(){
		return xt_sources_ctime;
	}
	public void setXt_sources_mtime(String xt_sources_mtime){
		this.xt_sources_mtime=xt_sources_mtime;
	}
	public String getXt_sources_mtime(){
		return xt_sources_mtime;
	}
}
