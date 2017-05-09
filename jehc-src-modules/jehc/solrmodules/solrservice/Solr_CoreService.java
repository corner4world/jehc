package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.solrmodules.solrmodel.Solr_Document;
import jehc.solrmodules.solrmodel.Solr_Index;

/**
* 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
public interface Solr_CoreService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Core> getSolrCoreListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_core_id 
	* @return
	*/
	public Solr_Core getSolrCoreById(String solr_core_id);
	/**
	* 添加
	* @param solr_core 
	* @return
	*/
	public int addSolrCore(Solr_Core solr_Core,Solr_Document solr_Document,List<Solr_Index> solr_IndexList);
	/**
	* 修改
	* @param solr_core 
	* @return
	*/
	public int updateSolrCore(Solr_Core solr_Core,Solr_Document solr_Document,List<Solr_Index> solr_IndexList);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrCore(Map<String,Object> condition);
}
