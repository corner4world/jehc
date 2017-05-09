package jehc.xtmodules.xtcore.base;

import org.springframework.beans.factory.annotation.Autowired;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.Log4jUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.Xt_Operate_Business_Logs;
import jehc.xtmodules.xtservice.Xt_Operate_Business_LogsService;

/**
 * service父类支持
 * @author邓纯杰
 *
 */
public class BaseService extends Log4jUtil{
	@Autowired
	private Xt_Operate_Business_LogsService xt_Operate_Business_LogsService;
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 */
	public void aBLogs(String classname,String method,String message){
		Xt_Operate_Business_Logs xt_Operate_Business_Logs = new Xt_Operate_Business_Logs();
		xt_Operate_Business_Logs.setXt_operate_business_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_business_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_business_logsResult(message);
		/**
        xt_Operate_Business_LogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
		//需要走异步操作 为了不影响性能
		new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).run();
	}
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 * @param parm
	 */
	public void aBLogs(String classname,String method,String message,String parm){
		Xt_Operate_Business_Logs xt_Operate_Business_Logs = new Xt_Operate_Business_Logs();
		xt_Operate_Business_Logs.setXt_operate_business_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_business_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_business_logsResult(message);
		xt_Operate_Business_Logs.setXt_operate_business_logsMethodPar(parm);
		/**
        xt_Operate_Business_LogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
		//需要走异步操作 为了不影响性能
		new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).run();
	}
}
