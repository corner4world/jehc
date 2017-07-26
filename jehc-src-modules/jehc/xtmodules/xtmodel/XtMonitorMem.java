package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_monitor_mem 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
public class XtMonitorMem extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_monitor_mem_id;/**内存ID**/
	private String xt_monitor_memTotal;/**总内存**/
	private String xt_monitor_memCurrUse;/**当前内存使用量**/
	private String xt_monitor_memCurrSy;/**当前内存剩余量**/
	private String xt_monitor_memJhTotal;/**交换区总量**/
	private String xt_monitor_memJhCurrUse;/**当前交换区使用量**/
	private String xt_monitor_memJhSy;/**当前交换区剩余量**/
	private String xt_monitor_memTime;/**取读时间**/
	public void setXt_monitor_mem_id(String xt_monitor_mem_id){
		this.xt_monitor_mem_id=xt_monitor_mem_id;
	}
	public String getXt_monitor_mem_id(){
		return xt_monitor_mem_id;
	}
	public void setXt_monitor_memTotal(String xt_monitor_memTotal){
		this.xt_monitor_memTotal=xt_monitor_memTotal;
	}
	public String getXt_monitor_memTotal(){
		return xt_monitor_memTotal;
	}
	public void setXt_monitor_memCurrUse(String xt_monitor_memCurrUse){
		this.xt_monitor_memCurrUse=xt_monitor_memCurrUse;
	}
	public String getXt_monitor_memCurrUse(){
		return xt_monitor_memCurrUse;
	}
	public void setXt_monitor_memCurrSy(String xt_monitor_memCurrSy){
		this.xt_monitor_memCurrSy=xt_monitor_memCurrSy;
	}
	public String getXt_monitor_memCurrSy(){
		return xt_monitor_memCurrSy;
	}
	public void setXt_monitor_memJhTotal(String xt_monitor_memJhTotal){
		this.xt_monitor_memJhTotal=xt_monitor_memJhTotal;
	}
	public String getXt_monitor_memJhTotal(){
		return xt_monitor_memJhTotal;
	}
	public void setXt_monitor_memJhCurrUse(String xt_monitor_memJhCurrUse){
		this.xt_monitor_memJhCurrUse=xt_monitor_memJhCurrUse;
	}
	public String getXt_monitor_memJhCurrUse(){
		return xt_monitor_memJhCurrUse;
	}
	public void setXt_monitor_memJhSy(String xt_monitor_memJhSy){
		this.xt_monitor_memJhSy=xt_monitor_memJhSy;
	}
	public String getXt_monitor_memJhSy(){
		return xt_monitor_memJhSy;
	}
	public void setXt_monitor_memTime(String xt_monitor_memTime){
		this.xt_monitor_memTime=xt_monitor_memTime;
	}
	public String getXt_monitor_memTime(){
		return xt_monitor_memTime;
	}
}
