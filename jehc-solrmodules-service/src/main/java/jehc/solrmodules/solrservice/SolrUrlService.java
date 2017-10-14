package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrUrl;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
public interface SolrUrlService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrUrl> getSolrUrlListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_url_id 
	* @return
	*/
	public SolrUrl getSolrUrlById(String solr_url_id);
	/**
	* 添加
	* @param solr_url 
	* @return
	*/
	public int addSolrUrl(SolrUrl solr_Url);
	/**
	* 修改
	* @param solr_url 
	* @return
	*/
	public int updateSolrUrl(SolrUrl solr_Url);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrUrl(Map<String,Object> condition);
}
