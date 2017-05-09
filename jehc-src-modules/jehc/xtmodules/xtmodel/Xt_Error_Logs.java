package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_error_logs 异常日志表 
* 2015-05-24 08:33:40  邓纯杰
*/
public class Xt_Error_Logs extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_error_log_id;/**异常ID**/
	private String xt_error_logContent;/**异常日志内容**/
	private int xt_error_logType;/**异常日志级别**/
	private String xt_error_logTime;/**异常日志出错时间**/
	private String xt_userinfo_id;/**操作人ID外键**/
	public void setXt_error_log_id(String xt_error_log_id){
		this.xt_error_log_id=xt_error_log_id;
	}
	public String getXt_error_log_id(){
		return xt_error_log_id;
	}
	public void setXt_error_logContent(String xt_error_logContent){
		this.xt_error_logContent=xt_error_logContent;
	}
	public String getXt_error_logContent(){
		return xt_error_logContent;
	}
	public void setXt_error_logType(int xt_error_logType){
		this.xt_error_logType=xt_error_logType;
	}
	public int getXt_error_logType(){
		return xt_error_logType;
	}
	public void setXt_error_logTime(String xt_error_logTime){
		this.xt_error_logTime=xt_error_logTime;
	}
	public String getXt_error_logTime(){
		return xt_error_logTime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
