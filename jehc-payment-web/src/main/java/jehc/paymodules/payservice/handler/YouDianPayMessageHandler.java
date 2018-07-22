package jehc.paymodules.payservice.handler;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.exception.PayErrorException;
public class YouDianPayMessageHandler extends BasePayMessageHandler {
    public YouDianPayMessageHandler(String payId) {
        super(payId);
    }
    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        Map<String, Object> message = payMessage.getPayMessage();
        //上下文对象中获取账单
//        AmtApply amtApply = (AmtApply)context.get("amtApply");
        //日志存储
//        amtPaylogService.createAmtPaylogByCallBack(amtApply,  message.toString());
        if ("SUCCESS".equals(message.get("return_code"))){
            /////这里进行成功的处理，因没有返回金额
        }
        return  PayOutMessage.TEXT().content(JSON.toJSONString(message)).build();
    }
}
