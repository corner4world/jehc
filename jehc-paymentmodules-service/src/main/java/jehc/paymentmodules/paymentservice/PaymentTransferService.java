package jehc.paymentmodules.paymentservice;
import java.util.List;
import java.util.Map;
import jehc.paymentmodules.paymentmodel.PaymentTransfer;

/**
* 转账 
* 2018-07-24 15:31:30  邓纯杰
*/
public interface PaymentTransferService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentTransfer> getPaymentTransferListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentTransfer getPaymentTransferById(String id);
	/**
	* 添加
	* @param payment_transfer 
	* @return
	*/
	public int addPaymentTransfer(PaymentTransfer paymentTransfer);
	/**
	* 修改
	* @param payment_transfer 
	* @return
	*/
	public int updatePaymentTransfer(PaymentTransfer paymentTransfer);
	/**
	* 修改（根据动态条件）
	* @param payment_transfer 
	* @return
	*/
	public int updatePaymentTransferBySelective(PaymentTransfer paymentTransfer);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentTransfer(Map<String,Object> condition);
	/**
	* 批量添加
	* @param payment_transferList 
	* @return
	*/
	public int addBatchPaymentTransfer(List<PaymentTransfer> paymentTransferList);
	/**
	* 批量修改
	* @param payment_transferList 
	* @return
	*/
	public int updateBatchPaymentTransfer(List<PaymentTransfer> paymentTransferList);
	/**
	* 批量修改（根据动态条件）
	* @param payment_transferList 
	* @return
	*/
	public int updateBatchPaymentTransferBySelective(List<PaymentTransfer> paymentTransferList);
}
