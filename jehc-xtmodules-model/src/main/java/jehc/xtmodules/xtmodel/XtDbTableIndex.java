package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 表索引
 * @author 邓纯杰
 */
public class XtDbTableIndex  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String table;
	private String non_unique;
	private String key_name;
	private String seq_in_index;
	private String column_Name;
	private String collation;
	private String cardinality;
	private String sub_part;
	private String packed;
	private String isNull;
	private String index_type;
	private String comment;
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getNon_unique() {
		return non_unique;
	}
	public void setNon_unique(String non_unique) {
		this.non_unique = non_unique;
	}
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	public String getSeq_in_index() {
		return seq_in_index;
	}
	public void setSeq_in_index(String seq_in_index) {
		this.seq_in_index = seq_in_index;
	}
	public String getColumn_Name() {
		return column_Name;
	}
	public void setColumn_Name(String column_Name) {
		this.column_Name = column_Name;
	}
	public String getCollation() {
		return collation;
	}
	public void setCollation(String collation) {
		this.collation = collation;
	}
	public String getCardinality() {
		return cardinality;
	}
	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}
	public String getSub_part() {
		return sub_part;
	}
	public void setSub_part(String sub_part) {
		this.sub_part = sub_part;
	}
	public String getPacked() {
		return packed;
	}
	public void setPacked(String packed) {
		this.packed = packed;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getIndex_type() {
		return index_type;
	}
	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
