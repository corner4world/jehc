package jehc.solrmodules.solrdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.solrmodules.solrdao.SolrFiledCopyDao;
import jehc.solrmodules.solrmodel.SolrFiledCopy;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* Solr字段拷贝配置 
* 2016-11-14 22:20:39  邓纯杰
*/
@Repository("solrFiledCopyDao")
public class SolrFiledCopyDaoImpl  extends BaseDaoImpl implements SolrFiledCopyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<SolrFiledCopy> getSolrFiledCopyListByCondition(Map<String,Object> condition){
		return (List<SolrFiledCopy>)this.getList("getSolrFiledCopyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param solr_filed_copy_id 
	* @return
	*/
	public SolrFiledCopy getSolrFiledCopyById(String solr_filed_copy_id){
		return (SolrFiledCopy)this.get("getSolrFiledCopyById", solr_filed_copy_id);
	}
	/**
	* 添加
	* @param solr_filed_copy 
	* @return
	*/
	public int addSolrFiledCopy(SolrFiledCopy solr_Filed_Copy){
		return this.add("addSolrFiledCopy", solr_Filed_Copy);
	}
	/**
	* 修改
	* @param solr_filed_copy 
	* @return
	*/
	public int updateSolrFiledCopy(SolrFiledCopy solr_Filed_Copy){
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
	public int addBatchSolrFiledCopy(List<SolrFiledCopy> solr_Filed_CopyList){
		return this.add("addBatchSolrFiledCopy", solr_Filed_CopyList);
	}
	/**
	* 批量修改
	* @param solr_filed_copyList 
	* @return
	*/
	public int updateBatchSolrFiledCopy(List<SolrFiledCopy> solr_Filed_CopyList){
		return this.update("updateBatchSolrFiledCopy", solr_Filed_CopyList);
	}
}
