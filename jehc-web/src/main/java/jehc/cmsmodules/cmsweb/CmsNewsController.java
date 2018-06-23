package jehc.cmsmodules.cmsweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.cmsmodules.cmsmodel.CmsNews;
import jehc.cmsmodules.cmsservice.CmsNewsService;

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
	@RequestMapping(value="/loadCmsNews",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsNews(CmsNews cmsNews,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-news/cms-news-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_news 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsNewsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsNewsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsNews> cmsNewsList = cmsNewsService.getCmsNewsListByCondition(condition);
		PageInfo<CmsNews> page = new PageInfo<CmsNews>(cmsNewsList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_news_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsNewsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsNewsById(String cms_news_id,HttpServletRequest request){
		CmsNews cmsNews = cmsNewsService.getCmsNewsById(cms_news_id);
		return outDataStr(cmsNews);
	}
	/**
	* 添加
	* @param cms_news 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsNews",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsNews(CmsNews cmsNews,HttpServletRequest request){
		int i = 0;
		if(null != cmsNews && !"".equals(cmsNews)){
			cmsNews.setCms_news_id(UUID.toUUID());
			i=cmsNewsService.addCmsNews(cmsNews);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_news 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsNews",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsNews(CmsNews cmsNews,HttpServletRequest request){
		int i = 0;
		if(null != cmsNews && !"".equals(cmsNews)){
			i=cmsNewsService.updateCmsNews(cmsNews);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_news_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsNews",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsNews(String cms_news_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_news_id && !"".equals(cms_news_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_news_id",cms_news_id.split(","));
			i=cmsNewsService.delCmsNews(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_news_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsNews",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsNews(String cms_news_id,HttpServletRequest request){
		int i = 0;
		CmsNews cmsNews = cmsNewsService.getCmsNewsById(cms_news_id);
		if(null != cmsNews && !"".equals(cmsNews)){
			cmsNews.setCms_news_id(UUID.toUUID());
			i=cmsNewsService.addCmsNews(cmsNews);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportCmsNews",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsNews(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsAdd(CmsNews cmsNews,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-news/cms-news-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsUpdate(String cms_news_id,HttpServletRequest request, Model model){
		CmsNews cmsNews = cmsNewsService.getCmsNewsById(cms_news_id);
		model.addAttribute("cmsNews", cmsNews);
		return new ModelAndView("pc/cms-view/cms-news/cms-news-update");
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
