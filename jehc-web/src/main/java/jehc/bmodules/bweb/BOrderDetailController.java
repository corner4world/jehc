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

import jehc.bmodules.bmodel.BOrderDetail;
import jehc.bmodules.bservice.BOrderDetailService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础订单详细 
* 2016-01-27 13:59:04  邓纯杰
*/
@Controller
@RequestMapping("/bOrderDetailController")
public class BOrderDetailController extends BaseAction{
	@Autowired
	private BOrderDetailService bOrderDetailService;
	/**
	* 载入初始化页面
	* @param b_order_detail 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBOrderDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBOrderDetail(BOrderDetail b_Order_Detail,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-order-detail/b-order-detail-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_order_detail 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBOrderDetailListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBOrderDetailListByCondition(BOrderDetail b_Order_Detail,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("b_order_id", b_Order_Detail.getB_order_id());
		List<BOrderDetail> b_Order_DetailList = bOrderDetailService.getBOrderDetailListByCondition(condition);
		return outItemsStr(b_Order_DetailList);
	}
	/**
	* 获取对象
	* @param b_order_detail_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBOrderDetailById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBOrderDetailById(String b_order_detail_id,HttpServletRequest request){
		BOrderDetail b_Order_Detail = bOrderDetailService.getBOrderDetailById(b_order_detail_id);
		return outDataStr(b_Order_Detail);
	}
	/**
	* 添加
	* @param b_order_detail 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBOrderDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String addBOrderDetail(BOrderDetail b_Order_Detail,HttpServletRequest request){
		int i = 0;
		if(null != b_Order_Detail && !"".equals(b_Order_Detail)){
			b_Order_Detail.setB_order_detail_id(UUID.toUUID());
			i=bOrderDetailService.addBOrderDetail(b_Order_Detail);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_order_detail 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBOrderDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBOrderDetail(BOrderDetail b_Order_Detail,HttpServletRequest request){
		int i = 0;
		if(null != b_Order_Detail && !"".equals(b_Order_Detail)){
			i=bOrderDetailService.updateBOrderDetail(b_Order_Detail);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_order_detail_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBOrderDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String delBOrderDetail(String b_order_detail_id,HttpServletRequest request){
		int i = 0;
		if(null != b_order_detail_id && !"".equals(b_order_detail_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_order_detail_id",b_order_detail_id.split(","));
			i=bOrderDetailService.delBOrderDetail(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_order_detail_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBOrderDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBOrderDetail(String b_order_detail_id,HttpServletRequest request){
		int i = 0;
		BOrderDetail b_Order_Detail = bOrderDetailService.getBOrderDetailById(b_order_detail_id);
		if(null != b_Order_Detail && !"".equals(b_Order_Detail)){
			b_Order_Detail.setB_order_detail_id(UUID.toUUID());
			i=bOrderDetailService.addBOrderDetail(b_Order_Detail);
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
	@RequestMapping(value="/exportBOrderDetail",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBOrderDetail(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
