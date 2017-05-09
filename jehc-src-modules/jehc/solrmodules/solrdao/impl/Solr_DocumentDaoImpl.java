package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_DocumentDao;
import jehc.solrmodules.solrmodel.Solr_Document;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr文档 
* 2015-12-23 09:38:59  邓纯杰
*/
@Repository("solr_DocumentDao")
public class Solr_DocumentDaoImpl  extends BaseDaoImpl implements Solr_DocumentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Document> getSolrDocumentListByCondition(Map<String,Object> condition){
		return (List<Solr_Document>)this.getList("getSolrDocumentListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_document_id 
	* @return
	*/
	public Solr_Document getSolrDocumentById(String solr_document_id){
		return (Solr_Document)this.get("getSolrDocumentById", solr_document_id);
	}
	/**
	* 添加
	* @param solr_document 
	* @return
	*/
	public int addSolrDocument(Solr_Document solr_Document){
		return this.add("addSolrDocument", solr_Document);
	}
	/**
	* 修改
	* @param solr_document 
	* @return
	*/
	public int updateSolrDocument(Solr_Document solr_Document){
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
