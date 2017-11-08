package jehc.xtmodules.xtmodel;

import java.io.Serializable;
import java.util.List;

/**
 * 代码生成器 信息
 * @author邓纯杰
 *
 */
public class XtGenerator implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_generator_id;/**id**/
	private String xt_generator_tbname;/**表名**/
	private String xt_generator_tbcomment;/**备注**/
	private String xt_userinfo_id;/**操作人**/
	private String xt_generator_time;/**生成日期**/
	private String xt_generator_state;/**状态1成功2失败**/
	private String xt_generator_path;/**生成路径**/
	private String xt_generator_model_package;/**包名(Model)**/
	private String xt_generator_dao_package;/**数据层包名(Dao)**/
	private String xt_generator_service_package;/**业务层包名 (Service)**/
	private String xt_generator_web_package;/**控制层(Web)**/
	private String xt_generator_page_package;/**页面层(Page)**/
	private String xt_generator_modules;/**格式如生成:实体类,数据层,业务层,控制层,页面层**/
	private String xt_generator_scope;/**控制层采用模式1单例2多例**/
	
	private String xt_generator_page_max;/**页面是否最大化**/
	private int xt_generator_page_width;/**页面宽度**/
	private int xt_generator_page_height;/**页面高度**/
	private String xt_generator_page_labelAlign;/**表单标签对齐方式1向左2向右3向上**/
	private int xt_generator_page_labelWidth;/**表单标签宽度**/
	private String xt_generator_page_winScroll;/**窗体(表单)是否支持滚动条**/
	
	
	private String xt_generator_page_checkboxmodel;/**列表中显示复选框**/
	private String xt_generator_page_multiSelect;/**列表中可多选行**/
	private String xt_generator_page_collapsible;/**列表中是否可折叠**/
	
	private String xt_generator_extendF;/**可继承父类1可以2不能**/
	private String xt_generator_page;/**分页方式1物理2普通**/
	private String xt_userinfo_realName;/**操作人姓名**/
	private String xt_generator_isuse_rediscache;/**是否选择Redis作为二级缓存？（默认采用Ehcache缓存作为二级缓存）**/
	private String xt_generator_batch_add_update;/**平台是否默认生成批量添加、修改功能1是2否**/
	private List<XtGeneratorSearchFiled> xt_generator_search_filedList;/**查询字段封装**/
	private List<XtGeneratorGridColumn> xt_Generator_Grid_ColumnList;/**GridList列表字段封装**/
	private List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList;/**新增 编辑 明细表单等字段封装**/
	
	private String is_one_to_many;/**0单表生成 1为一对多生成**/
	private boolean is_main_table;/**临时使用是否为主表true是false否**/
	private String fk;/**子表中外键**/
	private String one_to_many_type;/**一对多操作方式0多的一方为列表动态添加1多的一方为动态添加表单**/
	private List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList;/**子表集合**/
	
	private String kf_mode;/**开发模式0前端采用Extjs模式 1前端采用bootstrap模式*/
	public List<XtGeneratorSearchFiled> getXt_generator_search_filedList() {
		return xt_generator_search_filedList;
	}
	public void setXt_generator_search_filedList(
			List<XtGeneratorSearchFiled> xt_generator_search_filedList) {
		this.xt_generator_search_filedList = xt_generator_search_filedList;
	}
	public String getXt_generator_page() {
		return xt_generator_page;
	}
	public void setXt_generator_page(String xt_generator_page) {
		this.xt_generator_page = xt_generator_page;
	}
	public String getXt_generator_page_checkboxmodel() {
		return xt_generator_page_checkboxmodel;
	}
	public void setXt_generator_page_checkboxmodel(
			String xt_generator_page_checkboxmodel) {
		this.xt_generator_page_checkboxmodel = xt_generator_page_checkboxmodel;
	}
	public String getXt_generator_page_multiSelect() {
		return xt_generator_page_multiSelect;
	}
	public void setXt_generator_page_multiSelect(
			String xt_generator_page_multiSelect) {
		this.xt_generator_page_multiSelect = xt_generator_page_multiSelect;
	}
	public String getXt_generator_page_collapsible() {
		return xt_generator_page_collapsible;
	}
	public void setXt_generator_page_collapsible(
			String xt_generator_page_collapsible) {
		this.xt_generator_page_collapsible = xt_generator_page_collapsible;
	}
	public String getXt_generator_page_labelAlign() {
		return xt_generator_page_labelAlign;
	}
	public void setXt_generator_page_labelAlign(String xt_generator_page_labelAlign) {
		this.xt_generator_page_labelAlign = xt_generator_page_labelAlign;
	}
	public String getXt_userinfo_realName() {
		return xt_userinfo_realName;
	}
	public void setXt_userinfo_realName(String xtUserinfoRealName) {
		xt_userinfo_realName = xtUserinfoRealName;
	}
	
	public String getXt_generator_modules() {
		return xt_generator_modules;
	}
	public void setXt_generator_modules(String xtGeneratorModules) {
		xt_generator_modules = xtGeneratorModules;
	}
	public String getXt_generator_model_package() {
		return xt_generator_model_package;
	}
	public void setXt_generator_model_package(String xt_generator_model_package) {
		this.xt_generator_model_package = xt_generator_model_package;
	}
	public String getXt_generator_path() {
		return xt_generator_path;
	}
	public void setXt_generator_path(String xtGeneratorPath) {
		xt_generator_path = xtGeneratorPath;
	}
	public String getXt_generator_id() {
		return xt_generator_id;
	}
	public void setXt_generator_id(String xtGeneratorId) {
		xt_generator_id = xtGeneratorId;
	}
	public String getXt_generator_tbname() {
		return xt_generator_tbname;
	}
	public void setXt_generator_tbname(String xtGeneratorTbname) {
		xt_generator_tbname = xtGeneratorTbname;
	}
	public String getXt_generator_tbcomment() {
		return xt_generator_tbcomment;
	}
	public void setXt_generator_tbcomment(String xtGeneratorTbcomment) {
		xt_generator_tbcomment = xtGeneratorTbcomment;
	}
	public String getXt_userinfo_id() {
		return xt_userinfo_id;
	}
	public void setXt_userinfo_id(String xtUserinfoId) {
		xt_userinfo_id = xtUserinfoId;
	}
	public String getXt_generator_time() {
		return xt_generator_time;
	}
	public void setXt_generator_time(String xtGeneratorTime) {
		xt_generator_time = xtGeneratorTime;
	}
	public String getXt_generator_state() {
		return xt_generator_state;
	}
	public void setXt_generator_state(String xtGeneratorState) {
		xt_generator_state = xtGeneratorState;
	}
	public String getXt_generator_dao_package() {
		return xt_generator_dao_package;
	}
	public void setXt_generator_dao_package(String xt_generator_dao_package) {
		this.xt_generator_dao_package = xt_generator_dao_package;
	}
	public String getXt_generator_service_package() {
		return xt_generator_service_package;
	}
	public void setXt_generator_service_package(String xt_generator_service_package) {
		this.xt_generator_service_package = xt_generator_service_package;
	}
	public String getXt_generator_web_package() {
		return xt_generator_web_package;
	}
	public void setXt_generator_web_package(String xt_generator_web_package) {
		this.xt_generator_web_package = xt_generator_web_package;
	}
	public String getXt_generator_page_package() {
		return xt_generator_page_package;
	}
	public void setXt_generator_page_package(String xt_generator_page_package) {
		this.xt_generator_page_package = xt_generator_page_package;
	}
	public String getXt_generator_page_max() {
		return xt_generator_page_max;
	}
	public void setXt_generator_page_max(String xt_generator_page_max) {
		this.xt_generator_page_max = xt_generator_page_max;
	}
	public int getXt_generator_page_width() {
		return xt_generator_page_width;
	}
	public void setXt_generator_page_width(int xt_generator_page_width) {
		this.xt_generator_page_width = xt_generator_page_width;
	}
	public int getXt_generator_page_height() {
		return xt_generator_page_height;
	}
	public void setXt_generator_page_height(int xt_generator_page_height) {
		this.xt_generator_page_height = xt_generator_page_height;
	}
	public String getXt_generator_scope() {
		return xt_generator_scope;
	}
	public void setXt_generator_scope(String xt_generator_scope) {
		this.xt_generator_scope = xt_generator_scope;
	}
	public int getXt_generator_page_labelWidth() {
		return xt_generator_page_labelWidth;
	}
	public void setXt_generator_page_labelWidth(int xt_generator_page_labelWidth) {
		this.xt_generator_page_labelWidth = xt_generator_page_labelWidth;
	}
	public String getXt_generator_page_winScroll() {
		return xt_generator_page_winScroll;
	}
	public void setXt_generator_page_winScroll(String xt_generator_page_winScroll) {
		this.xt_generator_page_winScroll = xt_generator_page_winScroll;
	}
	public String getXt_generator_extendF() {
		return xt_generator_extendF;
	}
	public void setXt_generator_extendF(String xt_generator_extendF) {
		this.xt_generator_extendF = xt_generator_extendF;
	}
	public List<XtGeneratorGridColumn> getXt_Generator_Grid_ColumnList() {
		return xt_Generator_Grid_ColumnList;
	}
	public void setXt_Generator_Grid_ColumnList(
			List<XtGeneratorGridColumn> xt_Generator_Grid_ColumnList) {
		this.xt_Generator_Grid_ColumnList = xt_Generator_Grid_ColumnList;
	}
	public List<XtGeneratorTableColumnForm> getXt_Generator_Table_Column_FormList() {
		return xt_Generator_Table_Column_FormList;
	}
	public void setXt_Generator_Table_Column_FormList(
			List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList) {
		this.xt_Generator_Table_Column_FormList = xt_Generator_Table_Column_FormList;
	}
	public String getXt_generator_isuse_rediscache() {
		return xt_generator_isuse_rediscache;
	}
	public void setXt_generator_isuse_rediscache(
			String xt_generator_isuse_rediscache) {
		this.xt_generator_isuse_rediscache = xt_generator_isuse_rediscache;
	}
	public String getXt_generator_batch_add_update() {
		return xt_generator_batch_add_update;
	}
	public void setXt_generator_batch_add_update(
			String xt_generator_batch_add_update) {
		this.xt_generator_batch_add_update = xt_generator_batch_add_update;
	}
	public String getIs_one_to_many() {
		return is_one_to_many;
	}
	public void setIs_one_to_many(String is_one_to_many) {
		this.is_one_to_many = is_one_to_many;
	}
	public List<XtGeneratorTableManyToOne> getXt_Generator_TableMany_To_OneList() {
		return xt_Generator_TableMany_To_OneList;
	}
	public void setXt_Generator_TableMany_To_OneList(
			List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList) {
		this.xt_Generator_TableMany_To_OneList = xt_Generator_TableMany_To_OneList;
	}
	public boolean isIs_main_table() {
		return is_main_table;
	}
	public void setIs_main_table(boolean is_main_table) {
		this.is_main_table = is_main_table;
	}
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	public String getOne_to_many_type() {
		return one_to_many_type;
	}
	public void setOne_to_many_type(String one_to_many_type) {
		this.one_to_many_type = one_to_many_type;
	}
	public String getKf_mode() {
		return kf_mode;
	}
	public void setKf_mode(String kf_mode) {
		this.kf_mode = kf_mode;
	}
	
}
