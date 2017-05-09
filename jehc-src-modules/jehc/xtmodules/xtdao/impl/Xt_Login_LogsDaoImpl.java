package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Login_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Login_Logs;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
@Repository("xt_Login_LogsDao")
public class Xt_Login_LogsDaoImpl  extends BaseDaoImpl implements Xt_Login_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Login_Logs> getXtLoginLogsListByCondition(Map<String,Object> condition){
		return (List<Xt_Login_Logs>)this.getList("getXtLoginLogsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_login_log_id 
	* @return
	*/
	public Xt_Login_Logs getXtLoginLogsById(String xt_login_log_id){
		return (Xt_Login_Logs)this.get("getXtLoginLogsById", xt_login_log_id);
	}
	/**
	* 添加
	* @param xt_login_logs 
	* @return
	*/
	public int addXtLoginLogs(Xt_Login_Logs xt_Login_Logs){
		return this.add("addXtLoginLogs", xt_Login_Logs);
	}
	/**
	* 修改
	* @param xt_login_logs 
	* @return
	*/
	public int updateXtLoginLogs(Xt_Login_Logs xt_Login_Logs){
		return this.update("updateXtLoginLogs", xt_Login_Logs);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtLoginLogs(Map<String,Object> condition){
		return this.del("delXtLoginLogs", condition);
	}
	
	/**
	 * 统计出每个人登录的次数
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Login_Logs> getGroupXtLoginLogsList(Map<String,Object> condition){
		return (List<Xt_Login_Logs>)this.getList("getGroupXtLoginLogsList",condition); 
	}
}
