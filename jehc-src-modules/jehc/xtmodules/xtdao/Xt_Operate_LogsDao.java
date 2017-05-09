package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Operate_Logs;

/**
* 操作日志表; InnoDB free: 6144 kB 
* 2015-08-28 23:15:51  邓纯杰
*/
public interface Xt_Operate_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Operate_Logs> getXtOperateLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_operate_log_id 
	* @return
	*/
	public Xt_Operate_Logs getXtOperateLogsById(String xt_operate_log_id);
	/**
	* 添加
	* @param xt_operate_logs 
	* @return
	*/
	public int addXtOperateLogs(Xt_Operate_Logs xt_Operate_Logs);
	/**
	* 修改
	* @param xt_operate_logs 
	* @return
	*/
	public int updateXtOperateLogs(Xt_Operate_Logs xt_Operate_Logs);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtOperateLogs(Map<String,Object> condition);
}
