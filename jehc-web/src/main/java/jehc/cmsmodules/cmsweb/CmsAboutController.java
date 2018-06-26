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
import jehc.cmsmodules.cmsmodel.CmsAbout;
import jehc.cmsmodules.cmsservice.CmsAboutService;

/**
* 内容发布平台关于我们 
* 2018-06-10 17:56:24  邓纯杰
*/
@Controller
@RequestMapping("/cmsAboutController")
public class CmsAboutController extends BaseAction{
	@Autowired
	private CmsAboutService cmsAboutService;
	/**
	* 载入初始化页面
	* @param cms_about 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsAbout",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsAbout(CmsAbout cmsAbout,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-about/cms-about-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_about 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsAboutListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsAboutListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsAbout> cmsAboutList = cmsAboutService.getCmsAboutListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsAboutList.size(); i++){
			cmsAboutList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsAboutList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsAbout> page = new PageInfo<CmsAbout>(cmsAboutList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_about_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsAboutById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsAboutById(String cms_about_id,HttpServletRequest request){
		CmsAbout cmsAbout = cmsAboutService.getCmsAboutById(cms_about_id);
		return outDataStr(cmsAbout);
	}
	/**
	* 添加
	* @param cms_about 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsAbout",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsAbout(CmsAbout cmsAbout,HttpServletRequest request){
		int i = 0;
		if(null != cmsAbout && !"".equals(cmsAbout)){
			cmsAbout.setCms_about_id(UUID.toUUID());
			cmsAbout.setCtime(getDate());
			cmsAbout.setXt_userinfo_id(getXtUid());
			i=cmsAboutService.addCmsAbout(cmsAbout);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_about 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsAbout",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsAbout(CmsAbout cmsAbout,HttpServletRequest request){
		int i = 0;
		if(null != cmsAbout && !"".equals(cmsAbout)){
			cmsAbout.setMtime(getDate());
			cmsAbout.setXt_userinfo_id(getXtUid());
			i=cmsAboutService.updateCmsAbout(cmsAbout);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_about_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsAbout",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsAbout(String cms_about_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_about_id && !"".equals(cms_about_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_about_id",cms_about_id.split(","));
			i=cmsAboutService.delCmsAbout(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_about_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsAbout",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsAbout(String cms_about_id,HttpServletRequest request){
		int i = 0;
		CmsAbout cmsAbout = cmsAboutService.getCmsAboutById(cms_about_id);
		if(null != cmsAbout && !"".equals(cmsAbout)){
			cmsAbout.setCms_about_id(UUID.toUUID());
			i=cmsAboutService.addCmsAbout(cmsAbout);
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
	@RequestMapping(value="/exportCmsAbout",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsAbout(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsAboutAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsAboutAdd(CmsAbout cmsAbout,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-about/cms-about-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsAboutUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsAboutUpdate(String cms_about_id,HttpServletRequest request, Model model){
		CmsAbout cmsAbout = cmsAboutService.getCmsAboutById(cms_about_id);
		model.addAttribute("cmsAbout", cmsAbout);
		return new ModelAndView("pc/cms-view/cms-about/cms-about-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsAboutDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsAboutDetail(String cms_about_id,HttpServletRequest request, Model model){
		CmsAbout cmsAbout = cmsAboutService.getCmsAboutById(cms_about_id);
		model.addAttribute("cmsAbout", cmsAbout);
		return new ModelAndView("pc/cms-view/cms-about/cms-about-detail");
	}
}
