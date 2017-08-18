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

import jehc.bmodules.bmodel.BSellerContacts;
import jehc.bmodules.bservice.BSellerContactsService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础卖家联系人 
* 2016-02-18 17:11:48  邓纯杰
*/
@Controller
@RequestMapping("/bSellerContactsController")
public class BSellerContactsController extends BaseAction{
	@Autowired
	private BSellerContactsService bSellerContactsService;
	/**
	* 载入初始化页面
	* @param b_seller_contacts 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBSellerContacts",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBSellerContacts(BSellerContacts b_Seller_Contacts,HttpServletRequest request,String b_seller_id){
		request.setAttribute("b_seller_id", b_seller_id);
		return new ModelAndView("pc/b-view/b-seller-contacts/b-seller-contacts-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_seller_contacts 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerContactsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerContactsListByCondition(String b_seller_id,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BSellerContacts> b_Seller_ContactsList = bSellerContactsService.getBSellerContactsListByCondition(condition);
		PageInfo<BSellerContacts> page = new PageInfo<BSellerContacts>(b_Seller_ContactsList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_seller_contacts_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBSellerContactsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBSellerContactsById(String b_seller_contacts_id,HttpServletRequest request){
		BSellerContacts b_Seller_Contacts = bSellerContactsService.getBSellerContactsById(b_seller_contacts_id);
		return outDataStr(b_Seller_Contacts);
	}
	/**
	* 添加
	* @param b_seller_contacts 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBSellerContacts",method={RequestMethod.POST,RequestMethod.GET})
	public String addBSellerContacts(BSellerContacts b_Seller_Contacts,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Contacts && !"".equals(b_Seller_Contacts)){
			b_Seller_Contacts.setB_seller_contacts_id(UUID.toUUID());
			i=bSellerContactsService.addBSellerContacts(b_Seller_Contacts);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_seller_contacts 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBSellerContacts",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBSellerContacts(BSellerContacts b_Seller_Contacts,HttpServletRequest request){
		int i = 0;
		if(null != b_Seller_Contacts && !"".equals(b_Seller_Contacts)){
			i=bSellerContactsService.updateBSellerContacts(b_Seller_Contacts);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_seller_contacts_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBSellerContacts",method={RequestMethod.POST,RequestMethod.GET})
	public String delBSellerContacts(String b_seller_contacts_id,HttpServletRequest request){
		int i = 0;
		if(null != b_seller_contacts_id && !"".equals(b_seller_contacts_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_seller_contacts_id",b_seller_contacts_id.split(","));
			i=bSellerContactsService.delBSellerContacts(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_seller_contacts_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBSellerContacts",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBSellerContacts(String b_seller_contacts_id,HttpServletRequest request){
		int i = 0;
		BSellerContacts b_Seller_Contacts = bSellerContactsService.getBSellerContactsById(b_seller_contacts_id);
		if(null != b_Seller_Contacts && !"".equals(b_Seller_Contacts)){
			b_Seller_Contacts.setB_seller_contacts_id(UUID.toUUID());
			i=bSellerContactsService.addBSellerContacts(b_Seller_Contacts);
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
	@RequestMapping(value="/exportBSellerContacts",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBSellerContacts(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
