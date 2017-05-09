package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_Index_Sql_FiledDao;
import jehc.solrmodules.solrmodel.Solr_Index_Sql_Filed;
import jehc.solrmodules.solrservice.Solr_Index_Sql_FiledService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* SQL查询结果返回字段 
* 2015-12-23 09:44:02  邓纯杰
*/
@Service("solr_Index_Sql_FiledService")
public class Solr_Index_Sql_FiledServiceImpl extends BaseService implements Solr_Index_Sql_FiledService{
	@Autowired
	private Solr_Index_Sql_FiledDao solr_Index_Sql_FiledDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Index_Sql_Filed> getSolrIndexSqlFiledListByCondition(Map<String,Object> condition){
		try{
			return solr_Index_Sql_FiledDao.getSolrIndexSqlFiledListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_index_sql_filed_id 
	* @return
	*/
	public Solr_Index_Sql_Filed getSolrIndexSqlFiledById(String solr_index_sql_filed_id){
		try{
			return solr_Index_Sql_FiledDao.getSolrIndexSqlFiledById(solr_index_sql_filed_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_index_sql_filed 
	* @return
	*/
	public int addSolrIndexSqlFiled(Solr_Index_Sql_Filed solr_Index_Sql_Filed){
		int i = 0;
		try {
			i = solr_Index_Sql_FiledDao.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_index_sql_filed 
	* @return
	*/
	public int updateSolrIndexSqlFiled(Solr_Index_Sql_Filed solr_Index_Sql_Filed){
		int i = 0;
		try {
			i = solr_Index_Sql_FiledDao.updateSolrIndexSqlFiled(solr_Index_Sql_Filed);
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
	public int delSolrIndexSqlFiled(Map<String,Object> condition){
		int i = 0;
		try {
			i = solr_Index_Sql_FiledDao.delSolrIndexSqlFiled(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
