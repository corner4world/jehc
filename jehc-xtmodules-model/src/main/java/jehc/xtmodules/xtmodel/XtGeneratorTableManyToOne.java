package jehc.xtmodules.xtmodel;

import java.io.Serializable;
import java.util.List;

/**
 * 代码生成-一对多表信息
 * @author 邓纯杰
 *
 */
public class XtGeneratorTableManyToOne implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_generator_one_to_many_table_name;//表名
	private String xt_generator_one_to_many_table_name_zh;//表备注
	private String xt_generator_ontomany_grid;//一对多表Grid信息
	private String xt_generator_one_to_many_table_fkey;//外键（子表中对应主表编号）
	private String xt_generator_one_to_many_table_model_package;//实体包路径
	private String xt_generator_one_to_many_table_dao_package;//数据访问层包路径
	private String xt_generator_one_to_many_table_service_package;//业务逻辑层包路径
	private String xt_generator_one_to_many_table_web_package;//控制层包路径
	private List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList;
	public String getXt_generator_one_to_many_table_name() {
		return xt_generator_one_to_many_table_name;
	}
	public void setXt_generator_one_to_many_table_name(
			String xt_generator_one_to_many_table_name) {
		this.xt_generator_one_to_many_table_name = xt_generator_one_to_many_table_name;
	}
	public String getXt_generator_one_to_many_table_name_zh() {
		return xt_generator_one_to_many_table_name_zh;
	}
	public void setXt_generator_one_to_many_table_name_zh(
			String xt_generator_one_to_many_table_name_zh) {
		this.xt_generator_one_to_many_table_name_zh = xt_generator_one_to_many_table_name_zh;
	}
	public String getXt_generator_ontomany_grid() {
		return xt_generator_ontomany_grid;
	}
	public void setXt_generator_ontomany_grid(String xt_generator_ontomany_grid) {
		this.xt_generator_ontomany_grid = xt_generator_ontomany_grid;
	}
	public String getXt_generator_one_to_many_table_fkey() {
		return xt_generator_one_to_many_table_fkey;
	}
	public void setXt_generator_one_to_many_table_fkey(
			String xt_generator_one_to_many_table_fkey) {
		this.xt_generator_one_to_many_table_fkey = xt_generator_one_to_many_table_fkey;
	}
	public List<XtGeneratorTableColumnManyToOne> getXt_Generator_Table_ColumnMany_To_OneList() {
		return xt_Generator_Table_ColumnMany_To_OneList;
	}
	public void setXt_Generator_Table_ColumnMany_To_OneList(
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList) {
		this.xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_Table_ColumnMany_To_OneList;
	}
	public String getXt_generator_one_to_many_table_model_package() {
		return xt_generator_one_to_many_table_model_package;
	}
	public void setXt_generator_one_to_many_table_model_package(
			String xt_generator_one_to_many_table_model_package) {
		this.xt_generator_one_to_many_table_model_package = xt_generator_one_to_many_table_model_package;
	}
	public String getXt_generator_one_to_many_table_dao_package() {
		return xt_generator_one_to_many_table_dao_package;
	}
	public void setXt_generator_one_to_many_table_dao_package(
			String xt_generator_one_to_many_table_dao_package) {
		this.xt_generator_one_to_many_table_dao_package = xt_generator_one_to_many_table_dao_package;
	}
	public String getXt_generator_one_to_many_table_service_package() {
		return xt_generator_one_to_many_table_service_package;
	}
	public void setXt_generator_one_to_many_table_service_package(
			String xt_generator_one_to_many_table_service_package) {
		this.xt_generator_one_to_many_table_service_package = xt_generator_one_to_many_table_service_package;
	}
	public String getXt_generator_one_to_many_table_web_package() {
		return xt_generator_one_to_many_table_web_package;
	}
	public void setXt_generator_one_to_many_table_web_package(
			String xt_generator_one_to_many_table_web_package) {
		this.xt_generator_one_to_many_table_web_package = xt_generator_one_to_many_table_web_package;
	}
}
