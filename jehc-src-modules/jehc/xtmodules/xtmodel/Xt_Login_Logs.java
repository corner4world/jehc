package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_login_logs 登录日志 
* 2015-05-24 08:17:40  邓纯杰
*/
public class Xt_Login_Logs extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_login_log_id;/**日志编号**/
	private String xt_userinfo_id;/**操作人ID外键**/
	private String xt_login_logIP;/**登录IP**/
	private String xt_login_logtime;/**登录时间**/
	private String xt_login_logContent;/**登陆内容日志**/
	private String xt_login_log_browser_type;/**浏览器类型**/
	private String xt_login_log_browser_name;/**浏览器名称**/
	private String xt_login_log_browser_version;/**浏览器版本**/
	private String xt_login_log_system;/**操作系统**/
	private int count;/**登陆次数**/
	public void setXt_login_log_id(String xt_login_log_id){
		this.xt_login_log_id=xt_login_log_id;
	}
	public String getXt_login_log_id(){
		return xt_login_log_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setXt_login_logIP(String xt_login_logIP){
		this.xt_login_logIP=xt_login_logIP;
	}
	public String getXt_login_logIP(){
		return xt_login_logIP;
	}
	public void setXt_login_logtime(String xt_login_logtime){
		this.xt_login_logtime=xt_login_logtime;
	}
	public String getXt_login_logtime(){
		return xt_login_logtime;
	}
	public void setXt_login_logContent(String xt_login_logContent){
		this.xt_login_logContent=xt_login_logContent;
	}
	public String getXt_login_logContent(){
		return xt_login_logContent;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getXt_login_log_browser_type() {
		return xt_login_log_browser_type;
	}
	public void setXt_login_log_browser_type(String xt_login_log_browser_type) {
		this.xt_login_log_browser_type = xt_login_log_browser_type;
	}
	public String getXt_login_log_browser_name() {
		return xt_login_log_browser_name;
	}
	public void setXt_login_log_browser_name(String xt_login_log_browser_name) {
		this.xt_login_log_browser_name = xt_login_log_browser_name;
	}
	public String getXt_login_log_browser_version() {
		return xt_login_log_browser_version;
	}
	public void setXt_login_log_browser_version(String xt_login_log_browser_version) {
		this.xt_login_log_browser_version = xt_login_log_browser_version;
	}
	public String getXt_login_log_system() {
		return xt_login_log_system;
	}
	public void setXt_login_log_system(String xt_login_log_system) {
		this.xt_login_log_system = xt_login_log_system;
	}
}
