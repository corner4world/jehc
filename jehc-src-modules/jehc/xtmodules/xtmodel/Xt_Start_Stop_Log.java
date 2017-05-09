package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_start_stop_log 服务器启动与关闭日志; InnoDB free: 9216 kB; InnoDB free: 9216 kB 
* 2015-10-31 21:28:14  邓纯杰
*/
public class Xt_Start_Stop_Log extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_start_stop_log_id;/**主键**/
	private String xt_start_stop_log_starttime;/**服务器启动时间**/
	private String xt_start_stop_log_stoptime;/**服务器停止时间**/
	private String xt_start_stop_log_iserror;/**是否出错0正常1错误**/
	private String xt_start_stop_log_content;/**加载内容**/
	public void setXt_start_stop_log_id(String xt_start_stop_log_id){
		this.xt_start_stop_log_id=xt_start_stop_log_id;
	}
	public String getXt_start_stop_log_id(){
		return xt_start_stop_log_id;
	}
	public void setXt_start_stop_log_starttime(String xt_start_stop_log_starttime){
		this.xt_start_stop_log_starttime=xt_start_stop_log_starttime;
	}
	public String getXt_start_stop_log_starttime(){
		return xt_start_stop_log_starttime;
	}
	public void setXt_start_stop_log_stoptime(String xt_start_stop_log_stoptime){
		this.xt_start_stop_log_stoptime=xt_start_stop_log_stoptime;
	}
	public String getXt_start_stop_log_stoptime(){
		return xt_start_stop_log_stoptime;
	}
	public void setXt_start_stop_log_iserror(String xt_start_stop_log_iserror){
		this.xt_start_stop_log_iserror=xt_start_stop_log_iserror;
	}
	public String getXt_start_stop_log_iserror(){
		return xt_start_stop_log_iserror;
	}
	public void setXt_start_stop_log_content(String xt_start_stop_log_content){
		this.xt_start_stop_log_content=xt_start_stop_log_content;
	}
	public String getXt_start_stop_log_content(){
		return xt_start_stop_log_content;
	}
}
