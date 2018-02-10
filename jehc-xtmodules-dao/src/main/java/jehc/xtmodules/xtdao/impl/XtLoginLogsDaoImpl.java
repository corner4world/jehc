package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtLoginLogsDao;
import jehc.xtmodules.xtmodel.XtLoginLogs;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
@Repository("xtLoginLogsDao")
public class XtLoginLogsDaoImpl  extends BaseDaoImpl implements XtLoginLogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtLoginLogs> getXtLoginLogsListByCondition(Map<String,Object> condition){
		return (List<XtLoginLogs>)this.getList("getXtLoginLogsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_login_log_id 
	* @return
	*/
	public XtLoginLogs getXtLoginLogsById(String xt_login_log_id){
		return (XtLoginLogs)this.get("getXtLoginLogsById", xt_login_log_id);
	}
	/**
	* 添加
	* @param xt_login_logs 
	* @return
	*/
	public int addXtLoginLogs(XtLoginLogs xt_Login_Logs){
		return this.add("addXtLoginLogs", xt_Login_Logs);
	}
	/**
	* 修改
	* @param xt_login_logs 
	* @return
	*/
	public int updateXtLoginLogs(XtLoginLogs xt_Login_Logs){
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
	public List<XtLoginLogs> getGroupXtLoginLogsList(Map<String,Object> condition){
		return (List<XtLoginLogs>)this.getList("getGroupXtLoginLogsList",condition); 
	}
	/**
	 * 个人登录次数
	 * @param condition
	 * @return
	 */
	public int getXtLoginLogsCount(Map<String,Object> condition){
		return new Integer(this.get("getXtLoginLogsCount", condition).toString());
	}
}
