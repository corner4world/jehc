package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtMessageDao;
import jehc.xtmodules.xtmodel.XtMessage;
import jehc.xtmodules.xtservice.XtMessageService;

/**
* 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
@Service("xtMessageService")
public class XtMessageServiceImpl extends BaseService implements XtMessageService{
	@Autowired
	private XtMessageDao xtMessageDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMessage> getXtMessageListByCondition(Map<String,Object> condition){
		try{
			return xtMessageDao.getXtMessageListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_message_id 
	* @return
	*/
	public XtMessage getXtMessageById(String xt_message_id){
		try{
			return xtMessageDao.getXtMessageById(xt_message_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_message 
	* @return
	*/
	public int addXtMessage(XtMessage xt_Message){
		int i = 0;
		try {
			i = xtMessageDao.addXtMessage(xt_Message);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_message 
	* @return
	*/
	public int updateXtMessage(XtMessage xt_Message){
		int i = 0;
		try {
			i = xtMessageDao.updateXtMessage(xt_Message);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMessage(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtMessageDao.delXtMessage(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_messageList 
	* @return
	*/
	public int addBatchXtMessage(List<XtMessage> xt_MessageList){
		int i = 0;
		try {
			i = xtMessageDao.addBatchXtMessage(xt_MessageList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_messageList 
	* @return
	*/
	public int updateBatchXtMessage(List<XtMessage> xt_MessageList){
		int i = 0;
		try {
			i = xtMessageDao.updateBatchXtMessage(xt_MessageList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 更新已读状态
	 * @param condition
	 * @return
	 */
	public int updateRead(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtMessageDao.updateRead(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	public List<XtMessage> getXtMessageCountByCondition(Map<String,Object> condition){
		try{
			return xtMessageDao.getXtMessageCountByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
