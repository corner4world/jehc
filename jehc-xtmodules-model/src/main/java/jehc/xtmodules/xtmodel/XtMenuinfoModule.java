package jehc.xtmodules.xtmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* xt_menuinfo_module 菜单资源模块 
* 2018-06-21 09:22:18  邓纯杰
*/
public class XtMenuinfoModule extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_menuinfo_module_id;/**主键**/
	private String title;/**模块名称**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private int status;/**状态0激活1关闭**/
	private String remark;/**描述**/
	private String keyid;/**唯一值**/
	public void setXt_menuinfo_module_id(String xt_menuinfo_module_id){
		this.xt_menuinfo_module_id=xt_menuinfo_module_id;
	}
	public String getXt_menuinfo_module_id(){
		return xt_menuinfo_module_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
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
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setKeyid(String keyid){
		this.keyid=keyid;
	}
	public String getKeyid(){
		return keyid;
	}
}
