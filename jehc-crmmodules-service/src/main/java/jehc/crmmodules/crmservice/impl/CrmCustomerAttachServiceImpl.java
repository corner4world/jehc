package jehc.crmmodules.crmservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.crmmodules.crmservice.CrmCustomerAttachService;
import jehc.crmmodules.crmdao.CrmCustomerAttachDao;
import jehc.crmmodules.crmmodel.CrmCustomerAttach;

/**
* 客户附件 
* 2018-06-27 15:17:45  邓纯杰
*/
@Service("crmCustomerAttachService")
public class CrmCustomerAttachServiceImpl extends BaseService implements CrmCustomerAttachService{
	@Autowired
	private CrmCustomerAttachDao crmCustomerAttachDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmCustomerAttach> getCrmCustomerAttachListByCondition(Map<String,Object> condition){
		try{
			return crmCustomerAttachDao.getCrmCustomerAttachListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param customer_attach_id 
	* @return
	*/
	public CrmCustomerAttach getCrmCustomerAttachById(String customer_attach_id){
		try{
			CrmCustomerAttach crmCustomerAttach = crmCustomerAttachDao.getCrmCustomerAttachById(customer_attach_id);
			return crmCustomerAttach;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param crm_customer_attach 
	* @return
	*/
	public int addCrmCustomerAttach(CrmCustomerAttach crmCustomerAttach){
		int i = 0;
		try {
			i = crmCustomerAttachDao.addCrmCustomerAttach(crmCustomerAttach);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param crm_customer_attach 
	* @return
	*/
	public int updateCrmCustomerAttach(CrmCustomerAttach crmCustomerAttach){
		int i = 0;
		try {
			i = crmCustomerAttachDao.updateCrmCustomerAttach(crmCustomerAttach);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param crm_customer_attach 
	* @return
	*/
	public int updateCrmCustomerAttachBySelective(CrmCustomerAttach crmCustomerAttach){
		int i = 0;
		try {
			i = crmCustomerAttachDao.updateCrmCustomerAttachBySelective(crmCustomerAttach);
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
	public int delCrmCustomerAttach(Map<String,Object> condition){
		int i = 0;
		try {
			i = crmCustomerAttachDao.delCrmCustomerAttach(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param crm_customer_attachList 
	* @return
	*/
	public int addBatchCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttachList){
		int i = 0;
		try {
			i = crmCustomerAttachDao.addBatchCrmCustomerAttach(crmCustomerAttachList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param crm_customer_attachList 
	* @return
	*/
	public int updateBatchCrmCustomerAttach(List<CrmCustomerAttach> crmCustomerAttachList){
		int i = 0;
		try {
			i = crmCustomerAttachDao.updateBatchCrmCustomerAttach(crmCustomerAttachList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_customer_attachList 
	* @return
	*/
	public int updateBatchCrmCustomerAttachBySelective(List<CrmCustomerAttach> crmCustomerAttachList){
		int i = 0;
		try {
			i = crmCustomerAttachDao.updateBatchCrmCustomerAttachBySelective(crmCustomerAttachList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
