package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_Data_ConfigDao;
import jehc.solrmodules.solrmodel.Solr_Data_Config;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
@Repository("solr_Data_ConfigDao")
public class Solr_Data_ConfigDaoImpl  extends BaseDaoImpl implements Solr_Data_ConfigDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Data_Config> getSolrDataConfigListByCondition(Map<String,Object> condition){
		return (List<Solr_Data_Config>)this.getList("getSolrDataConfigListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_data_config_id 
	* @return
	*/
	public Solr_Data_Config getSolrDataConfigById(String solr_data_config_id){
		return (Solr_Data_Config)this.get("getSolrDataConfigById", solr_data_config_id);
	}
	/**
	* 添加
	* @param solr_data_config 
	* @return
	*/
	public int addSolrDataConfig(Solr_Data_Config solr_Data_Config){
		return this.add("addSolrDataConfig", solr_Data_Config);
	}
	/**
	* 修改
	* @param solr_data_config 
	* @return
	*/
	public int updateSolrDataConfig(Solr_Data_Config solr_Data_Config){
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
