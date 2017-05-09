package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_Schema_TempletDao;
import jehc.solrmodules.solrmodel.Solr_Schema_Templet;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
@Repository("solr_Schema_TempletDao")
public class Solr_Schema_TempletDaoImpl  extends BaseDaoImpl implements Solr_Schema_TempletDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Schema_Templet> getSolrSchemaTempletListByCondition(Map<String,Object> condition){
		return (List<Solr_Schema_Templet>)this.getList("getSolrSchemaTempletListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_schema_templet_id 
	* @return
	*/
	public Solr_Schema_Templet getSolrSchemaTempletById(String solr_schema_templet_id){
		return (Solr_Schema_Templet)this.get("getSolrSchemaTempletById", solr_schema_templet_id);
	}
	/**
	* 添加
	* @param solr_schema_templet 
	* @return
	*/
	public int addSolrSchemaTemplet(Solr_Schema_Templet solr_Schema_Templet){
		return this.add("addSolrSchemaTemplet", solr_Schema_Templet);
	}
	/**
	* 修改
	* @param solr_schema_templet 
	* @return
	*/
	public int updateSolrSchemaTemplet(Solr_Schema_Templet solr_Schema_Templet){
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
