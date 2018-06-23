package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_contact 内容发布平台联系我们 
* 2018-06-10 14:42:49  邓纯杰
*/
public class CmsContact extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_contact_id;/****/
	private String content;/**默认**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private int status;/**状态0正常1禁用**/
	private String xt_userinfo_id;/**创建人**/
	private String imgpath;/**大图片**/
	public void setCms_contact_id(String cms_contact_id){
		this.cms_contact_id=cms_contact_id;
	}
	public String getCms_contact_id(){
		return cms_contact_id;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
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
	public void setStatus(int status){
		this.status=status;
	}
	public int getStatus(){
		return status;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setImgpath(String imgpath){
		this.imgpath=imgpath;
	}
	public String getImgpath(){
		return imgpath;
	}
}
