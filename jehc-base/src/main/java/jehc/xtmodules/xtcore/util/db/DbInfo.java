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
}
