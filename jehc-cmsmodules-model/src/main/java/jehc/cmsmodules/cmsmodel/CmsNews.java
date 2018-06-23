package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_news 内容发布平台新闻 
* 2018-06-10 14:56:47  邓纯杰
*/
public class CmsNews extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_news_id;/**主键**/
	private String title;/**标题**/
	private String imgpath;/**图片**/
	private String content;/**内容**/
	private int status;/**状态0正常1关闭**/
	private String xt_userinfo_id;/**创建人**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private String news_category_id;/**新闻分类**/
	public void setCms_news_id(String cms_news_id){
		this.cms_news_id=cms_news_id;
	}
	public String getCms_news_id(){
		return cms_news_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setImgpath(String imgpath){
		this.imgpath=imgpath;
	}
	public String getImgpath(){
		return imgpath;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
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
	public void setNews_category_id(String news_category_id){
		this.news_category_id=news_category_id;
	}
	public String getNews_category_id(){
		return news_category_id;
	}
}
