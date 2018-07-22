package jehc.paymodules.base.util.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;


/**
 * 请求头
 * @author Administrator
 *
 */
public class HttpHeader{
    private List<Header> headers;//请求头
    public HttpHeader() {
    }

    public HttpHeader(List<Header> headers) {
        this.headers = headers;
    }

    /**
     * 请求头
     *
     * @param header 请求头
     */
    public HttpHeader(Header header) {
        addHeader(header);
    }

    /**
     * 获取请求头集
     *
     * @return 请求头集
     */
    public List<Header> getHeaders() {
        return headers;
    }

    /**
     * 添加请求头
     *
     * @param header 请求头
     */
    public void addHeader(Header header) {
        if (null == this.headers) {
            this.headers = new ArrayList<>();
        }
        this.headers.add(header);
    }

    /**
     * 设置请求头集
     *
     * @param headers 请求头集
     */
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    /**
     * 设置请求头集
     * @param headers 请求头集
     */
    public void setHeaders(Map<String, String> headers) {
        for (String key : headers.keySet()) {
            addHeader(new BasicHeader(key, headers.get(key)));
        }
    }
}
