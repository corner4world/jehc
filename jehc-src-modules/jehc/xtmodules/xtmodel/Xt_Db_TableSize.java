package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 表大小
 * @author 邓纯杰
 */
public class Xt_Db_TableSize  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String table_name;
	private String data_length;
	private String index_length;
	private String table_rows;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getData_length() {
		return data_length;
	}
	public void setData_length(String data_length) {
		this.data_length = data_length;
	}
	public String getIndex_length() {
		return index_length;
	}
	public void setIndex_length(String index_length) {
		this.index_length = index_length;
	}
	public String getTable_rows() {
		return table_rows;
	}
	public void setTable_rows(String table_rows) {
		this.table_rows = table_rows;
	}
}
