package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Login_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Login_Logs;
import jehc.xtmodules.xtservice.Xt_Login_LogsService;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
@Service("xt_Login_LogsService")
public class Xt_Login_LogsServiceImpl extends BaseService implements Xt_Login_LogsService{
	@Autowired
	private Xt_Login_LogsDao xt_Login_LogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Login_Logs> getXtLoginLogsListByCondition(Map<String,Object> condition){
		try {
			return xt_Login_LogsDao.getXtLoginLogsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_login_log_id 
	* @return
	*/
	public Xt_Login_Logs getXtLoginLogsById(String xt_login_log_id){
		try {
			return xt_Login_LogsDao.getXtLoginLogsById(xt_login_log_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_login_logs 
	* @return
	*/
	public int addXtLoginLogs(Xt_Login_Logs xt_Login_Logs){
		int i = 0;
		try {
			i = xt_Login_LogsDao.addXtLoginLogs(xt_Login_Logs);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_login_logs 
	* @return
	*/
	public int updateXtLoginLogs(Xt_Login_Logs xt_Login_Logs){
		int i = 0;
		try {
			i = xt_Login_LogsDao.updateXtLoginLogs(xt_Login_Logs);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtLoginLogs(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Login_LogsDao.delXtLoginLogs(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 统计出每个人登录的次数
	 * @param condition
	 * @return
	 */
	public List<Xt_Login_Logs> getGroupXtLoginLogsList(Map<String,Object> condition){
		try {
			return xt_Login_LogsDao.getGroupXtLoginLogsList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
