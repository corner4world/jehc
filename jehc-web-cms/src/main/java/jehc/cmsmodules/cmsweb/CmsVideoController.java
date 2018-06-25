package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsAbout;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

/**
* 视屏
* 2018-06-10 17:56:24  邓纯杰
*/
@Controller
@RequestMapping("/cmsvideoController")
public class CmsVideoController extends BaseAction{
	/**
	* 载入初始化页面
	* @param cms_about 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/video.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsAbout(CmsAbout cmsAbout,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-video/cms-video-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-video/cms-video-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsVideoDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsAboutDetail(String cms_about_id,HttpServletRequest request, Model model){
		return new ModelAndView("pc/cms-view/cms-video/cms-video-detail");
	}
}
