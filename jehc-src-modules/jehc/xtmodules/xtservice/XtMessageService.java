package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtMessage;

/**
* 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
public interface XtMessageService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMessage> getXtMessageListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_message_id 
	* @return
	*/
	public XtMessage getXtMessageById(String xt_message_id);
	/**
	* 添加
	* @param xt_message 
	* @return
	*/
	public int addXtMessage(XtMessage xt_Message);
	/**
	* 修改
	* @param xt_message 
	* @return
	*/
	public int updateXtMessage(XtMessage xt_Message);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMessage(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_messageList 
	* @return
	*/
	public int addBatchXtMessage(List<XtMessage> xt_MessageList);
	/**
	* 批量修改
	* @param xt_messageList 
	* @return
	*/
	public int updateBatchXtMessage(List<XtMessage> xt_MessageList);
	/**
	 * 更新已读状态
	 * @param condition
	 * @return
	 */
	public int updateRead(Map<String,Object> condition);
	
	/**
	 * 分组统计
	 * @param condition
	 * @return
	 */
	public List<XtMessage> getXtMessageCountByCondition(Map<String,Object> condition);
}
