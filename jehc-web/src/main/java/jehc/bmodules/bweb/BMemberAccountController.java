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

import jehc.bmodules.bmodel.BMemberAccount;
import jehc.bmodules.bservice.BMemberAccountService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础会员余额账户 
* 2016-03-24 20:30:14  邓纯杰
*/
@Controller
@RequestMapping("/bMemberAccountController")
public class BMemberAccountController extends BaseAction{
	@Autowired
	private BMemberAccountService bMemberAccountService;
	/**
	* 载入初始化页面
	* @param b_member_account 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBMemberAccount",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBMemberAccount(BMemberAccount b_Member_Account,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-member-account/b-member-account-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_member_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAccountListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAccountListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BMemberAccount> b_Member_AccountList = bMemberAccountService.getBMemberAccountListByCondition(condition);
		PageInfo<BMemberAccount> page = new PageInfo<BMemberAccount>(b_Member_AccountList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_member_account_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAccountById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAccountById(String b_member_account_id,HttpServletRequest request){
		BMemberAccount b_Member_Account = bMemberAccountService.getBMemberAccountById(b_member_account_id);
		return outDataStr(b_Member_Account);
	}
	/**
	* 添加
	* @param b_member_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBMemberAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String addBMemberAccount(BMemberAccount b_Member_Account,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Account && !"".equals(b_Member_Account)){
			b_Member_Account.setB_member_account_id(UUID.toUUID());
			i=bMemberAccountService.addBMemberAccount(b_Member_Account);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_member_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBMemberAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBMemberAccount(BMemberAccount b_Member_Account,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Account && !"".equals(b_Member_Account)){
			i=bMemberAccountService.updateBMemberAccount(b_Member_Account);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_member_account_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBMemberAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String delBMemberAccount(String b_member_account_id,HttpServletRequest request){
		int i = 0;
		if(null != b_member_account_id && !"".equals(b_member_account_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_member_account_id",b_member_account_id.split(","));
			i=bMemberAccountService.delBMemberAccount(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_member_account_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBMemberAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBMemberAccount(String b_member_account_id,HttpServletRequest request){
		int i = 0;
		BMemberAccount b_Member_Account = bMemberAccountService.getBMemberAccountById(b_member_account_id);
		if(null != b_Member_Account && !"".equals(b_Member_Account)){
			b_Member_Account.setB_member_account_id(UUID.toUUID());
			i=bMemberAccountService.addBMemberAccount(b_Member_Account);
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
	@RequestMapping(value="/exportBMemberAccount",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBMemberAccount(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
