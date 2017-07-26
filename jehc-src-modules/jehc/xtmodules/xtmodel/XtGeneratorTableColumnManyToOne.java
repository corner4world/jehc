package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 代码生成-表字段信息
 * @author邓纯杰
 *
 */
public class XtGeneratorTableColumnManyToOne implements Serializable{
	private static final long serialVersionUID = 1L;
	private String COLUMN_NAME;//字段名称
	private String DATA_TYPE;//字段类型
	private String COLUMN_COMMENT;//字段说明
	private String CHARACTER_MAXIMUM_LENGTH;//字段长度
	private String COLUMN_KEY;//字段性质:PRI为主键
	private String IS_NULLABLE;//是否必填YES,NO
	
	///////////////以下为扩展字段//////////////////
	private String column_label_position;/**label标签位置缺省正常位置TOP上部**/
	private String column_label_anchor;/**标签宽度**/
	private String column_type;/**类型0文本框1文本域2数字框3下拉框4日期框**/
	private String isHidden;/**是否隐藏该列0否1是**/
	private String xt_script_id;/**脚本编号**/
	private String xt_script_text;
	public String getCOLUMN_KEY() {
		return COLUMN_KEY;
	}
	public void setCOLUMN_KEY(String cOLUMNKEY) {
		COLUMN_KEY = cOLUMNKEY;
	}
	public String getCHARACTER_MAXIMUM_LENGTH() {
		return CHARACTER_MAXIMUM_LENGTH;
	}
	public void setCHARACTER_MAXIMUM_LENGTH(String cHARACTERMAXIMUMLENGTH) {
		CHARACTER_MAXIMUM_LENGTH = cHARACTERMAXIMUMLENGTH;
	}
	public String getCOLUMN_NAME() {
		return COLUMN_NAME;
	}
	public void setCOLUMN_NAME(String cOLUMNNAME) {
		COLUMN_NAME = cOLUMNNAME;
	}
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(String dATATYPE) {
		DATA_TYPE = dATATYPE;
	}
	public String getCOLUMN_COMMENT() {
		return COLUMN_COMMENT;
	}
	public void setCOLUMN_COMMENT(String cOLUMNCOMMENT) {
		COLUMN_COMMENT = cOLUMNCOMMENT;
	}
	public String getIS_NULLABLE() {
		return IS_NULLABLE;
	}
	public void setIS_NULLABLE(String is_nullable) {
		IS_NULLABLE = is_nullable;
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
	public String getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}
	public String getXt_script_id() {
		return xt_script_id;
	}
	public void setXt_script_id(String xt_script_id) {
		this.xt_script_id = xt_script_id;
	}
	public String getXt_script_text() {
		return xt_script_text;
	}
	public void setXt_script_text(String xt_script_text) {
		this.xt_script_text = xt_script_text;
	}
	
}
