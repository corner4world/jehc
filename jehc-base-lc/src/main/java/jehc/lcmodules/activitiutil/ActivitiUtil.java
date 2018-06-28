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
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.lcmodules.activitiutil.util.Variable;
import jehc.lcmodules.lcdao.LcApplyDao;
import jehc.lcmodules.lcdao.LcDeploymentHisDao;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.lcmodules.lcmodel.LcDeploymentHis;
import jehc.xtmodules.xtcore.allutils.ImageAnd64Binary;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtConstantDao;
import jehc.xtmodules.xtmodel.XtConstant;
/**
 * activiti核心引擎
 * @author邓纯杰
 *
 */
@SuppressWarnings("unused")
public class ActivitiUtil extends BaseService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
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
			throw new ExceptionUtil(e.getMessage(),e.getCause());
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
			throw new ExceptionUtil(e.getMessage(),e.getCause());
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
			throw new ExceptionUtil(ex.getMessage(),ex.getCause());
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
		map.put("task", task);
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
			throw new ExceptionUtil(e.getMessage(),e.getCause());
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
			throw new ExceptionUtil(e.getMessage(),e.getCause());
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
     * 获取当前流程的下一个节点
     * @param procInstanceId
     * @return
     */
    public PvmActivity getNextNode(String procInstanceId){
        // 1、首先是根据流程ID获取当前任务：
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstanceId).list();
        String nextId = "";
        PvmActivity ac=null;
        for (Task task : tasks) {
            // 2、然后根据当前任务获取当前流程的流程定义，然后根据流程定义获得所有的节点：
            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(task.getProcessDefinitionId());
            List<ActivityImpl> activitiList = def.getActivities(); 
            // 3、根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID：
            String excId = task.getExecutionId();
            ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId)
                    .singleResult();
            String activitiId = execution.getActivityId();
            // 4、然后循环activitiList
            // 并判断出当前流程所处节点，然后得到当前节点实例，根据节点实例获取所有从当前节点出发的路径，然后根据路径获得下一个节点实例：
            for (ActivityImpl activityImpl : activitiList) {
                String id = activityImpl.getId();
                if (activitiId.equals(id)) {
                    logger.debug("当前任务：" + activityImpl.getProperty("name")); // 输出某个节点的某种属性
                    List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
                    for (PvmTransition tr : outTransitions) {
                        ac = tr.getDestination(); // 获取线路的终点节点
                        logger.debug("下一步任务任务：" + ac.getProperty("name"));
                        nextId = ac.getId();
                    }
                    break;
                }
            }
        }
        return ac;
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
	 * 获取当前任务集合 根据流程实例
	 * @param procInstanceId
	 * @return
	 */
	public List<Task> getCurrentTaskList(String procInstanceId){
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstanceId).list(); 
//		for(Task task:tasks){
//			 taskService.setAssignee(task.getId(), "用户编号");  
//		}
		return tasks;
	}
	
	/**
	 * 根据当前节点获取下个节点为用户任务的所有节点编号
	 * @param procInstanceId
	 * @return
	 */
	public List<String> getNextTaskList(ProcessInstance processInstance){
		List<String> taskIdList = new ArrayList<String>();
		ProcessDefinitionEntity processDefinition1 = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
		        .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());  
		List<PvmActivity> pvmActivityList = new ArrayList<PvmActivity>();
		List<ActivityImpl> activitiList = processDefinition1.getActivities();
		for(ActivityImpl activityImpl:activitiList){
//			if ("userTask".equals(activityImpl.getProperty("type"))) {
//				List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
//				for(PvmTransition pr:outTransitions){
//					PvmActivity pa = pr.getDestination();
//					pa.getId();
//					pvmActivityList.add(pa);
//					//获取线路的终点节点
//					System.out.println("下一步任务："+pa.getProperty("name"));
//				}
//			}
			
			List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
			for(PvmTransition pr:outTransitions){
				PvmActivity pa = pr.getDestination();
				pvmActivityList.add(pa);
			}
		}
		for(PvmActivity pvmActivity:pvmActivityList){
			if ("userTask".equals(pvmActivity.getProperty("type"))) {
				taskIdList.add(pvmActivity.getId());
			}
		}
		return taskIdList;
	} 
	/**
	 * 获取当前所有活动节点 根据流程实例
	 * @param processInstance
	 * @return
	 */
	public List<ActivityImpl> getActivitiList(ProcessInstance processInstance){
		ProcessDefinitionEntity processDefinition1 = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
		        .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());  
		List<ActivityImpl> activitiList = processDefinition1.getActivities();
		return activitiList;
	}
	
