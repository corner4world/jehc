package jehc.solrmodules.solrservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrCoreDao;
import jehc.solrmodules.solrdao.SolrDocumentDao;
import jehc.solrmodules.solrdao.SolrEntityDao;
import jehc.solrmodules.solrdao.SolrUrlDao;
import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrmodel.SolrDocument;
import jehc.solrmodules.solrmodel.SolrEntity;
import jehc.solrmodules.solrmodel.SolrFiledCopy;
import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.solrmodules.solrmodel.SolrIndexSqlFiled;
import jehc.solrmodules.solrmodel.SolrUrl;
import jehc.solrmodules.solrservice.SolrEntityService;
import jehc.solrmodules.solrservice.SolrFiledCopyService;
import jehc.solrmodules.solrservice.SolrIndexSqlService;
import jehc.solrmodules.solrservice.SolrIndexSqlFiledService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
@Service("solrEntityService")
public class SolrEntityServiceImpl extends BaseService implements SolrEntityService{
	@Autowired
	private SolrEntityDao solrEntityDao;
	@Autowired
	private SolrIndexSqlService solrIndexSqlService;
	@Autowired
	private SolrIndexSqlFiledService solrIndexSqlFiledService;
	@Autowired
	private SolrCoreDao solrCoreDao;
	@Autowired
	private SolrUrlDao solrUrlDao;
	@Autowired
	private SolrDocumentDao solrDocumentDao;
	
	@Autowired
	private SolrFiledCopyService solrFiledCopyService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrEntity> getSolrEntityListByCondition(Map<String,Object> condition){
		try{
			return solrEntityDao.getSolrEntityListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_entity_id 
	* @return
	*/
	public SolrEntity getSolrEntityById(String solr_entity_id){
		try{
			return solrEntityDao.getSolrEntityById(solr_entity_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_entity 
	* @return
	*/
	public int addSolrEntity(SolrEntity solr_Entity,List<SolrIndexSql> solr_Index_SqlList,List<SolrIndexSqlFiled> solr_Index_Sql_FiledList){
		int i = 0;
		try {
			String solr_document_id = solr_Entity.getSolr_document_id();
			SolrDocument solr_Document = solrDocumentDao.getSolrDocumentById(solr_document_id);
			SolrCore solr_Core = solrCoreDao.getSolrCoreById(solr_Document.getSolr_core_id());
			SolrUrl solr_url =solrUrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			if(null == solr_Document){
				throw new ExceptionUtil("未获取到solr_Document对象");
			}
			if(null == solr_Core){
				throw new ExceptionUtil("未获取到solr_Core对象");
			}
			if(null == solr_url){
				throw new ExceptionUtil("未获取到SOlr_URL对象");
			}
			String solr_url_url = solr_url.getSolr_url_url();
			/**添加实体**/
			solr_Entity.setSolr_entity_id(UUID.toUUID());
			solrEntityDao.addSolrEntity(solr_Entity);
			/**添加SQL**/
			for(int l = 0; l < solr_Index_SqlList.size(); l++){
				SolrIndexSql solr_Index_Sql = solr_Index_SqlList.get(l);
				solr_Index_Sql.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				solrIndexSqlService.addSolrIndexSql(solr_Index_Sql);
			}
			/**添加SQL FILED**/
			for(int l = 0;l < solr_Index_Sql_FiledList.size(); l++){
				SolrIndexSqlFiled solr_Index_Sql_Filed = solr_Index_Sql_FiledList.get(l);
				solr_Index_Sql_Filed.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				solrIndexSqlFiledService.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
			}
			SolrUtil.updateCore(solr_url_url, solr_Core, solr_Document, null,null);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_entity 
	* @return
	*/
	public int updateSolrEntity(SolrEntity solr_Entity,List<SolrIndexSql> solr_Index_SqlList,List<SolrIndexSqlFiled> solr_Index_Sql_FiledList){
		int i = 0;
		try {
			String solr_document_id = solr_Entity.getSolr_document_id();
			SolrDocument solr_Document = solrDocumentDao.getSolrDocumentById(solr_document_id);
			SolrCore solr_Core = solrCoreDao.getSolrCoreById(solr_Document.getSolr_core_id());
			SolrUrl solr_url =solrUrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			if(null == solr_Document){
				throw new ExceptionUtil("未获取到solr_Document对象");
			}
			if(null == solr_Core){
				throw new ExceptionUtil("未获取到solr_Core对象");
			}
			if(null == solr_url){
				throw new ExceptionUtil("未获取到SOlr_URL对象");
			}
			String solr_url_url = solr_url.getSolr_url_url();
			/**修改实体**/
			solrEntityDao.updateSolrEntity(solr_Entity);
			/**添加SQL**/
			for(int l = 0; l < solr_Index_SqlList.size(); l++){
				SolrIndexSql solr_Index_Sql = solr_Index_SqlList.get(l);
				solr_Index_Sql.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				if(null != solr_Index_Sql.getSolr_index_sql_id() && !"".equals(solr_Index_Sql.getSolr_index_sql_id())){
					solrIndexSqlService.updateSolrIndexSql(solr_Index_Sql);
				}else{
					solr_Index_SqlList.get(l).setSolr_index_sql_id(UUID.toUUID());
					solrIndexSqlService.addSolrIndexSql(solr_Index_Sql);
				}
			}
			/**添加SQL FILED**/
			for(int l = 0;l < solr_Index_Sql_FiledList.size(); l++){
				SolrIndexSqlFiled solr_Index_Sql_Filed = solr_Index_Sql_FiledList.get(l);
				solr_Index_Sql_Filed.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				if(null != solr_Index_Sql_Filed.getSolr_index_sql_filed_id() && !"".equals(solr_Index_Sql_Filed.getSolr_index_sql_filed_id())){
					solrIndexSqlFiledService.updateSolrIndexSqlFiled(solr_Index_Sql_Filed);
				}else{
					solr_Index_Sql_FiledList.get(l).setSolr_index_sql_filed_id(UUID.toUUID());
					solrIndexSqlFiledService.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
				}
			}
			/**添加SQL CopyFILED**/
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<SolrFiledCopy> solr_Filed_CopyList = solrFiledCopyService.getSolrFiledCopyListByCondition(condition);
			SolrUtil.updateCore(solr_url_url, solr_Core, solr_Document, null,solr_Filed_CopyList);
			i = 1;
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
	public int delSolrEntity(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrEntityDao.delSolrEntity(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
