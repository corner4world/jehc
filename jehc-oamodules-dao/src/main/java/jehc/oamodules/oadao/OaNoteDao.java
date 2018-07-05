package jehc.oamodules.oadao;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaNote;

/**
* 记事本 
* 2018-07-05 19:35:07  邓纯杰
*/
public interface OaNoteDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaNote> getOaNoteListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_note_id 
	* @return
	*/
	public OaNote getOaNoteById(String oa_note_id);
	/**
	* 添加
	* @param oa_note 
	* @return
	*/
	public int addOaNote(OaNote oaNote);
	/**
	* 修改
	* @param oa_note 
	* @return
	*/
	public int updateOaNote(OaNote oaNote);
	/**
	* 修改（根据动态条件）
	* @param oa_note 
	* @return
	*/
	public int updateOaNoteBySelective(OaNote oaNote);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaNote(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_noteList 
	* @return
	*/
	public int addBatchOaNote(List<OaNote> oaNoteList);
	/**
	* 批量修改
	* @param oa_noteList 
	* @return
	*/
	public int updateBatchOaNote(List<OaNote> oaNoteList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_noteList 
	* @return
	*/
	public int updateBatchOaNoteBySelective(List<OaNote> oaNoteList);
}
