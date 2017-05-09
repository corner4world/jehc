package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_SortDao;
import jehc.solrmodules.solrmodel.Solr_Sort;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr排序 
* 2016-07-08 23:49:58  邓纯杰
*/
@Repository("solr_SortDao")
public class Solr_SortDaoImpl  extends BaseDaoImpl implements Solr_SortDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Sort> getSolrSortListByCondition(Map<String,Object> condition){
		return (List<Solr_Sort>)this.getList("getSolrSortListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_sort_id 
	* @return
	*/
	public Solr_Sort getSolrSortById(String solr_sort_id){
		return (Solr_Sort)this.get("getSolrSortById", solr_sort_id);
	}
	/**
	* 添加
	* @param solr_sort 
	* @return
	*/
	public int addSolrSort(Solr_Sort solr_Sort){
		return this.add("addSolrSort", solr_Sort);
	}
	/**
	* 修改
	* @param solr_sort 
	* @return
	*/
	public int updateSolrSort(Solr_Sort solr_Sort){
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
	public List<Solr_Sort> getSolrSortList(Map<String,Object> condition){
		return (List<Solr_Sort>)this.getList("getSolrSortList",condition);
	}
}
