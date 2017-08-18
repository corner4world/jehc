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

import jehc.bmodules.bmodel.BCartOrderAddress;
import jehc.bmodules.bservice.BCartOrderAddressService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础购物车订单常用配送地址 
* 2016-02-22 21:17:25  邓纯杰
*/
@Controller
@RequestMapping("/bCartOrderAddressController")
public class BCartOrderAddressController extends BaseAction{
	@Autowired
	private BCartOrderAddressService bCartOrderAddressService;
	/**
	* 载入初始化页面
	* @param b_cart_order_address 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBCartOrderAddress",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-cart-order-address/b-cart-order-address-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_cart_order_address 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCartOrderAddressListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCartOrderAddressListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BCartOrderAddress> b_Cart_Order_AddressList = bCartOrderAddressService.getBCartOrderAddressListByCondition(condition);
		PageInfo<BCartOrderAddress> page = new PageInfo<BCartOrderAddress>(b_Cart_Order_AddressList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_cart_order_address_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCartOrderAddressById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCartOrderAddressById(String b_cart_order_address_id,HttpServletRequest request){
		BCartOrderAddress b_Cart_Order_Address = bCartOrderAddressService.getBCartOrderAddressById(b_cart_order_address_id);
		return outDataStr(b_Cart_Order_Address);
	}
	/**
	* 添加
	* @param b_cart_order_address 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBCartOrderAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String addBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address,HttpServletRequest request){
		int i = 0;
		if(null != b_Cart_Order_Address && !"".equals(b_Cart_Order_Address)){
			b_Cart_Order_Address.setB_cart_order_address_id(UUID.toUUID());
			i=bCartOrderAddressService.addBCartOrderAddress(b_Cart_Order_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_cart_order_address 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBCartOrderAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address,HttpServletRequest request){
		int i = 0;
		if(null != b_Cart_Order_Address && !"".equals(b_Cart_Order_Address)){
			i=bCartOrderAddressService.updateBCartOrderAddress(b_Cart_Order_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_cart_order_address_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBCartOrderAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String delBCartOrderAddress(String b_cart_order_address_id,HttpServletRequest request){
		int i = 0;
		if(null != b_cart_order_address_id && !"".equals(b_cart_order_address_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_cart_order_address_id",b_cart_order_address_id.split(","));
			i=bCartOrderAddressService.delBCartOrderAddress(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_cart_order_address_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBCartOrderAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBCartOrderAddress(String b_cart_order_address_id,HttpServletRequest request){
		int i = 0;
		BCartOrderAddress b_Cart_Order_Address = bCartOrderAddressService.getBCartOrderAddressById(b_cart_order_address_id);
		if(null != b_Cart_Order_Address && !"".equals(b_Cart_Order_Address)){
			b_Cart_Order_Address.setB_cart_order_address_id(UUID.toUUID());
			i=bCartOrderAddressService.addBCartOrderAddress(b_Cart_Order_Address);
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
	@RequestMapping(value="/exportBCartOrderAddress",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBCartOrderAddress(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
