package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_message 内容发布平台在线留言 
* 2018-06-10 14:51:17  邓纯杰
*/
public class CmsMessage extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_message_id;/**主键**/
	private String title;/**标题**/
	private String content;/**内容**/
	private String userName;/**姓名**/
	private String phone;/**手机号**/
	private Date ctime;/**创建时间**/
	public void setCms_message_id(String cms_message_id){
		this.cms_message_id=cms_message_id;
	}
	public String getCms_message_id(){
		return cms_message_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	public Date getCtime(){
		return ctime;
	}
}
