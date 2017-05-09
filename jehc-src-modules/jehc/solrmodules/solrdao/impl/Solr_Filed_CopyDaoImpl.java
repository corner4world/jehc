package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.Solr_Filed_CopyDao;
import jehc.solrmodules.solrmodel.Solr_Filed_Copy;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* Solr字段拷贝配置 
* 2016-11-14 22:20:39  邓纯杰
*/
@Repository("solr_Filed_CopyDao")
public class Solr_Filed_CopyDaoImpl  extends BaseDaoImpl implements Solr_Filed_CopyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Solr_Filed_Copy> getSolrFiledCopyListByCondition(Map<String,Object> condition){
		return (List<Solr_Filed_Copy>)this.getList("getSolrFiledCopyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_filed_copy_id 
	* @return
	*/
	public Solr_Filed_Copy getSolrFiledCopyById(String solr_filed_copy_id){
		return (Solr_Filed_Copy)this.get("getSolrFiledCopyById", solr_filed_copy_id);
	}
	/**
	* 添加
	* @param solr_filed_copy 
	* @return
	*/
	public int addSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy){
		return this.add("addSolrFiledCopy", solr_Filed_Copy);
	}
	/**
	* 修改
	* @param solr_filed_copy 
	* @return
	*/
	public int updateSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy){
		return this.update("updateSolrFiledCopy", solr_Filed_Copy);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delSolrFiledCopy(Map<String,Object> condition){
		return this.del("delSolrFiledCopy", condition);
	}
	/**
	* 批量添加
	* @param solr_filed_copyList 
	* @return
	*/
	public int addBatchSolrFiledCopy(List<Solr_Filed_Copy> solr_Filed_CopyList){
		return this.add("addBatchSolrFiledCopy", solr_Filed_CopyList);
	}
	/**
	* 批量修改
	* @param solr_filed_copyList 
	* @return
	*/
	public int updateBatchSolrFiledCopy(List<Solr_Filed_Copy> solr_Filed_CopyList){
		return this.update("updateBatchSolrFiledCopy", solr_Filed_CopyList);
	}
}
