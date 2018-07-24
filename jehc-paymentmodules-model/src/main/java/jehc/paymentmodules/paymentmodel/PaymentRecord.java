package jehc.paymentmodules.paymentmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* payment_record 支付记录 
* 2018-07-24 13:33:48  邓
*/
public class PaymentRecord extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;/**主键**/
	private String subject;/**商品名称**/
	private String body;/**描述**/
	private BigDecimal price;/**价格**/
	private String outTradeNo;/**商户订单号**/
	private String bankType;/**银行卡类型**/
	private String deviceInfo;/**设备信息**/
	private String spbillCreateIp;/**支付创建ip**/
	private String authCode;/**付款条码串**/
	private String wapUrl;/**WAP支付链接**/
	private String wapName;/**WAP支付网页名称**/
	private String openid;/**微信会员唯一标识**/
	private String transactionType;/**交易类型**/
	private String curType;/**支付币种**/
	private Date ctime;/**支付时间**/
	
	private String fromto;/**创建来源（backstage：后台，fontpay前台支付）**/
	private String payresult;/**支付结果（0成功1失败）**/
	private String payresultmsg;/**支付结果描述**/
	private Date paylasttime;/**支付最后成功时间**/
	private int isdelete;/**是否删除0正常 1删除**/
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	public String getSubject(){
		return subject;
	}
	public void setBody(String body){
		this.body=body;
	}
	public String getBody(){
		return body;
	}
	public void setPrice(BigDecimal price){
		this.price=price;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setOutTradeNo(String outTradeNo){
		this.outTradeNo=outTradeNo;
	}
	public String getOutTradeNo(){
		return outTradeNo;
	}
	public void setBankType(String bankType){
		this.bankType=bankType;
	}
	public String getBankType(){
		return bankType;
	}
	public void setDeviceInfo(String deviceInfo){
		this.deviceInfo=deviceInfo;
	}
	public String getDeviceInfo(){
		return deviceInfo;
	}
	public void setSpbillCreateIp(String spbillCreateIp){
		this.spbillCreateIp=spbillCreateIp;
	}
	public String getSpbillCreateIp(){
		return spbillCreateIp;
	}
	public void setAuthCode(String authCode){
		this.authCode=authCode;
	}
	public String getAuthCode(){
		return authCode;
	}
	public void setWapUrl(String wapUrl){
		this.wapUrl=wapUrl;
	}
	public String getWapUrl(){
		return wapUrl;
	}
	public void setWapName(String wapName){
		this.wapName=wapName;
	}
	public String getWapName(){
		return wapName;
	}
	public void setOpenid(String openid){
		this.openid=openid;
	}
	public String getOpenid(){
		return openid;
	}
	public void setTransactionType(String transactionType){
		this.transactionType=transactionType;
	}
	public String getTransactionType(){
		return transactionType;
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
	public String getFromto() {
		return fromto;
	}
	public void setFromto(String fromto) {
		this.fromto = fromto;
	}
	public String getPayresult() {
		return payresult;
	}
	public void setPayresult(String payresult) {
		this.payresult = payresult;
	}
	public String getPayresultmsg() {
		return payresultmsg;
	}
	public void setPayresultmsg(String payresultmsg) {
		this.payresultmsg = payresultmsg;
	}
	public Date getPaylasttime() {
		return paylasttime;
	}
	public void setPaylasttime(Date paylasttime) {
		this.paylasttime = paylasttime;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
}
