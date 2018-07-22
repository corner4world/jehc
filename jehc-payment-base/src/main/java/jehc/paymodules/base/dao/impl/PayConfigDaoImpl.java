package jehc.paymodules.base.dao.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import jehc.paymodules.base.dao.PayConfigDao;
import jehc.paymodules.base.model.MsgType;
import jehc.paymodules.base.util.exception.PayErrorException;
import jehc.paymodules.base.util.exception.entity.PayException;
import jehc.paymodules.base.util.sign.CertDescriptor;

/**
 * 支付基础配置存储
 */
public abstract class PayConfigDaoImpl implements PayConfigDao{

    private volatile Object attach;
    /**
     * 证书管理器
     */
    private volatile CertDescriptor certDescriptor;

    /**
     *   应用私钥，rsa_private pkcs8格式 生成签名时使用
     */
    private volatile  String keyPrivate;
    /**
     *   应用私钥，rsa_private pkcs8格式 生成签名时使用
     */
    private volatile  String keyPrivateCertPwd;
    /**
     *  支付平台公钥(签名校验使用)
     */
    private volatile  String keyPublic;
    /**
     * 异步回调地址
     */
    private volatile  String notifyUrl;
    /**
     * 同步回调地址，支付完成后展示的页面
     */
    private volatile  String returnUrl;
    /**
     * 签名加密类型
     */
    private volatile  String signType;
    /**
     * 字符类型
     */
    private volatile  String inputCharset;


    /**
     * 支付类型 aliPay 支付宝， wxPay微信..等等，扩展支付模块定义唯一。
     */
    private volatile  String payType;
    /**
     * 消息来源类型
     */
    private volatile MsgType msgType;
    /**
     *  访问令牌 每次请求其他方法都要传入的值
     */
    private volatile String accessToken;
    /**
     * access token 到期时间时间戳
     */
    private volatile long expiresTime;
    /**
     * 授权码锁
     */
    private Lock accessTokenLock = new ReentrantLock();
    /**
     * 是否为沙箱环境，默认为正式环境
     */
    private boolean isTest = false;
    /**
     * 是否为证书签名
     */
    private boolean isCertSign = false;
    
    public Object getAttach() {
        return attach;
    }
    public void setAttach(Object attach) {
        this.attach = attach;
    }
    public CertDescriptor getCertDescriptor() {
        if (!isCertSign){
           throw new PayErrorException(new PayException("certDescriptor fail", "isCertSign is false"));
        }
        if(null == certDescriptor){
            certDescriptor = new CertDescriptor();
        }
        return certDescriptor;
    }
    public String getKeyPrivate() {
        return keyPrivate;
    }
    public void setKeyPrivate(String keyPrivate) {
        this.keyPrivate = keyPrivate;
    }
    public String getKeyPrivateCertPwd() {
        return keyPrivateCertPwd;
    }
    public void setKeyPrivateCertPwd(String keyPrivateCertPwd) {
        this.keyPrivateCertPwd = keyPrivateCertPwd;
    }
    public String getKeyPublic() {
        return keyPublic;
    }
    public void setKeyPublic(String keyPublic) {
        this.keyPublic = keyPublic;
    }
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    public String getReturnUrl() {
        return returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getInputCharset() {
        return inputCharset;
    }
    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public MsgType getMsgType() {
        return msgType;
    }
    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public Lock getAccessTokenLock() {
        return this.accessTokenLock;
    }
    public long getExpiresTime() {
        return expiresTime;
    }
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 600) * 1000L;
    }
    public synchronized void updateAccessToken(String accessToken, long expiresTime) {
        this.accessToken = accessToken;
        this.expiresTime = expiresTime;
    }
    public void expireAccessToken() {
        this.expiresTime = 0;
    }
    public String getToken() {
        return null;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }
    public void setAccessTokenLock(Lock accessTokenLock) {
        this.accessTokenLock = accessTokenLock;
    }
    public boolean isTest() {
        return isTest;
    }
    public void setTest(boolean test) {
        isTest = test;
    }
    public boolean isCertSign() {
        return isCertSign;
    }
    public void setCertSign(boolean certSign) {
        isCertSign = certSign;
        if (certSign){
            certDescriptor = new CertDescriptor();
        }
    }
}
