package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
* oa_notice 公告 
* 2017-11-16 13:23:07  邓纯杰
*/
public class OaNotice extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_noticeID;/**公告ID主键**/
	private String xt_userinfo_id;/**创建人ID外键**/
	private String oa_noticeTitle;/**公告题标**/
	private String oa_noticeContent;/**告公内容**/
	private Date oa_noticeCreateTime;/**建创时间**/
	private int oa_noticeIsDelete;/**否是删除0正常1删除**/
	private String oa_noticeType;/**类型1一般2重要3非常重要**/
	private String oa_notice_status;/**审核状态（执行的每一步）**/
	private String status;/**状态0草稿1审核中2审核通过3审核未通过**/
	private String xt_attachement_id;/**附件**/
	private int oa_notice_hits;/**点击量**/
	private String submitType;/**提交方式0草稿1提交**/
	public void setOa_noticeID(String oa_noticeID){
		this.oa_noticeID=oa_noticeID;
	}
	public String getOa_noticeID(){
		return oa_noticeID;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setOa_noticeTitle(String oa_noticeTitle){
		this.oa_noticeTitle=oa_noticeTitle;
	}
	public String getOa_noticeTitle(){
		return oa_noticeTitle;
	}
	public void setOa_noticeContent(String oa_noticeContent){
		this.oa_noticeContent=oa_noticeContent;
	}
	public String getOa_noticeContent(){
		return oa_noticeContent;
	}
	public Date getOa_noticeCreateTime() {
		return oa_noticeCreateTime;
	}
	public void setOa_noticeCreateTime(Date oa_noticeCreateTime) {
		this.oa_noticeCreateTime = oa_noticeCreateTime;
	}
	public void setOa_noticeIsDelete(int oa_noticeIsDelete){
		this.oa_noticeIsDelete=oa_noticeIsDelete;
	}
	public int getOa_noticeIsDelete(){
		return oa_noticeIsDelete;
	}
	public void setOa_noticeType(String oa_noticeType){
		this.oa_noticeType=oa_noticeType;
	}
	public String getOa_noticeType(){
		return oa_noticeType;
	}
	public void setOa_notice_status(String oa_notice_status){
		this.oa_notice_status=oa_notice_status;
	}
	public String getOa_notice_status(){
		return oa_notice_status;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setXt_attachement_id(String xt_attachement_id){
		this.xt_attachement_id=xt_attachement_id;
	}
	public String getXt_attachement_id(){
		return xt_attachement_id;
	}
	public void setOa_notice_hits(int oa_notice_hits){
		this.oa_notice_hits=oa_notice_hits;
	}
	public int getOa_notice_hits(){
		return oa_notice_hits;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	
}
