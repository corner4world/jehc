package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_MessageDao;
import jehc.xtmodules.xtmodel.Xt_Message;
import jehc.xtmodules.xtservice.Xt_MessageService;

/**
* 短消息 
* 2016-10-20 17:49:40  邓纯杰
*/
@Service("xt_MessageService")
public class Xt_MessageServiceImpl extends BaseService implements Xt_MessageService{
	@Autowired
	private Xt_MessageDao xt_MessageDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Message> getXtMessageListByCondition(Map<String,Object> condition){
		try{
			return xt_MessageDao.getXtMessageListByCondition(condition);
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
	public Xt_Message getXtMessageById(String xt_message_id){
		try{
			return xt_MessageDao.getXtMessageById(xt_message_id);
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
	public int addXtMessage(Xt_Message xt_Message){
		int i = 0;
		try {
			i = xt_MessageDao.addXtMessage(xt_Message);
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
	public int updateXtMessage(Xt_Message xt_Message){
		int i = 0;
		try {
			i = xt_MessageDao.updateXtMessage(xt_Message);
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
			i = xt_MessageDao.delXtMessage(condition);
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
	public int addBatchXtMessage(List<Xt_Message> xt_MessageList){
		int i = 0;
		try {
			i = xt_MessageDao.addBatchXtMessage(xt_MessageList);
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
	public int updateBatchXtMessage(List<Xt_Message> xt_MessageList){
		int i = 0;
		try {
			i = xt_MessageDao.updateBatchXtMessage(xt_MessageList);
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
			i = xt_MessageDao.updateRead(condition);
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
	public List<Xt_Message> getXtMessageCountByCondition(Map<String,Object> condition){
		try{
			return xt_MessageDao.getXtMessageCountByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
