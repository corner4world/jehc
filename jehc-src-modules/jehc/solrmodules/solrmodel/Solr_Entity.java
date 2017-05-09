package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_entity solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
public class Solr_Entity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_entity_id;/**编号主键**/
	private String solr_entity_name;/**实体名称**/
	private String solr_document_id;/**SOLR文档编号**/
	private String solr_entity_ctime;/**创建时间**/
	private String solr_entity_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String solr_entity_pid;/**实体父级编号**/
	private String solr_entity_text;/**实体备注**/
	private String solr_entity_transformer;/**格式转换器**/
	private String solr_entity_transformer_text;/**格式转换器**/
	public void setSolr_entity_id(String solr_entity_id){
		this.solr_entity_id=solr_entity_id;
	}
	public String getSolr_entity_id(){
		return solr_entity_id;
	}
	public void setSolr_entity_name(String solr_entity_name){
		this.solr_entity_name=solr_entity_name;
	}
	public String getSolr_entity_name(){
		return solr_entity_name;
	}
	public void setSolr_document_id(String solr_document_id){
		this.solr_document_id=solr_document_id;
	}
	public String getSolr_document_id(){
		return solr_document_id;
	}
	public void setSolr_entity_ctime(String solr_entity_ctime){
		this.solr_entity_ctime=solr_entity_ctime;
	}
	public String getSolr_entity_ctime(){
		return solr_entity_ctime;
	}
	public void setSolr_entity_mtime(String solr_entity_mtime){
		this.solr_entity_mtime=solr_entity_mtime;
	}
	public String getSolr_entity_mtime(){
		return solr_entity_mtime;
	}
	public String getSolr_entity_pid() {
		return solr_entity_pid;
	}
	public void setSolr_entity_pid(String solr_entity_pid) {
		this.solr_entity_pid = solr_entity_pid;
	}
	public String getSolr_entity_text() {
		return solr_entity_text;
	}
	public void setSolr_entity_text(String solr_entity_text) {
		this.solr_entity_text = solr_entity_text;
	}
	public String getXt_userinfo_id() {
		return xt_userinfo_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id) {
		this.xt_userinfo_id = xt_userinfo_id;
	}
	public String getSolr_entity_transformer() {
		return solr_entity_transformer;
	}
	public void setSolr_entity_transformer(String solr_entity_transformer) {
		this.solr_entity_transformer = solr_entity_transformer;
	}
	public String getSolr_entity_transformer_text() {
		return solr_entity_transformer_text;
	}
	public void setSolr_entity_transformer_text(String solr_entity_transformer_text) {
		this.solr_entity_transformer_text = solr_entity_transformer_text;
	}
}
