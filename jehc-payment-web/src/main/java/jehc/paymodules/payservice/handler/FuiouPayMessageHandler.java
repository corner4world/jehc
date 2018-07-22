package jehc.paymodules.payservice.handler;

import java.util.Map;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.exception.PayErrorException;

public class FuiouPayMessageHandler extends BasePayMessageHandler {
    public FuiouPayMessageHandler(String payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if ("0000".equals(payMessage.getPayMessage().get("order_pay_code"))){
            /////这里进行成功的处理

            return PayOutMessage.JSON().content("success","成功").build();
        }

        return PayOutMessage.JSON().content("fail", "失败").build();
    }
}
