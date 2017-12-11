package jehc.solrmodules.solrmodel;
import java.io.Serializable;
import java.util.Date;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_schema_templet solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
public class SolrSchemaTemplet extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_schema_templet_id;/**模板编号**/
	private String solr_schema_templet_content;/**模板内容**/
	private Date solr_schema_templet_ctime;/**创建时间**/
	private Date solr_schema_templet_mtime;/**修改时间**/
	private String xt_userinfo_id;/**操作人**/
	private int solr_schema_templet_status;/**状态0正常1禁用**/
	private String solr_schema_templet_title;/**标题**/
	public void setSolr_schema_templet_id(String solr_schema_templet_id){
		this.solr_schema_templet_id=solr_schema_templet_id;
	}
	public String getSolr_schema_templet_id(){
		return solr_schema_templet_id;
	}
	public void setSolr_schema_templet_content(String solr_schema_templet_content){
		this.solr_schema_templet_content=solr_schema_templet_content;
	}
	public String getSolr_schema_templet_content(){
		return solr_schema_templet_content;
	}
	
	public Date getSolr_schema_templet_ctime() {
		return solr_schema_templet_ctime;
	}
	public void setSolr_schema_templet_ctime(Date solr_schema_templet_ctime) {
		this.solr_schema_templet_ctime = solr_schema_templet_ctime;
	}
	public Date getSolr_schema_templet_mtime() {
		return solr_schema_templet_mtime;
	}
	public void setSolr_schema_templet_mtime(Date solr_schema_templet_mtime) {
		this.solr_schema_templet_mtime = solr_schema_templet_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	
	public int getSolr_schema_templet_status() {
		return solr_schema_templet_status;
	}
	public void setSolr_schema_templet_status(int solr_schema_templet_status) {
		this.solr_schema_templet_status = solr_schema_templet_status;
	}
	public void setSolr_schema_templet_title(String solr_schema_templet_title){
		this.solr_schema_templet_title=solr_schema_templet_title;
	}
	public String getSolr_schema_templet_title(){
		return solr_schema_templet_title;
	}
}
