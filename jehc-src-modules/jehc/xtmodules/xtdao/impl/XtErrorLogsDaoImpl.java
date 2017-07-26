package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtErrorLogsDao;
import jehc.xtmodules.xtmodel.XtErrorLogs;

/**
* 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
@Repository("xtErrorLogsDao")
public class XtErrorLogsDaoImpl  extends BaseDaoImpl implements XtErrorLogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtErrorLogs> getXtErrorLogsListByCondition(Map<String,Object> condition){
		return (List<XtErrorLogs>)this.getList("getXtErrorLogsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_error_log_id 
	* @return
	*/
	public XtErrorLogs getXtErrorLogsById(String xt_error_log_id){
		return (XtErrorLogs)this.get("getXtErrorLogsById", xt_error_log_id);
	}
	/**
	* 添加
	* @param xt_error_logs 
	* @return
	*/
	public int addXtErrorLogs(XtErrorLogs xt_Error_Logs){
		return this.add("addXtErrorLogs", xt_Error_Logs);
	}
	/**
	* 修改
	* @param xt_error_logs 
	* @return
	*/
	public int updateXtErrorLogs(XtErrorLogs xt_Error_Logs){
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
