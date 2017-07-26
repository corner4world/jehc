package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 数据库表
 * @author邓纯杰
 */
public class XtDbStructure  implements Serializable{
	private static final long serialVersionUID = 1L;
	//////数据库表字段结构 begin////
	private String field;
	private String type;
	private String collation;
	private String isNull;
	private String key;
	private String defaults;
	private String extra;
	private String privileges;
	private String comment;
	//////end///////////////
	private String table;
	private String createTable;
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getCreateTable() {
		return createTable;
	}
	public void setCreateTable(String createTable) {
		this.createTable = createTable;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCollation() {
		return collation;
	}
	public void setCollation(String collation) {
		this.collation = collation;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
