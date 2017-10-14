package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_document solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
public class SolrDocument extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_document_id;/**文档编号**/
	private String solr_document_name;/**文档名称**/
	private String solr_core_id;/**SOLR实例编号**/
	private String solr_document_ctime;/**创建时间**/
	private String solr_document_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	public void setSolr_document_id(String solr_document_id){
		this.solr_document_id=solr_document_id;
	}
	public String getSolr_document_id(){
		return solr_document_id;
	}
	public void setSolr_document_name(String solr_document_name){
		this.solr_document_name=solr_document_name;
	}
	public String getSolr_document_name(){
		return solr_document_name;
	}
	public void setSolr_core_id(String solr_core_id){
		this.solr_core_id=solr_core_id;
	}
	public String getSolr_core_id(){
		return solr_core_id;
	}
	public void setSolr_document_ctime(String solr_document_ctime){
		this.solr_document_ctime=solr_document_ctime;
	}
	public String getSolr_document_ctime(){
		return solr_document_ctime;
	}
	public void setSolr_document_mtime(String solr_document_mtime){
		this.solr_document_mtime=solr_document_mtime;
	}
	public String getSolr_document_mtime(){
		return solr_document_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
}
