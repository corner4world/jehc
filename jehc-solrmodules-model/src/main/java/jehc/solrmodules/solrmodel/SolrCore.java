package jehc.solrmodules.solrmodel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_core 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
public class SolrCore extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_core_id;/**实例主键**/
	private String solr_core_name;/**实例名称**/
	private Date solr_core_ctime;/**创建时间**/
	private Date solr_core_uptime;/**修改时间**/
	private String xt_userinfo_id;/**操作人**/
	private String solr_url_id;/**URLID**/
	private String solr_url_url;/**URL**/
	private String solr_core_text;/**备注**/
	private String useonlynumbercode;/**使用唯一编号编码**/
	
	private String solr_document_name;/**文档名称**/
	private String solr_document_id;/**文档编号**/
	private String solr_schema_templet_id;/**schema_templet编号**/
	private String solr_data_config_id;/**solr data-config配置编号**/
	
	//////////////////////////以下三个在查询中调用//////////////////////////
	private List<SolrIndex> solr_index_list;//索引集合
	private List<SolrSort> solr_sort_list;//排序集合
	private List<SolrIndexAttribute> solr_index_attribute_list;//索引属性集合
	//////////////////////////以上三个在查询中调用//////////////////////////
	public List<SolrIndex> getSolr_index_list() {
		return solr_index_list;
	}
	public void setSolr_index_list(List<SolrIndex> solr_index_list) {
		this.solr_index_list = solr_index_list;
	}
	public void setSolr_core_id(String solr_core_id){
		this.solr_core_id=solr_core_id;
	}
	public String getSolr_core_id(){
		return solr_core_id;
	}
	public void setSolr_core_name(String solr_core_name){
		this.solr_core_name=solr_core_name;
	}
	public String getSolr_core_name(){
		return solr_core_name;
	}
	public Date getSolr_core_ctime() {
		return solr_core_ctime;
	}
	public void setSolr_core_ctime(Date solr_core_ctime) {
		this.solr_core_ctime = solr_core_ctime;
	}
	public Date getSolr_core_uptime() {
		return solr_core_uptime;
	}
	public void setSolr_core_uptime(Date solr_core_uptime) {
		this.solr_core_uptime = solr_core_uptime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setSolr_url_id(String solr_url_id){
		this.solr_url_id=solr_url_id;
	}
	public String getSolr_url_id(){
		return solr_url_id;
	}
	public String getSolr_url_url() {
		return solr_url_url;
	}
	public void setSolr_url_url(String solr_url_url) {
		this.solr_url_url = solr_url_url;
	}
	public String getSolr_core_text() {
		return solr_core_text;
	}
	public void setSolr_core_text(String solr_core_text) {
		this.solr_core_text = solr_core_text;
	}
	public String getSolr_document_name() {
		return solr_document_name;
	}
	public void setSolr_document_name(String solr_document_name) {
		this.solr_document_name = solr_document_name;
	}
	public String getSolr_document_id() {
		return solr_document_id;
	}
	public void setSolr_document_id(String solr_document_id) {
		this.solr_document_id = solr_document_id;
	}
	public String getSolr_schema_templet_id() {
		return solr_schema_templet_id;
	}
	public void setSolr_schema_templet_id(String solr_schema_templet_id) {
		this.solr_schema_templet_id = solr_schema_templet_id;
	}	public String getSolr_data_config_id() {
		return solr_data_config_id;
	}
	public void setSolr_data_config_id(String solr_data_config_id) {
		this.solr_data_config_id = solr_data_config_id;
	}
	public String getUseonlynumbercode() {
		return useonlynumbercode;
	}
	public void setUseonlynumbercode(String useonlynumbercode) {
		this.useonlynumbercode = useonlynumbercode;
	}
	public List<SolrSort> getSolr_sort_list() {
		return solr_sort_list;
	}
	public void setSolr_sort_list(List<SolrSort> solr_sort_list) {
		this.solr_sort_list = solr_sort_list;
	}
	public List<SolrIndexAttribute> getSolr_index_attribute_list() {
		return solr_index_attribute_list;
	}
	public void setSolr_index_attribute_list(
			List<SolrIndexAttribute> solr_index_attribute_list) {
		this.solr_index_attribute_list = solr_index_attribute_list;
	}

}
