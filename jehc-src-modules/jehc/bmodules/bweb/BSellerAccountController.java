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

import jehc.bmodules.bmodel.BSellerAccount;
import jehc.bmodules.bservice.BSellerAccountService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础卖家账号 
* 2016-02-18 17:07:37  邓纯杰
*/
@Controller
@RequestMapping("/bSellerAccountController")
public class BSellerAccountController extends BaseAction{
	@Autowired
	private BSellerAccountService bSellerAccountService;
	/**
	* 载入初始化页面
	* @param b_seller_account 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBSellerAccount",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBSellerAccount(BSellerAccount b_Seller_Account,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-seller-account/b-seller-account-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_seller_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerAccountListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerAccountListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BSellerAccount> b_Seller_AccountList = bSellerAccountService.getBSellerAccountListByCondition(condition);
		PageInfo<BSellerAccount> page = new PageInfo<BSellerAccount>(b_Seller_AccountList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_seller_account_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerAccountById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerAccountById(String b_seller_account_id,HttpServletRequest request){
		BSellerAccount b_Seller_Account = bSellerAccountService.getBSellerAccountById(b_seller_account_id);
		return outDataStr(b_Seller_Account);
	}
	/**
	* 添加
	* @param b_seller_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBSellerAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String addBSellerAccount(BSellerAccount b_Seller_Account,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Account && !"".equals(b_Seller_Account)){
			b_Seller_Account.setB_seller_account_id(UUID.toUUID());
			i=bSellerAccountService.addBSellerAccount(b_Seller_Account);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_seller_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBSellerAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBSellerAccount(BSellerAccount b_Seller_Account,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Account && !"".equals(b_Seller_Account)){
			i=bSellerAccountService.updateBSellerAccount(b_Seller_Account);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_seller_account_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBSellerAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String delBSellerAccount(String b_seller_account_id,HttpServletRequest request){
		int i = 0;
		if(null != b_seller_account_id && !"".equals(b_seller_account_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_seller_account_id",b_seller_account_id.split(","));
			i=bSellerAccountService.delBSellerAccount(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_seller_account_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBSellerAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBSellerAccount(String b_seller_account_id,HttpServletRequest request){
		int i = 0;
		BSellerAccount b_Seller_Account = bSellerAccountService.getBSellerAccountById(b_seller_account_id);
		if(null != b_Seller_Account && !"".equals(b_Seller_Account)){
			b_Seller_Account.setB_seller_account_id(UUID.toUUID());
			i=bSellerAccountService.addBSellerAccount(b_Seller_Account);
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
	@RequestMapping(value="/exportBSellerAccount",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBSellerAccount(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
