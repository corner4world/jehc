package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_IndexDao;
import jehc.solrmodules.solrmodel.Solr_Index;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
@Repository("solr_IndexDao")
public class Solr_IndexDaoImpl  extends BaseDaoImpl implements Solr_IndexDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Index> getSolrIndexListByCondition(Map<String,Object> condition){
		return (List<Solr_Index>)this.getList("getSolrIndexListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_id 
	* @return
	*/
	public Solr_Index getSolrIndexById(String solr_index_id){
		return (Solr_Index)this.get("getSolrIndexById", solr_index_id);
	}
	/**
	* 添加
	* @param solr_index 
	* @return
	*/
	public int addSolrIndex(Solr_Index solr_Index){
		return this.add("addSolrIndex", solr_Index);
	}
	/**
	* 修改
	* @param solr_index 
	* @return
	*/
	public int updateSolrIndex(Solr_Index solr_Index){
		return this.update("updateSolrIndex", solr_Index);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndex(Map<String,Object> condition){
		return this.del("delSolrIndex", condition);
	}
}
