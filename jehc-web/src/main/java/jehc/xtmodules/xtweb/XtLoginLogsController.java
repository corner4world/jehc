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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtLoginLogs;
import jehc.xtmodules.xtservice.XtLoginLogsService;

/**
* 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
@Controller
@RequestMapping("/xtLoginLogsController")
public class XtLoginLogsController extends BaseAction{
	@Autowired
	private XtLoginLogsService xtLoginLogsService;
	/**
	* 载入初始化页面
	* @param xt_login_logs 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtLoginLogs(XtLoginLogs xt_Login_Logs,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-login-logs/xt-login-logs-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_login_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtLoginLogsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoginLogsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		String flag = request.getParameter("flag");
		if(null != flag){
			condition.put("xt_userinfo_id", CommonUtils.getXtUid());
		}
		commonHPager(condition,request);
		List<XtLoginLogs>XtLoginLogsList = xtLoginLogsService.getXtLoginLogsListByCondition(condition);
		PageInfo<XtLoginLogs> page = new PageInfo<XtLoginLogs>(XtLoginLogsList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_login_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtLoginLogsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtLoginLogsById(String xt_login_log_id,HttpServletRequest request){
		XtLoginLogs xt_Login_Logs = xtLoginLogsService.getXtLoginLogsById(xt_login_log_id);
		return outDataStr(xt_Login_Logs);
	}
	/**
	* 添加
	* @param xt_login_logs 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtLoginLogs",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtLoginLogs(XtLoginLogs xt_Login_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Login_Logs && !"".equals(xt_Login_Logs)){
			xt_Login_Logs.setXt_login_log_id(UUID.toUUID());
			i=xtLoginLogsService.addXtLoginLogs(xt_Login_Logs);
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
	public String updateXtLoginLogs(XtLoginLogs xt_Login_Logs,HttpServletRequest request){
		int i = 0;
		if(null != xt_Login_Logs && !"".equals(xt_Login_Logs)){
			i=xtLoginLogsService.updateXtLoginLogs(xt_Login_Logs);
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
			i=xtLoginLogsService.delXtLoginLogs(condition);
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
		List<XtLoginLogs> list = xtLoginLogsService.getGroupXtLoginLogsList(condition);
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
		XtLoginLogs xt_Login_Logs = xtLoginLogsService.getXtLoginLogsById(xt_login_log_id);
		if(null != xt_Login_Logs && !"".equals(xt_Login_Logs)){
			xt_Login_Logs.setXt_login_log_id(UUID.toUUID());
			i=xtLoginLogsService.addXtLoginLogs(xt_Login_Logs);
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
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtLoginLogsDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtLoginLogsDetail(String xt_login_log_id,HttpServletRequest request, Model model){
		XtLoginLogs xtLoginLogs = xtLoginLogsService.getXtLoginLogsById(xt_login_log_id);
		model.addAttribute("xtLoginLogs", xtLoginLogs);
		return new ModelAndView("pc/xt-view/xt-login-logs/xt-login-logs-detail");
	}
}
