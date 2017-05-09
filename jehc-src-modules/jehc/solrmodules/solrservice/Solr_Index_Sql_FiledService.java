package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Index_Sql_Filed;

/**
* SQL查询结果返回字段 
* 2015-12-23 09:44:02  邓纯杰
*/
public interface Solr_Index_Sql_FiledService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Index_Sql_Filed> getSolrIndexSqlFiledListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_index_sql_filed_id 
	* @return
	*/
	public Solr_Index_Sql_Filed getSolrIndexSqlFiledById(String solr_index_sql_filed_id);
	/**
	* 添加
	* @param solr_index_sql_filed 
	* @return
	*/
	public int addSolrIndexSqlFiled(Solr_Index_Sql_Filed solr_Index_Sql_Filed);
	/**
	* 修改
	* @param solr_index_sql_filed 
	* @return
	*/
	public int updateSolrIndexSqlFiled(Solr_Index_Sql_Filed solr_Index_Sql_Filed);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexSqlFiled(Map<String,Object> condition);
}
