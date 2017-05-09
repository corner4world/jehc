package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Url;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
public interface Solr_UrlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Url> getSolrUrlListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_url_id 
	* @return
	*/
	public Solr_Url getSolrUrlById(String solr_url_id);
	/**
	* 添加
	* @param solr_url 
	* @return
	*/
	public int addSolrUrl(Solr_Url solr_Url);
	/**
	* 修改
	* @param solr_url 
	* @return
	*/
	public int updateSolrUrl(Solr_Url solr_Url);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrUrl(Map<String,Object> condition);
}
