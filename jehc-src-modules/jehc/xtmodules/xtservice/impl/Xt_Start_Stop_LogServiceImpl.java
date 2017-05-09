package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Start_Stop_LogDao;
import jehc.xtmodules.xtmodel.Xt_Start_Stop_Log;
import jehc.xtmodules.xtservice.Xt_Start_Stop_LogService;

/**
* 服务器启动与关闭日志; InnoDB free: 9216 kB; InnoDB free: 9216 kB 
* 2015-10-31 21:28:14  邓纯杰
*/
@Service("xt_Start_Stop_LogService")
public class Xt_Start_Stop_LogServiceImpl extends BaseService implements Xt_Start_Stop_LogService{
	@Autowired
	private Xt_Start_Stop_LogDao xt_Start_Stop_LogDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Start_Stop_Log> getXtStartStopLogListByCondition(Map<String,Object> condition){
		try{
			return xt_Start_Stop_LogDao.getXtStartStopLogListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_start_stop_log_id 
	* @return
	*/
	public Xt_Start_Stop_Log getXtStartStopLogById(String xt_start_stop_log_id){
		try{
			return xt_Start_Stop_LogDao.getXtStartStopLogById(xt_start_stop_log_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_start_stop_log 
	* @return
	*/
	public int addXtStartStopLog(Xt_Start_Stop_Log xt_Start_Stop_Log){
		int i = 0;
		try {
			i = xt_Start_Stop_LogDao.addXtStartStopLog(xt_Start_Stop_Log);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_start_stop_log 
	* @return
	*/
	public int updateXtStartStopLog(Xt_Start_Stop_Log xt_Start_Stop_Log){
		int i = 0;
		try {
			i = xt_Start_Stop_LogDao.updateXtStartStopLog(xt_Start_Stop_Log);
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
	public int delXtStartStopLog(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Start_Stop_LogDao.delXtStartStopLog(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
