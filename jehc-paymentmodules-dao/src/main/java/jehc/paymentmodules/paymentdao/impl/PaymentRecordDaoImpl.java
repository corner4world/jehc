package jehc.paymentmodules.paymentdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.paymentmodules.paymentdao.PaymentRecordDao;
import jehc.paymentmodules.paymentmodel.PaymentRecord;

/**
* 支付记录 
* 2018-07-24 13:33:48  邓
*/
@Repository("paymentRecordDao")
public class PaymentRecordDaoImpl  extends BaseDaoImpl implements PaymentRecordDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<PaymentRecord> getPaymentRecordListByCondition(Map<String,Object> condition){
		return (List<PaymentRecord>)this.getList("getPaymentRecordListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public PaymentRecord getPaymentRecordById(String id){
		return (PaymentRecord)this.get("getPaymentRecordById", id);
	}
	/**
	* 添加
	* @param payment_record 
	* @return
	*/
	public int addPaymentRecord(PaymentRecord paymentRecord){
		return this.add("addPaymentRecord", paymentRecord);
	}
	/**
	* 修改
	* @param payment_record 
	* @return
	*/
	public int updatePaymentRecord(PaymentRecord paymentRecord){
		return this.update("updatePaymentRecord", paymentRecord);
	}
	/**
	* 修改（根据动态条件）
	* @param payment_record 
	* @return
	*/
	public int updatePaymentRecordBySelective(PaymentRecord paymentRecord){
		return this.update("updatePaymentRecordBySelective", paymentRecord);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delPaymentRecord(Map<String,Object> condition){
		return this.update("delPaymentRecord", condition);
	}
	/**
	* 批量添加
	* @param payment_recordList 
	* @return
	*/
	public int addBatchPaymentRecord(List<PaymentRecord> paymentRecordList){
		return this.add("addBatchPaymentRecord", paymentRecordList);
	}
	/**
	* 批量修改
	* @param payment_recordList 
	* @return
	*/
	public int updateBatchPaymentRecord(List<PaymentRecord> paymentRecordList){
		return this.update("updateBatchPaymentRecord", paymentRecordList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param payment_recordList 
	* @return
	*/
	public int updateBatchPaymentRecordBySelective(List<PaymentRecord> paymentRecordList){
		return this.update("updateBatchPaymentRecordBySelective", paymentRecordList);
	}
}
