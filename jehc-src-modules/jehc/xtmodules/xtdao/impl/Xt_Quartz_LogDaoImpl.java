package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Quartz_LogDao;
import jehc.xtmodules.xtmodel.Xt_Quartz_Log;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
@Repository("xt_Quartz_LogDao")
public class Xt_Quartz_LogDaoImpl  extends BaseDaoImpl implements Xt_Quartz_LogDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Quartz_Log> getXtQuartzLogListByCondition(Map<String,Object> condition){
		return (List<Xt_Quartz_Log>)this.getList("getXtQuartzLogListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_quartz_log_id 
	* @return
	*/
	public Xt_Quartz_Log getXtQuartzLogById(String xt_quartz_log_id){
		return (Xt_Quartz_Log)this.get("getXtQuartzLogById", xt_quartz_log_id);
	}
	/**
	* 添加
	* @param xt_quartz_log 
	* @return
	*/
	public int addXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log){
		return this.add("addXtQuartzLog", xt_Quartz_Log);
	}
	/**
	* 修改
	* @param xt_quartz_log 
	* @return
	*/
	public int updateXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log){
		return this.update("updateXtQuartzLog", xt_Quartz_Log);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtQuartzLog(Map<String,Object> condition){
		return this.del("delXtQuartzLog", condition);
	}
}
