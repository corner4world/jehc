package jehc.crmmodules.crmmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* crm_followup 客户跟进日志 
* 2018-06-27 16:55:12  邓纯杰
*/
public class CrmFollowup extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String followupId;/****/
	private String content;/**内容**/
	private String xt_userinfo_id;/**跟进人**/
	private Date ctime;/**填写日志时间**/
	private Date followupTime;/**跟进日期**/
	private String customerId;/**客户编号**/
	public void setFollowupId(String followupId){
		this.followupId=followupId;
	}
	public String getFollowupId(){
		return followupId;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	public Date getCtime(){
		return ctime;
	}
	public void setFollowupTime(Date followupTime){
		this.followupTime=followupTime;
	}
	public Date getFollowupTime(){
		return followupTime;
	}
	public void setCustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getCustomerId(){
		return customerId;
	}
}
