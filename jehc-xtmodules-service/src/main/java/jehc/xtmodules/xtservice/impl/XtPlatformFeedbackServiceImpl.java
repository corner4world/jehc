package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.XtPlatformFeedbackService;
import jehc.xtmodules.xtdao.XtPlatformFeedbackDao;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;

/**
* 平台反馈意见 
* 2017-11-13 15:15:38  邓纯杰
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
			XtPlatformFeedback xtPlatformFeedback = xtPlatformFeedbackDao.getXtPlatformFeedbackById(xt_platform_feedback_id);
			return xtPlatformFeedback;
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
	public int addXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.addXtPlatformFeedback(xtPlatformFeedback);
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
	public int updateXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.updateXtPlatformFeedback(xtPlatformFeedback);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedbackBySelective(XtPlatformFeedback xtPlatformFeedback){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.updateXtPlatformFeedbackBySelective(xtPlatformFeedback);
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
	public int addBatchXtPlatformFeedback(List<XtPlatformFeedback> xtPlatformFeedbackList){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.addBatchXtPlatformFeedback(xtPlatformFeedbackList);
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
	public int updateBatchXtPlatformFeedback(List<XtPlatformFeedback> xtPlatformFeedbackList){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.updateBatchXtPlatformFeedback(xtPlatformFeedbackList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedbackBySelective(List<XtPlatformFeedback> xtPlatformFeedbackList){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.updateBatchXtPlatformFeedbackBySelective(xtPlatformFeedbackList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 根据外键删除方法
	* @param xt_platform_id
	* @return
	*/
	public int delXtPlatformFeedbackByForeignKey(String xt_platform_id){
		int i = 0;
		try {
			i = xtPlatformFeedbackDao.delXtPlatformFeedbackByForeignKey(xt_platform_id);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
