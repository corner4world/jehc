package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtMonitorDao;
import jehc.xtmodules.xtmodel.XtMonitor;

/**
* 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
@Repository("xtMonitorDao")
public class XtMonitorDaoImpl  extends BaseDaoImpl implements XtMonitorDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMonitor> getXtMonitorListByCondition(Map<String,Object> condition){
		return (List<XtMonitor>)this.getList("getXtMonitorListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_monitor_id 
	* @return
	*/
	public XtMonitor getXtMonitorById(String xt_monitor_id){
		return (XtMonitor)this.get("getXtMonitorById", xt_monitor_id);
	}
	/**
	* 添加
	* @param xt_monitor 
	* @return
	*/
	public int addXtMonitor(XtMonitor xt_Monitor){
		return this.add("addXtMonitor", xt_Monitor);
	}
	/**
	* 修改
	* @param xt_monitor 
	* @return
	*/
	public int updateXtMonitor(XtMonitor xt_Monitor){
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
