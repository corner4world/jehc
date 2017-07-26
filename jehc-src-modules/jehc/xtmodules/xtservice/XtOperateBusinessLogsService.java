package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtOperateBusinessLogs;

/**
* 平台业务操作日志表 
* 2016-09-19 23:24:17  邓纯杰
*/
public interface XtOperateBusinessLogsService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtOperateBusinessLogs> getXtOperateBusinessLogsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_operate_business_logs_id 
	* @return
	*/
	public XtOperateBusinessLogs getXtOperateBusinessLogsById(String xt_operate_business_logs_id);
	/**
	* 添加
	* @param xt_operate_business_logs 
	* @return
	*/
	public int putXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs);
	/**
	* 修改
	* @param xt_operate_business_logs 
	* @return
	*/
	public int updateXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtOperateBusinessLogs(Map<String,Object> condition);
}
