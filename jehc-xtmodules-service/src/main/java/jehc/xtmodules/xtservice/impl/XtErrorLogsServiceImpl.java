package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtErrorLogsDao;
import jehc.xtmodules.xtmodel.XtErrorLogs;
import jehc.xtmodules.xtservice.XtErrorLogsService;

/**
* 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
@Service("xtErrorLogsService")
public class XtErrorLogsServiceImpl extends BaseService implements XtErrorLogsService{
	@Autowired
	private XtErrorLogsDao xtErrorLogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtErrorLogs> getXtErrorLogsListByCondition(Map<String,Object> condition){
		try {
			return xtErrorLogsDao.getXtErrorLogsListByCondition(condition);
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
	public XtErrorLogs getXtErrorLogsById(String xt_error_log_id){
		try {
			return xtErrorLogsDao.getXtErrorLogsById(xt_error_log_id);
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
	public int addXtErrorLogs(XtErrorLogs xt_Error_Logs){
		int i = 0;
		try {
			i = xtErrorLogsDao.addXtErrorLogs(xt_Error_Logs);
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
	public int updateXtErrorLogs(XtErrorLogs xt_Error_Logs){
		int i = 0;
		try {
			i = xtErrorLogsDao.updateXtErrorLogs(xt_Error_Logs);
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
			i = xtErrorLogsDao.delXtErrorLogs(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
