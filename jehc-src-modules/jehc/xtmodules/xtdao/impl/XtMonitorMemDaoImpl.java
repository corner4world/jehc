package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtMonitorMemDao;
import jehc.xtmodules.xtmodel.XtMonitorMem;

/**
* 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
@Repository("xtMonitorMemDao")
public class XtMonitorMemDaoImpl  extends BaseDaoImpl implements XtMonitorMemDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMonitorMem> getXtMonitorMemListByCondition(Map<String,Object> condition){
		return (List<XtMonitorMem>)this.getList("getXtMonitorMemListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_monitor_mem_id 
	* @return
	*/
	public XtMonitorMem getXtMonitorMemById(String xt_monitor_mem_id){
		return (XtMonitorMem)this.get("getXtMonitorMemById", xt_monitor_mem_id);
	}
	/**
	* 添加
	* @param xt_monitor_mem 
	* @return
	*/
	public int addXtMonitorMem(XtMonitorMem xt_Monitor_Mem){
		return this.add("addXtMonitorMem", xt_Monitor_Mem);
	}
	/**
	* 修改
	* @param xt_monitor_mem 
	* @return
	*/
	public int updateXtMonitorMem(XtMonitorMem xt_Monitor_Mem){
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
