package jehc.paymentmodules.paymentdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.paymentmodules.paymentdao.PaymentAccountDao;
import jehc.paymentmodules.paymentmodel.PaymentAccount;

/**
* 支付账号配置 
* 2018-07-21 14:33:58  邓纯杰
*/
@Repository("paymentAccountDao")
public class PaymentAccountDaoImpl  extends BaseDaoImpl implements PaymentAccountDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<PaymentAccount> getPaymentAccountListByCondition(Map<String,Object> condition){
		return (List<PaymentAccount>)this.getList("getPaymentAccountListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentAccount getPaymentAccountById(String id){
		return (PaymentAccount)this.get("getPaymentAccountById", id);
	}
	/**
	* 添加
	* @param payment_account 
	* @return
	*/
	public int addPaymentAccount(PaymentAccount paymentAccount){
		return this.add("addPaymentAccount", paymentAccount);
	}
	/**
	* 修改
	* @param payment_account 
	* @return
	*/
	public int updatePaymentAccount(PaymentAccount paymentAccount){
		return this.update("updatePaymentAccount", paymentAccount);
	}
	/**
	* 修改（根据动态条件）
	* @param payment_account 
	* @return
	*/
	public int updatePaymentAccountBySelective(PaymentAccount paymentAccount){
		return this.update("updatePaymentAccountBySelective", paymentAccount);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentAccount(Map<String,Object> condition){
		return this.del("delPaymentAccount", condition);
	}
	/**
	* 批量添加
	* @param payment_accountList 
	* @return
	*/
	public int addBatchPaymentAccount(List<PaymentAccount> paymentAccountList){
		return this.add("addBatchPaymentAccount", paymentAccountList);
	}
	/**
	* 批量修改
	* @param payment_accountList 
	* @return
	*/
	public int updateBatchPaymentAccount(List<PaymentAccount> paymentAccountList){
		return this.update("updateBatchPaymentAccount", paymentAccountList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_accountList 
	* @return
	*/
	public int updateBatchPaymentAccountBySelective(List<PaymentAccount> paymentAccountList){
		return this.update("updateBatchPaymentAccountBySelective", paymentAccountList);
	}
}
