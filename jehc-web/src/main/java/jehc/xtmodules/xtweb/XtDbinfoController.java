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
import jehc.xtmodules.xtmodel.XtDbinfo;
import jehc.xtmodules.xtservice.XtDbinfoService;

/**
* 数据库信息表; InnoDB free: 4096 kB 
* 2015-09-30 16:37:40  邓纯杰
*/
@Controller
@RequestMapping("/xtDbinfoController")
public class XtDbinfoController extends BaseAction{
	@Autowired
	private XtDbinfoService xtDbinfoService;
	/**
	* 载入初始化页面
	* @param xt_dbinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDbinfo(XtDbinfo xt_Dbinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-dbinfo/xt-dbinfo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDbinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonPager(condition,request);
		List<XtDbinfo> xt_DbinfoList = xtDbinfoService.getXtDbinfoListByCondition(condition);
		PageInfo<XtDbinfo> page = new PageInfo<XtDbinfo>(xt_DbinfoList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_dbinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDbinfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDbinfoById(String xt_dbinfo_id,HttpServletRequest request){
		XtDbinfo xt_Dbinfo = xtDbinfoService.getXtDbinfoById(xt_dbinfo_id);
		return outDataStr(xt_Dbinfo);
	}
	/**
	* 添加
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDbinfo(XtDbinfo xt_Dbinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Dbinfo && !"".equals(xt_Dbinfo)){
			xt_Dbinfo.setXt_dbinfo_id(UUID.toUUID());
			i=xtDbinfoService.addXtDbinfo(xt_Dbinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_dbinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtDbinfo(XtDbinfo xt_Dbinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Dbinfo && !"".equals(xt_Dbinfo)){
			i=xtDbinfoService.updateXtDbinfo(xt_Dbinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_dbinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDbinfo(String xt_dbinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_dbinfo_id && !"".equals(xt_dbinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_dbinfo_id",xt_dbinfo_id.split(","));
			i=xtDbinfoService.delXtDbinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_dbinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtDbinfo(String xt_dbinfo_id,HttpServletRequest request){
		int i = 0;
		XtDbinfo xt_Dbinfo = xtDbinfoService.getXtDbinfoById(xt_dbinfo_id);
		if(null != xt_Dbinfo && !"".equals(xt_Dbinfo)){
			xt_Dbinfo.setXt_dbinfo_id(UUID.toUUID());
			i=xtDbinfoService.addXtDbinfo(xt_Dbinfo);
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
	@RequestMapping(value="/exportXtDbinfo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtDbinfo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtDbinfoAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtDbinfoAdd(XtDbinfo xtDbinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-dbinfo/xt-dbinfo-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtDbinfoUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtDbinfoUpdate(String xt_dbinfo_id,HttpServletRequest request, Model model){
		XtDbinfo xtDbinfo = xtDbinfoService.getXtDbinfoById(xt_dbinfo_id);
		model.addAttribute("xtDbinfo", xtDbinfo);
		return new ModelAndView("pc/xt-view/xt-dbinfo/xt-dbinfo-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtDbinfoDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtDbinfoDetail(String xt_dbinfo_id,HttpServletRequest request, Model model){
		XtDbinfo xtDbinfo = xtDbinfoService.getXtDbinfoById(xt_dbinfo_id);
		model.addAttribute("xtDbinfo", xtDbinfo);
		return new ModelAndView("pc/xt-view/xt-dbinfo/xt-dbinfo-detail");
	}
}
