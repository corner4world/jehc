package jehc.xtmodules.xtmodel;

import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;
/**
 * 通知接收人
 * @author邓纯杰
 *
 */
public class XtNotifyReceiver extends BaseEntity{

	private static final long serialVersionUID = 6495284093904411707L;
	
	private String xt_notify_receiver_id;/**id**/
	private Date receive_time;/**接收时间**/ 
	private String xt_userinfo_id;/**接收人编号**/
	private String xt_userinfo_realName;/**接收人名称**/
	private String xt_notify_id;/**通知编号外键**/
	private int isDelete;/**是否删除0正常 1已删除**/
	private Date read_time;/**已读时间**/
	private int receive_status;/**状态0未读1已读**/
	
	
	//通知表信息////
	private String sendUserRealName;/**发送人名称**/
	private String xt_notify_title;
	private String xt_notify_content;
	public String getXt_notify_receiver_id() {
		return xt_notify_receiver_id;
	}
	public void setXt_notify_receiver_id(String xt_notify_receiver_id) {
		this.xt_notify_receiver_id = xt_notify_receiver_id;
	}
	public Date getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
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
	public String getXt_notify_id() {
		return xt_notify_id;
	}
	public void setXt_notify_id(String xt_notify_id) {
		this.xt_notify_id = xt_notify_id;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getRead_time() {
		return read_time;
	}
	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}
	public int getReceive_status() {
		return receive_status;
	}
	public void setReceive_status(int receive_status) {
		this.receive_status = receive_status;
	}
	public String getSendUserRealName() {
		return sendUserRealName;
	}
	public void setSendUserRealName(String sendUserRealName) {
		this.sendUserRealName = sendUserRealName;
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
	
}
