package jehc.xtmodules.xtweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.quartz.QuartzJobFactory;
import jehc.xtmodules.xtcore.util.quartz.ScheduleJob;
import jehc.xtmodules.xtmodel.XtQuartz;
import jehc.xtmodules.xtservice.XtQuartzService;

/**
 * 定时任务配置
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/xtQuartzSetController")
public class XtQuartzSetController extends BaseAction{
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private XtQuartzService xtQuartzService;
	/**
	* 载入初始化页面
	* @param xt_constant 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtQuartz(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-quartz-set/xt-quartz-set-list");
	} 
	
	/**
	 * 加载所有任务
	 * @param model
	 * @return
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/getXtQuartzSetList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtQuartzList(Model model) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for(JobKey jobKey:jobKeys){
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for(Trigger trigger : triggers){
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDesc("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if(trigger instanceof CronTrigger){
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
					jobList.add(job);
				}
			}
		}
		return outItemsStr(jobList);
	}
	
	/**
	 * 添加一个任务
	 * @param job
	 * @return
	 * @throws IOException
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/addXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtQuartz(ScheduleJob job) throws IOException, SchedulerException {
		try{
			//一定要紧跟Validate之后写验证结果类
			String seconds = job.getCronExpression();
//			String cronExp = "0/" + seconds + " * * * * ?";
			String cronExp = seconds;
			job.setCronExpression(cronExp);
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
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
			return outAudStr(true);
		}catch(Exception e){
			return outAudStr(false);
		}
	}
	/**
	 * 暂停任务
	 * @param jobGroup
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/stopXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String stopXtQuartz(HttpServletRequest request) throws SchedulerException {
		try{
			String jobGroup = request.getParameter("jobGroup"); 
			String jobName = request.getParameter("jobName");
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.pauseJob(jobKey);
			return outAudStr(true);
		} catch (Exception e) {
			return outAudStr(false);
		}
	}

	/**
	 * 重启任务
	 * @param jobGroup
	 * @param jobName
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/reStartXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String reStartXtQuartz(String jobGroup,String jobName) throws SchedulerException {
		try{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.resumeJob(jobKey);
			return outAudStr(true);
		}catch(Exception e){
			return outAudStr(false);
		}
	}
	
	/**
	 * 立即执行一次
	 * @param jobGroup
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/startXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String startXtQuartz(String jobGroup,String jobName) throws SchedulerException {
	    try{
	    	Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		    scheduler.triggerJob(jobKey);
			return outAudStr(true);
		}catch(Exception e){
			return outAudStr(false);
		}
	}
	
	/**
	 * 删除
	 * @param jobGroup
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/delXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtQuartz(String jobGroup,String jobName) throws SchedulerException {
		try{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		    scheduler.deleteJob(jobKey);
			return outAudStr(true);
		}catch(Exception e){
			return outAudStr(false);
		}
	}
	
	/**
	 * 一秒执行一次
	 * @param jobGroup
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/oneSecondXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String oneSecondXtQuartz(String jobGroup,String jobName) throws SchedulerException {
		try{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
		    TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		    //表达式调度构建器
		    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
		    //按新的cronExpression表达式重新构建trigger
		    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		    //按新的trigger重新设置job执行
		    scheduler.rescheduleJob(triggerKey, trigger);
			return outAudStr(true);
		}catch(Exception e){
			return outAudStr(false);
		}
	}
	
	/**
	 * 五秒执行一次
	 * @param jobGroup
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 */
	@ResponseBody
	@RequestMapping(value = "/fiveSecondsXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String fiveSecondsXtQuartz(String jobGroup,String jobName) throws SchedulerException {
		try{
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
		    TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		    //表达式调度构建器
		    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		    //按新的cronExpression表达式重新构建trigger
		    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		    //按新的trigger重新设置job执行
		    scheduler.rescheduleJob(triggerKey, trigger);
			return outAudStr(true);
		}catch(Exception e){
			return outAudStr(false);
		}
	}
	
	/**
	 * 启动默认所有调度任务
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/defaultStartXtQuartzSet",method={RequestMethod.POST,RequestMethod.GET})
	public String defaultStartXtQuartzSet(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("jobStatus", "NORMAL");
		List<XtQuartz> xtQuartzList = xtQuartzService.getXtQuartzListAllByCondition(condition);
		for(int i = 0; i < xtQuartzList.size(); i++){
			XtQuartz xtQuartz = xtQuartzList.get(i);
			ScheduleJob job = new ScheduleJob(); 
			job.setCronExpression(xtQuartz.getCronExpression());
			job.setDesc(xtQuartz.getDesc());
			job.setJobGroup(xtQuartz.getJobGroup());
			job.setJobId(xtQuartz.getJobId());
			job.setJobName(xtQuartz.getJobName());
			job.setTargetClass(xtQuartz.getTargetClass());
			job.setTargetMethod(xtQuartz.getTargetMethod());
			try {
				initQuartz(job);
			} catch (IOException e) {
				return outAudStr(false);
			} catch (SchedulerException e) {
				return outAudStr(false);
			}
		}
		return outAudStr(true);
	}
	/**
	 * 封装
	 * @param ac
	 * @param job
	 * @throws IOException
	 * @throws SchedulerException
	 */
	public void initQuartz(ScheduleJob job) throws IOException, SchedulerException {
		try{
			//一定要紧跟Validate之后写验证结果类
			String seconds = job.getCronExpression();
			String cronExp = seconds;
			job.setCronExpression(cronExp);
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
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
//			System.out.println("------------------------"+sdf.format(new Date())+"--->初始化启动调度任务成功------------------------------------------");
		}catch(Exception e){
			e.printStackTrace();
//			System.out.println("------------------------"+sdf.format(new Date())+"--->初始化启动调度任务失败------------------------------------------");
		}
	}
}
