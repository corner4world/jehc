package jehc.paymodules.payservice.handler;

import jehc.paymodules.base.util.apiservice.PayMessageHandler;
public abstract class BasePayMessageHandler implements PayMessageHandler {
    //支付账户id
    private String payId;

    public BasePayMessageHandler(String payId) {
        this.payId = payId;
    }

    public String getPayId() {
        return payId;
    }
}
