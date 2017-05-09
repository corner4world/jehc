package jehc.xtmodules.xtcore.mq.rabbit.common;
/**
 * 消息实体
 * @author 邓纯杰
 */
public class RabbitMsg {
	private int status =0;
	private String content=null;
	public String getStatus() {
		return String.valueOf(status);
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
