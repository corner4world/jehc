package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_case 内容发布平台案例 
* 2018-06-10 18:01:22  邓纯
*/
public class CmsCase extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_case_id;/****/
	private String title;/**标题**/
	private String remark;/**简介**/
	private String content;/**默认**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private int status;/**状态0正常1禁用**/
	private String xt_userinfo_id;/**创建人**/
	private String imgpath;/**大图片**/
	public void setCms_case_id(String cms_case_id){
		this.cms_case_id=cms_case_id;
	}
	public String getCms_case_id(){
		return cms_case_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
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
