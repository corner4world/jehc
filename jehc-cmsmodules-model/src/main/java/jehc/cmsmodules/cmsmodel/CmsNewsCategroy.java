package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_news_categroy 内容发布平台新闻分类 
* 2018-06-10 15:01:32  邓纯杰
*/
public class CmsNewsCategroy extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_news_categroy_id;/**主键**/
	private String name;/**分类名称**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private int status;/**状态0正常1关闭**/
	private String content;/**描述**/
	public void setCms_news_categroy_id(String cms_news_categroy_id){
		this.cms_news_categroy_id=cms_news_categroy_id;
	}
	public String getCms_news_categroy_id(){
		return cms_news_categroy_id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
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
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
}
