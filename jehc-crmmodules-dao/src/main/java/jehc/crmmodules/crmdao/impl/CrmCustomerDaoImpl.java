package jehc.crmmodules.crmdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.crmmodules.crmdao.CrmCustomerDao;
import jehc.crmmodules.crmmodel.CrmCustomer;

/**
* 客户基础资料 
* 2018-06-27 13:45:48  邓纯杰
*/
@Repository("crmCustomerDao")
public class CrmCustomerDaoImpl  extends BaseDaoImpl implements CrmCustomerDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CrmCustomer> getCrmCustomerListByCondition(Map<String,Object> condition){
		return (List<CrmCustomer>)this.getList("getCrmCustomerListByCondition",condition);
	}
	/**
	* 查询对象
	* @param customerId 
	* @return
	*/
	public CrmCustomer getCrmCustomerById(String customerId){
		return (CrmCustomer)this.get("getCrmCustomerById", customerId);
	}
	/**
	* 添加
	* @param crm_customer 
	* @return
	*/
	public int addCrmCustomer(CrmCustomer crmCustomer){
		return this.add("addCrmCustomer", crmCustomer);
	}
	/**
	* 修改
	* @param crm_customer 
	* @return
	*/
	public int updateCrmCustomer(CrmCustomer crmCustomer){
		return this.update("updateCrmCustomer", crmCustomer);
	}
	/**
	* 修改（根据动态条件）
	* @param crm_customer 
	* @return
	*/
	public int updateCrmCustomerBySelective(CrmCustomer crmCustomer){
		return this.update("updateCrmCustomerBySelective", crmCustomer);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmCustomer(Map<String,Object> condition){
		return this.del("delCrmCustomer", condition);
	}
	/**
	* 批量添加
	* @param crm_customerList 
	* @return
	*/
	public int addBatchCrmCustomer(List<CrmCustomer> crmCustomerList){
		return this.add("addBatchCrmCustomer", crmCustomerList);
	}
	/**
	* 批量修改
	* @param crm_customerList 
	* @return
	*/
	public int updateBatchCrmCustomer(List<CrmCustomer> crmCustomerList){
		return this.update("updateBatchCrmCustomer", crmCustomerList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_customerList 
	* @return
	*/
	public int updateBatchCrmCustomerBySelective(List<CrmCustomer> crmCustomerList){
		return this.update("updateBatchCrmCustomerBySelective", crmCustomerList);
	}
	
	/**
	 * 批量分配客户至客户所属人
	 * @param crmCustomer
	 * @return
	 */
	public int updateCToUser(Map<String, Object> map){
		return this.update("updateCToUser", map);
	}
	
	/**
	 * 客户等级状态更新
	 * @param map
	 * @return
	 */
	public int updateCrmLevelStatus(Map<String, Object> map){
		return this.update("updateCrmLevelStatus", map);
	}
}
