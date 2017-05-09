package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_monitor 监控主表 
* 2016-03-04 12:49:59  邓纯杰
*/
public class Xt_Monitor extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_monitor_id;/**编号**/
	private String xt_monitor_userName;/**用户名**/
	private String xt_monitor_accountName;/**用户的账户名称**/
	private String xt_monitor_comName;/**计算机名**/
	private String xt_monitor_localName;/**本地主机名**/
	private int xt_monitor_jvm_totalMem;/**JVM可以使用的总内存**/
	private int xt_monitor_jvm_Mem;/**JVM可以使用的剩余内存**/
	private String xt_monitor_operate_sysName;/**操作系统的名称**/
	private String xt_monitor_operate_org;/**操作系统的构架**/
	private int xt_monitor_jvm_cup_count;/**JVM可以使用的处理器个数**/
	private String xt_monitorIP;/**本地IP地址**/
	private String xt_monitor_environment;/**Java的运行环境版本**/
	private String xt_monitorPath;/**Java的安装路径**/
	private String xt_monitorTime;/**监控时间**/
	public void setXt_monitor_id(String xt_monitor_id){
		this.xt_monitor_id=xt_monitor_id;
	}
	public String getXt_monitor_id(){
		return xt_monitor_id;
	}
	public void setXt_monitor_userName(String xt_monitor_userName){
		this.xt_monitor_userName=xt_monitor_userName;
	}
	public String getXt_monitor_userName(){
		return xt_monitor_userName;
	}
	public void setXt_monitor_accountName(String xt_monitor_accountName){
		this.xt_monitor_accountName=xt_monitor_accountName;
	}
	public String getXt_monitor_accountName(){
		return xt_monitor_accountName;
	}
	public void setXt_monitor_comName(String xt_monitor_comName){
		this.xt_monitor_comName=xt_monitor_comName;
	}
	public String getXt_monitor_comName(){
		return xt_monitor_comName;
	}
	public void setXt_monitor_localName(String xt_monitor_localName){
		this.xt_monitor_localName=xt_monitor_localName;
	}
	public String getXt_monitor_localName(){
		return xt_monitor_localName;
	}
	public void setXt_monitor_jvm_totalMem(int xt_monitor_jvm_totalMem){
		this.xt_monitor_jvm_totalMem=xt_monitor_jvm_totalMem;
	}
	public int getXt_monitor_jvm_totalMem(){
		return xt_monitor_jvm_totalMem;
	}
	public void setXt_monitor_jvm_Mem(int xt_monitor_jvm_Mem){
		this.xt_monitor_jvm_Mem=xt_monitor_jvm_Mem;
	}
	public int getXt_monitor_jvm_Mem(){
		return xt_monitor_jvm_Mem;
	}
	public void setXt_monitor_operate_sysName(String xt_monitor_operate_sysName){
		this.xt_monitor_operate_sysName=xt_monitor_operate_sysName;
	}
	public String getXt_monitor_operate_sysName(){
		return xt_monitor_operate_sysName;
	}
	public void setXt_monitor_operate_org(String xt_monitor_operate_org){
		this.xt_monitor_operate_org=xt_monitor_operate_org;
	}
	public String getXt_monitor_operate_org(){
		return xt_monitor_operate_org;
	}
	public void setXt_monitor_jvm_cup_count(int xt_monitor_jvm_cup_count){
		this.xt_monitor_jvm_cup_count=xt_monitor_jvm_cup_count;
	}
	public int getXt_monitor_jvm_cup_count(){
		return xt_monitor_jvm_cup_count;
	}
	public void setXt_monitorIP(String xt_monitorIP){
		this.xt_monitorIP=xt_monitorIP;
	}
	public String getXt_monitorIP(){
		return xt_monitorIP;
	}
	public void setXt_monitor_environment(String xt_monitor_environment){
		this.xt_monitor_environment=xt_monitor_environment;
	}
	public String getXt_monitor_environment(){
		return xt_monitor_environment;
	}
	public void setXt_monitorPath(String xt_monitorPath){
		this.xt_monitorPath=xt_monitorPath;
	}
	public String getXt_monitorPath(){
		return xt_monitorPath;
	}
	public String getXt_monitorTime() {
		return xt_monitorTime;
	}
	public void setXt_monitorTime(String xt_monitorTime) {
		this.xt_monitorTime = xt_monitorTime;
	}
}
