package jehc.crmmodules.crmweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.annotation.NeedLoginUnAuth;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtUserinfoService;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.crmmodules.crmmodel.CrmCustomer;
import jehc.crmmodules.crmmodel.CrmFollowup;
import jehc.crmmodules.crmservice.CrmCustomerService;
import jehc.crmmodules.crmservice.CrmFollowupService;

/**
* 客户跟进日志 
* 2018-04-10 15:36:12  赵贵志
*/
@Controller
@RequestMapping("/crmFollowupController")
public class CrmFollowupController extends BaseAction{
	@Autowired
	private CrmFollowupService crmFollowupService;
	@Autowired
	private CrmCustomerService crmCustomerService;
	@Autowired
	private XtUserinfoService xtUserinfoService;
	/**
	* 载入初始化页面
	* @param crm_followup 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCrmFollowup",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCrmFollowup(CrmFollowup crmFollowup,Model model,HttpServletRequest request){
		model.addAttribute("crmFollowup", crmFollowup);
		model.addAttribute("crmCustomer", crmCustomerService.getCrmCustomerById(crmFollowup.getCustomerId()));
		return new ModelAndView("pc/crm-view/crm-followup/crm-followup-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param crm_followup 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCrmFollowupListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCrmFollowupListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CrmFollowup> crmFollowupList = crmFollowupService.getCrmFollowupListByCondition(condition);
		PageInfo<CrmFollowup> page = new PageInfo<CrmFollowup>(crmFollowupList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param followupId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCrmFollowupById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCrmFollowupById(String followupId,HttpServletRequest request){
		CrmFollowup crmFollowup = crmFollowupService.getCrmFollowupById(followupId);
		return outDataStr(crmFollowup);
	}
	/**
	* 载入初始化页面
	* @param crm_customer 
	* @param request 
	* @return
	*/
	@NeedLoginUnAuth
	@RequestMapping(value="/loadCrmCustomer",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCrmCustomer(CrmCustomer crmCustomer,HttpServletRequest request){
		crmCustomer.setCustomerId(UUID.toUUID());
		crmCustomer.setCdate(getDate());
		crmCustomer.setCreateUser(getXtUid());
		return new ModelAndView("pc/crm-view/crm-customer/crm-customer-list");
	}
	/**
	* 添加
	* @param crm_followup 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCrmFollowup",method={RequestMethod.POST,RequestMethod.GET})
	public String addCrmFollowup(CrmFollowup crmFollowup,HttpServletRequest request){
		int i = 0;
		if(null != crmFollowup && !"".equals(crmFollowup)){
			crmFollowup.setFollowupId(UUID.toUUID());
			crmFollowup.setCtime(getDate());
			crmFollowup.setXt_userinfo_id(getXtUid());
			i=crmFollowupService.addCrmFollowup(crmFollowup);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param crm_followup 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCrmFollowup",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCrmFollowup(CrmFollowup crmFollowup,HttpServletRequest request){
		int i = 0;
		if(null != crmFollowup && !"".equals(crmFollowup)){
			i=crmFollowupService.updateCrmFollowup(crmFollowup);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param followupId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCrmFollowup",method={RequestMethod.POST,RequestMethod.GET})
	public String delCrmFollowup(String followupId,HttpServletRequest request){
		int i = 0;
		if(null != followupId && !"".equals(followupId)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("followupId",followupId.split(","));
			i=crmFollowupService.delCrmFollowup(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param followupId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCrmFollowup",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCrmFollowup(String followupId,HttpServletRequest request){
		int i = 0;
		CrmFollowup crmFollowup = crmFollowupService.getCrmFollowupById(followupId);
		if(null != crmFollowup && !"".equals(crmFollowup)){
			crmFollowup.setFollowupId(UUID.toUUID());
			i=crmFollowupService.addCrmFollowup(crmFollowup);
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
	@RequestMapping(value="/exportCrmFollowup",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCrmFollowup(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmFollowupAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmFollowupAdd(CrmFollowup crmFollowup,Model model,HttpServletRequest request){
		model.addAttribute("crmFollowup", crmFollowup);
		model.addAttribute("crmCustomer", crmCustomerService.getCrmCustomerById(crmFollowup.getCustomerId()));
		return new ModelAndView("pc/crm-view/crm-followup/crm-followup-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmFollowupUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmFollowupUpdate(String followupId,HttpServletRequest request, Model model){
		CrmFollowup crmFollowup = crmFollowupService.getCrmFollowupById(followupId);
		model.addAttribute("crmFollowup", crmFollowup);
		return new ModelAndView("pc/crm-view/crm-followup/crm-followup-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmFollowupDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmFollowupDetail(String followupId,HttpServletRequest request, Model model){
//		model.addAttribute("xt_userinfo_realName",xtUserinfoService.getXtUserinfoById(crmFollowup.getXt_userinfo_realName()));
		CrmFollowup crmFollowup = crmFollowupService.getCrmFollowupById(followupId);
		model.addAttribute("crmFollowup", crmFollowup);
		return new ModelAndView("pc/crm-view/crm-followup/crm-followup-detail");
	}
}
