package jehc.xtmodules.xtweb;
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
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtMenuinfoModule;
import jehc.xtmodules.xtservice.XtMenuinfoModuleService;

/**
* 菜单资源模块 
* 2018-06-21 09:22:18  邓纯杰
*/
@Controller
@RequestMapping("/xtMenuinfoModuleController")
public class XtMenuinfoModuleController extends BaseAction{
	@Autowired
	private XtMenuinfoModuleService xtMenuinfoModuleService;
	/**
	* 载入初始化页面
	* @param xt_menuinfo_module 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMenuinfoModule",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-menuinfo-module/xt-menuinfo-module-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_menuinfo_module 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoModuleListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoModuleListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtMenuinfoModule> xtMenuinfoModuleList = xtMenuinfoModuleService.getXtMenuinfoModuleListByCondition(condition);
		PageInfo<XtMenuinfoModule> page = new PageInfo<XtMenuinfoModule>(xtMenuinfoModuleList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_menuinfo_module_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoModuleById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoModuleById(String xt_menuinfo_module_id,HttpServletRequest request){
		XtMenuinfoModule xtMenuinfoModule = xtMenuinfoModuleService.getXtMenuinfoModuleById(xt_menuinfo_module_id);
		return outDataStr(xtMenuinfoModule);
	}
	/**
	* 添加
	* @param xt_menuinfo_module 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtMenuinfoModule",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule,HttpServletRequest request){
		int i = 0;
		if(null != xtMenuinfoModule && !"".equals(xtMenuinfoModule)){
			xtMenuinfoModule.setXt_menuinfo_module_id(UUID.toUUID());
			i=xtMenuinfoModuleService.addXtMenuinfoModule(xtMenuinfoModule);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_menuinfo_module 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtMenuinfoModule",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule,HttpServletRequest request){
		int i = 0;
		if(null != xtMenuinfoModule && !"".equals(xtMenuinfoModule)){
			i=xtMenuinfoModuleService.updateXtMenuinfoModule(xtMenuinfoModule);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_menuinfo_module_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtMenuinfoModule",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtMenuinfoModule(String xt_menuinfo_module_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_menuinfo_module_id && !"".equals(xt_menuinfo_module_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_module_id",xt_menuinfo_module_id.split(","));
			i=xtMenuinfoModuleService.delXtMenuinfoModule(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_menuinfo_module_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtMenuinfoModule",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtMenuinfoModule(String xt_menuinfo_module_id,HttpServletRequest request){
		int i = 0;
		XtMenuinfoModule xtMenuinfoModule = xtMenuinfoModuleService.getXtMenuinfoModuleById(xt_menuinfo_module_id);
		if(null != xtMenuinfoModule && !"".equals(xtMenuinfoModule)){
			xtMenuinfoModule.setXt_menuinfo_module_id(UUID.toUUID());
			i=xtMenuinfoModuleService.addXtMenuinfoModule(xtMenuinfoModule);
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
	@RequestMapping(value="/exportXtMenuinfoModule",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtMenuinfoModule(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtMenuinfoModuleAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtMenuinfoModuleAdd(XtMenuinfoModule xtMenuinfoModule,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-menuinfo-module/xt-menuinfo-module-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtMenuinfoModuleUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtMenuinfoModuleUpdate(String xt_menuinfo_module_id,HttpServletRequest request, Model model){
		XtMenuinfoModule xtMenuinfoModule = xtMenuinfoModuleService.getXtMenuinfoModuleById(xt_menuinfo_module_id);
		model.addAttribute("xtMenuinfoModule", xtMenuinfoModule);
		return new ModelAndView("pc/xt-view/xt-menuinfo-module/xt-menuinfo-module-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtMenuinfoModuleDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtMenuinfoModuleDetail(String xt_menuinfo_module_id,HttpServletRequest request, Model model){
		XtMenuinfoModule xtMenuinfoModule = xtMenuinfoModuleService.getXtMenuinfoModuleById(xt_menuinfo_module_id);
		model.addAttribute("xtMenuinfoModule", xtMenuinfoModule);
		return new ModelAndView("pc/xt-view/xt-menuinfo-module/xt-menuinfo-module-detail");
	}
	
	/**
	* 加载初始化列表数据并分页
	* @param xt_menuinfo_module 
	* @param request 
	*/
	@ResponseBody
	@NeedLoginUnAuth
	@RequestMapping(value="/getXtMenuinfoModuleList",method={RequestMethod.POST,RequestMethod.GET})
	public List<XtMenuinfoModule> getXtMenuinfoModuleList(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		List<XtMenuinfoModule> xtMenuinfoModuleList = xtMenuinfoModuleService.getXtMenuinfoModuleListByCondition(condition);
		return xtMenuinfoModuleList;
	}
}
