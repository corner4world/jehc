package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Index;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
public interface Solr_IndexService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Index> getSolrIndexListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_index_id 
	* @return
	*/
	public Solr_Index getSolrIndexById(String solr_index_id);
	/**
	* 添加
	* @param solr_index 
	* @return
	*/
	public int addSolrIndex(Solr_Index solr_Index);
	/**
	* 修改
	* @param solr_index 
	* @return
	*/
	public int updateSolrIndex(Solr_Index solr_Index);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndex(Map<String,Object> condition);
}
