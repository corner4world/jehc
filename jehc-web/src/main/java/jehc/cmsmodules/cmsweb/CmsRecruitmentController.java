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
import jehc.cmsmodules.cmsmodel.CmsRecruitment;
import jehc.cmsmodules.cmsservice.CmsRecruitmentService;

/**
* 内容发布平台招贤纳士 
* 2018-06-10 15:11:58  邓纯杰
*/
@Controller
@RequestMapping("/cmsRecruitmentController")
public class CmsRecruitmentController extends BaseAction{
	@Autowired
	private CmsRecruitmentService cmsRecruitmentService;
	/**
	* 载入初始化页面
	* @param cms_recruitment 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsRecruitment",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsRecruitment(CmsRecruitment cmsRecruitment,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-recruitment/cms-recruitment-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_recruitment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsRecruitmentListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsRecruitmentListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsRecruitment> cmsRecruitmentList = cmsRecruitmentService.getCmsRecruitmentListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsRecruitmentList.size(); i++){
			cmsRecruitmentList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsRecruitmentList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsRecruitment> page = new PageInfo<CmsRecruitment>(cmsRecruitmentList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_recruitment_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsRecruitmentById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsRecruitmentById(String cms_recruitment_id,HttpServletRequest request){
		CmsRecruitment cmsRecruitment = cmsRecruitmentService.getCmsRecruitmentById(cms_recruitment_id);
		return outDataStr(cmsRecruitment);
	}
	/**
	* 添加
	* @param cms_recruitment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsRecruitment",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsRecruitment(CmsRecruitment cmsRecruitment,HttpServletRequest request){
		int i = 0;
		if(null != cmsRecruitment && !"".equals(cmsRecruitment)){
			cmsRecruitment.setCtime(getDate());
			cmsRecruitment.setXt_userinfo_id(getXtUid());
			cmsRecruitment.setCms_recruitment_id(UUID.toUUID());
			i=cmsRecruitmentService.addCmsRecruitment(cmsRecruitment);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_recruitment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsRecruitment",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsRecruitment(CmsRecruitment cmsRecruitment,HttpServletRequest request){
		int i = 0;
		if(null != cmsRecruitment && !"".equals(cmsRecruitment)){
			cmsRecruitment.setMtime(getDate());
			i=cmsRecruitmentService.updateCmsRecruitment(cmsRecruitment);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_recruitment_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsRecruitment",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsRecruitment(String cms_recruitment_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_recruitment_id && !"".equals(cms_recruitment_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_recruitment_id",cms_recruitment_id.split(","));
			i=cmsRecruitmentService.delCmsRecruitment(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_recruitment_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsRecruitment",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsRecruitment(String cms_recruitment_id,HttpServletRequest request){
		int i = 0;
		CmsRecruitment cmsRecruitment = cmsRecruitmentService.getCmsRecruitmentById(cms_recruitment_id);
		if(null != cmsRecruitment && !"".equals(cmsRecruitment)){
			cmsRecruitment.setCms_recruitment_id(UUID.toUUID());
			i=cmsRecruitmentService.addCmsRecruitment(cmsRecruitment);
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
	@RequestMapping(value="/exportCmsRecruitment",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsRecruitment(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsRecruitmentAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsRecruitmentAdd(CmsRecruitment cmsRecruitment,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-recruitment/cms-recruitment-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsRecruitmentUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsRecruitmentUpdate(String cms_recruitment_id,HttpServletRequest request, Model model){
		CmsRecruitment cmsRecruitment = cmsRecruitmentService.getCmsRecruitmentById(cms_recruitment_id);
		model.addAttribute("cmsRecruitment", cmsRecruitment);
		return new ModelAndView("pc/cms-view/cms-recruitment/cms-recruitment-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsRecruitmentDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsRecruitmentDetail(String cms_recruitment_id,HttpServletRequest request, Model model){
		CmsRecruitment cmsRecruitment = cmsRecruitmentService.getCmsRecruitmentById(cms_recruitment_id);
		model.addAttribute("cmsRecruitment", cmsRecruitment);
		return new ModelAndView("pc/cms-view/cms-recruitment/cms-recruitment-detail");
	}
}
