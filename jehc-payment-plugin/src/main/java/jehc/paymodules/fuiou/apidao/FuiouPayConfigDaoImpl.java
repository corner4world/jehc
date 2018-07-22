package jehc.paymodules.fuiou.apidao;
import jehc.paymodules.base.dao.impl.PayConfigDaoImpl;

public class FuiouPayConfigDaoImpl extends PayConfigDaoImpl {
    public String mchntCd;//商户代码

    /**
     * 应用id
     * @return 空
     */
    public String getAppid() {
        return null;
    }

    /**
     * 合作商唯一标识
     */
    public String getPid () {
        return mchntCd;
    }

    public String getMchntCd () {
        return mchntCd;
    }
    public void setMchntCd (String mchntCd) {
        this.mchntCd = mchntCd;
    }
    public String getSeller() {
        return null;
    }

}
