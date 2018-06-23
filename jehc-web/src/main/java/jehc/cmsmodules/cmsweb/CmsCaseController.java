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
import jehc.cmsmodules.cmsmodel.CmsCase;
import jehc.cmsmodules.cmsservice.CmsCaseService;

/**
* 内容发布平台案例 
* 2018-06-10 18:01:22  邓纯
*/
@Controller
@RequestMapping("/cmsCaseController")
public class CmsCaseController extends BaseAction{
	@Autowired
	private CmsCaseService cmsCaseService;
	/**
	* 载入初始化页面
	* @param cms_case 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsCase",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsCase(CmsCase cmsCase,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-case/cms-case-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_case 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsCaseListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsCaseListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsCase> cmsCaseList = cmsCaseService.getCmsCaseListByCondition(condition);
		PageInfo<CmsCase> page = new PageInfo<CmsCase>(cmsCaseList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_case_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsCaseById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsCaseById(String cms_case_id,HttpServletRequest request){
		CmsCase cmsCase = cmsCaseService.getCmsCaseById(cms_case_id);
		return outDataStr(cmsCase);
	}
	/**
	* 添加
	* @param cms_case 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsCase",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsCase(CmsCase cmsCase,HttpServletRequest request){
		int i = 0;
		if(null != cmsCase && !"".equals(cmsCase)){
			cmsCase.setCms_case_id(UUID.toUUID());
			i=cmsCaseService.addCmsCase(cmsCase);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_case 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsCase",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsCase(CmsCase cmsCase,HttpServletRequest request){
		int i = 0;
		if(null != cmsCase && !"".equals(cmsCase)){
			i=cmsCaseService.updateCmsCase(cmsCase);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_case_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsCase",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsCase(String cms_case_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_case_id && !"".equals(cms_case_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_case_id",cms_case_id.split(","));
			i=cmsCaseService.delCmsCase(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_case_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsCase",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsCase(String cms_case_id,HttpServletRequest request){
		int i = 0;
		CmsCase cmsCase = cmsCaseService.getCmsCaseById(cms_case_id);
		if(null != cmsCase && !"".equals(cmsCase)){
			cmsCase.setCms_case_id(UUID.toUUID());
			i=cmsCaseService.addCmsCase(cmsCase);
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
	@RequestMapping(value="/exportCmsCase",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsCase(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsCaseAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsCaseAdd(CmsCase cmsCase,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-case/cms-case-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsCaseUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsCaseUpdate(String cms_case_id,HttpServletRequest request, Model model){
		CmsCase cmsCase = cmsCaseService.getCmsCaseById(cms_case_id);
		model.addAttribute("cmsCase", cmsCase);
		return new ModelAndView("pc/cms-view/cms-case/cms-case-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsCaseDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsCaseDetail(String cms_case_id,HttpServletRequest request, Model model){
		CmsCase cmsCase = cmsCaseService.getCmsCaseById(cms_case_id);
		model.addAttribute("cmsCase", cmsCase);
		return new ModelAndView("pc/cms-view/cms-case/cms-case-detail");
	}
}
