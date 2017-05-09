package jehc.xtmodules.xtweb;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.FastJsonUtils;
import jehc.xtmodules.xtcore.util.JsonUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.db.DBMSMetaUtil;
import jehc.xtmodules.xtcore.util.db.DbInfo;
import jehc.xtmodules.xtcore.util.db.MapUtil;
import jehc.xtmodules.xtmodel.Xt_Generator;
import jehc.xtmodules.xtmodel.Xt_Generator_Forbidtable;
import jehc.xtmodules.xtmodel.Xt_Generator_Grid_Column;
import jehc.xtmodules.xtmodel.Xt_Generator_Search_Filed;
import jehc.xtmodules.xtmodel.Xt_Generator_Table;
import jehc.xtmodules.xtmodel.Xt_Generator_TableMany_To_One;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_ColumnMany_To_One;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column_Form;
import jehc.xtmodules.xtservice.Xt_GeneratorService;
import jehc.xtmodules.xtservice.Xt_Generator_ForbidtableService;
/**
 * 代码生成器
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/xtGeneratorController")
@Scope("prototype")
public class Xt_GeneratorController extends BaseAction {
	@Autowired
	private Xt_GeneratorService xt_GeneratorService;
	@Autowired
	private Xt_Generator_ForbidtableService xt_Generator_ForbidtableService;
	/**
	@Autowired
	private Xt_Generator_TableService xt_Generator_TableService;
	@Autowired
	private Xt_Generator_Table_ColumnService xt_Generator_Table_ColumnService;
	**/
	/**
	 * 载入初始化页面
	 * @param xt_Generator
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtGenerator",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtGenerator(Xt_Generator xt_Generator,HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-generator/xt-generator-list");
	}
	
	/**
	 * 查找所有生成记录并分页
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorListByCondition(BaseSearch baseSearch,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Generator> xtGeneratorList = xt_GeneratorService.getXtGeneratorListByCondition(condition);
		PageInfo<Xt_Generator> page = new PageInfo<Xt_Generator>(xtGeneratorList);
		return outPageStr(page,request);
	}
	
	/**
	 * 获取表中所有字段用于显示字段列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorTableColumnListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorTableColumnListByCondition(HttpServletRequest request, HttpServletResponse response){
		//支持多数据库如Mysql oracle  sqlserver sybase db2
		String xt_generator_tbname = request.getParameter("xt_generator_tbname");
		List<Xt_Generator_Table_Column> xtGeneratorTableColumnList = new ArrayList<Xt_Generator_Table_Column>();
		DbInfo dbInfo = DBMSMetaUtil.excuteDB(xt_generator_tbname);
		String primaryKey="";
		List<Map<String, Object>> columnsPrimary = MapUtil.convertKeyList2LowerCase(dbInfo.getColumnsPrimary());
		if(!columnsPrimary.isEmpty() && columnsPrimary.size()>0){
			Map<String, Object> map = columnsPrimary.get(0);
			primaryKey = map.get("column_name").toString();
		}
		List<Map<String, Object>> columns = dbInfo.getColumns();
		if(!columns.isEmpty() && columns.size()>0){
			for(int i = 0; i < columns.size(); i++){
				Map<String, Object> map = columns.get(i);
				Xt_Generator_Table_Column xt_Generator_Table_Column = new Xt_Generator_Table_Column();
				if(primaryKey.equals(map.get("COLUMN_NAME").toString())){
					xt_Generator_Table_Column.setCOLUMN_KEY("PRI");
				}
				xt_Generator_Table_Column.setCOLUMN_NAME(map.get("COLUMN_NAME").toString());
				xt_Generator_Table_Column.setCOLUMN_COMMENT(map.get("REMARKS").toString());
				xt_Generator_Table_Column.setDATA_TYPE(map.get("TYPE_NAME").toString());
				xt_Generator_Table_Column.setIS_NULLABLE(map.get("IS_NULLABLE").toString());
				xt_Generator_Table_Column.setCHARACTER_MAXIMUM_LENGTH(map.get("COLUMN_SIZE").toString());
				xt_Generator_Table_Column.setColumn_label_position("居左");
				xt_Generator_Table_Column.setColumn_label_anchor("100");
				xt_Generator_Table_Column.setIsHidden("否");
				xtGeneratorTableColumnList.add(xt_Generator_Table_Column);
			}
		}
		return outItemsStr(xtGeneratorTableColumnList);
	}
	
	/**
	 * 生成代码
	 * @param xt_Generator
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/addXtGenerator",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtGenerator(Xt_Generator xt_Generator,String xtGeneratorSearchFileJSON,String xtGeneratorGridListJSON,String xtGeneratorTableColumnFormJson,String xtGeneratorOneToManyFormListJSON,HttpServletRequest request, HttpServletResponse response){
		xt_Generator.setXt_generator_id(UUID.toUUID());
		xt_Generator.setXt_Generator_Grid_ColumnList(commonGeneratorGridListJSONList(xtGeneratorGridListJSON));
		xt_Generator.setXt_generator_search_filedList(commonGeneratorSearchFileJSONList(xtGeneratorSearchFileJSON));
		xt_Generator.setXt_Generator_Table_Column_FormList(commonGeneratorFormJSONList(xtGeneratorTableColumnFormJson));
		xt_Generator.setXt_Generator_TableMany_To_OneList(commonXtGeneratorOneToManyFormListJSON(xtGeneratorOneToManyFormListJSON));
		int i = xt_GeneratorService.addXtGenerator(xt_Generator);
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 获取所有表
	 */
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorTableList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorTableList(HttpServletRequest request, HttpServletResponse response){
		//支持多数据库如Mysql oracle  sqlserver sybase db2
		List<Xt_Generator_Table> xtGeneratorTableList = new ArrayList<Xt_Generator_Table>();
		DbInfo dbInfo = DBMSMetaUtil.excuteDB(null);
		List<Map<String, Object>> tables = MapUtil.convertKeyList2LowerCase(dbInfo.getTables());
		for(int i = 0; i < tables.size(); i++){
			Map<String, Object> map = tables.get(i);
			Xt_Generator_Table xt_Generator_Table = new Xt_Generator_Table();
			if(null != map.get("remarks") && !"".equals(map.get("remarks"))){
				xt_Generator_Table.setTABLE_COMMENT(map.get("remarks").toString());
			}else{
				xt_Generator_Table.setTABLE_COMMENT(map.get("table_name").toString());
			}
			xt_Generator_Table.setTABLE_NAME(map.get("table_name").toString());
			xtGeneratorTableList.add(xt_Generator_Table);
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Xt_Generator_Forbidtable> list =  xt_Generator_ForbidtableService.getXtGeneratorForbidtableListByCondition(condition);
		for(int i = 0; i < list.size(); i++){
			Xt_Generator_Forbidtable obj = list.get(i);
			for(int j = 0; j < xtGeneratorTableList.size(); j++){
				Xt_Generator_Table xt_Generator_Table = xtGeneratorTableList.get(j);
				if(xt_Generator_Table.getTABLE_NAME().equals(obj.getXt_generator_forbidtable_table())){
					xtGeneratorTableList.remove(j);
					j--;
					break;
				}
			}
		}
		return outComboDataStr(xtGeneratorTableList);
	}
	
	/**
	 * 获取表中所有字段
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorTableColumnList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorTableColumnList(HttpServletRequest request, HttpServletResponse response){
		//支持多数据库如Mysql oracle  sqlserver sybase db2
		String xt_generator_tbname = request.getParameter("xt_generator_tbname");
		DbInfo dbInfo = DBMSMetaUtil.excuteDB(xt_generator_tbname);
		List<Map<String, Object>> columnsPrimary = MapUtil.convertKeyList2LowerCase(dbInfo.getColumnsPrimary());
		Xt_Generator_Table_Column xtGeneratorTableColumn = new Xt_Generator_Table_Column();
		for(int i = 0; i < columnsPrimary.size(); i++){
			xtGeneratorTableColumn = new Xt_Generator_Table_Column();
			Map<String, Object> map = columnsPrimary.get(i);
			if(null != map.get("remarks") && !"".equals(map.get("remarks"))){
				xtGeneratorTableColumn.setCOLUMN_COMMENT(map.get("remarks").toString());
			}else{
				xtGeneratorTableColumn.setCOLUMN_COMMENT(map.get("column_name").toString());
			}
			xtGeneratorTableColumn.setCOLUMN_NAME(map.get("column_name").toString());
			break;
		}
		return outComboDataStr(xtGeneratorTableColumn);
	}
	
	/**
	 * 获取所有表Grid使用
	 */
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorTableGridList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorTableGridList(HttpServletRequest request, HttpServletResponse response){
		//支持多数据库如Mysql oracle  sqlserver sybase db2
		List<Xt_Generator_Table> xtGeneratorTableList = new ArrayList<Xt_Generator_Table>();
		DbInfo dbInfo = DBMSMetaUtil.excuteDB(null);
		List<Map<String, Object>> tables = MapUtil.convertKeyList2LowerCase(dbInfo.getTables());
		for(int i = 0; i < tables.size(); i++){
			Map<String, Object> map = tables.get(i);
			Xt_Generator_Table xt_Generator_Table = new Xt_Generator_Table();
			if(null != map.get("remarks") && !"".equals(map.get("remarks"))){
				xt_Generator_Table.setTABLE_COMMENT(map.get("remarks").toString());
			}else{
				xt_Generator_Table.setTABLE_COMMENT(map.get("table_name").toString());
			}
			xt_Generator_Table.setTABLE_NAME(map.get("table_name").toString());
			xtGeneratorTableList.add(xt_Generator_Table);
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Xt_Generator_Forbidtable> list =  xt_Generator_ForbidtableService.getXtGeneratorForbidtableListByCondition(condition);
		for(int i = 0; i < list.size(); i++){
			Xt_Generator_Forbidtable obj = list.get(i);
			for(int j = 0; j < xtGeneratorTableList.size(); j++){
				Xt_Generator_Table xt_Generator_Table = xtGeneratorTableList.get(j);
				if(xt_Generator_Table.getTABLE_NAME().equals(obj.getXt_generator_forbidtable_table())){
					xtGeneratorTableList.remove(j);
					j--;
					break;
				}
			}
		}
		return outItemsStr(xtGeneratorTableList);
	}
	
	/**
	 * 获取可用硬盘
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getDisks",method={RequestMethod.POST,RequestMethod.GET})
	public String getDisks(HttpServletRequest request, HttpServletResponse response){
		File[] arFileRoot = File.listRoots();
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		for(int i=0;i<arFileRoot.length;i++){
			model.put("disks", arFileRoot[i].getAbsolutePath().replaceAll(":\\\\","://"));
			model.put("disksName", arFileRoot[i].getPath().replaceAll(":\\\\","")+"盘");
			jsonArray.add(model);
		}
		return outComboDataStr(jsonArray);
	}
	/**
	 * 返回配置字段LIST
	 * @param solrIndexSqlJSON
	 * @return
	 */
	private List<Xt_Generator_Search_Filed> commonGeneratorSearchFileJSONList(String xtGeneratorSearchFileJSON){
		try {
			xtGeneratorSearchFileJSON = URLDecoder.decode(xtGeneratorSearchFileJSON, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Xt_Generator_Search_Filed> xt_Generator_Search_FiledList = new ArrayList<Xt_Generator_Search_Filed>();
		JSONArray arr = JSONArray.fromObject(xtGeneratorSearchFileJSON);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	JSONObject json = (JSONObject)obj; 
		    	Xt_Generator_Search_Filed xt_Generator_Search_Filed = new Xt_Generator_Search_Filed();
		    	Object xt_generator_search_name = json.get("xt_generator_search_name");
		    	Object xt_generator_search_label = json.get("xt_generator_search_label");
		    	Object xt_generator_search_flag = json.get("xt_generator_search_flag");
		    	Object xt_generator_search_label_position = json.get("xt_generator_search_label_position");
		    	Object xt_generator_search_type = json.get("xt_generator_search_type");
		    	Object xt_generator_search_label_length = json.get("xt_generator_search_label_length");
		    	Object xt_script_id = json.get("xt_script_id");
		    	if(null != xt_generator_search_name){
		    		xt_Generator_Search_Filed.setXt_generator_search_name((String)xt_generator_search_name);
		    	}
		    	if(null != xt_script_id){
		    		xt_Generator_Search_Filed.setXt_script_id((String)xt_script_id);
		    	}
		    	if(null != xt_generator_search_label){
		    		xt_Generator_Search_Filed.setXt_generator_search_label((String)xt_generator_search_label);
				}
		    	if(null != xt_generator_search_flag){
		    		if(xt_generator_search_flag.toString().equals("包含")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("0");
		    		}else if(xt_generator_search_flag.toString().equals("等于")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("1");
		    		}else if(xt_generator_search_flag.toString().equals("大于等于")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("2");
		    		}else if(xt_generator_search_flag.toString().equals("小于等于")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("3");
		    		}else if(xt_generator_search_flag.toString().equals("大于")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("4");
		    		}else if(xt_generator_search_flag.toString().equals("小于")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("5");
		    		}else if(xt_generator_search_flag.toString().equals("范围")){
		    			xt_Generator_Search_Filed.setXt_generator_search_flag("6");
		    		}
		    	}
		    	if(null != xt_generator_search_label_position){
		    		if("居左".equals(xt_generator_search_label_position.toString())){
		    			xt_Generator_Search_Filed.setXt_generator_search_label_position("0");
		    		}else{
		    			xt_Generator_Search_Filed.setXt_generator_search_label_position("1");
		    		}
		    	}
		    	if(null != xt_generator_search_type){
		    		if(xt_generator_search_type.toString().equals("文本框")){
		    			xt_Generator_Search_Filed.setXt_generator_search_type("0");
		    		}else if(xt_generator_search_type.toString().equals("文本域")){
		    			xt_Generator_Search_Filed.setXt_generator_search_type("1");
		    		}else if(xt_generator_search_type.toString().equals("数字框")){
		    			xt_Generator_Search_Filed.setXt_generator_search_type("2");
		    		}else if(xt_generator_search_type.toString().equals("下拉框")){
		    			xt_Generator_Search_Filed.setXt_generator_search_type("3");
		    		}else if(xt_generator_search_type.toString().equals("日期框")){
		    			xt_Generator_Search_Filed.setXt_generator_search_type("4");
		    		}else if(xt_generator_search_type.toString().equals("文件框")){
		    			xt_Generator_Search_Filed.setXt_generator_search_type("5");
		    		}else{
		    			xt_Generator_Search_Filed.setXt_generator_search_type("0");
		    		}
		    	}
		    	if(null != xt_generator_search_label_length){
		    		xt_Generator_Search_Filed.setXt_generator_search_length(xt_generator_search_label_length.toString());
		    	}
		    	xt_Generator_Search_FiledList.add(xt_Generator_Search_Filed);
		    }
		}
		return xt_Generator_Search_FiledList;
	}
	
	/**
	 * 返回GridList集合
	 * @param xtGeneratorGridListJSON
	 * @return
	 */
	public List<Xt_Generator_Grid_Column> commonGeneratorGridListJSONList(String xtGeneratorGridListJSON){
		try {
			xtGeneratorGridListJSON = URLDecoder.decode(xtGeneratorGridListJSON, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Xt_Generator_Grid_Column> xt_Generator_Grid_ColumnList = new ArrayList<Xt_Generator_Grid_Column>();
		JSONArray arr = JSONArray.fromObject(xtGeneratorGridListJSON);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	Xt_Generator_Grid_Column xt_Generator_Grid_Column = new Xt_Generator_Grid_Column();
		    	JSONObject json = (JSONObject)obj;
		    	Object COLUMN_NAME = json.get("COLUMN_NAME");
		    	Object COLUMN_COMMENT = json.get("COLUMN_COMMENT");
		    	if(null != COLUMN_NAME && !"".equals(COLUMN_NAME)){
		    		xt_Generator_Grid_Column.setXt_generator_grid_column_name((String)COLUMN_NAME);
		    	}
		    	if(null != COLUMN_COMMENT && !"".equals(COLUMN_COMMENT)){
		    		xt_Generator_Grid_Column.setXt_generator_grid_column_label((String)COLUMN_COMMENT);
		    	}
		    	xt_Generator_Grid_ColumnList.add(xt_Generator_Grid_Column);
		    }
		}
		return xt_Generator_Grid_ColumnList;
	}
	
	/**
	 * 返回新增 编辑 明细表单集合
	 * @param xtGeneratorTableColumnFormJson
	 * @return
	 */
	public List<Xt_Generator_Table_Column_Form> commonGeneratorFormJSONList(String xtGeneratorTableColumnFormJson){
		try {
			xtGeneratorTableColumnFormJson = URLDecoder.decode(xtGeneratorTableColumnFormJson, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Xt_Generator_Table_Column_Form> xt_Generator_Table_Column_FormList = new ArrayList<Xt_Generator_Table_Column_Form>();
		JSONArray arr = JSONArray.fromObject(xtGeneratorTableColumnFormJson);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	Xt_Generator_Table_Column_Form xt_Generator_Table_Column_Form = new Xt_Generator_Table_Column_Form();
		    	JSONObject json = (JSONObject)obj;
		    	Object COLUMN_NAME = json.get("COLUMN_NAME");
		    	Object COLUMN_COMMENT = json.get("COLUMN_COMMENT");
		    	Object DATA_TYPE = json.get("DATA_TYPE");
		    	Object CHARACTER_MAXIMUM_LENGTH = json.get("CHARACTER_MAXIMUM_LENGTH");
		    	Object IS_NULLABLE = json.get("IS_NULLABLE");
		    	Object COLUMN_KEY = json.get("COLUMN_KEY");
		    	Object column_label_position = json.get("column_label_position");
		    	Object column_label_anchor = json.get("column_label_anchor");
		    	Object column_type = json.get("column_type");
		    	Object isHidden = json.get("isHidden");
		    	Object xt_script_id = json.get("xt_script_id");
		    	if(null != COLUMN_NAME && !"".equals(COLUMN_NAME)){
		    		xt_Generator_Table_Column_Form.setColumn_name((String)COLUMN_NAME);
		    	}
		    	if(null != COLUMN_COMMENT && !"".equals(COLUMN_COMMENT)){
		    		xt_Generator_Table_Column_Form.setColumn_comment((String)COLUMN_COMMENT);
		    	}
		    	if(null != DATA_TYPE && !"".equals(DATA_TYPE)){
		    		xt_Generator_Table_Column_Form.setData_type((String)DATA_TYPE);
		    	}
		    	if(null != CHARACTER_MAXIMUM_LENGTH && !"".equals(CHARACTER_MAXIMUM_LENGTH)){
		    		xt_Generator_Table_Column_Form.setCharacter_maximum_length((String)CHARACTER_MAXIMUM_LENGTH);
		    	}
		    	if(null != IS_NULLABLE && !"".equals(IS_NULLABLE)){
		    		xt_Generator_Table_Column_Form.setIs_nullable((String)IS_NULLABLE);
		    	}
		    	if(null != COLUMN_KEY && !"".equals(COLUMN_KEY)){
		    		xt_Generator_Table_Column_Form.setColumn_key((String)COLUMN_KEY);
		    	}
		    	if(null != column_label_position && !"".equals(column_label_position)){
		    		if("居左".equals(column_label_position.toString())){
		    			xt_Generator_Table_Column_Form.setColumn_label_position("0");
		    		}else{
		    			xt_Generator_Table_Column_Form.setColumn_label_position("1");
		    		}
		    	}
		    	if(null != column_label_anchor && !"".equals(column_label_anchor)){
		    		xt_Generator_Table_Column_Form.setColumn_label_anchor(""+column_label_anchor);
		    	}
		    	if(null != column_type && !"".equals(column_type)){
		    		if(column_type.toString().equals("文本框")){
		    			xt_Generator_Table_Column_Form.setColumn_type("0");
		    		}else if(column_type.toString().equals("文本域")){
		    			xt_Generator_Table_Column_Form.setColumn_type("1");
		    		}else if(column_type.toString().equals("数字框")){
		    			xt_Generator_Table_Column_Form.setColumn_type("2");
		    		}else if(column_type.toString().equals("下拉框")){
		    			xt_Generator_Table_Column_Form.setColumn_type("3");
		    		}else if(column_type.toString().equals("日期框")){
		    			xt_Generator_Table_Column_Form.setColumn_type("4");
		    		}else if(column_type.toString().equals("文件框")){
		    			xt_Generator_Table_Column_Form.setColumn_type("5");
		    		}else{
		    			xt_Generator_Table_Column_Form.setColumn_type("0");
		    		}
		    	}
		    	if(null != isHidden && !"".equals(isHidden)){
		    		if(isHidden.toString().equals("否")){
		    			xt_Generator_Table_Column_Form.setIsHidden("0");
		    		}else if(isHidden.toString().equals("是")){
		    			xt_Generator_Table_Column_Form.setIsHidden("1");
		    		}
		    	}
		    	if(null != xt_script_id && !"".equals(xt_script_id)){
		    		xt_Generator_Table_Column_Form.setXt_script_id((String)xt_script_id);
		    	}
		    	xt_Generator_Table_Column_FormList.add(xt_Generator_Table_Column_Form);
		    }
		}
		return xt_Generator_Table_Column_FormList;
	}
	
	/**
	 * 删除
	 * @param xt_generator_id
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/delXtGenerator",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtGenerator(String xt_generator_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_generator_id && !"".equals(xt_generator_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_generator_id",xt_generator_id.split(","));
			i=xt_GeneratorService.delXtGenerator(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 返回一对多信息
	 * @param xtGeneratorOneToManyFormListJSON
	 * @return
	 */
	public List<Xt_Generator_TableMany_To_One> commonXtGeneratorOneToManyFormListJSON(String xtGeneratorOneToManyFormListJSON){
		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = new ArrayList<Xt_Generator_TableMany_To_One>();
		if(!StringUtils.isEmpty(xtGeneratorOneToManyFormListJSON)){
			try {
				xtGeneratorOneToManyFormListJSON = URLDecoder.decode(xtGeneratorOneToManyFormListJSON, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			xt_Generator_TableMany_To_OneList = FastJsonUtils.toList(xtGeneratorOneToManyFormListJSON, Xt_Generator_TableMany_To_One.class);
			//一对多表中列信息
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
				List<Xt_Generator_Table_ColumnMany_To_One> xt_Generator_Table_ColumnMany_To_OneList;
				try {
					xt_Generator_Table_ColumnMany_To_OneList = JsonUtil.toList(URLDecoder.decode(xt_Generator_TableMany_To_One.getXt_generator_ontomany_grid(), "UTF-8"), Xt_Generator_Table_ColumnMany_To_One.class);  
					for(int j = 0; j < xt_Generator_Table_ColumnMany_To_OneList.size(); j++){
						Xt_Generator_Table_ColumnMany_To_One xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(j);
						String column_label_position = xt_Generator_Table_ColumnMany_To_One.getColumn_label_position();
						String column_type = xt_Generator_Table_ColumnMany_To_One.getColumn_type();
						String isHidden = xt_Generator_Table_ColumnMany_To_One.getIsHidden();
						if(null != column_label_position && !"".equals(column_label_position)){
				    		if("居左".equals(column_label_position.toString())){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_label_position("0");
				    		}else{
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_label_position("1");
				    		}
				    	}
				    	if(null != column_type && !"".equals(column_type)){
				    		if(column_type.toString().equals("文本框")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("0");
				    		}else if(column_type.toString().equals("文本域")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("1");
				    		}else if(column_type.toString().equals("数字框")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("2");
				    		}else if(column_type.toString().equals("下拉框")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("3");
				    		}else if(column_type.toString().equals("日期框")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("4");
				    		}else if(column_type.toString().equals("文件框")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("5");
				    		}else{
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setColumn_type("0");
				    		}
				    	}
				    	if(null != isHidden && !"".equals(isHidden)){
				    		if(isHidden.toString().equals("否")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setIsHidden("0");
				    		}else if(isHidden.toString().equals("是")){
				    			xt_Generator_Table_ColumnMany_To_OneList.get(j).setIsHidden("1");
				    		}
				    	}
					}
					xt_Generator_TableMany_To_OneList.get(i).setXt_Generator_Table_ColumnMany_To_OneList(xt_Generator_Table_ColumnMany_To_OneList);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return xt_Generator_TableMany_To_OneList;
		}
		return xt_Generator_TableMany_To_OneList;
	}
}

