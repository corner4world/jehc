package jehc.crmmodules.crmdao;
import java.util.List;
import java.util.Map;
import jehc.crmmodules.crmmodel.CrmInvoice;

/**
* 客户发票 
* 2018-06-27 15:13:10  邓纯杰
*/
public interface CrmInvoiceDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmInvoice> getCrmInvoiceListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param invoiceId 
	* @return
	*/
	public CrmInvoice getCrmInvoiceById(String invoiceId);
	/**
	* 添加
	* @param crm_invoice 
	* @return
	*/
	public int addCrmInvoice(CrmInvoice crmInvoice);
	/**
	* 修改
	* @param crm_invoice 
	* @return
	*/
	public int updateCrmInvoice(CrmInvoice crmInvoice);
	/**
	* 修改（根据动态条件）
	* @param crm_invoice 
	* @return
	*/
	public int updateCrmInvoiceBySelective(CrmInvoice crmInvoice);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmInvoice(Map<String,Object> condition);
	/**
	* 批量添加
	* @param crm_invoiceList 
	* @return
	*/
	public int addBatchCrmInvoice(List<CrmInvoice> crmInvoiceList);
	/**
	* 批量修改
	* @param crm_invoiceList 
	* @return
	*/
	public int updateBatchCrmInvoice(List<CrmInvoice> crmInvoiceList);
	/**
	* 批量修改（根据动态条件）
	* @param crm_invoiceList 
	* @return
	*/
	public int updateBatchCrmInvoiceBySelective(List<CrmInvoice> crmInvoiceList);
	
	/**
	 * 根据客户查找唯一发票
	 * @param customerId
	 * @return
	 */
	public CrmInvoice getCrmInvoiceSingleByCustomerId(String customerId);
}
