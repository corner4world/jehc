package jehc.paymentmodules.paymentmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* payment_account 支付账号配置 
* 2018-07-21 14:33:58  邓纯杰
*/
public class PaymentAccount extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;/**支付账号id**/
	private String partner;/**支付合作id,商户id，差不多是支付平台的账号或id**/
	private String appid;/**应用id**/
	private String public_key;/**支付平台公钥(签名校验使用)，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5(友店支付除外)的情况**/
	private String private_key;/**应用私钥(生成签名)**/
	private String notify_url;/**异步回调地址**/
	private String return_url;/**同步回调地址**/
	private String seller;/**收款账号, 针对支付宝**/
	private String sign_type;/**签名类型**/
	private String input_charset;/**枚举值，字符编码 utf-8,gbk等等**/
	private String pay_type;/**支付类型,aliPay：支付宝，wxPay：微信, youdianPay: 友店微信,此处开发者自定义对应jehc.paymodules.paymodel.PayType枚举值**/
	private String msg_type;/**消息类型，text,xml,json**/
	private String keystore_path;/**请求证书地址，请使用绝对路径**/
	private String store_password;/**证书对应的密码**/
	private int is_test;/**是否为测试环境**/
	private String create_by;/**创建人**/
	private Date create_time;/**创建时间**/
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setPartner(String partner){
		this.partner=partner;
	}
	public String getPartner(){
		return partner;
	}
	public void setAppid(String appid){
		this.appid=appid;
	}
	public String getAppid(){
		return appid;
	}
	public void setPublic_key(String public_key){
		this.public_key=public_key;
	}
	public String getPublic_key(){
		return public_key;
	}
	public void setPrivate_key(String private_key){
		this.private_key=private_key;
	}
	public String getPrivate_key(){
		return private_key;
	}
	public void setNotify_url(String notify_url){
		this.notify_url=notify_url;
	}
	public String getNotify_url(){
		return notify_url;
	}
	public void setReturn_url(String return_url){
		this.return_url=return_url;
	}
	public String getReturn_url(){
		return return_url;
	}
	public void setSeller(String seller){
		this.seller=seller;
	}
	public String getSeller(){
		return seller;
	}
	public void setSign_type(String sign_type){
		this.sign_type=sign_type;
	}
	public String getSign_type(){
		return sign_type;
	}
	public void setInput_charset(String input_charset){
		this.input_charset=input_charset;
	}
	public String getInput_charset(){
		return input_charset;
	}
	public void setPay_type(String pay_type){
		this.pay_type=pay_type;
	}
	public String getPay_type(){
		return pay_type;
	}
	public void setMsg_type(String msg_type){
		this.msg_type=msg_type;
	}
	public String getMsg_type(){
		return msg_type;
	}
	public void setKeystore_path(String keystore_path){
		this.keystore_path=keystore_path;
	}
	public String getKeystore_path(){
		return keystore_path;
	}
	public void setStore_password(String store_password){
		this.store_password=store_password;
	}
	public String getStore_password(){
		return store_password;
	}
	public void setIs_test(int is_test){
		this.is_test=is_test;
	}
	public int getIs_test(){
		return is_test;
	}
	public void setCreate_by(String create_by){
		this.create_by=create_by;
	}
	public String getCreate_by(){
		return create_by;
	}
	public void setCreate_time(Date create_time){
		this.create_time=create_time;
	}
	public Date getCreate_time(){
		return create_time;
	}
}
