package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrIndex;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
public interface SolrIndexDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrIndex> getSolrIndexListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_index_id 
	* @return
	*/
	public SolrIndex getSolrIndexById(String solr_index_id);
	/**
	* 添加
	* @param solr_index 
	* @return
	*/
	public int addSolrIndex(SolrIndex solr_Index);
	/**
	* 修改
	* @param solr_index 
	* @return
	*/
	public int updateSolrIndex(SolrIndex solr_Index);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndex(Map<String,Object> condition);
}
