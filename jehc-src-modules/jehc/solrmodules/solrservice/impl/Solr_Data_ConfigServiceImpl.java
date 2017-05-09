package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.Solr_Data_ConfigDao;
import jehc.solrmodules.solrmodel.Solr_Data_Config;
import jehc.solrmodules.solrservice.Solr_Data_ConfigService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
@Service("solr_Data_ConfigService")
public class Solr_Data_ConfigServiceImpl extends BaseService implements Solr_Data_ConfigService{
	@Autowired
	private Solr_Data_ConfigDao solr_Data_ConfigDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Data_Config> getSolrDataConfigListByCondition(Map<String,Object> condition){
		try{
			return solr_Data_ConfigDao.getSolrDataConfigListByCondition(condition);
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
	public Solr_Data_Config getSolrDataConfigById(String solr_data_config_id){
		try{
			return solr_Data_ConfigDao.getSolrDataConfigById(solr_data_config_id);
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
	public int addSolrDataConfig(Solr_Data_Config solr_Data_Config){
		int i = 0;
		try {
			i = solr_Data_ConfigDao.addSolrDataConfig(solr_Data_Config);
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
	public int updateSolrDataConfig(Solr_Data_Config solr_Data_Config){
		int i = 0;
		try {
			i = solr_Data_ConfigDao.updateSolrDataConfig(solr_Data_Config);
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
			i = solr_Data_ConfigDao.delSolrDataConfig(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
