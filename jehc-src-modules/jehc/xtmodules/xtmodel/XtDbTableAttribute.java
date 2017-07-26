package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 表中更多属性
 * @author 邓纯杰
 */
public class XtDbTableAttribute  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String engine;
	private String version;
	private String row_format;
	private String rows;
	private String avg_row_length;
	private String data_length;
	private String max_data_length;
	private String index_length;
	private String data_free;
	private String auto_increment;
	private String create_time;
	private String update_time;
	private String check_time;
	private String collation;
	private String checksum;
	private String create_options;
	private String comment;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRow_format() {
		return row_format;
	}
	public void setRow_format(String row_format) {
		this.row_format = row_format;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getAvg_row_length() {
		return avg_row_length;
	}
	public void setAvg_row_length(String avg_row_length) {
		this.avg_row_length = avg_row_length;
	}
	public String getData_length() {
		return data_length;
	}
	public void setData_length(String data_length) {
		this.data_length = data_length;
	}
	public String getMax_data_length() {
		return max_data_length;
	}
	public void setMax_data_length(String max_data_length) {
		this.max_data_length = max_data_length;
	}
	public String getIndex_length() {
		return index_length;
	}
	public void setIndex_length(String index_length) {
		this.index_length = index_length;
	}
	public String getData_free() {
		return data_free;
	}
	public void setData_free(String data_free) {
		this.data_free = data_free;
	}
	public String getAuto_increment() {
		return auto_increment;
	}
	public void setAuto_increment(String auto_increment) {
		this.auto_increment = auto_increment;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCheck_time() {
		return check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}
	public String getCollation() {
		return collation;
	}
	public void setCollation(String collation) {
		this.collation = collation;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getCreate_options() {
		return create_options;
	}
	public void setCreate_options(String create_options) {
		this.create_options = create_options;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
