package jehc.solrmodules.solrservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrCoreDao;
import jehc.solrmodules.solrdao.SolrUrlDao;
import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrmodel.SolrDocument;
import jehc.solrmodules.solrmodel.SolrFiledCopy;
import jehc.solrmodules.solrmodel.SolrIndex;
import jehc.solrmodules.solrmodel.SolrUrl;
import jehc.solrmodules.solrservice.SolrCoreService;
import jehc.solrmodules.solrservice.SolrDocumentService;
import jehc.solrmodules.solrservice.SolrFiledCopyService;
import jehc.solrmodules.solrservice.SolrIndexService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;

/**
* 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
@Service("solrCoreService")
public class SolrCoreServiceImpl extends BaseService implements SolrCoreService{
	@Autowired
	private SolrCoreDao solrCoreDao;
	@Autowired
	private SolrUrlDao solrUrlDao;
	@Autowired
	private SolrDocumentService solrDocumentService;
	@Autowired
	private SolrIndexService solrIndexService;
	@Autowired
	private SolrFiledCopyService solrFiledCopyService;
	
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrCore> getSolrCoreListByCondition(Map<String,Object> condition){
		try{
			return solrCoreDao.getSolrCoreListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_core_id 
	* @return
	*/
	public SolrCore getSolrCoreById(String solr_core_id){
		try{
			return solrCoreDao.getSolrCoreById(solr_core_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_core 
	* @return
	*/
	public int addSolrCore(SolrCore solr_Core,SolrDocument solr_Document,List<SolrIndex> solr_IndexList){
		int i = 0;
		try {
			SolrUrl solr_url =solrUrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			if(null == solr_url){
				throw new ExceptionUtil("未获取到SOlr_URL对象");
			}else{
				String solr_url_url = solr_url.getSolr_url_url();
				/**添加实例**/
				solrCoreDao.addSolrCore(solr_Core);
				for(int l = 0; l < solr_IndexList.size(); l++){
					SolrIndex solr_Index = solr_IndexList.get(l);
					solr_Index.setSolr_core_id(solr_Core.getSolr_core_id());
					/**添加索引**/
					solrIndexService.addSolrIndex(solr_Index);
				}
				solr_Document.setSolr_core_id(solr_Core.getSolr_core_id());
				/**添加文档**/
				i = solrDocumentService.addSolrDocument(solr_Document);
				SolrUtil.createCore(solr_url_url, solr_Core,solr_Document,solr_IndexList);
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_core 
	* @return
	*/
	public int updateSolrCore(SolrCore solr_Core,SolrDocument solr_Document,List<SolrIndex> solr_IndexList){
		int i = 0;
		try {
			SolrUrl solr_url =solrUrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			String solr_url_url = solr_url.getSolr_url_url();
			/**修改实例**/
			solrCoreDao.updateSolrCore(solr_Core);
			for(int l = 0; l < solr_IndexList.size(); l++){
				SolrIndex solr_Index = solr_IndexList.get(l);
				if(null != solr_Index.getSolr_index_id() && !"".equals(solr_Index.getSolr_index_id())){
					/**更新索引**/
					solr_Index.setSolr_core_id(solr_Core.getSolr_core_id());
					solrIndexService.updateSolrIndex(solr_Index);
				}else{
					solr_Index.setSolr_core_id(solr_Core.getSolr_core_id());
					solr_Index.setSolr_index_id(UUID.toUUID());
					/**添加索引**/
					solrIndexService.addSolrIndex(solr_Index);
				}
			}
			/**修改文档**/
			i = solrDocumentService.updateSolrDocument(solr_Document);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<SolrFiledCopy> solr_Filed_CopyList = solrFiledCopyService.getSolrFiledCopyListByCondition(condition);
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
	public int delSolrCore(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrCoreDao.delSolrCore(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
