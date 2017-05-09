package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Monitor_CpuDao;
import jehc.xtmodules.xtmodel.Xt_Monitor_Cpu;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
@Repository("xt_Monitor_CpuDao")
public class Xt_Monitor_CpuDaoImpl  extends BaseDaoImpl implements Xt_Monitor_CpuDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Monitor_Cpu> getXtMonitorCpuListByCondition(Map<String,Object> condition){
		return (List<Xt_Monitor_Cpu>)this.getList("getXtMonitorCpuListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_monitor_cpu_id 
	* @return
	*/
	public Xt_Monitor_Cpu getXtMonitorCpuById(String xt_monitor_cpu_id){
		return (Xt_Monitor_Cpu)this.get("getXtMonitorCpuById", xt_monitor_cpu_id);
	}
	/**
	* 添加
	* @param xt_monitor_cpu 
	* @return
	*/
	public int addXtMonitorCpu(Xt_Monitor_Cpu xt_Monitor_Cpu){
		return this.add("addXtMonitorCpu", xt_Monitor_Cpu);
	}
	/**
	* 修改
	* @param xt_monitor_cpu 
	* @return
	*/
	public int updateXtMonitorCpu(Xt_Monitor_Cpu xt_Monitor_Cpu){
		return this.update("updateXtMonitorCpu", xt_Monitor_Cpu);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitorCpu(Map<String,Object> condition){
		return this.del("delXtMonitorCpu", condition);
	}
}