//	public List<Task> getTaskList(ProcessInstance processInstance){
//		ProcessDefinitionEntity processDefinition1 = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
//		        .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());  
//		List<PvmActivity> pvmActivityList = new ArrayList<PvmActivity>();
//		List<ActivityImpl> activitiList = processDefinition1.getActivities();
//		for(ActivityImpl activityImpl:activitiList){
//			if ("userTask".equals(activityImpl.getProperty("type"))) {
//				List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
//				for(PvmTransition pr:outTransitions){
//					PvmActivity pa = pr.getDestination();
//					pvmActivityList.add(pa);
//					//获取线路的终点节点
//					System.out.println("下一步任务："+pa.getProperty("name"));
//				}
//			}
//		}
//	}
	
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
	 * 根据流程定义id查找所有实例
	 * @param processDefinitionKey
	 * @return
	 */
	public List<ProcessInstance> getProcessInstaceListById(String processDefinitionId){ 
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).list();  
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
	    if(null == processInstance){
	    	map.put("processInstance", null);
	    	return map;
	    }
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
        map.put("processInstance", processInstance);
	    return map;
    }
    
    /**
     * 根据流程定义获取流程图
     * @param processInstanceId
     * @return
     */
	public String getProcessImgEd(String deploymentId,String name){
    	Map<String,Object> map = new HashMap<String, Object>();
	    InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, name);
	    String base64Img = ImageAnd64Binary.GetImageStr(resourceAsStream);
	    return "data:image/png;base64," + base64Img;
    }
	
