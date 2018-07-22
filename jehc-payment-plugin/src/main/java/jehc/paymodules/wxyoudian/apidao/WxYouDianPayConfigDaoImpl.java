package jehc.paymodules.wxyoudian.apidao;

import jehc.paymodules.base.dao.impl.PayConfigDaoImpl;
/**
 * 支付客户端配置存储
 * @author Administrator
 *
 */
public class WxYouDianPayConfigDaoImpl extends PayConfigDaoImpl {
    public volatile String seller;//账号
    public String getAppid() {
        return null;
    }
    public String getPid() {
        return null;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public String getSeller() {
        return seller;
    }
    public void setToken(String accessToken) {
       setAccessToken(accessToken);
    }
    public String getToken() {
        return getAccessToken();
    }
}
