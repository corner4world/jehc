package jehc.oamodules.oaservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.oamodules.oaservice.OaSuggestionboxReplyService;
import jehc.oamodules.oadao.OaSuggestionboxDao;
import jehc.oamodules.oadao.OaSuggestionboxReplyDao;
import jehc.oamodules.oamodel.OaSuggestionbox;
import jehc.oamodules.oamodel.OaSuggestionboxReply;

/**
* 意见回复 
* 2018-07-08 10:28:44  邓纯杰
*/
@Service("oaSuggestionboxReplyService")
public class OaSuggestionboxReplyServiceImpl extends BaseService implements OaSuggestionboxReplyService{
	@Autowired
	private OaSuggestionboxReplyDao oaSuggestionboxReplyDao;
	@Autowired
	private OaSuggestionboxDao oaSuggestionboxDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaSuggestionboxReply> getOaSuggestionboxReplyListByCondition(Map<String,Object> condition){
		try{
			return oaSuggestionboxReplyDao.getOaSuggestionboxReplyListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param oa_suggestionbox_replyID 
	* @return
	*/
	public OaSuggestionboxReply getOaSuggestionboxReplyById(String oa_suggestionbox_replyID){
		try{
			OaSuggestionboxReply oaSuggestionboxReply = oaSuggestionboxReplyDao.getOaSuggestionboxReplyById(oa_suggestionbox_replyID);
			return oaSuggestionboxReply;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int addOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply){
		int i = 0;
		try {
			oaSuggestionboxReplyDao.addOaSuggestionboxReply(oaSuggestionboxReply);
			OaSuggestionbox oaSuggestionbox = new OaSuggestionbox();
			oaSuggestionbox.setOa_suggestionbox_id(oaSuggestionboxReply.getOa_suggestionbox_id());
			oaSuggestionbox.setState(1);
			i = oaSuggestionboxDao.updateOaSuggestionboxBySelective(oaSuggestionbox);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int updateOaSuggestionboxReply(OaSuggestionboxReply oaSuggestionboxReply){
		int i = 0;
		try {
			i = oaSuggestionboxReplyDao.updateOaSuggestionboxReply(oaSuggestionboxReply);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param oa_suggestionbox_reply 
	* @return
	*/
	public int updateOaSuggestionboxReplyBySelective(OaSuggestionboxReply oaSuggestionboxReply){
		int i = 0;
		try {
			i = oaSuggestionboxReplyDao.updateOaSuggestionboxReplyBySelective(oaSuggestionboxReply);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaSuggestionboxReply(Map<String,Object> condition){
		int i = 0;
		try {
			i = oaSuggestionboxReplyDao.delOaSuggestionboxReply(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int addBatchOaSuggestionboxReply(List<OaSuggestionboxReply> oaSuggestionboxReplyList){
		int i = 0;
		try {
			i = oaSuggestionboxReplyDao.addBatchOaSuggestionboxReply(oaSuggestionboxReplyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int updateBatchOaSuggestionboxReply(List<OaSuggestionboxReply> oaSuggestionboxReplyList){
		int i = 0;
		try {
			i = oaSuggestionboxReplyDao.updateBatchOaSuggestionboxReply(oaSuggestionboxReplyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_suggestionbox_replyList 
	* @return
	*/
	public int updateBatchOaSuggestionboxReplyBySelective(List<OaSuggestionboxReply> oaSuggestionboxReplyList){
		int i = 0;
		try {
			i = oaSuggestionboxReplyDao.updateBatchOaSuggestionboxReplyBySelective(oaSuggestionboxReplyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
