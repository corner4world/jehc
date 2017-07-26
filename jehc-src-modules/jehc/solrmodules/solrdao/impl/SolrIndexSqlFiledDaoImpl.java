package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrIndexSqlFiledDao;
import jehc.solrmodules.solrmodel.SolrIndexSqlFiled;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* SQL查询结果返回字段 
* 2015-12-23 09:44:02  邓纯杰
*/
@Repository("solrIndexSqlFiledDao")
public class SolrIndexSqlFiledDaoImpl  extends BaseDaoImpl implements SolrIndexSqlFiledDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrIndexSqlFiled> getSolrIndexSqlFiledListByCondition(Map<String,Object> condition){
		return (List<SolrIndexSqlFiled>)this.getList("getSolrIndexSqlFiledListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_sql_filed_id 
	* @return
	*/
	public SolrIndexSqlFiled getSolrIndexSqlFiledById(String solr_index_sql_filed_id){
		return (SolrIndexSqlFiled)this.get("getSolrIndexSqlFiledById", solr_index_sql_filed_id);
	}
	/**
	* 添加
	* @param solr_index_sql_filed 
	* @return
	*/
	public int addSolrIndexSqlFiled(SolrIndexSqlFiled solr_Index_Sql_Filed){
		return this.add("addSolrIndexSqlFiled", solr_Index_Sql_Filed);
	}
	/**
	* 修改
	* @param solr_index_sql_filed 
	* @return
	*/
	public int updateSolrIndexSqlFiled(SolrIndexSqlFiled solr_Index_Sql_Filed){
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
