package jehc.lcmodules.activitiutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import jehc.lcmodules.activitiutil.zl.Variable;
import jehc.lcmodules.lcmodel.Lc_Apply;
import jehc.lcmodules.lcmodel.Lc_Deployment_His;
import jehc.lcmodules.lcservice.Lc_ApplyService;
import jehc.lcmodules.lcservice.Lc_Deployment_HisService;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.allutils.file.ImageAnd64Binary;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.springutil.GetApplicationContext;
import jehc.xtmodules.xtmodel.Xt_Constant;
import jehc.xtmodules.xtservice.Xt_ConstantService;
/**
 * activiti核心引擎
 * @author邓纯杰
 *
 */
@SuppressWarnings("unused")
public class ActivitiUtil {
	private IdentityService identityService;
	
	private RuntimeService runtimeService;
	
	private HistoryService historyService;
	
	private TaskService taskService;
	
	private ManagementService managementService;
	
	private FormService formService;
	
	private RepositoryService repositoryService;
	
	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	/**
	 * 转换流程节点类型为中文说明
	 * @param type	英文名称
	 * @return	翻译后的中文名称
	 */
	public static String parseToZhType(String type) {
		Map<String, String> types = new HashMap<String, String>();
		types.put("userTask", "用户任务");
		types.put("serviceTask", "系统任务");
		types.put("startEvent", "开始节点");
		types.put("endEvent", "结束节点");
		types.put("exclusiveGateway", "条件判断节点(系统自动根据条件处理)");
		types.put("inclusiveGateway", "并行处理任务");
		types.put("callActivity", "子流程");
		return types.get(type) == null ? type: types.get(type);
	}
	
