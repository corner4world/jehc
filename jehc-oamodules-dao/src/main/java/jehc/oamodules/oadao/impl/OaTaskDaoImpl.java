package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaTaskDao;
import jehc.oamodules.oamodel.OaTask;

/**
* 任务表 
* 2018-07-02 21:09:04  邓纯杰
*/
@Repository("oaTaskDao")
public class OaTaskDaoImpl  extends BaseDaoImpl implements OaTaskDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaTask> getOaTaskListByCondition(Map<String,Object> condition){
		return (List<OaTask>)this.getList("getOaTaskListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_task_id 
	* @return
	*/
	public OaTask getOaTaskById(String oa_task_id){
		return (OaTask)this.get("getOaTaskById", oa_task_id);
	}
	/**
	* 添加
	* @param oa_task 
	* @return
	*/
	public int addOaTask(OaTask oaTask){
		return this.add("addOaTask", oaTask);
	}
	/**
	* 修改
	* @param oa_task 
	* @return
	*/
	public int updateOaTask(OaTask oaTask){
		return this.update("updateOaTask", oaTask);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_task 
	* @return
	*/
	public int updateOaTaskBySelective(OaTask oaTask){
		return this.update("updateOaTaskBySelective", oaTask);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaTask(Map<String,Object> condition){
		return this.del("delOaTask", condition);
	}
	/**
	* 批量添加
	* @param oa_taskList 
	* @return
	*/
	public int addBatchOaTask(List<OaTask> oaTaskList){
		return this.add("addBatchOaTask", oaTaskList);
	}
	/**
	* 批量修改
	* @param oa_taskList 
	* @return
	*/
	public int updateBatchOaTask(List<OaTask> oaTaskList){
		return this.update("updateBatchOaTask", oaTaskList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_taskList 
	* @return
	*/
	public int updateBatchOaTaskBySelective(List<OaTask> oaTaskList){
		return this.update("updateBatchOaTaskBySelective", oaTaskList);
	}
}
