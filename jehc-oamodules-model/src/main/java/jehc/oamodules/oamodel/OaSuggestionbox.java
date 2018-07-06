package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* oa_suggestionbox 意见申诉 
* 2018-07-06 20:11:52  邓纯杰
*/
public class OaSuggestionbox extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_suggestionbox_id;/****/
	private long oa_suggestionboxType;/**0签名方式1匿名**/
	private String xt_userinfo_id;/**发送人ID外键**/
	private String oa_suggestionboxTitle;/**意见标题**/
	private String oa_suggestionboxContent;/**意见内容**/
	private long oa_suggestionboxIsPub;/**是否公开0公开1否**/
	private Date createTime;/**创建时间**/
	private long isDelete;/**是否删除0正常1删除**/
	private long state;/**是否回复0未回复1已回复**/
	public void setOa_suggestionbox_id(String oa_suggestionbox_id){
		this.oa_suggestionbox_id=oa_suggestionbox_id;
	}
	public String getOa_suggestionbox_id(){
		return oa_suggestionbox_id;
	}
	public void setOa_suggestionboxType(long oa_suggestionboxType){
		this.oa_suggestionboxType=oa_suggestionboxType;
	}
	public long getOa_suggestionboxType(){
		return oa_suggestionboxType;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setOa_suggestionboxTitle(String oa_suggestionboxTitle){
		this.oa_suggestionboxTitle=oa_suggestionboxTitle;
	}
	public String getOa_suggestionboxTitle(){
		return oa_suggestionboxTitle;
	}
	public void setOa_suggestionboxContent(String oa_suggestionboxContent){
		this.oa_suggestionboxContent=oa_suggestionboxContent;
	}
	public String getOa_suggestionboxContent(){
		return oa_suggestionboxContent;
	}
	public void setOa_suggestionboxIsPub(long oa_suggestionboxIsPub){
		this.oa_suggestionboxIsPub=oa_suggestionboxIsPub;
	}
	public long getOa_suggestionboxIsPub(){
		return oa_suggestionboxIsPub;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setIsDelete(long isDelete){
		this.isDelete=isDelete;
	}
	public long getIsDelete(){
		return isDelete;
	}
	public void setState(long state){
		this.state=state;
	}
	public long getState(){
		return state;
	}
}
