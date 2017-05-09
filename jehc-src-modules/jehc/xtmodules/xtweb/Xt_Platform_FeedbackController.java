package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Platform_Feedback;
import jehc.xtmodules.xtservice.Xt_Platform_FeedbackService;

/**
* 平台反馈意见 
* 2016-08-30 22:22:10  邓纯杰
*/
@Controller
@RequestMapping("/xtPlatformFeedbackController")
public class Xt_Platform_FeedbackController extends BaseAction{
	@Autowired
	private Xt_Platform_FeedbackService xt_Platform_FeedbackService;
	/**
	* 载入初始化页面
	* @param xt_platform_feedback 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-platform-feedback/xt-platform-feedback-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_platform_feedback 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformFeedbackListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformFeedbackListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		condition.put("xt_platform_id", request.getParameter("xt_Platform_Feedback"));
		List<Xt_Platform_Feedback> xt_Platform_FeedbackList = xt_Platform_FeedbackService.getXtPlatformFeedbackListByCondition(condition);
		PageInfo<Xt_Platform_Feedback> page = new PageInfo<Xt_Platform_Feedback>(xt_Platform_FeedbackList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_platform_feedback_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformFeedbackById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformFeedbackById(String xt_platform_feedback_id,HttpServletRequest request){
		Xt_Platform_Feedback xt_Platform_Feedback = xt_Platform_FeedbackService.getXtPlatformFeedbackById(xt_platform_feedback_id);
		return outDataStr(xt_Platform_Feedback);
	}
	/**
	* 添加
	* @param xt_platform_feedback 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback,HttpServletRequest request){
		int i = 0;
		if(null != xt_Platform_Feedback && !"".equals(xt_Platform_Feedback)){
			xt_Platform_Feedback.setXt_platform_feedback_id(UUID.toUUID());
			i=xt_Platform_FeedbackService.addXtPlatformFeedback(xt_Platform_Feedback);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_platform_feedback 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtPlatformFeedback(Xt_Platform_Feedback xt_Platform_Feedback,HttpServletRequest request){
		int i = 0;
		if(null != xt_Platform_Feedback && !"".equals(xt_Platform_Feedback)){
			i=xt_Platform_FeedbackService.updateXtPlatformFeedback(xt_Platform_Feedback);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_platform_feedback_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtPlatformFeedback(String xt_platform_feedback_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_platform_feedback_id && !"".equals(xt_platform_feedback_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_platform_feedback_id",xt_platform_feedback_id.split(","));
			i=xt_Platform_FeedbackService.delXtPlatformFeedback(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_platform_feedback_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtPlatformFeedback(String xt_platform_feedback_id,HttpServletRequest request){
		int i = 0;
		Xt_Platform_Feedback xt_Platform_Feedback = xt_Platform_FeedbackService.getXtPlatformFeedbackById(xt_platform_feedback_id);
		if(null != xt_Platform_Feedback && !"".equals(xt_Platform_Feedback)){
			xt_Platform_Feedback.setXt_platform_feedback_id(UUID.toUUID());
			i=xt_Platform_FeedbackService.addXtPlatformFeedback(xt_Platform_Feedback);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtPlatformFeedback(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
