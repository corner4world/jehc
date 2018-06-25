package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsNews;
import jehc.cmsmodules.cmsservice.CmsNewsService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

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
	public ModelAndView loadCmsNews(CmsNews cmsNews,HttpServletRequest request){
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
		return new ModelAndView("pc/cms-view/cms-news/cms-news-detail");
	}
}
