package jehc.xtmodules.xtcore.util.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 数据库存放信息 用于JDBC取相关信息
 * @author 邓纯杰
 *
 */
public class DbInfo {
	private List<Map<String, Object>> tables = new ArrayList<Map<String,Object>>();;
	private List<Map<String, Object>> columns = new ArrayList<Map<String,Object>>();
	private List<Map<String, Object>> columnsPrimary = new ArrayList<Map<String,Object>>();
	private String ip;
	private String port;
	private String dbname;
	private String username;
	private String password;
	private String databasetype;
	public List<Map<String, Object>> getTables() {
		return tables;
	}
	public void setTables(List<Map<String, Object>> tables) {
		this.tables = tables;
	}
	public List<Map<String, Object>> getColumns() {
		return columns;
	}
	public void setColumns(List<Map<String, Object>> columns) {
		this.columns = columns;
	}
	public List<Map<String, Object>> getColumnsPrimary() {
		return columnsPrimary;
	}
	public void setColumnsPrimary(List<Map<String, Object>> columnsPrimary) {
		this.columnsPrimary = columnsPrimary;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabasetype() {
		return databasetype;
	}
	public void setDatabasetype(String databasetype) {
		this.databasetype = databasetype;
	}
	
	
}
