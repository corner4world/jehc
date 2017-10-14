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

import jehc.bmodules.bmodel.BProductPrice;
import jehc.bmodules.bmodel.BSellerProduct;
import jehc.bmodules.bservice.BProductPriceService;
import jehc.bmodules.bservice.BSellerProductService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 卖家商品 
* 2016-02-18 17:20:35  邓纯杰
*/
@Controller
@RequestMapping("/bSellerProductController")
public class BSellerProductController extends BaseAction{
	@Autowired
	private BSellerProductService bSellerProductService;
	@Autowired
	private BProductPriceService bProductPriceService;
	/**
	* 载入初始化页面
	* @param b_seller_product 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBSellerProduct",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBSellerProduct(String b_seller_id,BSellerProduct b_Seller_Product,HttpServletRequest request){
		request.setAttribute("b_seller_id", b_seller_id);
		return new ModelAndView("pc/b-view/b-seller-product/b-seller-product-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_seller_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerProductListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerProductListByCondition(String b_seller_id,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BSellerProduct> b_Seller_ProductList = bSellerProductService.getBSellerProductListByCondition(condition);
		PageInfo<BSellerProduct> page = new PageInfo<BSellerProduct>(b_Seller_ProductList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_seller_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerProductById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerProductById(String b_seller_product_id,HttpServletRequest request){
		BSellerProduct b_Seller_Product = bSellerProductService.getBSellerProductById(b_seller_product_id);
		return outDataStr(b_Seller_Product);
	}
	/**
	* 添加
	* @param b_seller_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBSellerProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String addBSellerProduct(BSellerProduct b_Seller_Product,BProductPrice b_Product_Price,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Product && !"".equals(b_Seller_Product)){
			b_Seller_Product.setB_seller_product_id(UUID.toUUID());
			b_Product_Price.setB_product_price_id(UUID.toUUID());
			b_Product_Price.setB_seller_product_id(b_Seller_Product.getB_seller_product_id());
			i=bSellerProductService.addBSellerProduct(b_Seller_Product,b_Product_Price);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_seller_product 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBSellerProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBSellerProduct(BSellerProduct b_Seller_Product,BProductPrice b_Product_Price,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Product && !"".equals(b_Seller_Product)){
			i=bSellerProductService.updateBSellerProduct(b_Seller_Product,b_Product_Price);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_seller_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBSellerProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String delBSellerProduct(String b_seller_product_id,HttpServletRequest request){
		int i = 0;
		if(null != b_seller_product_id && !"".equals(b_seller_product_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_seller_product_id",b_seller_product_id.split(","));
			i=bSellerProductService.delBSellerProduct(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_seller_product_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBSellerProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBSellerProduct(String b_seller_product_id,HttpServletRequest request){
		int i = 0;
		BSellerProduct b_Seller_Product = bSellerProductService.getBSellerProductById(b_seller_product_id);
		if(null != b_Seller_Product && !"".equals(b_Seller_Product)){
			b_Seller_Product.setB_seller_product_id(UUID.toUUID());
			BProductPrice b_Product_Price = bProductPriceService.getBProductPriceByBSellerProductId(b_seller_product_id);
			i=bSellerProductService.addBSellerProduct(b_Seller_Product,b_Product_Price);
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
	@RequestMapping(value="/exportBSellerProduct",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBSellerProduct(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 查询库存使用商户商品并分页
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getBSellerProductStockListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerProductStockListByCondition(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonPager(condition,request);
		List<BSellerProduct> b_Seller_ProductList = bSellerProductService.getBSellerProductStockListByCondition(condition);
		int total = bSellerProductService.getBSellerProductStockListCountByCondition(condition);
		return outPageStr(b_Seller_ProductList,total,request);
	}
}
