package jehc.paymentmodules.paymentmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* payment_refund 支付退款 
* 2018-07-24 14:51:42  邓纯杰
*/
public class PaymentRefund extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;/**主键**/
	private String refundNo;/**退款单号，每次进行退款的单号，此处唯一**/
	private String tradeNo;/**支付平台订单号,交易号**/
	private String outTradeNo;/**商户单号**/
	private BigDecimal refundAmount;/**退款金额**/
	private BigDecimal totalAmount;/**订单总金额**/
	private Date orderDate;/**交易日期**/
	private String curType;/**货币**/
	private String description;/**退款说明**/
	private String refundresult;/**退款结果（0成功1失败）**/
	private String refundresultmsg;/**退款结果描述**/
	private Date refundlasttime;/**最后操作时间**/
	private int isdelete;/**是否删除0正常 1删除**/
	private String fromto;/**创建来源（backstage：后台，fontpay前台退款）**/
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setRefundNo(String refundNo){
		this.refundNo=refundNo;
	}
	public String getRefundNo(){
		return refundNo;
	}
	public void setTradeNo(String tradeNo){
		this.tradeNo=tradeNo;
	}
	public String getTradeNo(){
		return tradeNo;
	}
	public void setOutTradeNo(String outTradeNo){
		this.outTradeNo=outTradeNo;
	}
	public String getOutTradeNo(){
		return outTradeNo;
	}
	public void setRefundAmount(BigDecimal refundAmount){
		this.refundAmount=refundAmount;
	}
	public BigDecimal getRefundAmount(){
		return refundAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount=totalAmount;
	}
	public BigDecimal getTotalAmount(){
		return totalAmount;
	}
	public void setOrderDate(Date orderDate){
		this.orderDate=orderDate;
	}
	public Date getOrderDate(){
		return orderDate;
	}
	public void setCurType(String curType){
		this.curType=curType;
	}
	public String getCurType(){
		return curType;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setRefundresult(String refundresult){
		this.refundresult=refundresult;
	}
	public String getRefundresult(){
		return refundresult;
	}
	public void setRefundresultmsg(String refundresultmsg){
		this.refundresultmsg=refundresultmsg;
	}
	public String getRefundresultmsg(){
		return refundresultmsg;
	}
	public void setRefundlasttime(Date refundlasttime){
		this.refundlasttime=refundlasttime;
	}
	public Date getRefundlasttime(){
		return refundlasttime;
	}
	public void setIsdelete(int isdelete){
		this.isdelete=isdelete;
	}
	public int getIsdelete(){
		return isdelete;
	}
	public void setFromto(String fromto){
		this.fromto=fromto;
	}
	public String getFromto(){
		return fromto;
	}
}
