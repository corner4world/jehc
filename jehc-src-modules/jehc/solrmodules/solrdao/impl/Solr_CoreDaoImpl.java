package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_CoreDao;
import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
@Repository("solr_CoreDao")
public class Solr_CoreDaoImpl  extends BaseDaoImpl implements Solr_CoreDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Core> getSolrCoreListByCondition(Map<String,Object> condition){
		return (List<Solr_Core>)this.getList("getSolrCoreListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_core_id 
	* @return
	*/
	public Solr_Core getSolrCoreById(String solr_core_id){
		return (Solr_Core)this.get("getSolrCoreById", solr_core_id);
	}
	/**
	* 添加
	* @param solr_core 
	* @return
	*/
	public int addSolrCore(Solr_Core solr_Core){
		return this.add("addSolrCore", solr_Core);
	}
	/**
	* 修改
	* @param solr_core 
	* @return
	*/
	public int updateSolrCore(Solr_Core solr_Core){
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
