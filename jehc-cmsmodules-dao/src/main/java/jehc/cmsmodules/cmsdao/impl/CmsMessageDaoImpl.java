package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsMessageDao;
import jehc.cmsmodules.cmsmodel.CmsMessage;

/**
* 内容发布平台在线留言 
* 2018-06-10 14:51:17  邓纯杰
*/
@Repository("cmsMessageDao")
public class CmsMessageDaoImpl  extends BaseDaoImpl implements CmsMessageDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsMessage> getCmsMessageListByCondition(Map<String,Object> condition){
		return (List<CmsMessage>)this.getList("getCmsMessageListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_message_id 
	* @return
	*/
	public CmsMessage getCmsMessageById(String cms_message_id){
		return (CmsMessage)this.get("getCmsMessageById", cms_message_id);
	}
	/**
	* 添加
	* @param cms_message 
	* @return
	*/
	public int addCmsMessage(CmsMessage cmsMessage){
		return this.add("addCmsMessage", cmsMessage);
	}
	/**
	* 修改
	* @param cms_message 
	* @return
	*/
	public int updateCmsMessage(CmsMessage cmsMessage){
		return this.update("updateCmsMessage", cmsMessage);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_message 
	* @return
	*/
	public int updateCmsMessageBySelective(CmsMessage cmsMessage){
		return this.update("updateCmsMessageBySelective", cmsMessage);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsMessage(Map<String,Object> condition){
		return this.del("delCmsMessage", condition);
	}
	/**
	* 批量添加
	* @param cms_messageList 
	* @return
	*/
	public int addBatchCmsMessage(List<CmsMessage> cmsMessageList){
		return this.add("addBatchCmsMessage", cmsMessageList);
	}
	/**
	* 批量修改
	* @param cms_messageList 
	* @return
	*/
	public int updateBatchCmsMessage(List<CmsMessage> cmsMessageList){
		return this.update("updateBatchCmsMessage", cmsMessageList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_messageList 
	* @return
	*/
	public int updateBatchCmsMessageBySelective(List<CmsMessage> cmsMessageList){
		return this.update("updateBatchCmsMessageBySelective", cmsMessageList);
	}
}
