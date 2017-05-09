package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_index 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
public class Solr_Index extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_index_id;/**主键**/
	private String solr_index_name;/**索引字段**/
	private String solr_index_remark;/**备注**/
	private String solr_index_type;/**索引类型|通过数据字典**/
	private String solr_index_indexed;/**是否被索引:true|false**/
	private String xt_userinfo_id;/**创建人**/
	private String solr_index_stored;/**是否存储内容:true|false**/
	private String solr_index_multiValued;/**是否为多值类型:true|false**/
	private String solr_core_id;/**实例编号**/
	private String solr_index_ctime;/**创建时间**/
	private String solr_index_mtime;/**修改时间**/
	public void setSolr_index_id(String solr_index_id){
		this.solr_index_id=solr_index_id;
	}
	public String getSolr_index_id(){
		return solr_index_id;
	}
	public void setSolr_index_name(String solr_index_name){
		this.solr_index_name=solr_index_name;
	}
	public String getSolr_index_name(){
		return solr_index_name;
	}
	public void setSolr_index_remark(String solr_index_remark){
		this.solr_index_remark=solr_index_remark;
	}
	public String getSolr_index_remark(){
		return solr_index_remark;
	}
	public void setSolr_index_type(String solr_index_type){
		this.solr_index_type=solr_index_type;
	}
	public String getSolr_index_type(){
		return solr_index_type;
	}
	public void setSolr_index_indexed(String solr_index_indexed){
		this.solr_index_indexed=solr_index_indexed;
	}
	public String getSolr_index_indexed(){
		return solr_index_indexed;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setSolr_index_stored(String solr_index_stored){
		this.solr_index_stored=solr_index_stored;
	}
	public String getSolr_index_stored(){
		return solr_index_stored;
	}
	public void setSolr_index_multiValued(String solr_index_multiValued){
		this.solr_index_multiValued=solr_index_multiValued;
	}
	public String getSolr_index_multiValued(){
		return solr_index_multiValued;
	}
	public String getSolr_core_id() {
		return solr_core_id;
	}
	public void setSolr_core_id(String solr_core_id) {
		this.solr_core_id = solr_core_id;
	}
	public void setSolr_index_ctime(String solr_index_ctime){
		this.solr_index_ctime=solr_index_ctime;
	}
	public String getSolr_index_ctime(){
		return solr_index_ctime;
	}
	public void setSolr_index_mtime(String solr_index_mtime){
		this.solr_index_mtime=solr_index_mtime;
	}
	public String getSolr_index_mtime(){
		return solr_index_mtime;
	}
}
