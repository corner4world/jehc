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
import jehc.xtmodules.xtcore.util.UUID;
import jehc.cmsmodules.cmsmodel.CmsMerchants;
import jehc.cmsmodules.cmsservice.CmsMerchantsService;

/**
* 内容发布平台招商加盟 
* 2018-06-10 14:47:12  邓纯杰
*/
@Controller
@RequestMapping("/cmsMerchantsController")
public class CmsMerchantsController extends BaseAction{
	@Autowired
	private CmsMerchantsService cmsMerchantsService;
	/**
	* 载入初始化页面
	* @param cms_merchants 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsMerchants",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsMerchants(CmsMerchants cmsMerchants,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_merchants 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsMerchantsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsMerchantsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsMerchants> cmsMerchantsList = cmsMerchantsService.getCmsMerchantsListByCondition(condition);
		PageInfo<CmsMerchants> page = new PageInfo<CmsMerchants>(cmsMerchantsList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_merchants_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsMerchantsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsMerchantsById(String cms_merchants_id,HttpServletRequest request){
		CmsMerchants cmsMerchants = cmsMerchantsService.getCmsMerchantsById(cms_merchants_id);
		return outDataStr(cmsMerchants);
	}
	/**
	* 添加
	* @param cms_merchants 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsMerchants",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsMerchants(CmsMerchants cmsMerchants,HttpServletRequest request){
		int i = 0;
		if(null != cmsMerchants && !"".equals(cmsMerchants)){
			cmsMerchants.setCms_merchants_id(UUID.toUUID());
			i=cmsMerchantsService.addCmsMerchants(cmsMerchants);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_merchants 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsMerchants",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsMerchants(CmsMerchants cmsMerchants,HttpServletRequest request){
		int i = 0;
		if(null != cmsMerchants && !"".equals(cmsMerchants)){
			i=cmsMerchantsService.updateCmsMerchants(cmsMerchants);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_merchants_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsMerchants",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsMerchants(String cms_merchants_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_merchants_id && !"".equals(cms_merchants_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_merchants_id",cms_merchants_id.split(","));
			i=cmsMerchantsService.delCmsMerchants(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_merchants_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsMerchants",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsMerchants(String cms_merchants_id,HttpServletRequest request){
		int i = 0;
		CmsMerchants cmsMerchants = cmsMerchantsService.getCmsMerchantsById(cms_merchants_id);
		if(null != cmsMerchants && !"".equals(cmsMerchants)){
			cmsMerchants.setCms_merchants_id(UUID.toUUID());
			i=cmsMerchantsService.addCmsMerchants(cmsMerchants);
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
	@RequestMapping(value="/exportCmsMerchants",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsMerchants(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMerchantsAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMerchantsAdd(CmsMerchants cmsMerchants,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMerchantsUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMerchantsUpdate(String cms_merchants_id,HttpServletRequest request, Model model){
		CmsMerchants cmsMerchants = cmsMerchantsService.getCmsMerchantsById(cms_merchants_id);
		model.addAttribute("cmsMerchants", cmsMerchants);
		return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsMerchantsDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsMerchantsDetail(String cms_merchants_id,HttpServletRequest request, Model model){
		CmsMerchants cmsMerchants = cmsMerchantsService.getCmsMerchantsById(cms_merchants_id);
		model.addAttribute("cmsMerchants", cmsMerchants);
		return new ModelAndView("pc/cms-view/cms-merchants/cms-merchants-detail");
	}
}
