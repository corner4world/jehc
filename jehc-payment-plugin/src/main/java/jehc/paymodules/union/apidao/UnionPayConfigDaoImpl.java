package jehc.paymodules.union.apidao;

import jehc.paymodules.base.dao.impl.PayConfigDaoImpl;
/**
 * unionPay
 * @author Administrator
 *
 */
public class UnionPayConfigDaoImpl extends PayConfigDaoImpl {
    /**
     * 商户号
     */
    private volatile String merId;
    /**
     * 商户收款账号
     */
    private volatile String seller;

    private volatile String version = "5.1.0";
    /**
     * 0：普通商户直连接入
     * 1： 收单机构
     * 2：平台类商户接入
     */
    private volatile String accessType = "0";

    public void setKeyPrivate(String keyPrivate) {
        super.setKeyPrivate(keyPrivate);
        if (isCertSign() && keyPrivate.length() < 1024 && keyPrivate.contains(";")){
            String[] split = keyPrivate.split(";");
            setKeyPrivateCertPwd( split[1]);
            super.setKeyPrivate(split[0]);
            getCertDescriptor().initPrivateSignCert(getKeyPrivate(), getKeyPrivateCertPwd(), "PKCS12");
        }
    }

    public void setKeyPublic(String keyPublic) {
        super.setKeyPublic(keyPublic);
        if (isCertSign() && keyPublic.length() < 1024 ){
            String[] split = keyPublic.split(";");
            getCertDescriptor().initPublicCert(split[0]);
            getCertDescriptor().initRootCert(split[1]);
        }
    }
    public String getAppid () {
        return null;
    }

    /**
     * @return 合作者id
     * @see #getPid()
     */
    @Deprecated
    public String getPartner () {
        return merId;
    }

    /**
     * 设置合作者id
     * @param partner 合作者id
     * @see #setPid(String)
     */
    @Deprecated
    public void setPartner (String partner) {
        this.merId = partner;
    }

    public String getPid () {
        return merId;
    }

    public void setPid (String pid) {
        this.merId = pid;
    }
    public String getSeller () {
        return seller;
    }

    public void setSeller (String seller) {
        this.seller = seller;
    }

    public String getMerId () {
        return merId;
    }

    public void setMerId (String merId) {
        this.merId = merId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
