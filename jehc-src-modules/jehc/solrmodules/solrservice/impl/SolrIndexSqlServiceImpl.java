package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrIndexSqlDao;
import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.solrmodules.solrservice.SolrIndexSqlService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
@Service("solrIndexSqlService")
public class SolrIndexSqlServiceImpl extends BaseService implements SolrIndexSqlService{
	@Autowired
	private SolrIndexSqlDao solrIndexSqlDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrIndexSql> getSolrIndexSqlListByCondition(Map<String,Object> condition){
		try{
			return solrIndexSqlDao.getSolrIndexSqlListByCondition(condition);
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
	public SolrIndexSql getSolrIndexSqlById(String solr_index_sql_id){
		try{
			return solrIndexSqlDao.getSolrIndexSqlById(solr_index_sql_id);
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
	public int addSolrIndexSql(SolrIndexSql solr_Index_Sql){
		int i = 0;
		try {
			i = solrIndexSqlDao.addSolrIndexSql(solr_Index_Sql);
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
	public int updateSolrIndexSql(SolrIndexSql solr_Index_Sql){
		int i = 0;
		try {
			i = solrIndexSqlDao.updateSolrIndexSql(solr_Index_Sql);
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
			i = solrIndexSqlDao.delSolrIndexSql(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