//	 public String viewImage(){
//	        InputStream  in = repositoryService.getResourceAsStream().getImageStream(deploymentId,imageName);
//	        HttpServletResponse resp = ServletActionContext.getResponse();
//	        try {
//	            OutputStream out = resp.getOutputStream();
//	            // 把图片的输入流程写入resp的输出流中
//	            byte[] b = new byte[1024];
//	            for (int len = -1; (len= in.read(b))!=-1; ) {
//	                out.write(b, 0, len);
//	            }
//	            // 关闭流
//	            out.close();
//	            in.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        return null;
//	    }
    
    /**
     * 添加审批（发起申请）
     * @param id流程发布历史编号
     * @param businessKey业务编号（可选）
     * @param variables变量（可选）
     * @return
     */
	public boolean addApply(String id,String businessKey,Map<String, Object> variables,LcApply lc_Apply){
		try {
			XtConstantDao xt_ConstantDao = (XtConstantDao)SpringUtil.getBean("xtConstantDao");
			LcDeploymentHisDao lc_Deployment_HisDao = (LcDeploymentHisDao)SpringUtil.getBean("lcDeploymentHisDao");
			if(StringUtil.isEmpty(id)){
				throw new ExceptionUtil("未能获取到最新流程部署"); 
			}
			LcDeploymentHis lc_Deployment_His = lc_Deployment_HisDao.getLcDeploymentHisById(id);
			XtConstant xtConstant = xt_ConstantDao.getXtConstantById(lc_Deployment_His.getXt_constant_id());
			LcApplyDao lc_ApplyService = (LcApplyDao)SpringUtil.getBean("lcApplyDao");
			ProcessDefinition processDefinition = getProcessDefinition(lc_Deployment_His.getLc_deployment_his_id());
			ProcessInstance procesInstance = startProcessInstanceByKey(processDefinition.getKey(), businessKey, variables);
			/**Activiti发起实例模块(即提交发起申请)开始**/
			/**调用审批工作流**/
			if(null != procesInstance){
				if(StringUtil.isEmpty(lc_Apply.getLc_apply_id())){
					lc_Apply.setLc_apply_id(UUID.toUUID());
				}
				lc_Apply.setLc_apply_model_id(xtConstant.getXt_constant_id());
				lc_Apply.setXt_userinfo_id(getXtUid());
				lc_Apply.setLc_apply_time(getDate());
				lc_Apply.setProcessdefinitions_id(processDefinition.getId());
				lc_Apply.setLc_apply_model_biz_id(businessKey);
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
		}
	}
	
	/**
     * 添加审批（发起申请）并设置第一个节点发起人并完成第一个节点
     * @param id流程发布历史编号
     * @param businessKey业务编号（可选）
     * @param variables变量（可选）
     * @return
     */
	public boolean addApplySetAssignee(String id,String businessKey,Map<String, Object> variables,LcApply lc_Apply){
		try {
			XtConstantDao xt_ConstantDao = (XtConstantDao)SpringUtil.getBean("xtConstantDao");
			LcDeploymentHisDao lc_Deployment_HisDao = (LcDeploymentHisDao)SpringUtil.getBean("lcDeploymentHisDao");
			LcDeploymentHis lc_Deployment_His = lc_Deployment_HisDao.getLcDeploymentHisById(id);
			if(null == lc_Deployment_His){
				throw new ExceptionUtil("未能获取到流程部署最新对象,jehc.lcmodules.lcmodel.LcDeploymentHis"); 
			}
			XtConstant xtConstant = xt_ConstantDao.getXtConstantById(lc_Deployment_His.getXt_constant_id());
			LcApplyDao lc_ApplyDao = (LcApplyDao)SpringUtil.getBean("lcApplyDao");
			ProcessDefinition processDefinition = getProcessDefinition(lc_Deployment_His.getLc_deployment_his_id());
			ProcessInstance procesInstance = startProcessInstanceByKey(processDefinition.getKey(), businessKey, variables);
			/**Activiti发起实例模块(即提交发起申请)开始**/
			/**调用审批工作流**/
			if(null != procesInstance){
				if(StringUtil.isEmpty(lc_Apply.getLc_apply_id())){
					lc_Apply.setLc_apply_id(UUID.toUUID());
				}
				lc_Apply.setLc_apply_model_id(xtConstant.getXt_constant_id());
				lc_Apply.setXt_userinfo_id(getXtUid());
				lc_Apply.setLc_apply_time(getDate());
				lc_Apply.setProcessdefinitions_id(processDefinition.getId());
				lc_Apply.setLc_apply_model_biz_id(businessKey);
				lc_Apply.setProcessInstance_id(procesInstance.getId());
				lc_ApplyDao.addLcApply(lc_Apply);
				
				///////////根据当前实例编号查找第一个节点并设置发起人//////////////
				List<Task> tasks = taskService.createTaskQuery().processInstanceId(procesInstance.getId()).orderByTaskCreateTime().asc().list();
				if(null != tasks &&!tasks.isEmpty()){
					//设置当前任务为处理人即发起人
					setAssignee(tasks.get(0).getId(),getXtUid());
					//完成第一个任务即发起人
					completeTask(tasks.get(0).getId());
				}
				logger.debug("-------调用工作流审批信息模块成功-------");
				return true;
			}else{
				logger.debug("-------调用工作流审批信息模块失败-------");
				return false;
			}
			/**Activiti发起实例模块(即提交发起申请)结束**/
		} catch (Exception e) {
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
	 * 查找个人任务(不分页)
	 * @param condition
	 * @return
	 */
	public List<Task> getAssigneeTaskList(Map<String, Object> condition){
		return taskService.createTaskQuery().taskAssignee(condition.get("assignee").toString()).list(); 
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
			throw new ExceptionUtil(e.getMessage(),e.getCause()); 
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
     * 会签操作 
     * @param taskId当前任务ID 
     * @param userCodes会签人账号集合 
     * @throws Exception 
     */  
    public void jointProcess(String taskId, List<String> userCodes)throws Exception {  
        for (String userCode : userCodes) {  
            TaskEntity task = (TaskEntity) taskService.newTask(IdGenerator.class.newInstance().getNextId());  
            task.setAssignee(userCode);  
            task.setName(findTaskById(taskId).getName() + "-会签");  
            task.setProcessDefinitionId(findProcessDefinitionEntityByTaskId(taskId).getId());  
            task.setProcessInstanceId(findProcessInstanceByTaskId(taskId).getId());  
            task.setParentTaskId(taskId);  
            task.setDescription("jointProcess");  
            taskService.saveTask(task);  
        }  
    }  
	/** 
     * 驳回流程 
     * @param taskId 当前任务ID 
     * @param activityId驳回节点ID 
     * @param variables流程存储参数 
     * @throws Exception 
    */  
    public void backProcess(String taskId, String activityId,Map<String, Object> variables) throws Exception {  
        if (StringUtils.isEmpty(activityId)) {  
            throw new Exception("驳回目标节点ID为空！");  
        }  
        // 查找所有并行任务节点，同时驳回  
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());  
        for (Task task : taskList) {  
            commitProcess(task.getId(), variables, activityId);  
        }  
    }

    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#callBackProcess(java.lang.String, java.lang.String)
     * 取回流程 
     * @param taskId 当前任务ID 
     * @param activityId取回节点ID 
     * @throws Exception 
     */  
    public void callBackProcess(String taskId, String activityId)throws Exception {  
        if (StringUtils.isEmpty(activityId)) {  
            throw new Exception("目标节点ID为空！");  
        }  
        // 查找所有并行任务节点，同时取回  
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());  
        for (Task task : taskList) {  
            commitProcess(task.getId(), null, activityId);  
        }  
    }

	/** 
     * 清空指定活动节点流向 
     * @param activityImpl活动节点 
     * @return 节点流向集合 
     */  
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        //存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();  
        //获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
        return oriPvmTransitionList;  
    }

	/** 
     * @param taskId 当前任务ID 
     * @param variables 流程变量 
     * @param activityId 流程转向执行任务节点ID 此参数为空，默认为提交操作 
     * @throws Exception 
     */  
    private void commitProcess(String taskId, Map<String, Object> variables,String activityId) throws Exception {  
        if (variables == null) {  
            variables = new HashMap<String, Object>();  
        }  
        // 跳转节点为空，默认提交操作  
        if (StringUtils.isEmpty(activityId)) {  
            taskService.complete(taskId, variables);  
        } else {// 流程转向操作  
            turnTransition(taskId, activityId, variables);  
        }  
    }

    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#endProcess(java.lang.String)
     * 中止流程(特权人直接审批通过等) 
     * @param taskId 
     */
    public void endProcess(String taskId) throws Exception {  
        ActivityImpl endActivity = findActivitiImpl(taskId, "end");  
        commitProcess(taskId, null, endActivity.getId());  
    }

	/** 
     * 根据流入任务集合，查询最近一次的流入任务节点 
     * @param processInstance 流程实例 
     * @param tempList 流入任务集合 
     * @return 
     */  
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,List<ActivityImpl> tempList) {  
        while (tempList.size() > 0) {  
            ActivityImpl activity_1 = tempList.get(0);  
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(processInstance, activity_1.getId());  
            if (activityInstance_1 == null) {  
                tempList.remove(activity_1);  
                continue;  
            }  
            if (tempList.size() > 1) {  
                ActivityImpl activity_2 = tempList.get(1);  
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(processInstance, activity_2.getId());  
                if (activityInstance_2 == null) {  
                    tempList.remove(activity_2);  
                    continue;  
                }  
                if (activityInstance_1.getEndTime().before(activityInstance_2.getEndTime())) {  
                    tempList.remove(activity_1);  
                } else {  
                    tempList.remove(activity_2);  
                }  
            } else {  
                break;  
            }  
        }  
        if (tempList.size() > 0) {  
            return tempList.get(0);  
        }  
        return null;  
    }

	/** 
     * 根据任务ID和节点ID获取活动节点
     * @param taskId 任务ID 
     * @param activityId 活动节点ID 如果为null或""，则默认查询当前活动节点 如果为"end"，则查询结束节点
     * @return 
     * @throws Exception 
     */  
    private ActivityImpl findActivitiImpl(String taskId, String activityId)throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
        // 获取当前活动节点ID  
        if (StringUtils.isEmpty(activityId)) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl  
                        .getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition).findActivity(activityId);  
        return activityImpl;  
    }

    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findBackAvtivity(java.lang.String)
     * 根据当前任务ID，查询可以驳回的任务节点 
     * @param taskId 当前任务ID 
     */ 
    public List<ActivityImpl> findBackAvtivity(String taskId) throws Exception {  
        List<ActivityImpl> rtnList = iteratorBackActivity(taskId, findActivitiImpl(taskId,  
                    null), new ArrayList<ActivityImpl>(),  
                    new ArrayList<ActivityImpl>());  
        return reverList(rtnList);  
    }

    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findHistoricUserTask(org.activiti.engine.runtime.ProcessInstance, java.lang.String)
     * 查询指定任务节点的最新记录 
     * @param processInstance 流程实例 
     * @param activityId 
     * @return 
    */
    public HistoricActivityInstance findHistoricUserTask(ProcessInstance processInstance, String activityId) {  
        HistoricActivityInstance rtnVal = null;  
        // 查询当前流程实例审批结束的历史节点  
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().activityType("userTask")  
                .processInstanceId(processInstance.getId()).activityId(activityId).finished()  
                .orderByHistoricActivityInstanceEndTime().desc().list();  
        if (historicActivityInstances.size() > 0) {  
            rtnVal = historicActivityInstances.get(0);  
        }  
        return rtnVal;  
    }  
  
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findParallelGatewayId(org.activiti.engine.impl.pvm.process.ActivityImpl)
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID 
     * @param activityImpl 当前节点 
     * @return 
     */  
    public String findParallelGatewayId(ActivityImpl activityImpl) {  
        List<PvmTransition> incomingTransitions = activityImpl.getOutgoingTransitions();  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            activityImpl = transitionImpl.getDestination();  
            String type = (String) activityImpl.getProperty("type");  
            if ("parallelGateway".equals(type)) {
            	//并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId.lastIndexOf("_") + 1);  
                if ("END".equals(gatewayType.toUpperCase())) {  
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))+ "_start";  
                }  
            }  
        }  
        return null;  
    }  
  
    /**
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findProcessDefinitionEntityByTaskId(java.lang.String) 
     * 根据任务ID获取流程定义 
     * @param taskId 任务ID 
     * @return 
     * @throws Exception 
     */ 
    public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId) throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(findTaskById(taskId).getProcessDefinitionId());  
        if (processDefinition == null) {  
            throw new Exception("流程定义未找到!");  
        }  
        return processDefinition;  
    }  
  
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findProcessInstanceByTaskId(java.lang.String)
     * 根据任务ID获取对应的流程实例 
     * @param taskId 任务ID 
     * @return 
     * @throws Exception 
     */
    public ProcessInstance findProcessInstanceByTaskId(String taskId)throws Exception {  
        // 找到流程实例  
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(findTaskById(taskId).getProcessInstanceId()).singleResult();  
        if (processInstance == null) {   
            throw new Exception("流程实例未找到!");  
        }  
        return processInstance;  
    }  
  
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findTaskById(java.lang.String)
     * 根据任务ID获得任务实例 
     * @param taskId 任务ID 
     * @return 
     * @throws Exception 
     */  
    public TaskEntity findTaskById(String taskId) throws Exception {  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();  
        if (task == null) {  
            throw new Exception("任务实例未找到!");  
        }  
        return task;  
    }  
  
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#findTaskListByKey(java.lang.String, java.lang.String)
     * 根据流程实例ID和任务key值查询所有同级任务集合 
     * @param processInstanceId 
     * @param key 
     * @return 
     */
    public List<Task> findTaskListByKey(String processInstanceId, String key) {  
        return taskService.createTaskQuery().processInstanceId(processInstanceId).taskDefinitionKey(key).list();  
    }  
  
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#iteratorBackActivity(java.lang.String, org.activiti.engine.impl.pvm.process.ActivityImpl, java.util.List, java.util.List)
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点 
     * @param taskId 当前任务ID 
     * @param currActivity 当前活动节点 
     * @param rtnList 存储回退节点集合 
     * @param tempList 临时存储节点集合（存储一次迭代过程中的同级userTask节点） 
     * @return 回退节点集合 
     */  
    public List<ActivityImpl> iteratorBackActivity(String taskId,  
            ActivityImpl currActivity, List<ActivityImpl> rtnList,  
            List<ActivityImpl> tempList) throws Exception {  
        // 查询流程定义，生成流程树结构  
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);  
        // 当前节点的流入来源  
        List<PvmTransition> incomingTransitions = currActivity.getIncomingTransitions();  
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点  
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();  
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点  
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();  
        // 遍历当前节点所有流入路径  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            ActivityImpl activityImpl = transitionImpl.getSource();  
            String type = (String) activityImpl.getProperty("type");  
            /** 
             * 并行节点配置要求：
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束) 
             */  
            if ("parallelGateway".equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId.lastIndexOf("_") + 1);  
                if ("START".equals(gatewayType.toUpperCase())) {
                	//并行起点，停止递归  
                    return rtnList;  
                } else {
                	//并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                    parallelGateways.add(activityImpl);  
                }  
            } else if ("startEvent".equals(type)) {
            	//开始节点，停止递归  
                return rtnList;  
            } else if ("userTask".equals(type)) {
            	//用户任务  
                tempList.add(activityImpl);  
            } else if ("exclusiveGateway".equals(type)) {
            	//分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                currActivity = transitionImpl.getSource();  
                exclusiveGateways.add(currActivity);  
            }  
        }  
        /** 
         * 迭代条件分支集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : exclusiveGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
        /** 
         * 迭代并行集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : parallelGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
        /** 
         * 根据同级userTask集合，过滤最近发生的节点 
         */  
        currActivity = filterNewestActivity(processInstance, tempList);  
        if (currActivity != null) {  
            //查询当前节点的流向是否为并行终点，并获取并行起点ID  
            String id = findParallelGatewayId(currActivity);  
            if (StringUtils.isEmpty(id)) {
            	//并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点  
                rtnList.add(currActivity);  
            } else {
            	//根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点  
                currActivity = findActivitiImpl(taskId, id);  
            }  
            // 清空本次迭代临时集合  
            tempList.clear();  
            // 执行下次迭代  
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);  
        }  
        return rtnList;  
    }  
  
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#restoreTransition(org.activiti.engine.impl.pvm.process.ActivityImpl, java.util.List)
     * 还原指定活动节点流向 
     * @param activityImpl 活动节点 
     * @param oriPvmTransitionList 原有节点流向集合 
     */ 
    public void restoreTransition(ActivityImpl activityImpl,List<PvmTransition> oriPvmTransitionList) {  
        //清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
        pvmTransitionList.clear();  
        //还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }  
    
    /** 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#reverList(java.util.List)
     * 反向排序list集合，便于驳回节点按顺序显示 
     * @param list 
     * @return 
     */  
    public List<ActivityImpl> reverList(List<ActivityImpl> list) {  
        List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();  
        //由于迭代出现重复数据，排除重复  
        for (int i = list.size(); i > 0; i--) {  
            if (!rtnList.contains(list.get(i - 1)))  
                rtnList.add(list.get(i - 1));  
        }  
        return rtnList;  
    }  
  
    /** 
     * 转办流程 
     * @param taskId 当前任务节点ID 
     * @param userCode 被转办人Code 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#transferAssignee(java.lang.String, java.lang.String)
     */
    public void transferAssignee(String taskId, String userCode) {  
        taskService.setAssignee(taskId, userCode);  
    }  
  
    /** 
     * 流程转向操作 
     * @param taskId 
     * 当前任务ID 
     * @param activityId 
     * 目标节点任务ID 
     * @param variables 
     * 流程变量 
     * @throws Exception 
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#turnTransition(java.lang.String, java.lang.String, java.util.Map)
     */  
    public void turnTransition(String taskId, String activityId,Map<String, Object> variables) throws Exception {  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
        // 执行转向任务  
        taskService.complete(taskId, variables);  
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    }  
    
    /**
     * (non-Javadoc)
	 * @see org.activiti.web.simple.webapp.service.ActivitiService#getImageStream(java.lang.String)
     * @param taskId 根据当前任务获取图片
     */
    public InputStream getImageStream(String taskId) throws Exception{
//    	ProcessDefinitionEntity pde = findProcessDefinitionEntityByTaskId(taskId);
//    	InputStream imageStream = ProcessDiagramGenerator.generateDiagram(pde, "png",runtimeService.getActiveActivityIds(findProcessInstanceByTaskId(taskId).getId()));
//    	return imageStream;
    	return null;
    }

    /**
     * 验证登录
     */
	public boolean login(String userid, String password) throws Exception {
		return identityService.checkPassword(userid, password);
	}

	/**
	 * 获取用户详细信息
	 */
	public User getUserInfo(String userid) {
		return identityService.createUserQuery().userId(userid).singleResult();
	}

	/**
	 * 根据用户id查询用户所在的组
	 */
	public List<Group> getUserOfGroup(String userid) {
		return identityService.createGroupQuery().groupMember(userid).list();
	}


	/**
	 * 根据groupId查询组详细信息
	 */
	public Group getGroupInfo(String groupId) {
		return identityService.createGroupQuery().groupId(groupId).singleResult();
	}

	/**
	 * 列出组内的所有用户
	 */
	public List<User> memberOfGroup(String groupId) {
		return identityService.createUserQuery().memberOfGroup(groupId).list();
	}
	
	/**
	 * 根据流程实例编号查找当前任务
	 * @param processInstanceID
	 * @return
	 */
	public Task getTaskByProcessInstanceID(String processInstanceID){
		if(validatePEnd(processInstanceID)){
			return null;
		}
		return taskService.createTaskQuery().processInstanceId(processInstanceID).singleResult();
	}
	
	/**
	 * 发起开始动态表单 返回实例对象
	 * @param processDefinitionId
	 * @param properties
	 * @return
	 */
	public ProcessInstance submitStartFormData(String processDefinitionId, Map<String, String> properties){
		try {
			ProcessInstance processInstance = formService.submitStartFormData(processDefinitionId, properties);
			return processInstance;
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 *  发起开始动态表单 返回实例对象
	 * @param processDefinitionId
	 * @param properties
	 * @return
	 */
	public ProcessInstance submitStartFormData(String processDefinitionId, String businessKey, Map<String, String> properties){
		try {
			ProcessInstance processInstance = formService.submitStartFormData(processDefinitionId, businessKey, properties);
			return processInstance;
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据流程定义获取开始表单字段信息（即内容）
	 * @param processDefinitionId
	 * @return
	 */
	public StartFormData getStartFormData(String processDefinitionId){
		StartFormData startFormData = formService.getStartFormData(processDefinitionId);
		return startFormData;
	}
	
	/**
	 * 获取开始节点中表单属性集合
	 * @param startFormData
	 * @return
	 */
	public  List<FormProperty> getStartFormProperties(StartFormData startFormData){
		return startFormData.getFormProperties();
	}
	/**
	 * 根据taskId获取task节点中表单
	 * @param taskId
	 * @return
	 */
	public TaskFormData getTaskFormData(String taskId){
		return formService.getTaskFormData(taskId);
	}
	
	/**
	 * 获取task节点中表单属性集合
	 * @param startFormData
	 * @return
	 */
	public  List<FormProperty> getTaskFormProperties(TaskFormData taskFormData){
		return taskFormData.getFormProperties();
	}
	
	/**
	 * 发起任务动态表单
	 * @param processDefinitionId
	 * @param properties
	 * @return
	 */
	public void submitTaskFormData(String processDefinitionId, Map<String, String> properties){
		try {
			formService.submitTaskFormData(processDefinitionId, properties);
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
