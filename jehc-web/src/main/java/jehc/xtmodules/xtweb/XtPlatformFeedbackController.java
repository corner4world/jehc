package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
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
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;
import jehc.xtmodules.xtservice.XtPlatformFeedbackService;

/**
* 平台反馈意见 
* 2017-11-13 15:15:38  邓纯杰
*/
@Controller
@RequestMapping("/xtPlatformFeedbackController")
public class XtPlatformFeedbackController extends BaseAction{
	@Autowired
	private XtPlatformFeedbackService xtPlatformFeedbackService;
	/**
	* 载入初始化页面
	* @param xt_platform_feedback 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback,HttpServletRequest request){
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
		condition.put("xt_platform_id",request.getParameter("xt_platform_id"));
		List<XtPlatformFeedback> xtPlatformFeedbackList = xtPlatformFeedbackService.getXtPlatformFeedbackListByCondition(condition);
		PageInfo<XtPlatformFeedback> page = new PageInfo<XtPlatformFeedback>(xtPlatformFeedbackList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_platform_feedback_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformFeedbackById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformFeedbackById(String xt_platform_feedback_id,HttpServletRequest request){
		XtPlatformFeedback xtPlatformFeedback = xtPlatformFeedbackService.getXtPlatformFeedbackById(xt_platform_feedback_id);
		return outDataStr(xtPlatformFeedback);
	}
	/**
	* 添加
	* @param xt_platform_feedback 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtPlatformFeedback",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback,HttpServletRequest request){
		int i = 0;
		if(null != xtPlatformFeedback && !"".equals(xtPlatformFeedback)){
			xtPlatformFeedback.setXt_platform_feedback_id(UUID.toUUID());
			i=xtPlatformFeedbackService.addXtPlatformFeedback(xtPlatformFeedback);
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
	public String updateXtPlatformFeedback(XtPlatformFeedback xtPlatformFeedback,HttpServletRequest request){
		int i = 0;
		if(null != xtPlatformFeedback && !"".equals(xtPlatformFeedback)){
			i=xtPlatformFeedbackService.updateXtPlatformFeedback(xtPlatformFeedback);
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
			i=xtPlatformFeedbackService.delXtPlatformFeedback(condition);
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
		XtPlatformFeedback xtPlatformFeedback = xtPlatformFeedbackService.getXtPlatformFeedbackById(xt_platform_feedback_id);
		if(null != xtPlatformFeedback && !"".equals(xtPlatformFeedback)){
			xtPlatformFeedback.setXt_platform_feedback_id(UUID.toUUID());
			i=xtPlatformFeedbackService.addXtPlatformFeedback(xtPlatformFeedback);
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
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtPlatformFeedbackAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtPlatformFeedbackAdd(XtPlatformFeedback xtPlatformFeedback,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-platform-feedback/xt-platform-feedback-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtPlatformFeedbackUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtPlatformFeedbackUpdate(String xt_platform_feedback_id,HttpServletRequest request, Model model){
		XtPlatformFeedback xtPlatformFeedback = xtPlatformFeedbackService.getXtPlatformFeedbackById(xt_platform_feedback_id);
		model.addAttribute("xtPlatformFeedback", xtPlatformFeedback);
		return new ModelAndView("pc/xt-view/xt-platform-feedback/xt-platform-feedback-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtPlatformFeedbackDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtPlatformFeedbackDetail(String xt_platform_feedback_id,HttpServletRequest request, Model model){
		XtPlatformFeedback xtPlatformFeedback = xtPlatformFeedbackService.getXtPlatformFeedbackById(xt_platform_feedback_id);
		model.addAttribute("xtPlatformFeedback", xtPlatformFeedback);
		return new ModelAndView("pc/xt-view/xt-platform-feedback/xt-platform-feedback-detail");
	}
}
