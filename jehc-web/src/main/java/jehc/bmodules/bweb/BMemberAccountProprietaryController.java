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

import jehc.bmodules.bmodel.BMemberAccountProprietary;
import jehc.bmodules.bservice.BMemberAccountProprietaryService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础专有账户 
* 2016-03-24 20:33:38  邓纯杰
*/
@Controller
@RequestMapping("/bMemberAccountProprietaryController")
public class BMemberAccountProprietaryController extends BaseAction{
	@Autowired
	private BMemberAccountProprietaryService bMemberAccountProprietaryService;
	/**
	* 载入初始化页面
	* @param b_member_account_proprietary 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBMemberAccountProprietary",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-member-account-proprietary/b-member-account-proprietary-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_member_account_proprietary 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAccountProprietaryListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAccountProprietaryListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BMemberAccountProprietary> b_Member_Account_ProprietaryList = bMemberAccountProprietaryService.getBMemberAccountProprietaryListByCondition(condition);
		PageInfo<BMemberAccountProprietary> page = new PageInfo<BMemberAccountProprietary>(b_Member_Account_ProprietaryList);
		return outPageStr(page,request);
	}
	/**
	* 查询对象 会员编号 或者专有账户编号
	* @param condition 
	* @return
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAccountProprietaryById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAccountProprietaryById(String b_member_account_proprietary_id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("b_member_account_proprietary_id", b_member_account_proprietary_id);
		BMemberAccountProprietary b_Member_Account_Proprietary = bMemberAccountProprietaryService.getBMemberAccountProprietaryById(condition);
		return outDataStr(b_Member_Account_Proprietary);
	}
	/**
	* 添加
	* @param b_member_account_proprietary 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBMemberAccountProprietary",method={RequestMethod.POST,RequestMethod.GET})
	public String addBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Account_Proprietary && !"".equals(b_Member_Account_Proprietary)){
			b_Member_Account_Proprietary.setB_member_account_proprietary_id(UUID.toUUID());
			i=bMemberAccountProprietaryService.addBMemberAccountProprietary(b_Member_Account_Proprietary);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_member_account_proprietary 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBMemberAccountProprietary",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Account_Proprietary && !"".equals(b_Member_Account_Proprietary)){
			i=bMemberAccountProprietaryService.updateBMemberAccountProprietary(b_Member_Account_Proprietary);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_member_account_proprietary_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBMemberAccountProprietary",method={RequestMethod.POST,RequestMethod.GET})
	public String delBMemberAccountProprietary(String b_member_account_proprietary_id,HttpServletRequest request){
		int i = 0;
		if(null != b_member_account_proprietary_id && !"".equals(b_member_account_proprietary_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_member_account_proprietary_id",b_member_account_proprietary_id.split(","));
			i=bMemberAccountProprietaryService.delBMemberAccountProprietary(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_member_account_proprietary_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBMemberAccountProprietary",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBMemberAccountProprietary(String b_member_account_proprietary_id,HttpServletRequest request){
		int i = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("b_member_account_proprietary_id", b_member_account_proprietary_id);
		BMemberAccountProprietary b_Member_Account_Proprietary = bMemberAccountProprietaryService.getBMemberAccountProprietaryById(condition);
		if(null != b_Member_Account_Proprietary && !"".equals(b_Member_Account_Proprietary)){
			b_Member_Account_Proprietary.setB_member_account_proprietary_id(UUID.toUUID());
			i=bMemberAccountProprietaryService.addBMemberAccountProprietary(b_Member_Account_Proprietary);
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
	@RequestMapping(value="/exportBMemberAccountProprietary",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBMemberAccountProprietary(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
