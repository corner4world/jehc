package jehc.solrmodules.solrmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* solr_sort solr排序 
* 2016-07-08 23:49:58  邓纯杰
*/
public class Solr_Sort extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String solr_sort_id;/**主键**/
	private String solr_sort_name;/**排序名称**/
	private String solr_sort_ctime;/**创建时间**/
	private String solr_sort_mtime;/**修改时间**/
	private String solr_sort_code;/**排序code**/
	private int solr_sort_useboost;/**选用权重**/
	private String solr_index_id;/**索引编号**/
	private String xt_userinfo_id;/**操作人**/
	private String solr_index_name;//索引名称
	public String getSolr_index_name() {
		return solr_index_name;
	}
	public void setSolr_index_name(String solr_index_name) {
		this.solr_index_name = solr_index_name;
	}
	public void setSolr_sort_id(String solr_sort_id){
		this.solr_sort_id=solr_sort_id;
	}
	public String getSolr_sort_id(){
		return solr_sort_id;
	}
	public void setSolr_sort_name(String solr_sort_name){
		this.solr_sort_name=solr_sort_name;
	}
	public String getSolr_sort_name(){
		return solr_sort_name;
	}
	public void setSolr_sort_ctime(String solr_sort_ctime){
		this.solr_sort_ctime=solr_sort_ctime;
	}
	public String getSolr_sort_ctime(){
		return solr_sort_ctime;
	}
	public void setSolr_sort_mtime(String solr_sort_mtime){
		this.solr_sort_mtime=solr_sort_mtime;
	}
	public String getSolr_sort_mtime(){
		return solr_sort_mtime;
	}
	public void setSolr_sort_code(String solr_sort_code){
		this.solr_sort_code=solr_sort_code;
	}
	public String getSolr_sort_code(){
		return solr_sort_code;
	}
	public void setSolr_sort_useboost(int solr_sort_useboost){
		this.solr_sort_useboost=solr_sort_useboost;
	}
	public int getSolr_sort_useboost(){
		return solr_sort_useboost;
	}
	public void setSolr_index_id(String solr_index_id){
		this.solr_index_id=solr_index_id;
	}
	public String getSolr_index_id(){
		return solr_index_id;
	}
	public String getXt_userinfo_id() {
		return xt_userinfo_id;
	}
	public void setXt_userinfo_id(String xt_userinfo_id) {
		this.xt_userinfo_id = xt_userinfo_id;
	}
}
