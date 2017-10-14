package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtOperateLogs;

/**
* 操作日志表 
* 2015-05-24 08:30:58  邓纯杰
*/
public interface XtOperateLogsService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtOperateLogs> getXtOperateLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_operate_log_id 
	* @return
	*/
	public XtOperateLogs getXtOperateLogsById(String xt_operate_log_id);
	/**
	* 添加
	* @param xt_operate_logs 
	* @return
	*/
	public int addXtOperateLogs(XtOperateLogs xt_Operate_Logs);
	/**
	* 修改
	* @param xt_operate_logs 
	* @return
	*/
	public int updateXtOperateLogs(XtOperateLogs xt_Operate_Logs);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtOperateLogs(Map<String,Object> condition);
}
