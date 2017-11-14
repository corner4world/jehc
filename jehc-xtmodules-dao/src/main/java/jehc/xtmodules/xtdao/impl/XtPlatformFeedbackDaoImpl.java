package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtPlatformFeedbackDao;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;

/**
* 平台反馈意见 
* 2017-11-13 15:15:38  邓纯杰
*/
@Repository("xtPlatformFeedbackDao")
public class XtPlatformFeedbackDaoImpl  extends BaseDaoImpl implements XtPlatformFeedbackDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtPlatformFeedback> getXtPlatformFeedbackListByCondition(Map<String,Object> condition){
		return (List<XtPlatformFeedback>)this.getList("getXtPlatformFeedbackListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_platform_feedback_id 
	* @return
	*/
	public XtPlatformFeedback getXtPlatformFeedbackById(String xt_platform_feedback_id){
		return (XtPlatformFeedback)this.get("getXtPlatformFeedbackById", xt_platform_feedback_id);
	}
	/**
	* 添加
	* @param xt_platform_feedback 
	* @return
	*/
	public int addXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback){
		return this.add("addXtPlatformFeedback", xtPlatformFeedback);
	}
	/**
	* 修改
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback){
		return this.update("updateXtPlatformFeedback", xtPlatformFeedback);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_platform_feedback 
	* @return
	*/
	public int updateXtPlatformFeedbackBySelective(XtPlatformFeedback xtPlatformFeedback){
		return this.update("updateXtPlatformFeedbackBySelective", xtPlatformFeedback);
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
	public int addBatchXtPlatformFeedback(List<XtPlatformFeedback> xtPlatformFeedbackList){
		return this.add("addBatchXtPlatformFeedback", xtPlatformFeedbackList);
	}
	/**
	* 批量修改
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedback(List<XtPlatformFeedback> xtPlatformFeedbackList){
		return this.update("updateBatchXtPlatformFeedback", xtPlatformFeedbackList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_platform_feedbackList 
	* @return
	*/
	public int updateBatchXtPlatformFeedbackBySelective(List<XtPlatformFeedback> xtPlatformFeedbackList){
		return this.update("updateBatchXtPlatformFeedbackBySelective", xtPlatformFeedbackList);
	}
	/**
	* 根据外键删除方法
	* @param xt_platform_id
	* @return
	*/
	public int delXtPlatformFeedbackByForeignKey(String xt_platform_id){
		return this.del("delXtPlatformFeedbackByForeignKey", xt_platform_id);
	}
}
