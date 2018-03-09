package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
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
import jehc.xtmodules.xtmodel.XtMonitorCpu;
import jehc.xtmodules.xtservice.XtMonitorCpuService;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
@Controller
@RequestMapping("/xtMonitorCpuController")
@Scope("prototype")
public class XtMonitorCpuController extends BaseAction{
	@Autowired
	private XtMonitorCpuService xtMonitorCpuService;
	/**
	* 载入初始化页面
	* @param xt_monitor_cpu 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMonitorCpu",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-monitor-cpu/xt-monitor-cpu-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_monitor_cpu 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorCpuListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorCpuListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtMonitorCpu>XtMonitorCpuList = xtMonitorCpuService.getXtMonitorCpuListByCondition(condition);
		PageInfo<XtMonitorCpu> page = new PageInfo<XtMonitorCpu>(XtMonitorCpuList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_monitor_cpu_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorCpuById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorCpuById(String xt_monitor_cpu_id,HttpServletRequest request){
		XtMonitorCpu xt_Monitor_Cpu = xtMonitorCpuService.getXtMonitorCpuById(xt_monitor_cpu_id);
		return outDataStr(xt_Monitor_Cpu);
	}
	/**
	* 添加
	* @param xt_monitor_cpu 
	* @param request 
	*/
	@RequestMapping(value="/addXtMonitorCpu",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu,HttpServletRequest request){
		int i = 0;
		if(null != xt_Monitor_Cpu && !"".equals(xt_Monitor_Cpu)){
			xt_Monitor_Cpu.setXt_monitor_cpu_id(UUID.toUUID());
			i=xtMonitorCpuService.addXtMonitorCpu(xt_Monitor_Cpu);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_monitor_cpu 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtMonitorCpu",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu,HttpServletRequest request){
		int i = 0;
		if(null != xt_Monitor_Cpu && !"".equals(xt_Monitor_Cpu)){
			i=xtMonitorCpuService.updateXtMonitorCpu(xt_Monitor_Cpu);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_monitor_cpu_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtMonitorCpu",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtMonitorCpu(String xt_monitor_cpu_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_monitor_cpu_id && !"".equals(xt_monitor_cpu_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_monitor_cpu_id",xt_monitor_cpu_id.split(","));
			i=xtMonitorCpuService.delXtMonitorCpu(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_monitor_cpu_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtMonitorCpu",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtMonitorCpu(String xt_monitor_cpu_id,HttpServletRequest request){
		int i = 0;
		XtMonitorCpu xt_Monitor_Cpu = xtMonitorCpuService.getXtMonitorCpuById(xt_monitor_cpu_id);
		if(null != xt_Monitor_Cpu && !"".equals(xt_Monitor_Cpu)){
			xt_Monitor_Cpu.setXt_monitor_cpu_id(UUID.toUUID());
			i=xtMonitorCpuService.addXtMonitorCpu(xt_Monitor_Cpu);
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
	@RequestMapping(value="/exportXtMonitorCpu",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtMonitorCpu(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 载入监控图表页面
	* @param xt_monitor_cpu 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtMonitorCpuChart",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMonitorCpuChart(XtMonitorCpu xt_Monitor_Cpu,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-monitor-cpu/xt-monitor-cpu-chart");
	}
	/**
	* 实时监控
	* @param request 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtMonitorCPU",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMonitorCPU(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		String libs = System.getProperty("java.library.path");
        System.setProperty("java.library.path", libs);
        Sigar sigar = new Sigar();
        CpuInfo infos[];
        try {
			infos = sigar.getCpuInfoList();
			CpuPerc cpuList[] = null;
	        cpuList = sigar.getCpuPercList();
	        for(int i = 0; i < infos.length; i++) {
	            CpuPerc cpu = cpuList[i];
	        	String xt_monitor_cpu_user_use_rate = CpuPerc.format(cpu.getUser());
	        	String xt_monitor_cpu_sys_use_rate = CpuPerc.format(cpu.getSys());
	        	String xt_monitor_cpu_currently_idle = CpuPerc.format(cpu.getIdle());
	        	String xt_monitor_cpu_use_rate = CpuPerc.format(cpu.getCombined());
	        	model.put("xt_monitor_cpu_user_use_rate",Double.parseDouble((xt_monitor_cpu_user_use_rate.split("%")[0])));
	        	model.put("xt_monitor_cpu_sys_use_rate",Double.parseDouble((xt_monitor_cpu_sys_use_rate.split("%")[0])));
	        	model.put("xt_monitor_cpu_currently_idle",Double.parseDouble((xt_monitor_cpu_currently_idle.split("%")[0])));
				model.put("xt_monitor_cpu_use_rate",Double.parseDouble((xt_monitor_cpu_use_rate.split("%")[0])));
				model.put("x_zon", "第"+(i+1)+"块");
				jsonArray.add(model);
	        }
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return outItemsStr(jsonArray);
	}
}
