package jehc.solrmodules.solrservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_CoreDao;
import jehc.solrmodules.solrdao.Solr_Filed_CopyDao;
import jehc.solrmodules.solrdao.Solr_UrlDao;
import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.solrmodules.solrmodel.Solr_Document;
import jehc.solrmodules.solrmodel.Solr_Filed_Copy;
import jehc.solrmodules.solrmodel.Solr_Index;
import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.solrmodules.solrservice.Solr_DocumentService;
import jehc.solrmodules.solrservice.Solr_Filed_CopyService;
import jehc.solrmodules.solrservice.Solr_IndexService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* Solr字段拷贝配置 
* 2016-11-14 22:20:39  邓纯杰
*/
@Service("solr_Filed_CopyService")
public class Solr_Filed_CopyServiceImpl extends BaseService implements Solr_Filed_CopyService{
	@Autowired
	private Solr_Filed_CopyDao solr_Filed_CopyDao;
	@Autowired
	private Solr_CoreDao solr_CoreDao;
	@Autowired
	private Solr_UrlDao solr_UrlDao;
	@Autowired
	private Solr_DocumentService solr_DocumentService;
	@Autowired
	private Solr_IndexService solr_IndexService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Filed_Copy> getSolrFiledCopyListByCondition(Map<String,Object> condition){
		try{
			return solr_Filed_CopyDao.getSolrFiledCopyListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_filed_copy_id 
	* @return
	*/
	public Solr_Filed_Copy getSolrFiledCopyById(String solr_filed_copy_id){
		try{
			return solr_Filed_CopyDao.getSolrFiledCopyById(solr_filed_copy_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_filed_copy 
	* @return
	*/
	public int addSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy){
		int i = 0;
		try {
			Solr_Core solr_Core = solr_CoreDao.getSolrCoreById(solr_Filed_Copy.getSolr_core_id());
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			String solr_url_url = solr_url.getSolr_url_url();
			Solr_Document solr_Document = solr_DocumentService.getSolrDocumentById(solr_Core.getSolr_document_id());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<Solr_Index> solr_IndexList = solr_IndexService.getSolrIndexListByCondition(condition);
			i = solr_Filed_CopyDao.addSolrFiledCopy(solr_Filed_Copy);
			List<Solr_Filed_Copy> solr_Filed_CopyList = solr_Filed_CopyDao.getSolrFiledCopyListByCondition(condition);
			SolrUtil.updateCore(solr_url_url, solr_Core,solr_Document,solr_IndexList,solr_Filed_CopyList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_filed_copy 
	* @return
	*/
	public int updateSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy){
		int i = 0;
		try {
			Solr_Core solr_Core = solr_CoreDao.getSolrCoreById(solr_Filed_Copy.getSolr_core_id());
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			String solr_url_url = solr_url.getSolr_url_url();
			Solr_Document solr_Document = solr_DocumentService.getSolrDocumentById(solr_Core.getSolr_document_id());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<Solr_Index> solr_IndexList = solr_IndexService.getSolrIndexListByCondition(condition);
			i = solr_Filed_CopyDao.updateSolrFiledCopy(solr_Filed_Copy);
			List<Solr_Filed_Copy> solr_Filed_CopyList = solr_Filed_CopyDao.getSolrFiledCopyListByCondition(condition);
			SolrUtil.updateCore(solr_url_url, solr_Core,solr_Document,solr_IndexList,solr_Filed_CopyList);
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
	public int delSolrFiledCopy(Map<String,Object> map){
		int i = 0;
		try {
			String[] solr_filed_copy_id =(String[])map.get("solr_filed_copy_id");
			Solr_Filed_Copy solr_Filed_Copy = solr_Filed_CopyDao.getSolrFiledCopyById(solr_filed_copy_id[0]);
			Solr_Core solr_Core = solr_CoreDao.getSolrCoreById(solr_Filed_Copy.getSolr_core_id());
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			String solr_url_url = solr_url.getSolr_url_url();
			Solr_Document solr_Document = solr_DocumentService.getSolrDocumentById(solr_Core.getSolr_document_id());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<Solr_Index> solr_IndexList = solr_IndexService.getSolrIndexListByCondition(condition);
			i = solr_Filed_CopyDao.delSolrFiledCopy(map);
			List<Solr_Filed_Copy> solr_Filed_CopyList = solr_Filed_CopyDao.getSolrFiledCopyListByCondition(condition);
			SolrUtil.updateCore(solr_url_url, solr_Core,solr_Document,solr_IndexList,solr_Filed_CopyList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param solr_filed_copyList 
	* @return
	*/
	public int addBatchSolrFiledCopy(List<Solr_Filed_Copy> solr_Filed_CopyList){
		int i = 0;
		try {
			i = solr_Filed_CopyDao.addBatchSolrFiledCopy(solr_Filed_CopyList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param solr_filed_copyList 
	* @return
	*/
	public int updateBatchSolrFiledCopy(List<Solr_Filed_Copy> solr_Filed_CopyList){
		int i = 0;
		try {
			i = solr_Filed_CopyDao.updateBatchSolrFiledCopy(solr_Filed_CopyList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
