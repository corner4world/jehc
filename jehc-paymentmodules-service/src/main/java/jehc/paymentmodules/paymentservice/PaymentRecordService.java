package jehc.paymentmodules.paymentservice;
import java.util.List;
import java.util.Map;
import jehc.paymentmodules.paymentmodel.PaymentRecord;

/**
* 支付记录 
* 2018-07-24 13:33:48  邓
*/
public interface PaymentRecordService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<PaymentRecord> getPaymentRecordListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentRecord getPaymentRecordById(String id);
	/**
	* 添加
	* @param payment_record 
	* @return
	*/
	public int addPaymentRecord(PaymentRecord paymentRecord);
	/**
	* 修改
	* @param payment_record 
	* @return
	*/
	public int updatePaymentRecord(PaymentRecord paymentRecord);
	/**
	* 修改（根据动态条件）
	* @param payment_record 
	* @return
	*/
	public int updatePaymentRecordBySelective(PaymentRecord paymentRecord);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentRecord(Map<String,Object> condition);
	/**
	* 批量添加
	* @param payment_recordList 
	* @return
	*/
	public int addBatchPaymentRecord(List<PaymentRecord> paymentRecordList);
	/**
	* 批量修改
	* @param payment_recordList 
	* @return
	*/
	public int updateBatchPaymentRecord(List<PaymentRecord> paymentRecordList);
	/**
	* 批量修改（根据动态条件）
	* @param payment_recordList 
	* @return
	*/
	public int updateBatchPaymentRecordBySelective(List<PaymentRecord> paymentRecordList);
}
