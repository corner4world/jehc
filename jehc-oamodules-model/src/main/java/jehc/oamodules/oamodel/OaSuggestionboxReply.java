package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* oa_suggestionbox_reply 意见回复 
* 2018-07-08 10:28:44  邓纯杰
*/
public class OaSuggestionboxReply extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_suggestionbox_replyID;/**主键**/
	private String oa_suggestionbox_id;/**意见ID外键**/
	private String xt_userinfo_id;/**回复人ID外键**/
	private String oa_suggestionbox_replyContent;/**复回内容**/
	private Date createtime;/**回复时间**/
	private int isDelete;/**是否删除0正常1删除**/
	public void setOa_suggestionbox_replyID(String oa_suggestionbox_replyID){
		this.oa_suggestionbox_replyID=oa_suggestionbox_replyID;
	}
	public String getOa_suggestionbox_replyID(){
		return oa_suggestionbox_replyID;
	}
	public void setOa_suggestionbox_id(String oa_suggestionbox_id){
		this.oa_suggestionbox_id=oa_suggestionbox_id;
	}
	public String getOa_suggestionbox_id(){
		return oa_suggestionbox_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setOa_suggestionbox_replyContent(String oa_suggestionbox_replyContent){
		this.oa_suggestionbox_replyContent=oa_suggestionbox_replyContent;
	}
	public String getOa_suggestionbox_replyContent(){
		return oa_suggestionbox_replyContent;
	}
	public void setCreatetime(Date createtime){
		this.createtime=createtime;
	}
	public Date getCreatetime(){
		return createtime;
	}
	public void setIsDelete(int isDelete){
		this.isDelete=isDelete;
	}
	public int getIsDelete(){
		return isDelete;
	}
}
