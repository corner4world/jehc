package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
 * 存储过程
 * @author 邓纯杰
 *
 */
public class Xt_Db_Proc  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
