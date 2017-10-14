package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_operate_logs 操作日志表; InnoDB free: 6144 kB 
* 2015-08-28 23:15:51  邓纯杰
*/
public class XtOperateLogs implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_operate_log_id;/**操作日志ID**/
	private String xt_operate_logBegTime;/**开始时间**/
	private String xt_userinfo_id;/**操作人ID外键**/
	private String xt_operate_logClasseName;/**执行的类名**/
	private String xt_operate_logMethod;/**执行的方法名**/
	private String xt_operate_logMethodPar;/**方法参数**/
	private String xt_operate_logResult;/**执行的结果**/
	private String xt_operate_logTotalTime;/**执行总时间**/
	private String xt_operate_logEndTime;/**结束时间**/
	private String xt_operate_logType;/**访问类型**/
	private String xt_operate_logUri;/**访问地址**/
	private int xt_operate_logMaxMemory;/**最大内存**/
	private int xt_operate_logTotalMemory;/**已分配内存**/
	private int xt_operate_logFreeMemory;/**已分配内存中的剩余空间**/
	private int xt_operate_logUseMemory;/**最大可用内存**/
	public void setXt_operate_log_id(String xt_operate_log_id){
		this.xt_operate_log_id=xt_operate_log_id;
	}
	public String getXt_operate_log_id(){
		return xt_operate_log_id;
	}
	public void setXt_operate_logBegTime(String xt_operate_logBegTime){
		this.xt_operate_logBegTime=xt_operate_logBegTime;
	}
	public String getXt_operate_logBegTime(){
		return xt_operate_logBegTime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_operate_logClasseName(String xt_operate_logClasseName){
		this.xt_operate_logClasseName=xt_operate_logClasseName;
	}
	public String getXt_operate_logClasseName(){
		return xt_operate_logClasseName;
	}
	public void setXt_operate_logMethod(String xt_operate_logMethod){
		this.xt_operate_logMethod=xt_operate_logMethod;
	}
	public String getXt_operate_logMethod(){
		return xt_operate_logMethod;
	}
	public void setXt_operate_logMethodPar(String xt_operate_logMethodPar){
		this.xt_operate_logMethodPar=xt_operate_logMethodPar;
	}
	public String getXt_operate_logMethodPar(){
		return xt_operate_logMethodPar;
	}
	public void setXt_operate_logResult(String xt_operate_logResult){
		this.xt_operate_logResult=xt_operate_logResult;
	}
	public String getXt_operate_logResult(){
		return xt_operate_logResult;
	}
	public void setXt_operate_logTotalTime(String xt_operate_logTotalTime){
		this.xt_operate_logTotalTime=xt_operate_logTotalTime;
	}
	public String getXt_operate_logTotalTime(){
		return xt_operate_logTotalTime;
	}
	public void setXt_operate_logEndTime(String xt_operate_logEndTime){
		this.xt_operate_logEndTime=xt_operate_logEndTime;
	}
	public String getXt_operate_logEndTime(){
		return xt_operate_logEndTime;
	}
	public void setXt_operate_logType(String xt_operate_logType){
		this.xt_operate_logType=xt_operate_logType;
	}
	public String getXt_operate_logType(){
		return xt_operate_logType;
	}
	public void setXt_operate_logUri(String xt_operate_logUri){
		this.xt_operate_logUri=xt_operate_logUri;
	}
	public String getXt_operate_logUri(){
		return xt_operate_logUri;
	}
	public void setXt_operate_logMaxMemory(int xt_operate_logMaxMemory){
		this.xt_operate_logMaxMemory=xt_operate_logMaxMemory;
	}
	public int getXt_operate_logMaxMemory(){
		return xt_operate_logMaxMemory;
	}
	public void setXt_operate_logTotalMemory(int xt_operate_logTotalMemory){
		this.xt_operate_logTotalMemory=xt_operate_logTotalMemory;
	}
	public int getXt_operate_logTotalMemory(){
		return xt_operate_logTotalMemory;
	}
	public void setXt_operate_logFreeMemory(int xt_operate_logFreeMemory){
		this.xt_operate_logFreeMemory=xt_operate_logFreeMemory;
	}
	public int getXt_operate_logFreeMemory(){
		return xt_operate_logFreeMemory;
	}
	public void setXt_operate_logUseMemory(int xt_operate_logUseMemory){
		this.xt_operate_logUseMemory=xt_operate_logUseMemory;
	}
	public int getXt_operate_logUseMemory(){
		return xt_operate_logUseMemory;
	}
}
