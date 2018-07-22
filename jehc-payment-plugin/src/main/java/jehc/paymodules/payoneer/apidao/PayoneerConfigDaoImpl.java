package jehc.paymodules.payoneer.apidao;
import jehc.paymodules.base.dao.impl.PayConfigDaoImpl;

/**
 * Payoneer P卡 支付 配置
 */
public class PayoneerConfigDaoImpl extends PayConfigDaoImpl {
    /**
     * 商户Id
     */
    public String programId;
    /**
     *  PayoneerPay 用户名
     */
    public String userName;

    /**
     *  应用id
     * @return 空
     */
    @Override
    public String getAppid() {
        return null;
    }



    /**
     * 合作商唯一标识
     *
     */
    @Override
    public String getPid () {
        return programId;
    }

    @Override
    public String getSeller() {
        return userName;
    }

    /**
     *  获取商户Id
     * @return 商户Id
     */
    public String getProgramId() {
        return programId;
    }

    /**
     *  设置商户Id
     * @param programId 商户Id
     */
    public void setProgramId(String programId) {
        this.programId = programId;
    }

    /**
     *  PayoneerPay 用户名
     * @param userName 用户名
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    /**
     *  设置PayoneerPay API password
     * @param apiPassword api密钥
     */
    public void setApiPassword(String apiPassword){
        setKeyPrivate(apiPassword);
    }
    /**
     *  获取 PayoneerPay API password
     */
    public String getApiPassword(){
       return getKeyPrivate();
    }
}
