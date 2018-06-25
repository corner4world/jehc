package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsProductCategory;
import jehc.cmsmodules.cmsservice.CmsProductCategoryService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

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
	@RequestMapping(value="/productCategory.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsProductCategory(CmsProductCategory cmsProductCategory,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-product-category/cms-product-category-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-product-category/cms-product-category-list");
		}
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
