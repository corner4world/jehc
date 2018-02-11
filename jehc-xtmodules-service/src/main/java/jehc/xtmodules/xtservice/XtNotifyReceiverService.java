package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtNotifyReceiver;

/**
 * 通知接收人
 * @author邓纯杰
 *
 */
public interface XtNotifyReceiverService {
	/**
	 * 初始化分页
	 * @param condition
	 * @return
	 */
	public List<XtNotifyReceiver> getXtNotifyReceiverListByCondition(Map<String, Object> condition);
	
	public XtNotifyReceiver getXtNotifyReceiverById(String xt_notify_receiver_id);
	
	public int delXtNotifyReceiver(Map<String, Object> condition);
	
	/**
	 * 根据通知编号查找集合
	 * @param xt_notify_id
	 * @return
	 */
	public List<XtNotifyReceiver> getXtNotifyReceiverListById(String xt_notify_id);
	/**
	 * 更新已读
	 * @param condition
	 * @return
	 */
	public int updateXtNotifyReceiver(Map<String, Object> condition);
}
