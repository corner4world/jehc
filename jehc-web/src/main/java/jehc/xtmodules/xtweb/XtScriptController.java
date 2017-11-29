package jehc.xtmodules.xtweb;
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

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtScript;
import jehc.xtmodules.xtservice.XtScriptService;

/**
* 平台脚本 
* 2016-06-15 17:08:31  邓纯杰
*/
@Controller
@RequestMapping("/xtScriptController")
public class XtScriptController extends BaseAction{
	@Autowired
	private XtScriptService xtScriptService;
	/**
	* 载入初始化页面
	* @param xt_script 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtScript",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtScript(XtScript xt_Script,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-script/xt-script-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_script 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtScriptListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtScriptListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtScript> xt_ScriptList = xtScriptService.getXtScriptListByCondition(condition);
		PageInfo<XtScript> page = new PageInfo<XtScript>(xt_ScriptList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_script_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtScriptById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtScriptById(String xt_script_id,HttpServletRequest request){
		XtScript xt_Script = xtScriptService.getXtScriptById(xt_script_id);
		return outDataStr(xt_Script);
	}
	/**
	* 添加
	* @param xt_script 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtScript",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtScript(XtScript xt_Script,HttpServletRequest request){
		int i = 0;
		if(null != xt_Script && !"".equals(xt_Script)){
			xt_Script.setXt_script_id(UUID.toUUID());
			xt_Script.setXt_script_text(request.getParameter("xt_script_text"));
			xt_Script.setXt_script_ctime(getSimpleDateFormat());
			xt_Script.setXt_userinfo_id(getXtUid());
			xt_Script.setXt_script_content(request.getParameter("xt_script_content"));
			i=xtScriptService.addXtScript(xt_Script);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_script 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtScript",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtScript(XtScript xt_Script,HttpServletRequest request){
		int i = 0;
		if(null != xt_Script && !"".equals(xt_Script)){
			xt_Script.setXt_script_mtime(getSimpleDateFormat());
			xt_Script.setXt_userinfo_id(getXtUid());
			xt_Script.setXt_script_content(request.getParameter("xt_script_content"));
			xt_Script.setXt_script_text(request.getParameter("xt_script_text"));
			i=xtScriptService.updateXtScript(xt_Script);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_script_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtScript",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtScript(String xt_script_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_script_id && !"".equals(xt_script_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_script_id",xt_script_id.split(","));
			i=xtScriptService.delXtScript(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_script_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtScript",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtScript(String xt_script_id,HttpServletRequest request){
		int i = 0;
		XtScript xt_Script = xtScriptService.getXtScriptById(xt_script_id);
		if(null != xt_Script && !"".equals(xt_Script)){
			xt_Script.setXt_script_id(UUID.toUUID());
			xt_Script.setXt_script_ctime(getSimpleDateFormat());
			i=xtScriptService.addXtScript(xt_Script);
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
	@RequestMapping(value="/exportXtScript",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtScript(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 获取集合
	* @param xt_script 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtScriptList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtScriptList(XtScript xt_Script,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_script_type",1);
		condition.put("xt_script_status",0);
		List<XtScript> xt_ScriptList = xtScriptService.getXtScriptListByCondition(condition);
		return outComboDataStr(xt_ScriptList);
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtScriptAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtScriptAdd(XtScript xtScript,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-script/xt-script-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtScriptUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtScriptUpdate(String xt_script_id,HttpServletRequest request, Model model){
		XtScript xtScript = xtScriptService.getXtScriptById(xt_script_id);
		model.addAttribute("xtScript", xtScript);
		return new ModelAndView("pc/xt-view/xt-script/xt-script-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtScriptDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtScriptDetail(String xt_script_id,HttpServletRequest request, Model model){
		XtScript xtScript = xtScriptService.getXtScriptById(xt_script_id);
		model.addAttribute("xtScript", xtScript);
		return new ModelAndView("pc/xt-view/xt-script/xt-script-detail");
	}
}
