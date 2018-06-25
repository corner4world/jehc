package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsMessage;
import jehc.cmsmodules.cmsservice.CmsMessageService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

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
	public ModelAndView loadCmsMessage(CmsMessage cmsMessage,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-message/cms-message-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-message/cms-message-list");
		}
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
