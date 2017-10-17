package jehc.oamodules.oamodel;

import java.io.Serializable;
import jehc.xtmodules.xtcore.base.BaseEntity;

/**
 * 公告
 * @author邓纯杰
 *
 */
public class OaNotice extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String oa_noticeID;/**编号**/
	private String xt_userinfo_id;/**创建人编号**/
	private String oa_noticeTitle;/**标题**/
	private String oa_noticeContent;/**公告内容**/
	private String oa_noticeCreateTime;/**创建时间**/
	private int oa_noticeIsDelete;/**是否删除0正常1 删除**/
	private int oa_noticeType;/**类型1一般2重要3非常重要**/
	private String oa_notice_status;/**审核状态（执行的每一步）**/
	private String submitType;/** 提交类型0新增 1保存并提交 **/
	private String status;/**状态0草稿1审核中2审核通过3审核未通过**/

	public void setOa_noticeID(String oa_noticeID) {
		this.oa_noticeID = oa_noticeID;
	}

	public String getOa_noticeID() {
		return this.oa_noticeID;
	}

	public void setXt_userinfo_id(String xt_userinfo_id) {
		this.xt_userinfo_id = xt_userinfo_id;
	}

	public String getXt_userinfo_id() {
		return this.xt_userinfo_id;
	}

	public void setOa_noticeTitle(String oa_noticeTitle) {
		this.oa_noticeTitle = oa_noticeTitle;
	}

	public String getOa_noticeTitle() {
		return this.oa_noticeTitle;
	}

	public void setOa_noticeContent(String oa_noticeContent) {
		this.oa_noticeContent = oa_noticeContent;
	}

	public String getOa_noticeContent() {
		return this.oa_noticeContent;
	}

	public void setOa_noticeCreateTime(String oa_noticeCreateTime) {
		this.oa_noticeCreateTime = oa_noticeCreateTime;
	}

	public String getOa_noticeCreateTime() {
		return this.oa_noticeCreateTime;
	}

	public int getOa_noticeIsDelete() {
		return oa_noticeIsDelete;
	}

	public void setOa_noticeIsDelete(int oa_noticeIsDelete) {
		this.oa_noticeIsDelete = oa_noticeIsDelete;
	}

	public void setOa_noticeType(int oa_noticeType) {
		this.oa_noticeType = oa_noticeType;
	}

	public long getOa_noticeType() {
		return this.oa_noticeType;
	}

	public String getOa_notice_status() {
		return this.oa_notice_status;
	}

	public void setOa_notice_status(String oa_notice_status) {
		this.oa_notice_status = oa_notice_status;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}