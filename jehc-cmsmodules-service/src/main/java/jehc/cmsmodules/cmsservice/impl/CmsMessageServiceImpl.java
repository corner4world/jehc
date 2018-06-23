package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsMessageService;
import jehc.cmsmodules.cmsdao.CmsMessageDao;
import jehc.cmsmodules.cmsmodel.CmsMessage;

/**
* 内容发布平台在线留言 
* 2018-06-10 14:51:17  邓纯杰
*/
@Service("cmsMessageService")
public class CmsMessageServiceImpl extends BaseService implements CmsMessageService{
	@Autowired
	private CmsMessageDao cmsMessageDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsMessage> getCmsMessageListByCondition(Map<String,Object> condition){
		try{
			return cmsMessageDao.getCmsMessageListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_message_id 
	* @return
	*/
	public CmsMessage getCmsMessageById(String cms_message_id){
		try{
			CmsMessage cmsMessage = cmsMessageDao.getCmsMessageById(cms_message_id);
			return cmsMessage;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_message 
	* @return
	*/
	public int addCmsMessage(CmsMessage cmsMessage){
		int i = 0;
		try {
			i = cmsMessageDao.addCmsMessage(cmsMessage);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_message 
	* @return
	*/
	public int updateCmsMessage(CmsMessage cmsMessage){
		int i = 0;
		try {
			i = cmsMessageDao.updateCmsMessage(cmsMessage);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_message 
	* @return
	*/
	public int updateCmsMessageBySelective(CmsMessage cmsMessage){
		int i = 0;
		try {
			i = cmsMessageDao.updateCmsMessageBySelective(cmsMessage);
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
	public int delCmsMessage(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsMessageDao.delCmsMessage(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_messageList 
	* @return
	*/
	public int addBatchCmsMessage(List<CmsMessage> cmsMessageList){
		int i = 0;
		try {
			i = cmsMessageDao.addBatchCmsMessage(cmsMessageList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_messageList 
	* @return
	*/
	public int updateBatchCmsMessage(List<CmsMessage> cmsMessageList){
		int i = 0;
		try {
			i = cmsMessageDao.updateBatchCmsMessage(cmsMessageList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_messageList 
	* @return
	*/
	public int updateBatchCmsMessageBySelective(List<CmsMessage> cmsMessageList){
		int i = 0;
		try {
			i = cmsMessageDao.updateBatchCmsMessageBySelective(cmsMessageList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
