package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrUrlDao;
import jehc.solrmodules.solrmodel.SolrUrl;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
@Repository("solrUrlDao")
public class SolrUrlDaoImpl  extends BaseDaoImpl implements SolrUrlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrUrl> getSolrUrlListByCondition(Map<String,Object> condition){
		return (List<SolrUrl>)this.getList("getSolrUrlListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_url_id 
	* @return
	*/
	public SolrUrl getSolrUrlById(String solr_url_id){
		return (SolrUrl)this.get("getSolrUrlById", solr_url_id);
	}
	/**
	* 添加
	* @param solr_url 
	* @return
	*/
	public int addSolrUrl(SolrUrl solr_Url){
		return this.add("addSolrUrl", solr_Url);
	}
	/**
	* 修改
	* @param solr_url 
	* @return
	*/
	public int updateSolrUrl(SolrUrl solr_Url){
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
