package jehc.paymentmodules.paymentservice;
import java.util.List;
import java.util.Map;
import jehc.paymentmodules.paymentmodel.PaymentAccount;

/**
* 支付账号配置 
* 2018-07-21 14:33:58  邓纯杰
*/
public interface PaymentAccountService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentAccount> getPaymentAccountListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentAccount getPaymentAccountById(String id);
	/**
	* 添加
	* @param payment_account 
	* @return
	*/
	public int addPaymentAccount(PaymentAccount paymentAccount);
	/**
	* 修改
	* @param payment_account 
	* @return
	*/
	public int updatePaymentAccount(PaymentAccount paymentAccount);
	/**
	* 修改（根据动态条件）
	* @param payment_account 
	* @return
	*/
	public int updatePaymentAccountBySelective(PaymentAccount paymentAccount);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentAccount(Map<String,Object> condition);
	/**
	* 批量添加
	* @param payment_accountList 
	* @return
	*/
	public int addBatchPaymentAccount(List<PaymentAccount> paymentAccountList);
	/**
	* 批量修改
	* @param payment_accountList 
	* @return
	*/
	public int updateBatchPaymentAccount(List<PaymentAccount> paymentAccountList);
	/**
	* 批量修改（根据动态条件）
	* @param payment_accountList 
	* @return
	*/
	public int updateBatchPaymentAccountBySelective(List<PaymentAccount> paymentAccountList);
}
