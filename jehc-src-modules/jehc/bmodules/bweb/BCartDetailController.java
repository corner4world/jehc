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

import jehc.bmodules.bmodel.BCartDetail;
import jehc.bmodules.bservice.BCartDetailService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础购物车明细 
* 2016-01-27 13:52:21  邓纯杰
*/
@Controller
@RequestMapping("/bCartDetailController")
public class BCartDetailController extends BaseAction{
	@Autowired
	private BCartDetailService bCartDetailService;
	/**
	* 载入初始化页面
	* @param b_cart_detail 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBCartDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBCartDetail(BCartDetail b_Cart_Detail,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-cart-detail/b-cart-detail-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_cart_detail 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCartDetailListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCartDetailListByCondition(BCartDetail b_Cart_Detail,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("b_cart_id", b_Cart_Detail.getB_cart_id());
		List<BCartDetail> b_Cart_DetailList = bCartDetailService.getBCartDetailListByCondition(condition);
		return outItemsStr(b_Cart_DetailList);
	}
	/**
	* 获取对象
	* @param b_cart_detail_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCartDetailById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCartDetailById(String b_cart_detail_id,HttpServletRequest request){
		BCartDetail b_Cart_Detail = bCartDetailService.getBCartDetailById(b_cart_detail_id);
		return outDataStr(b_Cart_Detail);
	}
	/**
	* 添加
	* @param b_cart_detail 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBCartDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String addBCartDetail(BCartDetail b_Cart_Detail,HttpServletRequest request){
		int i = 0;
		if(null != b_Cart_Detail && !"".equals(b_Cart_Detail)){
			b_Cart_Detail.setB_cart_detail_id(UUID.toUUID());
			i=bCartDetailService.addBCartDetail(b_Cart_Detail);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_cart_detail 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBCartDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBCartDetail(BCartDetail b_Cart_Detail,HttpServletRequest request){
		int i = 0;
		if(null != b_Cart_Detail && !"".equals(b_Cart_Detail)){
			i=bCartDetailService.updateBCartDetail(b_Cart_Detail);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_cart_detail_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBCartDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String delBCartDetail(String b_cart_detail_id,HttpServletRequest request){
		int i = 0;
		if(null != b_cart_detail_id && !"".equals(b_cart_detail_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_cart_detail_id",b_cart_detail_id.split(","));
			i=bCartDetailService.delBCartDetail(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_cart_detail_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBCartDetail",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBCartDetail(String b_cart_detail_id,HttpServletRequest request){
		int i = 0;
		BCartDetail b_Cart_Detail = bCartDetailService.getBCartDetailById(b_cart_detail_id);
		if(null != b_Cart_Detail && !"".equals(b_Cart_Detail)){
			b_Cart_Detail.setB_cart_detail_id(UUID.toUUID());
			i=bCartDetailService.addBCartDetail(b_Cart_Detail);
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
	@RequestMapping(value="/exportBCartDetail",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBCartDetail(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
