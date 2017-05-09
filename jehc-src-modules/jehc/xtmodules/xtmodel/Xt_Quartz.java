package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_quartz 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
public class Xt_Quartz extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;/**主键**/
	private String jobId;/**任务id**/
	private String jobName;/**任务名称**/
	private String jobGroup;/**任务分组**/
	private String jobStatus;/**任务状态 0禁用 1启用 2删除**/
	private String cronExpression;/**任务运行时间表达式**/
	private String desc;/**任务描述**/
	private String targetMethod;/**执行的类方法**/
	private String targetClass;/**执行的类**/
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setJobId(String jobId){
		this.jobId=jobId;
	}
	public String getJobId(){
		return jobId;
	}
	public void setJobName(String jobName){
		this.jobName=jobName;
	}
	public String getJobName(){
		return jobName;
	}
	public void setJobGroup(String jobGroup){
		this.jobGroup=jobGroup;
	}
	public String getJobGroup(){
		return jobGroup;
	}
	public void setJobStatus(String jobStatus){
		this.jobStatus=jobStatus;
	}
	public String getJobStatus(){
		return jobStatus;
	}
	public void setCronExpression(String cronExpression){
		this.cronExpression=cronExpression;
	}
	public String getCronExpression(){
		return cronExpression;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	public String getDesc(){
		return desc;
	}
	public void setTargetMethod(String targetMethod){
		this.targetMethod=targetMethod;
	}
	public String getTargetMethod(){
		return targetMethod;
	}
	public void setTargetClass(String targetClass){
		this.targetClass=targetClass;
	}
	public String getTargetClass(){
		return targetClass;
	}
}
