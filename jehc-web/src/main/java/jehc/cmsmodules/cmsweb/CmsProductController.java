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
import jehc.cmsmodules.cmsmodel.CmsProduct;
import jehc.cmsmodules.cmsservice.CmsProductService;

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
		return new ModelAndView("pc/cms-view/cms-product/cms-product-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsProductListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsProductListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsProduct> cmsProductList = cmsProductService.getCmsProductListByCondition(condition);
		PageInfo<CmsProduct> page = new PageInfo<CmsProduct>(cmsProductList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsProductById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsProductById(String cms_product_id,HttpServletRequest request){
		CmsProduct cmsProduct = cmsProductService.getCmsProductById(cms_product_id);
		return outDataStr(cmsProduct);
	}
	/**
	* 添加
	* @param cms_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsProduct(CmsProduct cmsProduct,HttpServletRequest request){
		int i = 0;
		if(null != cmsProduct && !"".equals(cmsProduct)){
			cmsProduct.setCms_product_id(UUID.toUUID());
			i=cmsProductService.addCmsProduct(cmsProduct);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsProduct(CmsProduct cmsProduct,HttpServletRequest request){
		int i = 0;
		if(null != cmsProduct && !"".equals(cmsProduct)){
			i=cmsProductService.updateCmsProduct(cmsProduct);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsProduct(String cms_product_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_product_id && !"".equals(cms_product_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_product_id",cms_product_id.split(","));
			i=cmsProductService.delCmsProduct(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsProduct(String cms_product_id,HttpServletRequest request){
		int i = 0;
		CmsProduct cmsProduct = cmsProductService.getCmsProductById(cms_product_id);
		if(null != cmsProduct && !"".equals(cmsProduct)){
			cmsProduct.setCms_product_id(UUID.toUUID());
			i=cmsProductService.addCmsProduct(cmsProduct);
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
	@RequestMapping(value="/exportCmsProduct",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsProduct(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsProductAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsProductAdd(CmsProduct cmsProduct,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-product/cms-product-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsProductUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsProductUpdate(String cms_product_id,HttpServletRequest request, Model model){
		CmsProduct cmsProduct = cmsProductService.getCmsProductById(cms_product_id);
		model.addAttribute("cmsProduct", cmsProduct);
		return new ModelAndView("pc/cms-view/cms-product/cms-product-update");
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
