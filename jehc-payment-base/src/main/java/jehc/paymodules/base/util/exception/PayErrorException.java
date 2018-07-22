package jehc.paymodules.base.util.exception;

import jehc.paymodules.base.util.exception.entity.PayError;

/**
 * 支付异常
 * @author Administrator
 *
 */
public class PayErrorException extends RuntimeException  {
    private PayError error;
    public PayErrorException(PayError error) {
        super(error.getString());
        this.error = error;
    }
    public PayError getPayError() {
        return error;
    }
}
