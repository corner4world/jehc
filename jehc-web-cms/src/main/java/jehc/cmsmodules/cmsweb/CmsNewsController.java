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

import jehc.cmsmodules.cmsmodel.CmsNews;
import jehc.cmsmodules.cmsservice.CmsNewsService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.BrowserUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;

/**
* 内容发布平台新闻 
* 2018-06-10 14:56:47  邓纯杰
*/
@Controller
@RequestMapping("/cmsNewsController")
public class CmsNewsController extends BaseAction{
	@Autowired
	private CmsNewsService cmsNewsService;
	/**
	* 载入初始化页面
	* @param cms_news 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/news.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsNews(BaseSearch baseSearch,HttpServletRequest request, Model model){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsNews> cmsNewsList = cmsNewsService.getCmsNewsListByCondition(condition);
		PageInfo<CmsNews> page = new PageInfo<CmsNews>(cmsNewsList);
		model.addAttribute("page", page);
		model.addAttribute("title", "资讯中心");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-news/cms-news-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-news/cms-news-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsDetail(String cms_news_id,HttpServletRequest request, Model model){
		CmsNews cmsNews = cmsNewsService.getCmsNewsById(cms_news_id);
		model.addAttribute("cmsNews", cmsNews);
		model.addAttribute("title", "资讯中心");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-news/cms-news-detail");
		}else{
			return new ModelAndView("pc/cms-view/cms-news/cms-news-detail");
		}
	}
}
