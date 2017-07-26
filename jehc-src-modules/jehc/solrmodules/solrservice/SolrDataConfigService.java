package jehc.solrmodules.solrservice;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.SolrDataConfig;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
public interface SolrDataConfigService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrDataConfig> getSolrDataConfigListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_data_config_id 
	* @return
	*/
	public SolrDataConfig getSolrDataConfigById(String solr_data_config_id);
	/**
	* 添加
	* @param solr_data_config 
	* @return
	*/
	public int addSolrDataConfig(SolrDataConfig solr_Data_Config);
	/**
	* 修改
	* @param solr_data_config 
	* @return
	*/
	public int updateSolrDataConfig(SolrDataConfig solr_Data_Config);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrDataConfig(Map<String,Object> condition);
}
