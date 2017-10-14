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

import jehc.bmodules.bmodel.BSellerLogin;
import jehc.bmodules.bservice.BSellerLoginService;
import jehc.xtmodules.xtcore.allutils.MD5;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础卖家登陆账号 
* 2016-02-18 17:17:12  邓纯杰
*/
@Controller
@RequestMapping("/bSellerLoginController")
public class BSellerLoginController extends BaseAction{
	@Autowired
	private BSellerLoginService bSellerLoginService;
	/**
	* 载入初始化页面
	* @param b_seller_login 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBSellerLogin(String b_seller_id,BSellerLogin b_Seller_Login,HttpServletRequest request){
		request.setAttribute("b_seller_id", b_seller_id);
		return new ModelAndView("pc/b-view/b-seller-login/b-seller-login-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_seller_login 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerLoginListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerLoginListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BSellerLogin> b_Seller_LoginList = bSellerLoginService.getBSellerLoginListByCondition(condition);
		PageInfo<BSellerLogin> page = new PageInfo<BSellerLogin>(b_Seller_LoginList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_seller_login_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerLoginById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerLoginById(String b_seller_login_id,HttpServletRequest request){
		BSellerLogin b_Seller_Login = bSellerLoginService.getBSellerLoginById(b_seller_login_id);
		return outDataStr(b_Seller_Login);
	}
	/**
	* 获取对象
	* @param b_seller_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerLogin(String b_seller_id,HttpServletRequest request){
		if(null != b_seller_id && !"".equals(b_seller_id)){
			BSellerLogin b_Seller_Login = bSellerLoginService.getBSellerLogin(b_seller_id);
			return outDataStr(b_Seller_Login);
		}
		return null;
	}
	/**
	* 添加
	* @param b_seller_login 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public String addBSellerLogin(BSellerLogin b_Seller_Login,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Login && !"".equals(b_Seller_Login)){
			b_Seller_Login.setB_seller_login_pwd(MD5.md5(b_Seller_Login.getB_seller_login_pwd()));
			b_Seller_Login.setB_seller_login_id(UUID.toUUID());
			i=bSellerLoginService.addBSellerLogin(b_Seller_Login);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_seller_login 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBSellerLogin(BSellerLogin b_Seller_Login,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Login && !"".equals(b_Seller_Login)){
			b_Seller_Login.setB_seller_login_pwd(MD5.md5(b_Seller_Login.getB_seller_login_pwd()));
			i=bSellerLoginService.updateBSellerLogin(b_Seller_Login);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_seller_login_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public String delBSellerLogin(String b_seller_login_id,HttpServletRequest request){
		int i = 0;
		if(null != b_seller_login_id && !"".equals(b_seller_login_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_seller_login_id",b_seller_login_id.split(","));
			i=bSellerLoginService.delBSellerLogin(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_seller_login_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBSellerLogin(String b_seller_login_id,HttpServletRequest request){
		int i = 0;
		BSellerLogin b_Seller_Login = bSellerLoginService.getBSellerLoginById(b_seller_login_id);
		if(null != b_Seller_Login && !"".equals(b_Seller_Login)){
			b_Seller_Login.setB_seller_login_id(UUID.toUUID());
			i=bSellerLoginService.addBSellerLogin(b_Seller_Login);
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
	@RequestMapping(value="/exportBSellerLogin",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBSellerLogin(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
