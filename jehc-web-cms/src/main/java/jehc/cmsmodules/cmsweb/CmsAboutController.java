package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsAbout;
import jehc.cmsmodules.cmsservice.CmsAboutService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

/**
* 内容发布平台关于我们 
* 2018-06-10 17:56:24  邓纯杰
*/
@Controller
@RequestMapping("/cmsAboutController")
public class CmsAboutController extends BaseAction{
	@Autowired
	private CmsAboutService cmsAboutService;
	/**
	* 载入初始化页面
	* @param cms_about 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/about.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsAbout(CmsAbout cmsAbout,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-about/cms-about-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-about/cms-about-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsAboutDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsAboutDetail(String cms_about_id,HttpServletRequest request, Model model){
		CmsAbout cmsAbout = cmsAboutService.getCmsAboutById(cms_about_id);
		model.addAttribute("cmsAbout", cmsAbout);
		return new ModelAndView("pc/cms-view/cms-about/cms-about-detail");
	}
}
