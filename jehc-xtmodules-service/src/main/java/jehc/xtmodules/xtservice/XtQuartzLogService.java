package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtQuartzLog;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
public interface XtQuartzLogService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtQuartzLog> getXtQuartzLogListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_quartz_log_id 
	* @return
	*/
	public XtQuartzLog getXtQuartzLogById(String xt_quartz_log_id);
	/**
	* 添加
	* @param xt_quartz_log 
	* @return
	*/
	public int addXtQuartzLog(XtQuartzLog xt_Quartz_Log);
	/**
	* 修改
	* @param xt_quartz_log 
	* @return
	*/
	public int updateXtQuartzLog(XtQuartzLog xt_Quartz_Log);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtQuartzLog(Map<String,Object> condition);
}
