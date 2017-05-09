package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Monitor_Cpu;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
public interface Xt_Monitor_CpuDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Monitor_Cpu> getXtMonitorCpuListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_monitor_cpu_id 
	* @return
	*/
	public Xt_Monitor_Cpu getXtMonitorCpuById(String xt_monitor_cpu_id);
	/**
	* 添加
	* @param xt_monitor_cpu 
	* @return
	*/
	public int addXtMonitorCpu(Xt_Monitor_Cpu xt_Monitor_Cpu);
	/**
	* 修改
	* @param xt_monitor_cpu 
	* @return
	*/
	public int updateXtMonitorCpu(Xt_Monitor_Cpu xt_Monitor_Cpu);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitorCpu(Map<String,Object> condition);
}
