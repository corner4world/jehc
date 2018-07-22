package jehc.paymodules.payenum;
import jehc.paymentmodules.paymentmodel.PaymentAccount;
import jehc.paymodules.ali.apidao.AliPayConfigDaoImpl;
import jehc.paymodules.ali.apiservice.AliPayServiceImpl;
import jehc.paymodules.ali.model.AliTransactionType;
import jehc.paymodules.base.model.BasePayType;
import jehc.paymodules.base.model.MsgType;
import jehc.paymodules.base.model.TransactionType;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.http.HttpConfigDb;
import jehc.paymodules.fuiou.apidao.FuiouPayConfigDaoImpl;
import jehc.paymodules.fuiou.apiservice.FuiouPayServiceImpl;
import jehc.paymodules.fuiou.model.FuiouTransactionType;
import jehc.paymodules.payoneer.apidao.PayoneerConfigDaoImpl;
import jehc.paymodules.payoneer.apiservice.impl.PayoneerPayServiceImpl;
import jehc.paymodules.payoneer.model.PayoneerTransactionType;
import jehc.paymodules.paypal.api.PayPalConfigDao;
import jehc.paymodules.paypal.api.PayPalPayService;
import jehc.paymodules.paypal.bean.PayPalTransactionType;
import jehc.paymodules.union.apidao.UnionPayConfigDaoImpl;
import jehc.paymodules.union.apiservice.UnionPayServiceImpl;
import jehc.paymodules.union.model.UnionTransactionType;
import jehc.paymodules.wx.apidao.WxPayConfigDaoImpl;
import jehc.paymodules.wx.apiservice.WxPayServiceImpl;
import jehc.paymodules.wx.model.WxTransactionType;
import jehc.paymodules.wxyoudian.apidao.WxYouDianPayConfigDaoImpl;
import jehc.paymodules.wxyoudian.apiservice.WxYouDianPayServiceImpl;
import jehc.paymodules.wxyoudian.model.YoudianTransactionType;

/**
 * 支付类型
 */
