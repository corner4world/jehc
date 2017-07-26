package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrEntity;
import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.solrmodules.solrmodel.SolrIndexSqlFiled;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
public interface SolrEntityService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrEntity> getSolrEntityListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_entity_id 
	* @return
	*/
	public SolrEntity getSolrEntityById(String solr_entity_id);
	/**
	* 添加
	* @param solr_entity 
	* @return
	*/
	public int addSolrEntity(SolrEntity solr_Entity,List<SolrIndexSql> solr_Index_SqlList,List<SolrIndexSqlFiled> solr_Index_Sql_FiledList);
	/**
	* 修改
	* @param solr_entity 
	* @return
	*/
	public int updateSolrEntity(SolrEntity solr_Entity,List<SolrIndexSql> solr_Index_SqlList,List<SolrIndexSqlFiled> solr_Index_Sql_FiledList);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrEntity(Map<String,Object> condition);
}
