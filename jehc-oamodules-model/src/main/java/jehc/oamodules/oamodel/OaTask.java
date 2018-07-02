package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* oa_task 任务表 
* 2018-07-02 21:09:04  邓纯杰
*/
public class OaTask extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_task_id;/**任务ID自动自增列主键**/
	private String oa_taskTitle;/**任务标题**/
	private Date oa_taskBegTime;/**开始时间**/
	private Date oa_taskBegApTime;/**开始分钟**/
	private Date oa_taskEndTime;/**结束时间**/
	private Date oa_taskEndApTime;/**结束分钟**/
	private int oa_taskIsAllDay;/**是否全天1是2否**/
	private int oa_taskType;/**任务类型1个人2公开**/
	private int oa_taskRemindType;/**事件发生前多少提醒0表示不提醒**/
	private int oa_taskTightness;/**1一般2重要3紧急**/
	private String oa_taskContent;/**任务描述**/
	private String xt_userinfo_id;/**用户序列号**/
	public void setOa_task_id(String oa_task_id){
		this.oa_task_id=oa_task_id;
	}
	public String getOa_task_id(){
		return oa_task_id;
	}
	public void setOa_taskTitle(String oa_taskTitle){
		this.oa_taskTitle=oa_taskTitle;
	}
	public String getOa_taskTitle(){
		return oa_taskTitle;
	}
	public void setOa_taskBegTime(Date oa_taskBegTime){
		this.oa_taskBegTime=oa_taskBegTime;
	}
	public Date getOa_taskBegTime(){
		return oa_taskBegTime;
	}
	public void setOa_taskBegApTime(Date oa_taskBegApTime){
		this.oa_taskBegApTime=oa_taskBegApTime;
	}
	public Date getOa_taskBegApTime(){
		return oa_taskBegApTime;
	}
	public void setOa_taskEndTime(Date oa_taskEndTime){
		this.oa_taskEndTime=oa_taskEndTime;
	}
	public Date getOa_taskEndTime(){
		return oa_taskEndTime;
	}
	public void setOa_taskEndApTime(Date oa_taskEndApTime){
		this.oa_taskEndApTime=oa_taskEndApTime;
	}
	public Date getOa_taskEndApTime(){
		return oa_taskEndApTime;
	}
	public void setOa_taskIsAllDay(int oa_taskIsAllDay){
		this.oa_taskIsAllDay=oa_taskIsAllDay;
	}
	public int getOa_taskIsAllDay(){
		return oa_taskIsAllDay;
	}
	public void setOa_taskType(int oa_taskType){
		this.oa_taskType=oa_taskType;
	}
	public int getOa_taskType(){
		return oa_taskType;
	}
	public void setOa_taskRemindType(int oa_taskRemindType){
		this.oa_taskRemindType=oa_taskRemindType;
	}
	public int getOa_taskRemindType(){
		return oa_taskRemindType;
	}
	public void setOa_taskTightness(int oa_taskTightness){
		this.oa_taskTightness=oa_taskTightness;
	}
	public int getOa_taskTightness(){
		return oa_taskTightness;
	}
	public void setOa_taskContent(String oa_taskContent){
		this.oa_taskContent=oa_taskContent;
	}
	public String getOa_taskContent(){
		return oa_taskContent;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
