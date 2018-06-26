package jehc.cmsmodules.cmsweb;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.cmsmodules.cmsmodel.CmsContact;
import jehc.cmsmodules.cmsservice.CmsContactService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.BrowserUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;

/**
* 内容发布平台联系我们 
* 2018-06-10 14:42:49  邓纯杰
*/
@Controller
@RequestMapping("/cmsContactController")
public class CmsContactController extends BaseAction{
	@Autowired
	private CmsContactService cmsContactService;
	/**
	* 载入初始化页面
	* @param cms_contact 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/contact.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsContact(BaseSearch baseSearch,HttpServletRequest request, Model model){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsContact> cmsContactList = cmsContactService.getCmsContactListByCondition(condition);
		PageInfo<CmsContact> page = new PageInfo<CmsContact>(cmsContactList);
		if(null != page.getList() && page.getList().size() > 0){
			model.addAttribute("page", page.getList().get(0));
		}
		model.addAttribute("title", "联系我们");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-contact/cms-contact-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-contact/cms-contact-list");
		}
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsContactListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsContactListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsContact> cmsContactList = cmsContactService.getCmsContactListByCondition(condition);
		PageInfo<CmsContact> page = new PageInfo<CmsContact>(cmsContactList);
		return outPageBootStr(page,request);
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsContactDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsContactDetail(String cms_contact_id,HttpServletRequest request, Model model){
		CmsContact cmsContact = cmsContactService.getCmsContactById(cms_contact_id);
		model.addAttribute("cmsContact", cmsContact);
		model.addAttribute("title", "联系我们");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-contact/cms-contact-detail");
		}else{
			return new ModelAndView("pc/cms-view/cms-contact/cms-contact-detail");
		}
	}
}
