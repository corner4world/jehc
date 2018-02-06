package jehc.xtmodules.xtmodel;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
 * 数据库备份
 * @author 邓纯杰
 *
 */
public class XtDb extends BaseEntity{

	private static final long serialVersionUID = -6815408663743910895L;
	private String xt_db_id;/**序列号**/
	private String xt_db_name;/**备份的数据库名称**/
	private String xt_db_time;/**备份时间**/
	private String xt_db_path;/**备份路径**/
	private String xt_db_user;/**当前执行人**/
	private int xt_db_type;/**0表示整库备份1按指定表备份**/
	public String getXt_db_id() {
		return xt_db_id;
	}
	public void setXt_db_id(String xt_db_id) {
		this.xt_db_id = xt_db_id;
	}
	public String getXt_db_name() {
		return xt_db_name;
	}
	public void setXt_db_name(String xt_db_name) {
		this.xt_db_name = xt_db_name;
	}
	public String getXt_db_time() {
		return xt_db_time;
	}
	public void setXt_db_time(String xt_db_time) {
		this.xt_db_time = xt_db_time;
	}
	public String getXt_db_path() {
		return xt_db_path;
	}
	public void setXt_db_path(String xt_db_path) {
		this.xt_db_path = xt_db_path;
	}
	public String getXt_db_user() {
		return xt_db_user;
	}
	public void setXt_db_user(String xt_db_user) {
		this.xt_db_user = xt_db_user;
	}
	public int getXt_db_type() {
		return xt_db_type;
	}
	public void setXt_db_type(int xt_db_type) {
		this.xt_db_type = xt_db_type;
	}
}
