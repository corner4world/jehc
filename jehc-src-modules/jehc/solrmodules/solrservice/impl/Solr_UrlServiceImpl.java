package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_UrlDao;
import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.solrmodules.solrservice.Solr_UrlService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
@Service("solr_UrlService")
public class Solr_UrlServiceImpl extends BaseService implements Solr_UrlService{
	@Autowired
	private Solr_UrlDao solr_UrlDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Url> getSolrUrlListByCondition(Map<String,Object> condition){
		try{
			return solr_UrlDao.getSolrUrlListByCondition(condition);
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
	public Solr_Url getSolrUrlById(String solr_url_id){
		try{
			return solr_UrlDao.getSolrUrlById(solr_url_id);
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
	public int addSolrUrl(Solr_Url solr_Url){
		int i = 0;
		try {
			i = solr_UrlDao.addSolrUrl(solr_Url);
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
	public int updateSolrUrl(Solr_Url solr_Url){
		int i = 0;
		try {
			i = solr_UrlDao.updateSolrUrl(solr_Url);
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
			i = solr_UrlDao.delSolrUrl(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
