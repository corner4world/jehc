package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 视图
 * @author邓纯杰
 *
 */
public class XtDbView  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String table_schema;
	private String table_name;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_schema() {
		return table_schema;
	}

	public void setTable_schema(String table_schema) {
		this.table_schema = table_schema;
	}
}
