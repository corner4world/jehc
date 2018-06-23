package jehc.cmsmodules.cmsmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* cms_recruitment 内容发布平台招贤纳士 
* 2018-06-10 15:11:58  邓纯杰
*/
public class CmsRecruitment extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cms_recruitment_id;/**主键**/
	private String post;/**职位**/
	private int sex;/**性别0男1女**/
	private String age_from;/**年龄范围**/
	private String treatment;/**待遇**/
	private String language;/**语言要求**/
	private String workplace;/**工作地点**/
	private String vld;/**有效期至**/
	private String education;/**学历要求**/
	private String content;/**详细描述**/
	private String numbersH;/**招聘人数**/
	private int status;/**状态0正常1关闭**/
	private Date ctime;/**创建时间**/
	private Date mtime;/**最后修改时间**/
	private String xt_userinfo_id;/**创建人**/
	public void setCms_recruitment_id(String cms_recruitment_id){
		this.cms_recruitment_id=cms_recruitment_id;
	}
	public String getCms_recruitment_id(){
		return cms_recruitment_id;
	}
	public void setPost(String post){
		this.post=post;
	}
	public String getPost(){
		return post;
	}
	public void setSex(int sex){
		this.sex=sex;
	}
	public int getSex(){
		return sex;
	}
	public void setAge_from(String age_from){
		this.age_from=age_from;
	}
	public String getAge_from(){
		return age_from;
	}
	public void setTreatment(String treatment){
		this.treatment=treatment;
	}
	public String getTreatment(){
		return treatment;
	}
	public void setLanguage(String language){
		this.language=language;
	}
	public String getLanguage(){
		return language;
	}
	public void setWorkplace(String workplace){
		this.workplace=workplace;
	}
	public String getWorkplace(){
		return workplace;
	}
	public void setVld(String vld){
		this.vld=vld;
	}
	public String getVld(){
		return vld;
	}
	public void setEducation(String education){
		this.education=education;
	}
	public String getEducation(){
		return education;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setNumbersH(String numbersH){
		this.numbersH=numbersH;
	}
	public String getNumbersH(){
		return numbersH;
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
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
