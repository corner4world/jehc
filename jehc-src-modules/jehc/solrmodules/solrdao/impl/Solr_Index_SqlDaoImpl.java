package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_Index_SqlDao;
import jehc.solrmodules.solrmodel.Solr_Index_Sql;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
@Repository("solr_Index_SqlDao")
public class Solr_Index_SqlDaoImpl  extends BaseDaoImpl implements Solr_Index_SqlDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Index_Sql> getSolrIndexSqlListByCondition(Map<String,Object> condition){
		return (List<Solr_Index_Sql>)this.getList("getSolrIndexSqlListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_sql_id 
	* @return
	*/
	public Solr_Index_Sql getSolrIndexSqlById(String solr_index_sql_id){
		return (Solr_Index_Sql)this.get("getSolrIndexSqlById", solr_index_sql_id);
	}
	/**
	* 添加
	* @param solr_index_sql 
	* @return
	*/
	public int addSolrIndexSql(Solr_Index_Sql solr_Index_Sql){
		return this.add("addSolrIndexSql", solr_Index_Sql);
	}
	/**
	* 修改
	* @param solr_index_sql 
	* @return
	*/
	public int updateSolrIndexSql(Solr_Index_Sql solr_Index_Sql){
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
