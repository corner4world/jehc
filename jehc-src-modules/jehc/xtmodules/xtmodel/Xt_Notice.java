package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_notice 平台公告 
* 2016-06-18 15:45:40  邓纯杰
*/
public class Xt_Notice extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_notice_id;/**主键**/
	@NotEmpty(message = "标题不能为空")
	@NotNull(message = "标题不能为空")
	private String xt_title;/**标题**/
	@Size(min=0 ,max= 200 ,message = "内容长度不符合标准")
	private String xt_content;/**内容**/
	private String xt_userinfo_id;/**创建人**/
	private String xt_isDel;/**标识0正常1删除**/
	@NotEmpty(message = "状态不能为空")
	private String xt_state;/**状态0初稿1审核中2审核通过3审核未通过**/
	private String xt_createTime;/**创建时间**/
	private String xt_attachment_id;/**附件编号**/
	private String xt_attachment_id_;/**其他附件**/
	public void setXt_notice_id(String xt_notice_id){
		this.xt_notice_id=xt_notice_id;
	}
	public String getXt_notice_id(){
		return xt_notice_id;
	}
	public void setXt_title(String xt_title){
		this.xt_title=xt_title;
	}
	public String getXt_title(){
		return xt_title;
	}
	public void setXt_content(String xt_content){
		this.xt_content=xt_content;
	}
	public String getXt_content(){
		return xt_content;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_isDel(String xt_isDel){
		this.xt_isDel=xt_isDel;
	}
	public String getXt_isDel(){
		return xt_isDel;
	}
	public void setXt_state(String xt_state){
		this.xt_state=xt_state;
	}
	public String getXt_state(){
		return xt_state;
	}
	public void setXt_createTime(String xt_createTime){
		this.xt_createTime=xt_createTime;
	}
	public String getXt_createTime(){
		return xt_createTime;
	}
	public void setXt_attachment_id(String xt_attachment_id){
		this.xt_attachment_id=xt_attachment_id;
	}
	public String getXt_attachment_id(){
		return xt_attachment_id;
	}
	public void setXt_attachment_id_(String xt_attachment_id_){
		this.xt_attachment_id_=xt_attachment_id_;
	}
	public String getXt_attachment_id_(){
		return xt_attachment_id_;
	}
}
