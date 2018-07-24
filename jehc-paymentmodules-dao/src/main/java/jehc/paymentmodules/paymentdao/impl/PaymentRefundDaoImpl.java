package jehc.paymentmodules.paymentdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.paymentmodules.paymentdao.PaymentRefundDao;
import jehc.paymentmodules.paymentmodel.PaymentRefund;

/**
* 支付退款 
* 2018-07-24 14:51:42  邓纯杰
*/
@Repository("paymentRefundDao")
public class PaymentRefundDaoImpl  extends BaseDaoImpl implements PaymentRefundDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<PaymentRefund> getPaymentRefundListByCondition(Map<String,Object> condition){
		return (List<PaymentRefund>)this.getList("getPaymentRefundListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentRefund getPaymentRefundById(String id){
		return (PaymentRefund)this.get("getPaymentRefundById", id);
	}
	/**
	* 添加
	* @param payment_refund 
	* @return
	*/
	public int addPaymentRefund(PaymentRefund paymentRefund){
		return this.add("addPaymentRefund", paymentRefund);
	}
	/**
	* 修改
	* @param payment_refund 
	* @return
	*/
	public int updatePaymentRefund(PaymentRefund paymentRefund){
		return this.update("updatePaymentRefund", paymentRefund);
	}
	/**
	* 修改（根据动态条件）
	* @param payment_refund 
	* @return
	*/
	public int updatePaymentRefundBySelective(PaymentRefund paymentRefund){
		return this.update("updatePaymentRefundBySelective", paymentRefund);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentRefund(Map<String,Object> condition){
		return this.del("delPaymentRefund", condition);
	}
	/**
	* 批量添加
	* @param payment_refundList 
	* @return
	*/
	public int addBatchPaymentRefund(List<PaymentRefund> paymentRefundList){
		return this.add("addBatchPaymentRefund", paymentRefundList);
	}
	/**
	* 批量修改
	* @param payment_refundList 
	* @return
	*/
	public int updateBatchPaymentRefund(List<PaymentRefund> paymentRefundList){
		return this.update("updateBatchPaymentRefund", paymentRefundList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_refundList 
	* @return
	*/
	public int updateBatchPaymentRefundBySelective(List<PaymentRefund> paymentRefundList){
		return this.update("updateBatchPaymentRefundBySelective", paymentRefundList);
	}
}
