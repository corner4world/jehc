package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_MonitorDao;
import jehc.xtmodules.xtmodel.Xt_Monitor;
import jehc.xtmodules.xtservice.Xt_MonitorService;

/**
* 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
@Service("xt_MonitorService")
public class Xt_MonitorServiceImpl extends BaseService implements Xt_MonitorService{
	@Autowired
	private Xt_MonitorDao xt_MonitorDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Monitor> getXtMonitorListByCondition(Map<String,Object> condition){
		try{
			return xt_MonitorDao.getXtMonitorListByCondition(condition);
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
	public Xt_Monitor getXtMonitorById(String xt_monitor_id){
		try{
			return xt_MonitorDao.getXtMonitorById(xt_monitor_id);
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
	public int addXtMonitor(Xt_Monitor xt_Monitor){
		int i = 0;
		try {
			i = xt_MonitorDao.addXtMonitor(xt_Monitor);
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
	public int updateXtMonitor(Xt_Monitor xt_Monitor){
		int i = 0;
		try {
			i = xt_MonitorDao.updateXtMonitor(xt_Monitor);
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
			i = xt_MonitorDao.delXtMonitor(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
