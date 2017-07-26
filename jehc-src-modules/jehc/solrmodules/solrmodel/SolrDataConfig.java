package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_data_config solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
public class SolrDataConfig extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_data_config_id;/**主键**/
	private String solr_data_config_title;/**标题**/
	private String solr_data_config_datasource;/**数据源连接配置**/
	private String solr_data_config_ctime;/**创建时间**/
	private String solr_data_config_mtime;/**修改时间**/
	private String xt_userinfo_id;/**操作人**/
	private String solr_data_config_status;/**状态0正常1删除**/
	private String solr_data_config_content;/**配置内容**/
	public void setSolr_data_config_id(String solr_data_config_id){
		this.solr_data_config_id=solr_data_config_id;
	}
	public String getSolr_data_config_id(){
		return solr_data_config_id;
	}
	public void setSolr_data_config_title(String solr_data_config_title){
		this.solr_data_config_title=solr_data_config_title;
	}
	public String getSolr_data_config_title(){
		return solr_data_config_title;
	}
	public void setSolr_data_config_datasource(String solr_data_config_datasource){
		this.solr_data_config_datasource=solr_data_config_datasource;
	}
	public String getSolr_data_config_datasource(){
		return solr_data_config_datasource;
	}
	public void setSolr_data_config_ctime(String solr_data_config_ctime){
		this.solr_data_config_ctime=solr_data_config_ctime;
	}
	public String getSolr_data_config_ctime(){
		return solr_data_config_ctime;
	}
	public String getSolr_data_config_mtime() {
		return solr_data_config_mtime;
	}
	public void setSolr_data_config_mtime(String solr_data_config_mtime) {
		this.solr_data_config_mtime = solr_data_config_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public void setSolr_data_config_status(String solr_data_config_status){
		this.solr_data_config_status=solr_data_config_status;
	}
	public String getSolr_data_config_status(){
		return solr_data_config_status;
	}
	public void setSolr_data_config_content(String solr_data_config_content){
		this.solr_data_config_content=solr_data_config_content;
	}
	public String getSolr_data_config_content(){
		return solr_data_config_content;
	}
}
