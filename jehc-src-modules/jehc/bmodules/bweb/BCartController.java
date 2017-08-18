package jehc.bmodules.bweb;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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

import jehc.bmodules.bmodel.BCart;
import jehc.bmodules.bmodel.BCartDetail;
import jehc.bmodules.bmodel.BCartOrderAddress;
import jehc.bmodules.bservice.BCartService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.GeneratorNum;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 基础购物车 
* 2016-01-27 13:36:04  邓纯杰
*/
@Controller
@RequestMapping("/bCartController")
public class BCartController extends BaseAction{
	@Autowired
	private BCartService bCartService;
	/**
	* 载入初始化页面
	* @param b_cart 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBCart",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBCart(BCart b_Cart,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-cart/b-cart-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_cart 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCartListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCartListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BCart> b_CartList = bCartService.getBCartListByCondition(condition);
		PageInfo<BCart> page = new PageInfo<BCart>(b_CartList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_cart_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCartById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCartById(String b_cart_id,HttpServletRequest request){
		BCart b_Cart = bCartService.getBCartById(b_cart_id);
		return outDataStr(b_Cart);
	}
	/**
	* 添加
	* @param b_cart 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBCart",method={RequestMethod.POST,RequestMethod.GET})
	public String addBCart(BCart b_Cart,HttpServletRequest request,BCartOrderAddress b_Cart_Order_Address){
		int i = 0;
		String bCartDetailJSON = request.getParameter("bCartDetailJSON");
		if(null != b_Cart && !"".equals(b_Cart)){
			List<BCartDetail> b_Cart_DetailList = commonBCartDetailList(bCartDetailJSON);
			b_Cart.setB_cart_ctime(CommonUtils.getSimpleDateFormat());
			b_Cart.setB_cart_orderkey(GeneratorNum.generatorOrderID());
			b_Cart.setB_cart_id(UUID.toUUID());
			b_Cart_Order_Address.setB_cart_order_address_id(UUID.toUUID());
			b_Cart_Order_Address.setB_cart_order_address_status("0");
			for(int j = 0; j < b_Cart_DetailList.size();j++){
				b_Cart_DetailList.get(j).setB_cart_detail_id(UUID.toUUID());
				b_Cart_DetailList.get(j).setB_cart_id(b_Cart.getB_cart_id());
				b_Cart_DetailList.get(j).setB_cart_detail_ctime(b_Cart.getB_cart_ctime());
			}
			i=bCartService.addBCart(b_Cart,b_Cart_DetailList,b_Cart_Order_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_cart 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBCart",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBCart(BCart b_Cart,HttpServletRequest request,BCartOrderAddress b_Cart_Order_Address){
		int i = 0;
		String bCartDetailJSON = request.getParameter("bCartDetailJSON");
		List<BCartDetail> b_Cart_DetailList = commonBCartDetailList(bCartDetailJSON);
		if(null != b_Cart && !"".equals(b_Cart)){
			b_Cart.setB_cart_mtime(CommonUtils.getSimpleDateFormat());
			i=bCartService.updateBCart(b_Cart,b_Cart_DetailList,b_Cart_Order_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_cart_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBCart",method={RequestMethod.POST,RequestMethod.GET})
	public String delBCart(String b_cart_id,HttpServletRequest request){
		int i = 0;
		if(null != b_cart_id && !"".equals(b_cart_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_cart_id",b_cart_id.split(","));
			i=bCartService.delBCart(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_cart_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBCart",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBCart(String b_cart_id,HttpServletRequest request){
		int i = 0;
		BCart b_Cart = bCartService.getBCartById(b_cart_id);
		if(null != b_Cart && !"".equals(b_Cart)){
			b_Cart.setB_cart_id(UUID.toUUID());
			i=bCartService.addBCart(b_Cart,null,null);
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
	@RequestMapping(value="/exportBCart",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBCart(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 返回查询购物车明细集合
	 * @param solrIndexSqlJSON
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<BCartDetail> commonBCartDetailList(String bCartDetailJSON){
		try {
			bCartDetailJSON = URLDecoder.decode(bCartDetailJSON, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<BCartDetail> b_Cart_DetailList = new ArrayList<BCartDetail>();
		JSONArray arr = JSONArray.fromObject(bCartDetailJSON);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	JSONObject json = (JSONObject)obj; 
		    	BCartDetail b_Cart_Detail = new BCartDetail();
		    	Object b_seller_id = json.get("b_seller_id");
		    	Object b_cart_detail_id = json.get("b_cart_detail_id");
		    	Object b_product_id = json.get("b_product_id");
		    	Object b_cart_detail_number = json.get("b_cart_detail_number");
		    	Object b_cart_detail_price = json.get("b_cart_detail_price");
		    	Object b_cart_detail_discount = json.get("b_cart_detail_discount");
		    	Object b_cart_detail_total_price = json.get("b_cart_detail_total_price");
		    	if(null != b_cart_detail_id){
		    		b_Cart_Detail.setB_cart_detail_id((String)b_cart_detail_id);
		    	}
		    	if(null != b_seller_id){
		    		b_Cart_Detail.setB_seller_id((String)b_seller_id);
				}
		    	if(null != b_product_id){
		    		b_Cart_Detail.setB_product_id((String)b_product_id);
		    	}
		    	b_Cart_Detail.setB_cart_detail_discount(new Double(b_cart_detail_discount.toString()));
		    	b_Cart_Detail.setB_cart_detail_number(new Integer(b_cart_detail_number.toString()));
		    	b_Cart_Detail.setB_cart_detail_price(new Double(b_cart_detail_price.toString()));
		    	b_Cart_DetailList.add(b_Cart_Detail);
		    }
		}
		return b_Cart_DetailList;
	}
	/**
	* 单个购物车转订单转换
	* @param b_cart_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/singleBCartTBOrderPoulators",method={RequestMethod.POST,RequestMethod.GET})
	public String singleBCartTBOrderPoulators(String b_cart_id,HttpServletRequest request){
		int i = 0;
		if(null != b_cart_id && !"".equals(b_cart_id)){
			i=bCartService.singleBCartTBOrderPoulators(b_cart_id);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
