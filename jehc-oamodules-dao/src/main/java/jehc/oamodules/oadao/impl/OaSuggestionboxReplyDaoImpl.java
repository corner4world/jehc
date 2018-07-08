package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaSuggestionboxReplyDao;
import jehc.oamodules.oamodel.OaSuggestionboxReply;

/**
* 意见回复 
* 2018-07-08 10:28:44  邓纯杰
*/
@Repository("oaSuggestionboxReplyDao")
public class OaSuggestionboxReplyDaoImpl  extends BaseDaoImpl implements OaSuggestionboxReplyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaSuggestionboxReply> getOaSuggestionboxReplyListByCondition(Map<String,Object> condition){
		return (List<OaSuggestionboxReply>)this.getList("getOaSuggestionboxReplyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_suggestionbox_replyID 
	* @return
	*/
	public OaSuggestionboxReply getOaSuggestionboxReplyById(String oa_suggestionbox_replyID){
		return (OaSuggestionboxReply)this.get("getOaSuggestionboxReplyById", oa_suggestionbox_replyID);
	}
	/**
	* 添加
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int addOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply){
		return this.add("addOaSuggestionboxReply", oaSuggestionboxReply);
	}
	/**
	* 修改
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int updateOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply){
		return this.update("updateOaSuggestionboxReply", oaSuggestionboxReply);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int updateOaSuggestionboxReplyBySelective(OaSuggestionboxReply oaSuggestionboxReply){
		return this.update("updateOaSuggestionboxReplyBySelective", oaSuggestionboxReply);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaSuggestionboxReply(Map<String,Object> condition){
		return this.del("delOaSuggestionboxReply", condition);
	}
	/**
	* 批量添加
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int addBatchOaSuggestionboxReply(List<OaSuggestionboxReply> oaSuggestionboxReplyList){
		return this.add("addBatchOaSuggestionboxReply", oaSuggestionboxReplyList);
	}
	/**
	* 批量修改
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int updateBatchOaSuggestionboxReply(List<OaSuggestionboxReply> oaSuggestionboxReplyList){
		return this.update("updateBatchOaSuggestionboxReply", oaSuggestionboxReplyList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int updateBatchOaSuggestionboxReplyBySelective(List<OaSuggestionboxReply> oaSuggestionboxReplyList){
		return this.update("updateBatchOaSuggestionboxReplyBySelective", oaSuggestionboxReplyList);
	}
}
