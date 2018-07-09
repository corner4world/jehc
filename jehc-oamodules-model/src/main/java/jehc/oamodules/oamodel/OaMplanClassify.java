package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* oa_mplan_classify 个人计划分类 
* 2018-07-09 20:18:35  邓纯杰
*/
public class OaMplanClassify extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_mplan_classify_id;/**我的计划分类ID自动自增列**/
	private String oa_mplan_classify_name;/**个人计划分类名称**/
	private String xt_userinfo_id;/**创建人**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private int isdelete;/**是否删除0正常1删除**/
	public void setOa_mplan_classify_id(String oa_mplan_classify_id){
		this.oa_mplan_classify_id=oa_mplan_classify_id;
	}
	public String getOa_mplan_classify_id(){
		return oa_mplan_classify_id;
	}
	public void setOa_mplan_classify_name(String oa_mplan_classify_name){
		this.oa_mplan_classify_name=oa_mplan_classify_name;
	}
	public String getOa_mplan_classify_name(){
		return oa_mplan_classify_name;
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
	public void setIsdelete(int isdelete){
		this.isdelete=isdelete;
	}
	public int getIsdelete(){
		return isdelete;
	}
}
