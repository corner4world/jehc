package jehc.bmodules.bweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.bmodules.bmodel.BProduct;
import jehc.bmodules.bservice.BProductService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础_产品 
* 2016-01-08 21:03:47  邓纯杰
*/
@Controller
@RequestMapping("/bProductController")
public class BProductController extends BaseAction{
	@Autowired
	private BProductService bProductService;
	/**
	* 载入初始化页面
	* @param b_product 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBProduct",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBProduct(BProduct b_Product,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-product/b-product-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BProduct> b_ProductList = bProductService.getBProductListByCondition(condition);
		PageInfo<BProduct> page = new PageInfo<BProduct>(b_ProductList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductById(String b_product_id,HttpServletRequest request){
		BProduct b_Product = bProductService.getBProductById(b_product_id);
		return outDataStr(b_Product);
	}
	/**
	* 添加
	* @param b_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String addBProduct(BProduct b_Product,HttpServletRequest request){
		int i = 0;
		String b_product_features = request.getParameter("b_product_features");
		if(null != b_Product && !"".equals(b_Product)){
			b_Product.setB_product_id(UUID.toUUID());
			b_Product.setXt_userinfo_id(CommonUtils.getXtUid());
			b_Product.setB_product_ctime(CommonUtils.getSimpleDateFormat());
			b_Product.setB_product_features(b_product_features);
			i=bProductService.addBProduct(b_Product);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBProduct(BProduct b_Product,HttpServletRequest request){
		int i = 0;
		String b_product_features = request.getParameter("b_product_features");
		if(null != b_Product && !"".equals(b_Product)){
			b_Product.setXt_userinfo_id(CommonUtils.getXtUid());
			b_Product.setB_product_mtime(CommonUtils.getSimpleDateFormat());
			b_Product.setB_product_features(b_product_features);
			i=bProductService.updateBProduct(b_Product);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String delBProduct(String b_product_id,HttpServletRequest request){
		int i = 0;
		if(null != b_product_id && !"".equals(b_product_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_product_id",b_product_id.split(","));
			i=bProductService.delBProduct(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBProduct(String b_product_id,HttpServletRequest request){
		int i = 0;
		BProduct b_Product = bProductService.getBProductById(b_product_id);
		if(null != b_Product && !"".equals(b_Product)){
			b_Product.setB_product_id(UUID.toUUID());
			i=bProductService.addBProduct(b_Product);
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
	@RequestMapping(value="/exportBProduct",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBProduct(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
