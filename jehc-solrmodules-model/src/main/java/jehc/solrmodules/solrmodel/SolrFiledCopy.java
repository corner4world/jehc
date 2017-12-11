package jehc.solrmodules.solrmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_filed_copy Solr字段拷贝配置 
* 2016-11-14 22:20:39  邓纯杰
*/
public class SolrFiledCopy extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_filed_copy_id;/**主键编号**/
	private String solr_filed_copy_remark;/**备注**/
	private String solr_filed_copy_source_id;/**索引字段源**/
	private String solr_filed_copy_dest_id;/**目标字段索引编号**/
	private Date solr_filed_copy_ctime;/**创建时间**/
	private Date solr_filed_copy_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String solr_core_id;/**实例编号**/
	/////////////扩展字段////////////
	private String solr_filed_copy_source_name;
	private String solr_filed_copy_source_index;
	private String solr_filed_copy_dest_name;
	private String solr_filed_copy_dest_index;
	public String getSolr_filed_copy_source_name() {
		return solr_filed_copy_source_name;
	}
	public void setSolr_filed_copy_source_name(String solr_filed_copy_source_name) {
		this.solr_filed_copy_source_name = solr_filed_copy_source_name;
	}
	public String getSolr_filed_copy_source_index() {
		return solr_filed_copy_source_index;
	}
	public void setSolr_filed_copy_source_index(String solr_filed_copy_source_index) {
		this.solr_filed_copy_source_index = solr_filed_copy_source_index;
	}
	public String getSolr_filed_copy_dest_name() {
		return solr_filed_copy_dest_name;
	}
	public void setSolr_filed_copy_dest_name(String solr_filed_copy_dest_name) {
		this.solr_filed_copy_dest_name = solr_filed_copy_dest_name;
	}
	public String getSolr_filed_copy_dest_index() {
		return solr_filed_copy_dest_index;
	}
	public void setSolr_filed_copy_dest_index(String solr_filed_copy_dest_index) {
		this.solr_filed_copy_dest_index = solr_filed_copy_dest_index;
	}
	public void setSolr_filed_copy_id(String solr_filed_copy_id){
		this.solr_filed_copy_id=solr_filed_copy_id;
	}
	public String getSolr_filed_copy_id(){
		return solr_filed_copy_id;
	}
	public void setSolr_filed_copy_remark(String solr_filed_copy_remark){
		this.solr_filed_copy_remark=solr_filed_copy_remark;
	}
	public String getSolr_filed_copy_remark(){
		return solr_filed_copy_remark;
	}
	public void setSolr_filed_copy_source_id(String solr_filed_copy_source_id){
		this.solr_filed_copy_source_id=solr_filed_copy_source_id;
	}
	public String getSolr_filed_copy_source_id(){
		return solr_filed_copy_source_id;
	}
	public void setSolr_filed_copy_dest_id(String solr_filed_copy_dest_id){
		this.solr_filed_copy_dest_id=solr_filed_copy_dest_id;
	}
	public String getSolr_filed_copy_dest_id(){
		return solr_filed_copy_dest_id;
	}
	
	public Date getSolr_filed_copy_ctime() {
		return solr_filed_copy_ctime;
	}
	public void setSolr_filed_copy_ctime(Date solr_filed_copy_ctime) {
		this.solr_filed_copy_ctime = solr_filed_copy_ctime;
	}
	public Date getSolr_filed_copy_mtime() {
		return solr_filed_copy_mtime;
	}
	public void setSolr_filed_copy_mtime(Date solr_filed_copy_mtime) {
		this.solr_filed_copy_mtime = solr_filed_copy_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setSolr_core_id(String solr_core_id){
		this.solr_core_id=solr_core_id;
	}
	public String getSolr_core_id(){
		return solr_core_id;
	}
}
