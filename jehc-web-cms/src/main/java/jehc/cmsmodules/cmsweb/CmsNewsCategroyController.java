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

import jehc.cmsmodules.cmsmodel.CmsNewsCategroy;
import jehc.cmsmodules.cmsservice.CmsNewsCategroyService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.BrowserUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;

/**
* 内容发布平台新闻分类 
* 2018-06-10 15:01:32  邓纯杰
*/
@Controller
@RequestMapping("/cmsNewsCategroyController")
public class CmsNewsCategroyController extends BaseAction{
	@Autowired
	private CmsNewsCategroyService cmsNewsCategroyService;
	/**
	* 载入初始化页面
	* @param cms_news_categroy 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsNewsCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsNewsCategroy(BaseSearch baseSearch,HttpServletRequest request, Model model){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsNewsCategroy> cmsNewsCategroyList = cmsNewsCategroyService.getCmsNewsCategroyListByCondition(condition);
		PageInfo<CmsNewsCategroy> page = new PageInfo<CmsNewsCategroy>(cmsNewsCategroyList);
		model.addAttribute("page", page);
		model.addAttribute("title", "资讯中心");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-news-categroy/cms-news-categroy-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-news-categroy/cms-news-categroy-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsCategroyDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsCategroyDetail(String cms_news_categroy_id,HttpServletRequest request, Model model){
		CmsNewsCategroy cmsNewsCategroy = cmsNewsCategroyService.getCmsNewsCategroyById(cms_news_categroy_id);
		model.addAttribute("cmsNewsCategroy", cmsNewsCategroy);
		model.addAttribute("title", "资讯中心");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-news-categroy/cms-news-categroy-detail");
		}else{
			return new ModelAndView("pc/cms-view/cms-news-categroy/cms-news-categroy-detail");
		}
	}
}
