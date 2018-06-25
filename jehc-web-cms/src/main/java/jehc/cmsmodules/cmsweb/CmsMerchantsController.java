package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsMerchants;
import jehc.cmsmodules.cmsservice.CmsMerchantsService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

/**
* 内容发布平台招商加盟 
* 2018-06-10 14:47:12  邓纯杰
*/
@Controller
@RequestMapping("/cmsMerchantsController")
public class CmsMerchantsController extends BaseAction{
	@Autowired
	private CmsMerchantsService cmsMerchantsService;
	/**
	* 载入初始化页面
	* @param cms_merchants 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/merchants.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsMerchants(CmsMerchants cmsMerchants,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-merchants/cms-merchants-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMerchantsDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMerchantsDetail(String cms_merchants_id,HttpServletRequest request, Model model){
		CmsMerchants cmsMerchants = cmsMerchantsService.getCmsMerchantsById(cms_merchants_id);
		model.addAttribute("cmsMerchants", cmsMerchants);
		return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-detail");
	}
}
