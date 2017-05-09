package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_Schema_TempletDao;
import jehc.solrmodules.solrmodel.Solr_Schema_Templet;
import jehc.solrmodules.solrservice.Solr_Schema_TempletService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
@Service("solr_Schema_TempletService")
public class Solr_Schema_TempletServiceImpl extends BaseService implements Solr_Schema_TempletService{
	@Autowired
	private Solr_Schema_TempletDao solr_Schema_TempletDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Schema_Templet> getSolrSchemaTempletListByCondition(Map<String,Object> condition){
		try{
			return solr_Schema_TempletDao.getSolrSchemaTempletListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_schema_templet_id 
	* @return
	*/
	public Solr_Schema_Templet getSolrSchemaTempletById(String solr_schema_templet_id){
		try{
			return solr_Schema_TempletDao.getSolrSchemaTempletById(solr_schema_templet_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_schema_templet 
	* @return
	*/
	public int addSolrSchemaTemplet(Solr_Schema_Templet solr_Schema_Templet){
		int i = 0;
		try {
			i = solr_Schema_TempletDao.addSolrSchemaTemplet(solr_Schema_Templet);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_schema_templet 
	* @return
	*/
	public int updateSolrSchemaTemplet(Solr_Schema_Templet solr_Schema_Templet){
		int i = 0;
		try {
			i = solr_Schema_TempletDao.updateSolrSchemaTemplet(solr_Schema_Templet);
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
	public int delSolrSchemaTemplet(Map<String,Object> condition){
		int i = 0;
		try {
			i = solr_Schema_TempletDao.delSolrSchemaTemplet(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
