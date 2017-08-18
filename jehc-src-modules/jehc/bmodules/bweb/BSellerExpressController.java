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

import jehc.bmodules.bmodel.BSellerExpress;
import jehc.bmodules.bservice.BSellerExpressService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础卖家快递 
* 2016-02-18 17:14:52  邓纯杰
*/
@Controller
@RequestMapping("/bSellerExpressController")
public class BSellerExpressController extends BaseAction{
	@Autowired
	private BSellerExpressService bSellerExpressService;
	/**
	* 载入初始化页面
	* @param b_seller_express 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBSellerExpress",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBSellerExpress(String b_seller_id ,BSellerExpress b_Seller_Express,HttpServletRequest request){
		request.setAttribute("b_seller_id", b_seller_id);
		return new ModelAndView("pc/b-view/b-seller-express/b-seller-express-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_seller_express 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerExpressListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerExpressListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BSellerExpress> b_Seller_ExpressList = bSellerExpressService.getBSellerExpressListByCondition(condition);
		PageInfo<BSellerExpress> page = new PageInfo<BSellerExpress>(b_Seller_ExpressList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_seller_express_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerExpressById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerExpressById(String b_seller_express_id,HttpServletRequest request){
		BSellerExpress b_Seller_Express = bSellerExpressService.getBSellerExpressById(b_seller_express_id);
		return outDataStr(b_Seller_Express);
	}
	/**
	* 添加
	* @param b_seller_express 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBSellerExpress",method={RequestMethod.POST,RequestMethod.GET})
	public String addBSellerExpress(BSellerExpress b_Seller_Express,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Express && !"".equals(b_Seller_Express)){
			b_Seller_Express.setB_seller_express_id(UUID.toUUID());
			b_Seller_Express.setB_seller_express_ctime(CommonUtils.getSimpleDateFormat());
			i=bSellerExpressService.addBSellerExpress(b_Seller_Express);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_seller_express 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBSellerExpress",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBSellerExpress(BSellerExpress b_Seller_Express,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Express && !"".equals(b_Seller_Express)){
			b_Seller_Express.setB_seller_express_mtime(CommonUtils.getSimpleDateFormat());
			i=bSellerExpressService.updateBSellerExpress(b_Seller_Express);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_seller_express_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBSellerExpress",method={RequestMethod.POST,RequestMethod.GET})
	public String delBSellerExpress(String b_seller_express_id,HttpServletRequest request){
		int i = 0;
		if(null != b_seller_express_id && !"".equals(b_seller_express_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_seller_express_id",b_seller_express_id.split(","));
			i=bSellerExpressService.delBSellerExpress(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_seller_express_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBSellerExpress",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBSellerExpress(String b_seller_express_id,HttpServletRequest request){
		int i = 0;
		BSellerExpress b_Seller_Express = bSellerExpressService.getBSellerExpressById(b_seller_express_id);
		if(null != b_Seller_Express && !"".equals(b_Seller_Express)){
			b_Seller_Express.setB_seller_express_id(UUID.toUUID());
			i=bSellerExpressService.addBSellerExpress(b_Seller_Express);
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
	@RequestMapping(value="/exportBSellerExpress",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBSellerExpress(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
