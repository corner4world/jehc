package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtOperateLogsDao;
import jehc.xtmodules.xtmodel.XtOperateLogs;
import jehc.xtmodules.xtservice.XtOperateLogsService;

/**
* 操作日志表 
* 2015-05-24 08:30:58  邓纯杰
*/
@Service("xtOperateLogsService")
public class XtOperateLogsServiceImpl implements XtOperateLogsService{
	@Autowired
	private XtOperateLogsDao xtOperateLogsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtOperateLogs> getXtOperateLogsListByCondition(Map<String,Object> condition){
		try {
			return xtOperateLogsDao.getXtOperateLogsListByCondition(condition);
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
	public XtOperateLogs getXtOperateLogsById(String xt_operate_log_id){
		try {
			return xtOperateLogsDao.getXtOperateLogsById(xt_operate_log_id);
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
	public int addXtOperateLogs(XtOperateLogs xt_Operate_Logs){
		int i = 0;
		try {
			i = xtOperateLogsDao.addXtOperateLogs(xt_Operate_Logs);
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
	public int updateXtOperateLogs(XtOperateLogs xt_Operate_Logs){
		int i = 0;
		try {
			i = xtOperateLogsDao.updateXtOperateLogs(xt_Operate_Logs);
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
			i = xtOperateLogsDao.delXtOperateLogs(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
