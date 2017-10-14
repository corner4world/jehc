package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrSchemaTempletDao;
import jehc.solrmodules.solrmodel.SolrSchemaTemplet;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
@Repository("solrSchemaTempletDao")
public class SolrSchemaTempletDaoImpl  extends BaseDaoImpl implements SolrSchemaTempletDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrSchemaTemplet> getSolrSchemaTempletListByCondition(Map<String,Object> condition){
		return (List<SolrSchemaTemplet>)this.getList("getSolrSchemaTempletListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_schema_templet_id 
	* @return
	*/
	public SolrSchemaTemplet getSolrSchemaTempletById(String solr_schema_templet_id){
		return (SolrSchemaTemplet)this.get("getSolrSchemaTempletById", solr_schema_templet_id);
	}
	/**
	* 添加
	* @param solr_schema_templet 
	* @return
	*/
	public int addSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet){
		return this.add("addSolrSchemaTemplet", solr_Schema_Templet);
	}
	/**
	* 修改
	* @param solr_schema_templet 
	* @return
	*/
	public int updateSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet){
		return this.update("updateSolrSchemaTemplet", solr_Schema_Templet);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrSchemaTemplet(Map<String,Object> condition){
		return this.del("delSolrSchemaTemplet", condition);
	}
}
