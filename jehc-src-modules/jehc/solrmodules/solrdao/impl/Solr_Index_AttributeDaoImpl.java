package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_Index_AttributeDao;
import jehc.solrmodules.solrmodel.Solr_Index_Attribute;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 索引更多属性 
* 2016-07-10 22:19:49  邓纯杰
*/
@Repository("solr_Index_AttributeDao")
public class Solr_Index_AttributeDaoImpl  extends BaseDaoImpl implements Solr_Index_AttributeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Index_Attribute> getSolrIndexAttributeListByCondition(Map<String,Object> condition){
		return (List<Solr_Index_Attribute>)this.getList("getSolrIndexAttributeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_index_attribute_id 
	* @return
	*/
	public Solr_Index_Attribute getSolrIndexAttributeById(String solr_index_attribute_id){
		return (Solr_Index_Attribute)this.get("getSolrIndexAttributeById", solr_index_attribute_id);
	}
	/**
	* 添加
	* @param solr_index_attribute 
	* @return
	*/
	public int addSolrIndexAttribute(Solr_Index_Attribute solr_Index_Attribute){
		return this.add("addSolrIndexAttribute", solr_Index_Attribute);
	}
	/**
	* 修改
	* @param solr_index_attribute 
	* @return
	*/
	public int updateSolrIndexAttribute(Solr_Index_Attribute solr_Index_Attribute){
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
	public List<Solr_Index_Attribute> getSolrIndexAttributeList(Map<String,Object> condition){
		return (List<Solr_Index_Attribute>)this.getList("getSolrIndexAttributeList",condition); 
	}
}
