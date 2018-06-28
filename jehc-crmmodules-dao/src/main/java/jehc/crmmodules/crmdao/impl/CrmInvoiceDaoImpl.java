package jehc.crmmodules.crmdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.crmmodules.crmdao.CrmInvoiceDao;
import jehc.crmmodules.crmmodel.CrmInvoice;

/**
* 客户发票 
* 2018-06-27 15:13:10  邓纯杰
*/
@Repository("crmInvoiceDao")
public class CrmInvoiceDaoImpl  extends BaseDaoImpl implements CrmInvoiceDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CrmInvoice> getCrmInvoiceListByCondition(Map<String,Object> condition){
		return (List<CrmInvoice>)this.getList("getCrmInvoiceListByCondition",condition);
	}
	/**
	* 查询对象
	* @param invoiceId 
	* @return
	*/
	public CrmInvoice getCrmInvoiceById(String invoiceId){
		return (CrmInvoice)this.get("getCrmInvoiceById", invoiceId);
	}
	/**
	* 添加
	* @param crm_invoice 
	* @return
	*/
	public int addCrmInvoice(CrmInvoice crmInvoice){
		return this.add("addCrmInvoice", crmInvoice);
	}
	/**
	* 修改
	* @param crm_invoice 
	* @return
	*/
	public int updateCrmInvoice(CrmInvoice crmInvoice){
		return this.update("updateCrmInvoice", crmInvoice);
	}
	/**
	* 修改（根据动态条件）
	* @param crm_invoice 
	* @return
	*/
	public int updateCrmInvoiceBySelective(CrmInvoice crmInvoice){
		return this.update("updateCrmInvoiceBySelective", crmInvoice);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmInvoice(Map<String,Object> condition){
		return this.del("delCrmInvoice", condition);
	}
	/**
	* 批量添加
	* @param crm_invoiceList 
	* @return
	*/
	public int addBatchCrmInvoice(List<CrmInvoice> crmInvoiceList){
		return this.add("addBatchCrmInvoice", crmInvoiceList);
	}
	/**
	* 批量修改
	* @param crm_invoiceList 
	* @return
	*/
	public int updateBatchCrmInvoice(List<CrmInvoice> crmInvoiceList){
		return this.update("updateBatchCrmInvoice", crmInvoiceList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_invoiceList 
	* @return
	*/
	public int updateBatchCrmInvoiceBySelective(List<CrmInvoice> crmInvoiceList){
		return this.update("updateBatchCrmInvoiceBySelective", crmInvoiceList);
	}
	
	/**
	 * 根据客户查找唯一发票
	 * @param customerId
	 * @return
	 */
	public CrmInvoice getCrmInvoiceSingleByCustomerId(String customerId){
		return (CrmInvoice)this.get("getCrmInvoiceSingleByCustomerId", customerId);
	}
}
