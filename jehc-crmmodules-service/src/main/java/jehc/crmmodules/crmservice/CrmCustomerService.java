package jehc.crmmodules.crmservice;
import java.util.List;
import java.util.Map;

import jehc.crmmodules.crmmodel.CrmCustomer;
import jehc.crmmodules.crmmodel.CrmInvoice;
import jehc.lcmodules.lcmodel.LcApply;

/**
* 客户基础资料 
* 2018-06-27 13:45:48  邓纯杰
*/
public interface CrmCustomerService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmCustomer> getCrmCustomerListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param customerId 
	* @return
	*/
	public CrmCustomer getCrmCustomerById(String customerId);
	/**
	* 添加
	* @param crm_customer 
	* @return
	*/
	public int addCrmCustomer(CrmCustomer crmCustomer,CrmInvoice crmInvoice);
	/**
	* 修改
	* @param crm_customer 
	* @return
	*/
	public int updateCrmCustomer(CrmCustomer crmCustomer,CrmInvoice crmInvoice);
	/**
	* 修改（根据动态条件）
	* @param crm_customer 
	* @return
	*/
	public int updateCrmCustomerBySelective(CrmCustomer crmCustomer);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmCustomer(Map<String,Object> condition);
	/**
	* 批量添加
	* @param crm_customerList 
	* @return
	*/
	public int addBatchCrmCustomer(List<CrmCustomer> crmCustomerList);
	/**
	* 批量修改
	* @param crm_customerList 
	* @return
	*/
	public int updateBatchCrmCustomer(List<CrmCustomer> crmCustomerList);
	/**
	* 批量修改（根据动态条件）
	* @param crm_customerList 
	* @return
	*/
	public int updateBatchCrmCustomerBySelective(List<CrmCustomer> crmCustomerList);
	/**
	 * 批量分配客户至客户所属人
	 * @param crmCustomer
	 * @return
	 */
	public int updateCToUser(Map<String, Object> map);
	
	/**
	 * 客户等级申请
	 * @param crmCustomer
	 * @param lcApply
	 * @return
	 */
	public int addCrmLevelApply(CrmCustomer crmCustomer,LcApply lcApply);
}
