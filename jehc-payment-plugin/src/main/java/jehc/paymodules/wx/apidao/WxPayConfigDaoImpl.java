package jehc.paymodules.wx.apidao;

import jehc.paymodules.base.dao.impl.PayConfigDaoImpl;

/**
 * 微信配置存储
 */
public class WxPayConfigDaoImpl extends PayConfigDaoImpl {
    /**
     * 应用id
     */
    private   String appid ;
    /**
     *  商户号 合作者id
     */
    private  String mchId;
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 合作商唯一标识
     */
    public String getPid() {
        return mchId;
    }
    
    public String getSeller() {
        return null;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    /**
     *  为商户平台设置的密钥key
     * @return 微信密钥
     */
    public String getSecretKey() {
        return getKeyPrivate();
    }

    public void setSecretKey(String secretKey) {
         setKeyPrivate(secretKey);
    }
}
