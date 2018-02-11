package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtNotifyDao;
import jehc.xtmodules.xtdao.XtNotifyReceiverDao;
import jehc.xtmodules.xtmodel.XtNotify;
import jehc.xtmodules.xtmodel.XtNotifyReceiver;
import jehc.xtmodules.xtservice.XtNotifyService;
/**
 * 通知
 * @author 邓纯杰
 *
 */
@Service("xtNotifyService")
public class XtNotifyServiceImpl extends BaseService implements XtNotifyService {
	@Autowired
	private XtNotifyDao xtNotifyDao;
	@Autowired
	private XtNotifyReceiverDao xtNotifyReceiverDao;
	/**
	 * 初始化分页
	 * @param condition
	 * @return
	 */
	public List<XtNotify> getXtNotifyListByCondition(Map<String, Object> condition){
		try {
			return xtNotifyDao.getXtNotifyListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询对象
	 * @param xt_notify_id
	 * @return
	 */
	public XtNotify getXtNotifyById(String xt_notify_id){
		try {
			return xtNotifyDao.getXtNotifyById(xt_notify_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 插入对象
	 * @param xtNotify
	 * @return
	 */
	public int addXtNotify(XtNotify xtNotify){
		try {
			List<XtNotifyReceiver> xtNotifyReceiverList =  xtNotify.getXtNotifyReceiverList();
			//先插入主表
			xtNotifyDao.addXtNotify(xtNotify);
			//操作子表
			for(int i = 0; i < xtNotifyReceiverList.size(); i++){
				xtNotifyReceiverList.get(i).setXt_notify_id(xtNotify.getXt_notify_id());
				xtNotifyReceiverDao.addXtNotifyReceiver(xtNotifyReceiverList.get(i));
			}
			return 1;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/***
	 * 删除
	 * @param condition
	 * @return
	 */
	public int delXtNotify(Map<String, Object> condition){
		try {
			return xtNotifyDao.delXtNotify(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
