package jehc.paymodules.base.util.apiservice;


import java.util.Map;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.exception.PayErrorException;


/**
 * 支付消息拦截器，可以用来做验证
 * @author Administrator
 */
public interface PayMessageInterceptor {
    /**
     * 拦截微信消息
     * @param payMessage 支付消息
     * @param context上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param payService 支付服务
     * @return true代表OK，false代表不OK
     */
     boolean intercept(PayMessage payMessage,Map<String, Object> context,PayService payService) throws PayErrorException;
}
