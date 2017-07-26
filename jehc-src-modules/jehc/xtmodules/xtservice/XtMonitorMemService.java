package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtMonitorMem;

/**
* 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
public interface XtMonitorMemService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMonitorMem> getXtMonitorMemListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_monitor_mem_id 
	* @return
	*/
	public XtMonitorMem getXtMonitorMemById(String xt_monitor_mem_id);
	/**
	* 添加
	* @param xt_monitor_mem 
	* @return
	*/
	public int addXtMonitorMem(XtMonitorMem xt_Monitor_Mem);
	/**
	* 修改
	* @param xt_monitor_mem 
	* @return
	*/
	public int updateXtMonitorMem(XtMonitorMem xt_Monitor_Mem);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitorMem(Map<String,Object> condition);
}
