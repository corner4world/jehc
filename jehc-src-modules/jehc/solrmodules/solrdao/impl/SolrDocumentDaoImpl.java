package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrDocumentDao;
import jehc.solrmodules.solrmodel.SolrDocument;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
@Repository("solrDocumentDao")
public class SolrDocumentDaoImpl  extends BaseDaoImpl implements SolrDocumentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrDocument> getSolrDocumentListByCondition(Map<String,Object> condition){
		return (List<SolrDocument>)this.getList("getSolrDocumentListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_document_id 
	* @return
	*/
	public SolrDocument getSolrDocumentById(String solr_document_id){
		return (SolrDocument)this.get("getSolrDocumentById", solr_document_id);
	}
	/**
	* 添加
	* @param solr_document 
	* @return
	*/
	public int addSolrDocument(SolrDocument solr_Document){
		return this.add("addSolrDocument", solr_Document);
	}
	/**
	* 修改
	* @param solr_document 
	* @return
	*/
	public int updateSolrDocument(SolrDocument solr_Document){
		return this.update("updateSolrDocument", solr_Document);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrDocument(Map<String,Object> condition){
		return this.del("delSolrDocument", condition);
	}
}
