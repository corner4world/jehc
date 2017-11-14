package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;

/**
* 平台反馈意见 
* 2017-11-13 15:15:38  邓纯杰
*/
public interface XtPlatformFeedbackDao{
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
	public int addXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback);
	/**
	* 修改
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback);
	/**
	* 修改（根据动态条件）
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedbackBySelective(XtPlatformFeedback xtPlatformFeedback);
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
	public int addBatchXtPlatformFeedback(List<XtPlatformFeedback> xtPlatformFeedbackList);
	/**
	* 批量修改
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedback(List<XtPlatformFeedback> xtPlatformFeedbackList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedbackBySelective(List<XtPlatformFeedback> xtPlatformFeedbackList);
	/**
	* 根据外键删除方法
	* @param xt_platform_id
	* @return
	*/
	public int delXtPlatformFeedbackByForeignKey(String xt_platform_id);
}
