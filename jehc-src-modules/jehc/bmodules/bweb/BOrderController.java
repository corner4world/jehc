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

import jehc.bmodules.bmodel.BOrder;
import jehc.bmodules.bservice.BOrderService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础订单 
* 2016-01-27 13:55:11  邓纯杰
*/
@Controller
@RequestMapping("/bOrderController")
public class BOrderController extends BaseAction{
	@Autowired
	private BOrderService bOrderService;
	/**
	* 载入初始化页面
	* @param b_order 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBOrder",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBOrder(BOrder b_Order,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-order/b-order-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_order 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBOrderListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBOrderListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BOrder> b_OrderList = bOrderService.getBOrderListByCondition(condition);
		PageInfo<BOrder> page = new PageInfo<BOrder>(b_OrderList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_order_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBOrderById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBOrderById(String b_order_id,HttpServletRequest request){
		BOrder b_Order = bOrderService.getBOrderById(b_order_id);
		return outDataStr(b_Order);
	}
	/**
	* 添加
	* @param b_order 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBOrder",method={RequestMethod.POST,RequestMethod.GET})
	public String addBOrder(BOrder b_Order,HttpServletRequest request){
		int i = 0;
		if(null != b_Order && !"".equals(b_Order)){
			b_Order.setB_order_id(UUID.toUUID());
			i=bOrderService.addBOrder(b_Order);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_order 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBOrder",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBOrder(BOrder b_Order,HttpServletRequest request){
		int i = 0;
		if(null != b_Order && !"".equals(b_Order)){
			i=bOrderService.updateBOrder(b_Order);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_order_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBOrder",method={RequestMethod.POST,RequestMethod.GET})
	public String delBOrder(String b_order_id,HttpServletRequest request){
		int i = 0;
		if(null != b_order_id && !"".equals(b_order_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_order_id",b_order_id.split(","));
			i=bOrderService.delBOrder(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_order_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBOrder",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBOrder(String b_order_id,HttpServletRequest request){
		int i = 0;
		BOrder b_Order = bOrderService.getBOrderById(b_order_id);
		if(null != b_Order && !"".equals(b_Order)){
			b_Order.setB_order_id(UUID.toUUID());
			i=bOrderService.addBOrder(b_Order);
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
	@RequestMapping(value="/exportBOrder",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBOrder(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
