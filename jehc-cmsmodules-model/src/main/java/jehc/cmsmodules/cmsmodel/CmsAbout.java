package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_about 内容发布平台关于我们 
* 2018-06-10 17:56:24  邓纯杰
*/
public class CmsAbout extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_about_id;/**主键**/
	private String title;/**标题**/
	private String content;/**内容**/
	private String imgpath;/**照片**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改日期**/
	private String xt_userinfo_id;/**创建人**/
	private int status;/**状态0正常1关闭**/
	public void setCms_about_id(String cms_about_id){
		this.cms_about_id=cms_about_id;
	}
	public String getCms_about_id(){
		return cms_about_id;
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
	public void setImgpath(String imgpath){
		this.imgpath=imgpath;
	}
	public String getImgpath(){
		return imgpath;
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
