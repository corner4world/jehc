package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrIndexSql;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
public interface SolrIndexSqlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrIndexSql> getSolrIndexSqlListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_index_sql_id 
	* @return
	*/
	public SolrIndexSql getSolrIndexSqlById(String solr_index_sql_id);
	/**
	* 添加
	* @param solr_index_sql 
	* @return
	*/
	public int addSolrIndexSql(SolrIndexSql solr_Index_Sql);
	/**
	* 修改
	* @param solr_index_sql 
	* @return
	*/
	public int updateSolrIndexSql(SolrIndexSql solr_Index_Sql);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexSql(Map<String,Object> condition);
}
