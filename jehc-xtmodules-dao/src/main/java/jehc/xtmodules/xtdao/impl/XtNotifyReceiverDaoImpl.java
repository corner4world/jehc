package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtNotifyReceiverDao;
import jehc.xtmodules.xtmodel.XtNotifyReceiver;
/**
 * 通知接收人
 * @author邓纯杰
 *
 */
@Repository("xtNotifyReceiverDao")
public class XtNotifyReceiverDaoImpl extends BaseDaoImpl implements XtNotifyReceiverDao {
	/**
	 * 初始化分页
	 * @param condition
	 * @return
	 */
	public List<XtNotifyReceiver> getXtNotifyReceiverListByCondition(Map<String, Object> condition){
		return (List<XtNotifyReceiver>)this.getList("getXtNotifyReceiverListByCondition", condition);
	}
	
	
	public XtNotifyReceiver getXtNotifyReceiverById(String xt_notify_receiver_id){
		return (XtNotifyReceiver)this.get("getXtNotifyReceiverById", xt_notify_receiver_id);
	}
	
	
	public int addXtNotifyReceiver(XtNotifyReceiver xtNotifyReceiver){
		return this.add("addXtNotifyReceiver", xtNotifyReceiver);
	}
	
	public int delXtNotifyReceiver(Map<String, Object> condition){
		return this.update("delXtNotifyReceiver", condition);
	}
	
	/**
	 * 根据通知编号查找集合
	 * @param xt_notify_id
	 * @return
	 */
	public List<XtNotifyReceiver> getXtNotifyReceiverListById(String xt_notify_id){
		return (List<XtNotifyReceiver>)this.getList("getXtNotifyReceiverListById", xt_notify_id);
	}
	
	/**
	 * 更新已读
	 * @param condition
	 * @return
	 */
	public int updateXtNotifyReceiver(Map<String, Object> condition){
		return this.update("updateXtNotifyReceiver", condition);
	}
}
