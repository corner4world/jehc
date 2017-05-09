package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_index_sql 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
public class Solr_Index_Sql extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_index_sql_id;/**主键**/
	private String solr_index_sql_query;/**SQL执行语句**/
	private String solr_index_sql_type;/**类型:query,deltaImportQuery,deltaQuery 数据字典读取**/
	private String solr_entity_id;/**实体编号**/
	private String solr_index_ctime;/**创建时间**/
	private String solr_index_mtime;/**修改时间**/
	private String xt_userinfo_id;/**创建人**/
	public void setSolr_index_sql_id(String solr_index_sql_id){
		this.solr_index_sql_id=solr_index_sql_id;
	}
	public String getSolr_index_sql_id(){
		return solr_index_sql_id;
	}
	public void setSolr_index_sql_query(String solr_index_sql_query){
		this.solr_index_sql_query=solr_index_sql_query;
	}
	public String getSolr_index_sql_query(){
		return solr_index_sql_query;
	}
	public void setSolr_index_sql_type(String solr_index_sql_type){
		this.solr_index_sql_type=solr_index_sql_type;
	}
	public String getSolr_index_sql_type(){
		return solr_index_sql_type;
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
	public void setXt_userinfo_id(String xt_userinfo_id){
		this.xt_userinfo_id=xt_userinfo_id;
	}
	public String getXt_userinfo_id(){
		return xt_userinfo_id;
	}
	public String getSolr_entity_id() {
		return solr_entity_id;
	}
	public void setSolr_entity_id(String solr_entity_id) {
		this.solr_entity_id = solr_entity_id;
	}
}
