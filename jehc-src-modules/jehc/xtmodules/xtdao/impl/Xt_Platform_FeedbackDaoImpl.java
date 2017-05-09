package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Platform_FeedbackDao;
import jehc.xtmodules.xtmodel.Xt_Platform_Feedback;

/**
* 平台反馈意见 
* 2016-09-23 22:32:55  邓纯杰
*/
@Repository("xt_Platform_FeedbackDao")
public class Xt_Platform_FeedbackDaoImpl  extends BaseDaoImpl implements Xt_Platform_FeedbackDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Platform_Feedback> getXtPlatformFeedbackListByCondition(Map<String,Object> condition){
		return (List<Xt_Platform_Feedback>)this.getList("getXtPlatformFeedbackListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_platform_feedback_id 
	* @return
	*/
	public Xt_Platform_Feedback getXtPlatformFeedbackById(String xt_platform_feedback_id){
		return (Xt_Platform_Feedback)this.get("getXtPlatformFeedbackById", xt_platform_feedback_id);
	}
	/**
	* 添加
	* @param xt_platform_feedback 
	* @return
	*/
	public int addXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback){
		return this.add("addXtPlatformFeedback", xt_Platform_Feedback);
	}
	/**
	* 修改
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback){
		return this.update("updateXtPlatformFeedback", xt_Platform_Feedback);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatformFeedback(Map<String,Object> condition){
		return this.del("delXtPlatformFeedback", condition);
	}
	/**
	* 批量添加
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int addBatchXtPlatformFeedback(List<Xt_Platform_Feedback> xt_Platform_FeedbackList){
		return this.add("addBatchXtPlatformFeedback", xt_Platform_FeedbackList);
	}
	/**
	* 批量修改
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedback(List<Xt_Platform_Feedback> xt_Platform_FeedbackList){
		return this.update("updateBatchXtPlatformFeedback", xt_Platform_FeedbackList);
	}
}
