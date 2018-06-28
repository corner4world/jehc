package jehc.crmmodules.crmweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.crmmodules.crmmodel.CrmCustomer;
import jehc.crmmodules.crmmodel.CrmInvoice;
import jehc.crmmodules.crmservice.CrmCustomerService;
import jehc.crmmodules.crmservice.CrmInvoiceService;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.annotation.NeedLoginUnAuth;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtDataDictionary;

/**
* 客户基础资料 
* 2018-06-27 13:45:48  邓纯杰
*/
@Controller
@RequestMapping("/crmCustomerController")
public class CrmCustomerController extends BaseAction{
	@Autowired
	private CrmCustomerService crmCustomerService;
	@Autowired
	CrmInvoiceService crmInvoiceService;
	/**
	* 载入初始化页面
	* @param crm_customer 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCrmCustomer",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCrmCustomer(CrmCustomer crmCustomer,HttpServletRequest request){
		return new ModelAndView("pc/crm-view/crm-customer/crm-customer-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param crm_customer 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCrmCustomerListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCrmCustomerListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		dataAuthForXtUID(request,"createUser", condition);
		List<CrmCustomer> crmCustomerList = crmCustomerService.getCrmCustomerListByCondition(condition);
		PageInfo<CrmCustomer> page = new PageInfo<CrmCustomer>(crmCustomerList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param customerId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCrmCustomerById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCrmCustomerById(String customerId,HttpServletRequest request){
		CrmCustomer crmCustomer = crmCustomerService.getCrmCustomerById(customerId);
		return outDataStr(crmCustomer);
	}
	/**
	* 添加
	* @param crm_customer 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCrmCustomer",method={RequestMethod.POST,RequestMethod.GET})
	public String addCrmCustomer(CrmCustomer crmCustomer,CrmInvoice crmInvoice,HttpServletRequest request){
		int i = 0;
		if(null != crmCustomer && !"".equals(crmCustomer)){
			crmCustomer.setCustomerId(UUID.toUUID());
			crmCustomer.setCdate(getDate());
			crmCustomer.setCreateUser(getXtUid());
			i=crmCustomerService.addCrmCustomer(crmCustomer,crmInvoice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param crm_customer 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCrmCustomer",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCrmCustomer(CrmCustomer crmCustomer,CrmInvoice crmInvoice,HttpServletRequest request){
		int i = 0;
		if(null != crmCustomer && !"".equals(crmCustomer)){
			crmCustomer.setMdate(getDate());
			crmCustomer.setModifyUser(getXtUid());
			i=crmCustomerService.updateCrmCustomer(crmCustomer,crmInvoice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param customerId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCrmCustomer",method={RequestMethod.POST,RequestMethod.GET})
	public String delCrmCustomer(String customerId,HttpServletRequest request){
		int i = 0;
		if(null != customerId && !"".equals(customerId)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("customerId",customerId.split(","));
			i=crmCustomerService.delCrmCustomer(condition);
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
	@RequestMapping(value="/exportCrmCustomer",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCrmCustomer(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmCustomerAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmCustomerAdd(CrmCustomer crmCustomer,HttpServletRequest request){
		return new ModelAndView("pc/crm-view/crm-customer/crm-customer-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmCustomerUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmCustomerUpdate(String customerId,HttpServletRequest request, Model model){
		CrmCustomer crmCustomer = crmCustomerService.getCrmCustomerById(customerId);
		model.addAttribute("crmCustomer", crmCustomer);
		model.addAttribute("crmInvoice", crmInvoiceService.getCrmInvoiceSingleByCustomerId(customerId));
		model.addAttribute("crmCustomerJSON", outItemsStr(crmCustomer));
		return new ModelAndView("pc/crm-view/crm-customer/crm-customer-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmCustomerDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmCustomerDetail(String customerId,HttpServletRequest request, Model model){
		CrmCustomer crmCustomer = crmCustomerService.getCrmCustomerById(customerId);
		model.addAttribute("crmCustomer", crmCustomer);
		model.addAttribute("crmInvoice", crmInvoiceService.getCrmInvoiceSingleByCustomerId(customerId));
		model.addAttribute("crmCustomerJSON", outItemsStr(crmCustomer));
		return new ModelAndView("pc/crm-view/crm-customer/crm-customer-detail");
	}
	
	/**
	 * 获取所属行业数据字典
	 * @param industryId
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getIndustryIdList",method={RequestMethod.POST,RequestMethod.GET})
	@NeedLoginUnAuth
	public String getIndustryIdList(HttpServletRequest request,HttpServletResponse response){
		List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("industryId");
		return outComboDataStr(xtDataDictionaryList);
	}
	/**
	 * 获取公司规模数据字典
	 * @param scaleId
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getScaleId",method={RequestMethod.POST,RequestMethod.GET})
	@NeedLoginUnAuth
	public String getSCaleIdList(HttpServletRequest request,HttpServletResponse response){
		List<XtDataDictionary> xtDataDictionaryList= CommonUtils.getXtDataDictionaryCache("scaleId");
		return outComboDataStr(xtDataDictionaryList);
	}
	/**
	 * 获取年龄结构
	 * @param ageScope
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getAgeScope",method={RequestMethod.POST,RequestMethod.GET})
	@NeedLoginUnAuth
	public String getAgeScopeList(HttpServletRequest request,HttpServletResponse response){
		List<XtDataDictionary> xtDataDictionaryList= CommonUtils.getXtDataDictionaryCache("ageScope");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 分配客户
	 * @param customerId
	 * @param xt_userinfo_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCToUser",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCToUser(String customerId,String xt_userinfo_id){
		int i = 0;
		if(!StringUtil.isEmpty(customerId) && !StringUtil.isEmpty(xt_userinfo_id)){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("customerId", customerId.split(","));
			map.put("createUser", xt_userinfo_id);
			map.put("mdate", getDate());
			map.put("modifyUser", getXtUid());
			i = crmCustomerService.updateCToUser(map);
			if(i>0){
				return outAudStr(true);
			}else{
				return outAudStr(false);
			}
		}else{
			return outAudStr(true,"未能获取到用户编号或客户编号");
		}
	}
	
	/**
	 * 客户等级申请
	 * @param lcApply
	 * @param crmCustomer
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCrmLevelApply",method={RequestMethod.POST,RequestMethod.GET})
	public String addCrmLevelApply(LcApply lcApply,String customerId){
		int i = 0;
		if(null != lcApply){
			CrmCustomer crmCustomer = new CrmCustomer();
			crmCustomer.setCustomerId(customerId);
			i = crmCustomerService.addCrmLevelApply(crmCustomer, lcApply);
			if(i>0){
				return outAudStr(true);
			}else{
				return outAudStr(false);
			}
		}else{
			return outAudStr(true,"未能获取到用户编号或客户编号");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@NeedLoginUnAuth
	@RequestMapping(value="/toCrmCustomerApply",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmCustomerApply(String lc_apply_model_biz_id,HttpServletRequest request, Model model){
		CrmCustomer crmCustomer = crmCustomerService.getCrmCustomerById(lc_apply_model_biz_id);
		model.addAttribute("crmCustomer", crmCustomer);
		model.addAttribute("crmInvoice", crmInvoiceService.getCrmInvoiceSingleByCustomerId(lc_apply_model_biz_id));
		model.addAttribute("crmCustomerJSON", outItemsStr(crmCustomer));
		return new ModelAndView("pc/crm-view/crm-customer/crm-customer-apply");
	}
}
