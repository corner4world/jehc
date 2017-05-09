package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Error_Logs;

/**
* 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
public interface Xt_Error_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Error_Logs> getXtErrorLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_error_log_id 
	* @return
	*/
	public Xt_Error_Logs getXtErrorLogsById(String xt_error_log_id);
	/**
	* 添加
	* @param xt_error_logs 
	* @return
	*/
	public int addXtErrorLogs(Xt_Error_Logs xt_Error_Logs);
	/**
	* 修改
	* @param xt_error_logs 
	* @return
	*/
	public int updateXtErrorLogs(Xt_Error_Logs xt_Error_Logs);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtErrorLogs(Map<String,Object> condition);
}
