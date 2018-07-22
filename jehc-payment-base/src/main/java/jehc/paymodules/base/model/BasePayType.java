package jehc.paymodules.base.model;

/**
 * 基础的支付类型
 */
public interface BasePayType {
    /**
     * 根据支付类型获取交易类型
     * @param transactionType 类型值
     * @return  交易类型
     */
    TransactionType getTransactionType(String transactionType);
}
