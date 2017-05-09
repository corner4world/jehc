package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Entity;
import jehc.solrmodules.solrmodel.Solr_Index_Sql;
import jehc.solrmodules.solrmodel.Solr_Index_Sql_Filed;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
public interface Solr_EntityService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Entity> getSolrEntityListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_entity_id 
	* @return
	*/
	public Solr_Entity getSolrEntityById(String solr_entity_id);
	/**
	* 添加
	* @param solr_entity 
	* @return
	*/
	public int addSolrEntity(Solr_Entity solr_Entity,List<Solr_Index_Sql> solr_Index_SqlList,List<Solr_Index_Sql_Filed> solr_Index_Sql_FiledList);
	/**
	* 修改
	* @param solr_entity 
	* @return
	*/
	public int updateSolrEntity(Solr_Entity solr_Entity,List<Solr_Index_Sql> solr_Index_SqlList,List<Solr_Index_Sql_Filed> solr_Index_Sql_FiledList);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrEntity(Map<String,Object> condition);
}
