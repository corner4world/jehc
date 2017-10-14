package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrDataConfigDao;
import jehc.solrmodules.solrmodel.SolrDataConfig;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
@Repository("solrDataConfigDao")
public class SolrDataConfigDaoImpl  extends BaseDaoImpl implements SolrDataConfigDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrDataConfig> getSolrDataConfigListByCondition(Map<String,Object> condition){
		return (List<SolrDataConfig>)this.getList("getSolrDataConfigListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_data_config_id 
	* @return
	*/
	public SolrDataConfig getSolrDataConfigById(String solr_data_config_id){
		return (SolrDataConfig)this.get("getSolrDataConfigById", solr_data_config_id);
	}
	/**
	* 添加
	* @param solr_data_config 
	* @return
	*/
	public int addSolrDataConfig(SolrDataConfig solr_Data_Config){
		return this.add("addSolrDataConfig", solr_Data_Config);
	}
	/**
	* 修改
	* @param solr_data_config 
	* @return
	*/
	public int updateSolrDataConfig(SolrDataConfig solr_Data_Config){
		return this.update("updateSolrDataConfig", solr_Data_Config);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrDataConfig(Map<String,Object> condition){
		return this.del("delSolrDataConfig", condition);
	}
}
