package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrSortDao;
import jehc.solrmodules.solrmodel.SolrSort;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr排序 
* 2016-07-08 23:49:58  邓纯杰
*/
@Repository("solrSortDao")
public class SolrSortDaoImpl  extends BaseDaoImpl implements SolrSortDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrSort> getSolrSortListByCondition(Map<String,Object> condition){
		return (List<SolrSort>)this.getList("getSolrSortListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_sort_id 
	* @return
	*/
	public SolrSort getSolrSortById(String solr_sort_id){
		return (SolrSort)this.get("getSolrSortById", solr_sort_id);
	}
	/**
	* 添加
	* @param solr_sort 
	* @return
	*/
	public int addSolrSort(SolrSort solr_Sort){
		return this.add("addSolrSort", solr_Sort);
	}
	/**
	* 修改
	* @param solr_sort 
	* @return
	*/
	public int updateSolrSort(SolrSort solr_Sort){
		return this.update("updateSolrSort", solr_Sort);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrSort(Map<String,Object> condition){
		return this.del("delSolrSort", condition);
	}
	
	/**
	 * 根据实例编号查找其下面排序字段
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SolrSort> getSolrSortList(Map<String,Object> condition){
		return (List<SolrSort>)this.getList("getSolrSortList",condition);
	}
}
