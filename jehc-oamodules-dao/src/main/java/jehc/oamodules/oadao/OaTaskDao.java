package jehc.oamodules.oadao;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaTask;

/**
* 任务表 
* 2018-07-02 21:09:04  邓纯杰
*/
public interface OaTaskDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaTask> getOaTaskListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_task_id 
	* @return
	*/
	public OaTask getOaTaskById(String oa_task_id);
	/**
	* 添加
	* @param oa_task 
	* @return
	*/
	public int addOaTask(OaTask oaTask);
	/**
	* 修改
	* @param oa_task 
	* @return
	*/
	public int updateOaTask(OaTask oaTask);
	/**
	* 修改（根据动态条件）
	* @param oa_task 
	* @return
	*/
	public int updateOaTaskBySelective(OaTask oaTask);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaTask(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_taskList 
	* @return
	*/
	public int addBatchOaTask(List<OaTask> oaTaskList);
	/**
	* 批量修改
	* @param oa_taskList 
	* @return
	*/
	public int updateBatchOaTask(List<OaTask> oaTaskList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_taskList 
	* @return
	*/
	public int updateBatchOaTaskBySelective(List<OaTask> oaTaskList);
}
