package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_index_sql_filed SQL查询结果返回字段 
* 2015-12-23 09:44:02  邓纯杰
*/
public class Solr_Index_Sql_Filed extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_index_sql_filed_id;/**SQL查询结果编号**/
	private String solr_index_sql_filed_name;/**字段名称**/
	private String solr_index_sql_filed_zh;/**字段中文说明**/
	private String solr_entity_id;/**实体编号**/
	private String solr_index_sql_filed_ctime;/**创建时间**/
	private String solr_index_sql_filed_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	private String solr_index_filed_name;/**对应索引字段**/
	public void setSolr_index_sql_filed_id(String solr_index_sql_filed_id){
		this.solr_index_sql_filed_id=solr_index_sql_filed_id;
	}
	public String getSolr_index_sql_filed_id(){
		return solr_index_sql_filed_id;
	}
	public void setSolr_index_sql_filed_name(String solr_index_sql_filed_name){
		this.solr_index_sql_filed_name=solr_index_sql_filed_name;
	}
	public String getSolr_index_sql_filed_name(){
		return solr_index_sql_filed_name;
	}
	public void setSolr_index_sql_filed_zh(String solr_index_sql_filed_zh){
		this.solr_index_sql_filed_zh=solr_index_sql_filed_zh;
	}
	public String getSolr_index_sql_filed_zh(){
		return solr_index_sql_filed_zh;
	}
	public void setSolr_index_sql_filed_ctime(String solr_index_sql_filed_ctime){
		this.solr_index_sql_filed_ctime=solr_index_sql_filed_ctime;
	}
	public String getSolr_index_sql_filed_ctime(){
		return solr_index_sql_filed_ctime;
	}
	public void setSolr_index_sql_filed_mtime(String solr_index_sql_filed_mtime){
		this.solr_index_sql_filed_mtime=solr_index_sql_filed_mtime;
	}
	public String getSolr_index_sql_filed_mtime(){
		return solr_index_sql_filed_mtime;
	}
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public String getSolr_index_filed_name() {
		return solr_index_filed_name;
	}
	public void setSolr_index_filed_name(String solr_index_filed_name) {
		this.solr_index_filed_name = solr_index_filed_name;
	}
	public String getSolr_entity_id() {
		return solr_entity_id;
	}
	public void setSolr_entity_id(String solr_entity_id) {
		this.solr_entity_id = solr_entity_id;
	}
}
