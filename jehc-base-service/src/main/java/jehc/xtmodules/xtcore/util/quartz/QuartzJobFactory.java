package jehc.xtmodules.xtcore.util.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.quartz.task.Solr_dataimportTask;
import jehc.xtmodules.xtcore.util.quartz.task.Solr_fullimportTask;
import jehc.xtmodules.xtcore.util.quartz.task.Xt_Data_Authority_Task;
import jehc.xtmodules.xtcore.util.quartz.task.Xt_DbinfoBack_Task;
import jehc.xtmodules.xtcore.util.quartz.task.Xt_Ehcache_Task;
import jehc.xtmodules.xtcore.util.quartz.task.Xt_Monitor_Task;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtQuartzLogDao;
import jehc.xtmodules.xtmodel.XtQuartzLog;

public class QuartzJobFactory extends BaseService implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		XtQuartzLogDao xtQuartzLogService = (XtQuartzLogDao)SpringUtil.getBean("xtQuartzLogDao");
		XtQuartzLog xt_Quartz_Log = new XtQuartzLog();
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		String name = scheduleJob.getJobName();
		String id = scheduleJob.getJobId();
		String des = scheduleJob.getDesc();
		xt_Quartz_Log.setXt_quartz_log_ctime(getSimpleDateFormat());
		xt_Quartz_Log.setXt_quartz_log_id(UUID.toUUID());
		xt_Quartz_Log.setXt_quartz_log_name(name);
		xt_Quartz_Log.setXt_quartz_log_content(des);
		xt_Quartz_Log.setXt_quartz_log_key(id);
		xt_Quartz_Log.setXt_quartz_log_flag(scheduleJob.getJobStatus());
		String targetMethod = scheduleJob.getTargetMethod();
		String targetClass = scheduleJob.getTargetClass();
		if(null != targetMethod && null != targetClass && targetMethod.equals("service") && targetClass.equals("xtCore.util.quartz.task.Xt_DbinfoBack_Task")){
			Xt_DbinfoBack_Task xt_DbinfoBack_Task = new Xt_DbinfoBack_Task();
			xt_DbinfoBack_Task.service();
		}else if(null != targetMethod && null != targetClass && targetMethod.equals("service") && targetClass.equals("xtCore.util.quartz.task.Xt_Ehcache_Task")){
			Xt_Ehcache_Task xt_Ehcache_Task = new Xt_Ehcache_Task();
			xt_Ehcache_Task.service();
		}else if(null != targetMethod && null != targetClass && targetMethod.equals("service") && targetClass.equals("xtCore.util.quartz.task.Xt_Monitor_Task")){
			Xt_Monitor_Task xt_Monitor_Task = new Xt_Monitor_Task();
			xt_Monitor_Task.service();
		}else if(null != targetMethod && null != targetClass && targetMethod.equals("service") && targetClass.equals("xtCore.util.quartz.task.Solr_dataimportTask")){
			Solr_dataimportTask solr_dataimportTask = new Solr_dataimportTask();
			solr_dataimportTask.service();
		}else if(null != targetMethod && null != targetClass && targetMethod.equals("service") && targetClass.equals("xtCore.util.quartz.task.Solr_fullimportTask")){
			Solr_fullimportTask solr_fullimportTask = new Solr_fullimportTask();
			solr_fullimportTask.service();
		}else if(null != targetMethod && null != targetClass && targetMethod.equals("service") && targetClass.equals("xtCore.util.quartz.task.Xt_Data_Authority_Task")){
			Xt_Data_Authority_Task xt_Data_Authority_Task = new Xt_Data_Authority_Task();
			xt_Data_Authority_Task.service();
		}
		xt_Quartz_Log.setXt_quartz_log_etime(getSimpleDateFormat());
		xtQuartzLogService.addXtQuartzLog(xt_Quartz_Log);
	}
}