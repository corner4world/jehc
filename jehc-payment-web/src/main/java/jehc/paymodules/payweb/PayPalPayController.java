package jehc.paymodules.payweb;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jehc.paymodules.base.model.CurType;
import jehc.paymodules.base.model.MethodType;
import jehc.paymodules.base.model.PayOrder;
import jehc.paymodules.base.model.RefundOrder;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.http.HttpConfigDb;
import jehc.paymodules.paypal.api.PayPalConfigDao;
import jehc.paymodules.paypal.api.PayPalPayService;
import jehc.paymodules.paypal.bean.PayPalTransactionType;
/**
 * 发起支付入口
 */
@RestController
@RequestMapping("payPal")
public class PayPalPayController {
    private PayService service = null;
    @PostConstruct
    public void init() {
        PayPalConfigDao storage = new PayPalConfigDao();
        storage.setClientID("商户id");
        storage.setClientSecret("商户密钥");
        storage.setTest(true);
        //发起付款后的页面转跳地址
        storage.setReturnUrl("http://127.0.0.1:8088/pay/success");
        //取消按钮转跳地址,这里用异步通知地址的兼容的做法
        storage.setNotifyUrl("http://127.0.0.1:8088/pay/cancel");
        service = new PayPalPayService(storage);
        //请求连接池配置
        HttpConfigDb httpConfigDb = new HttpConfigDb();
        //最大连接数
        httpConfigDb.setMaxTotal(20);
        //默认的每个路由的最大连接数
        httpConfigDb.setDefaultMaxPerRoute(10);
        service.setRequestTemplateConfigDao(httpConfigDb);
    }

    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     * @param price 金额
     * @return 跳到支付页面
     */
    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay(BigDecimal price) {
        //及时收款
        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), PayPalTransactionType.sale);
        Map orderInfo = service.orderInfo(order);
        return service.buildRequest(orderInfo, MethodType.POST);
    }
    /**
     * 申请退款接口
     * @return 返回支付方申请退款后的结果
     */
    @RequestMapping("refund")
    public Map<String, Object> refund() {
        // TODO 这里需要  refundAmount， curType， description， tradeNo
        RefundOrder order = new RefundOrder();
        order.setCurType(CurType.USD);
        order.setDescription(" description ");
        order.setTradeNo("paypal 平台的单号");
        order.setRefundAmount(new BigDecimal(0.01));
        return service.refund(order);
    }

    /**
     * return url
     * PayPal确认付款调用的接口
     * 用户确认付款后，paypal调用的这个方法执行付款
     * @return 付款成功信息
     */
    
    @RequestMapping(value="payBack.json",method={RequestMethod.POST,RequestMethod.GET})
    public String executePayment(HttpServletRequest request) throws IOException {
        try (InputStream is = request.getInputStream()) {
            if (service.verify(service.getParameter2Map(request.getParameterMap(), is))) {
                // TODO 这里进行成功后的订单业务处理
                // TODO 返回成功付款页面，这个到时候再做一个漂亮的页面显示，并使用前后端分离的模式
                return "success";
            }
        }
        return "failure";
    }
}
