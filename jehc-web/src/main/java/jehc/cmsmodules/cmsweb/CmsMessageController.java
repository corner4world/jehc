package jehc.cmsmodules.cmsweb;
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
import jehc.cmsmodules.cmsmodel.CmsMessage;
import jehc.cmsmodules.cmsservice.CmsMessageService;

/**
* 内容发布平台在线留言 
* 2018-06-10 14:51:17  邓纯杰
*/
@Controller
@RequestMapping("/cmsMessageController")
public class CmsMessageController extends BaseAction{
	@Autowired
	private CmsMessageService cmsMessageService;
	/**
	* 载入初始化页面
	* @param cms_message 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsMessage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsMessage(CmsMessage cmsMessage,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-message/cms-message-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsMessageListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsMessageListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsMessage> cmsMessageList = cmsMessageService.getCmsMessageListByCondition(condition);
		PageInfo<CmsMessage> page = new PageInfo<CmsMessage>(cmsMessageList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsMessageById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsMessageById(String cms_message_id,HttpServletRequest request){
		CmsMessage cmsMessage = cmsMessageService.getCmsMessageById(cms_message_id);
		return outDataStr(cmsMessage);
	}
	/**
	* 添加
	* @param cms_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsMessage(CmsMessage cmsMessage,HttpServletRequest request){
		int i = 0;
		if(null != cmsMessage && !"".equals(cmsMessage)){
			cmsMessage.setCms_message_id(UUID.toUUID());
			i=cmsMessageService.addCmsMessage(cmsMessage);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_message 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsMessage(CmsMessage cmsMessage,HttpServletRequest request){
		int i = 0;
		if(null != cmsMessage && !"".equals(cmsMessage)){
			i=cmsMessageService.updateCmsMessage(cmsMessage);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsMessage(String cms_message_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_message_id && !"".equals(cms_message_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_message_id",cms_message_id.split(","));
			i=cmsMessageService.delCmsMessage(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_message_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsMessage",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsMessage(String cms_message_id,HttpServletRequest request){
		int i = 0;
		CmsMessage cmsMessage = cmsMessageService.getCmsMessageById(cms_message_id);
		if(null != cmsMessage && !"".equals(cmsMessage)){
			cmsMessage.setCms_message_id(UUID.toUUID());
			i=cmsMessageService.addCmsMessage(cmsMessage);
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
	@RequestMapping(value="/exportCmsMessage",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsMessage(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMessageAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMessageAdd(CmsMessage cmsMessage,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-message/cms-message-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMessageUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMessageUpdate(String cms_message_id,HttpServletRequest request, Model model){
		CmsMessage cmsMessage = cmsMessageService.getCmsMessageById(cms_message_id);
		model.addAttribute("cmsMessage", cmsMessage);
		return new ModelAndView("pc/cms-view/cms-message/cms-message-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMessageDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMessageDetail(String cms_message_id,HttpServletRequest request, Model model){
		CmsMessage cmsMessage = cmsMessageService.getCmsMessageById(cms_message_id);
		model.addAttribute("cmsMessage", cmsMessage);
		return new ModelAndView("pc/cms-view/cms-message/cms-message-detail");
	}
}
