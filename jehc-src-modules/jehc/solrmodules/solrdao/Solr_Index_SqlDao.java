package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Index_Sql;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
public interface Solr_Index_SqlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Index_Sql> getSolrIndexSqlListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_index_sql_id 
	* @return
	*/
	public Solr_Index_Sql getSolrIndexSqlById(String solr_index_sql_id);
	/**
	* 添加
	* @param solr_index_sql 
	* @return
	*/
	public int addSolrIndexSql(Solr_Index_Sql solr_Index_Sql);
	/**
	* 修改
	* @param solr_index_sql 
	* @return
	*/
	public int updateSolrIndexSql(Solr_Index_Sql solr_Index_Sql);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexSql(Map<String,Object> condition);
}
