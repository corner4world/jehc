package jehc.oamodules.oaservice;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaMplanClassify;

/**
* 个人计划分类 
* 2018-07-09 20:18:35  邓纯杰
*/
public interface OaMplanClassifyService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaMplanClassify> getOaMplanClassifyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_mplan_classify_id 
	* @return
	*/
	public OaMplanClassify getOaMplanClassifyById(String oa_mplan_classify_id);
	/**
	* 添加
	* @param oa_mplan_classify 
	* @return
	*/
	public int addOaMplanClassify(OaMplanClassify oaMplanClassify);
	/**
	* 修改
	* @param oa_mplan_classify 
	* @return
	*/
	public int updateOaMplanClassify(OaMplanClassify oaMplanClassify);
	/**
	* 修改（根据动态条件）
	* @param oa_mplan_classify 
	* @return
	*/
	public int updateOaMplanClassifyBySelective(OaMplanClassify oaMplanClassify);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaMplanClassify(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int addBatchOaMplanClassify(List<OaMplanClassify> oaMplanClassifyList);
	/**
	* 批量修改
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int updateBatchOaMplanClassify(List<OaMplanClassify> oaMplanClassifyList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int updateBatchOaMplanClassifyBySelective(List<OaMplanClassify> oaMplanClassifyList);
}
