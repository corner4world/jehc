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
import jehc.cmsmodules.cmsmodel.CmsProductCategory;
import jehc.cmsmodules.cmsservice.CmsProductCategoryService;

/**
* 内容发布平台产品分类 
* 2018-06-10 15:08:21  邓纯杰
*/
@Controller
@RequestMapping("/cmsProductCategoryController")
public class CmsProductCategoryController extends BaseAction{
	@Autowired
	private CmsProductCategoryService cmsProductCategoryService;
	/**
	* 载入初始化页面
	* @param cms_product_category 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsProductCategory",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsProductCategory(CmsProductCategory cmsProductCategory,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-product-category/cms-product-category-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_product_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsProductCategoryListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsProductCategoryListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsProductCategory> cmsProductCategoryList = cmsProductCategoryService.getCmsProductCategoryListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsProductCategoryList.size(); i++){
			cmsProductCategoryList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsProductCategoryList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsProductCategory> page = new PageInfo<CmsProductCategory>(cmsProductCategoryList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_product_category_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsProductCategoryById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsProductCategoryById(String cms_product_category_id,HttpServletRequest request){
		CmsProductCategory cmsProductCategory = cmsProductCategoryService.getCmsProductCategoryById(cms_product_category_id);
		return outDataStr(cmsProductCategory);
	}
	/**
	* 添加
	* @param cms_product_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsProductCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsProductCategory(CmsProductCategory cmsProductCategory,HttpServletRequest request){
		int i = 0;
		if(null != cmsProductCategory && !"".equals(cmsProductCategory)){
			cmsProductCategory.setCtime(getDate());
			cmsProductCategory.setXt_userinfo_id(getXtUid());
			cmsProductCategory.setCms_product_category_id(UUID.toUUID());
			i=cmsProductCategoryService.addCmsProductCategory(cmsProductCategory);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_product_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsProductCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsProductCategory(CmsProductCategory cmsProductCategory,HttpServletRequest request){
		int i = 0;
		if(null != cmsProductCategory && !"".equals(cmsProductCategory)){
			cmsProductCategory.setMtime(getDate());
			i=cmsProductCategoryService.updateCmsProductCategory(cmsProductCategory);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_product_category_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsProductCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsProductCategory(String cms_product_category_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_product_category_id && !"".equals(cms_product_category_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_product_category_id",cms_product_category_id.split(","));
			i=cmsProductCategoryService.delCmsProductCategory(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_product_category_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsProductCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsProductCategory(String cms_product_category_id,HttpServletRequest request){
		int i = 0;
		CmsProductCategory cmsProductCategory = cmsProductCategoryService.getCmsProductCategoryById(cms_product_category_id);
		if(null != cmsProductCategory && !"".equals(cmsProductCategory)){
			cmsProductCategory.setCms_product_category_id(UUID.toUUID());
			i=cmsProductCategoryService.addCmsProductCategory(cmsProductCategory);
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
	@RequestMapping(value="/exportCmsProductCategory",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsProductCategory(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsProductCategoryAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsProductCategoryAdd(CmsProductCategory cmsProductCategory,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-product-category/cms-product-category-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsProductCategoryUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsProductCategoryUpdate(String cms_product_category_id,HttpServletRequest request, Model model){
		CmsProductCategory cmsProductCategory = cmsProductCategoryService.getCmsProductCategoryById(cms_product_category_id);
		model.addAttribute("cmsProductCategory", cmsProductCategory);
		return new ModelAndView("pc/cms-view/cms-product-category/cms-product-category-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsProductCategoryDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsProductCategoryDetail(String cms_product_category_id,HttpServletRequest request, Model model){
		CmsProductCategory cmsProductCategory = cmsProductCategoryService.getCmsProductCategoryById(cms_product_category_id);
		model.addAttribute("cmsProductCategory", cmsProductCategory);
		return new ModelAndView("pc/cms-view/cms-product-category/cms-product-category-detail");
	}
}
