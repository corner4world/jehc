package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsMessage;
import jehc.cmsmodules.cmsservice.CmsMessageService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;
import jehc.xtmodules.xtcore.util.UUID;

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
	@RequestMapping(value="/message.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsMessage(CmsMessage cmsMessage,HttpServletRequest request, Model model){
		model.addAttribute("title", "在线留言");
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-message/cms-message-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-message/cms-message-list");
		}
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
			cmsMessage.setCtime(getDate());
			cmsMessage.setCms_message_id(UUID.toUUID());
			i=cmsMessageService.addCmsMessage(cmsMessage);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
