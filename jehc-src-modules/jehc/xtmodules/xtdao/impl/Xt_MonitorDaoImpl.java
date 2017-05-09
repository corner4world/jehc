package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_MonitorDao;
import jehc.xtmodules.xtmodel.Xt_Monitor;

/**
* 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
@Repository("xt_MonitorDao")
public class Xt_MonitorDaoImpl  extends BaseDaoImpl implements Xt_MonitorDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Monitor> getXtMonitorListByCondition(Map<String,Object> condition){
		return (List<Xt_Monitor>)this.getList("getXtMonitorListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_monitor_id 
	* @return
	*/
	public Xt_Monitor getXtMonitorById(String xt_monitor_id){
		return (Xt_Monitor)this.get("getXtMonitorById", xt_monitor_id);
	}
	/**
	* 添加
	* @param xt_monitor 
	* @return
	*/
	public int addXtMonitor(Xt_Monitor xt_Monitor){
		return this.add("addXtMonitor", xt_Monitor);
	}
	/**
	* 修改
	* @param xt_monitor 
	* @return
	*/
	public int updateXtMonitor(Xt_Monitor xt_Monitor){
		return this.update("updateXtMonitor", xt_Monitor);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitor(Map<String,Object> condition){
		return this.del("delXtMonitor", condition);
	}
}
