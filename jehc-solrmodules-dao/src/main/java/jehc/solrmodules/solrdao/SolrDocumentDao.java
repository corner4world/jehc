package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrDocument;

/**
* solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
public interface SolrDocumentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrDocument> getSolrDocumentListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_document_id 
	* @return
	*/
	public SolrDocument getSolrDocumentById(String solr_document_id);
	/**
	* 添加
	* @param solr_document 
	* @return
	*/
	public int addSolrDocument(SolrDocument solr_Document);
	/**
	* 修改
	* @param solr_document 
	* @return
	*/
	public int updateSolrDocument(SolrDocument solr_Document);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrDocument(Map<String,Object> condition);
}
