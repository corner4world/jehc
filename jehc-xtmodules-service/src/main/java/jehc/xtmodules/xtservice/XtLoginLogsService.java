package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtLoginLogs;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
public interface XtLoginLogsService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtLoginLogs> getXtLoginLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_login_log_id 
	* @return
	*/
	public XtLoginLogs getXtLoginLogsById(String xt_login_log_id);
	/**
	* 添加
	* @param xt_login_logs 
	* @return
	*/
	public int addXtLoginLogs(XtLoginLogs xt_Login_Logs);
	/**
	* 修改
	* @param xt_login_logs 
	* @return
	*/
	public int updateXtLoginLogs(XtLoginLogs xt_Login_Logs);
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
	public List<XtLoginLogs> getGroupXtLoginLogsList(Map<String,Object> condition);
}
