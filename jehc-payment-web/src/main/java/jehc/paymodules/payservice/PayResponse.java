package jehc.paymodules.payservice;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import jehc.paymentmodules.paymentmodel.PaymentAccount;
import jehc.paymodules.base.dao.PayConfigDao;
import jehc.paymodules.base.model.MsgType;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.apiservice.PayMessageHandler;
import jehc.paymodules.base.util.apiservice.PayMessageRouter;
import jehc.paymodules.base.util.http.HttpConfigDb;
import jehc.paymodules.payenum.PayType;
import jehc.paymodules.payservice.handler.AliPayMessageHandler;
import jehc.paymodules.payservice.handler.FuiouPayMessageHandler;
import jehc.paymodules.payservice.handler.PayoneerMessageHandler;
import jehc.paymodules.payservice.handler.UnionPayMessageHandler;
import jehc.paymodules.payservice.handler.WxPayMessageHandler;
import jehc.paymodules.payservice.handler.YouDianPayMessageHandler;
import jehc.paymodules.payservice.interceptor.AliPayMessageInterceptor;
import jehc.paymodules.payservice.interceptor.YoudianPayMessageInterceptor;

/**
 * 支付响应对象
 */
public class PayResponse {
    @Resource
    private AutowireCapableBeanFactory beanFactory;
    private PayConfigDao payConfigDao;
    private PayService payService;
    private PayMessageRouter payMessageRouter;

    public PayResponse() {

    }

    /**
     * 初始化支付配置
     * @param apyAccount 账户信息
     */
    public void init(PaymentAccount apyAccount) {
        //根据不同的账户类型 初始化支付配置
    	PayType payType = null;
    	if(apyAccount.getPay_type().equals("aliPay")){
    		
    	}
    	
    	switch(apyAccount.getPay_type()){
	        case "aliPay":
	        	payType = PayType.aliPay;break;
	        case "wxPay":
	        	payType = PayType.wxPay;break;
	        case "youdianPay":
	        	payType = PayType.youdianPay;break;
	        case "fuiou":
	        	payType = PayType.fuiou;break;
	        case "unionPay":
	        	payType = PayType.unionPay;break;
	        case "payoneer":
	        	payType = PayType.payoneer;break;
	        case "payPal":
	        	payType = PayType.payPal;break;
	        default:
	        	payType = null;break;
        }
    	
        this.payService = payType.getPayService(apyAccount);
        this.payConfigDao = payService.getPayConfigDao();
        //这里设置http请求配置
        //payConfigDao.setRequestTemplateConfigStorage(getHttpConfigStorage());
        buildRouter(apyAccount.getId());
    }

    /**
     * 获取http配置，如果配置为null则为默认配置，无代理,无证书的请求方式。
     *  此处非必需
     * @param apyAccount 账户信息
     * @return 请求配置
     */
    public HttpConfigDb getHttpConfigStorage(PaymentAccount apyAccount){
        HttpConfigDb httpConfigStorage = new HttpConfigDb();
     /* 网路代理配置 根据需求进行设置*/
//        //http代理地址
//        httpConfigStorage.setHttpProxyHost("192.168.1.69");
//        //代理端口
//        httpConfigStorage.setHttpProxyPort(3308);
//        //代理用户名
//        httpConfigStorage.setHttpProxyUsername("user");
//        //代理密码
//        httpConfigStorage.setHttpProxyPassword("password");
        //设置ssl证书路径 https证书设置 方式二
        httpConfigStorage.setKeystorePath(apyAccount.getKeystore_path());
        //设置ssl证书对应的密码
        httpConfigStorage.setStorePassword(apyAccount.getStore_password());
        return httpConfigStorage;
    }


    /**
     * 配置路由
     * @param payId 指定账户id，用户多微信支付多支付宝支付
     */
    private void buildRouter(String payId) {
    	payMessageRouter = new PayMessageRouter(this.payService);
    	payMessageRouter.rule()
                //消息类型
                .msgType(MsgType.text.name())
                //支付账户事件类型
                .payType(PayType.aliPay.name())
                //拦截器
                .interceptor(new AliPayMessageInterceptor())
                //处理器
                .handler(beanFactory.getBean(AliPayMessageHandler.class))
                .end()
                .rule()
                .msgType(MsgType.xml.name())
                .payType(PayType.wxPay.name())
                .handler(autowire(new WxPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(MsgType.json.name())
                .payType(PayType.youdianPay.name())
                .interceptor(new YoudianPayMessageInterceptor()) //拦截器
                .handler(autowire(new YouDianPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(MsgType.xml.name())
                .payType(PayType.fuiou.name())
                .handler(autowire(new FuiouPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(MsgType.json.name())
                .payType(PayType.unionPay.name())
                .handler(autowire(new UnionPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(MsgType.json.name())
                .payType(PayType.payoneer.name())
                .handler(autowire(new PayoneerMessageHandler(payId)))
                .end()
                .rule()
                .msgType(MsgType.text.name())
                .payType(PayType.payPal.name())
                .handler(beanFactory.getBean(AliPayMessageHandler.class))
                .end()
        ;
    }

    private PayMessageHandler autowire(PayMessageHandler handler) {
    	beanFactory.autowireBean(handler);
        return handler;
    }

	public PayConfigDao getPayConfigDao() {
		return payConfigDao;
	}

	public void setPayConfigDao(PayConfigDao payConfigDao) {
		this.payConfigDao = payConfigDao;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public PayMessageRouter getPayMessageRouter() {
		return payMessageRouter;
	}

	public void setPayMessageRouter(PayMessageRouter payMessageRouter) {
		this.payMessageRouter = payMessageRouter;
	}

   
}
