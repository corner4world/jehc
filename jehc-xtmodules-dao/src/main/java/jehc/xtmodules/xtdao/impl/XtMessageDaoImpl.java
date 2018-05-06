package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtMessageDao;
import jehc.xtmodules.xtmodel.XtMessage;

/**
* 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
@Repository("xtMessageDao")
public class XtMessageDaoImpl  extends BaseDaoImpl implements XtMessageDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMessage> getXtMessageListByCondition(Map<String,Object> condition){
		return (List<XtMessage>)this.getList("getXtMessageListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_message_id 
	* @return
	*/
	public XtMessage getXtMessageById(String xt_message_id){
		return (XtMessage)this.get("getXtMessageById", xt_message_id);
	}
	/**
	* 添加
	* @param xt_message 
	* @return
	*/
	public int addXtMessage(XtMessage xt_Message){
		return this.add("addXtMessage", xt_Message);
	}
	/**
	* 修改
	* @param xt_message 
	* @return
	*/
	public int updateXtMessage(XtMessage xt_Message){
		return this.update("updateXtMessage", xt_Message);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMessage(Map<String,Object> condition){
		return this.update("delXtMessage", condition);
	}
	/**
	* 批量添加
	* @param xt_messageList 
	* @return
	*/
	public int addBatchXtMessage(List<XtMessage> xt_MessageList){
		return this.add("addBatchXtMessage", xt_MessageList);
	}
	/**
	* 批量修改
	* @param xt_messageList 
	* @return
	*/
	public int updateBatchXtMessage(List<XtMessage> xt_MessageList){
		return this.update("updateBatchXtMessage", xt_MessageList);
	}
	/**
	 * 更新已读状态
	 * @param condition
	 * @return
	 */
	public int updateRead(Map<String,Object> condition){
		return this.update("updateRead", condition);
	}
	
	/**
	 * 分组统计
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMessage> getXtMessageCountByCondition(Map<String,Object> condition){
		return (List<XtMessage>)this.getList("getXtMessageCountByCondition", condition);
	}
}
