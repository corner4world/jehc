package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_message 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
public class Xt_Message extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_message_id;/**主键**/
	private String from_id;/**发送者编号**/
	private String to_id;/**接收者编号**/
	private String xt_meesage_content;/**送发内容**/
	private String isread;/**是否已读0未读1已读**/
	private String ctime;/**发送时间**/
	private String readtime;/**取读时间**/
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
	public void setIsread(String isread){
		this.isread=isread;
	}
	public String getIsread(){
		return isread;
	}
	public void setCtime(String ctime){
		this.ctime=ctime;
	}
	public String getCtime(){
		return ctime;
	}
	public void setReadtime(String readtime){
		this.readtime=readtime;
	}
	public String getReadtime(){
		return readtime;
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