	/**
	 * 部署流程
	 * @param file部署的文件流
	 * @return 返回流程部署对象
	 */
	public Deployment createDeployment(File file){
		Deployment deployment=null;
		if(FilenameUtils.getExtension(file.getName()).equals("zip")||FilenameUtils.getExtension(file.getName()).equals("bar")){
			ZipInputStream zipInputStream;
			try {
				zipInputStream = new ZipInputStream(new FileInputStream(file));
				deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
				System.out.println("---------流程部署成功，信息如下:"+"ID---"+deployment.getId()+"----name---"+deployment.getName()+"----time----"+deployment.getDeploymentTime());
			} catch (FileNotFoundException e) {
				System.out.println("---------------流程部署失败,原因所部署的文件解压失败");
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		}else{
			System.out.println("---------------流程部署失败,原因所部署的文件类型不正确");
		}
		return deployment;
	}
	
	/**
	 * 删除流程部署
	 * @param deploymentId 流程部署ID号
	 */
	public int deleteDeploymentById(String deploymentId){
		int flag=0;
		try {
			repositoryService.deleteDeployment(deploymentId,true);
			flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
	/**
	 * 获取流程定义对象
	 * @param deploymentId 流程部署ID号
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String deploymentId){
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
			return processDefinition;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据流程定义id查询流程定义
	 * @return
	 */
	public ProcessDefinition getProcessDefinitionByDid(String processDefinitionId){
		ProcessDefinition processDefinition=null;
		try {
			processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return processDefinition;
	}
	
	/**
	 * 获取所有流程定义集合
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitionList(){
		return repositoryService.createProcessDefinitionQuery().list();
	}
	
	/**
	 * 根据流程定义的key启动工作流实例
	 * 发起一个流程实例
	 * @param key流程部署
	 * @param businessKey业务键
	 * @param variables参数
	 * @return
	 */
	public ProcessInstance startProcessInstanceByKey(String key,String businessKey,Map<String, Object> variables) {
		try {
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businessKey, variables);
			return processInstance;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		
	}
	
	/**
	 * 设置跨节点提交
	 * 根据流程定义的key启动工作流实例
	 * 发起一个流程实例
	 * @param key流程部署
	 * @param businessKey业务键
	 * @param initialActivityId [设置跨节点提交]
	 * @param variables参数
	 * @return
	 */
	public ProcessInstance startProcessInstanceByKey(String key,String businessKey,String initialActivityId,Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businessKey, variables);
		return processInstance;
	} 
	/**
	 * 根据流程实例获取流程对象
	 * @param processInstanceId 流程实例
	 * @return
	 */
	public ProcessInstance getProcessInstanceById(String processInstanceId){
		ProcessInstance processInstance=null;
		try {
			processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		} catch (Exception e) {
			System.out.println("---------------获取流程实例对象失败");
			e.printStackTrace();
		}
		return processInstance;
	}
	
	/**
	 * 根据流程实例查找流程图
	 * @param processInstanceId
	 */
	public void getProcessInstanceImageById(String processInstanceId,HttpServletResponse response){
		InputStream resourceAsStream = null;
		try {
			//根据流程实例id查询流程实例
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			//根据流程定义id查询流程定义
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
			String resourceName=processDefinition.getDiagramResourceName();
			//打开流程资源流
			resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
			runtimeService.getActiveActivityIds(processInstance.getId());
			//输出到浏览器
			byte[] byteArray = IOUtils.toByteArray(resourceAsStream);
			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(byteArray, 0, byteArray.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 获取流程图信息
	 * @param executionId
	 * @param processInstanceId
	 * @return
	 */
	public Map<String,Object> getActivityImageInfo(String executionId,String processInstanceId){
		//根据executionId查询当前执行的节点
		ExecutionEntity execution=(ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId(processInstanceId).executionId(executionId).singleResult();
		//获取当前节点的activityId
		String activityId=execution.getActivityId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		List<ActivityImpl> activities = processDefinitionEntity.getActivities();
		Map<String,Object> activityImageInfo=new HashMap<String,Object>();
		for (ActivityImpl activityImpl : activities) {
			String id=activityImpl.getId();
			//判断是否是当前节点
			if(id.equals(activityId)){
				activityImageInfo.put("x", activityImpl.getX());
				activityImageInfo.put("y", activityImpl.getY());
				activityImageInfo.put("width", activityImpl.getWidth());
				activityImageInfo.put("height", activityImpl.getHeight());
				break;//跳出循环
			}
		}
		return activityImageInfo;
	}
	
	/**
	 * 根据任务ID获取activityId,businessKey[业务Key],variables[节点变量]
	 * @param taskId
	 * @return
	 */
	public Map<String, Object> getTask(String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ExecutionEntity executionEntity=(ExecutionEntity) runtimeService.createExecutionQuery().executionId(task.getExecutionId()).processInstanceId(task.getProcessInstanceId()).singleResult();
		//获取当前正在执行的节点
		String activityId = executionEntity.getActivityId();
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//业务Key即可以作为业务ID处理
		String businessKey = processInstance.getBusinessKey();
		//获取节点变量
		Map<String, Object> variables = taskService.getVariables(taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskVariables", variables);
		map.put("businessKey", businessKey);
		map.put("activityId", activityId);
		return map;
	}
	
	/**
	 * 完成任务
	 * @param taskId
	 */
	public boolean completeTask(String taskId,Variable variable){
		try {
			Map<String, Object> variables = variable.getVariableMap();
			taskService.complete(taskId,variables);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据用户ID+taskId签收任务
	 * @param taskId
	 * @param UID
	 * @return 0失败---1成功
	 */
	public int claimTask(String taskId,String UID){
		int i = 0;
		try {
			taskService.claim(taskId, UID);
			i = 1;
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		return i;
	}
	
	/**
	 * 判断流程实例是否结束
	 * @param processInstanceId
	 * @return
	 */
	public boolean validatePEnd(String processInstanceID){
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceID).singleResult();
		if(pi==null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 挂起流程实例（可作为撤销流程）
	 * @param processInstanceID
	 * @return
	 */
	public boolean suspendProcessInstanceById(String processInstanceID){
		try {
			runtimeService.suspendProcessInstanceById(processInstanceID);
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return true;
	}
	
	/**
	 * 激活流程实例(开启流程实例)
	 * @param processInstanceID
	 * @return
	 */
	public boolean activateProcessInstanceById(String processInstanceID){
		try {
			runtimeService.activateProcessInstanceById(processInstanceID);
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return true;
	}
	
	/**
	 * 删除流程部署
	 * @param deploymentId
	 * @return
	 * @throws Exception
	 * 说明：因为删除的是流程定义，而流程定义的部署是属于仓库服务的，所以应该先得到RepositoryService
		    如果该流程定义下没有正在运行的流程，则可以用普通删除。
		    如果是有关联的信息，用级联删除。一般情况下用普通删除就可以。
		    由于级联删除涉及的数据比较多，一般只开放给超级管理员使用。
	 */
	public boolean delDeployment(String deploymentId){
		try {
		  //普通删除，如果当前规则下有正在执行的流程，则抛异常
		  repositoryService.deleteDeployment(deploymentId);
		  //级联删除,会删除和当前规则相关的所有信息，包括历史
		  repositoryService.deleteDeployment(deploymentId, true);
		  return true;
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据任务ID获取ActivityImpl
	 * @param taskID任务ID
	 * @param elString线上表达式
	 * @return
	 */
	public ActivityImpl getActivityImpl(String taskID){
		ActivityImpl activityImpl = null;
		//获取当前活动节点ID  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskID).singleResult();  
        if (task != null) {  
        	//取得流程定义  
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());  
            if (processDefinition != null) {  
            	String activityId = task.getTaskDefinitionKey();   
                //根据流程定义，获取该流程实例的结束节点  
                if (activityId.toUpperCase().equals("END")) {  
                    for (ActivityImpl actImpl : processDefinition.getActivities()) {  
                        List<PvmTransition> pvmTransitionList = actImpl.getOutgoingTransitions();  
                        if (pvmTransitionList.isEmpty()) {  
                            return actImpl;  
                        }  
                    }  
                }  
                // 根据节点ID，获取对应的活动节点  
                activityImpl = ((ProcessDefinitionImpl) processDefinition).findActivity(activityId);  
            }  
        }  
        return activityImpl;  
	}
	
	/**
	 * 根据实例编号查找下一个任务节点
	 * @param String processInstanceID:实例编号
	 * @param elString线上表达式
	 * @return
	 */
	public TaskDefinition nextTaskDefinition(String processInstanceID,String elString){
		//流程标示
		String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceID).singleResult().getProcessDefinitionId();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
		//执行实例
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceID).singleResult();
		//当前实例的执行到哪个节点
		String activitiId = execution.getActivityId();
		//获得当前任务的所有节点
		List<ActivityImpl> activitiList = def.getActivities();
		String id = null;
		for(ActivityImpl activityImpl:activitiList){  
			id = activityImpl.getId(); 
			if(activitiId.equals(id)){
				System.out.println("当前任务："+activityImpl.getProperty("name"));
				/**该方法可参考**/
//				return nextTaskDefinition(activityImpl, activityImpl.getId(),"${iscorrect==1}");
				return nextTaskDefinition(activityImpl, activityImpl.getId(),elString);
			}
		}
		return null;
	}
	
	/**
	 * 根据任务ID查找下一步节点
	 * @param taskID任务ID
	 * @param elString线上表达式
	 * @return
	 */
	public TaskDefinition getNextTaskDefinition(String taskID,String elString){
		ActivityImpl activityImpl = null;
		//获取当前活动节点ID  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskID).singleResult();  
        String activityId = "";
        if (task != null) {  
        	//取得流程定义  
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());  
            if (processDefinition != null) {  
            	activityId = task.getTaskDefinitionKey();   
                //根据流程定义，获取该流程实例的结束节点  
                if (activityId.toUpperCase().equals("END")) {  
                    for (ActivityImpl actImpl : processDefinition.getActivities()) {  
                        List<PvmTransition> pvmTransitionList = actImpl.getOutgoingTransitions();  
                        if (pvmTransitionList.isEmpty()) {  
                        	activityImpl = actImpl;  
                            break;
                        }  
                    }  
                }else{
                	 //根据节点ID，获取对应的活动节点  
                    activityImpl = ((ProcessDefinitionImpl) processDefinition).findActivity(activityId);  
                }
            }  
        }  
        if(null != activityImpl){
        	return nextTaskDefinition(activityImpl, activityId, elString);
        }else{
        	return null;
        }
	}
	
	/**
	 * 下一个任务节点
	 * @param activityImpl
	 * @param activityId
	 * @param elString连线上表达式 或者直接下个节点
	 * @return
	 */
	private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl,String activityId, String elString){
		if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){
			TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
			return taskDefinition;
		}else{
			List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
			List<PvmTransition> outTransitionsTemp = null;
			for(PvmTransition pt:outTransitions){  
				PvmActivity ac = pt.getDestination();//获取线路的终点节点  
				if("exclusiveGateway".equals(ac.getProperty("type"))){
					outTransitionsTemp = ac.getOutgoingTransitions();
					if(outTransitionsTemp.size() == 1){
						return nextTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(), activityId, elString);
					}else if(outTransitionsTemp.size() > 1){
						for(PvmTransition pt1 : outTransitionsTemp){
							Object s = pt1.getProperty("conditionText");
							if(elString.equals(s.toString().trim())){
								return nextTaskDefinition((ActivityImpl)pt1.getDestination(), activityId, elString);
							}
						}
					}
				}else if("userTask".equals(ac.getProperty("type"))){
					return ((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition();
				}
			} 
			return null;
		}
	}
	
	/**
	 * 根据与流程定义processDefinitionId获取当前节点的下一个任务节点
	 * @param processDefinitionId流程定义processDefinitionId
	 * @param elementId当前节点Id
	 * @param elString当前节点流向下一个节点的匹配字符串如下${pass}或者${!pass} 获取领导同意的userTask，则传入 ${pass}
	 * @return
	 */
	public TaskDefinition getNextTaskDefinition(String processDefinitionId,String activityId,String elString,boolean flag){
		try {
			elString = URLDecoder.decode(elString,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		if (processDefinition != null) {
			ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinition.getId());  
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
			if ("userTask".equals(activityImpl.getProperty("type")) && !flag) {
				TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
				return taskDefinition;
			}else {
				List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
				List<PvmTransition> outpvmTransitions = null;
				for(PvmTransition pvmTransition : pvmTransitions){
					//获取所有的终点节点
					PvmActivity pa = pvmTransition.getDestination();
					if ("exclusiveGateway".equals(pa.getProperty("type"))) {
						outpvmTransitions = pa.getOutgoingTransitions();
						if (outpvmTransitions.size() == 1) {
							return getNextTaskDefinition(processDefinitionId, pa.getId(), elString, false);
						}else if (outpvmTransitions.size() > 1) {
							for (PvmTransition outPvmTransition : outpvmTransitions) {
								Object object = outPvmTransition.getProperty("conditionText");
								if (elString.equals((object.toString().trim()))) {
									PvmActivity pvmActivity = outPvmTransition.getDestination();
									return getNextTaskDefinition(processDefinitionId, pvmActivity.getId(), elString, false);
								}
							}
						}
					}else if ("userTask".equals(pa.getProperty("type"))) {
						return ((UserTaskActivityBehavior)((ActivityImpl)pa).getActivityBehavior()).getTaskDefinition();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取下一步相关内容
	 * @param taskID
	 * @return
	 */
	public List<PvmActivity> getNextTaskDefinition(String taskID){
		List<PvmActivity> pvmActivityList = new ArrayList<PvmActivity>();
		TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskID).singleResult();  
		ProcessDefinitionEntity def = (ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
		List<ActivityImpl> activitiList = def.getActivities(); 
		String excId = task.getExecutionId();
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
		String activitiId = execution.getActivityId();
		for(ActivityImpl activityImpl:activitiList){
			String id = activityImpl.getId();
			if(activitiId.equals(id)){
				//输出某个节点的某种属性
				System.out.println("当前任务："+activityImpl.getProperty("name")); 
				//获取从某个节点出来的所有线路
				List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
				for(PvmTransition pr:outTransitions){
					PvmActivity pa = pr.getDestination();
					pvmActivityList.add(pa);
					//获取线路的终点节点
					System.out.println("下一步任务："+pa.getProperty("name"));
				}
				break;
			}
		}
		return pvmActivityList;
	}
	
	/**
	 * API通过下个节点 获取节点中各个属性
	 * @param taskDefinition
	 */
	public void getNextTaskDefinition(TaskDefinition taskDefinition){
		Set<Expression>	setGroups = taskDefinition.getCandidateGroupIdExpressions();
		Set<Expression> setUsers = taskDefinition.getCandidateUserIdExpressions();
		Iterator<Expression> iterGroups = setGroups.iterator();
		Iterator<Expression> iterUsers = setUsers.iterator();
		List<String> alGroups = new ArrayList<String>();
		List<String> alUsers = new ArrayList<String>();
		while(iterGroups.hasNext()){
			Expression expression = iterGroups.next();
			alGroups.add(expression.getExpressionText());
		}
		while (iterUsers.hasNext()) {
			Expression expression = iterUsers.next();
			alUsers.add(expression.getExpressionText());
		}
		taskDefinition.getAssigneeExpression();
		taskDefinition.getCandidateUserIdExpressions();
		taskDefinition.getTaskListeners();
		taskDefinition.getTaskListener("");
		taskDefinition.getTaskFormHandler();
		taskDefinition.getPriorityExpression();
		taskDefinition.getKey();
		taskDefinition.getDueDateExpression();
		taskDefinition.getDescriptionExpression();
	}
	
	/**
	 * 查询所有实例
	 * @return
	 */
	public List<ProcessInstance> getProcessInstaceList(){ 
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();  
		return list;
	}
	
	/**
	 * 根据流程定义Key查找所有实例
	 * @param processDefinitionKey
	 * @return
	 */
	public List<ProcessInstance> getProcessInstaceList(String processDefinitionKey){ 
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefinitionKey).list();  
		return list;
	}
	
	
	/**
     * 获取活动任务
     * @return
     */
    public Map<String,Object> getProcessMap(ProcessInstance pi,ProcessDefinition processDefinition) {
    	Map<String,Object> map = new HashMap<String, Object>();
        List<ActivityImpl> actImpls = new ArrayList<ActivityImpl>();
        ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl) processDefinition;
        String processDefinitionId = pdImpl.getId();//流程标识
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
        List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点
        List<String> ActiveActivityIds = runtimeService.getActiveActivityIds(pi.getId());
        for (String activeId : ActiveActivityIds) {
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (activityImpl.isScope()) {
                    if (activityImpl.getActivities().size() > 1) {
                        List<ActivityImpl> subAcList = activityImpl.getActivities();
                        for (ActivityImpl subActImpl : subAcList) {
                            String subid = subActImpl.getId();
                            if (activeId.equals(subid)) {// 获得执行到那个节点
                                actImpls.add(subActImpl);
                                break;
                            }
                        }
                    }
                }
                if (activeId.equals(id)) {// 获得执行到那个节点
                    actImpls.add(activityImpl);
                }
            }
        }
        map.put("actImpls", actImpls);
        map.put("activitiList", activitiList);
        return map;
    }
    
    /**
     * 根据流程实例获取流程图并高亮
     * @param processInstanceId
     * @return
     */
    @SuppressWarnings("unchecked")
	public Map<String,Object> getProcessInstanceImg(String processInstanceId){
    	Map<String,Object> map = new HashMap<String, Object>();
    	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    String processDefinitionId = processInstance.getProcessDefinitionId();
	    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	    String deploymentId = processDefinition.getDeploymentId();
	    String imageName = processDefinition.getDiagramResourceName();
	    Map<String, Object> actMap = getProcessMap(processInstance,processDefinition);
	    List<ActivityImpl> processMap = (List<ActivityImpl>)actMap.get("actImpls");
	    List<ActivityImpl> activitiList = (List<ActivityImpl>)actMap.get("activitiList");
	    ActivityImpl activity = processMap.get(0);
	    int x = activity.getX();
	    int y = activity.getY();
	    int width = activity.getWidth();
	    int height = activity.getHeight();
	    InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, imageName);
	    String base64Img = ImageAnd64Binary.GetImageStr(resourceAsStream);
	    map.put("x", x);
	    map.put("y", y);
	    map.put("width", width);
        map.put("height", height);
        map.put("img", "data:image/png;base64," + base64Img);
        map.put("ActivityImpl", processMap);
        map.put("activitiList", activitiList);
	    return map;
    }
    
    /**
     * 添加审批（发起申请）
     * @param id流程发布历史编号
     * @param businessKey业务编号（可选）
     * @param variables变量（可选）
     * @return
     */
	public boolean addApply(String id,String businessKey,Map<String, Object> variables,Lc_Apply lc_Apply){
		try {
			Xt_ConstantService xt_ConstantService = (Xt_ConstantService)GetApplicationContext.getBean("xt_ConstantService");
			Lc_Deployment_HisService lc_Deployment_HisService = (Lc_Deployment_HisService)GetApplicationContext.getBean("lc_Deployment_HisService");
			Lc_Deployment_His lc_Deployment_His = lc_Deployment_HisService.getLcDeploymentHisById(id);
			Xt_Constant xtConstant = xt_ConstantService.getXtConstantById(lc_Deployment_His.getXt_constant_id());
			Lc_ApplyService lc_ApplyService = (Lc_ApplyService)GetApplicationContext.getBean("lc_ApplyService");
			ProcessDefinition processDefinition = getProcessDefinition(lc_Deployment_His.getLc_deployment_his_id());
			ProcessInstance procesInstance = startProcessInstanceByKey(processDefinition.getKey(), businessKey, variables);
			/**Activiti发起实例模块(即提交发起申请)开始**/
			/**调用审批工作流**/
			if(null != procesInstance){
				if(StringUtil.isEmpty(lc_Apply.getLc_apply_id())){
					lc_Apply.setLc_apply_id(UUID.toUUID());
				}
				lc_Apply.setLc_apply_model_id(xtConstant.getXt_constant_id());
				lc_Apply.setXt_userinfo_id(CommonUtils.getXtUid());
				lc_Apply.setLc_apply_time(CommonUtils.getSimpleDateFormat());
				lc_Apply.setProcessdefinitions_id(processDefinition.getId());
				lc_Apply.setProcessInstance_id(procesInstance.getId());
				lc_ApplyService.addLcApply(lc_Apply);
				System.out.println("-------调用工作流审批信息模块成功-------");
				return true;
			}else{
				System.out.println("-------调用工作流审批信息模块失败-------");
				return false;
			}
			/**Activiti发起实例模块(即提交发起申请)结束**/
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
		}
	}
	
	/**
	 * 将组任务指定个人任务（该方法：检查该任务是否已经被认领，如果被认领则会抛出ActivitiTaskAlreadyClaimedException）
	 * taskId
	 * userId
	 */  
    public boolean claim(String taskId,String userId){  
    	try {
    		 taskService.claim(taskId, userId);  
    		 return true;
		} catch (Exception e) {
			return false;
		}
    }  
    
    /**
     * 将个人任务再回退到组任务（前提：之前这个任务是组任务）
     * taskId
     * userId
     */  
    public boolean setAssignee(String taskId,String userId){  
    	try {
    		taskService.setAssignee(taskId, userId);   
   		 return true;
		} catch (Exception e) {
			return false;
		}
    }  
    

    /**
     * 设置任务的归属者
     * @param taskId
     * @param userId
     */
    public boolean setOwner(String taskId,String userId){
    	try {
    		taskService.setOwner(taskId, userId); 
   		 return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
     * 向组任务中添加成员
     * taskId
     * userId
     */  
    public boolean addGroupUser(String taskId,String userId){  
    	try {
    		 taskService.addCandidateUser(taskId, userId);  
   		 return true;
		} catch (Exception e) {
			return false;
		}
    }  
    
    /**
     * 向组任务中删除成员
     * taskId
     * userId
     */  
    public boolean deleteGroupUser(String taskId,String userId){  
    	try {
    		taskService .deleteCandidateUser(taskId, userId);  
  		 return true;
		} catch (Exception e) {
			return false;
		}
    }   
	
	/**
	 * 查找个人任务
	 * @param condition
	 * @return
	 */
	public Map<String, Object> getAssigneeTaskPageList(Map<String, Object> condition){
		Map<String, Object> map = new HashMap<String, Object>();
		int offset = new Integer(condition.get("offset").toString());
		int pageSize = new Integer(condition.get("pageSize").toString());
		/**
		List<Task> list = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();  
	    if (list != null && list.size() > 0) {  
	        for (Task task : list) {  
	            System.out.println("当前任务办理人：    " + task.getAssignee());  
	            System.out.println("任务类型：       " + task.getCategory());  
	            System.out.println("任务描述：       " + task.getDescription());  
	            System.out.println("任务执行ID： " + task.getExecutionId());  
	            System.out.println("表单key：      " + task.getFormKey());  
	            System.out.println("任务ID：       " + task.getId());  
	            System.out.println("任务名称：       " + task.getName());  
	            System.out.println("任务所有者：  " + task.getOwner());  
	            System.out.println("任务父ID：      " + task.getParentTaskId());  
	            System.out.println("任务优先级：  " + task.getPriority());  
	            System.out.println("流程定义的id：    " + task.getProcessDefinitionId());  
	            System.out.println("流程实例的id：    " + task.getProcessInstanceId());  
	            System.out.println("任务定义的key：   " + task.getTaskDefinitionKey());  
	            System.out.println("所有人ID：      " + task.getTenantId());  
	            System.out.println("任务创建时间： " + task.getCreateTime());  
	            System.out.println("任务委派状态： " + task.getDelegationState());  
	            System.out.println("持续时间：       " + task.getDueDate());  
	            System.out.println("任务流程变量： " + task.getProcessVariables());  
	            System.out.println("##############################");  
	        }  
	    }  
	    **/
		List<Task> TaskList = taskService.createTaskQuery().taskAssignee(condition.get("assignee").toString()).listPage(offset, pageSize); 
		long count = taskService.createTaskQuery().taskAssignee(condition.get("assignee").toString()).count();
		map.put("TaskList", TaskList);
		map.put("TaskCount", count);
		return map;
	}
	
	/**
	 * 查找候选人任务
	 * @param condition
	 * @return
	 */
	public Map<String, Object> getCandidateTaskPageList(Map<String, Object> condition){  
		Map<String, Object> map = new HashMap<String, Object>();
		int offset = new Integer(condition.get("offset").toString());
		int pageSize = new Integer(condition.get("pageSize").toString());
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(condition.get("candidateUser").toString()).listPage(offset, pageSize); 
		long count = taskService.createTaskQuery().taskCandidateUser(condition.get("candidateUser").toString()).count();
		map.put("TaskList", list);
		map.put("TaskCount", count);
		return map;
	}  
	
	/**
	 * 查找处理组任务
	 * @param condition
	 * @return
	 */
	public Map<String, Object> getCandidateGroupTaskPageList(Map<String, Object> condition){ 
		Map<String, Object> map = new HashMap<String, Object>();
		int offset = new Integer(condition.get("offset").toString());
		int pageSize = new Integer(condition.get("pageSize").toString());
		List<Task> list = taskService.createTaskQuery().taskCandidateGroup(condition.get("candidateGroup").toString()).listPage(offset, pageSize); 
		long count = taskService.createTaskQuery().taskCandidateGroup(condition.get("candidateGroup").toString()).count(); 
        map.put("TaskList", list);
		map.put("TaskCount", count);
		return map;
	}
	
	/**
	 * 完成任务
	 * @param taskId
	 */
	public boolean completeTask(String taskId){
		try {
			taskService.complete(taskId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断流程实例是否已经结束
	 * @param processInstanceId
	 * @return
	 */
	public boolean isEnded(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        return processInstance.isEnded();
    }
	
	/**
	 * 获取所有任务
	 * @param condition
	 * @return
	 */
	public Map<String, Object> getTaskList(Map<String, Object> condition){
		Map<String, Object> map = new HashMap<String, Object>();
		int offset = new Integer(condition.get("offset").toString());
		int pageSize = new Integer(condition.get("pageSize").toString());
		List<Task> list = taskService.createTaskQuery().orderByTaskCreateTime().desc().listPage(offset, pageSize); 
		long count = taskService.createTaskQuery().count(); 
        map.put("TaskList", list);
		map.put("TaskCount", count);
		return map;
	}
	
	/**
	 * 获取实例下所有任务
	 * @param condition
	 * @return
	 */
	public Map<String, Object> getTaskListByInstanceId(Map<String, Object> condition){
		Map<String, Object> map = new HashMap<String, Object>();
		int offset = new Integer(condition.get("offset").toString());
		int pageSize = new Integer(condition.get("pageSize").toString());
		String instanceId = ""+condition.get("instanceId");
		List<Task> list = taskService.createTaskQuery().orderByTaskCreateTime().desc().processInstanceId(instanceId).listPage(offset, pageSize); 
		long count = taskService.createTaskQuery().count(); 
        map.put("TaskList", list);
		map.put("TaskCount", count);
		return map;
	} 
	
	
	/**
	 * 调用跳转节点
	 * executionID:当前任务
	 * activityId:跳转目标
	 */
    public boolean jump(String executionId,String activityId){
    	try {
    		TaskServiceImpl taskServiceImpl=(TaskServiceImpl)taskService;  
        	taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, activityId));  
        	return true;
		} catch (Exception e) {
			return false;
		}
    }
}
