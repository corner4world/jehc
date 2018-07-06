package jehc.oamodules.oadao;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaSuggestionbox;

/**
* 意见申诉 
* 2018-07-06 20:11:52  邓纯杰
*/
public interface OaSuggestionboxDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaSuggestionbox> getOaSuggestionboxListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_suggestionbox_id 
	* @return
	*/
	public OaSuggestionbox getOaSuggestionboxById(String oa_suggestionbox_id);
	/**
	* 添加
	* @param oa_suggestionbox 
	* @return
	*/
	public int addOaSuggestionbox(OaSuggestionbox oaSuggestionbox);
	/**
	* 修改
	* @param oa_suggestionbox 
	* @return
	*/
	public int updateOaSuggestionbox(OaSuggestionbox oaSuggestionbox);
	/**
	* 修改（根据动态条件）
	* @param oa_suggestionbox 
	* @return
	*/
	public int updateOaSuggestionboxBySelective(OaSuggestionbox oaSuggestionbox);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaSuggestionbox(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_suggestionboxList 
	* @return
	*/
	public int addBatchOaSuggestionbox(List<OaSuggestionbox> oaSuggestionboxList);
	/**
	* 批量修改
	* @param oa_suggestionboxList 
	* @return
	*/
	public int updateBatchOaSuggestionbox(List<OaSuggestionbox> oaSuggestionboxList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_suggestionboxList 
	* @return
	*/
	public int updateBatchOaSuggestionboxBySelective(List<OaSuggestionbox> oaSuggestionboxList);
}
