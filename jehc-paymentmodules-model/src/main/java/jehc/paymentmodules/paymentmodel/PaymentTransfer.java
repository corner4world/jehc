package jehc.paymentmodules.paymentmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* payment_transfer 转账 
* 2018-07-24 15:31:30  邓纯杰
*/
public class PaymentTransfer extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;/**主键**/
	private String outNo;/**转账订单单号**/
	private String payeeAccount;/**收款方账户**/
	private BigDecimal amount;/**转账金额**/
	private String payerName;/**付款人名称**/
	private String payeeName;/**收款人名称**/
	private String remark;/**备注**/
	private String bank;/**收款开户行**/
	private String curType;/**币种**/
	private Date ctime;/**转账日期**/
	private Date transferlasttime;/**最后操作时间**/
	private String transferresult;/**退款结果（0成功1失败）**/
	private String transferresultmsg;/**退款结果描述**/
	private String fromto;/**创建来源（backstage：后台，fontpay前台退款）**/
	private int isdelete;/**是否删除0正常 1删除**/
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setOutNo(String outNo){
		this.outNo=outNo;
	}
	public String getOutNo(){
		return outNo;
	}
	public void setPayeeAccount(String payeeAccount){
		this.payeeAccount=payeeAccount;
	}
	public String getPayeeAccount(){
		return payeeAccount;
	}
	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}
	public BigDecimal getAmount(){
		return amount;
	}
	public void setPayerName(String payerName){
		this.payerName=payerName;
	}
	public String getPayerName(){
		return payerName;
	}
	public void setPayeeName(String payeeName){
		this.payeeName=payeeName;
	}
	public String getPayeeName(){
		return payeeName;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setBank(String bank){
		this.bank=bank;
	}
	public String getBank(){
		return bank;
	}
	public void setCurType(String curType){
		this.curType=curType;
	}
	public String getCurType(){
		return curType;
	}
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	public Date getCtime(){
		return ctime;
	}
	public void setTransferlasttime(Date transferlasttime){
		this.transferlasttime=transferlasttime;
	}
	public Date getTransferlasttime(){
		return transferlasttime;
	}
	public void setTransferresult(String transferresult){
		this.transferresult=transferresult;
	}
	public String getTransferresult(){
		return transferresult;
	}
	public void setTransferresultmsg(String transferresultmsg){
		this.transferresultmsg=transferresultmsg;
	}
	public String getTransferresultmsg(){
		return transferresultmsg;
	}
	public void setFromto(String fromto){
		this.fromto=fromto;
	}
	public String getFromto(){
		return fromto;
	}
	public void setIsdelete(int isdelete){
		this.isdelete=isdelete;
	}
	public int getIsdelete(){
		return isdelete;
	}
}
