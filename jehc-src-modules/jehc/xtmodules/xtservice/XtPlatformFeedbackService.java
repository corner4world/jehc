package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtPlatformFeedback;

/**
* 平台反馈意见 
* 2016-09-23 22:32:55  邓纯杰
*/
public interface XtPlatformFeedbackService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtPlatformFeedback> getXtPlatformFeedbackListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_platform_feedback_id 
	* @return
	*/
	public XtPlatformFeedback getXtPlatformFeedbackById(String xt_platform_feedback_id);
	/**
	* 添加
	* @param xt_platform_feedback 
	* @return
	*/
	public int addXtPlatformFeedback(XtPlatformFeedback xt_Platform_Feedback);
	/**
	* 修改
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedback(XtPlatformFeedback xt_Platform_Feedback);
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
	public int addBatchXtPlatformFeedback(List<XtPlatformFeedback> xt_Platform_FeedbackList);
	/**
	* 批量修改
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedback(List<XtPlatformFeedback> xt_Platform_FeedbackList);
}
