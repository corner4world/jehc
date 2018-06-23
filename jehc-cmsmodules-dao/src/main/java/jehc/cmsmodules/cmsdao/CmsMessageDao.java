package jehc.cmsmodules.cmsdao;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsMessage;

/**
* 内容发布平台在线留言 
* 2018-06-10 14:51:17  邓纯杰
*/
public interface CmsMessageDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsMessage> getCmsMessageListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_message_id 
	* @return
	*/
	public CmsMessage getCmsMessageById(String cms_message_id);
	/**
	* 添加
	* @param cms_message 
	* @return
	*/
	public int addCmsMessage(CmsMessage cmsMessage);
	/**
	* 修改
	* @param cms_message 
	* @return
	*/
	public int updateCmsMessage(CmsMessage cmsMessage);
	/**
	* 修改（根据动态条件）
	* @param cms_message 
	* @return
	*/
	public int updateCmsMessageBySelective(CmsMessage cmsMessage);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsMessage(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_messageList 
	* @return
	*/
	public int addBatchCmsMessage(List<CmsMessage> cmsMessageList);
	/**
	* 批量修改
	* @param cms_messageList 
	* @return
	*/
	public int updateBatchCmsMessage(List<CmsMessage> cmsMessageList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_messageList 
	* @return
	*/
	public int updateBatchCmsMessageBySelective(List<CmsMessage> cmsMessageList);
}
