package jehc.xtmodules.xtcore.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertisUtil {
	private static Logger logger = Logger.getLogger(PropertisUtil.class);

	private PropertisUtil() {
	}

	/**
	 *  读取配置文件某属性      
	 */
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			// 注意路径以 / 开始，没有则处理
			if (!filePath.startsWith("/"))
				filePath = "/" + filePath;
			InputStream in = PropertisUtil.class.getResourceAsStream(filePath);
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 读取文件并返回map
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> resultProperties(String filePath) {
		Map<String, Object> map = new HashMap<String, Object>();
		Properties pps = new Properties();
		try {
			pps.load(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
		while (enum1.hasMoreElements()) {
			String key = (String) enum1.nextElement();
			String value = pps.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 打印配置文件全部内容（filePath，配置文件名，如果有路径，props/test.properties）      
	 */
	public static void readProperties(String filePath) {
		Properties props = new Properties();
		try {
			if (!filePath.startsWith("/"))
				filePath = "/" + filePath;
			InputStream in = PropertisUtil.class.getResourceAsStream(filePath);
			props.load(in);
			Enumeration<?> en = props.propertyNames();
			//  遍历打印
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				logger.info(key + ":" + Property);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 将值写入配置文件      
	 * 
	 * @param fileName
	 * @param parameterName
	 * @param parameterValue
	 * @throws IOException
	 */
	public static void writeProperties(String fileName, String parameterName, String parameterValue)
			throws IOException {
		if (fileName.startsWith("/"))
			fileName.substring(1);
		String filePath = PropertisUtil.class.getResource("/").getPath() + fileName;
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		pps.load(in);
		in.close();
		OutputStream out = new FileOutputStream(filePath);
		// 设置配置名称和值
		pps.setProperty(parameterName, parameterValue);
		// comments 等于配置文件的注释
		pps.store(out, "Update " + parameterName + " name");
		out.flush();
		out.close();
	}
}
