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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.cmsmodules.cmsmodel.CmsNewsCategroy;
import jehc.cmsmodules.cmsservice.CmsNewsCategroyService;

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
	public ModelAndView loadCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-news-categroy/cms-news-categroy-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_news_categroy 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsNewsCategroyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsNewsCategroyListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsNewsCategroy> cmsNewsCategroyList = cmsNewsCategroyService.getCmsNewsCategroyListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsNewsCategroyList.size(); i++){
			cmsNewsCategroyList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsNewsCategroyList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsNewsCategroy> page = new PageInfo<CmsNewsCategroy>(cmsNewsCategroyList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_news_categroy_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsNewsCategroyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsNewsCategroyById(String cms_news_categroy_id,HttpServletRequest request){
		CmsNewsCategroy cmsNewsCategroy = cmsNewsCategroyService.getCmsNewsCategroyById(cms_news_categroy_id);
		return outDataStr(cmsNewsCategroy);
	}
	/**
	* 添加
	* @param cms_news_categroy 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsNewsCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy,HttpServletRequest request){
		int i = 0;
		if(null != cmsNewsCategroy && !"".equals(cmsNewsCategroy)){
			cmsNewsCategroy.setCtime(getDate());
			cmsNewsCategroy.setXt_userinfo_id(getXtUid());
			cmsNewsCategroy.setCms_news_categroy_id(UUID.toUUID());
			i=cmsNewsCategroyService.addCmsNewsCategroy(cmsNewsCategroy);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_news_categroy 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsNewsCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy,HttpServletRequest request){
		int i = 0;
		if(null != cmsNewsCategroy && !"".equals(cmsNewsCategroy)){
			cmsNewsCategroy.setMtime(getDate());
			i=cmsNewsCategroyService.updateCmsNewsCategroy(cmsNewsCategroy);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_news_categroy_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsNewsCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsNewsCategroy(String cms_news_categroy_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_news_categroy_id && !"".equals(cms_news_categroy_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_news_categroy_id",cms_news_categroy_id.split(","));
			i=cmsNewsCategroyService.delCmsNewsCategroy(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_news_categroy_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsNewsCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsNewsCategroy(String cms_news_categroy_id,HttpServletRequest request){
		int i = 0;
		CmsNewsCategroy cmsNewsCategroy = cmsNewsCategroyService.getCmsNewsCategroyById(cms_news_categroy_id);
		if(null != cmsNewsCategroy && !"".equals(cmsNewsCategroy)){
			cmsNewsCategroy.setCms_news_categroy_id(UUID.toUUID());
			i=cmsNewsCategroyService.addCmsNewsCategroy(cmsNewsCategroy);
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
	@RequestMapping(value="/exportCmsNewsCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsNewsCategroy(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsCategroyAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsCategroyAdd(CmsNewsCategroy cmsNewsCategroy,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-news-categroy/cms-news-categroy-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsCategroyUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsCategroyUpdate(String cms_news_categroy_id,HttpServletRequest request, Model model){
		CmsNewsCategroy cmsNewsCategroy = cmsNewsCategroyService.getCmsNewsCategroyById(cms_news_categroy_id);
		model.addAttribute("cmsNewsCategroy", cmsNewsCategroy);
		return new ModelAndView("pc/cms-view/cms-news-categroy/cms-news-categroy-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsNewsCategroyDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsNewsCategroyDetail(String cms_news_categroy_id,HttpServletRequest request, Model model){
		CmsNewsCategroy cmsNewsCategroy = cmsNewsCategroyService.getCmsNewsCategroyById(cms_news_categroy_id);
		model.addAttribute("cmsNewsCategroy", cmsNewsCategroy);
		return new ModelAndView("pc/cms-view/cms-news-categroy/cms-news-categroy-detail");
	}
}
