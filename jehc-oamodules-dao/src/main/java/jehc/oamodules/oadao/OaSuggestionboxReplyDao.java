package jehc.oamodules.oadao;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaSuggestionboxReply;

/**
* 意见回复 
* 2018-07-08 10:28:44  邓纯杰
*/
public interface OaSuggestionboxReplyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaSuggestionboxReply> getOaSuggestionboxReplyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_suggestionbox_replyID 
	* @return
	*/
	public OaSuggestionboxReply getOaSuggestionboxReplyById(String oa_suggestionbox_replyID);
	/**
	* 添加
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int addOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply);
	/**
	* 修改
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int updateOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply);
	/**
	* 修改（根据动态条件）
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int updateOaSuggestionboxReplyBySelective(OaSuggestionboxReply oaSuggestionboxReply);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaSuggestionboxReply(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int addBatchOaSuggestionboxReply(List<OaSuggestionboxReply> oaSuggestionboxReplyList);
	/**
	* 批量修改
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int updateBatchOaSuggestionboxReply(List<OaSuggestionboxReply> oaSuggestionboxReplyList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int updateBatchOaSuggestionboxReplyBySelective(List<OaSuggestionboxReply> oaSuggestionboxReplyList);
}
