package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* oa_note_classify 记事本分类 
* 2018-07-04 21:06:37  邓纯杰
*/
public class OaNoteClassify extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_note_classify_id;/**主键**/
	private String oa_note_classify_name;/**分类名称**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private int isdeleted;/**是否删除0正常1删除**/
	private String xt_userinfo_id;/**创建人**/
	public void setOa_note_classify_id(String oa_note_classify_id){
		this.oa_note_classify_id=oa_note_classify_id;
	}
	public String getOa_note_classify_id(){
		return oa_note_classify_id;
	}
	public void setOa_note_classify_name(String oa_note_classify_name){
		this.oa_note_classify_name=oa_note_classify_name;
	}
	public String getOa_note_classify_name(){
		return oa_note_classify_name;
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
	public void setIsdeleted(int isdeleted){
		this.isdeleted=isdeleted;
	}
	public int getIsdeleted(){
		return isdeleted;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
