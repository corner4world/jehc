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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Login_Logs;
import jehc.xtmodules.xtservice.Xt_Login_LogsService;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
@Controller
@RequestMapping("/xtLoginLogsController")
public class Xt_Login_LogsController extends BaseAction{
	@Autowired
	private Xt_Login_LogsService xt_Login_LogsService;
	/**
	* 载入初始化页面
	* @param xt_login_logs 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtLoginLogs(Xt_Login_Logs xt_Login_Logs,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-login-logs/xt-login-logs-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_login_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtLoginLogsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoginLogsListByCondition(Xt_Login_Logs xt_Login_Logs,HttpServletRequest request){
		String flag = request.getParameter("flag");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null != flag){
			condition.put("xt_userinfo_id", CommonUtils.getXtUid());
		}
		commonHPager(condition,request);
		List<Xt_Login_Logs>XtLoginLogsList = xt_Login_LogsService.getXtLoginLogsListByCondition(condition);
		PageInfo<Xt_Login_Logs> page = new PageInfo<Xt_Login_Logs>(XtLoginLogsList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_login_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtLoginLogsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoginLogsById(String xt_login_log_id,HttpServletRequest request){
		Xt_Login_Logs xt_Login_Logs = xt_Login_LogsService.getXtLoginLogsById(xt_login_log_id);
		return outDataStr(xt_Login_Logs);
	}
	/**
	* 添加
	* @param xt_login_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtLoginLogs(Xt_Login_Logs xt_Login_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Login_Logs && !"".equals(xt_Login_Logs)){
			xt_Login_Logs.setXt_login_log_id(UUID.toUUID());
			i=xt_Login_LogsService.addXtLoginLogs(xt_Login_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_login_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtLoginLogs(Xt_Login_Logs xt_Login_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Login_Logs && !"".equals(xt_Login_Logs)){
			i=xt_Login_LogsService.updateXtLoginLogs(xt_Login_Logs);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_login_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtLoginLogs(String xt_login_log_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_login_log_id && !"".equals(xt_login_log_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_login_log_id",xt_login_log_id.split(","));
			i=xt_Login_LogsService.delXtLoginLogs(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	 * 统计出每个人登录的次数
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGroupXtLoginLogsList",method={RequestMethod.POST,RequestMethod.GET})
	public String getGroupXtLoginLogsList(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Xt_Login_Logs> list = xt_Login_LogsService.getGroupXtLoginLogsList(condition);
		return outItemsStr(list);
	}
	/**
	* 复制一行并生成记录
	* @param xt_login_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtLoginLogs(String xt_login_log_id,HttpServletRequest request){
		int i = 0;
		Xt_Login_Logs xt_Login_Logs = xt_Login_LogsService.getXtLoginLogsById(xt_login_log_id);
		if(null != xt_Login_Logs && !"".equals(xt_Login_Logs)){
			xt_Login_Logs.setXt_login_log_id(UUID.toUUID());
			i=xt_Login_LogsService.addXtLoginLogs(xt_Login_Logs);
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
	@RequestMapping(value="/exportXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtLoginLogs(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
