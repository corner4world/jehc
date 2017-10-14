package jehc.xtmodules.xtcore.httpclient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
public class SSLProtocolSocketFactory implements ProtocolSocketFactory{
	  private SSLContext sslcontext = null;     
	  private SSLContext createSSLContext() {     
	      SSLContext sslcontext=null;     
	      try {     
	          sslcontext = SSLContext.getInstance("TLS");  
	          sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());     
	      } catch (NoSuchAlgorithmException e) {     
	          e.printStackTrace();     
	      } catch (KeyManagementException e) {     
	          e.printStackTrace();     
	      }     
	      return sslcontext;     
	  }     
	     
	  private SSLContext getSSLContext() {     
	      if (this.sslcontext == null) {     
	          this.sslcontext = createSSLContext();     
	      }     
	      return this.sslcontext;     
	  }     
	  /**
	   * 创建Socket协议
	   */
	  public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {     
	      return getSSLContext().getSocketFactory().createSocket(     
	              socket,     
	              host,     
	              port,     
	              autoClose     
	          );     
	  }     
	  /**
	   * 创建Socket协议
	   */
	  public Socket createSocket(String host, int port) throws IOException,UnknownHostException {     
	      return getSSLContext().getSocketFactory().createSocket(     
              host,     
              port     
          );     
	  }     
	  /**
	   * 创建Socket协议
	   */
	  public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)throws IOException, UnknownHostException {     
	      return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);     
	  }     
	  /**
	   * 创建Socket协议
	   */
	  public Socket createSocket(String host, int port, InetAddress localAddress,int localPort, HttpConnectionParams params) throws IOException,UnknownHostException, ConnectTimeoutException {     
	      if (params == null) {     
	          throw new IllegalArgumentException("Parameters may not be null");     
	      }     
	      int timeout = params.getConnectionTimeout();     
	      SocketFactory socketfactory = getSSLContext().getSocketFactory();     
	      if (timeout == 0) {     
	          return socketfactory.createSocket(host, port, localAddress, localPort);     
	      } else {     
	          Socket socket = socketfactory.createSocket();     
	          SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);     
	          SocketAddress remoteaddr = new InetSocketAddress(host, port);     
	          socket.bind(localaddr);     
	          socket.connect(remoteaddr, timeout);     
	          return socket;     
	      } 
	  }  
}
