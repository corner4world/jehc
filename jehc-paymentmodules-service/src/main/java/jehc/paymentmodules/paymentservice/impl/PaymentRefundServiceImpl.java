package jehc.paymentmodules.paymentservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.paymentmodules.paymentservice.PaymentRefundService;
import jehc.paymentmodules.paymentdao.PaymentRefundDao;
import jehc.paymentmodules.paymentmodel.PaymentRefund;

/**
* 支付退款 
* 2018-07-24 14:51:42  邓纯杰
*/
@Service("paymentRefundService")
public class PaymentRefundServiceImpl extends BaseService implements PaymentRefundService{
	@Autowired
	private PaymentRefundDao paymentRefundDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentRefund> getPaymentRefundListByCondition(Map<String,Object> condition){
		try{
			return paymentRefundDao.getPaymentRefundListByCondition(condition);
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
	public PaymentRefund getPaymentRefundById(String id){
		try{
			PaymentRefund paymentRefund = paymentRefundDao.getPaymentRefundById(id);
			return paymentRefund;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param payment_refund 
	* @return
	*/
	public int addPaymentRefund(PaymentRefund paymentRefund){
		int i = 0;
		try {
			i = paymentRefundDao.addPaymentRefund(paymentRefund);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param payment_refund 
	* @return
	*/
	public int updatePaymentRefund(PaymentRefund paymentRefund){
		int i = 0;
		try {
			i = paymentRefundDao.updatePaymentRefund(paymentRefund);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param payment_refund 
	* @return
	*/
	public int updatePaymentRefundBySelective(PaymentRefund paymentRefund){
		int i = 0;
		try {
			i = paymentRefundDao.updatePaymentRefundBySelective(paymentRefund);
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
	public int delPaymentRefund(Map<String,Object> condition){
		int i = 0;
		try {
			i = paymentRefundDao.delPaymentRefund(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param payment_refundList 
	* @return
	*/
	public int addBatchPaymentRefund(List<PaymentRefund> paymentRefundList){
		int i = 0;
		try {
			i = paymentRefundDao.addBatchPaymentRefund(paymentRefundList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param payment_refundList 
	* @return
	*/
	public int updateBatchPaymentRefund(List<PaymentRefund> paymentRefundList){
		int i = 0;
		try {
			i = paymentRefundDao.updateBatchPaymentRefund(paymentRefundList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_refundList 
	* @return
	*/
	public int updateBatchPaymentRefundBySelective(List<PaymentRefund> paymentRefundList){
		int i = 0;
		try {
			i = paymentRefundDao.updateBatchPaymentRefundBySelective(paymentRefundList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
