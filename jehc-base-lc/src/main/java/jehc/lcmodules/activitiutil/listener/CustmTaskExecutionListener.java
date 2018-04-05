package jehc.lcmodules.activitiutil.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustmTaskExecutionListener implements ExecutionListener, TaskListener {
	
	private static final long serialVersionUID = 7960387497099642910L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	// ExecutionListener类的实现
	public void notify(DelegateExecution execution) throws Exception {
		String eventName = execution.getEventName();
		// start
		if ("start".equals(eventName)) {
			logger.debug("start=========");
		} else if ("end".equals(eventName)) {
			logger.debug("end=========");
		} else if ("take".equals(eventName)) {
			logger.debug("take=========");
		}
	}

	// 实现TaskListener中的方法
	public void notify(DelegateTask delegateTask) {
		String eventName = delegateTask.getEventName();
		if ("create".endsWith(eventName)) {
			logger.debug("create=========");
		} else if ("assignment".endsWith(eventName)) {
			logger.debug("assignment========");
		} else if ("complete".endsWith(eventName)) {
			logger.debug("complete===========");
		} else if ("delete".endsWith(eventName)) {
			logger.debug("delete=============");
		}
	}
}
