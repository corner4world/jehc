package jehc.xtmodules.xtservice.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.FastJsonUtils;
import jehc.xtmodules.xtdao.XtPlatformDao;
import jehc.xtmodules.xtmodel.XtPlatform;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;
import jehc.xtmodules.xtservice.XtPlatformService;
import jehc.xtmodules.xtservice.XtPlatformFeedbackService;

/**
* 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
*/
@Service("xtPlatformService")
public class XtPlatformServiceImpl extends BaseService implements XtPlatformService{
	@Autowired
	private XtPlatformDao xtPlatformDao;
	@Autowired
	private XtPlatformFeedbackService xtPlatformFeedbackService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtPlatform> getXtPlatformListByCondition(Map<String,Object> condition){
		try{
			return xtPlatformDao.getXtPlatformListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_platform_id 
	* @return
	*/
	public XtPlatform getXtPlatformById(String xt_platform_id){
		try{
			return xtPlatformDao.getXtPlatformById(xt_platform_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_platform 
	* @return
	*/
	public int addXtPlatform(XtPlatform xt_Platform){
		int i = 0;
		try {
			i= xtPlatformDao.addXtPlatform(xt_Platform);
			List<XtPlatformFeedback> xt_Platform_FeedbackList = FastJsonUtils.toList(xt_Platform.getXt_Platform_FeedbackList(), XtPlatformFeedback.class);
			for(int j = 0; j < xt_Platform_FeedbackList.size(); j++){
				xt_Platform_FeedbackList.get(j).setXt_platform_feedback_id(toUUID());
				xt_Platform_FeedbackList.get(j).setXt_platform_id(xt_Platform.getXt_platform_id());
				xt_Platform_FeedbackList.get(j).setXt_userinfo_id(xt_Platform.getXt_userinfo_id());
				xt_Platform_FeedbackList.get(j).setXt_platform_feedback_ctime(getSimpleDateFormat());
			}
			xtPlatformFeedbackService.addBatchXtPlatformFeedback(xt_Platform_FeedbackList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatform(XtPlatform xt_Platform){
		int i = 0;
		try {
			i = xtPlatformDao.updateXtPlatform(xt_Platform);
			List<XtPlatformFeedback> xt_Platform_FeedbackList = FastJsonUtils.toList(xt_Platform.getXt_Platform_FeedbackList(), XtPlatformFeedback.class);
			List<XtPlatformFeedback> xt_Platform_FeedbackAddList = new ArrayList<XtPlatformFeedback>();
			List<XtPlatformFeedback> xt_Platform_FeedbackUpdateList = new ArrayList<XtPlatformFeedback>();
			for(int j = 0; j < xt_Platform_FeedbackList.size(); j++){
				XtPlatformFeedback xt_Platform_Feedback = xt_Platform_FeedbackList.get(j);
				xt_Platform_Feedback.setXt_platform_feedback_ctime(getSimpleDateFormat());
				xt_Platform_Feedback.setXt_userinfo_id(xt_Platform.getXt_userinfo_id());
				if(StringUtils.isEmpty(xt_Platform_Feedback.getXt_platform_feedback_id())){
					xt_Platform_Feedback.setXt_platform_feedback_id(toUUID());
					xt_Platform_Feedback.setXt_platform_id(xt_Platform.getXt_platform_id());
					xt_Platform_FeedbackAddList.add(xt_Platform_Feedback);
				}else{
					xt_Platform_FeedbackUpdateList.add(xt_Platform_Feedback);
				}
			}
			xtPlatformFeedbackService.addBatchXtPlatformFeedback(xt_Platform_FeedbackList);
			xtPlatformFeedbackService.updateBatchXtPlatformFeedback(xt_Platform_FeedbackList);
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
	public int delXtPlatform(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtPlatformDao.delXtPlatform(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
