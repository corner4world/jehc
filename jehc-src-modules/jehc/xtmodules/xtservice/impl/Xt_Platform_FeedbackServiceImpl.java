package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Platform_FeedbackDao;
import jehc.xtmodules.xtmodel.Xt_Platform_Feedback;
import jehc.xtmodules.xtservice.Xt_Platform_FeedbackService;

/**
* 平台反馈意见 
* 2016-09-23 22:32:55  邓纯杰
*/
@Service("xt_Platform_FeedbackService")
public class Xt_Platform_FeedbackServiceImpl extends BaseService implements Xt_Platform_FeedbackService{
	@Autowired
	private Xt_Platform_FeedbackDao xt_Platform_FeedbackDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Platform_Feedback> getXtPlatformFeedbackListByCondition(Map<String,Object> condition){
		try{
			return xt_Platform_FeedbackDao.getXtPlatformFeedbackListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_platform_feedback_id 
	* @return
	*/
	public Xt_Platform_Feedback getXtPlatformFeedbackById(String xt_platform_feedback_id){
		try{
			return xt_Platform_FeedbackDao.getXtPlatformFeedbackById(xt_platform_feedback_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_platform_feedback 
	* @return
	*/
	public int addXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback){
		int i = 0;
		try {
			i = xt_Platform_FeedbackDao.addXtPlatformFeedback(xt_Platform_Feedback);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback){
		int i = 0;
		try {
			i = xt_Platform_FeedbackDao.updateXtPlatformFeedback(xt_Platform_Feedback);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatformFeedback(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Platform_FeedbackDao.delXtPlatformFeedback(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int addBatchXtPlatformFeedback(List<Xt_Platform_Feedback> xt_Platform_FeedbackList){
		int i = 0;
		try {
			i = xt_Platform_FeedbackDao.addBatchXtPlatformFeedback(xt_Platform_FeedbackList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedback(List<Xt_Platform_Feedback> xt_Platform_FeedbackList){
		int i = 0;
		try {
			i = xt_Platform_FeedbackDao.updateBatchXtPlatformFeedback(xt_Platform_FeedbackList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
