package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_Index_Sql_FiledDao;
import jehc.solrmodules.solrmodel.Solr_Index_Sql_Filed;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* SQL查询结果返回字段 
* 2015-12-23 09:44:02  邓纯杰
*/
@Repository("solr_Index_Sql_FiledDao")
public class Solr_Index_Sql_FiledDaoImpl  extends BaseDaoImpl implements Solr_Index_Sql_FiledDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Index_Sql_Filed> getSolrIndexSqlFiledListByCondition(Map<String,Object> condition){
		return (List<Solr_Index_Sql_Filed>)this.getList("getSolrIndexSqlFiledListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_sql_filed_id 
	* @return
	*/
	public Solr_Index_Sql_Filed getSolrIndexSqlFiledById(String solr_index_sql_filed_id){
		return (Solr_Index_Sql_Filed)this.get("getSolrIndexSqlFiledById", solr_index_sql_filed_id);
	}
	/**
	* 添加
	* @param solr_index_sql_filed 
	* @return
	*/
	public int addSolrIndexSqlFiled(Solr_Index_Sql_Filed solr_Index_Sql_Filed){
		return this.add("addSolrIndexSqlFiled", solr_Index_Sql_Filed);
	}
	/**
	* 修改
	* @param solr_index_sql_filed 
	* @return
	*/
	public int updateSolrIndexSqlFiled(Solr_Index_Sql_Filed solr_Index_Sql_Filed){
		return this.update("updateSolrIndexSqlFiled", solr_Index_Sql_Filed);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexSqlFiled(Map<String,Object> condition){
		return this.del("delSolrIndexSqlFiled", condition);
	}
}
