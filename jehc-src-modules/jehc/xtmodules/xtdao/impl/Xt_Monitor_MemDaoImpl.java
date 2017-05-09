package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Monitor_MemDao;
import jehc.xtmodules.xtmodel.Xt_Monitor_Mem;

/**
* 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
@Repository("xt_Monitor_MemDao")
public class Xt_Monitor_MemDaoImpl  extends BaseDaoImpl implements Xt_Monitor_MemDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Monitor_Mem> getXtMonitorMemListByCondition(Map<String,Object> condition){
		return (List<Xt_Monitor_Mem>)this.getList("getXtMonitorMemListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_monitor_mem_id 
	* @return
	*/
	public Xt_Monitor_Mem getXtMonitorMemById(String xt_monitor_mem_id){
		return (Xt_Monitor_Mem)this.get("getXtMonitorMemById", xt_monitor_mem_id);
	}
	/**
	* 添加
	* @param xt_monitor_mem 
	* @return
	*/
	public int addXtMonitorMem(Xt_Monitor_Mem xt_Monitor_Mem){
		return this.add("addXtMonitorMem", xt_Monitor_Mem);
	}
	/**
	* 修改
	* @param xt_monitor_mem 
	* @return
	*/
	public int updateXtMonitorMem(Xt_Monitor_Mem xt_Monitor_Mem){
		return this.update("updateXtMonitorMem", xt_Monitor_Mem);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMonitorMem(Map<String,Object> condition){
		return this.del("delXtMonitorMem", condition);
	}
}
