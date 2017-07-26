package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrUrlDao;
import jehc.solrmodules.solrmodel.SolrUrl;
import jehc.solrmodules.solrservice.SolrUrlService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
@Service("solrUrlService")
public class SolrUrlServiceImpl extends BaseService implements SolrUrlService{
	@Autowired
	private SolrUrlDao solrUrlDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrUrl> getSolrUrlListByCondition(Map<String,Object> condition){
		try{
			return solrUrlDao.getSolrUrlListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_url_id 
	* @return
	*/
	public SolrUrl getSolrUrlById(String solr_url_id){
		try{
			return solrUrlDao.getSolrUrlById(solr_url_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_url 
	* @return
	*/
	public int addSolrUrl(SolrUrl solr_Url){
		int i = 0;
		try {
			i = solrUrlDao.addSolrUrl(solr_Url);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_url 
	* @return
	*/
	public int updateSolrUrl(SolrUrl solr_Url){
		int i = 0;
		try {
			i = solrUrlDao.updateSolrUrl(solr_Url);
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
	public int delSolrUrl(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrUrlDao.delSolrUrl(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
