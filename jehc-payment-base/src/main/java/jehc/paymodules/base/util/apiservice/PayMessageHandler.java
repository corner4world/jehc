package jehc.paymodules.base.util.apiservice;

import java.util.Map;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.exception.PayErrorException;



/**
 * 处理支付回调消息的处理器接口
 * @author Administrator
 *
 */
public interface PayMessageHandler {
    /**
     * @param payMessage 支付消息
     * @param context上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param payService 支付服务
     * @return xml,text格式的消息，如果在异步规则里处理的话，可以返回null
     */
    PayOutMessage handle(PayMessage payMessage,Map<String, Object> context,PayService payService) throws PayErrorException;
}
