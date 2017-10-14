package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtQuartzLogDao;
import jehc.xtmodules.xtmodel.XtQuartzLog;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
@Repository("xtQuartzLogDao")
public class XtQuartzLogDaoImpl  extends BaseDaoImpl implements XtQuartzLogDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtQuartzLog> getXtQuartzLogListByCondition(Map<String,Object> condition){
		return (List<XtQuartzLog>)this.getList("getXtQuartzLogListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_quartz_log_id 
	* @return
	*/
	public XtQuartzLog getXtQuartzLogById(String xt_quartz_log_id){
		return (XtQuartzLog)this.get("getXtQuartzLogById", xt_quartz_log_id);
	}
	/**
	* 添加
	* @param xt_quartz_log 
	* @return
	*/
	public int addXtQuartzLog(XtQuartzLog xt_Quartz_Log){
		return this.add("addXtQuartzLog", xt_Quartz_Log);
	}
	/**
	* 修改
	* @param xt_quartz_log 
	* @return
	*/
	public int updateXtQuartzLog(XtQuartzLog xt_Quartz_Log){
		return this.update("updateXtQuartzLog", xt_Quartz_Log);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtQuartzLog(Map<String,Object> condition){
		return this.del("delXtQuartzLog", condition);
	}
}
