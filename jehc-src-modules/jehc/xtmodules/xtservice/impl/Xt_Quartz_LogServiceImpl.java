package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Quartz_LogDao;
import jehc.xtmodules.xtmodel.Xt_Quartz_Log;
import jehc.xtmodules.xtservice.Xt_Quartz_LogService;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
@Service("xt_Quartz_LogService")
public class Xt_Quartz_LogServiceImpl extends BaseService implements Xt_Quartz_LogService{
	@Autowired
	private Xt_Quartz_LogDao xt_Quartz_LogDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Quartz_Log> getXtQuartzLogListByCondition(Map<String,Object> condition){
		try{
			return xt_Quartz_LogDao.getXtQuartzLogListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_quartz_log_id 
	* @return
	*/
	public Xt_Quartz_Log getXtQuartzLogById(String xt_quartz_log_id){
		try{
			return xt_Quartz_LogDao.getXtQuartzLogById(xt_quartz_log_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_quartz_log 
	* @return
	*/
	public int addXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log){
		int i = 0;
		try {
			i = xt_Quartz_LogDao.addXtQuartzLog(xt_Quartz_Log);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_quartz_log 
	* @return
	*/
	public int updateXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log){
		int i = 0;
		try {
			i = xt_Quartz_LogDao.updateXtQuartzLog(xt_Quartz_Log);
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
	public int delXtQuartzLog(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Quartz_LogDao.delXtQuartzLog(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
