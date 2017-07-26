package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
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
import jehc.xtmodules.xtmodel.XtOperateBusinessLogs;
import jehc.xtmodules.xtservice.XtOperateBusinessLogsService;

/**
* 平台业务操作日志表 
* 2016-09-16 16:39:20  邓纯杰
*/
@Controller
@RequestMapping("/xtOperateBusinessLogsController")
public class XtOperateBusinessLogsController extends BaseAction{
	@Autowired
	private XtOperateBusinessLogsService xtOperateBusinessLogsService;
	/**
	* 载入初始化页面
	* @param xt_operate_business_logs 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtOperateBusinessLogs",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-operate-business-logs/xt-operate-business-logs-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_operate_business_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtOperateBusinessLogsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtOperateBusinessLogsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtOperateBusinessLogs> xt_Operate_Business_LogsList = xtOperateBusinessLogsService.getXtOperateBusinessLogsListByCondition(condition);
		PageInfo<XtOperateBusinessLogs> page = new PageInfo<XtOperateBusinessLogs>(xt_Operate_Business_LogsList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_operate_business_logs_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtOperateBusinessLogsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtOperateBusinessLogsById(String xt_operate_business_logs_id,HttpServletRequest request){
		XtOperateBusinessLogs xt_Operate_Business_Logs = xtOperateBusinessLogsService.getXtOperateBusinessLogsById(xt_operate_business_logs_id);
		return outDataStr(xt_Operate_Business_Logs);
	}
	/**
	* 添加
	* @param xt_operate_business_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtOperateBusinessLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Operate_Business_Logs && !"".equals(xt_Operate_Business_Logs)){
			xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
			i=xtOperateBusinessLogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_operate_business_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtOperateBusinessLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtOperateBusinessLogs(XtOperateBusinessLogs xt_Operate_Business_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Operate_Business_Logs && !"".equals(xt_Operate_Business_Logs)){
			i=xtOperateBusinessLogsService.updateXtOperateBusinessLogs(xt_Operate_Business_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_operate_business_logs_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtOperateBusinessLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtOperateBusinessLogs(String xt_operate_business_logs_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_operate_business_logs_id && !"".equals(xt_operate_business_logs_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_operate_business_logs_id",xt_operate_business_logs_id.split(","));
			i=xtOperateBusinessLogsService.delXtOperateBusinessLogs(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_operate_business_logs_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtOperateBusinessLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtOperateBusinessLogs(String xt_operate_business_logs_id,HttpServletRequest request){
		int i = 0;
		XtOperateBusinessLogs xt_Operate_Business_Logs = xtOperateBusinessLogsService.getXtOperateBusinessLogsById(xt_operate_business_logs_id);
		if(null != xt_Operate_Business_Logs && !"".equals(xt_Operate_Business_Logs)){
			xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
			i=xtOperateBusinessLogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
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
	@RequestMapping(value="/exportXtOperateBusinessLogs",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtOperateBusinessLogs(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
