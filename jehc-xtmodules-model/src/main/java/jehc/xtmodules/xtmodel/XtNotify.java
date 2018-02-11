package jehc.xtmodules.xtmodel;

import java.util.Date;
import java.util.List;

import jehc.xtmodules.xtcore.base.BaseEntity;
/**
 * 通知
 * @author 邓纯杰
 *
 */
public class XtNotify  extends BaseEntity{
	private static final long serialVersionUID = -359952928476588641L;
	
	private String xt_notify_id;/**通知编号**/
	private String xt_notify_title;/**通知标题**/
	private String xt_notify_content;/**通知内容**/
	private Date xt_notify_ctime;/**创建时间**/
	private String xt_userinfo_id;/**创建人编号**/
	private String xt_userinfo_realName;/**创建人名称**/
	private int isDelete;/**是否删除0正常1已删除**/
	private int xt_notify_type;/**通知类型0默认1平台通知(系统自动通知）**/
	private List<XtNotifyReceiver> xtNotifyReceiverList;
	public String getXt_notify_id() {
		return xt_notify_id;
	}
	public void setXt_notify_id(String xt_notify_id) {
		this.xt_notify_id = xt_notify_id;
	}
	public String getXt_notify_title() {
		return xt_notify_title;
	}
	public void setXt_notify_title(String xt_notify_title) {
		this.xt_notify_title = xt_notify_title;
	}
	public String getXt_notify_content() {
		return xt_notify_content;
	}
	public void setXt_notify_content(String xt_notify_content) {
		this.xt_notify_content = xt_notify_content;
	}
	public Date getXt_notify_ctime() {
		return xt_notify_ctime;
	}
	public void setXt_notify_ctime(Date xt_notify_ctime) {
		this.xt_notify_ctime = xt_notify_ctime;
	}
	public String getXt_userinfo_id() {
		return xt_userinfo_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id) {
		this.xt_userinfo_id = xt_userinfo_id;
	}
	public String getXt_userinfo_realName() {
		return xt_userinfo_realName;
	}
	public void setXt_userinfo_realName(String xt_userinfo_realName) {
		this.xt_userinfo_realName = xt_userinfo_realName;
	}
	public List<XtNotifyReceiver> getXtNotifyReceiverList() {
		return xtNotifyReceiverList;
	}
	public void setXtNotifyReceiverList(List<XtNotifyReceiver> xtNotifyReceiverList) {
		this.xtNotifyReceiverList = xtNotifyReceiverList;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getXt_notify_type() {
		return xt_notify_type;
	}
	public void setXt_notify_type(int xt_notify_type) {
		this.xt_notify_type = xt_notify_type;
	}
	
}
