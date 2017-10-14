package jehc.oamodules.oamodel;

import java.io.Serializable;
import jehc.xtmodules.xtcore.base.BaseEntity;

public class OaNotice extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String oa_noticeID;
  private String xt_userinfo_id;
  private String oa_noticeTitle;
  private String oa_noticeContent;
  private String oa_noticeCreateTime;
  private long oa_noticeIsDelete;
  private long oa_noticeType;
  private String oa_notice_status;

  public void setOa_noticeID(String oa_noticeID)
  {
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
  public void setOa_noticeIsDelete(long oa_noticeIsDelete) {
    this.oa_noticeIsDelete = oa_noticeIsDelete;
  }
  public long getOa_noticeIsDelete() {
    return this.oa_noticeIsDelete;
  }
  public void setOa_noticeType(long oa_noticeType) {
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
}