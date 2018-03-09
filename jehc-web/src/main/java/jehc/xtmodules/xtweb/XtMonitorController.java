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
import jehc.xtmodules.xtmodel.XtMonitor;
import jehc.xtmodules.xtservice.XtMonitorService;

/**
* 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
@Controller
@RequestMapping("/xtMonitorController")
@Scope("prototype")
public class XtMonitorController extends BaseAction{
	@Autowired
	private XtMonitorService xtMonitorService;
	/**
	* 载入初始化页面
	* @param xt_monitor 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMonitor",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMonitor(XtMonitor xt_Monitor,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-monitor/xt-monitor-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_monitor 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtMonitor> xt_MonitorList = xtMonitorService.getXtMonitorListByCondition(condition);
		PageInfo<XtMonitor> page = new PageInfo<XtMonitor>(xt_MonitorList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_monitor_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorById(String xt_monitor_id,HttpServletRequest request){
		XtMonitor xt_Monitor = xtMonitorService.getXtMonitorById(xt_monitor_id);
		return outDataStr(xt_Monitor);
	}
	/**
	* 添加
	* @param xt_monitor 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtMonitor",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMonitor(XtMonitor xt_Monitor,HttpServletRequest request){
		int i = 0;
		if(null != xt_Monitor && !"".equals(xt_Monitor)){
			xt_Monitor.setXt_monitor_id(UUID.toUUID());
			i=xtMonitorService.addXtMonitor(xt_Monitor);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_monitor 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtMonitor",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMonitor(XtMonitor xt_Monitor,HttpServletRequest request){
		int i = 0;
		if(null != xt_Monitor && !"".equals(xt_Monitor)){
			i=xtMonitorService.updateXtMonitor(xt_Monitor);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_monitor_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtMonitor",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtMonitor(String xt_monitor_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_monitor_id && !"".equals(xt_monitor_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_monitor_id",xt_monitor_id.split(","));
			i=xtMonitorService.delXtMonitor(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_monitor_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtMonitor",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtMonitor(String xt_monitor_id,HttpServletRequest request){
		int i = 0;
		XtMonitor xt_Monitor = xtMonitorService.getXtMonitorById(xt_monitor_id);
		if(null != xt_Monitor && !"".equals(xt_Monitor)){
			xt_Monitor.setXt_monitor_id(UUID.toUUID());
			i=xtMonitorService.addXtMonitor(xt_Monitor);
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
	@RequestMapping(value="/exportXtMonitor",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtMonitor(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	 * 当前JVM
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMonitorJvm",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorJvm(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		List<XtMonitor> xtMonitorList = xtMonitorService.getXtMonitorListByCondition(condition);
		for(int i = 0; i < xtMonitorList.size(); i++){
			XtMonitor xt_monitor = xtMonitorList.get(i);
			model.put("xt_monitor_jvm_totalMem",(new Integer(xt_monitor.getXt_monitor_jvm_Mem())/(1024*1024)));
			model.put("xt_monitorTime", xt_monitor.getXt_monitorTime());
			jsonArray.add(model);
		}
		return outItemsStr(jsonArray);
	}
	/**
	 * 载入JVM图表页面
	 * @return
	 */
	@RequestMapping(value="/loadXtMonitorJvmChart",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMonitorJvmChart(){
		return new ModelAndView("pc/xt-view/xt-monitor/xt-monitor-chart");
	}
}
