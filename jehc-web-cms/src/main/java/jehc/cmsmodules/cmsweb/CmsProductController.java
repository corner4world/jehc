package jehc.cmsmodules.cmsweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.cmsmodules.cmsmodel.CmsProduct;
import jehc.cmsmodules.cmsservice.CmsProductService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.BrowserUtil;

/**
* 内容发布平台 产品 
* 2018-06-10 15:05:11  邓纯杰
*/
@Controller
@RequestMapping("/cmsProductController")
public class CmsProductController extends BaseAction{
	@Autowired
	private CmsProductService cmsProductService;
	/**
	* 载入初始化页面
	* @param cms_product 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsProduct",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsProduct(CmsProduct cmsProduct,HttpServletRequest request){
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-product/cms-product-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-product/cms-product-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsProductDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsProductDetail(String cms_product_id,HttpServletRequest request, Model model){
		CmsProduct cmsProduct = cmsProductService.getCmsProductById(cms_product_id);
		model.addAttribute("cmsProduct", cmsProduct);
		return new ModelAndView("pc/cms-view/cms-product/cms-product-detail");
	}
}
