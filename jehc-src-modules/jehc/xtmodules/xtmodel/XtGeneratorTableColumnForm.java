package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 增 编辑 明细表单字段
 * @author 邓纯杰
 *
 */
public class XtGeneratorTableColumnForm implements Serializable{
	private static final long serialVersionUID = 1L;
	private String column_name;/**字段名称**/
	private String data_type;/**字段类型**/
	private String column_comment;/**字段说明**/
	private String character_maximum_length;/**字段长度**/
	private String column_key;/**字段性质:PRI为主键**/
	private String is_nullable;/**是否必填YES,NO**/
	private String column_label_position;/**label标签位置0缺省正常位置1TOP上部**/
	private String column_label_anchor;/**标签宽度**/
	private String column_type;/**类型0文本框1文本域2数字框3下拉框4日期框5文件框**/
	private String isHidden;/**是否隐藏该列0否1是**/
	private String xt_script_id;/**脚本编号**/
	public String getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getColumn_comment() {
		return column_comment;
	}
	public void setColumn_comment(String column_comment) {
		this.column_comment = column_comment;
	}
	public String getCharacter_maximum_length() {
		return character_maximum_length;
	}
	public void setCharacter_maximum_length(String character_maximum_length) {
		this.character_maximum_length = character_maximum_length;
	}
	public String getColumn_key() {
		return column_key;
	}
	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}
	public String getIs_nullable() {
		return is_nullable;
	}
	public void setIs_nullable(String is_nullable) {
		this.is_nullable = is_nullable;
	}
	public String getColumn_label_position() {
		return column_label_position;
	}
	public void setColumn_label_position(String column_label_position) {
		this.column_label_position = column_label_position;
	}
	public String getColumn_label_anchor() {
		return column_label_anchor;
	}
	public void setColumn_label_anchor(String column_label_anchor) {
		this.column_label_anchor = column_label_anchor;
	}
	public String getColumn_type() {
		return column_type;
	}
	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}
	public String getXt_script_id() {
		return xt_script_id;
	}
	public void setXt_script_id(String xt_script_id) {
		this.xt_script_id = xt_script_id;
	}
	
	
}
