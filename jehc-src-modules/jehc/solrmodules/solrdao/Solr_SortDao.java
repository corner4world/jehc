package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Sort;

/**
* solr排序 
* 2016-07-08 23:49:58  邓纯杰
*/
public interface Solr_SortDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Sort> getSolrSortListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_sort_id 
	* @return
	*/
	public Solr_Sort getSolrSortById(String solr_sort_id);
	/**
	* 添加
	* @param solr_sort 
	* @return
	*/
	public int addSolrSort(Solr_Sort solr_Sort);
	/**
	* 修改
	* @param solr_sort 
	* @return
	*/
	public int updateSolrSort(Solr_Sort solr_Sort);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrSort(Map<String,Object> condition);
	
	/**
	 * 根据实例编号查找其下面排序字段
	 * @param condition
	 * @return
	 */
	public List<Solr_Sort> getSolrSortList(Map<String,Object> condition);
}
