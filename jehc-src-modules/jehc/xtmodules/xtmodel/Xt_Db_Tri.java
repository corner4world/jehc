package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 触发器
 * @author邓纯杰
 *
 */
public class Xt_Db_Tri  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String trigger;
	private String event;
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
