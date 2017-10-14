package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrDataConfigDao;
import jehc.solrmodules.solrmodel.SolrDataConfig;
import jehc.solrmodules.solrservice.SolrDataConfigService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
@Service("solrDataConfigService")
public class SolrDataConfigServiceImpl extends BaseService implements SolrDataConfigService{
	@Autowired
	private SolrDataConfigDao solrDataConfigDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrDataConfig> getSolrDataConfigListByCondition(Map<String,Object> condition){
		try{
			return solrDataConfigDao.getSolrDataConfigListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_data_config_id 
	* @return
	*/
	public SolrDataConfig getSolrDataConfigById(String solr_data_config_id){
		try{
			return solrDataConfigDao.getSolrDataConfigById(solr_data_config_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_data_config 
	* @return
	*/
	public int addSolrDataConfig(SolrDataConfig solr_Data_Config){
		int i = 0;
		try {
			i = solrDataConfigDao.addSolrDataConfig(solr_Data_Config);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_data_config 
	* @return
	*/
	public int updateSolrDataConfig(SolrDataConfig solr_Data_Config){
		int i = 0;
		try {
			i = solrDataConfigDao.updateSolrDataConfig(solr_Data_Config);
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
	public int delSolrDataConfig(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrDataConfigDao.delSolrDataConfig(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
