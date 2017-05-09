package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Document;

/**
* solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
public interface Solr_DocumentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Document> getSolrDocumentListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_document_id 
	* @return
	*/
	public Solr_Document getSolrDocumentById(String solr_document_id);
	/**
	* 添加
	* @param solr_document 
	* @return
	*/
	public int addSolrDocument(Solr_Document solr_Document);
	/**
	* 修改
	* @param solr_document 
	* @return
	*/
	public int updateSolrDocument(Solr_Document solr_Document);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrDocument(Map<String,Object> condition);
}
