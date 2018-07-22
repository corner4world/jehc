package jehc.paymodules.paypal.api;
import jehc.paymodules.base.dao.impl.PayConfigDaoImpl;
/**
 * 贝宝支付配置存储
 * @author Administrator
 *
 */
public class PayPalConfigDao extends PayConfigDaoImpl {

    private volatile String clientID;

    public String getAppid() {
        return clientID;
    }

    public String getPid() {
        return clientID;
    }

    public String getSeller() {
        return clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return getKeyPrivate();
    }

    public void setClientSecret(String clientSecret) {
       setKeyPrivate(clientSecret);
    }

    public boolean isAccessTokenExpired() {
        if (getExpiresTime() == 0){
            return true;
        }
        return (getExpiresTime() - System.currentTimeMillis() / 1000) <= 0;
    }

    /**
     * 设置取消页面的url
     * @param cancelUrl 取消页面的url
     */
    public void setCancelUrl(String cancelUrl){
        setNotifyUrl(cancelUrl);
    }
    /**
     * 获取取消页面的url
     */
    public String getCancelUrl(){
      return getNotifyUrl();
    }
}
