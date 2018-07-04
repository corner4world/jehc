package jehc.oamodules.oadao;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaNoteClassify;

/**
* 记事本分类 
* 2018-07-04 21:06:37  邓纯杰
*/
public interface OaNoteClassifyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaNoteClassify> getOaNoteClassifyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_note_classify_id 
	* @return
	*/
	public OaNoteClassify getOaNoteClassifyById(String oa_note_classify_id);
	/**
	* 添加
	* @param oa_note_classify 
	* @return
	*/
	public int addOaNoteClassify(OaNoteClassify oaNoteClassify);
	/**
	* 修改
	* @param oa_note_classify 
	* @return
	*/
	public int updateOaNoteClassify(OaNoteClassify oaNoteClassify);
	/**
	* 修改（根据动态条件）
	* @param oa_note_classify 
	* @return
	*/
	public int updateOaNoteClassifyBySelective(OaNoteClassify oaNoteClassify);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaNoteClassify(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_note_classifyList 
	* @return
	*/
	public int addBatchOaNoteClassify(List<OaNoteClassify> oaNoteClassifyList);
	/**
	* 批量修改
	* @param oa_note_classifyList 
	* @return
	*/
	public int updateBatchOaNoteClassify(List<OaNoteClassify> oaNoteClassifyList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_note_classifyList 
	* @return
	*/
	public int updateBatchOaNoteClassifyBySelective(List<OaNoteClassify> oaNoteClassifyList);
}
