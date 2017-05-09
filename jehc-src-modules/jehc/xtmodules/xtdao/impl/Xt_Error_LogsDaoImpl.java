package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Error_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Error_Logs;

/**
* 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
@Repository("xt_Error_LogsDao")
public class Xt_Error_LogsDaoImpl  extends BaseDaoImpl implements Xt_Error_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Error_Logs> getXtErrorLogsListByCondition(Map<String,Object> condition){
		return (List<Xt_Error_Logs>)this.getList("getXtErrorLogsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_error_log_id 
	* @return
	*/
	public Xt_Error_Logs getXtErrorLogsById(String xt_error_log_id){
		return (Xt_Error_Logs)this.get("getXtErrorLogsById", xt_error_log_id);
	}
	/**
	* 添加
	* @param xt_error_logs 
	* @return
	*/
	public int addXtErrorLogs(Xt_Error_Logs xt_Error_Logs){
		return this.add("addXtErrorLogs", xt_Error_Logs);
	}
	/**
	* 修改
	* @param xt_error_logs 
	* @return
	*/
	public int updateXtErrorLogs(Xt_Error_Logs xt_Error_Logs){
		return this.update("updateXtErrorLogs", xt_Error_Logs);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtErrorLogs(Map<String,Object> condition){
		return this.del("delXtErrorLogs", condition);
	}
}
