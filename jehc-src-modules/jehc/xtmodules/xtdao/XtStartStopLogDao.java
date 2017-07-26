package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtStartStopLog;

/**
* 服务器启动与关闭日志; InnoDB free: 9216 kB; InnoDB free: 9216 kB 
* 2015-10-31 21:28:14  邓纯杰
*/
public interface XtStartStopLogDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtStartStopLog> getXtStartStopLogListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_start_stop_log_id 
	* @return
	*/
	public XtStartStopLog getXtStartStopLogById(String xt_start_stop_log_id);
	/**
	* 添加
	* @param xt_start_stop_log 
	* @return
	*/
	public int addXtStartStopLog(XtStartStopLog xt_Start_Stop_Log);
	/**
	* 修改
	* @param xt_start_stop_log 
	* @return
	*/
	public int updateXtStartStopLog(XtStartStopLog xt_Start_Stop_Log);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtStartStopLog(Map<String,Object> condition);
}
