package jehc.oamodules.oaservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.oamodules.oaservice.OaTaskService;
import jehc.oamodules.oadao.OaTaskDao;
import jehc.oamodules.oamodel.OaTask;

/**
* 任务表 
* 2018-07-02 21:09:04  邓纯杰
*/
@Service("oaTaskService")
public class OaTaskServiceImpl extends BaseService implements OaTaskService{
	@Autowired
	private OaTaskDao oaTaskDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaTask> getOaTaskListByCondition(Map<String,Object> condition){
		try{
			return oaTaskDao.getOaTaskListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param oa_task_id 
	* @return
	*/
	public OaTask getOaTaskById(String oa_task_id){
		try{
			OaTask oaTask = oaTaskDao.getOaTaskById(oa_task_id);
			return oaTask;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param oa_task 
	* @return
	*/
	public int addOaTask(OaTask oaTask){
		int i = 0;
		try {
			i = oaTaskDao.addOaTask(oaTask);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param oa_task 
	* @return
	*/
	public int updateOaTask(OaTask oaTask){
		int i = 0;
		try {
			i = oaTaskDao.updateOaTask(oaTask);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param oa_task 
	* @return
	*/
	public int updateOaTaskBySelective(OaTask oaTask){
		int i = 0;
		try {
			i = oaTaskDao.updateOaTaskBySelective(oaTask);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaTask(Map<String,Object> condition){
		int i = 0;
		try {
			i = oaTaskDao.delOaTask(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param oa_taskList 
	* @return
	*/
	public int addBatchOaTask(List<OaTask> oaTaskList){
		int i = 0;
		try {
			i = oaTaskDao.addBatchOaTask(oaTaskList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param oa_taskList 
	* @return
	*/
	public int updateBatchOaTask(List<OaTask> oaTaskList){
		int i = 0;
		try {
			i = oaTaskDao.updateBatchOaTask(oaTaskList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_taskList 
	* @return
	*/
	public int updateBatchOaTaskBySelective(List<OaTask> oaTaskList){
		int i = 0;
		try {
			i = oaTaskDao.updateBatchOaTaskBySelective(oaTaskList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
