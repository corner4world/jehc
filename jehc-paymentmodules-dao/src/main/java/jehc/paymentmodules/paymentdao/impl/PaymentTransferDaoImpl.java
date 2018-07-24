package jehc.paymentmodules.paymentdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.paymentmodules.paymentdao.PaymentTransferDao;
import jehc.paymentmodules.paymentmodel.PaymentTransfer;

/**
* 转账 
* 2018-07-24 15:31:30  邓纯杰
*/
@Repository("paymentTransferDao")
public class PaymentTransferDaoImpl  extends BaseDaoImpl implements PaymentTransferDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<PaymentTransfer> getPaymentTransferListByCondition(Map<String,Object> condition){
		return (List<PaymentTransfer>)this.getList("getPaymentTransferListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentTransfer getPaymentTransferById(String id){
		return (PaymentTransfer)this.get("getPaymentTransferById", id);
	}
	/**
	* 添加
	* @param payment_transfer 
	* @return
	*/
	public int addPaymentTransfer(PaymentTransfer paymentTransfer){
		return this.add("addPaymentTransfer", paymentTransfer);
	}
	/**
	* 修改
	* @param payment_transfer 
	* @return
	*/
	public int updatePaymentTransfer(PaymentTransfer paymentTransfer){
		return this.update("updatePaymentTransfer", paymentTransfer);
	}
	/**
	* 修改（根据动态条件）
	* @param payment_transfer 
	* @return
	*/
	public int updatePaymentTransferBySelective(PaymentTransfer paymentTransfer){
		return this.update("updatePaymentTransferBySelective", paymentTransfer);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentTransfer(Map<String,Object> condition){
		return this.del("delPaymentTransfer", condition);
	}
	/**
	* 批量添加
	* @param payment_transferList 
	* @return
	*/
	public int addBatchPaymentTransfer(List<PaymentTransfer> paymentTransferList){
		return this.add("addBatchPaymentTransfer", paymentTransferList);
	}
	/**
	* 批量修改
	* @param payment_transferList 
	* @return
	*/
	public int updateBatchPaymentTransfer(List<PaymentTransfer> paymentTransferList){
		return this.update("updateBatchPaymentTransfer", paymentTransferList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_transferList 
	* @return
	*/
	public int updateBatchPaymentTransferBySelective(List<PaymentTransfer> paymentTransferList){
		return this.update("updateBatchPaymentTransferBySelective", paymentTransferList);
	}
}
