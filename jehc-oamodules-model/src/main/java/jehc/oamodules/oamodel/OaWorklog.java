package jehc.oamodules.oamodel;

import java.io.Serializable;
import jehc.xtmodules.xtcore.base.BaseEntity;

public class OaWorklog extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String oa_worklogID;
  private String oa_worklogTitle;
  private String oa_worklogContent;
  private String oa_worklogCreateTime;
  private String xt_userinfo_id;

  public void setOa_worklogID(String oa_worklogID)
  {
    this.oa_worklogID = oa_worklogID;
  }
  public String getOa_worklogID() {
    return this.oa_worklogID;
  }
  public void setOa_worklogTitle(String oa_worklogTitle) {
    this.oa_worklogTitle = oa_worklogTitle;
  }
  public String getOa_worklogTitle() {
    return this.oa_worklogTitle;
  }
  public void setOa_worklogContent(String oa_worklogContent) {
    this.oa_worklogContent = oa_worklogContent;
  }
  public String getOa_worklogContent() {
    return this.oa_worklogContent;
  }
  public void setOa_worklogCreateTime(String oa_worklogCreateTime) {
    this.oa_worklogCreateTime = oa_worklogCreateTime;
  }
  public String getOa_worklogCreateTime() {
    return this.oa_worklogCreateTime;
  }
  public void setXt_userinfo_id(String xt_userinfo_id) {
    this.xt_userinfo_id = xt_userinfo_id;
  }
  public String getXt_userinfo_id() {
    return this.xt_userinfo_id;
  }
}