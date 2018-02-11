package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtNotifyReceiverDao;
import jehc.xtmodules.xtmodel.XtNotifyReceiver;
import jehc.xtmodules.xtservice.XtNotifyReceiverService;
/**
 * 通知接收人
 * @author邓纯杰
 *
 */
@Service("xtNotifyReceiverService")
public class XtNotifyReceiverServiceImpl extends BaseService implements XtNotifyReceiverService {
	@Autowired
	private XtNotifyReceiverDao xtNotifyReceiverDao;
	/**
	 * 初始化分页
	 * @param condition
	 * @return
	 */
	public List<XtNotifyReceiver> getXtNotifyReceiverListByCondition(Map<String, Object> condition){
		try {
			return xtNotifyReceiverDao.getXtNotifyReceiverListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	
	public XtNotifyReceiver getXtNotifyReceiverById(String xt_notify_receiver_id){
		try {
			return xtNotifyReceiverDao.getXtNotifyReceiverById(xt_notify_receiver_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	public int delXtNotifyReceiver(Map<String, Object> condition){
		try {
			return xtNotifyReceiverDao.delXtNotifyReceiver(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据通知编号查找集合
	 * @param xt_notify_id
	 * @return
	 */
	public List<XtNotifyReceiver> getXtNotifyReceiverListById(String xt_notify_id){
		return xtNotifyReceiverDao.getXtNotifyReceiverListById(xt_notify_id);
	}
	
	/**
	 * 更新已读
	 * @param condition
	 * @return
	 */
	public int updateXtNotifyReceiver(Map<String, Object> condition){
		try {
			return xtNotifyReceiverDao.updateXtNotifyReceiver(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
