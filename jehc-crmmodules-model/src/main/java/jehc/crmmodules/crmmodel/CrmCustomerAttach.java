package jehc.crmmodules.crmmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* crm_customer_attach 客户附件 
* 2018-06-27 15:17:45  邓纯杰
*/
public class CrmCustomerAttach extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String customer_attach_id;/**附件id  主键**/
	private String custom_id;/**客户id**/
	private String xt_attachement_id;/**附件编号**/
	private String ctime;/**上传时间**/
	private String xt_userinfo_id;/**操作人编号**/
	private int isDeleted;/**是否删除 0 已删除  1未删除**/
	public void setCustomer_attach_id(String customer_attach_id){
		this.customer_attach_id=customer_attach_id;
	}
	public String getCustomer_attach_id(){
		return customer_attach_id;
	}
	public void setCustom_id(String custom_id){
		this.custom_id=custom_id;
	}
	public String getCustom_id(){
		return custom_id;
	}
	public void setXt_attachement_id(String xt_attachement_id){
		this.xt_attachement_id=xt_attachement_id;
	}
	public String getXt_attachement_id(){
		return xt_attachement_id;
	}
	public void setCtime(String ctime){
		this.ctime=ctime;
	}
	public String getCtime(){
		return ctime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setIsDeleted(int isDeleted){
		this.isDeleted=isDeleted;
	}
	public int getIsDeleted(){
		return isDeleted;
	}
}
