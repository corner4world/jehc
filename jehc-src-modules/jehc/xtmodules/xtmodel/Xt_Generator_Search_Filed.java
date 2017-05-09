package jehc.xtmodules.xtmodel;
/**
 * 查询字段封装
 * @author 邓纯杰
 *
 */
public class Xt_Generator_Search_Filed {
	private String xt_generator_search_name;/**字段**/
	private String xt_generator_search_label;/**label标签**/
	private String xt_generator_search_flag;/**标识0包含,1等于,2大于等于,3小于等于,4大于,5小于,6范围**/
	private String xt_generator_search_label_position;/**label标签位置缺省正常位置TOP上部**/ 
	private String xt_generator_search_type;/**类型0文本框1文本域2数字框3下拉框4日期框**/
	private String xt_generator_search_length;/**标签长度**/
	private String xt_script_id;/**脚本编号**/
	public String getXt_generator_search_name() {
		return xt_generator_search_name;
	}
	public void setXt_generator_search_name(String xt_generator_search_name) {
		this.xt_generator_search_name = xt_generator_search_name;
	}
	public String getXt_generator_search_label() {
		return xt_generator_search_label;
	}
	public void setXt_generator_search_label(String xt_generator_search_label) {
		this.xt_generator_search_label = xt_generator_search_label;
	}
	public String getXt_generator_search_flag() {
		return xt_generator_search_flag;
	}
	public void setXt_generator_search_flag(String xt_generator_search_flag) {
		this.xt_generator_search_flag = xt_generator_search_flag;
	}
	public String getXt_generator_search_label_position() {
		return xt_generator_search_label_position;
	}
	public void setXt_generator_search_label_position(
			String xt_generator_search_label_position) {
		this.xt_generator_search_label_position = xt_generator_search_label_position;
	}
	public String getXt_generator_search_type() {
		return xt_generator_search_type;
	}
	public void setXt_generator_search_type(String xt_generator_search_type) {
		this.xt_generator_search_type = xt_generator_search_type;
	}
	public String getXt_generator_search_length() {
		return xt_generator_search_length;
	}
	public void setXt_generator_search_length(String xt_generator_search_length) {
		this.xt_generator_search_length = xt_generator_search_length;
	}
	public String getXt_script_id() {
		return xt_script_id;
	}
	public void setXt_script_id(String xt_script_id) {
		this.xt_script_id = xt_script_id;
	}
}
