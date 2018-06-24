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
import jehc.cmsmodules.cmsmodel.CmsSeo;
import jehc.cmsmodules.cmsservice.CmsSeoService;

/**
* 内容发布平台SEO配置 
* 2018-06-10 15:15:07  邓纯杰
*/
@Controller
@RequestMapping("/cmsSeoController")
public class CmsSeoController extends BaseAction{
	@Autowired
	private CmsSeoService cmsSeoService;
	/**
	* 载入初始化页面
	* @param cms_seo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsSeo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsSeo(CmsSeo cmsSeo,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-seo/cms-seo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_seo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsSeoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsSeoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsSeo> cmsSeoList = cmsSeoService.getCmsSeoListByCondition(condition);
		PageInfo<CmsSeo> page = new PageInfo<CmsSeo>(cmsSeoList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_seo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsSeoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsSeoById(String cms_seo_id,HttpServletRequest request){
		CmsSeo cmsSeo = cmsSeoService.getCmsSeoById(cms_seo_id);
		return outDataStr(cmsSeo);
	}
	/**
	* 添加
	* @param cms_seo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsSeo",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsSeo(CmsSeo cmsSeo,HttpServletRequest request){
		int i = 0;
		if(null != cmsSeo && !"".equals(cmsSeo)){
			cmsSeo.setCms_seo_id(UUID.toUUID());
			cmsSeo.setXt_userinfo_id(getXtUid());
			cmsSeo.setCtime(getDate());
			i=cmsSeoService.addCmsSeo(cmsSeo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_seo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsSeo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsSeo(CmsSeo cmsSeo,HttpServletRequest request){
		int i = 0;
		if(null != cmsSeo && !"".equals(cmsSeo)){
			cmsSeo.setMtime(getDate());
			i=cmsSeoService.updateCmsSeo(cmsSeo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_seo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsSeo",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsSeo(String cms_seo_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_seo_id && !"".equals(cms_seo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_seo_id",cms_seo_id.split(","));
			i=cmsSeoService.delCmsSeo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_seo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsSeo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsSeo(String cms_seo_id,HttpServletRequest request){
		int i = 0;
		CmsSeo cmsSeo = cmsSeoService.getCmsSeoById(cms_seo_id);
		if(null != cmsSeo && !"".equals(cmsSeo)){
			cmsSeo.setCms_seo_id(UUID.toUUID());
			i=cmsSeoService.addCmsSeo(cmsSeo);
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
	@RequestMapping(value="/exportCmsSeo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsSeo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsSeoAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsSeoAdd(CmsSeo cmsSeo,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-seo/cms-seo-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsSeoUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsSeoUpdate(String cms_seo_id,HttpServletRequest request, Model model){
		CmsSeo cmsSeo = cmsSeoService.getCmsSeoById(cms_seo_id);
		model.addAttribute("cmsSeo", cmsSeo);
		return new ModelAndView("pc/cms-view/cms-seo/cms-seo-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsSeoDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsSeoDetail(String cms_seo_id,HttpServletRequest request, Model model){
		CmsSeo cmsSeo = cmsSeoService.getCmsSeoById(cms_seo_id);
		model.addAttribute("cmsSeo", cmsSeo);
		return new ModelAndView("pc/cms-view/cms-seo/cms-seo-detail");
	}
}
