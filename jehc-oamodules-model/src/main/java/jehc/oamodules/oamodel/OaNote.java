package jehc.oamodules.oamodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* oa_note 记事本 
* 2018-07-05 19:35:07  邓纯杰
*/
public class OaNote extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oa_note_id;/**主键**/
	private String oa_noteTitle;/**主题**/
	private String oa_note_classify_id;/**笔记本分类id**/
	private String content;/**内容**/
	private Date begtime;/**记事开始时间**/
	private Date endtime;/**记事结束时间**/
	private String xt_userinfo_id;/**用户序列号**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	
	private String oa_note_classify_name;//分类名称
	public void setOa_note_id(String oa_note_id){
		this.oa_note_id=oa_note_id;
	}
	public String getOa_note_id(){
		return oa_note_id;
	}
	public void setOa_noteTitle(String oa_noteTitle){
		this.oa_noteTitle=oa_noteTitle;
	}
	public String getOa_noteTitle(){
		return oa_noteTitle;
	}
	public void setOa_note_classify_id(String oa_note_classify_id){
		this.oa_note_classify_id=oa_note_classify_id;
	}
	public String getOa_note_classify_id(){
		return oa_note_classify_id;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setBegtime(Date begtime){
		this.begtime=begtime;
	}
	public Date getBegtime(){
		return begtime;
	}
	public void setEndtime(Date endtime){
		this.endtime=endtime;
	}
	public Date getEndtime(){
		return endtime;
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
	public String getOa_note_classify_name() {
		return oa_note_classify_name;
	}
	public void setOa_note_classify_name(String oa_note_classify_name) {
		this.oa_note_classify_name = oa_note_classify_name;
	}
	
}
