package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtMonitor;

/**
* 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
public interface XtMonitorDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMonitor> getXtMonitorListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_monitor_id 
	* @return
	*/
	public XtMonitor getXtMonitorById(String xt_monitor_id);
	/**
	* 添加
	* @param xt_monitor 
	* @return
	*/
	public int addXtMonitor(XtMonitor xt_Monitor);
	/**
	* 修改
	* @param xt_monitor 
	* @return
	*/
	public int updateXtMonitor(XtMonitor xt_Monitor);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitor(Map<String,Object> condition);
}
