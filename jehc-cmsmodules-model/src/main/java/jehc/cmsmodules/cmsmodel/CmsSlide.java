package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_slide 内容发布平台幻灯片 
* 2018-06-27 12:44:03  邓纯杰
*/
public class CmsSlide extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_slide_id;/**主键**/
	private String title;/**标题**/
	private int status;/**状态0正常1关闭**/
	private String imgpath;/**图片**/
	private String href;/**链接地址**/
	private String content;/**备注**/
	private String xt_userinfo_id;/**创建人**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	public void setCms_slide_id(String cms_slide_id){
		this.cms_slide_id=cms_slide_id;
	}
	public String getCms_slide_id(){
		return cms_slide_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public int getStatus(){
		return status;
	}
	public void setImgpath(String imgpath){
		this.imgpath=imgpath;
	}
	public String getImgpath(){
		return imgpath;
	}
	public void setHref(String href){
		this.href=href;
	}
	public String getHref(){
		return href;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
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
}
