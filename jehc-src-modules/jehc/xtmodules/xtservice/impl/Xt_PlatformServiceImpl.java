package jehc.xtmodules.xtservice.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.FastJsonUtils;
import jehc.xtmodules.xtdao.Xt_PlatformDao;
import jehc.xtmodules.xtmodel.Xt_Platform;
import jehc.xtmodules.xtmodel.Xt_Platform_Feedback;
import jehc.xtmodules.xtservice.Xt_PlatformService;
import jehc.xtmodules.xtservice.Xt_Platform_FeedbackService;

/**
* 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
*/
@Service("xt_PlatformService")
public class Xt_PlatformServiceImpl extends BaseService implements Xt_PlatformService{
	@Autowired
	private Xt_PlatformDao xt_PlatformDao;
	@Autowired
	private Xt_Platform_FeedbackService xt_Platform_FeedbackService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Platform> getXtPlatformListByCondition(Map<String,Object> condition){
		try{
			return xt_PlatformDao.getXtPlatformListByCondition(condition);
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
	public Xt_Platform getXtPlatformById(String xt_platform_id){
		try{
			return xt_PlatformDao.getXtPlatformById(xt_platform_id);
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
	public int addXtPlatform(Xt_Platform xt_Platform){
		int i = 0;
		try {
			i= xt_PlatformDao.addXtPlatform(xt_Platform);
			List<Xt_Platform_Feedback> xt_Platform_FeedbackList = FastJsonUtils.toList(xt_Platform.getXt_Platform_FeedbackList(), Xt_Platform_Feedback.class);
			for(int j = 0; j < xt_Platform_FeedbackList.size(); j++){
				xt_Platform_FeedbackList.get(j).setXt_platform_feedback_id(toUUID());
				xt_Platform_FeedbackList.get(j).setXt_platform_id(xt_Platform.getXt_platform_id());
				xt_Platform_FeedbackList.get(j).setXt_userinfo_id(xt_Platform.getXt_userinfo_id());
				xt_Platform_FeedbackList.get(j).setXt_platform_feedback_ctime(getSimpleDateFormat());
			}
			xt_Platform_FeedbackService.addBatchXtPlatformFeedback(xt_Platform_FeedbackList);
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
	public int updateXtPlatform(Xt_Platform xt_Platform){
		int i = 0;
		try {
			i = xt_PlatformDao.updateXtPlatform(xt_Platform);
			List<Xt_Platform_Feedback> xt_Platform_FeedbackList = FastJsonUtils.toList(xt_Platform.getXt_Platform_FeedbackList(), Xt_Platform_Feedback.class);
			List<Xt_Platform_Feedback> xt_Platform_FeedbackAddList = new ArrayList<Xt_Platform_Feedback>();
			List<Xt_Platform_Feedback> xt_Platform_FeedbackUpdateList = new ArrayList<Xt_Platform_Feedback>();
			for(int j = 0; j < xt_Platform_FeedbackList.size(); j++){
				Xt_Platform_Feedback xt_Platform_Feedback = xt_Platform_FeedbackList.get(j);
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
			xt_Platform_FeedbackService.addBatchXtPlatformFeedback(xt_Platform_FeedbackList);
			xt_Platform_FeedbackService.updateBatchXtPlatformFeedback(xt_Platform_FeedbackList);
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
			i = xt_PlatformDao.delXtPlatform(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
