package jehc.paymodules.payservice.handler;

import java.util.Map;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.exception.PayErrorException;
import jehc.paymodules.payoneer.apiservice.impl.PayoneerPayServiceImpl;
public class PayoneerMessageHandler extends BasePayMessageHandler {
    public PayoneerMessageHandler(String payId) {
        super(payId);
    }
    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if ("0".equals(payMessage.getPayMessage().get(PayoneerPayServiceImpl.CODE))) {
            /////这里进行成功的处理
            return payService.successPayOutMessage(payMessage);
        }
        return payService.getPayOutMessage("fail", "失败");
    }
}
