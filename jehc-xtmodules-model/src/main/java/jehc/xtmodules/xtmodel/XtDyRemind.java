package jehc.xtmodules.xtmodel;

import java.util.List;

/**
 * 全局动态提醒
 * @author 邓纯杰
 *
 */
public class XtDyRemind extends XtUserinfo{

	private static final long serialVersionUID = -8760313876335891332L;
	
	private List<XtNotifyReceiver> xtNotifyReceiverList;/**我的通知**/
	
	private List<XtMessage> xtMessageList;/**我的消息**/

	public List<XtNotifyReceiver> getXtNotifyReceiverList() {
		return xtNotifyReceiverList;
	}

	public void setXtNotifyReceiverList(List<XtNotifyReceiver> xtNotifyReceiverList) {
		this.xtNotifyReceiverList = xtNotifyReceiverList;
	}

	public List<XtMessage> getXtMessageList() {
		return xtMessageList;
	}

	public void setXtMessageList(List<XtMessage> xtMessageList) {
		this.xtMessageList = xtMessageList;
	}
	
	
}
