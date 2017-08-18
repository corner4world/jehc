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

import jehc.bmodules.bmodel.BOrderPay;
import jehc.bmodules.bservice.BOrderPayService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础订单支付 
* 2016-03-22 16:47:52  邓纯杰
*/
@Controller
@RequestMapping("/bOrderPayController")
public class BOrderPayController extends BaseAction{
	@Autowired
	private BOrderPayService bOrderPayService;
	/**
	* 载入初始化页面
	* @param b_order_pay 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBOrderPay",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBOrderPay(BOrderPay b_Order_Pay,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-order-pay/b-order-pay-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_order_pay 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBOrderPayListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBOrderPayListByCondition(BOrderPay b_Order_Pay,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("b_order_id", b_Order_Pay.getB_order_id());
		List<BOrderPay> b_Order_PayList = bOrderPayService.getBOrderPayListByCondition(condition);
		return outItemsStr(b_Order_PayList);
	}
	/**
	* 获取对象
	* @param b_order_pay_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBOrderPayById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBOrderPayById(String b_order_pay_id,HttpServletRequest request){
		BOrderPay b_Order_Pay = bOrderPayService.getBOrderPayById(b_order_pay_id);
		return outDataStr(b_Order_Pay);
	}
	/**
	* 添加
	* @param b_order_pay 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBOrderPay",method={RequestMethod.POST,RequestMethod.GET})
	public String addBOrderPay(BOrderPay b_Order_Pay,HttpServletRequest request){
		int i = 0;
		if(null != b_Order_Pay && !"".equals(b_Order_Pay)){
			b_Order_Pay.setB_order_pay_id(UUID.toUUID());
			i=bOrderPayService.addBOrderPay(b_Order_Pay);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_order_pay 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBOrderPay",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBOrderPay(BOrderPay b_Order_Pay,HttpServletRequest request){
		int i = 0;
		if(null != b_Order_Pay && !"".equals(b_Order_Pay)){
			i=bOrderPayService.updateBOrderPay(b_Order_Pay);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_order_pay_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBOrderPay",method={RequestMethod.POST,RequestMethod.GET})
	public String delBOrderPay(String b_order_pay_id,HttpServletRequest request){
		int i = 0;
		if(null != b_order_pay_id && !"".equals(b_order_pay_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_order_pay_id",b_order_pay_id.split(","));
			i=bOrderPayService.delBOrderPay(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_order_pay_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBOrderPay",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBOrderPay(String b_order_pay_id,HttpServletRequest request){
		int i = 0;
		BOrderPay b_Order_Pay = bOrderPayService.getBOrderPayById(b_order_pay_id);
		if(null != b_Order_Pay && !"".equals(b_Order_Pay)){
			b_Order_Pay.setB_order_pay_id(UUID.toUUID());
			i=bOrderPayService.addBOrderPay(b_Order_Pay);
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
	@RequestMapping(value="/exportBOrderPay",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBOrderPay(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
