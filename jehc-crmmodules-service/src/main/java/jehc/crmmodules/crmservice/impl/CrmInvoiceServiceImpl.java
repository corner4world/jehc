package jehc.crmmodules.crmservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.crmmodules.crmservice.CrmInvoiceService;
import jehc.crmmodules.crmdao.CrmInvoiceDao;
import jehc.crmmodules.crmmodel.CrmInvoice;

/**
* 客户发票 
* 2018-06-27 15:13:11  邓纯杰
*/
@Service("crmInvoiceService")
public class CrmInvoiceServiceImpl extends BaseService implements CrmInvoiceService{
	@Autowired
	private CrmInvoiceDao crmInvoiceDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmInvoice> getCrmInvoiceListByCondition(Map<String,Object> condition){
		try{
			return crmInvoiceDao.getCrmInvoiceListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param invoiceId 
	* @return
	*/
	public CrmInvoice getCrmInvoiceById(String invoiceId){
		try{
			CrmInvoice crmInvoice = crmInvoiceDao.getCrmInvoiceById(invoiceId);
			return crmInvoice;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param crm_invoice 
	* @return
	*/
	public int addCrmInvoice(CrmInvoice crmInvoice){
		int i = 0;
		try {
			i = crmInvoiceDao.addCrmInvoice(crmInvoice);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param crm_invoice 
	* @return
	*/
	public int updateCrmInvoice(CrmInvoice crmInvoice){
		int i = 0;
		try {
			i = crmInvoiceDao.updateCrmInvoice(crmInvoice);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param crm_invoice 
	* @return
	*/
	public int updateCrmInvoiceBySelective(CrmInvoice crmInvoice){
		int i = 0;
		try {
			i = crmInvoiceDao.updateCrmInvoiceBySelective(crmInvoice);
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
	public int delCrmInvoice(Map<String,Object> condition){
		int i = 0;
		try {
			i = crmInvoiceDao.delCrmInvoice(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param crm_invoiceList 
	* @return
	*/
	public int addBatchCrmInvoice(List<CrmInvoice> crmInvoiceList){
		int i = 0;
		try {
			i = crmInvoiceDao.addBatchCrmInvoice(crmInvoiceList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param crm_invoiceList 
	* @return
	*/
	public int updateBatchCrmInvoice(List<CrmInvoice> crmInvoiceList){
		int i = 0;
		try {
			i = crmInvoiceDao.updateBatchCrmInvoice(crmInvoiceList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_invoiceList 
	* @return
	*/
	public int updateBatchCrmInvoiceBySelective(List<CrmInvoice> crmInvoiceList){
		int i = 0;
		try {
			i = crmInvoiceDao.updateBatchCrmInvoiceBySelective(crmInvoiceList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 根据客户查找唯一发票
	 * @param customerId
	 * @return
	 */
	public CrmInvoice getCrmInvoiceSingleByCustomerId(String customerId){
		try{
			return crmInvoiceDao.getCrmInvoiceSingleByCustomerId(customerId);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
