package jehc.crmmodules.crmmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* crm_contact 客户联系人 
* 2018-06-27 17:26:15  邓纯杰
*/
public class CrmContact extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String contactId;/**联系人id，主键**/
	private String contactName;/**联系人姓名**/
	private String contactTel;/**联系人电话**/
	private String interests;/**爱好**/
	private String postId;/**岗位，注：数据字典读取**/
	private int incumbency;/**是否在职0在职1已离职**/
	private String customerId;/**客户编号**/
	
	private String postValue;/**岗位别名**/
	public void setContactId(String contactId){
		this.contactId=contactId;
	}
	public String getContactId(){
		return contactId;
	}
	public void setContactName(String contactName){
		this.contactName=contactName;
	}
	public String getContactName(){
		return contactName;
	}
	public void setContactTel(String contactTel){
		this.contactTel=contactTel;
	}
	public String getContactTel(){
		return contactTel;
	}
	public void setInterests(String interests){
		this.interests=interests;
	}
	public String getInterests(){
		return interests;
	}
	public void setPostId(String postId){
		this.postId=postId;
	}
	public String getPostId(){
		return postId;
	}
	public void setIncumbency(int incumbency){
		this.incumbency=incumbency;
	}
	public int getIncumbency(){
		return incumbency;
	}
	public void setCustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getCustomerId(){
		return customerId;
	}
	public String getPostValue() {
		return postValue;
	}
	public void setPostValue(String postValue) {
		this.postValue = postValue;
	}
	
}
