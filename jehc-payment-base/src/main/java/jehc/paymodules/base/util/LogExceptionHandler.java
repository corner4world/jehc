package jehc.paymodules.base.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jehc.paymodules.base.util.apiservice.PayErrorExceptionHandler;
import jehc.paymodules.base.util.exception.PayErrorException;
/**
 * LogExceptionHandler 日志处理器
 * source chanjarster/weixin-java-tools
 * </pre>
 */
public class LogExceptionHandler implements PayErrorExceptionHandler {
    protected final Log log = LogFactory.getLog(PayErrorExceptionHandler.class);
    public void handle(PayErrorException e) {
        log.error("Error happens", e);
    }

}
