package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_DocumentDao;
import jehc.solrmodules.solrmodel.Solr_Document;
import jehc.solrmodules.solrservice.Solr_DocumentService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
@Service("solr_DocumentService")
public class Solr_DocumentServiceImpl extends BaseService implements Solr_DocumentService{
	@Autowired
	private Solr_DocumentDao solr_DocumentDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Document> getSolrDocumentListByCondition(Map<String,Object> condition){
		try{
			return solr_DocumentDao.getSolrDocumentListByCondition(condition);
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
	public Solr_Document getSolrDocumentById(String solr_document_id){
		try{
			return solr_DocumentDao.getSolrDocumentById(solr_document_id);
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
	public int addSolrDocument(Solr_Document solr_Document){
		int i = 0;
		try {
			i = solr_DocumentDao.addSolrDocument(solr_Document);
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
	public int updateSolrDocument(Solr_Document solr_Document){
		int i = 0;
		try {
			i = solr_DocumentDao.updateSolrDocument(solr_Document);
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
			i = solr_DocumentDao.delSolrDocument(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
