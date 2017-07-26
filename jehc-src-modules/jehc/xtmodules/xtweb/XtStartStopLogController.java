package jehc.xtmodules.xtweb;
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

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtStartStopLog;
import jehc.xtmodules.xtservice.XtStartStopLogService;

/**
* 服务器启动与关闭日志; InnoDB free: 9216 kB; InnoDB free: 9216 kB 
* 2015-10-31 21:28:14  邓纯杰
*/
@Controller
@RequestMapping("/xtStartStopLogController")
public class XtStartStopLogController extends BaseAction{
	@Autowired
	private XtStartStopLogService xtStartStopLogService;
	/**
	* 载入初始化页面
	* @param xt_start_stop_log 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtStartStopLog",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtStartStopLog(XtStartStopLog xt_Start_Stop_Log,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-start-stop-log/xt-start-stop-log-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_start_stop_log 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtStartStopLogListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtStartStopLogListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtStartStopLog> xt_Start_Stop_LogList = xtStartStopLogService.getXtStartStopLogListByCondition(condition);
		PageInfo<XtStartStopLog> page = new PageInfo<XtStartStopLog>(xt_Start_Stop_LogList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_start_stop_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtStartStopLogById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtStartStopLogById(String xt_start_stop_log_id,HttpServletRequest request){
		XtStartStopLog xt_Start_Stop_Log = xtStartStopLogService.getXtStartStopLogById(xt_start_stop_log_id);
		return outDataStr(xt_Start_Stop_Log);
	}
	/**
	* 添加
	* @param xt_start_stop_log 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtStartStopLog",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtStartStopLog(XtStartStopLog xt_Start_Stop_Log,HttpServletRequest request){
		int i = 0;
		if(null != xt_Start_Stop_Log && !"".equals(xt_Start_Stop_Log)){
			xt_Start_Stop_Log.setXt_start_stop_log_id(UUID.toUUID());
			i=xtStartStopLogService.addXtStartStopLog(xt_Start_Stop_Log);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_start_stop_log 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtStartStopLog",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtStartStopLog(XtStartStopLog xt_Start_Stop_Log,HttpServletRequest request){
		int i = 0;
		if(null != xt_Start_Stop_Log && !"".equals(xt_Start_Stop_Log)){
			i=xtStartStopLogService.updateXtStartStopLog(xt_Start_Stop_Log);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_start_stop_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtStartStopLog",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtStartStopLog(String xt_start_stop_log_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_start_stop_log_id && !"".equals(xt_start_stop_log_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_start_stop_log_id",xt_start_stop_log_id.split(","));
			i=xtStartStopLogService.delXtStartStopLog(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_start_stop_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtStartStopLog",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtStartStopLog(String xt_start_stop_log_id,HttpServletRequest request){
		int i = 0;
		XtStartStopLog xt_Start_Stop_Log = xtStartStopLogService.getXtStartStopLogById(xt_start_stop_log_id);
		if(null != xt_Start_Stop_Log && !"".equals(xt_Start_Stop_Log)){
			xt_Start_Stop_Log.setXt_start_stop_log_id(UUID.toUUID());
			i=xtStartStopLogService.addXtStartStopLog(xt_Start_Stop_Log);
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
	@RequestMapping(value="/exportXtStartStopLog",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtStartStopLog(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
