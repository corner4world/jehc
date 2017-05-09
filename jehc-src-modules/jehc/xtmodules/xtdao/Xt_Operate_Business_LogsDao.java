package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Operate_Business_Logs;

/**
* 平台业务操作日志表 
* 2016-09-19 23:24:17  邓纯杰
*/
public interface Xt_Operate_Business_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Operate_Business_Logs> getXtOperateBusinessLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_operate_business_logs_id 
	* @return
	*/
	public Xt_Operate_Business_Logs getXtOperateBusinessLogsById(String xt_operate_business_logs_id);
	/**
	* 添加
	* @param xt_operate_business_logs 
	* @return
	*/
	public int addXtOperateBusinessLogs(Xt_Operate_Business_Logs xt_Operate_Business_Logs);
	/**
	* 修改
	* @param xt_operate_business_logs 
	* @return
	*/
	public int updateXtOperateBusinessLogs(Xt_Operate_Business_Logs xt_Operate_Business_Logs);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtOperateBusinessLogs(Map<String,Object> condition);
}
