package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrEntity;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
public interface SolrEntityDao{
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
	public int addSolrEntity(SolrEntity solr_Entity);
	/**
	* 修改
	* @param solr_entity 
	* @return
	*/
	public int updateSolrEntity(SolrEntity solr_Entity);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrEntity(Map<String,Object> condition);
}
