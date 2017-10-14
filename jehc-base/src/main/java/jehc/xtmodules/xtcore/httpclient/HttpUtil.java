package jehc.xtmodules.xtcore.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HTTP工具类 发送http/https协议get/post请求，发送map，json，xml，txt数据
 * 
 */
public class HttpUtil {
	private static Log log = LogFactory.getLog(HttpUtil.class);

	/**
	 * 执行一个http/https get请求，返回请求响应的文本数据
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的文本数据
	 */
	public static String doGet(String url, String queryString, String charset,boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		if (url.startsWith("https")) {
			// https请求
			Protocol myhttps = new Protocol("https",new SSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
		}
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				// 对get请求参数编码，汉字编码后，就成为%式样的字符串
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (URIException e) {
			log.error("执行Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			log.error("执行Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	/**
	 * 执行一个http/https post请求，返回请求响应的文本数据
	 * @param url 请求的URL地址
	 * @param params 请求的查询参数,可以为null
	 * @param charset 字符集
	 * @param pretty 是否美化
	 * @return 返回请求响应的文本数据
	 */
	public static String doPost(String url, Map<String, String> params,String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		if (url.startsWith("https")) {
			// https请求
			Protocol myhttps = new Protocol("https",new SSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
		}
		PostMethod method = new PostMethod(url);
		// 设置参数的字符集
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);
		// 设置post数据
		if (params != null) {
			// HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				// p.setParameter(entry.getKey(), entry.getValue());
				method.setParameter(entry.getKey(), entry.getValue());
			}
			// method.setParams(p);
		}
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			log.error("执行Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	/**
	 * 执行一个http/https post请求， 直接写数据 json,xml,txt
	 * @param url 请求的URL地址
	 * @param params  请求的查询参数,可以为null
	 * @param charset  字符集
	 * @param pretty 是否美化
	 * @return 返回请求响应的文本数据
	 */
	public static String writePost(String url, String content, String charset,boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		if (url.startsWith("https")) {
			// https请求
			Protocol myhttps = new Protocol("https",new SSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);
		}
		PostMethod method = new PostMethod(url);
		try {
			// 设置请求头部类型参数
			// method.setRequestHeader("Content-Type","text/plain;
			// charset=utf-8");//application/json,text/xml,text/plain
			// method.setRequestBody(content);
			// //InputStream,NameValuePair[],String
			// RequestEntity是个接口，有很多实现类，发送不同类型的数据
			RequestEntity requestEntity = new StringRequestEntity(content,"text/plain", charset);// application/json,text/xml,text/plain
			method.setRequestEntity(requestEntity);
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (Exception e) {
			log.error("执行Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	public static void main(String[] args) {
		try {
			String y = doGet("http://video.sina.com.cn/life/tips.html", null,"GBK", true);
			System.out.println(y);
			// Map params = new HashMap();
			// params.put("param1", "value1");
			// params.put("json", "{\"aa\":\"11\"}");
			// String j =
			// doPost("http://localhost/uplat/manage/test.do?reqCode=add",
			// params, "UTF-8", true);
			// System.out.println(j);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
