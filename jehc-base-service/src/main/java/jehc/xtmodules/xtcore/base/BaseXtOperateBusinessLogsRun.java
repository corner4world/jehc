package jehc.xtmodules.xtcore.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtOperateBusinessLogsDao;
import jehc.xtmodules.xtmodel.XtOperateBusinessLogs;
/**
 * 异日
 * @author Administrator
 *
 */
public class BaseXtOperateBusinessLogsRun extends Thread {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private XtOperateBusinessLogs xt_Operate_Business_Logs;
	public BaseXtOperateBusinessLogsRun(XtOperateBusinessLogs xt_Operate_Business_Logs){
		this.xt_Operate_Business_Logs = xt_Operate_Business_Logs;
	}
	
//	private static final long serialVersionUID = 1L;
//	private String xt_operate_business_logs_id;/**平台业务操作日志ID**/
//	private String xt_operate_business_logsTime;/**操作时间**/
//	private String xt_userinfo_id;/**操作人ID外键**/
//	private String xt_operate_business_logsModules;/**模块**/
//	private String xt_operate_business_logsMethod;/**执行方法**/
//	private String xt_operate_business_logsMethodPar;/**方法参数**/
//	private String xt_operate_business_logsResult;/**执行的结果**/
//	public BaseXtOperateBusinessLogsRun(String xt_operate_business_logsModules,String xt_operate_business_logsMethod,String xt_operate_business_logsMethodPar,String xt_operate_business_logsResult){
//		this.xt_operate_business_logsModules = xt_operate_business_logsModules;
//		this.xt_operate_business_logsMethod = xt_operate_business_logsMethod;
//		this.xt_operate_business_logsMethodPar = xt_operate_business_logsMethodPar;
//		this.xt_operate_business_logsResult = xt_operate_business_logsResult;
//	}
//	public void setXt_operate_business_logs_id(String xt_operate_business_logs_id){
//		this.xt_operate_business_logs_id=xt_operate_business_logs_id;
//	}
//	public String getXt_operate_business_logs_id(){
//		return xt_operate_business_logs_id;
//	}
//	public void setXt_operate_business_logsTime(String xt_operate_business_logsTime){
//		this.xt_operate_business_logsTime=xt_operate_business_logsTime;
//	}
//	public String getXt_operate_business_logsTime(){
//		return xt_operate_business_logsTime;
//	}
//	public void setXt_userinfo_id(String xt_userinfo_id){
//		this.xt_userinfo_id=xt_userinfo_id;
//	}
//	public String getXt_userinfo_id(){
//		return xt_userinfo_id;
//	}
//	public void setXt_operate_business_logsModules(String xt_operate_business_logsModules){
//		this.xt_operate_business_logsModules=xt_operate_business_logsModules;
//	}
//	public String getXt_operate_business_logsModules(){
//		return xt_operate_business_logsModules;
//	}
//	public void setXt_operate_business_logsMethod(String xt_operate_business_logsMethod){
//		this.xt_operate_business_logsMethod=xt_operate_business_logsMethod;
//	}
//	public String getXt_operate_business_logsMethod(){
//		return xt_operate_business_logsMethod;
//	}
//	public void setXt_operate_business_logsMethodPar(String xt_operate_business_logsMethodPar){
//		this.xt_operate_business_logsMethodPar=xt_operate_business_logsMethodPar;
//	}
//	public String getXt_operate_business_logsMethodPar(){
//		return xt_operate_business_logsMethodPar;
//	}
//	public void setXt_operate_business_logsResult(String xt_operate_business_logsResult){
//		this.xt_operate_business_logsResult=xt_operate_business_logsResult;
//	}
//	public String getXt_operate_business_logsResult(){
//		return xt_operate_business_logsResult;
//	}
	public void run(){
		try {
			putXtOperateBusinessLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 */
	public void putXtOperateBusinessLogs(){
		XtOperateBusinessLogs xt_Operate_Business_Logs = new XtOperateBusinessLogs();
		try {
			logger.info("----------开始记录日志--------------");
			XtOperateBusinessLogsDao xtOperateBusinessLogsDao = (XtOperateBusinessLogsDao)SpringUtil.getBean("xtOperateBusinessLogsDao");
//			xt_Operate_Business_Logs.setXt_operate_business_logsTime(CommonUtils.getSimpleDateFormat());
//			xt_Operate_Business_Logs.setXt_operate_business_logs_id(UUID.toUUID());
//			xt_Operate_Business_Logs.setXt_operate_business_logsModules(this.xt_operate_business_logsModules);
//			xt_Operate_Business_Logs.setXt_operate_business_logsMethod(this.xt_operate_business_logsMethod);
//			xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
//			xt_Operate_Business_Logs.setXt_operate_business_logsResult(this.xt_operate_business_logsResult);
//			xt_Operate_Business_Logs.setXt_operate_business_logsMethodPar(this.xt_operate_business_logsMethodPar);
			xtOperateBusinessLogsDao.addXtOperateBusinessLogs(this.xt_Operate_Business_Logs);
	        logger.info("----------结束记录日志--------------");
		} catch (Exception e) {
			logger.info("----------记录日志失败:"+xt_Operate_Business_Logs.toString()+"--------------");
		}
	}
}
