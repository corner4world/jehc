package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;
import jehc.xtmodules.xtservice.XtPlatformFeedbackService;
import java.util.HashMap;
import java.util.ArrayList;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtservice.XtPlatformService;
import jehc.xtmodules.xtdao.XtPlatformDao;
import jehc.xtmodules.xtmodel.XtPlatform;

/**
* 平台信息发布 
* 2017-11-13 15:15:38  邓纯杰
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
			XtPlatform xtPlatform = xtPlatformDao.getXtPlatformById(xt_platform_id);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_platform_id", xt_platform_id);
			List<XtPlatformFeedback> xtPlatformFeedback = xtPlatformFeedbackService.getXtPlatformFeedbackListByCondition(condition);
			xtPlatform.setXtPlatformFeedback(xtPlatformFeedback);
			return xtPlatform;
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
	public int addXtPlatform(XtPlatform xtPlatform){
		int i = 0;
		try {
			i = xtPlatformDao.addXtPlatform(xtPlatform);
			List<XtPlatformFeedback> xtPlatformFeedbackTempList = xtPlatform.getXtPlatformFeedback();
			List<XtPlatformFeedback> xtPlatformFeedbackList = new ArrayList<XtPlatformFeedback>();
			for(int j = 0; j < xtPlatformFeedbackTempList.size(); j++){
				if(xtPlatform.getXtPlatformFeedback_removed_flag().indexOf(","+j+",") == -1){
					xtPlatformFeedbackTempList.get(j).setXt_platform_id(xtPlatform.getXt_platform_id());
					xtPlatformFeedbackTempList.get(j).setXt_platform_feedback_id(toUUID());
					xtPlatformFeedbackList.add(xtPlatformFeedbackTempList.get(j));
				}
			}
			if(!xtPlatformFeedbackList.isEmpty()&&xtPlatformFeedbackList.size()>0){
				xtPlatformFeedbackService.addBatchXtPlatformFeedback(xtPlatformFeedbackList);
			}
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
	public int updateXtPlatform(XtPlatform xtPlatform){
		int i = 0;
		try {
			i = xtPlatformDao.updateXtPlatform(xtPlatform);
			List<XtPlatformFeedback> xtPlatformFeedbackList = xtPlatform.getXtPlatformFeedback();
			List<XtPlatformFeedback> xtPlatformFeedbackAddList = new ArrayList<XtPlatformFeedback>();
			List<XtPlatformFeedback> xtPlatformFeedbackUpdateList = new ArrayList<XtPlatformFeedback>();
			for(int j = 0; j < xtPlatformFeedbackList.size(); j++){
				if(xtPlatform.getXtPlatformFeedback_removed_flag().indexOf(","+j+",") == -1){
					xtPlatformFeedbackList.get(j).setXt_platform_id(xtPlatform.getXt_platform_id());
					if(StringUtil.isEmpty(xtPlatformFeedbackList.get(j).getXt_platform_feedback_id())){
						xtPlatformFeedbackList.get(j).setXt_platform_feedback_id(toUUID());
						xtPlatformFeedbackAddList.add(xtPlatformFeedbackList.get(j));
					}else{
						xtPlatformFeedbackUpdateList.add(xtPlatformFeedbackList.get(j));
					}
				}
			}
			if(!xtPlatformFeedbackAddList.isEmpty()&&xtPlatformFeedbackAddList.size()>0){
				xtPlatformFeedbackService.addBatchXtPlatformFeedback(xtPlatformFeedbackAddList);
			}
			if(!xtPlatformFeedbackUpdateList.isEmpty()&&xtPlatformFeedbackUpdateList.size()>0){
				xtPlatformFeedbackService.updateBatchXtPlatformFeedback(xtPlatformFeedbackUpdateList);
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatformBySelective(XtPlatform xtPlatform){
		int i = 0;
		try {
			i = xtPlatformDao.updateXtPlatformBySelective(xtPlatform);
			List<XtPlatformFeedback> xtPlatformFeedbackList = xtPlatform.getXtPlatformFeedback();
			List<XtPlatformFeedback> xtPlatformFeedbackAddList = new ArrayList<XtPlatformFeedback>();
			List<XtPlatformFeedback> xtPlatformFeedbackUpdateList = new ArrayList<XtPlatformFeedback>();
			for(int j = 0; j < xtPlatformFeedbackList.size(); j++){
				if(xtPlatform.getXtPlatformFeedback_removed_flag().indexOf(","+j+",") == -1){
					xtPlatformFeedbackList.get(j).setXt_platform_id(xtPlatform.getXt_platform_id());
					if(StringUtil.isEmpty(xtPlatformFeedbackList.get(j).getXt_platform_feedback_id())){
						xtPlatformFeedbackList.get(j).setXt_platform_feedback_id(toUUID());
						xtPlatformFeedbackAddList.add(xtPlatformFeedbackList.get(j));
					}else{
						xtPlatformFeedbackUpdateList.add(xtPlatformFeedbackList.get(j));
					}
				}
			}
			if(!xtPlatformFeedbackAddList.isEmpty()&&xtPlatformFeedbackAddList.size()>0){
				xtPlatformFeedbackService.addBatchXtPlatformFeedback(xtPlatformFeedbackAddList);
			}
			if(!xtPlatformFeedbackUpdateList.isEmpty()&&xtPlatformFeedbackUpdateList.size()>0){
				xtPlatformFeedbackService.updateBatchXtPlatformFeedbackBySelective(xtPlatformFeedbackUpdateList);
			}
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
			String[] xt_platform_idList= (String[])condition.get("xt_platform_id");
			for(String xt_platform_id:xt_platform_idList){
				xtPlatformFeedbackService.delXtPlatformFeedbackByForeignKey(xt_platform_id);
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_platformList 
	* @return
	*/
	public int addBatchXtPlatform(List<XtPlatform> xtPlatformList){
		int i = 0;
		try {
			i = xtPlatformDao.addBatchXtPlatform(xtPlatformList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_platformList 
	* @return
	*/
	public int updateBatchXtPlatform(List<XtPlatform> xtPlatformList){
		int i = 0;
		try {
			i = xtPlatformDao.updateBatchXtPlatform(xtPlatformList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_platformList 
	* @return
	*/
	public int updateBatchXtPlatformBySelective(List<XtPlatform> xtPlatformList){
		int i = 0;
		try {
			i = xtPlatformDao.updateBatchXtPlatformBySelective(xtPlatformList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
