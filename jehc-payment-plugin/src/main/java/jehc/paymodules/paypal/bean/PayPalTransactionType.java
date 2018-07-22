package jehc.paymodules.paypal.bean;

import jehc.paymodules.base.model.TransactionType;

/**
 * 贝宝交易类型
 * 说明交易类型主要用于支付接口调用参数所需
 */
public enum PayPalTransactionType implements TransactionType {
    /**
     * 获取token
     */
    AUTHORIZE("oauth2/token"),
    /**
     * 付款 网页支付
     */
    sale("payments/payment"),
    /**
     *  sale 支付退款
     */
    REFUND("payments/sale/{saleId}/refund"),
    REFUND_QUERY("payments/refund/{refundId}"),
    PAYOUT("payments/payouts/{payoutBatchId}"),
    ORDERS("payments/orders/{orderId}"),
    /**
     * 回调订单状态查询
     */
    EXECUTE("v1/payments/payment/{paymentId}/execute"),
    ;
    private String method;
    private PayPalTransactionType(String method) {
        this.method = method;
    }
    public String getType() {
        return this.name();
    }
    /**
     * 获取接口名称
     * @return 接口名称
     */
    public String getMethod() {
        return this.method;
    }
}
