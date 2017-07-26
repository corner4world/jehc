package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrEntityDao;
import jehc.solrmodules.solrmodel.SolrEntity;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
@Repository("solrEntityDao")
public class SolrEntityDaoImpl  extends BaseDaoImpl implements SolrEntityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrEntity> getSolrEntityListByCondition(Map<String,Object> condition){
		return (List<SolrEntity>)this.getList("getSolrEntityListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_entity_id 
	* @return
	*/
	public SolrEntity getSolrEntityById(String solr_entity_id){
		return (SolrEntity)this.get("getSolrEntityById", solr_entity_id);
	}
	/**
	* 添加
	* @param solr_entity 
	* @return
	*/
	public int addSolrEntity(SolrEntity solr_Entity){
		return this.add("addSolrEntity", solr_Entity);
	}
	/**
	* 修改
	* @param solr_entity 
	* @return
	*/
	public int updateSolrEntity(SolrEntity solr_Entity){
		return this.update("updateSolrEntity", solr_Entity);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrEntity(Map<String,Object> condition){
		return this.del("delSolrEntity", condition);
	}
}
