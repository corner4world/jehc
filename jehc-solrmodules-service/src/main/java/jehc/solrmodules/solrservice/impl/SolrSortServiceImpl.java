package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrSortDao;
import jehc.solrmodules.solrmodel.SolrSort;
import jehc.solrmodules.solrservice.SolrSortService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr排序 
* 2016-07-08 23:49:58  邓纯杰
*/
@Service("solrSortService")
public class SolrSortServiceImpl extends BaseService implements SolrSortService{
	@Autowired
	private SolrSortDao solrSortDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrSort> getSolrSortListByCondition(Map<String,Object> condition){
		try{
			return solrSortDao.getSolrSortListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_sort_id 
	* @return
	*/
	public SolrSort getSolrSortById(String solr_sort_id){
		try{
			return solrSortDao.getSolrSortById(solr_sort_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_sort 
	* @return
	*/
	public int addSolrSort(SolrSort solr_Sort){
		int i = 0;
		try {
			i = solrSortDao.addSolrSort(solr_Sort);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_sort 
	* @return
	*/
	public int updateSolrSort(SolrSort solr_Sort){
		int i = 0;
		try {
			i = solrSortDao.updateSolrSort(solr_Sort);
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
	public int delSolrSort(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrSortDao.delSolrSort(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 根据实例编号查找其下面排序字段
	 * @param condition
	 * @return
	 */
	public List<SolrSort> getSolrSortList(Map<String,Object> condition){
		try{
			return solrSortDao.getSolrSortList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
