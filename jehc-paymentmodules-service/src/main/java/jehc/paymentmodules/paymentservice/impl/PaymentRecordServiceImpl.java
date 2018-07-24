package jehc.paymentmodules.paymentservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.paymentmodules.paymentservice.PaymentRecordService;
import jehc.paymentmodules.paymentdao.PaymentRecordDao;
import jehc.paymentmodules.paymentmodel.PaymentRecord;

/**
* 支付记录 
* 2018-07-24 13:33:48  邓
*/
@Service("paymentRecordService")
public class PaymentRecordServiceImpl extends BaseService implements PaymentRecordService{
	@Autowired
	private PaymentRecordDao paymentRecordDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentRecord> getPaymentRecordListByCondition(Map<String,Object> condition){
		try{
			return paymentRecordDao.getPaymentRecordListByCondition(condition);
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
	public PaymentRecord getPaymentRecordById(String id){
		try{
			PaymentRecord paymentRecord = paymentRecordDao.getPaymentRecordById(id);
			return paymentRecord;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param payment_record 
	* @return
	*/
	public int addPaymentRecord(PaymentRecord paymentRecord){
		int i = 0;
		try {
			i = paymentRecordDao.addPaymentRecord(paymentRecord);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param payment_record 
	* @return
	*/
	public int updatePaymentRecord(PaymentRecord paymentRecord){
		int i = 0;
		try {
			i = paymentRecordDao.updatePaymentRecord(paymentRecord);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param payment_record 
	* @return
	*/
	public int updatePaymentRecordBySelective(PaymentRecord paymentRecord){
		int i = 0;
		try {
			i = paymentRecordDao.updatePaymentRecordBySelective(paymentRecord);
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
	public int delPaymentRecord(Map<String,Object> condition){
		int i = 0;
		try {
			i = paymentRecordDao.delPaymentRecord(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param payment_recordList 
	* @return
	*/
	public int addBatchPaymentRecord(List<PaymentRecord> paymentRecordList){
		int i = 0;
		try {
			i = paymentRecordDao.addBatchPaymentRecord(paymentRecordList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param payment_recordList 
	* @return
	*/
	public int updateBatchPaymentRecord(List<PaymentRecord> paymentRecordList){
		int i = 0;
		try {
			i = paymentRecordDao.updateBatchPaymentRecord(paymentRecordList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_recordList 
	* @return
	*/
	public int updateBatchPaymentRecordBySelective(List<PaymentRecord> paymentRecordList){
		int i = 0;
		try {
			i = paymentRecordDao.updateBatchPaymentRecordBySelective(paymentRecordList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
