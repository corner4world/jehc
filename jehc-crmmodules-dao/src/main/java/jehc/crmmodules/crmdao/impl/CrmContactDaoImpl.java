package jehc.crmmodules.crmdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.crmmodules.crmdao.CrmContactDao;
import jehc.crmmodules.crmmodel.CrmContact;

/**
* 客户联系人 
* 2018-06-27 17:26:15  邓纯杰
*/
@Repository("crmContactDao")
public class CrmContactDaoImpl  extends BaseDaoImpl implements CrmContactDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CrmContact> getCrmContactListByCondition(Map<String,Object> condition){
		return (List<CrmContact>)this.getList("getCrmContactListByCondition",condition);
	}
	/**
	* 查询对象
	* @param contactId 
	* @return
	*/
	public CrmContact getCrmContactById(String contactId){
		return (CrmContact)this.get("getCrmContactById", contactId);
	}
	/**
	* 添加
	* @param crm_contact 
	* @return
	*/
	public int addCrmContact(CrmContact crmContact){
		return this.add("addCrmContact", crmContact);
	}
	/**
	* 修改
	* @param crm_contact 
	* @return
	*/
	public int updateCrmContact(CrmContact crmContact){
		return this.update("updateCrmContact", crmContact);
	}
	/**
	* 修改（根据动态条件）
	* @param crm_contact 
	* @return
	*/
	public int updateCrmContactBySelective(CrmContact crmContact){
		return this.update("updateCrmContactBySelective", crmContact);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmContact(Map<String,Object> condition){
		return this.del("delCrmContact", condition);
	}
	/**
	* 批量添加
	* @param crm_contactList 
	* @return
	*/
	public int addBatchCrmContact(List<CrmContact> crmContactList){
		return this.add("addBatchCrmContact", crmContactList);
	}
	/**
	* 批量修改
	* @param crm_contactList 
	* @return
	*/
	public int updateBatchCrmContact(List<CrmContact> crmContactList){
		return this.update("updateBatchCrmContact", crmContactList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_contactList 
	* @return
	*/
	public int updateBatchCrmContactBySelective(List<CrmContact> crmContactList){
		return this.update("updateBatchCrmContactBySelective", crmContactList);
	}
}
