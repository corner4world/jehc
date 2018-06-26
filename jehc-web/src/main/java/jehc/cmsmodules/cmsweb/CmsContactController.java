package jehc.cmsmodules.cmsweb;
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
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.cmsmodules.cmsmodel.CmsAbout;
import jehc.cmsmodules.cmsmodel.CmsContact;
import jehc.cmsmodules.cmsservice.CmsContactService;

/**
* 内容发布平台联系我们 
* 2018-06-10 14:42:49  邓纯杰
*/
@Controller
@RequestMapping("/cmsContactController")
public class CmsContactController extends BaseAction{
	@Autowired
	private CmsContactService cmsContactService;
	/**
	* 载入初始化页面
	* @param cms_contact 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsContact",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsContact(CmsContact cmsContact,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-contact/cms-contact-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsContactListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsContactListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsContact> cmsContactList = cmsContactService.getCmsContactListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsContactList.size(); i++){
			cmsContactList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsContactList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsContact> page = new PageInfo<CmsContact>(cmsContactList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_contact_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsContactById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsContactById(String cms_contact_id,HttpServletRequest request){
		CmsContact cmsContact = cmsContactService.getCmsContactById(cms_contact_id);
		return outDataStr(cmsContact);
	}
	/**
	* 添加
	* @param cms_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsContact",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsContact(CmsContact cmsContact,HttpServletRequest request){
		int i = 0;
		if(null != cmsContact && !"".equals(cmsContact)){
			cmsContact.setCms_contact_id(UUID.toUUID());
			cmsContact.setCtime(getDate());
			cmsContact.setXt_userinfo_id(getXtUid());
			i=cmsContactService.addCmsContact(cmsContact);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_contact 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsContact",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsContact(CmsContact cmsContact,HttpServletRequest request){
		int i = 0;
		if(null != cmsContact && !"".equals(cmsContact)){
			cmsContact.setMtime(getDate());
			i=cmsContactService.updateCmsContact(cmsContact);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_contact_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsContact",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsContact(String cms_contact_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_contact_id && !"".equals(cms_contact_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_contact_id",cms_contact_id.split(","));
			i=cmsContactService.delCmsContact(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_contact_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsContact",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsContact(String cms_contact_id,HttpServletRequest request){
		int i = 0;
		CmsContact cmsContact = cmsContactService.getCmsContactById(cms_contact_id);
		if(null != cmsContact && !"".equals(cmsContact)){
			cmsContact.setCms_contact_id(UUID.toUUID());
			i=cmsContactService.addCmsContact(cmsContact);
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
	@RequestMapping(value="/exportCmsContact",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsContact(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsContactAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsContactAdd(CmsContact cmsContact,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-contact/cms-contact-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsContactUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsContactUpdate(String cms_contact_id,HttpServletRequest request, Model model){
		CmsContact cmsContact = cmsContactService.getCmsContactById(cms_contact_id);
		model.addAttribute("cmsContact", cmsContact);
		return new ModelAndView("pc/cms-view/cms-contact/cms-contact-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsContactDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsContactDetail(String cms_contact_id,HttpServletRequest request, Model model){
		CmsContact cmsContact = cmsContactService.getCmsContactById(cms_contact_id);
		model.addAttribute("cmsContact", cmsContact);
		return new ModelAndView("pc/cms-view/cms-contact/cms-contact-detail");
	}
}
