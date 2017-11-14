package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import jehc.xtmodules.xtmodel.XtPlatformFeedback;
import jehc.xtmodules.xtservice.XtPlatformFeedbackService;
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
import jehc.xtmodules.xtmodel.XtPlatform;
import jehc.xtmodules.xtservice.XtPlatformService;

/**
* 平台信息发布 
* 2017-11-13 15:15:38  邓纯杰
*/
@Controller
@RequestMapping("/xtPlatformController")
public class XtPlatformController extends BaseAction{
	@Autowired
	private XtPlatformService xtPlatformService;
	@Autowired
	private XtPlatformFeedbackService xtPlatformFeedbackService;
	/**
	* 载入初始化页面
	* @param xt_platform 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtPlatform(XtPlatform xtPlatform,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-platform/xt-platform-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_platform 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtPlatform> xtPlatformList = xtPlatformService.getXtPlatformListByCondition(condition);
		PageInfo<XtPlatform> page = new PageInfo<XtPlatform>(xtPlatformList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_platform_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPlatformById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPlatformById(String xt_platform_id,HttpServletRequest request){
		XtPlatform xtPlatform = xtPlatformService.getXtPlatformById(xt_platform_id);
		return outDataStr(xtPlatform);
	}
	/**
	* 添加
	* @param xt_platform 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtPlatform(XtPlatform xtPlatform,HttpServletRequest request){
		int i = 0;
		if(null != xtPlatform && !"".equals(xtPlatform)){
			xtPlatform.setXt_platform_id(UUID.toUUID());
			i=xtPlatformService.addXtPlatform(xtPlatform);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_platform 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtPlatform(XtPlatform xtPlatform,HttpServletRequest request){
		int i = 0;
		if(null != xtPlatform && !"".equals(xtPlatform)){
			i=xtPlatformService.updateXtPlatform(xtPlatform);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_platform_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtPlatform(String xt_platform_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_platform_id && !"".equals(xt_platform_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_platform_id",xt_platform_id.split(","));
			i=xtPlatformService.delXtPlatform(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_platform_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtPlatform(String xt_platform_id,HttpServletRequest request){
		int i = 0;
		XtPlatform xtPlatform = xtPlatformService.getXtPlatformById(xt_platform_id);
		if(null != xtPlatform && !"".equals(xtPlatform)){
			xtPlatform.setXt_platform_id(UUID.toUUID());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_platform_id", xt_platform_id);
			List<XtPlatformFeedback> xtPlatformFeedbackList = xtPlatformFeedbackService.getXtPlatformFeedbackListByCondition(condition);
			xtPlatform.setXtPlatformFeedback(xtPlatformFeedbackList);
			i=xtPlatformService.addXtPlatform(xtPlatform);
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
	@RequestMapping(value="/exportXtPlatform",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtPlatform(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtPlatformAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtPlatformAdd(XtPlatform xtPlatform,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-platform/xt-platform-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtPlatformUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtPlatformUpdate(String xt_platform_id,HttpServletRequest request, Model model){
		XtPlatform xtPlatform = xtPlatformService.getXtPlatformById(xt_platform_id);
		model.addAttribute("xtPlatform", xtPlatform);
		return new ModelAndView("pc/xt-view/xt-platform/xt-platform-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtPlatformDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtPlatformDetail(String xt_platform_id,HttpServletRequest request, Model model){
		XtPlatform xtPlatform = xtPlatformService.getXtPlatformById(xt_platform_id);
		model.addAttribute("xtPlatform", xtPlatform);
		return new ModelAndView("pc/xt-view/xt-platform/xt-platform-detail");
	}
}
