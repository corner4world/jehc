package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Login_Logs;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
public interface Xt_Login_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Login_Logs> getXtLoginLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_login_log_id 
	* @return
	*/
	public Xt_Login_Logs getXtLoginLogsById(String xt_login_log_id);
	/**
	* 添加
	* @param xt_login_logs 
	* @return
	*/
	public int addXtLoginLogs(Xt_Login_Logs xt_Login_Logs);
	/**
	* 修改
	* @param xt_login_logs 
	* @return
	*/
	public int updateXtLoginLogs(Xt_Login_Logs xt_Login_Logs);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtLoginLogs(Map<String,Object> condition);
	
	/**
	 * 统计出每个人登录的次数
	 * @param condition
	 * @return
	 */
	public List<Xt_Login_Logs> getGroupXtLoginLogsList(Map<String,Object> condition);
}
