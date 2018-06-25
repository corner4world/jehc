package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsRecruitment;
import jehc.cmsmodules.cmsservice.CmsRecruitmentService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

/**
* 内容发布平台招贤纳士 
* 2018-06-10 15:11:58  邓纯杰
*/
@Controller
@RequestMapping("/cmsRecruitmentController")
public class CmsRecruitmentController extends BaseAction{
	@Autowired
	private CmsRecruitmentService cmsRecruitmentService;
	/**
	* 载入初始化页面
	* @param cms_recruitment 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/recruitment.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsRecruitment(CmsRecruitment cmsRecruitment,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-recruitment/cms-recruitment-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-recruitment/cms-recruitment-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsRecruitmentDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsRecruitmentDetail(String cms_recruitment_id,HttpServletRequest request, Model model){
		CmsRecruitment cmsRecruitment = cmsRecruitmentService.getCmsRecruitmentById(cms_recruitment_id);
		model.addAttribute("cmsRecruitment", cmsRecruitment);
		return new ModelAndView("pc/cms-view/cms-recruitment/cms-recruitment-detail");
	}
}
