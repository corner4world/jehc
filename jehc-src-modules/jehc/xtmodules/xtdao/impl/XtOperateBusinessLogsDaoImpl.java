package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtOperateBusinessLogsDao;
import jehc.xtmodules.xtmodel.XtOperateBusinessLogs;

/**
* 平台业务操作日志表 
* 2016-09-19 23:24:17  邓纯杰
*/
@Repository("xtOperateBusinessLogsDao")
public class XtOperateBusinessLogsDaoImpl  extends BaseDaoImpl implements XtOperateBusinessLogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtOperateBusinessLogs> getXtOperateBusinessLogsListByCondition(Map<String,Object> condition){
		return (List<XtOperateBusinessLogs>)this.getList("getXtOperateBusinessLogsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_operate_business_logs_id 
	* @return
	*/
	public XtOperateBusinessLogs getXtOperateBusinessLogsById(String xt_operate_business_logs_id){
		return (XtOperateBusinessLogs)this.get("getXtOperateBusinessLogsById", xt_operate_business_logs_id);
	}
	/**
	* 添加
	* @param xt_operate_business_logs 
	* @return
	*/
	public int addXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs){
		return this.add("addXtOperateBusinessLogs", xt_Operate_Business_Logs);
	}
	/**
	* 修改
	* @param xt_operate_business_logs 
	* @return
	*/
	public int updateXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs){
		return this.update("updateXtOperateBusinessLogs", xt_Operate_Business_Logs);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtOperateBusinessLogs(Map<String,Object> condition){
		return this.del("delXtOperateBusinessLogs", condition);
	}
}
