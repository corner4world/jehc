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
import jehc.xtmodules.xtmodel.XtErrorLogs;
import jehc.xtmodules.xtservice.XtErrorLogsService;

/**
* 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
@Controller
@RequestMapping("/xtErrorLogsController")
public class XtErrorLogsController extends BaseAction{
	@Autowired
	private XtErrorLogsService xtErrorLogsService;
	/**
	* 载入初始化页面
	* @param xt_error_logs 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtErrorLogs",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtErrorLogs(XtErrorLogs xt_Error_Logs,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-error-logs/xt-error-logs-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_error_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtErrorLogsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtErrorLogsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtErrorLogs>XtErrorLogsList = xtErrorLogsService.getXtErrorLogsListByCondition(condition);
		PageInfo<XtErrorLogs> page = new PageInfo<XtErrorLogs>(XtErrorLogsList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_error_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtErrorLogsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtErrorLogsById(String xt_error_log_id,HttpServletRequest request){
		XtErrorLogs xt_Error_Logs = xtErrorLogsService.getXtErrorLogsById(xt_error_log_id);
		return outDataStr(xt_Error_Logs);
	}
	/**
	* 添加
	* @param xt_error_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtErrorLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtErrorLogs(XtErrorLogs xt_Error_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Error_Logs && !"".equals(xt_Error_Logs)){
			xt_Error_Logs.setXt_error_log_id(UUID.toUUID());
			i=xtErrorLogsService.addXtErrorLogs(xt_Error_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_error_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtErrorLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtErrorLogs(XtErrorLogs xt_Error_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Error_Logs && !"".equals(xt_Error_Logs)){
			i=xtErrorLogsService.updateXtErrorLogs(xt_Error_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_error_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtErrorLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtErrorLogs(String xt_error_log_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_error_log_id && !"".equals(xt_error_log_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_error_log_id",xt_error_log_id.split(","));
			i=xtErrorLogsService.delXtErrorLogs(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_error_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtErrorLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtErrorLogs(String xt_error_log_id,HttpServletRequest request){
		int i = 0;
		XtErrorLogs xt_Error_Logs = xtErrorLogsService.getXtErrorLogsById(xt_error_log_id);
		if(null != xt_Error_Logs && !"".equals(xt_Error_Logs)){
			xt_Error_Logs.setXt_error_log_id(UUID.toUUID());
			i=xtErrorLogsService.addXtErrorLogs(xt_Error_Logs);
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
	@RequestMapping(value="/exportXtErrorLogs",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtErrorLogs(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
