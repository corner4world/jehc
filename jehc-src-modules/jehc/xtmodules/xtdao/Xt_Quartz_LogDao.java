package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Quartz_Log;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
public interface Xt_Quartz_LogDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Quartz_Log> getXtQuartzLogListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_quartz_log_id 
	* @return
	*/
	public Xt_Quartz_Log getXtQuartzLogById(String xt_quartz_log_id);
	/**
	* 添加
	* @param xt_quartz_log 
	* @return
	*/
	public int addXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log);
	/**
	* 修改
	* @param xt_quartz_log 
	* @return
	*/
	public int updateXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtQuartzLog(Map<String,Object> condition);
}
