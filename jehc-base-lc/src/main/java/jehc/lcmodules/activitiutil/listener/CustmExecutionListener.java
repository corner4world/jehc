package jehc.lcmodules.activitiutil.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustmExecutionListener implements ExecutionListener{
	private String jEhcVersion;
	public String getjEhcVersion() {
		return jEhcVersion;
	}
	public void setjEhcVersion(String jEhcVersion) {
		this.jEhcVersion = jEhcVersion;
	}
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public void notify(DelegateExecution execution) throws Exception {
		String eventName = execution.getEventName();//获取事件节点名称
		
		if("start".equals(eventName)){
			logger.debug("-------------------进入到开始节点------------");
		}else if("userTask".equals(eventName)){
			logger.debug("-------------------进入到任务节点------------");
		}
		
	}

}
