package jehc.xtmodules.xtcore.util;
/**
 * 自定义异常类
 * @author 邓纯杰
 *
 */
public class ExceptionUtil extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ExceptionUtil(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ExceptionUtil(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionUtil(String message) {
        super(message);
    }

    public ExceptionUtil(Throwable cause) {
        super(cause);
    }
}
