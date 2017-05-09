package jehc.xtmodules.xtcore.util.quartz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 初始化加载调度任务
 * @author 邓纯杰
 *
 */
public class QuartzInit extends Thread{
	Logger log = LoggerFactory.getLogger(this.getClass());
	//private SchedulerFactoryBean schedulerFactoryBean;
	private Scheduler scheduler; 
	private String jobId;/**任务id*/
	private String jobName;/**任务名称*/
	private String jobGroup;/**任务分组*/
	private String jobStatus;/**任务状态 0禁用 1启用 2删除*/
	private String cronExpression;/**任务运行时间表达式*/
	private String desc;/**任务描述*/
	private String targetMethod;/**执行的类方法*/
	private String targetClass;/**执行的类**/
	public QuartzInit(Scheduler scheduler,String jobId,String jobName,String jobGroup,String cronExpression,String desc,String targetMethod,String targetClass){
//	public QuartzInit(SchedulerFactoryBean schedulerFactoryBean,String jobId,String jobName,String jobGroup,String cronExpression,String desc,String targetMethod,String targetClass){
//		this.schedulerFactoryBean = schedulerFactoryBean;
		this.scheduler = scheduler;
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExpression=cronExpression;
		this.desc = desc;
		this.targetClass=targetClass;
		this.targetMethod = targetMethod;
	}
	public void run() {
		ScheduleJob job = new ScheduleJob(); 
		job.setCronExpression(getCronExpression());
		job.setDesc(getDesc());
		job.setJobGroup(getJobGroup());
		job.setJobId(getJobId());
		job.setJobName(getJobName());
		job.setTargetClass(getTargetClass());
		job.setTargetMethod(getTargetMethod());
		try {
			initQuartz(scheduler,job);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
	public void initQuartz(Scheduler scheduler,ScheduleJob job) throws IOException, SchedulerException {
//	public void initQuartz(SchedulerFactoryBean schedulerFactoryBean,ScheduleJob job) throws IOException, SchedulerException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
//	        Scheduler scheduler = schedulerFactoryBean.getScheduler();
			//一定要紧跟Validate之后写验证结果类
			String seconds = job.getCronExpression();
			String cronExp = seconds;
			job.setCronExpression(cronExp);
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			//不存在，创建一个
			if(null == trigger){
				JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				//表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				//按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			}else{
				//Trigger已存在，那么更新相应的定时设置
				//表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				//按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				//按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		}catch(Exception e){
			log.info("------------------------"+sdf.format(new Date())+"--->初始化启动调度任务失败------------------------------------------");
		}
	}
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
//	public SchedulerFactoryBean getSchedulerFactoryBean() {
//		return schedulerFactoryBean;
//	}
//	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
//		this.schedulerFactoryBean = schedulerFactoryBean;
//	}
	public Scheduler getScheduler() {
		return scheduler;
	}
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
