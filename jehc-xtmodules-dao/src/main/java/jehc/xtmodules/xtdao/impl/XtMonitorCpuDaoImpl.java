package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtMonitorCpuDao;
import jehc.xtmodules.xtmodel.XtMonitorCpu;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
@Repository("xtMonitorCpuDao")
public class XtMonitorCpuDaoImpl  extends BaseDaoImpl implements XtMonitorCpuDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMonitorCpu> getXtMonitorCpuListByCondition(Map<String,Object> condition){
		return (List<XtMonitorCpu>)this.getList("getXtMonitorCpuListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_monitor_cpu_id 
	* @return
	*/
	public XtMonitorCpu getXtMonitorCpuById(String xt_monitor_cpu_id){
		return (XtMonitorCpu)this.get("getXtMonitorCpuById", xt_monitor_cpu_id);
	}
	/**
	* 添加
	* @param xt_monitor_cpu 
	* @return
	*/
	public int addXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu){
		return this.add("addXtMonitorCpu", xt_Monitor_Cpu);
	}
	/**
	* 修改
	* @param xt_monitor_cpu 
	* @return
	*/
	public int updateXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu){
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
