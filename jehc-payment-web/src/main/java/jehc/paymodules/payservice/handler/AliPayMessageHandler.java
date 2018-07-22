package jehc.paymodules.payservice.handler;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import jehc.paymodules.base.model.PayMessage;
import jehc.paymodules.base.model.PayOutMessage;
import jehc.paymodules.base.service.PayService;
import jehc.paymodules.base.util.apiservice.PayMessageHandler;
import jehc.paymodules.base.util.exception.PayErrorException;

/**
 * 支付宝支付回调处理器
 */
@Component
public class AliPayMessageHandler implements PayMessageHandler {
    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        Object payId = payService.getPayConfigDao().getAttach();

        Map<String, Object> message = payMessage.getPayMessage();
        //交易状态
        String trade_status = (String) message.get("trade_status");

        //上下文对象中获取账单
//        AmtApply amtApply = (AmtApply)context.get("amtApply");
        //日志存储
//        amtPaylogService.createAmtPaylogByCallBack(amtApply,  message.toString());
        //交易完成
        if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {

            BigDecimal payAmount = new BigDecimal((String) message.get("total_fee"));

            return payService.getPayOutMessage("success", "成功");

        }/* else if ("WAIT_BUYER_PAY".equals(trade_status) || "TRADE_CLOSED".equals(trade_status)) {

        }*/

        return payService.getPayOutMessage("fail", "失败");
    }
}
