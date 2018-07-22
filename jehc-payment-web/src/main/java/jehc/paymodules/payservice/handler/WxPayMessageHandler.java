package jehc.paymodules.payservice.handler;

import java.util.Map;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.exception.PayErrorException;

/**
 * 微信支付回调处理器
 */
public class WxPayMessageHandler extends BasePayMessageHandler {
    public WxPayMessageHandler(String payId) {
        super(payId);
    }
    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if ("SUCCESS".equals(payMessage.getPayMessage().get("result_code"))){
            /////这里进行成功的处理
            return  payService.getPayOutMessage("SUCCESS", "OK");
        }
        return  payService.getPayOutMessage("FAIL", "失败");
    }
}
