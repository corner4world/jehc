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

import jehc.bmodules.bmodel.BMemberAccountProprietaryHis;
import jehc.bmodules.bservice.BMemberAccountProprietaryHisService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础专有账户充值记录 
* 2016-03-24 20:48:25  邓纯杰
*/
@Controller
@RequestMapping("/bMemberAccountProprietaryHisController")
public class BMemberAccountProprietaryHisController extends BaseAction{
	@Autowired
	private BMemberAccountProprietaryHisService bMemberAccountProprietaryHisService;
	/**
	* 载入初始化页面
	* @param b_member_account_proprietary_his 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBMemberAccountProprietaryHis",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-member-account-proprietary-his/b-member-account-proprietary-his-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_member_account_proprietary_his 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAccountProprietaryHisListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAccountProprietaryHisListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BMemberAccountProprietaryHis> b_Member_Account_Proprietary_HisList = bMemberAccountProprietaryHisService.getBMemberAccountProprietaryHisListByCondition(condition);
		PageInfo<BMemberAccountProprietaryHis> page = new PageInfo<BMemberAccountProprietaryHis>(b_Member_Account_Proprietary_HisList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_member_account_proprietary_his_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAccountProprietaryHisById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAccountProprietaryHisById(String b_member_account_proprietary_his_id,HttpServletRequest request){
		BMemberAccountProprietaryHis b_Member_Account_Proprietary_His = bMemberAccountProprietaryHisService.getBMemberAccountProprietaryHisById(b_member_account_proprietary_his_id);
		return outDataStr(b_Member_Account_Proprietary_His);
	}
	/**
	* 添加
	* @param b_member_account_proprietary_his 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBMemberAccountProprietaryHis",method={RequestMethod.POST,RequestMethod.GET})
	public String addBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Account_Proprietary_His && !"".equals(b_Member_Account_Proprietary_His)){
			b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_id(UUID.toUUID());
			i=bMemberAccountProprietaryHisService.addBMemberAccountProprietaryHis(b_Member_Account_Proprietary_His);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_member_account_proprietary_his 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBMemberAccountProprietaryHis",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Account_Proprietary_His && !"".equals(b_Member_Account_Proprietary_His)){
			i=bMemberAccountProprietaryHisService.updateBMemberAccountProprietaryHis(b_Member_Account_Proprietary_His);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_member_account_proprietary_his_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBMemberAccountProprietaryHis",method={RequestMethod.POST,RequestMethod.GET})
	public String delBMemberAccountProprietaryHis(String b_member_account_proprietary_his_id,HttpServletRequest request){
		int i = 0;
		if(null != b_member_account_proprietary_his_id && !"".equals(b_member_account_proprietary_his_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_member_account_proprietary_his_id",b_member_account_proprietary_his_id.split(","));
			i=bMemberAccountProprietaryHisService.delBMemberAccountProprietaryHis(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_member_account_proprietary_his_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBMemberAccountProprietaryHis",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBMemberAccountProprietaryHis(String b_member_account_proprietary_his_id,HttpServletRequest request){
		int i = 0;
		BMemberAccountProprietaryHis b_Member_Account_Proprietary_His = bMemberAccountProprietaryHisService.getBMemberAccountProprietaryHisById(b_member_account_proprietary_his_id);
		if(null != b_Member_Account_Proprietary_His && !"".equals(b_Member_Account_Proprietary_His)){
			b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_id(UUID.toUUID());
			i=bMemberAccountProprietaryHisService.addBMemberAccountProprietaryHis(b_Member_Account_Proprietary_His);
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
	@RequestMapping(value="/exportBMemberAccountProprietaryHis",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBMemberAccountProprietaryHis(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
