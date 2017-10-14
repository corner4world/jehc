package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_platform_feedback 平台反馈意见 
* 2016-08-30 22:22:10  邓纯杰
*/
public class XtPlatformFeedback extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_platform_feedback_id;/**主键编号**/
	private String xt_platform_id;/**台平发布信息编号**/
	private String xt_platform_feedback_ctime;/**创建时间**/
	private String xt_userinfo_id;/**评论人编号**/
	private String xt_platform_feedback_remark;/**评论内容**/
	private String xt_platform_feedback_status;/**状态0正常1隐藏**/
	public void setXt_platform_feedback_id(String xt_platform_feedback_id){
		this.xt_platform_feedback_id=xt_platform_feedback_id;
	}
	public String getXt_platform_feedback_id(){
		return xt_platform_feedback_id;
	}
	public void setXt_platform_id(String xt_platform_id){
		this.xt_platform_id=xt_platform_id;
	}
	public String getXt_platform_id(){
		return xt_platform_id;
	}
	public void setXt_platform_feedback_ctime(String xt_platform_feedback_ctime){
		this.xt_platform_feedback_ctime=xt_platform_feedback_ctime;
	}
	public String getXt_platform_feedback_ctime(){
		return xt_platform_feedback_ctime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_platform_feedback_remark(String xt_platform_feedback_remark){
		this.xt_platform_feedback_remark=xt_platform_feedback_remark;
	}
	public String getXt_platform_feedback_remark(){
		return xt_platform_feedback_remark;
	}
	public void setXt_platform_feedback_status(String xt_platform_feedback_status){
		this.xt_platform_feedback_status=xt_platform_feedback_status;
	}
	public String getXt_platform_feedback_status(){
		return xt_platform_feedback_status;
	}
}
