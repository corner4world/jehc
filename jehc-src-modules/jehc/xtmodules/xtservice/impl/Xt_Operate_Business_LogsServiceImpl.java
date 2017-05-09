package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Operate_Business_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Operate_Business_Logs;
import jehc.xtmodules.xtservice.Xt_Operate_Business_LogsService;

/**
* 平台业务操作日志表 
* 2016-09-19 23:24:17  邓纯杰
*/
@Service("xt_Operate_Business_LogsService")
public class Xt_Operate_Business_LogsServiceImpl implements Xt_Operate_Business_LogsService{
	@Autowired
	private Xt_Operate_Business_LogsDao xt_Operate_Business_LogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Operate_Business_Logs> getXtOperateBusinessLogsListByCondition(Map<String,Object> condition){
		try{
			return xt_Operate_Business_LogsDao.getXtOperateBusinessLogsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_operate_business_logs_id 
	* @return
	*/
	public Xt_Operate_Business_Logs getXtOperateBusinessLogsById(String xt_operate_business_logs_id){
		try{
			return xt_Operate_Business_LogsDao.getXtOperateBusinessLogsById(xt_operate_business_logs_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_operate_business_logs 
	* @return
	*/
	public int putXtOperateBusinessLogs(Xt_Operate_Business_Logs xt_Operate_Business_Logs){
		int i = 0;
		try {
			i = xt_Operate_Business_LogsDao.addXtOperateBusinessLogs(xt_Operate_Business_Logs);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_operate_business_logs 
	* @return
	*/
	public int updateXtOperateBusinessLogs(Xt_Operate_Business_Logs xt_Operate_Business_Logs){
		int i = 0;
		try {
			i = xt_Operate_Business_LogsDao.updateXtOperateBusinessLogs(xt_Operate_Business_Logs);
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
	public int delXtOperateBusinessLogs(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Operate_Business_LogsDao.delXtOperateBusinessLogs(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
