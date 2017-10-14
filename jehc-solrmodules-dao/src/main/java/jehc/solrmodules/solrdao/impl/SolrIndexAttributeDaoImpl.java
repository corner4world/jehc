package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrIndexAttributeDao;
import jehc.solrmodules.solrmodel.SolrIndexAttribute;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 索引更多属性 
* 2016-07-10 22:19:49  邓纯杰
*/
@Repository("solrIndexAttributeDao")
public class SolrIndexAttributeDaoImpl  extends BaseDaoImpl implements SolrIndexAttributeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrIndexAttribute> getSolrIndexAttributeListByCondition(Map<String,Object> condition){
		return (List<SolrIndexAttribute>)this.getList("getSolrIndexAttributeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_attribute_id 
	* @return
	*/
	public SolrIndexAttribute getSolrIndexAttributeById(String solr_index_attribute_id){
		return (SolrIndexAttribute)this.get("getSolrIndexAttributeById", solr_index_attribute_id);
	}
	/**
	* 添加
	* @param solr_index_attribute 
	* @return
	*/
	public int addSolrIndexAttribute(SolrIndexAttribute solr_Index_Attribute){
		return this.add("addSolrIndexAttribute", solr_Index_Attribute);
	}
	/**
	* 修改
	* @param solr_index_attribute 
	* @return
	*/
	public int updateSolrIndexAttribute(SolrIndexAttribute solr_Index_Attribute){
		return this.update("updateSolrIndexAttribute", solr_Index_Attribute);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrIndexAttribute(Map<String,Object> condition){
		return this.del("delSolrIndexAttribute", condition);
	}
	
	/**
	 * 根据实例编号查找其下面属性字段
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SolrIndexAttribute> getSolrIndexAttributeList(Map<String,Object> condition){
		return (List<SolrIndexAttribute>)this.getList("getSolrIndexAttributeList",condition); 
	}
}
