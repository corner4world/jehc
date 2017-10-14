package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtMonitorCpu;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
public interface XtMonitorCpuService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMonitorCpu> getXtMonitorCpuListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_monitor_cpu_id 
	* @return
	*/
	public XtMonitorCpu getXtMonitorCpuById(String xt_monitor_cpu_id);
	/**
	* 添加
	* @param xt_monitor_cpu 
	* @return
	*/
	public int addXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu);
	/**
	* 修改
	* @param xt_monitor_cpu 
	* @return
	*/
	public int updateXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitorCpu(Map<String,Object> condition);
}
