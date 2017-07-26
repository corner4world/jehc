package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrFiledCopy;

/**
* Solr字段拷贝配置 
* 2016-11-14 22:20:39  邓纯杰
*/
public interface SolrFiledCopyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrFiledCopy> getSolrFiledCopyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_filed_copy_id 
	* @return
	*/
	public SolrFiledCopy getSolrFiledCopyById(String solr_filed_copy_id);
	/**
	* 添加
	* @param solr_filed_copy 
	* @return
	*/
	public int addSolrFiledCopy(SolrFiledCopy solr_Filed_Copy);
	/**
	* 修改
	* @param solr_filed_copy 
	* @return
	*/
	public int updateSolrFiledCopy(SolrFiledCopy solr_Filed_Copy);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrFiledCopy(Map<String,Object> condition);
	/**
	* 批量添加
	* @param solr_filed_copyList 
	* @return
	*/
	public int addBatchSolrFiledCopy(List<SolrFiledCopy> solr_Filed_CopyList);
	/**
	* 批量修改
	* @param solr_filed_copyList 
	* @return
	*/
	public int updateBatchSolrFiledCopy(List<SolrFiledCopy> solr_Filed_CopyList);
}
