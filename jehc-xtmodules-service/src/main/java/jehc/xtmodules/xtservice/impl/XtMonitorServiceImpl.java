package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtMonitorDao;
import jehc.xtmodules.xtmodel.XtMonitor;
import jehc.xtmodules.xtservice.XtMonitorService;

/**
* 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
@Service("xtMonitorService")
public class XtMonitorServiceImpl extends BaseService implements XtMonitorService{
	@Autowired
	private XtMonitorDao xtMonitorDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMonitor> getXtMonitorListByCondition(Map<String,Object> condition){
		try{
			return xtMonitorDao.getXtMonitorListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_monitor_id 
	* @return
	*/
	public XtMonitor getXtMonitorById(String xt_monitor_id){
		try{
			return xtMonitorDao.getXtMonitorById(xt_monitor_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_monitor 
	* @return
	*/
	public int addXtMonitor(XtMonitor xt_Monitor){
		int i = 0;
		try {
			i = xtMonitorDao.addXtMonitor(xt_Monitor);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_monitor 
	* @return
	*/
	public int updateXtMonitor(XtMonitor xt_Monitor){
		int i = 0;
		try {
			i = xtMonitorDao.updateXtMonitor(xt_Monitor);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitor(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtMonitorDao.delXtMonitor(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
