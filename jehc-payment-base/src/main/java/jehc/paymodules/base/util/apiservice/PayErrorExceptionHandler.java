package jehc.paymodules.base.util.apiservice;


import jehc.paymodules.base.util.exception.PayErrorException;


/**
 * PayErrorExceptionHandler处理器
 * @author Administrator
 *
 */
public interface PayErrorExceptionHandler {
    /**
     * 异常统一处理器
     * @param e 支付异常
     */
     void handle(PayErrorException e);
}
