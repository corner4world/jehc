package jehc.solrmodules.solrmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_index_attribute 索引更多属性 
* 2016-07-10 22:19:49  邓纯杰
*/
public class SolrIndexAttribute extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_index_attribute_id;/**主键**/
	private String solr_index_attribute_facet;/**facet**/
	private float solr_index_attribute_boost;/**权重**/
	private String solr_index_attribute_spellcheck;/**拼写**/
	private int solr_index_attribute_status;/**状态0启用1禁用**/
	private int solr_index_attribute_issearch;/**是否参与查询0是1否**/
	private String solr_index_id;/**索引编号**/
	private Date solr_index_attribute_ctime;/**创建时间**/
	private Date solr_index_attribute_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建者**/
	private int sor_index_highlight;/**是否高亮0是1否**/
	private int sor_index_filter;/**否是作为过滤器查询0是1否**/
	private String solr_index_attribute_type;/**属性类型AND（并且） OR（或者） NOT（不包含） 3.TO（范围）**/
	
	private String solr_index_name;
	public String getSolr_index_name() {
		return solr_index_name;
	}
	public void setSolr_index_name(String solr_index_name) {
		this.solr_index_name = solr_index_name;
	}
	public void setSolr_index_attribute_id(String solr_index_attribute_id){
		this.solr_index_attribute_id=solr_index_attribute_id;
	}
	public String getSolr_index_attribute_id(){
		return solr_index_attribute_id;
	}
	public void setSolr_index_attribute_facet(String solr_index_attribute_facet){
		this.solr_index_attribute_facet=solr_index_attribute_facet;
	}
	public String getSolr_index_attribute_facet(){
		return solr_index_attribute_facet;
	}
	public void setSolr_index_attribute_boost(float solr_index_attribute_boost){
		this.solr_index_attribute_boost=solr_index_attribute_boost;
	}
	public float getSolr_index_attribute_boost(){
		return solr_index_attribute_boost;
	}
	public void setSolr_index_attribute_spellcheck(String solr_index_attribute_spellcheck){
		this.solr_index_attribute_spellcheck=solr_index_attribute_spellcheck;
	}
	public String getSolr_index_attribute_spellcheck(){
		return solr_index_attribute_spellcheck;
	}
	
	public void setSolr_index_id(String solr_index_id){
		this.solr_index_id=solr_index_id;
	}
	public String getSolr_index_id(){
		return solr_index_id;
	}
	
	public Date getSolr_index_attribute_ctime() {
		return solr_index_attribute_ctime;
	}
	public void setSolr_index_attribute_ctime(Date solr_index_attribute_ctime) {
		this.solr_index_attribute_ctime = solr_index_attribute_ctime;
	}
	public Date getSolr_index_attribute_mtime() {
		return solr_index_attribute_mtime;
	}
	public void setSolr_index_attribute_mtime(Date solr_index_attribute_mtime) {
		this.solr_index_attribute_mtime = solr_index_attribute_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	
	public int getSolr_index_attribute_status() {
		return solr_index_attribute_status;
	}
	public void setSolr_index_attribute_status(int solr_index_attribute_status) {
		this.solr_index_attribute_status = solr_index_attribute_status;
	}
	public int getSolr_index_attribute_issearch() {
		return solr_index_attribute_issearch;
	}
	public void setSolr_index_attribute_issearch(int solr_index_attribute_issearch) {
		this.solr_index_attribute_issearch = solr_index_attribute_issearch;
	}
	public int getSor_index_highlight() {
		return sor_index_highlight;
	}
	public void setSor_index_highlight(int sor_index_highlight) {
		this.sor_index_highlight = sor_index_highlight;
	}
	public int getSor_index_filter() {
		return sor_index_filter;
	}
	public void setSor_index_filter(int sor_index_filter) {
		this.sor_index_filter = sor_index_filter;
	}
	public String getSolr_index_attribute_type() {
		return solr_index_attribute_type;
	}
	public void setSolr_index_attribute_type(String solr_index_attribute_type) {
		this.solr_index_attribute_type = solr_index_attribute_type;
	}
}
