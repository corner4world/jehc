package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_EntityDao;
import jehc.solrmodules.solrmodel.Solr_Entity;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
@Repository("solr_EntityDao")
public class Solr_EntityDaoImpl  extends BaseDaoImpl implements Solr_EntityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Entity> getSolrEntityListByCondition(Map<String,Object> condition){
		return (List<Solr_Entity>)this.getList("getSolrEntityListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_entity_id 
	* @return
	*/
	public Solr_Entity getSolrEntityById(String solr_entity_id){
		return (Solr_Entity)this.get("getSolrEntityById", solr_entity_id);
	}
	/**
	* 添加
	* @param solr_entity 
	* @return
	*/
	public int addSolrEntity(Solr_Entity solr_Entity){
		return this.add("addSolrEntity", solr_Entity);
	}
	/**
	* 修改
	* @param solr_entity 
	* @return
	*/
	public int updateSolrEntity(Solr_Entity solr_Entity){
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
