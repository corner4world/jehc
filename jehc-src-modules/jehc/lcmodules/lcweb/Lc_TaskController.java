package jehc.lcmodules.lcweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtservice.Xt_UserinfoService;

/**
 * 流程任务管理
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/lcTaskController")
public class Lc_TaskController extends BaseAction{
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private Xt_UserinfoService xt_UserinfoService;
	/**
	* 载入初始化页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcTask",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcProcess(HttpServletRequest request){
		return new ModelAndView("pc/lc-view/lc-task/lc-task-list");
	}
	
	/**
	* 读取所有任务
	* @param request 
	*/
	@SuppressWarnings({ "unchecked", "deprecation" })
	@ResponseBody
	@RequestMapping(value="/getTaskListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcApprovalListByCondition(String instanceId,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonPager(condition, request);
		Map<String, Object> map;
		if(StringUtil.isEmpty(instanceId)){
			map = activitiUtil.getTaskList(condition);
		}else{
			condition.put("instanceId", instanceId);
			map = activitiUtil.getTaskListByInstanceId(condition);
		}
		List<Task> list = (List<Task>)map.get("TaskList");
		int total = new Integer(map.get("TaskCount").toString());
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		for(int i = 0; i < list.size(); i++){
			Task task = list.get(i);
			model.put("category", task.getCategory());
			if(!StringUtil.isEmpty(task.getAssignee())){
				model.put("assignee", xt_UserinfoService.getXtUserinfoById(task.getAssignee()).getXt_userinfo_realName());
			}else{
				model.put("assignee", task.getAssignee());
			}
			model.put("description", task.getDescription());
			model.put("executionId", task.getExecutionId());
			model.put("formKey", task.getFormKey());
			model.put("taskId", task.getId());
			model.put("name", task.getName());
			if(!StringUtil.isEmpty(task.getOwner())){
				model.put("owner", xt_UserinfoService.getXtUserinfoById(task.getOwner()).getXt_userinfo_realName());
			}else{
				model.put("owner", task.getOwner());
			}
			model.put("parentTaskId", task.getParentTaskId());
			model.put("priority", task.getPriority());
			model.put("processDefinitionId", task.getProcessDefinitionId());
			model.put("processInstanceId", task.getProcessInstanceId());
			model.put("taskDefinitionKey", task.getTaskDefinitionKey());
			model.put("tenantId", task.getTenantId());
			model.put("createTime", task.getCreateTime().toLocaleString());
			model.put("delegationState", task.getDelegationState());
			model.put("dueDate", task.getDueDate());
			model.put("processVariables", task.getProcessVariables());
			model.put("processDefinitionId", task.getProcessDefinitionId());
			model.put("processInstanceId", task.getProcessInstanceId());
            jsonArray.add(model);
		}
		return outPageStr(jsonArray,total, request);
	}
	
	
	/**
	* 设置经办人
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/setAssignee",method={RequestMethod.POST,RequestMethod.GET})
	public String setAssignee(String taskId,String userId,HttpServletRequest request){
		return outAudStr(activitiUtil.setAssignee(taskId, userId));
	} 
	
	/**
	* 设置归属人
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/setOwner",method={RequestMethod.POST,RequestMethod.GET})
	public String setOwner(String taskId,String userId,HttpServletRequest request){
		return outAudStr(activitiUtil.setOwner(taskId, userId));
	} 
	
	/**
	* 向组任务中添加成员
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addGroupUser",method={RequestMethod.POST,RequestMethod.GET})
	public String addGroupUser(String taskId,String userId,HttpServletRequest request){
		return outAudStr(activitiUtil.addGroupUser(taskId, userId));
	} 
	
	/**
	* 向组任务中删除成员
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/deleteGroupUser",method={RequestMethod.POST,RequestMethod.GET})
	public String deleteGroupUser(String taskId,String userId,HttpServletRequest request){
		return outAudStr(activitiUtil.deleteGroupUser(taskId, userId));
	} 
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param userId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/completeTask",method={RequestMethod.POST,RequestMethod.GET})
	public String completeTask(String taskId,String userId,HttpServletRequest request){
		return outAudStr(activitiUtil.completeTask(taskId));
	}
}
