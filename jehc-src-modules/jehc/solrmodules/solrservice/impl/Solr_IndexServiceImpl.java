package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_IndexDao;
import jehc.solrmodules.solrmodel.Solr_Index;
import jehc.solrmodules.solrservice.Solr_IndexService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
@Service("solr_IndexService")
public class Solr_IndexServiceImpl extends BaseService implements Solr_IndexService{
	@Autowired
	private Solr_IndexDao solr_IndexDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Index> getSolrIndexListByCondition(Map<String,Object> condition){
		try{
			return solr_IndexDao.getSolrIndexListByCondition(condition);
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
	public Solr_Index getSolrIndexById(String solr_index_id){
		try{
			return solr_IndexDao.getSolrIndexById(solr_index_id);
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
	public int addSolrIndex(Solr_Index solr_Index){
		int i = 0;
		try {
			i = solr_IndexDao.addSolrIndex(solr_Index);
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
	public int updateSolrIndex(Solr_Index solr_Index){
		int i = 0;
		try {
			i = solr_IndexDao.updateSolrIndex(solr_Index);
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
			i = solr_IndexDao.delSolrIndex(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
