package jehc.crmmodules.crmdao;
import java.util.List;
import java.util.Map;
import jehc.crmmodules.crmmodel.CrmContact;

/**
* 客户联系人 
* 2018-06-27 17:26:15  邓纯杰
*/
public interface CrmContactDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmContact> getCrmContactListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param contactId 
	* @return
	*/
	public CrmContact getCrmContactById(String contactId);
	/**
	* 添加
	* @param crm_contact 
	* @return
	*/
	public int addCrmContact(CrmContact crmContact);
	/**
	* 修改
	* @param crm_contact 
	* @return
	*/
	public int updateCrmContact(CrmContact crmContact);
	/**
	* 修改（根据动态条件）
	* @param crm_contact 
	* @return
	*/
	public int updateCrmContactBySelective(CrmContact crmContact);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmContact(Map<String,Object> condition);
	/**
	* 批量添加
	* @param crm_contactList 
	* @return
	*/
	public int addBatchCrmContact(List<CrmContact> crmContactList);
	/**
	* 批量修改
	* @param crm_contactList 
	* @return
	*/
	public int updateBatchCrmContact(List<CrmContact> crmContactList);
	/**
	* 批量修改（根据动态条件）
	* @param crm_contactList 
	* @return
	*/
	public int updateBatchCrmContactBySelective(List<CrmContact> crmContactList);
}
