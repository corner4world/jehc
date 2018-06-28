package jehc.crmmodules.crmdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.crmmodules.crmdao.CrmCustomerAttachDao;
import jehc.crmmodules.crmmodel.CrmCustomerAttach;

/**
* 客户附件 
* 2018-06-27 15:17:45  邓纯杰
*/
@Repository("crmCustomerAttachDao")
public class CrmCustomerAttachDaoImpl  extends BaseDaoImpl implements CrmCustomerAttachDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CrmCustomerAttach> getCrmCustomerAttachListByCondition(Map<String,Object> condition){
		return (List<CrmCustomerAttach>)this.getList("getCrmCustomerAttachListByCondition",condition);
	}
	/**
	* 查询对象
	* @param customer_attach_id 
	* @return
	*/
	public CrmCustomerAttach getCrmCustomerAttachById(String customer_attach_id){
		return (CrmCustomerAttach)this.get("getCrmCustomerAttachById", customer_attach_id);
	}
	/**
	* 添加
	* @param crm_customer_attach 
	* @return
	*/
	public int addCrmCustomerAttach(CrmCustomerAttach crmCustomerAttach){
		return this.add("addCrmCustomerAttach", crmCustomerAttach);
	}
	/**
	* 修改
	* @param crm_customer_attach 
	* @return
	*/
	public int updateCrmCustomerAttach(CrmCustomerAttach crmCustomerAttach){
		return this.update("updateCrmCustomerAttach", crmCustomerAttach);
	}
	/**
	* 修改（根据动态条件）
	* @param crm_customer_attach 
	* @return
	*/
	public int updateCrmCustomerAttachBySelective(CrmCustomerAttach crmCustomerAttach){
		return this.update("updateCrmCustomerAttachBySelective", crmCustomerAttach);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmCustomerAttach(Map<String,Object> condition){
		return this.del("delCrmCustomerAttach", condition);
	}
	/**
	* 批量添加
	* @param crm_customer_attachList 
	* @return
	*/
	public int addBatchCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttachList){
		return this.add("addBatchCrmCustomerAttach", crmCustomerAttachList);
	}
	/**
	* 批量修改
	* @param crm_customer_attachList 
	* @return
	*/
	public int updateBatchCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttachList){
		return this.update("updateBatchCrmCustomerAttach", crmCustomerAttachList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_customer_attachList 
	* @return
	*/
	public int updateBatchCrmCustomerAttachBySelective(List<CrmCustomerAttach> crmCustomerAttachList){
		return this.update("updateBatchCrmCustomerAttachBySelective", crmCustomerAttachList);
	}
}
