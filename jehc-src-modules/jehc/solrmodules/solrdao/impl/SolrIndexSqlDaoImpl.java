package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrIndexSqlDao;
import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
@Repository("solrIndexSqlDao")
public class SolrIndexSqlDaoImpl  extends BaseDaoImpl implements SolrIndexSqlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrIndexSql> getSolrIndexSqlListByCondition(Map<String,Object> condition){
		return (List<SolrIndexSql>)this.getList("getSolrIndexSqlListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_sql_id 
	* @return
	*/
	public SolrIndexSql getSolrIndexSqlById(String solr_index_sql_id){
		return (SolrIndexSql)this.get("getSolrIndexSqlById", solr_index_sql_id);
	}
	/**
	* 添加
	* @param solr_index_sql 
	* @return
	*/
	public int addSolrIndexSql(SolrIndexSql solr_Index_Sql){
		return this.add("addSolrIndexSql", solr_Index_Sql);
	}
	/**
	* 修改
	* @param solr_index_sql 
	* @return
	*/
	public int updateSolrIndexSql(SolrIndexSql solr_Index_Sql){
		return this.update("updateSolrIndexSql", solr_Index_Sql);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexSql(Map<String,Object> condition){
		return this.del("delSolrIndexSql", condition);
	}
}
