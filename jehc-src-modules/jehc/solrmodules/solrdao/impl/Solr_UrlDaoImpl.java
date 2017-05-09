package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_UrlDao;
import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
@Repository("solr_UrlDao")
public class Solr_UrlDaoImpl  extends BaseDaoImpl implements Solr_UrlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Url> getSolrUrlListByCondition(Map<String,Object> condition){
		return (List<Solr_Url>)this.getList("getSolrUrlListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_url_id 
	* @return
	*/
	public Solr_Url getSolrUrlById(String solr_url_id){
		return (Solr_Url)this.get("getSolrUrlById", solr_url_id);
	}
	/**
	* 添加
	* @param solr_url 
	* @return
	*/
	public int addSolrUrl(Solr_Url solr_Url){
		return this.add("addSolrUrl", solr_Url);
	}
	/**
	* 修改
	* @param solr_url 
	* @return
	*/
	public int updateSolrUrl(Solr_Url solr_Url){
		return this.update("updateSolrUrl", solr_Url);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrUrl(Map<String,Object> condition){
		return this.del("delSolrUrl", condition);
	}
}
