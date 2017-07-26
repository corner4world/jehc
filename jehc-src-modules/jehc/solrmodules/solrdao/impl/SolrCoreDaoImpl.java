package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrCoreDao;
import jehc.solrmodules.solrmodel.SolrCore;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
@Repository("solrCoreDao")
public class SolrCoreDaoImpl  extends BaseDaoImpl implements SolrCoreDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrCore> getSolrCoreListByCondition(Map<String,Object> condition){
		return (List<SolrCore>)this.getList("getSolrCoreListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_core_id 
	* @return
	*/
	public SolrCore getSolrCoreById(String solr_core_id){
		return (SolrCore)this.get("getSolrCoreById", solr_core_id);
	}
	/**
	* 添加
	* @param solr_core 
	* @return
	*/
	public int addSolrCore(SolrCore solr_Core){
		return this.add("addSolrCore", solr_Core);
	}
	/**
	* 修改
	* @param solr_core 
	* @return
	*/
	public int updateSolrCore(SolrCore solr_Core){
		return this.update("updateSolrCore", solr_Core);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrCore(Map<String,Object> condition){
		return this.del("delSolrCore", condition);
	}
}
