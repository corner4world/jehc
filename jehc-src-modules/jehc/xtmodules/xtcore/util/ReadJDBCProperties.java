package jehc.xtmodules.xtcore.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取JDBC配置文件
 * @author 邓纯杰
 *
 */
public class ReadJDBCProperties {
	/**
	 * 读取配置文件
	 * @return
	 */
	public static Properties readProperties(){
		Properties pro = new Properties();
		try {
			pro.load(ReadJDBCProperties.class.getClassLoader().getResourceAsStream("jehc/xtmodules/xtcore/sources/jdbc.properties"));
		} catch (IOException e) {
			System.out.println("未找到配置文件！！！");
		}
		return pro;
	}
	/**
	 * 返回数据库类型1.Mysql 2.Oracle 3.Sqlserver 4.DB2 5.Sybase 6.其他
	 * @return
	 */
	public static int validateDriver(){
		Properties pro = ReadJDBCProperties.readProperties();
		String driverClassName = pro.get("driverClassName").toString().toLowerCase();
		if(driverClassName.indexOf("mysql")>0){
			return 1;
		}else if(driverClassName.indexOf("oracle")>0){
			return 2;
		}else if(driverClassName.indexOf("sqlserver")>0){
			return 3;
		}else if(driverClassName.indexOf("db2")>0){
			return 4;
		}else if(driverClassName.indexOf("sybase")>0){
			return 5;
		}else{
			return 6;
		}
	}
}
