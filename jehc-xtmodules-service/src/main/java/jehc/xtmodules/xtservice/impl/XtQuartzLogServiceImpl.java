package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtQuartzLogDao;
import jehc.xtmodules.xtmodel.XtQuartzLog;
import jehc.xtmodules.xtservice.XtQuartzLogService;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
@Service("xtQuartzLogService")
public class XtQuartzLogServiceImpl extends BaseService implements XtQuartzLogService{
	@Autowired
	private XtQuartzLogDao xtQuartzLogDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtQuartzLog> getXtQuartzLogListByCondition(Map<String,Object> condition){
		try{
			return xtQuartzLogDao.getXtQuartzLogListByCondition(condition);
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
	public XtQuartzLog getXtQuartzLogById(String xt_quartz_log_id){
		try{
			return xtQuartzLogDao.getXtQuartzLogById(xt_quartz_log_id);
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
	public int addXtQuartzLog(XtQuartzLog xt_Quartz_Log){
		int i = 0;
		try {
			i = xtQuartzLogDao.addXtQuartzLog(xt_Quartz_Log);
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
	public int updateXtQuartzLog(XtQuartzLog xt_Quartz_Log){
		int i = 0;
		try {
			i = xtQuartzLogDao.updateXtQuartzLog(xt_Quartz_Log);
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
			i = xtQuartzLogDao.delXtQuartzLog(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
