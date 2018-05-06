package jehc.xtmodules.xtmodel;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_message 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
public class XtMessage extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_message_id;/**主键**/
	private String from_id;/**发送者编号**/
	@NotEmpty(message = "接收者不能为空")
	@NotNull(message = "接收者不能为空")
	private String to_id;/**接收者编号**/
	@Size(min=1 ,max= 500 ,message = "聊天内容字数必须在1-500之内")
	private String xt_meesage_content;/**送发内容**/
	private int isread;/**是否已读0未读1已读**/
	private Date ctime;/**发送时间**/
	private Date readtime;/**取读时间**/
	private String fromName;/**发送者**/
	private String toName;/**接收者**/
	private int count;/**统计个数**/
	public void setXt_message_id(String xt_message_id){
		this.xt_message_id=xt_message_id;
	}
	public String getXt_message_id(){
		return xt_message_id;
	}
	public void setFrom_id(String from_id){
		this.from_id=from_id;
	}
	public String getFrom_id(){
		return from_id;
	}
	public void setTo_id(String to_id){
		this.to_id=to_id;
	}
	public String getTo_id(){
		return to_id;
	}
	public void setXt_meesage_content(String xt_meesage_content){
		this.xt_meesage_content=xt_meesage_content;
	}
	public String getXt_meesage_content(){
		return xt_meesage_content;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getReadtime() {
		return readtime;
	}
	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
