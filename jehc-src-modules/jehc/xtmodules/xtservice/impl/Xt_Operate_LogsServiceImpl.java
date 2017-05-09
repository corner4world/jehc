package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Operate_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Operate_Logs;
import jehc.xtmodules.xtservice.Xt_Operate_LogsService;

/**
* 操作日志表 
* 2015-05-24 08:30:58  邓纯杰
*/
@Service("xt_Operate_LogsService")
public class Xt_Operate_LogsServiceImpl implements Xt_Operate_LogsService{
	@Autowired
	private Xt_Operate_LogsDao xt_Operate_LogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Operate_Logs> getXtOperateLogsListByCondition(Map<String,Object> condition){
		try {
			return xt_Operate_LogsDao.getXtOperateLogsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_operate_log_id 
	* @return
	*/
	public Xt_Operate_Logs getXtOperateLogsById(String xt_operate_log_id){
		try {
			return xt_Operate_LogsDao.getXtOperateLogsById(xt_operate_log_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_operate_logs 
	* @return
	*/
	public int addXtOperateLogs(Xt_Operate_Logs xt_Operate_Logs){
		int i = 0;
		try {
			i = xt_Operate_LogsDao.addXtOperateLogs(xt_Operate_Logs);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_operate_logs 
	* @return
	*/
	public int updateXtOperateLogs(Xt_Operate_Logs xt_Operate_Logs){
		int i = 0;
		try {
			i = xt_Operate_LogsDao.updateXtOperateLogs(xt_Operate_Logs);
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
	public int delXtOperateLogs(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Operate_LogsDao.delXtOperateLogs(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
