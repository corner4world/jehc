package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrDocumentDao;
import jehc.solrmodules.solrmodel.SolrDocument;
import jehc.solrmodules.solrservice.SolrDocumentService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
@Service("solrDocumentService")
public class SolrDocumentServiceImpl extends BaseService implements SolrDocumentService{
	@Autowired
	private SolrDocumentDao solrDocumentDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrDocument> getSolrDocumentListByCondition(Map<String,Object> condition){
		try{
			return solrDocumentDao.getSolrDocumentListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_document_id 
	* @return
	*/
	public SolrDocument getSolrDocumentById(String solr_document_id){
		try{
			return solrDocumentDao.getSolrDocumentById(solr_document_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_document 
	* @return
	*/
	public int addSolrDocument(SolrDocument solr_Document){
		int i = 0;
		try {
			i = solrDocumentDao.addSolrDocument(solr_Document);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_document 
	* @return
	*/
	public int updateSolrDocument(SolrDocument solr_Document){
		int i = 0;
		try {
			i = solrDocumentDao.updateSolrDocument(solr_Document);
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
	public int delSolrDocument(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrDocumentDao.delSolrDocument(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
