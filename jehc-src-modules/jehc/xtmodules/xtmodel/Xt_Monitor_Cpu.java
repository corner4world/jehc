package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_monitor_cpu 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
public class Xt_Monitor_Cpu implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_monitor_cpu_id;/**CPUID编号**/
	private int xt_monitor_cpu_totalMHz;/**CPU的总量MHz**/
	private String xt_monitor_cpu_producer;/**CPU生产商**/
	private String xt_monitor_cpu_cache;/**CPU缓存数量**/
	private String xt_monitor_cpu_user_use_rate;/**CPU用户使用率**/
	private String xt_monitor_cpu_sys_use_rate;/**CPU系统使用率**/
	private String xt_monitor_cpu_wait_use_rate;/**CPU当前等待率**/
	private String xt_monitor_cpu_error_use_rate;/**CPU当前错误率**/
	private String xt_monitor_cpu_currently_idle;/**CPU当前空闲率**/
	private String xt_monitor_cpu_use_rate;/**CPU总的使用率**/
	private int xt_monitorNum;/**第几模块CPU信息**/
	private String xt_monitor_cpuTime;/**取读时间**/
	public void setXt_monitor_cpu_id(String xt_monitor_cpu_id){
		this.xt_monitor_cpu_id=xt_monitor_cpu_id;
	}
	public String getXt_monitor_cpu_id(){
		return xt_monitor_cpu_id;
	}
	public void setXt_monitor_cpu_totalMHz(int xt_monitor_cpu_totalMHz){
		this.xt_monitor_cpu_totalMHz=xt_monitor_cpu_totalMHz;
	}
	public int getXt_monitor_cpu_totalMHz(){
		return xt_monitor_cpu_totalMHz;
	}
	public void setXt_monitor_cpu_producer(String xt_monitor_cpu_producer){
		this.xt_monitor_cpu_producer=xt_monitor_cpu_producer;
	}
	public String getXt_monitor_cpu_producer(){
		return xt_monitor_cpu_producer;
	}
	public void setXt_monitor_cpu_cache(String xt_monitor_cpu_cache){
		this.xt_monitor_cpu_cache=xt_monitor_cpu_cache;
	}
	public String getXt_monitor_cpu_cache(){
		return xt_monitor_cpu_cache;
	}
	public void setXt_monitor_cpu_user_use_rate(String xt_monitor_cpu_user_use_rate){
		this.xt_monitor_cpu_user_use_rate=xt_monitor_cpu_user_use_rate;
	}
	public String getXt_monitor_cpu_user_use_rate(){
		return xt_monitor_cpu_user_use_rate;
	}
	public void setXt_monitor_cpu_sys_use_rate(String xt_monitor_cpu_sys_use_rate){
		this.xt_monitor_cpu_sys_use_rate=xt_monitor_cpu_sys_use_rate;
	}
	public String getXt_monitor_cpu_sys_use_rate(){
		return xt_monitor_cpu_sys_use_rate;
	}
	public void setXt_monitor_cpu_wait_use_rate(String xt_monitor_cpu_wait_use_rate){
		this.xt_monitor_cpu_wait_use_rate=xt_monitor_cpu_wait_use_rate;
	}
	public String getXt_monitor_cpu_wait_use_rate(){
		return xt_monitor_cpu_wait_use_rate;
	}
	public void setXt_monitor_cpu_error_use_rate(String xt_monitor_cpu_error_use_rate){
		this.xt_monitor_cpu_error_use_rate=xt_monitor_cpu_error_use_rate;
	}
	public String getXt_monitor_cpu_error_use_rate(){
		return xt_monitor_cpu_error_use_rate;
	}
	public void setXt_monitor_cpu_currently_idle(String xt_monitor_cpu_currently_idle){
		this.xt_monitor_cpu_currently_idle=xt_monitor_cpu_currently_idle;
	}
	public String getXt_monitor_cpu_currently_idle(){
		return xt_monitor_cpu_currently_idle;
	}
	public void setXt_monitor_cpu_use_rate(String xt_monitor_cpu_use_rate){
		this.xt_monitor_cpu_use_rate=xt_monitor_cpu_use_rate;
	}
	public String getXt_monitor_cpu_use_rate(){
		return xt_monitor_cpu_use_rate;
	}
	public void setXt_monitorNum(int xt_monitorNum){
		this.xt_monitorNum=xt_monitorNum;
	}
	public int getXt_monitorNum(){
		return xt_monitorNum;
	}
	public void setXt_monitor_cpuTime(String xt_monitor_cpuTime){
		this.xt_monitor_cpuTime=xt_monitor_cpuTime;
	}
	public String getXt_monitor_cpuTime(){
		return xt_monitor_cpuTime;
	}
}
