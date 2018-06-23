package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_seo 内容发布平台SEO配置 
* 2018-06-10 15:15:07  邓纯杰
*/
public class CmsSeo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_seo_id;/**主键**/
	private String domainname;/**域名**/
	private String title;/**站点标题**/
	private String keywords;/**关键字**/
	private String content;/**描述**/
	private String email;/**站点邮箱**/
	private String contact;/**联系人**/
	private String phone;/**联系电话**/
	private String icp;/**备案ICP**/
	private String address;/**备案地址**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private int status;/**状态0正常1关闭**/
	public void setCms_seo_id(String cms_seo_id){
		this.cms_seo_id=cms_seo_id;
	}
	public String getCms_seo_id(){
		return cms_seo_id;
	}
	public void setDomainname(String domainname){
		this.domainname=domainname;
	}
	public String getDomainname(){
		return domainname;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setKeywords(String keywords){
		this.keywords=keywords;
	}
	public String getKeywords(){
		return keywords;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setContact(String contact){
		this.contact=contact;
	}
	public String getContact(){
		return contact;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setIcp(String icp){
		this.icp=icp;
	}
	public String getIcp(){
		return icp;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	public Date getCtime(){
		return ctime;
	}
	public void setMtime(Date mtime){
		this.mtime=mtime;
	}
	public Date getMtime(){
		return mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public int getStatus(){
		return status;
	}
}
