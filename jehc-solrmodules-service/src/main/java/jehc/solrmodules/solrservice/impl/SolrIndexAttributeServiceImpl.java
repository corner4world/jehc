package jehc.solrmodules.solrservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.solrmodules.solrdao.SolrIndexAttributeDao;
import jehc.solrmodules.solrmodel.SolrIndexAttribute;
import jehc.solrmodules.solrservice.SolrIndexAttributeService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 索引更多属性 
* 2016-07-10 22:19:49  邓纯杰
*/
@Service("solrIndexAttributeService")
public class SolrIndexAttributeServiceImpl extends BaseService implements SolrIndexAttributeService{
	@Autowired
	private SolrIndexAttributeDao solrIndexAttributeDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<SolrIndexAttribute> getSolrIndexAttributeListByCondition(Map<String,Object> condition){
		try{
			return solrIndexAttributeDao.getSolrIndexAttributeListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param solr_index_attribute_id 
	* @return
	*/
	public SolrIndexAttribute getSolrIndexAttributeById(String solr_index_attribute_id){
		try{
			return solrIndexAttributeDao.getSolrIndexAttributeById(solr_index_attribute_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param solr_index_attribute 
	* @return
	*/
	public int addSolrIndexAttribute(SolrIndexAttribute solr_Index_Attribute){
		int i = 0;
		try {
			i = solrIndexAttributeDao.addSolrIndexAttribute(solr_Index_Attribute);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param solr_index_attribute 
	* @return
	*/
	public int updateSolrIndexAttribute(SolrIndexAttribute solr_Index_Attribute){
		int i = 0;
		try {
			i = solrIndexAttributeDao.updateSolrIndexAttribute(solr_Index_Attribute);
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
	public int delSolrIndexAttribute(Map<String,Object> condition){
		int i = 0;
		try {
			i = solrIndexAttributeDao.delSolrIndexAttribute(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 根据实例编号查找其下面属性字段
	 * @param condition
	 * @return
	 */
	public List<SolrIndexAttribute> getSolrIndexAttributeList(Map<String,Object> condition){
		try{
			return solrIndexAttributeDao.getSolrIndexAttributeList(condition);
		} catch (Exception e) {
			e.printStackTrace();
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
