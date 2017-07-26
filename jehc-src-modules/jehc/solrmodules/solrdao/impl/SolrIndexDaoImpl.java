package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrIndexDao;
import jehc.solrmodules.solrmodel.SolrIndex;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
@Repository("solrIndexDao")
public class SolrIndexDaoImpl  extends BaseDaoImpl implements SolrIndexDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrIndex> getSolrIndexListByCondition(Map<String,Object> condition){
		return (List<SolrIndex>)this.getList("getSolrIndexListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_id 
	* @return
	*/
	public SolrIndex getSolrIndexById(String solr_index_id){
		return (SolrIndex)this.get("getSolrIndexById", solr_index_id);
	}
	/**
	* 添加
	* @param solr_index 
	* @return
	*/
	public int addSolrIndex(SolrIndex solr_Index){
		return this.add("addSolrIndex", solr_Index);
	}
	/**
	* 修改
	* @param solr_index 
	* @return
	*/
	public int updateSolrIndex(SolrIndex solr_Index){
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
