package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_quartz_log 调度器日志 
* 2016-05-25 20:16:23  邓纯杰
*/
public class Xt_Quartz_Log extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_quartz_log_id;/**主键**/
	private String xt_quartz_log_name;/**名称**/
	private String xt_quartz_log_key;/**调度键**/
	private String xt_quartz_log_content;/**调度内容**/
	private String xt_quartz_log_ctime;/**开始时间**/
	private String xt_quartz_log_etime;/**结束时间**/
	private String xt_userinfo_id;/**操作人编号**/
	private String xt_quartz_log_flag;/**运行标识**/
	public void setXt_quartz_log_id(String xt_quartz_log_id){
		this.xt_quartz_log_id=xt_quartz_log_id;
	}
	public String getXt_quartz_log_id(){
		return xt_quartz_log_id;
	}
	public void setXt_quartz_log_name(String xt_quartz_log_name){
		this.xt_quartz_log_name=xt_quartz_log_name;
	}
	public String getXt_quartz_log_name(){
		return xt_quartz_log_name;
	}
	public void setXt_quartz_log_key(String xt_quartz_log_key){
		this.xt_quartz_log_key=xt_quartz_log_key;
	}
	public String getXt_quartz_log_key(){
		return xt_quartz_log_key;
	}
	public void setXt_quartz_log_content(String xt_quartz_log_content){
		this.xt_quartz_log_content=xt_quartz_log_content;
	}
	public String getXt_quartz_log_content(){
		return xt_quartz_log_content;
	}
	public void setXt_quartz_log_ctime(String xt_quartz_log_ctime){
		this.xt_quartz_log_ctime=xt_quartz_log_ctime;
	}
	public String getXt_quartz_log_ctime(){
		return xt_quartz_log_ctime;
	}
	public void setXt_quartz_log_etime(String xt_quartz_log_etime){
		this.xt_quartz_log_etime=xt_quartz_log_etime;
	}
	public String getXt_quartz_log_etime(){
		return xt_quartz_log_etime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_quartz_log_flag(String xt_quartz_log_flag){
		this.xt_quartz_log_flag=xt_quartz_log_flag;
	}
	public String getXt_quartz_log_flag(){
		return xt_quartz_log_flag;
	}
}
