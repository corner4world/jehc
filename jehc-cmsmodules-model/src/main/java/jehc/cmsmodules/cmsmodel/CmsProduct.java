package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_product 内容发布平台 产品 
* 2018-06-10 15:05:11  邓纯杰
*/
public class CmsProduct extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_product_id;/**主键**/
	private String name;/**产品名称**/
	private String product_category_id;/**产品分类**/
	private String content;/**内容**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private int status;/**状态0正常1关闭**/
	private String xt_userinfo_id;/**创建人**/
	private String imgpath;/**图片**/
	public void setCms_product_id(String cms_product_id){
		this.cms_product_id=cms_product_id;
	}
	public String getCms_product_id(){
		return cms_product_id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setProduct_category_id(String product_category_id){
		this.product_category_id=product_category_id;
	}
	public String getProduct_category_id(){
		return product_category_id;
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
