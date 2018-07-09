package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaMplanClassifyDao;
import jehc.oamodules.oamodel.OaMplanClassify;

/**
* 个人计划分类 
* 2018-07-09 20:18:35  邓纯杰
*/
@Repository("oaMplanClassifyDao")
public class OaMplanClassifyDaoImpl  extends BaseDaoImpl implements OaMplanClassifyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaMplanClassify> getOaMplanClassifyListByCondition(Map<String,Object> condition){
		return (List<OaMplanClassify>)this.getList("getOaMplanClassifyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_mplan_classify_id 
	* @return
	*/
	public OaMplanClassify getOaMplanClassifyById(String oa_mplan_classify_id){
		return (OaMplanClassify)this.get("getOaMplanClassifyById", oa_mplan_classify_id);
	}
	/**
	* 添加
	* @param oa_mplan_classify 
	* @return
	*/
	public int addOaMplanClassify(OaMplanClassify oaMplanClassify){
		return this.add("addOaMplanClassify", oaMplanClassify);
	}
	/**
	* 修改
	* @param oa_mplan_classify 
	* @return
	*/
	public int updateOaMplanClassify(OaMplanClassify oaMplanClassify){
		return this.update("updateOaMplanClassify", oaMplanClassify);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_mplan_classify 
	* @return
	*/
	public int updateOaMplanClassifyBySelective(OaMplanClassify oaMplanClassify){
		return this.update("updateOaMplanClassifyBySelective", oaMplanClassify);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaMplanClassify(Map<String,Object> condition){
		return this.del("delOaMplanClassify", condition);
	}
	/**
	* 批量添加
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int addBatchOaMplanClassify(List<OaMplanClassify> oaMplanClassifyList){
		return this.add("addBatchOaMplanClassify", oaMplanClassifyList);
	}
	/**
	* 批量修改
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int updateBatchOaMplanClassify(List<OaMplanClassify> oaMplanClassifyList){
		return this.update("updateBatchOaMplanClassify", oaMplanClassifyList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int updateBatchOaMplanClassifyBySelective(List<OaMplanClassify> oaMplanClassifyList){
		return this.update("updateBatchOaMplanClassifyBySelective", oaMplanClassifyList);
	}
}
