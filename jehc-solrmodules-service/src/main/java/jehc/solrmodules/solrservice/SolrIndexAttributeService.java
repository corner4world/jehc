package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrIndexAttribute;

/**
* 索引更多属性 
* 2016-07-10 22:19:49  邓纯杰
*/
public interface SolrIndexAttributeService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrIndexAttribute> getSolrIndexAttributeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_index_attribute_id 
	* @return
	*/
	public SolrIndexAttribute getSolrIndexAttributeById(String solr_index_attribute_id);
	/**
	* 添加
	* @param solr_index_attribute 
	* @return
	*/
	public int addSolrIndexAttribute(SolrIndexAttribute solr_Index_Attribute);
	/**
	* 修改
	* @param solr_index_attribute 
	* @return
	*/
	public int updateSolrIndexAttribute(SolrIndexAttribute solr_Index_Attribute);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexAttribute(Map<String,Object> condition);
	
	/**
	 * 根据实例编号查找其下面属性字段
	 * @param condition
	 * @return
	 */
	public List<SolrIndexAttribute> getSolrIndexAttributeList(Map<String,Object> condition);
}
