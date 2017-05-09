package jehc.solrmodules.solrservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_CoreDao;
import jehc.solrmodules.solrdao.Solr_UrlDao;
import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.solrmodules.solrmodel.Solr_Document;
import jehc.solrmodules.solrmodel.Solr_Filed_Copy;
import jehc.solrmodules.solrmodel.Solr_Index;
import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.solrmodules.solrservice.Solr_CoreService;
import jehc.solrmodules.solrservice.Solr_DocumentService;
import jehc.solrmodules.solrservice.Solr_Filed_CopyService;
import jehc.solrmodules.solrservice.Solr_IndexService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;

/**
* 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
@Service("solr_CoreService")
public class Solr_CoreServiceImpl extends BaseService implements Solr_CoreService{
	@Autowired
	private Solr_CoreDao solr_CoreDao;
	@Autowired
	private Solr_UrlDao solr_UrlDao;
	@Autowired
	private Solr_DocumentService solr_DocumentService;
	@Autowired
	private Solr_IndexService solr_IndexService;
	@Autowired
	private Solr_Filed_CopyService solr_Filed_CopyService;
	
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Core> getSolrCoreListByCondition(Map<String,Object> condition){
		try{
			return solr_CoreDao.getSolrCoreListByCondition(condition);
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
	public Solr_Core getSolrCoreById(String solr_core_id){
		try{
			return solr_CoreDao.getSolrCoreById(solr_core_id);
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
	public int addSolrCore(Solr_Core solr_Core,Solr_Document solr_Document,List<Solr_Index> solr_IndexList){
		int i = 0;
		try {
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			if(null == solr_url){
				throw new ExceptionUtil("未获取到SOlr_URL对象");
			}else{
				String solr_url_url = solr_url.getSolr_url_url();
				/**添加实例**/
				solr_CoreDao.addSolrCore(solr_Core);
				for(int l = 0; l < solr_IndexList.size(); l++){
					Solr_Index solr_Index = solr_IndexList.get(l);
					solr_Index.setSolr_core_id(solr_Core.getSolr_core_id());
					/**添加索引**/
					solr_IndexService.addSolrIndex(solr_Index);
				}
				solr_Document.setSolr_core_id(solr_Core.getSolr_core_id());
				/**添加文档**/
				i = solr_DocumentService.addSolrDocument(solr_Document);
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
	public int updateSolrCore(Solr_Core solr_Core,Solr_Document solr_Document,List<Solr_Index> solr_IndexList){
		int i = 0;
		try {
			Solr_Url solr_url =solr_UrlDao.getSolrUrlById(solr_Core.getSolr_url_id());
			String solr_url_url = solr_url.getSolr_url_url();
			/**修改实例**/
			solr_CoreDao.updateSolrCore(solr_Core);
			for(int l = 0; l < solr_IndexList.size(); l++){
				Solr_Index solr_Index = solr_IndexList.get(l);
				if(null != solr_Index.getSolr_index_id() && !"".equals(solr_Index.getSolr_index_id())){
					/**更新索引**/
					solr_Index.setSolr_core_id(solr_Core.getSolr_core_id());
					solr_IndexService.updateSolrIndex(solr_Index);
				}else{
					solr_Index.setSolr_core_id(solr_Core.getSolr_core_id());
					solr_Index.setSolr_index_id(UUID.toUUID());
					/**添加索引**/
					solr_IndexService.addSolrIndex(solr_Index);
				}
			}
			/**修改文档**/
			i = solr_DocumentService.updateSolrDocument(solr_Document);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			List<Solr_Filed_Copy> solr_Filed_CopyList = solr_Filed_CopyService.getSolrFiledCopyListByCondition(condition);
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
			i = solr_CoreDao.delSolrCore(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
