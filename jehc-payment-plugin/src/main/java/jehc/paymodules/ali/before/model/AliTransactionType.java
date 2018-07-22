package jehc.paymodules.ali.before.model;

import jehc.paymodules.base.model.TransactionType;

/**
 * 阿里交易类型
 * 说明交易类型主要用于支付接口调用参数所需
 * {@link #APP 新版app支付}
 */
@Deprecated
public enum AliTransactionType implements TransactionType {
    /**
     * 即时到帐
      */
    DIRECT("create_direct_pay_by_user"),
    /**
     * 移动支付
     */
    APP("mobile.securitypay.pay"),
    /**
     * 手机网站支付
     */
    WAP("alipay.wap.create.direct.pay.by.user"),

    //交易辅助接口

    /**
     * 交易订单查询
     */
    QUERY("alipay.trade.query"),
    /**
     * 交易订单关闭
     */
    CLOSE("alipay.trade.close"),
    /**
     * 退款
     */
    REFUND("alipay.trade.refund"),
    /**
     * 退款查询
     */
    REFUNDQUERY("alipay.trade.fastpay.refund.query"),
    /**
     * 下载对账单
     */
    DOWNLOADBILL("alipay.data.dataservice.bill.downloadurl.query")
    ;

    private String method;

    private AliTransactionType(String method) {
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
