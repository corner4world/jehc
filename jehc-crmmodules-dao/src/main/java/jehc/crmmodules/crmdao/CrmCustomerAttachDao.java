package jehc.crmmodules.crmdao;
import java.util.List;
import java.util.Map;
import jehc.crmmodules.crmmodel.CrmCustomerAttach;

/**
* 客户附件 
* 2018-06-27 15:17:45  邓纯杰
*/
public interface CrmCustomerAttachDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmCustomerAttach> getCrmCustomerAttachListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param customer_attach_id 
	* @return
	*/
	public CrmCustomerAttach getCrmCustomerAttachById(String customer_attach_id);
	/**
	* 添加
	* @param crm_customer_attach 
	* @return
	*/
	public int addCrmCustomerAttach(CrmCustomerAttach crmCustomerAttach);
	/**
	* 修改
	* @param crm_customer_attach 
	* @return
	*/
	public int updateCrmCustomerAttach(CrmCustomerAttach crmCustomerAttach);
	/**
	* 修改（根据动态条件）
	* @param crm_customer_attach 
	* @return
	*/
	public int updateCrmCustomerAttachBySelective(CrmCustomerAttach crmCustomerAttach);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmCustomerAttach(Map<String,Object> condition);
	/**
	* 批量添加
	* @param crm_customer_attachList 
	* @return
	*/
	public int addBatchCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttachList);
	/**
	* 批量修改
	* @param crm_customer_attachList 
	* @return
	*/
	public int updateBatchCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttachList);
	/**
	* 批量修改（根据动态条件）
	* @param crm_customer_attachList 
	* @return
	*/
	public int updateBatchCrmCustomerAttachBySelective(List<CrmCustomerAttach> crmCustomerAttachList);
}
