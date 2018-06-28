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
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.crmmodules.crmmodel.CrmContact;
import jehc.crmmodules.crmmodel.CrmCustomer;
import jehc.crmmodules.crmservice.CrmContactService;
import jehc.crmmodules.crmservice.CrmCustomerService;

/**
* 客户联系人 
* 2018-04-10 15:20:35  赵贵志
*/
@Controller
@RequestMapping("/crmContactController")
public class CrmContactController extends BaseAction{
	@Autowired
	private CrmContactService crmContactService;
	@Autowired
	private CrmCustomerService crmCustomerService;
	/**
	* 载入初始化页面
	* @param crm_contact 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCrmContact",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCrmContact(CrmContact crmContact,HttpServletRequest request,Model model){
		model.addAttribute("crmContact", crmContact);
		model.addAttribute("crmCustomer", crmCustomerService.getCrmCustomerById(crmContact.getCustomerId()));
		return new ModelAndView("pc/crm-view/crm-contact/crm-contact-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param crm_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCrmContactListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCrmContactListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CrmContact> crmContactList = crmContactService.getCrmContactListByCondition(condition);
		PageInfo<CrmContact> page = new PageInfo<CrmContact>(crmContactList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param contactId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCrmContactById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCrmContactById(String contactId,HttpServletRequest request){
		CrmContact crmContact = crmContactService.getCrmContactById(contactId);
		return outDataStr(crmContact);
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
	* @param crm_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCrmContact",method={RequestMethod.POST,RequestMethod.GET})
	public String addCrmContact(CrmContact crmContact,HttpServletRequest request){
		int i = 0;
		if(null != crmContact && !"".equals(crmContact)){
			crmContact.setContactId(UUID.toUUID());
			i=crmContactService.addCrmContact(crmContact);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param crm_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCrmContact",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCrmContact(CrmContact crmContact,HttpServletRequest request){
		int i = 0;
		if(null != crmContact && !"".equals(crmContact)){
			i=crmContactService.updateCrmContact(crmContact);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param contactId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCrmContact",method={RequestMethod.POST,RequestMethod.GET})
	public String delCrmContact(String contactId,HttpServletRequest request){
		int i = 0;
		if(null != contactId && !"".equals(contactId)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("contactId",contactId.split(","));
			i=crmContactService.delCrmContact(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param contactId 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCrmContact",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCrmContact(String contactId,HttpServletRequest request){
		int i = 0;
		CrmContact crmContact = crmContactService.getCrmContactById(contactId);
		if(null != crmContact && !"".equals(crmContact)){
			crmContact.setContactId(UUID.toUUID());
			i=crmContactService.addCrmContact(crmContact);
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
	@RequestMapping(value="/exportCrmContact",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCrmContact(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmContactAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmContactAdd(CrmContact crmContact,HttpServletRequest request,Model model){
		model.addAttribute("crmContact", crmContact);
		model.addAttribute("crmCustomer", crmCustomerService.getCrmCustomerById(crmContact.getCustomerId()));
		return new ModelAndView("pc/crm-view/crm-contact/crm-contact-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmContactUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmContactUpdate(String contactId,HttpServletRequest request, Model model){
		CrmContact crmContact = crmContactService.getCrmContactById(contactId);
		model.addAttribute("crmContact", crmContact);
		return new ModelAndView("pc/crm-view/crm-contact/crm-contact-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCrmContactDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCrmContactDetail(String contactId,HttpServletRequest request, Model model){
		CrmContact crmContact = crmContactService.getCrmContactById(contactId);
		model.addAttribute("crmContact", crmContact);
		return new ModelAndView("pc/crm-view/crm-contact/crm-contact-detail");
	}
	/**
	 * 获取岗位数据字典
	 * @param postId
	 * @param request
	 * @param response
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/getPostId",method={RequestMethod.POST,RequestMethod.GET})
	@NeedLoginUnAuth
	public String getPostIdList(HttpServletRequest request,HttpServletResponse response){
		List<XtDataDictionary> xtDataDictionaryList=CommonUtils.getXtDataDictionaryCache("postId");
		return outComboDataStr(xtDataDictionaryList);
	}
}
