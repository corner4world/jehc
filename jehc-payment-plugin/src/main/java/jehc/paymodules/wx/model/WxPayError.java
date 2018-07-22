package jehc.paymodules.wx.model;

import jehc.paymodules.base.util.exception.entity.PayError;

/**
 * 微信支付异常
 * @author Administrator
 *
 */
public class WxPayError implements PayError {
    private String errorCode;
    private String errorMsg;
    private String content;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    public WxPayError(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public WxPayError(String errorCode, String errorMsg, String content) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.content = content;
    }

    public String getString() {
    	return "支付错误: errcode=" + errorCode + ", errmsg=" + errorMsg + (null == content ? "" : "\n content:" + content);
    }
}
