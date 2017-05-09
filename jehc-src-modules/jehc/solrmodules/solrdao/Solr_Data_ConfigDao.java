package jehc.solrmodules.solrdao;
import java.util.List;
import java.util.Map;

import jehc.solrmodules.solrmodel.Solr_Data_Config;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
public interface Solr_Data_ConfigDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Solr_Data_Config> getSolrDataConfigListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param solr_data_config_id 
	* @return
	*/
	public Solr_Data_Config getSolrDataConfigById(String solr_data_config_id);
	/**
	* 添加
	* @param solr_data_config 
	* @return
	*/
	public int addSolrDataConfig(Solr_Data_Config solr_Data_Config);
	/**
	* 修改
	* @param solr_data_config 
	* @return
	*/
	public int updateSolrDataConfig(Solr_Data_Config solr_Data_Config);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrDataConfig(Map<String,Object> condition);
}
