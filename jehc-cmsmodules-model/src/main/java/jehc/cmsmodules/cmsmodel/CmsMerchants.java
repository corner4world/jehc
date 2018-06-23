package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_merchants 内容发布平台招商加盟 
* 2018-06-10 14:47:12  邓纯杰
*/
public class CmsMerchants extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_merchants_id;/**主键**/
	private String title;/**标题**/
	private String content;/**内容**/
	private String xt_userinfo_id;/**创建人**/
	private int status;/**状态0正常1关闭**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private String imgpath;/**大图片**/
	public void setCms_merchants_id(String cms_merchants_id){
		this.cms_merchants_id=cms_merchants_id;
	}
	public String getCms_merchants_id(){
		return cms_merchants_id;
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
	public void setImgpath(String imgpath){
		this.imgpath=imgpath;
	}
	public String getImgpath(){
		return imgpath;
	}
}
