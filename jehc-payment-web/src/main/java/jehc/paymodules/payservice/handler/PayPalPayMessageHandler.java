package jehc.paymodules.payservice.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.apiservice.PayMessageHandler;
import jehc.paymodules.base.util.exception.PayErrorException;

/**
 * PayPal支付回调处理器
 */
@Component
public class PayPalPayMessageHandler implements PayMessageHandler {
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        return payService.getPayOutMessage("fail", "失败");
    }
}
