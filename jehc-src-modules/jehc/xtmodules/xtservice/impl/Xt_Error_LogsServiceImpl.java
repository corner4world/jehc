package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Error_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Error_Logs;
import jehc.xtmodules.xtservice.Xt_Error_LogsService;

/**
* 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
@Service("xt_Error_LogsService")
public class Xt_Error_LogsServiceImpl extends BaseService implements Xt_Error_LogsService{
	@Autowired
	private Xt_Error_LogsDao xt_Error_LogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Error_Logs> getXtErrorLogsListByCondition(Map<String,Object> condition){
		try {
			return xt_Error_LogsDao.getXtErrorLogsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_error_log_id 
	* @return
	*/
	public Xt_Error_Logs getXtErrorLogsById(String xt_error_log_id){
		try {
			return xt_Error_LogsDao.getXtErrorLogsById(xt_error_log_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_error_logs 
	* @return
	*/
	public int addXtErrorLogs(Xt_Error_Logs xt_Error_Logs){
		int i = 0;
		try {
			i = xt_Error_LogsDao.addXtErrorLogs(xt_Error_Logs);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_error_logs 
	* @return
	*/
	public int updateXtErrorLogs(Xt_Error_Logs xt_Error_Logs){
		int i = 0;
		try {
			i = xt_Error_LogsDao.updateXtErrorLogs(xt_Error_Logs);
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
	public int delXtErrorLogs(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Error_LogsDao.delXtErrorLogs(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
