package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Operate_LogsDao;
import jehc.xtmodules.xtmodel.Xt_Operate_Logs;

/**
* 操作日志表; InnoDB free: 6144 kB 
* 2015-08-28 23:15:51  邓纯杰
*/
@Repository("xt_Operate_LogsDao")
public class Xt_Operate_LogsDaoImpl  extends BaseDaoImpl implements Xt_Operate_LogsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Operate_Logs> getXtOperateLogsListByCondition(Map<String,Object> condition){
		return (List<Xt_Operate_Logs>)this.getList("getXtOperateLogsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_operate_log_id 
	* @return
	*/
	public Xt_Operate_Logs getXtOperateLogsById(String xt_operate_log_id){
		return (Xt_Operate_Logs)this.get("getXtOperateLogsById", xt_operate_log_id);
	}
	/**
	* 添加
	* @param xt_operate_logs 
	* @return
	*/
	public int addXtOperateLogs(Xt_Operate_Logs xt_Operate_Logs){
		return this.add("addXtOperateLogs", xt_Operate_Logs);
	}
	/**
	* 修改
	* @param xt_operate_logs 
	* @return
	*/
	public int updateXtOperateLogs(Xt_Operate_Logs xt_Operate_Logs){
		return this.update("updateXtOperateLogs", xt_Operate_Logs);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtOperateLogs(Map<String,Object> condition){
		return this.del("delXtOperateLogs", condition);
	}
}
