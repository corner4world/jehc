package jehc.paymodules.wxyoudian.model;

import jehc.paymodules.base.util.exception.entity.PayError;

public class YdPayError implements PayError {
    private int errorcode;
    private String msg;
    private String content;
    public String getErrorCode() {
        return errorcode + "";
    }
    public String getErrorMsg() {
        return msg;
    }
    public YdPayError(int errorcode, String msg) {
        this.errorcode = errorcode;
        this.msg = msg;
    }
    public YdPayError(int errorcode, String msg, String content) {
        this.errorcode = errorcode;
        this.msg = msg;
        this.content = content;
    }
    public String getString() {
        return "支付错误: errcode=" + errorcode + ", msg=" + msg + (null == content ? "" : "\n content:" + content);
    }
}
