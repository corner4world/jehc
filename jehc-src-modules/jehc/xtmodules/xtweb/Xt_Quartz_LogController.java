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
import jehc.xtmodules.xtmodel.Xt_Quartz_Log;
import jehc.xtmodules.xtservice.Xt_Quartz_LogService;

/**
* 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
@Controller
@RequestMapping("/xtQuartzLogController")
public class Xt_Quartz_LogController extends BaseAction{
	@Autowired
	private Xt_Quartz_LogService xt_Quartz_LogService;
	/**
	* 载入初始化页面
	* @param xt_quartz_log 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtQuartzLog",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-quartz-log/xt-quartz-log-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_quartz_log 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtQuartzLogListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtQuartzLogListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition =  baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Quartz_Log> xt_Quartz_LogList = xt_Quartz_LogService.getXtQuartzLogListByCondition(condition);
		PageInfo<Xt_Quartz_Log> page = new PageInfo<Xt_Quartz_Log>(xt_Quartz_LogList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_quartz_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtQuartzLogById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtQuartzLogById(String xt_quartz_log_id,HttpServletRequest request){
		Xt_Quartz_Log xt_Quartz_Log = xt_Quartz_LogService.getXtQuartzLogById(xt_quartz_log_id);
		return outDataStr(xt_Quartz_Log);
	}
	/**
	* 添加
	* @param xt_quartz_log 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtQuartzLog",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log,HttpServletRequest request){
		int i = 0;
		if(null != xt_Quartz_Log && !"".equals(xt_Quartz_Log)){
			xt_Quartz_Log.setXt_quartz_log_id(UUID.toUUID());
			i=xt_Quartz_LogService.addXtQuartzLog(xt_Quartz_Log);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_quartz_log 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtQuartzLog",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtQuartzLog(Xt_Quartz_Log xt_Quartz_Log,HttpServletRequest request){
		int i = 0;
		if(null != xt_Quartz_Log && !"".equals(xt_Quartz_Log)){
			i=xt_Quartz_LogService.updateXtQuartzLog(xt_Quartz_Log);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_quartz_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtQuartzLog",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtQuartzLog(String xt_quartz_log_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_quartz_log_id && !"".equals(xt_quartz_log_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_quartz_log_id",xt_quartz_log_id.split(","));
			i=xt_Quartz_LogService.delXtQuartzLog(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_quartz_log_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtQuartzLog",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtQuartzLog(String xt_quartz_log_id,HttpServletRequest request){
		int i = 0;
		Xt_Quartz_Log xt_Quartz_Log = xt_Quartz_LogService.getXtQuartzLogById(xt_quartz_log_id);
		if(null != xt_Quartz_Log && !"".equals(xt_Quartz_Log)){
			xt_Quartz_Log.setXt_quartz_log_id(UUID.toUUID());
			i=xt_Quartz_LogService.addXtQuartzLog(xt_Quartz_Log);
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
	@RequestMapping(value="/exportXtQuartzLog",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtQuartzLog(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
