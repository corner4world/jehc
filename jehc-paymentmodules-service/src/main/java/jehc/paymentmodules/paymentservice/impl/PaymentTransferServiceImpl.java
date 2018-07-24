package jehc.paymentmodules.paymentservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.paymentmodules.paymentservice.PaymentTransferService;
import jehc.paymentmodules.paymentdao.PaymentTransferDao;
import jehc.paymentmodules.paymentmodel.PaymentTransfer;

/**
* 转账 
* 2018-07-24 15:31:30  邓纯杰
*/
@Service("paymentTransferService")
public class PaymentTransferServiceImpl extends BaseService implements PaymentTransferService{
	@Autowired
	private PaymentTransferDao paymentTransferDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentTransfer> getPaymentTransferListByCondition(Map<String,Object> condition){
		try{
			return paymentTransferDao.getPaymentTransferListByCondition(condition);
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
	public PaymentTransfer getPaymentTransferById(String id){
		try{
			PaymentTransfer paymentTransfer = paymentTransferDao.getPaymentTransferById(id);
			return paymentTransfer;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param payment_transfer 
	* @return
	*/
	public int addPaymentTransfer(PaymentTransfer paymentTransfer){
		int i = 0;
		try {
			i = paymentTransferDao.addPaymentTransfer(paymentTransfer);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param payment_transfer 
	* @return
	*/
	public int updatePaymentTransfer(PaymentTransfer paymentTransfer){
		int i = 0;
		try {
			i = paymentTransferDao.updatePaymentTransfer(paymentTransfer);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param payment_transfer 
	* @return
	*/
	public int updatePaymentTransferBySelective(PaymentTransfer paymentTransfer){
		int i = 0;
		try {
			i = paymentTransferDao.updatePaymentTransferBySelective(paymentTransfer);
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
	public int delPaymentTransfer(Map<String,Object> condition){
		int i = 0;
		try {
			i = paymentTransferDao.delPaymentTransfer(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param payment_transferList 
	* @return
	*/
	public int addBatchPaymentTransfer(List<PaymentTransfer> paymentTransferList){
		int i = 0;
		try {
			i = paymentTransferDao.addBatchPaymentTransfer(paymentTransferList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param payment_transferList 
	* @return
	*/
	public int updateBatchPaymentTransfer(List<PaymentTransfer> paymentTransferList){
		int i = 0;
		try {
			i = paymentTransferDao.updateBatchPaymentTransfer(paymentTransferList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_transferList 
	* @return
	*/
	public int updateBatchPaymentTransferBySelective(List<PaymentTransfer> paymentTransferList){
		int i = 0;
		try {
			i = paymentTransferDao.updateBatchPaymentTransferBySelective(paymentTransferList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
