package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsCase;
import jehc.cmsmodules.cmsservice.CmsCaseService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

/**
* 内容发布平台案例 
* 2018-06-10 18:01:22  邓纯
*/
@Controller
@RequestMapping("/cmsCaseController")
public class CmsCaseController extends BaseAction{
	@Autowired
	private CmsCaseService cmsCaseService;
	/**
	* 载入初始化页面
	* @param cms_case 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/case.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsCase(CmsCase cmsCase,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-case/cms-case-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-case/cms-case-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsCaseDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsCaseDetail(String cms_case_id,HttpServletRequest request, Model model){
		CmsCase cmsCase = cmsCaseService.getCmsCaseById(cms_case_id);
		model.addAttribute("cmsCase", cmsCase);
		return new ModelAndView("pc/cms-view/cms-case/cms-case-detail");
	}
}
