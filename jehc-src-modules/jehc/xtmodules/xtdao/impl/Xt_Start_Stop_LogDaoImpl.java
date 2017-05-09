package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Start_Stop_LogDao;
import jehc.xtmodules.xtmodel.Xt_Start_Stop_Log;

/**
* 服务器启动与关闭日志; InnoDB free: 9216 kB; InnoDB free: 9216 kB 
* 2015-10-31 21:28:14  邓纯杰
*/
@Repository("xt_Start_Stop_LogDao")
public class Xt_Start_Stop_LogDaoImpl  extends BaseDaoImpl implements Xt_Start_Stop_LogDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Start_Stop_Log> getXtStartStopLogListByCondition(Map<String,Object> condition){
		return (List<Xt_Start_Stop_Log>)this.getList("getXtStartStopLogListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_start_stop_log_id 
	* @return
	*/
	public Xt_Start_Stop_Log getXtStartStopLogById(String xt_start_stop_log_id){
		return (Xt_Start_Stop_Log)this.get("getXtStartStopLogById", xt_start_stop_log_id);
	}
	/**
	* 添加
	* @param xt_start_stop_log 
	* @return
	*/
	public int addXtStartStopLog(Xt_Start_Stop_Log xt_Start_Stop_Log){
		return this.add("addXtStartStopLog", xt_Start_Stop_Log);
	}
	/**
	* 修改
	* @param xt_start_stop_log 
	* @return
	*/
	public int updateXtStartStopLog(Xt_Start_Stop_Log xt_Start_Stop_Log){
		return this.update("updateXtStartStopLog", xt_Start_Stop_Log);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtStartStopLog(Map<String,Object> condition){
		return this.del("delXtStartStopLog", condition);
	}
}
