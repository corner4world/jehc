package jehc.xtmodules.xtcore.util.quartz;


/**
 * 定时任务实体
 * @author 邓纯杰
 *
 */
public class ScheduleJob {
	private String jobId;/**任务id*/
	private String jobName;/**任务名称*/
	private String jobGroup;/**任务分组*/
	private String jobStatus;/**任务状态 0禁用 1启用 2删除*/
	private String cronExpression;/**任务运行时间表达式*/
	private String desc;/**任务描述*/
	private String targetMethod;/**执行的类方法*/
	private String targetClass;/**执行的类**/
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
}