public enum PayType implements BasePayType {
    aliPay{
        /**
         *  @see jehc.paymodules.ali.apiservice.AliPayServiceImpl 17年更新的版本,旧版本请自行切换
         * @param apyAccount
         * @return
         */
        public PayService getPayService(PaymentAccount apyAccount) {
            AliPayConfigDaoImpl aliPayConfigDaoImpl = new AliPayConfigDaoImpl();
            MsgType msgType = null;
            if("text".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.text;
            }
            if("json".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.json;
            }
            if("xml".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.xml;
            }
            //配置的附加参数的使用
            aliPayConfigDaoImpl.setAttach(apyAccount.getId());
            aliPayConfigDaoImpl.setPid(apyAccount.getPartner());
            aliPayConfigDaoImpl.setAppId(apyAccount.getAppid());
            aliPayConfigDaoImpl.setKeyPublic(apyAccount.getPublic_key());
            aliPayConfigDaoImpl.setKeyPrivate(apyAccount.getPrivate_key());
            aliPayConfigDaoImpl.setNotifyUrl(apyAccount.getNotify_url());
            aliPayConfigDaoImpl.setReturnUrl(apyAccount.getReturn_url());
            aliPayConfigDaoImpl.setSignType(apyAccount.getSign_type());
            aliPayConfigDaoImpl.setSeller(apyAccount.getSeller());
            aliPayConfigDaoImpl.setPayType(apyAccount.getPay_type().toString());
            aliPayConfigDaoImpl.setMsgType(msgType);
            aliPayConfigDaoImpl.setInputCharset(apyAccount.getInput_charset());
            aliPayConfigDaoImpl.setTest(apyAccount.getIs_test() == 0?true:false);
            //请求连接池配置
            HttpConfigDb httpConfigDb = new HttpConfigDb();
            //最大连接数
            httpConfigDb.setMaxTotal(20);
            //默认的每个路由的最大连接数
            httpConfigDb.setDefaultMaxPerRoute(10);
            return new AliPayServiceImpl(aliPayConfigDaoImpl, httpConfigDb);
        }
        public TransactionType getTransactionType(String transactionType) {
            // jehc.paymodules.ali.before.bean.AliTransactionType 17年更新的版本,旧版本请自行切换
            // AliTransactionType 17年更新的版本,旧版本请自行切换
            return AliTransactionType.valueOf(transactionType);
        }
    },
    wxPay {
        public PayService getPayService(PaymentAccount apyAccount) {
        	MsgType msgType = null;
            if("text".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.text;
            }
            if("json".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.json;
            }
            if("xml".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.xml;
            }
            WxPayConfigDaoImpl wxPayConfigDaoImpl = new WxPayConfigDaoImpl();
            wxPayConfigDaoImpl.setMchId(apyAccount.getPartner());
            wxPayConfigDaoImpl.setAppid(apyAccount.getAppid());
            //转账公钥，转账时必填
            wxPayConfigDaoImpl.setKeyPublic(apyAccount.getPublic_key());
            wxPayConfigDaoImpl.setSecretKey(apyAccount.getPrivate_key());
            wxPayConfigDaoImpl.setNotifyUrl(apyAccount.getNotify_url());
            wxPayConfigDaoImpl.setReturnUrl(apyAccount.getReturn_url());
            wxPayConfigDaoImpl.setSignType(apyAccount.getSign_type());
            wxPayConfigDaoImpl.setPayType(apyAccount.getPay_type().toString());
            wxPayConfigDaoImpl.setMsgType(msgType);
            wxPayConfigDaoImpl.setInputCharset(apyAccount.getInput_charset());
            wxPayConfigDaoImpl.setTest(apyAccount.getIs_test() == 0?true:false);
            //https证书设置 方式一
            /**
            WxPayConfigDaoImpl wxPayConfigDaoImpl = new WxPayConfigDaoImpl();
            wxPayConfigDaoImpl.setKeystore("证书信息串");
            wxPayConfigDaoImpl.setStorePassword("证书密码");
            //是否为证书地址
            wxPayConfigDaoImpl.setPath(false);
            return new WxPayService(wxPayConfigStorage, httpConfigStorage);
            **/
            return new WxPayServiceImpl(wxPayConfigDaoImpl);
        }
        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see WxTransactionType
         * @return
         */
        public TransactionType getTransactionType(String transactionType) {
            return WxTransactionType.valueOf(transactionType);
        }
    },
    youdianPay {
        public PayService getPayService(PaymentAccount apyAccount) {
        	MsgType msgType = null;
            if("text".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.text;
            }
            if("json".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.json;
            }
            if("xml".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.xml;
            }
            //集群的话,友店可能会有bug。暂未测试集群环境
            WxYouDianPayConfigDaoImpl wxYouDianPayConfigDaoImpl = new WxYouDianPayConfigDaoImpl();
            wxYouDianPayConfigDaoImpl.setKeyPrivate(apyAccount.getPrivate_key());
            wxYouDianPayConfigDaoImpl.setKeyPublic(apyAccount.getPublic_key());
//            wxYouDianPayConfigDaoImpl.setNotifyUrl(apyAccount.getNotifyUrl());
//            wxYouDianPayConfigDaoImpl.setReturnUrl(apyAccount.getReturnUrl());
            wxYouDianPayConfigDaoImpl.setSignType(apyAccount.getSign_type());
            wxYouDianPayConfigDaoImpl.setPayType(apyAccount.getPay_type().toString());
            wxYouDianPayConfigDaoImpl.setMsgType(msgType);
            wxYouDianPayConfigDaoImpl.setSeller(apyAccount.getSeller());
            wxYouDianPayConfigDaoImpl.setInputCharset(apyAccount.getInput_charset());
            wxYouDianPayConfigDaoImpl.setTest(apyAccount.getIs_test() == 0?true:false);
            return  new WxYouDianPayServiceImpl(wxYouDianPayConfigDaoImpl);
        }

        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see YoudianTransactionType
         * @return
         */
        public TransactionType getTransactionType(String transactionType) {
            return YoudianTransactionType.valueOf(transactionType);
        }
    },
    fuiou{
        public PayService getPayService(PaymentAccount apyAccount) {
        	MsgType msgType = null;
            if("text".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.text;
            }
            if("json".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.json;
            }
            if("xml".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.xml;
            }
            FuiouPayConfigDaoImpl fuiouPayConfigDaoImpl = new FuiouPayConfigDaoImpl();
            fuiouPayConfigDaoImpl.setKeyPublic(apyAccount.getPublic_key());
            fuiouPayConfigDaoImpl.setKeyPrivate(apyAccount.getPrivate_key());
            fuiouPayConfigDaoImpl.setNotifyUrl(apyAccount.getNotify_url());
            fuiouPayConfigDaoImpl.setReturnUrl(apyAccount.getReturn_url());
            fuiouPayConfigDaoImpl.setSignType(apyAccount.getSign_type());
            fuiouPayConfigDaoImpl.setPayType(apyAccount.getPay_type().toString());
            fuiouPayConfigDaoImpl.setMsgType(msgType);
            fuiouPayConfigDaoImpl.setInputCharset(apyAccount.getInput_charset());
            fuiouPayConfigDaoImpl.setTest(apyAccount.getIs_test() == 0?true:false);
            return new FuiouPayServiceImpl(fuiouPayConfigDaoImpl);
        }
        public TransactionType getTransactionType(String transactionType) {
            return FuiouTransactionType.valueOf(transactionType);
        }
    },
    unionPay{
        public PayService getPayService(PaymentAccount apyAccount) {
        	MsgType msgType = null;
            if("text".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.text;
            }
            if("json".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.json;
            }
            if("xml".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.xml;
            }
            UnionPayConfigDaoImpl unionPayConfigDaoImpl = new UnionPayConfigDaoImpl();
            unionPayConfigDaoImpl.setMerId(apyAccount.getPartner());
            unionPayConfigDaoImpl.setCertSign(true);
            unionPayConfigDaoImpl.setKeyPublic(apyAccount.getPublic_key());
            unionPayConfigDaoImpl.setKeyPrivate(apyAccount.getPrivate_key());
            unionPayConfigDaoImpl.setNotifyUrl(apyAccount.getNotify_url());
            unionPayConfigDaoImpl.setReturnUrl(apyAccount.getReturn_url());
            unionPayConfigDaoImpl.setSignType(apyAccount.getSign_type());
            unionPayConfigDaoImpl.setPayType(apyAccount.getPay_type().toString());
            unionPayConfigDaoImpl.setMsgType(msgType);
            unionPayConfigDaoImpl.setInputCharset(apyAccount.getInput_charset());
            unionPayConfigDaoImpl.setTest(apyAccount.getIs_test() == 0?true:false);
            return new UnionPayServiceImpl(unionPayConfigDaoImpl);
        }
        public TransactionType getTransactionType(String transactionType) {
            return UnionTransactionType.valueOf(transactionType);
        }
    },
    payoneer{
        public PayService getPayService(PaymentAccount apyAccount) {
        	MsgType msgType;
            if("text".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.text;
            }
            if("json".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.json;
            }
            if("xml".equals(apyAccount.getMsg_type())){
            	msgType= MsgType.xml;
            }
            PayoneerConfigDaoImpl payoneerConfigDaoImpl = new PayoneerConfigDaoImpl();
            //设置商户Id
            payoneerConfigDaoImpl.setProgramId(apyAccount.getPartner());
            payoneerConfigDaoImpl.setMsgType(MsgType.json);
            payoneerConfigDaoImpl.setInputCharset("utf-8");
            //"PayoneerPay 用户名"
            payoneerConfigDaoImpl.setUserName(apyAccount.getSeller());
            //PayoneerPay API password
            payoneerConfigDaoImpl.setApiPassword(apyAccount.getPrivate_key());
            //是否为沙箱
            payoneerConfigDaoImpl.setTest(true);
            return new PayoneerPayServiceImpl(payoneerConfigDaoImpl);
        }
        public TransactionType getTransactionType(String transactionType) {
            return PayoneerTransactionType.valueOf(transactionType);
        }
    },
    payPal{
        public PayService getPayService(PaymentAccount apyAccount) {
            PayPalConfigDao storage = new PayPalConfigDao();
            //配置的附加参数的使用
            storage.setAttach(apyAccount.getId());
            storage.setClientID(apyAccount.getAppid());
            storage.setClientSecret(apyAccount.getPrivate_key());
            storage.setTest(true);
            //发起付款后的页面转跳地址
            storage.setReturnUrl(apyAccount.getReturn_url());
            //取消按钮转跳地址,这里兼容的做法
            storage.setNotifyUrl(apyAccount.getNotify_url());
            return new PayPalPayService(storage);
        }
        public TransactionType getTransactionType(String transactionType) {
            return PayPalTransactionType.valueOf(transactionType);
        }
    };
    public abstract PayService getPayService(PaymentAccount apyAccount);
}
