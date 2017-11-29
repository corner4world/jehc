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
import jehc.xtmodules.xtmodel.XtOperateLogs;
import jehc.xtmodules.xtservice.XtOperateLogsService;

/**
* 操作日志表; InnoDB free: 4096 kB 
* 2015-09-30 16:48:48  邓纯杰
*/
@Controller
@RequestMapping("/xtOperateLogsController")
public class XtOperateLogsController extends BaseAction{
	@Autowired
	private XtOperateLogsService xtOperateLogsService;
	/**
	* 载入初始化页面
	* @param xt_operate_logs 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtOperateLogs",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtOperateLogs(XtOperateLogs xt_Operate_Logs,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-operate-logs/xt-operate-logs-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_operate_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtOperateLogsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtOperateLogsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtOperateLogs> xt_Operate_LogsList = xtOperateLogsService.getXtOperateLogsListByCondition(condition);
		PageInfo<XtOperateLogs> page = new PageInfo<XtOperateLogs>(xt_Operate_LogsList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_operate_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtOperateLogsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtOperateLogsById(String xt_operate_log_id,HttpServletRequest request){
		XtOperateLogs xt_Operate_Logs = xtOperateLogsService.getXtOperateLogsById(xt_operate_log_id);
		return outDataStr(xt_Operate_Logs);
	}
	/**
	* 添加
	* @param xt_operate_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtOperateLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtOperateLogs(XtOperateLogs xt_Operate_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Operate_Logs && !"".equals(xt_Operate_Logs)){
			xt_Operate_Logs.setXt_operate_log_id(UUID.toUUID());
			i=xtOperateLogsService.addXtOperateLogs(xt_Operate_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_operate_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtOperateLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtOperateLogs(XtOperateLogs xt_Operate_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Operate_Logs && !"".equals(xt_Operate_Logs)){
			i=xtOperateLogsService.updateXtOperateLogs(xt_Operate_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_operate_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtOperateLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtOperateLogs(String xt_operate_log_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_operate_log_id && !"".equals(xt_operate_log_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_operate_log_id",xt_operate_log_id.split(","));
			i=xtOperateLogsService.delXtOperateLogs(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_operate_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtOperateLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtOperateLogs(String xt_operate_log_id,HttpServletRequest request){
		int i = 0;
		XtOperateLogs xt_Operate_Logs = xtOperateLogsService.getXtOperateLogsById(xt_operate_log_id);
		if(null != xt_Operate_Logs && !"".equals(xt_Operate_Logs)){
			xt_Operate_Logs.setXt_operate_log_id(UUID.toUUID());
			i=xtOperateLogsService.addXtOperateLogs(xt_Operate_Logs);
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
	@RequestMapping(value="/exportXtOperateLogs",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtOperateLogs(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtOperateLogsDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtOperateLogsDetail(String xt_operate_log_id,HttpServletRequest request, Model model){
		XtOperateLogs xtOperateLogs = xtOperateLogsService.getXtOperateLogsById(xt_operate_log_id);
		model.addAttribute("xtOperateLogs", xtOperateLogs);
		return new ModelAndView("pc/xt-view/xt-operate-logs/xt-operate-logs-detail");
	}
}
