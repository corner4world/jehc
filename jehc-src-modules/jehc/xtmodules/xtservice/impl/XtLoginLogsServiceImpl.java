package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtLoginLogsDao;
import jehc.xtmodules.xtmodel.XtLoginLogs;
import jehc.xtmodules.xtservice.XtLoginLogsService;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
@Service("xtLoginLogsService")
public class XtLoginLogsServiceImpl extends BaseService implements XtLoginLogsService{
	@Autowired
	private XtLoginLogsDao xtLoginLogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtLoginLogs> getXtLoginLogsListByCondition(Map<String,Object> condition){
		try {
			return xtLoginLogsDao.getXtLoginLogsListByCondition(condition);
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
	public XtLoginLogs getXtLoginLogsById(String xt_login_log_id){
		try {
			return xtLoginLogsDao.getXtLoginLogsById(xt_login_log_id);
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
	public int addXtLoginLogs(XtLoginLogs xt_Login_Logs){
		int i = 0;
		try {
			i = xtLoginLogsDao.addXtLoginLogs(xt_Login_Logs);
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
	public int updateXtLoginLogs(XtLoginLogs xt_Login_Logs){
		int i = 0;
		try {
			i = xtLoginLogsDao.updateXtLoginLogs(xt_Login_Logs);
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
			i = xtLoginLogsDao.delXtLoginLogs(condition);
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
	public List<XtLoginLogs> getGroupXtLoginLogsList(Map<String,Object> condition){
		try {
			return xtLoginLogsDao.getGroupXtLoginLogsList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
