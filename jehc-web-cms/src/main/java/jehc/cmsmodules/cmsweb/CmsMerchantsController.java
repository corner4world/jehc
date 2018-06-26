package jehc.cmsmodules.cmsweb;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.cmsmodules.cmsmodel.CmsMerchants;
import jehc.cmsmodules.cmsservice.CmsMerchantsService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.BrowserUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;

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
	public ModelAndView loadCmsMerchants(BaseSearch baseSearch,HttpServletRequest request, Model model){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsMerchants> cmsMerchantsList = cmsMerchantsService.getCmsMerchantsListByCondition(condition);
		PageInfo<CmsMerchants> page = new PageInfo<CmsMerchants>(cmsMerchantsList);
		model.addAttribute("page", page);
		model.addAttribute("title", "招商加盟");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
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
		model.addAttribute("title", "招商加盟");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-merchants/cms-merchants-detail");
		}else{
			return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-detail");
		}
	}
}
