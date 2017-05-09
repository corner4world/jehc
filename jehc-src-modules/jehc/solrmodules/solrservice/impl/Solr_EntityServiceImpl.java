package jehc.solrmodules.solrservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_CoreDao;
import jehc.solrmodules.solrdao.Solr_DocumentDao;
import jehc.solrmodules.solrdao.Solr_EntityDao;
import jehc.solrmodules.solrdao.Solr_UrlDao;
import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.solrmodules.solrmodel.Solr_Document;
import jehc.solrmodules.solrmodel.Solr_Entity;
import jehc.solrmodules.solrmodel.Solr_Filed_Copy;
import jehc.solrmodules.solrmodel.Solr_Index_Sql;
import jehc.solrmodules.solrmodel.Solr_Index_Sql_Filed;
import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.solrmodules.solrservice.Solr_EntityService;
import jehc.solrmodules.solrservice.Solr_Filed_CopyService;
import jehc.solrmodules.solrservice.Solr_Index_SqlService;
import jehc.solrmodules.solrservice.Solr_Index_Sql_FiledService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;

/**
* solr实体 
* 2015-12-23 09:40:52  邓纯杰
*/
@Service("solr_EntityService")
public class Solr_EntityServiceImpl extends BaseService implements Solr_EntityService{
	@Autowired
	private Solr_EntityDao solr_EntityDao;
	@Autowired
	private Solr_Index_SqlService solr_Index_SqlService;
	@Autowired
	private Solr_Index_Sql_FiledService solr_Index_Sql_FiledService;
	@Autowired
	private Solr_CoreDao solr_CoreDao;
	@Autowired
	private Solr_UrlDao solr_UrlDao;
	@Autowired
	private Solr_DocumentDao solr_DocumentDao;
	
	@Autowired
	private Solr_Filed_CopyService solr_Filed_CopyService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Entity> getSolrEntityListByCondition(Map<String,Object> condition){
		try{
			return solr_EntityDao.getSolrEntityListByCondition(condition);
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
	public Solr_Entity getSolrEntityById(String solr_entity_id){
		try{
			return solr_EntityDao.getSolrEntityById(solr_entity_id);
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
	public int addSolrEntity(Solr_Entity solr_Entity,List<Solr_Index_Sql> solr_Index_SqlList,List<Solr_Index_Sql_Filed> solr_Index_Sql_FiledList){
		int i = 0;
		try {
			String solr_document_id = solr_Entity.getSolr_document_id();
			Solr_Document solr_Document = solr_DocumentDao.getSolrDocumentById(solr_document_id);
			Solr_Core solr_Core = solr_CoreDao.getSolrCoreById(solr_Document.getSolr_core_id());
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
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
			solr_EntityDao.addSolrEntity(solr_Entity);
			/**添加SQL**/
			for(int l = 0; l < solr_Index_SqlList.size(); l++){
				Solr_Index_Sql solr_Index_Sql = solr_Index_SqlList.get(l);
				solr_Index_Sql.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				solr_Index_SqlService.addSolrIndexSql(solr_Index_Sql);
			}
			/**添加SQL FILED**/
			for(int l = 0;l < solr_Index_Sql_FiledList.size(); l++){
				Solr_Index_Sql_Filed solr_Index_Sql_Filed = solr_Index_Sql_FiledList.get(l);
				solr_Index_Sql_Filed.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				solr_Index_Sql_FiledService.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
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
	public int updateSolrEntity(Solr_Entity solr_Entity,List<Solr_Index_Sql> solr_Index_SqlList,List<Solr_Index_Sql_Filed> solr_Index_Sql_FiledList){
		int i = 0;
		try {
			String solr_document_id = solr_Entity.getSolr_document_id();
			Solr_Document solr_Document = solr_DocumentDao.getSolrDocumentById(solr_document_id);
			Solr_Core solr_Core = solr_CoreDao.getSolrCoreById(solr_Document.getSolr_core_id());
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
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
			solr_EntityDao.updateSolrEntity(solr_Entity);
			/**添加SQL**/
			for(int l = 0; l < solr_Index_SqlList.size(); l++){
				Solr_Index_Sql solr_Index_Sql = solr_Index_SqlList.get(l);
				solr_Index_Sql.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				if(null != solr_Index_Sql.getSolr_index_sql_id() && !"".equals(solr_Index_Sql.getSolr_index_sql_id())){
					solr_Index_SqlService.updateSolrIndexSql(solr_Index_Sql);
				}else{
					solr_Index_SqlList.get(l).setSolr_index_sql_id(UUID.toUUID());
					solr_Index_SqlService.addSolrIndexSql(solr_Index_Sql);
				}
			}
			/**添加SQL FILED**/
			for(int l = 0;l < solr_Index_Sql_FiledList.size(); l++){
				Solr_Index_Sql_Filed solr_Index_Sql_Filed = solr_Index_Sql_FiledList.get(l);
				solr_Index_Sql_Filed.setSolr_entity_id(solr_Entity.getSolr_entity_id());
				if(null != solr_Index_Sql_Filed.getSolr_index_sql_filed_id() && !"".equals(solr_Index_Sql_Filed.getSolr_index_sql_filed_id())){
					solr_Index_Sql_FiledService.updateSolrIndexSqlFiled(solr_Index_Sql_Filed);
				}else{
					solr_Index_Sql_FiledList.get(l).setSolr_index_sql_filed_id(UUID.toUUID());
					solr_Index_Sql_FiledService.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
				}
			}
			/**添加SQL CopyFILED**/
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<Solr_Filed_Copy> solr_Filed_CopyList = solr_Filed_CopyService.getSolrFiledCopyListByCondition(condition);
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
			i = solr_EntityDao.delSolrEntity(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
