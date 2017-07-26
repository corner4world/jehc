package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrIndexDao;
import jehc.solrmodules.solrmodel.SolrIndex;
import jehc.solrmodules.solrservice.SolrIndexService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
@Service("solrIndexService")
public class SolrIndexServiceImpl extends BaseService implements SolrIndexService{
	@Autowired
	private SolrIndexDao solrIndexDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrIndex> getSolrIndexListByCondition(Map<String,Object> condition){
		try{
			return solrIndexDao.getSolrIndexListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_index_id 
	* @return
	*/
	public SolrIndex getSolrIndexById(String solr_index_id){
		try{
			return solrIndexDao.getSolrIndexById(solr_index_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_index 
	* @return
	*/
	public int addSolrIndex(SolrIndex solr_Index){
		int i = 0;
		try {
			i = solrIndexDao.addSolrIndex(solr_Index);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_index 
	* @return
	*/
	public int updateSolrIndex(SolrIndex solr_Index){
		int i = 0;
		try {
			i = solrIndexDao.updateSolrIndex(solr_Index);
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
	public int delSolrIndex(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrIndexDao.delSolrIndex(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
