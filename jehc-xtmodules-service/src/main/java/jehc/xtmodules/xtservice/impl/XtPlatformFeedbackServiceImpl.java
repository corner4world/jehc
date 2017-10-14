package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtPlatformFeedbackDao;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;
import jehc.xtmodules.xtservice.XtPlatformFeedbackService;

/**
* 平台反馈意见 
* 2016-09-23 22:32:55  邓纯杰
*/
@Service("xtPlatformFeedbackService")
public class XtPlatformFeedbackServiceImpl extends BaseService implements XtPlatformFeedbackService{
	@Autowired
	private XtPlatformFeedbackDao xtPlatformFeedbackDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtPlatformFeedback> getXtPlatformFeedbackListByCondition(Map<String,Object> condition){
		try{
			return xtPlatformFeedbackDao.getXtPlatformFeedbackListByCondition(condition);
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
	public XtPlatformFeedback getXtPlatformFeedbackById(String xt_platform_feedback_id){
		try{
			return xtPlatformFeedbackDao.getXtPlatformFeedbackById(xt_platform_feedback_id);
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
	public int addXtPlatformFeedback(XtPlatformFeedback xt_Platform_Feedback){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.addXtPlatformFeedback(xt_Platform_Feedback);
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
	public int updateXtPlatformFeedback(XtPlatformFeedback xt_Platform_Feedback){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.updateXtPlatformFeedback(xt_Platform_Feedback);
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
			i = xtPlatformFeedbackDao.delXtPlatformFeedback(condition);
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
	public int addBatchXtPlatformFeedback(List<XtPlatformFeedback> xt_Platform_FeedbackList){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.addBatchXtPlatformFeedback(xt_Platform_FeedbackList);
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
	public int updateBatchXtPlatformFeedback(List<XtPlatformFeedback> xt_Platform_FeedbackList){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.updateBatchXtPlatformFeedback(xt_Platform_FeedbackList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
