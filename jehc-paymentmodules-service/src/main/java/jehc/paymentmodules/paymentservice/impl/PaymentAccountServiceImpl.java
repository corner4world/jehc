package jehc.paymentmodules.paymentservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.paymentmodules.paymentservice.PaymentAccountService;
import jehc.paymentmodules.paymentdao.PaymentAccountDao;
import jehc.paymentmodules.paymentmodel.PaymentAccount;

/**
* 支付账号配置 
* 2018-07-21 14:33:58  邓纯杰
*/
@Service("paymentAccountService")
public class PaymentAccountServiceImpl extends BaseService implements PaymentAccountService{
	@Autowired
	private PaymentAccountDao paymentAccountDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentAccount> getPaymentAccountListByCondition(Map<String,Object> condition){
		try{
			return paymentAccountDao.getPaymentAccountListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentAccount getPaymentAccountById(String id){
		try{
			PaymentAccount paymentAccount = paymentAccountDao.getPaymentAccountById(id);
			return paymentAccount;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param payment_account 
	* @return
	*/
	public int addPaymentAccount(PaymentAccount paymentAccount){
		int i = 0;
		try {
			i = paymentAccountDao.addPaymentAccount(paymentAccount);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param payment_account 
	* @return
	*/
	public int updatePaymentAccount(PaymentAccount paymentAccount){
		int i = 0;
		try {
			i = paymentAccountDao.updatePaymentAccount(paymentAccount);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param payment_account 
	* @return
	*/
	public int updatePaymentAccountBySelective(PaymentAccount paymentAccount){
		int i = 0;
		try {
			i = paymentAccountDao.updatePaymentAccountBySelective(paymentAccount);
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
	public int delPaymentAccount(Map<String,Object> condition){
		int i = 0;
		try {
			i = paymentAccountDao.delPaymentAccount(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param payment_accountList 
	* @return
	*/
	public int addBatchPaymentAccount(List<PaymentAccount> paymentAccountList){
		int i = 0;
		try {
			i = paymentAccountDao.addBatchPaymentAccount(paymentAccountList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param payment_accountList 
	* @return
	*/
	public int updateBatchPaymentAccount(List<PaymentAccount> paymentAccountList){
		int i = 0;
		try {
			i = paymentAccountDao.updateBatchPaymentAccount(paymentAccountList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_accountList 
	* @return
	*/
	public int updateBatchPaymentAccountBySelective(List<PaymentAccount> paymentAccountList){
		int i = 0;
		try {
			i = paymentAccountDao.updateBatchPaymentAccountBySelective(paymentAccountList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
