package jehc.xtmodules.xtcore.allutils.file.ftp;

import java.io.Serializable;

/**
 * ftp配置
 * @author邓纯杰
 *
 */
public class FtpConfig implements Serializable{
	private static final long serialVersionUID = -6305079897518518204L;
	private static String url;
	private static int port;
	private static String username;
	private static String password;
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		FtpConfig.url = url;
	}
	public static int getPort() {
		return port;
	}
	public static void setPort(int port) {
		FtpConfig.port = port;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		FtpConfig.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		FtpConfig.password = password;
	}
	
}
