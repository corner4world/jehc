package jehc.oamodules.oaweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
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
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.oamodules.oamodel.OaTask;
import jehc.oamodules.oaservice.OaTaskService;

/**
* 任务表 
* 2018-07-02 21:09:04  邓纯杰
*/
@Controller
@RequestMapping("/oaTaskController")
public class OaTaskController extends BaseAction{
	@Autowired
	private OaTaskService oaTaskService;
	/**
	* 载入初始化页面
	* @param oa_task 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaTask",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaTask(OaTask oaTask,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-task/oa-task-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_task 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaTaskListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaTaskListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaTask> oaTaskList = oaTaskService.getOaTaskListByCondition(condition);
		PageInfo<OaTask> page = new PageInfo<OaTask>(oaTaskList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_task_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaTaskById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaTaskById(String oa_task_id,HttpServletRequest request){
		OaTask oaTask = oaTaskService.getOaTaskById(oa_task_id);
		return outDataStr(oaTask);
	}
	/**
	* 添加
	* @param oa_task 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaTask",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaTask(OaTask oaTask,HttpServletRequest request){
		int i = 0;
		if(null != oaTask && !"".equals(oaTask)){
			oaTask.setOa_task_id(UUID.toUUID());
			i=oaTaskService.addOaTask(oaTask);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_task 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaTask",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaTask(OaTask oaTask,HttpServletRequest request){
		int i = 0;
		if(null != oaTask && !"".equals(oaTask)){
			i=oaTaskService.updateOaTask(oaTask);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_task_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaTask",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaTask(String oa_task_id,HttpServletRequest request){
		int i = 0;
		if(null != oa_task_id && !"".equals(oa_task_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_task_id",oa_task_id.split(","));
			i=oaTaskService.delOaTask(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_task_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaTask",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaTask(String oa_task_id,HttpServletRequest request){
		int i = 0;
		OaTask oaTask = oaTaskService.getOaTaskById(oa_task_id);
		if(null != oaTask && !"".equals(oaTask)){
			oaTask.setOa_task_id(UUID.toUUID());
			i=oaTaskService.addOaTask(oaTask);
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
	@RequestMapping(value="/exportOaTask",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaTask(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaTaskAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaTaskAdd(OaTask oaTask,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-task/oa-task-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaTaskUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaTaskUpdate(String oa_task_id,HttpServletRequest request, Model model){
		OaTask oaTask = oaTaskService.getOaTaskById(oa_task_id);
		model.addAttribute("oaTask", oaTask);
		return new ModelAndView("pc/oa-view/oa-task/oa-task-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaTaskDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaTaskDetail(String oa_task_id,HttpServletRequest request, Model model){
		OaTask oaTask = oaTaskService.getOaTaskById(oa_task_id);
		model.addAttribute("oaTask", oaTask);
		return new ModelAndView("pc/oa-view/oa-task/oa-task-detail");
	}
}
