package jehc.crmmodules.crmservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.crmmodules.crmservice.CrmContactService;
import jehc.crmmodules.crmdao.CrmContactDao;
import jehc.crmmodules.crmmodel.CrmContact;

/**
* 客户联系人 
* 2018-06-27 17:26:15  邓纯杰
*/
@Service("crmContactService")
public class CrmContactServiceImpl extends BaseService implements CrmContactService{
	@Autowired
	private CrmContactDao crmContactDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmContact> getCrmContactListByCondition(Map<String,Object> condition){
		try{
			return crmContactDao.getCrmContactListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param contactId 
	* @return
	*/
	public CrmContact getCrmContactById(String contactId){
		try{
			CrmContact crmContact = crmContactDao.getCrmContactById(contactId);
			return crmContact;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param crm_contact 
	* @return
	*/
	public int addCrmContact(CrmContact crmContact){
		int i = 0;
		try {
			i = crmContactDao.addCrmContact(crmContact);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param crm_contact 
	* @return
	*/
	public int updateCrmContact(CrmContact crmContact){
		int i = 0;
		try {
			i = crmContactDao.updateCrmContact(crmContact);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param crm_contact 
	* @return
	*/
	public int updateCrmContactBySelective(CrmContact crmContact){
		int i = 0;
		try {
			i = crmContactDao.updateCrmContactBySelective(crmContact);
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
	public int delCrmContact(Map<String,Object> condition){
		int i = 0;
		try {
			i = crmContactDao.delCrmContact(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param crm_contactList 
	* @return
	*/
	public int addBatchCrmContact(List<CrmContact> crmContactList){
		int i = 0;
		try {
			i = crmContactDao.addBatchCrmContact(crmContactList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param crm_contactList 
	* @return
	*/
	public int updateBatchCrmContact(List<CrmContact> crmContactList){
		int i = 0;
		try {
			i = crmContactDao.updateBatchCrmContact(crmContactList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_contactList 
	* @return
	*/
	public int updateBatchCrmContactBySelective(List<CrmContact> crmContactList){
		int i = 0;
		try {
			i = crmContactDao.updateBatchCrmContactBySelective(crmContactList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
