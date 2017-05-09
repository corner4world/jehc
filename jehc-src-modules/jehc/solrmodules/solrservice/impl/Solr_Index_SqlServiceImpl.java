package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_Index_SqlDao;
import jehc.solrmodules.solrmodel.Solr_Index_Sql;
import jehc.solrmodules.solrservice.Solr_Index_SqlService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
@Service("solr_Index_SqlService")
public class Solr_Index_SqlServiceImpl extends BaseService implements Solr_Index_SqlService{
	@Autowired
	private Solr_Index_SqlDao solr_Index_SqlDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Index_Sql> getSolrIndexSqlListByCondition(Map<String,Object> condition){
		try{
			return solr_Index_SqlDao.getSolrIndexSqlListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_index_sql_id 
	* @return
	*/
	public Solr_Index_Sql getSolrIndexSqlById(String solr_index_sql_id){
		try{
			return solr_Index_SqlDao.getSolrIndexSqlById(solr_index_sql_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_index_sql 
	* @return
	*/
	public int addSolrIndexSql(Solr_Index_Sql solr_Index_Sql){
		int i = 0;
		try {
			i = solr_Index_SqlDao.addSolrIndexSql(solr_Index_Sql);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_index_sql 
	* @return
	*/
	public int updateSolrIndexSql(Solr_Index_Sql solr_Index_Sql){
		int i = 0;
		try {
			i = solr_Index_SqlDao.updateSolrIndexSql(solr_Index_Sql);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexSql(Map<String,Object> condition){
		int i = 0;
		try {
			i = solr_Index_SqlDao.delSolrIndexSql(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
