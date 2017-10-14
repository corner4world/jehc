package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_sms 短信配置表 
* 2015-06-04 13:35:07  邓纯杰
*/
public class XtSms implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_sms_id;/**ID**/
	private String xt_smsName;/**用户名**/
	private String xt_smsPWD;/**短信接口密码**/
	private String xt_smsURL;/**URL地址**/
	private String xt_smsCtime;/**建创时间**/
	private String xt_smsCompany;/**公司**/
	private String xt_smsCompanTel;/**电话**/
	private String xt_smsValue;/**短信平台**/
	private String xt_smsCompanyAddress;/**公司地址**/
	private String xt_smsCompanyContacts;/**联系人**/
	private int xt_smsType;/**短信协议类型0http1其他**/
	private int xt_smsDelete;/**删除状态0正常1删除**/
	private int xt_smsState;/**状态0正常1启用**/
	public void setXt_sms_id(String xt_sms_id){
		this.xt_sms_id=xt_sms_id;
	}
	public String getXt_sms_id(){
		return xt_sms_id;
	}
	public void setXt_smsName(String xt_smsName){
		this.xt_smsName=xt_smsName;
	}
	public String getXt_smsName(){
		return xt_smsName;
	}
	public void setXt_smsPWD(String xt_smsPWD){
		this.xt_smsPWD=xt_smsPWD;
	}
	public String getXt_smsPWD(){
		return xt_smsPWD;
	}
	public void setXt_smsURL(String xt_smsURL){
		this.xt_smsURL=xt_smsURL;
	}
	public String getXt_smsURL(){
		return xt_smsURL;
	}
	public void setXt_smsCtime(String xt_smsCtime){
		this.xt_smsCtime=xt_smsCtime;
	}
	public String getXt_smsCtime(){
		return xt_smsCtime;
	}
	public void setXt_smsCompany(String xt_smsCompany){
		this.xt_smsCompany=xt_smsCompany;
	}
	public String getXt_smsCompany(){
		return xt_smsCompany;
	}
	public void setXt_smsCompanTel(String xt_smsCompanTel){
		this.xt_smsCompanTel=xt_smsCompanTel;
	}
	public String getXt_smsCompanTel(){
		return xt_smsCompanTel;
	}
	public void setXt_smsValue(String xt_smsValue){
		this.xt_smsValue=xt_smsValue;
	}
	public String getXt_smsValue(){
		return xt_smsValue;
	}
	public void setXt_smsCompanyAddress(String xt_smsCompanyAddress){
		this.xt_smsCompanyAddress=xt_smsCompanyAddress;
	}
	public String getXt_smsCompanyAddress(){
		return xt_smsCompanyAddress;
	}
	public void setXt_smsCompanyContacts(String xt_smsCompanyContacts){
		this.xt_smsCompanyContacts=xt_smsCompanyContacts;
	}
	public String getXt_smsCompanyContacts(){
		return xt_smsCompanyContacts;
	}
	public void setXt_smsType(int xt_smsType){
		this.xt_smsType=xt_smsType;
	}
	public int getXt_smsType(){
		return xt_smsType;
	}
	public void setXt_smsDelete(int xt_smsDelete){
		this.xt_smsDelete=xt_smsDelete;
	}
	public int getXt_smsDelete(){
		return xt_smsDelete;
	}
	public void setXt_smsState(int xt_smsState){
		this.xt_smsState=xt_smsState;
	}
	public int getXt_smsState(){
		return xt_smsState;
	}
}
