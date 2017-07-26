package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrSchemaTemplet;

/**
* solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
public interface SolrSchemaTempletService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrSchemaTemplet> getSolrSchemaTempletListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_schema_templet_id 
	* @return
	*/
	public SolrSchemaTemplet getSolrSchemaTempletById(String solr_schema_templet_id);
	/**
	* 添加
	* @param solr_schema_templet 
	* @return
	*/
	public int addSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet);
	/**
	* 修改
	* @param solr_schema_templet 
	* @return
	*/
	public int updateSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrSchemaTemplet(Map<String,Object> condition);
}
