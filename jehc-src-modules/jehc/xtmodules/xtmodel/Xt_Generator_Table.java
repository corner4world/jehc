package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 代码生成-表信息
 * @author 邓纯杰
 *
 */
public class Xt_Generator_Table implements Serializable{
	private static final long serialVersionUID = 1L;
	private String TABLE_NAME;//表名
	private String TABLE_COMMENT;//表备注
	public String getTABLE_NAME() {
		return TABLE_NAME;
	}
	public void setTABLE_NAME(String tABLENAME) {
		TABLE_NAME = tABLENAME;
	}
	public String getTABLE_COMMENT() {
		return TABLE_COMMENT;
	}
	public void setTABLE_COMMENT(String tABLECOMMENT) {
		TABLE_COMMENT = tABLECOMMENT;
	}
}
