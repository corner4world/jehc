package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtMonitorMem;
import jehc.xtmodules.xtservice.XtMonitorMemService;

/**
* 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
@Controller
@RequestMapping("/xtMonitorMemController")
@Scope("prototype")
public class XtMonitorMemController extends BaseAction{
	@Autowired
	private XtMonitorMemService xtMonitorMemService;
	/**
	* 载入初始化页面
	* @param xt_monitor_mem 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMonitorMem",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMonitorMem(XtMonitorMem xt_Monitor_Mem,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-monitor-mem/xt-monitor-mem-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_monitor_mem 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorMemListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorMemListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtMonitorMem> xt_Monitor_MemList = xtMonitorMemService.getXtMonitorMemListByCondition(condition);
		PageInfo<XtMonitorMem> page = new PageInfo<XtMonitorMem>(xt_Monitor_MemList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_monitor_mem_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorMemById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorMemById(String xt_monitor_mem_id,HttpServletRequest request){
		XtMonitorMem xt_Monitor_Mem = xtMonitorMemService.getXtMonitorMemById(xt_monitor_mem_id);
		return outDataStr(xt_Monitor_Mem);
	}
	/**
	* 添加
	* @param xt_monitor_mem 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtMonitorMem",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMonitorMem(XtMonitorMem xt_Monitor_Mem,HttpServletRequest request){
		int i = 0;
		if(null != xt_Monitor_Mem && !"".equals(xt_Monitor_Mem)){
			xt_Monitor_Mem.setXt_monitor_mem_id(UUID.toUUID());
			i=xtMonitorMemService.addXtMonitorMem(xt_Monitor_Mem);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_monitor_mem 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtMonitorMem",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMonitorMem(XtMonitorMem xt_Monitor_Mem,HttpServletRequest request){
		int i = 0;
		if(null != xt_Monitor_Mem && !"".equals(xt_Monitor_Mem)){
			i=xtMonitorMemService.updateXtMonitorMem(xt_Monitor_Mem);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_monitor_mem_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtMonitorMem",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtMonitorMem(String xt_monitor_mem_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_monitor_mem_id && !"".equals(xt_monitor_mem_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_monitor_mem_id",xt_monitor_mem_id.split(","));
			i=xtMonitorMemService.delXtMonitorMem(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_monitor_mem_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtMonitorMem",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtMonitorMem(String xt_monitor_mem_id,HttpServletRequest request){
		int i = 0;
		XtMonitorMem xt_Monitor_Mem = xtMonitorMemService.getXtMonitorMemById(xt_monitor_mem_id);
		if(null != xt_Monitor_Mem && !"".equals(xt_Monitor_Mem)){
			xt_Monitor_Mem.setXt_monitor_mem_id(UUID.toUUID());
			i=xtMonitorMemService.addXtMonitorMem(xt_Monitor_Mem);
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
	@RequestMapping(value="/exportXtMonitorMem",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtMonitorMem(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}

	/**
	 * 当前内存剩余量
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMonitorMemCurrSy",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorMemCurrSy(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		List<XtMonitorMem> xt_Monitor_MemList = xtMonitorMemService.getXtMonitorMemListByCondition(condition);
		for(int i = 0; i < xt_Monitor_MemList.size(); i++){
			XtMonitorMem xt_monitor_mem = xt_Monitor_MemList.get(i);
			model.put("xt_monitor_memTime", xt_monitor_mem.getXt_monitor_memTime());
			model.put("xt_monitor_memCurrSy", (new Integer(xt_monitor_mem.getXt_monitor_memCurrSy()))/(1024*1024));
			jsonArray.add(model);
		}
		return outItemsStr(jsonArray);
	}
	/**
	 * 载入内存图表页面
	 * @return
	 */
	@RequestMapping(value="/loadXtMonitorMemChart",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMonitorMemChart(){
		return new ModelAndView("pc/xt-view/xt-monitor-mem/xt-monitor-mem-chart");
	}
}
