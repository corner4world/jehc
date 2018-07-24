package jehc.paymentmodules.paymentdao;
import java.util.List;
import java.util.Map;
import jehc.paymentmodules.paymentmodel.PaymentRefund;

/**
* 支付退款 
* 2018-07-24 14:51:42  邓纯杰
*/
public interface PaymentRefundDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentRefund> getPaymentRefundListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentRefund getPaymentRefundById(String id);
	/**
	* 添加
	* @param payment_refund 
	* @return
	*/
	public int addPaymentRefund(PaymentRefund paymentRefund);
	/**
	* 修改
	* @param payment_refund 
	* @return
	*/
	public int updatePaymentRefund(PaymentRefund paymentRefund);
	/**
	* 修改（根据动态条件）
	* @param payment_refund 
	* @return
	*/
	public int updatePaymentRefundBySelective(PaymentRefund paymentRefund);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentRefund(Map<String,Object> condition);
	/**
	* 批量添加
	* @param payment_refundList 
	* @return
	*/
	public int addBatchPaymentRefund(List<PaymentRefund> paymentRefundList);
	/**
	* 批量修改
	* @param payment_refundList 
	* @return
	*/
	public int updateBatchPaymentRefund(List<PaymentRefund> paymentRefundList);
	/**
	* 批量修改（根据动态条件）
	* @param payment_refundList 
	* @return
	*/
	public int updateBatchPaymentRefundBySelective(List<PaymentRefund> paymentRefundList);
}
