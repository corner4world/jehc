package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_url solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
public class Solr_Url extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_url_id;/**主键**/
	private String solr_url_url;/**访问URL**/
	private String solr_url_ctime;/**创建时间**/
	private String solr_url_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String solr_url_content;/**备注**/
	public void setSolr_url_id(String solr_url_id){
		this.solr_url_id=solr_url_id;
	}
	public String getSolr_url_id(){
		return solr_url_id;
	}
	public void setSolr_url_url(String solr_url_url){
		this.solr_url_url=solr_url_url;
	}
	public String getSolr_url_url(){
		return solr_url_url;
	}
	public void setSolr_url_ctime(String solr_url_ctime){
		this.solr_url_ctime=solr_url_ctime;
	}
	public String getSolr_url_ctime(){
		return solr_url_ctime;
	}
	public void setSolr_url_mtime(String solr_url_mtime){
		this.solr_url_mtime=solr_url_mtime;
	}
	public String getSolr_url_mtime(){
		return solr_url_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public String getSolr_url_content() {
		return solr_url_content;
	}
	public void setSolr_url_content(String solr_url_content) {
		this.solr_url_content = solr_url_content;
	}
}
