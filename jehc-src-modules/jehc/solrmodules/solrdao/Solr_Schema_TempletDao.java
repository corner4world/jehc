package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Schema_Templet;

/**
* solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
public interface Solr_Schema_TempletDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Schema_Templet> getSolrSchemaTempletListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_schema_templet_id 
	* @return
	*/
	public Solr_Schema_Templet getSolrSchemaTempletById(String solr_schema_templet_id);
	/**
	* 添加
	* @param solr_schema_templet 
	* @return
	*/
	public int addSolrSchemaTemplet(Solr_Schema_Templet solr_Schema_Templet);
	/**
	* 修改
	* @param solr_schema_templet 
	* @return
	*/
	public int updateSolrSchemaTemplet(Solr_Schema_Templet solr_Schema_Templet);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrSchemaTemplet(Map<String,Object> condition);
}
