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

import jehc.bmodules.bmodel.BSeller;
import jehc.bmodules.bservice.BSellerService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础卖家 
* 2016-01-08 22:54:00  邓纯杰
*/
@Controller
@RequestMapping("/bSellerController")
public class BSellerController extends BaseAction{
	@Autowired
	private BSellerService bSellerService;
	/**
	* 载入初始化页面
	* @param b_seller 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBSeller",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBSeller(BSeller b_Seller,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-seller/b-seller-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_seller 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BSeller> b_SellerList = bSellerService.getBSellerListByCondition(condition);
		PageInfo<BSeller> page = new PageInfo<BSeller>(b_SellerList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_seller_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerById(String b_seller_id,HttpServletRequest request){
		BSeller b_Seller = bSellerService.getBSellerById(b_seller_id);
		return outDataStr(b_Seller);
	}
	/**
	* 添加
	* @param b_seller 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBSeller",method={RequestMethod.POST,RequestMethod.GET})
	public String addBSeller(BSeller b_Seller,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller && !"".equals(b_Seller)){
			b_Seller.setB_seller_id(UUID.toUUID());
			b_Seller.setXt_userinfo_id(CommonUtils.getXtUid());
			b_Seller.setB_seller_ctime(CommonUtils.getSimpleDateFormat());
			i=bSellerService.addBSeller(b_Seller);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_seller 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBSeller",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBSeller(BSeller b_Seller,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller && !"".equals(b_Seller)){
			b_Seller.setB_seller_mtime(CommonUtils.getSimpleDateFormat());
			b_Seller.setXt_userinfo_id(CommonUtils.getXtUid());
			i=bSellerService.updateBSeller(b_Seller);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_seller_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBSeller",method={RequestMethod.POST,RequestMethod.GET})
	public String delBSeller(String b_seller_id,HttpServletRequest request){
		int i = 0;
		if(null != b_seller_id && !"".equals(b_seller_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_seller_id",b_seller_id.split(","));
			i=bSellerService.delBSeller(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_seller_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBSeller",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBSeller(String b_seller_id,HttpServletRequest request){
		int i = 0;
		BSeller b_Seller = bSellerService.getBSellerById(b_seller_id);
		if(null != b_Seller && !"".equals(b_Seller)){
			b_Seller.setB_seller_id(UUID.toUUID());
			i=bSellerService.addBSeller(b_Seller);
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
	@RequestMapping(value="/exportBSeller",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBSeller(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
