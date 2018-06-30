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
import jehc.cmsmodules.cmsmodel.CmsSlide;
import jehc.cmsmodules.cmsservice.CmsSlideService;

/**
* 内容发布平台幻灯片 
* 2018-06-27 12:44:03  邓纯杰
*/
@Controller
@RequestMapping("/cmsSlideController")
public class CmsSlideController extends BaseAction{
	@Autowired
	private CmsSlideService cmsSlideService;
	/**
	* 载入初始化页面
	* @param cms_slide 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsSlide",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsSlide(CmsSlide cmsSlide,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-slide/cms-slide-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_slide 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsSlideListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsSlideListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsSlide> cmsSlideList = cmsSlideService.getCmsSlideListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsSlideList.size(); i++){
			cmsSlideList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsSlideList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsSlide> page = new PageInfo<CmsSlide>(cmsSlideList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_slide_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsSlideById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsSlideById(String cms_slide_id,HttpServletRequest request){
		CmsSlide cmsSlide = cmsSlideService.getCmsSlideById(cms_slide_id);
		return outDataStr(cmsSlide);
	}
	/**
	* 添加
	* @param cms_slide 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsSlide",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsSlide(CmsSlide cmsSlide,HttpServletRequest request){
		int i = 0;
		if(null != cmsSlide && !"".equals(cmsSlide)){
			cmsSlide.setCms_slide_id(UUID.toUUID());
			cmsSlide.setCtime(getDate());
			cmsSlide.setXt_userinfo_id(getXtUid());
			i=cmsSlideService.addCmsSlide(cmsSlide);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_slide 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsSlide",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsSlide(CmsSlide cmsSlide,HttpServletRequest request){
		int i = 0;
		if(null != cmsSlide && !"".equals(cmsSlide)){
			cmsSlide.setMtime(getDate());
			i=cmsSlideService.updateCmsSlide(cmsSlide);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_slide_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsSlide",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsSlide(String cms_slide_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_slide_id && !"".equals(cms_slide_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_slide_id",cms_slide_id.split(","));
			i=cmsSlideService.delCmsSlide(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_slide_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsSlide",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsSlide(String cms_slide_id,HttpServletRequest request){
		int i = 0;
		CmsSlide cmsSlide = cmsSlideService.getCmsSlideById(cms_slide_id);
		if(null != cmsSlide && !"".equals(cmsSlide)){
			cmsSlide.setCms_slide_id(UUID.toUUID());
			i=cmsSlideService.addCmsSlide(cmsSlide);
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
	@RequestMapping(value="/exportCmsSlide",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsSlide(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsSlideAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsSlideAdd(CmsSlide cmsSlide,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-slide/cms-slide-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsSlideUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsSlideUpdate(String cms_slide_id,HttpServletRequest request, Model model){
		CmsSlide cmsSlide = cmsSlideService.getCmsSlideById(cms_slide_id);
		model.addAttribute("cmsSlide", cmsSlide);
		return new ModelAndView("pc/cms-view/cms-slide/cms-slide-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsSlideDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsSlideDetail(String cms_slide_id,HttpServletRequest request, Model model){
		CmsSlide cmsSlide = cmsSlideService.getCmsSlideById(cms_slide_id);
		model.addAttribute("cmsSlide", cmsSlide);
		return new ModelAndView("pc/cms-view/cms-slide/cms-slide-detail");
	}
}
