package jehc.lcmodules.activitiutil.zl;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 预设置节点处理人
 * @author 邓纯杰
 */
public class AssigneeLister implements TaskListener {
	private static final long serialVersionUID = 1L;
	public void notify(DelegateTask delegaTask) {
		//获取流程设置的流程变量
		Map<String, Object> map = delegaTask.getExecution().getVariables();
		String assignee = map.get("assignee").toString();
		delegaTask.setAssignee(assignee);
	}
	
	/** 使用方法如下:即在任务节点中配置事件
	 <userTask id="psApply" name="行业评审组审批">
		 <extensionElements>
			<activiti:taskListener event="create" class="activitiUtil.AssigneeLister"></activiti:taskListener>
		 </extensionElements>
	</userTask>
	**/
}
