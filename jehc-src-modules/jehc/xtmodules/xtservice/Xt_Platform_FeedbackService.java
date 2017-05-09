package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Platform_Feedback;

/**
* 平台反馈意见 
* 2016-09-23 22:32:55  邓纯杰
*/
public interface Xt_Platform_FeedbackService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Platform_Feedback> getXtPlatformFeedbackListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_platform_feedback_id 
	* @return
	*/
	public Xt_Platform_Feedback getXtPlatformFeedbackById(String xt_platform_feedback_id);
	/**
	* 添加
	* @param xt_platform_feedback 
	* @return
	*/
	public int addXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback);
	/**
	* 修改
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatformFeedback(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int addBatchXtPlatformFeedback(List<Xt_Platform_Feedback> xt_Platform_FeedbackList);
	/**
	* 批量修改
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedback(List<Xt_Platform_Feedback> xt_Platform_FeedbackList);
}
