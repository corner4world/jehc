package jehc.xtmodules.xtcore.util.generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.allutils.AllUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtScriptDao;
import jehc.xtmodules.xtmodel.XtGenerator;
import jehc.xtmodules.xtmodel.XtGeneratorGridColumn;
import jehc.xtmodules.xtmodel.XtGeneratorSearchFiled;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumnForm;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumnManyToOne;
import jehc.xtmodules.xtmodel.XtGeneratorTableManyToOne;
import jehc.xtmodules.xtmodel.XtScript;

/**
 * 生成页面层代码（包括JS，JSP代码）
 * @author邓纯杰
 *
 */
public class GeneratorPage extends GeneratorUtil{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 生成所有页面JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	public void createPageAll(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		createPageList(xt_Generator_Table_ColumnList, xt_Generator);
		createPageAdd(xt_Generator_Table_ColumnList, xt_Generator);
		createPageUpdate(xt_Generator_Table_ColumnList, xt_Generator);
		createPageDetail(xt_Generator_Table_ColumnList, xt_Generator);
		//子表信息
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table() && xt_Generator.getOne_to_many_type().equals("0")){
			createOneToMany(xt_Generator_Table_ColumnList, xt_Generator);
		}
	}
	
	/////////////////////////////////////1.生成列表List开始//////////////////////////////////
	/**
	 * 生成列表(包括列表List,删除Del等方法)
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageList(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		StringBuffer sb = new StringBuffer();
		//定义对象
		sb.append("var store;\r\n");
		sb.append("var grid;\r\n");
		List<XtGeneratorSearchFiled> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		List<String> xt_script_idList = new ArrayList<String>();
		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
			XtGeneratorSearchFiled xt_generator_search_filed = xt_generator_search_filedList.get(i);
			String xt_script_id = xt_generator_search_filed.getXt_script_id();
			if(null != xt_script_id && !"".equals(xt_script_id)){
				xt_script_idList.add(xt_script_id);
			}
		}
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			if(null != xt_script_id && !"".equals(xt_script_id)){
				xt_script_idList.add(xt_script_id);
			}
		}
		for(XtGeneratorTableManyToOne xt_Generator_TableMany_To_One:xt_Generator_TableMany_To_OneList){
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
			for(XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One:xt_Generator_Table_ColumnMany_To_OneList){
				String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				if(null != xt_script_id && !"".equals(xt_script_id)){
					xt_script_idList.add(xt_script_id);
				}
			}
		}
		xt_script_idList = AllUtils.getNList(xt_script_idList);
		for(int i = 0; i < xt_script_idList.size(); i++){
			XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_idList.get(i));
			String xt_script_key = xtScript.getXt_script_key();
			sb.append("var "+xt_script_key +";\r\n");
		}
		sb.append("Ext.onReady(function(){\r\n");
		//添加内容区域
		sb.append(createPageContentList(xt_Generator_Table_ColumnList, xt_Generator));
		sb.append("});\r\n");
		//删除方法
		sb.append(createPageDel(xt_Generator_Table_ColumnList, xt_Generator));
		//复制一行并生成记录方法
		sb.append(createPageCopy(xt_Generator_Table_ColumnList, xt_Generator));
		//导出操作
		sb.append(createExport(xt_Generator_Table_ColumnList, xt_Generator));
		//初始化右键
		sb.append(createPageInitRight(xt_Generator_Table_ColumnList, xt_Generator));
		//查询方法
		sb.append(createPageSearch(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-list.js"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		}
		return sb.toString();
	}

	/**
	 * 创建列表内容区域
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageContentList(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String list_url = "get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition";
		String export_url = "export"+uprepchar(xt_Generator.getXt_generator_tbname())+"";
		StringBuffer sb = new StringBuffer();
		//创建下拉框远程数据或本地数据
		//查询模块
		List<XtGeneratorSearchFiled> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		List<String> xt_script_idList = new ArrayList<String>();
		for(XtGeneratorTableManyToOne xt_Generator_TableMany_To_One:xt_Generator_TableMany_To_OneList){
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
			for(XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One:xt_Generator_Table_ColumnMany_To_OneList){
				String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				if(null != xt_script_id && !"".equals(xt_script_id)){
					xt_script_idList.add(xt_script_id);
				}
			}
		}
		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
			XtGeneratorSearchFiled xt_generator_search_filed = xt_generator_search_filedList.get(i);
			String xt_script_id = xt_generator_search_filed.getXt_script_id();
			if(null != xt_script_id && !"".equals(xt_script_id)){
				xt_script_idList.add(xt_script_id);
			}
		}
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			if(null != xt_script_id && !"".equals(xt_script_id)){
				xt_script_idList.add(xt_script_id);
			}
		}
		xt_script_idList = AllUtils.getNList(xt_script_idList);
		for(int i = 0; i < xt_script_idList.size(); i++){
			String xt_script_id = xt_script_idList.get(i);
			if(null != xt_script_id && !"".equals(xt_script_id)){
				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				String xt_script_text = xtScript.getXt_script_text();
				String xt_script_key = xtScript.getXt_script_key();
				sb.append("\t"+xt_script_key + " = " + xt_script_text+"\r\n");
			}
		}
		//0.创建查询区域
		sb.append("\t/**查询区域可扩展**/\r\n");
		sb.append("\tvar items = Ext.create('Ext.FormPanel',{\r\n");
		sb.append("\t\txtype:'form',\r\n");
		sb.append("\t\tmaxHeight:150,\r\n");
		sb.append("\t\twaitMsgTarget:true,\r\n");
		sb.append("\t\tdefaultType:'textfield',\r\n");
		sb.append("\t\tautoScroll:true,\r\n");
		sb.append("\t\tfieldDefaults:{\r\n");
		sb.append("\t\t\tlabelWidth:70,\r\n");
		sb.append("\t\t\tlabelAlign:'left',\r\n");
		sb.append("\t\t\tflex:1,\r\n");
		sb.append("\t\t\tmargin:'2 5 4 5'\r\n");
		sb.append("\t\t},\r\n");
		sb.append("\t\titems:[\r\n");
    	if(!xt_generator_search_filedList.isEmpty() && xt_generator_search_filedList.size()>0){
    		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
    			XtGeneratorSearchFiled xt_generator_search_filed = xt_generator_search_filedList.get(i);
    			String xt_generator_search_name = xt_generator_search_filed.getXt_generator_search_name();
    			String xt_generator_search_label = xt_generator_search_filed.getXt_generator_search_label();
    			String xt_generator_search_type = xt_generator_search_filed.getXt_generator_search_type();
    			String xt_generator_search_label_position = xt_generator_search_filed.getXt_generator_search_label_position();
    			String xt_generator_search_length = xt_generator_search_filed.getXt_generator_search_length();
    			String xt_script_id = xt_generator_search_filed.getXt_script_id();
    			String xt_script_key = "";
    			String xt_script_valuefield="";
    			String xt_script_displayfield="";
    			if(null != xt_script_id && !"".equals(xt_script_id)){
    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
        			xt_script_key = xtScript.getXt_script_key();
        			xt_script_valuefield = xtScript.getXt_script_valuefield();
        			xt_script_displayfield = xtScript.getXt_script_displayfield();
    			}
    			String postion="left";
    			if("0".equals(xt_generator_search_label_position)){
    				postion="left";
    			}else{
    				postion="top";
    			}
    			/**类型0文本框1文本域2数字框3下拉框4日期框**/
    			String xtype="textfield";
    			if("0".equals(xt_generator_search_type)){
    				xtype="textfield";
    			}else if("1".equals(xt_generator_search_type)){
    				xtype="textareafield";
    			}else if("2".equals(xt_generator_search_type)){
    				xtype="numberfield";
    			}else if("3".equals(xt_generator_search_type)){
    				xtype="combo";
    			}else if("4".equals(xt_generator_search_type)){
    				xtype="datefield";
    			}
    			if(xt_generator_search_filedList.size() == (i+1)){
    				if(xtype.equals("numberfield")){//对数字框特定处理
    					sb.append("\t\t{\r\n");
    					sb.append("\t\t\tlayout:'table',\r\n");
    					sb.append("\t\t\txtype:'form',\r\n");
    					sb.append("\t\t\tanchor:'50%',\r\n");
    					sb.append("\t\t\titems:[\r\n");
    					//数字框
    					sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'"+xt_generator_search_label+"',\r\n");
            			sb.append("\t\t\t\tlabelWidth:"+xt_generator_search_length+",\r\n");
            			sb.append("\t\t\t\txtype:'combo',\r\n");
            			sb.append("\t\t\t\tstore:cSData(),\r\n");
            			sb.append("\t\t\t\tmode:'local',\r\n");
            			sb.append("\t\t\t\ttriggerAction:'all',\r\n");
            			sb.append("\t\t\t\teditable:false,\r\n");
            			sb.append("\t\t\t\tvalueField:'value',\r\n");
            			sb.append("\t\t\t\tdisplayField:'text',\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"_cs',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"_cs',\r\n");
            			sb.append("\t\t\t\thiddenName:'"+xt_generator_search_name+"_cs',\r\n");
            			sb.append("\t\t\t\twidth:220,\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t},\r\n");
            			//值
            			sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'值',\r\n");
            			sb.append("\t\t\t\txtype:'numberfield',\r\n");
            			sb.append("\t\t\t\tlabelWidth:15,\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\t\twidth:150,\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t}\r\n");
            			sb.append("\t\t\t]\r\n");
            			sb.append("\t\t}\r\n");
    				}else if(xtype.equals("datefield")){//对日期特定处理
    					sb.append("\t\t{\r\n");
    					sb.append("\t\t\tlayout:'table',\r\n");
    					sb.append("\t\t\txtype:'form',\r\n");
    					sb.append("\t\t\tanchor:'50%',\r\n");
    					sb.append("\t\t\titems:[\r\n");
    					//时间
    					sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'"+xt_generator_search_label+"',\r\n");
            			sb.append("\t\t\t\txtype:'"+xtype+"',\r\n");
            			if(null != xt_generator_search_length && !"".equals(xt_generator_search_length)){
            				sb.append("\t\t\t\tlabelWidth:"+xt_generator_search_length+",\r\n");
            			}
            			sb.append("\t\t\t\tformat:'Y-m-d',\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"_st',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"_st',\r\n");
            			sb.append("\t\t\t\tanchor:'30%',\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t},\r\n");
            			//至
            			sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'至',\r\n");
            			sb.append("\t\t\t\txtype:'"+xtype+"',\r\n");
            			sb.append("\t\t\t\tlabelWidth:15,\r\n");
            			sb.append("\t\t\t\tformat:'Y-m-d',\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"_et',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"_et',\r\n");
            			sb.append("\t\t\t\tanchor:'30%',\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t}\r\n");
            			sb.append("\t\t\t]\r\n");
            			sb.append("\t\t}\r\n");
    				}else{
    					sb.append("\t\t{\r\n");
            			sb.append("\t\t\tfieldLabel:'"+xt_generator_search_label+"',\r\n");
            			sb.append("\t\t\txtype:'"+xtype+"',\r\n");
            			if(null != xt_generator_search_length && !"".equals(xt_generator_search_length)){
            				sb.append("\t\t\tlabelWidth:"+xt_generator_search_length+",\r\n");
            			}
            			/**
            			if(xtype.equals("datefield")){
            				sb.append("\t\t\tformat:'Y-m-d',\r\n");
            			}else **/if(xtype.equals("combo")){
            				sb.append("\t\t\temptyText:'请选择',\r\n");
            				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
            				sb.append("\t\t\tmode:'local',\r\n");
            	            sb.append("\t\t\ttriggerAction:'all',\r\n");
            	            sb.append("\t\t\teditable:false,\r\n");
            	            sb.append("\t\t\thiddenName:'"+xt_generator_search_name+"',\r\n");
            	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
            	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
            			}
            			sb.append("\t\t\tid:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\tname:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\tanchor:'30%',\r\n");
            			sb.append("\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t}\r\n");
    				}
    			}else{
    				if(xtype.equals("numberfield")){//对数字框特定处理
    					sb.append("\t\t{\r\n");
    					sb.append("\t\t\tlayout:'table',\r\n");
    					sb.append("\t\t\txtype:'form',\r\n");
    					sb.append("\t\t\tanchor:'50%',\r\n");
    					sb.append("\t\t\titems:[\r\n");
    					//数字框
    					sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'"+xt_generator_search_label+"',\r\n");
            			sb.append("\t\t\t\tlabelWidth:"+xt_generator_search_length+",\r\n");
            			sb.append("\t\t\t\txtype:'combo',\r\n");
            			sb.append("\t\t\t\tstore:cSData(),\r\n");
            			sb.append("\t\t\t\tmode:'local',\r\n");
            			sb.append("\t\t\t\ttriggerAction:'all',\r\n");
            			sb.append("\t\t\t\teditable:false,\r\n");
            			sb.append("\t\t\t\tvalueField:'value',\r\n");
            			sb.append("\t\t\t\tdisplayField:'text',\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"_cs',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"_cs',\r\n");
            			sb.append("\t\t\t\thiddenName:'"+xt_generator_search_name+"_cs',\r\n");
            			sb.append("\t\t\t\twidth:220,\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t},\r\n");
            			//值
            			sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'值',\r\n");
            			sb.append("\t\t\t\txtype:'numberfield',\r\n");
            			sb.append("\t\t\t\tlabelWidth:15,\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\t\twidth:150,\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t}\r\n");
            			sb.append("\t\t\t]\r\n");
            			sb.append("\t\t},\r\n");
    				}else if(xtype.equals("datefield")){//对日期特定处理
    					sb.append("\t\t{\r\n");
    					sb.append("\t\t\tlayout:'table',\r\n");
    					sb.append("\t\t\txtype:'form',\r\n");
    					sb.append("\t\t\tanchor:'50%',\r\n");
    					sb.append("\t\t\titems:[\r\n");
    					//时间
    					sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'"+xt_generator_search_label+"',\r\n");
            			sb.append("\t\t\t\txtype:'"+xtype+"',\r\n");
            			if(null != xt_generator_search_length && !"".equals(xt_generator_search_length)){
            				sb.append("\t\t\t\tlabelWidth:"+xt_generator_search_length+",\r\n");
            			}
            			sb.append("\t\t\t\tformat:'Y-m-d',\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"_st',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"_st',\r\n");
            			sb.append("\t\t\t\tanchor:'30%',\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t},\r\n");
            			//至
            			sb.append("\t\t\t{\r\n");
            			sb.append("\t\t\t\tfieldLabel:'至',\r\n");
            			sb.append("\t\t\t\txtype:'"+xtype+"',\r\n");
            			sb.append("\t\t\t\tlabelWidth:15,\r\n");
            			sb.append("\t\t\t\tformat:'Y-m-d',\r\n");
            			sb.append("\t\t\t\tid:'"+xt_generator_search_name+"_et',\r\n");
            			sb.append("\t\t\t\tname:'"+xt_generator_search_name+"_et',\r\n");
            			sb.append("\t\t\t\tanchor:'30%',\r\n");
            			sb.append("\t\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t\t}\r\n");
            			sb.append("\t\t\t]\r\n");
            			sb.append("\t\t},\r\n");
    				}else{
    					sb.append("\t\t{\r\n");
            			sb.append("\t\t\tfieldLabel:'"+xt_generator_search_label+"',\r\n");
            			sb.append("\t\t\txtype:'"+xtype+"',\r\n");
            			if(null != xt_generator_search_length && !"".equals(xt_generator_search_length)){
            				sb.append("\t\t\tlabelWidth:"+xt_generator_search_length+",\r\n");
            			}/**
            			if(xtype.equals("datefield")){
            				sb.append("\t\t\tformat:'Y-m-d',\r\n");
            			}else **/if(xtype.equals("combo")){
            				sb.append("\t\t\temptyText:'请选择',\r\n");
            				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
            				sb.append("\t\t\tmode:'local',\r\n");
            	            sb.append("\t\t\ttriggerAction:'all',\r\n");
            	            sb.append("\t\t\teditable:false,\r\n");
            	            sb.append("\t\t\thiddenName:'"+xt_generator_search_name+"',\r\n");
            	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
            	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
            			}
            			sb.append("\t\t\tid:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\tname:'"+xt_generator_search_name+"',\r\n");
            			sb.append("\t\t\tanchor:'30%',\r\n");
            			sb.append("\t\t\tlabelAlign:'"+postion+"'\r\n");
            			sb.append("\t\t},\r\n");
    				}
    			}
    		}
    		sb.append("\t\t]\r\n");
    	}else{
    		sb.append("\t\t{}\r\n");
    		sb.append("\t\t]\r\n");
    	}
    	sb.append("\t});\r\n");
		
		sb.append("\tinitSearchForm('north',items,false,'left');\r\n");
		
		//1.创建store数据源
		sb.append("\tstore = getGridJsonStore('../"+root_url+"/"+list_url+"',[]);\r\n");
		//2.创建grid
		sb.append("\tgrid = Ext.create('Ext.grid.Panel',{\r\n");
		sb.append("\t\tregion:'center',\r\n");
		sb.append("\t\txtype:'panel',\r\n");
		sb.append("\t\tstore:store,\r\n");
		//标题可修改地方
		sb.append("\t\ttitle:'查询结果',\r\n");
		/**sb.append("\t\tstyle:'margin-left:auto;margin-right:auto',\r\n");**/
		sb.append("\t\tcolumnLines:true,\r\n");
		sb.append("\t\tselType:'cellmodel',\r\n");
		//列表中可多选行
		if(null != xt_Generator.getXt_generator_page_multiSelect() && !"".equals(xt_Generator.getXt_generator_page_multiSelect()) && "1".equals(xt_Generator.getXt_generator_page_multiSelect())){
			sb.append("\t\tmultiSelect:true,\r\n");
		}
		//列表中是否可折叠
//		if(null != xt_Generator.getXt_generator_page_collapsible() && !"".equals(xt_Generator.getXt_generator_page_collapsible()) && "1".equals(xt_Generator.getXt_generator_page_collapsible())){
//			sb.append("\t\tcollapsible:true,\r\n");
//		}
//		sb.append("\t\tborder:true,\r\n");
		//列表中是否显示复选框
		if(null != xt_Generator.getXt_generator_page_checkboxmodel() && !"".equals(xt_Generator.getXt_generator_page_checkboxmodel()) && "1".equals(xt_Generator.getXt_generator_page_checkboxmodel())){
			sb.append("\t\tselType:'checkboxmodel',\r\n");
		}
		/**配置1开始**/
		sb.append("\t\tviewConfig:{\r\n");
		sb.append("\t\t\temptyText:'暂无数据',\r\n");
		sb.append("\t\t\tstripeRows:true\r\n");
		sb.append("\t\t},\r\n");
		/**配置1结束**/
		
		/**配置2开始**/
		sb.append("\t\tloadMask:{\r\n");
		sb.append("\t\t\tmsg:'正在加载...'\r\n");
		sb.append("\t\t},\r\n");
		/**配置2结束**/
		
		/**列配置开始**/
		sb.append("\t\tcolumns:[\r\n");
		sb.append("\t\t\t{\r\n");
		sb.append("\t\t\t\theader:'序号',\r\n");
		sb.append("\t\t\t\twidth:77,\r\n");
		sb.append("\t\t\t\txtype:'rownumberer'\r\n");
		sb.append("\t\t\t},\r\n");	
		List<XtGeneratorGridColumn> xt_Generator_Grid_ColumnList = xt_Generator.getXt_Generator_Grid_ColumnList();
		for(int i = 0; i < xt_Generator_Grid_ColumnList.size(); i++){
			XtGeneratorGridColumn xt_Generator_Grid_Column = xt_Generator_Grid_ColumnList.get(i);
			if(i == (xt_Generator_Grid_ColumnList.size()-1)){
				sb.append("\t\t\t{\r\n");
				sb.append("\t\t\t\theader:'"+xt_Generator_Grid_Column.getXt_generator_grid_column_label()+"',\r\n");
				sb.append("\t\t\t\tflex:1,\r\n");
				sb.append("\t\t\t\tdataIndex:'"+xt_Generator_Grid_Column.getXt_generator_grid_column_name()+"'\r\n");
				sb.append("\t\t\t}\r\n");
			}else{
				sb.append("\t\t\t{\r\n");
				sb.append("\t\t\t\theader:'"+xt_Generator_Grid_Column.getXt_generator_grid_column_label()+"',\r\n");
				sb.append("\t\t\t\tflex:1,\r\n");
				sb.append("\t\t\t\tdataIndex:'"+xt_Generator_Grid_Column.getXt_generator_grid_column_name()+"'\r\n");
				sb.append("\t\t\t},\r\n");
			}
		}
		sb.append("\t\t],\r\n");
		/**列配置结束**/
		
		/**追加tbar**/
		sb.append("\t\ttbar:[\r\n");
		sb.append("\t\t\t {\r\n");
		//添加
		sb.append("\t\t\t\ttext:'添加',\r\n");
		sb.append("\t\t\t\ttooltip:'添加',\r\n");
//		sb.append("\t\t\t\tscope:this,\r\n");
		sb.append("\t\t\t\tminWidth:tbarBtnMinWidth,\r\n");
		sb.append("\t\t\t\tcls:'addBtn',\r\n");
		sb.append("\t\t\t\ticon:addIcon,\r\n");
		sb.append("\t\t\t\thandler:function(){\r\n");
		sb.append("\t\t\t\t\tadd"+uprepchar(xt_Generator.getXt_generator_tbname())+"();\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t },\r\n");
		//编辑
		sb.append("\t\t\t {\r\n");
		sb.append("\t\t\t\ttext:'编辑',\r\n");
		sb.append("\t\t\t\ttooltip:'编辑',\r\n");
//		sb.append("\t\t\t\tscope:this,\r\n");
		sb.append("\t\t\t\tminWidth:tbarBtnMinWidth,\r\n");
		sb.append("\t\t\t\tcls:'updateBtn',\r\n");
		sb.append("\t\t\t\ticon:editIcon,\r\n");
		sb.append("\t\t\t\thandler:function(){\r\n");
		sb.append("\t\t\t\t\tupdate"+uprepchar(xt_Generator.getXt_generator_tbname())+"();\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t },\r\n");
		//删除
		sb.append("\t\t\t {\r\n");
		sb.append("\t\t\t\ttext:'删除',\r\n");
		sb.append("\t\t\t\ttooltip:'删除',\r\n");
//		sb.append("\t\t\t\tscope:this,\r\n");
		sb.append("\t\t\t\tminWidth:tbarBtnMinWidth,\r\n");
		sb.append("\t\t\t\tcls:'delBtn',\r\n");
		sb.append("\t\t\t\ticon:delIcon,\r\n");
		sb.append("\t\t\t\thandler:function(){\r\n");
		sb.append("\t\t\t\t\tdel"+uprepchar(xt_Generator.getXt_generator_tbname())+"();\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t },\r\n");
		sb.append("\t\t\t {\r\n");
		sb.append("\t\t\t\ttext:'检索',\r\n");
//		sb.append("\t\t\t\tscope:this,\r\n");
		sb.append("\t\t\t\tminWidth:tbarBtnMinWidth,\r\n");
		sb.append("\t\t\t\tcls:'searchBtn',\r\n");
		sb.append("\t\t\t\ticon:searchIcon,\r\n");
		sb.append("\t\t\t\thandler:function(){\r\n");
		sb.append("\t\t\t\t\tsearch();\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t },\r\n");
		sb.append("\t\t\t {\r\n");
		sb.append("\t\t\t\ttext:'重置',\r\n");
		sb.append("\t\t\t\ttooltip:'重置',\r\n");
		sb.append("\t\t\t\tminWidth:tbarBtnMinWidth,\r\n");
		sb.append("\t\t\t\ticon:clearSearchIcon,\r\n");
		sb.append("\t\t\t\thandler:function(){\r\n");
		sb.append("\t\t\t\t\tsearchForm.reset();\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t },\r\n");
		sb.append("\t\t\t grid_toolbar_moretext_gaps,\r\n");
		//更多操作按钮包含以下代码
		sb.append("\t\t\t {\r\n");
		sb.append("\t\t\t\t text:moretext,\r\n");
		sb.append("\t\t\t\t tooltip:moretexttooltip,\r\n");
//		sb.append("\t\t\t\t scope:this,\r\n");
		sb.append("\t\t\t\t icon:listIcon,\r\n");
		sb.append("\t\t\t\t iconAlign:'left',\r\n");
		sb.append("\t\t\t\t menu:[\r\n");
			//复制一行并生成记录
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'复制一行并生成记录',\r\n");
			sb.append("\t\t\t\t\ttooltip:'复制一行并生成记录',\r\n");
//			sb.append("\t\t\t\t\tscope:this,\r\n");
			sb.append("\t\t\t\t\tglyph:0xf0ea,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\tcopy"+uprepchar(xt_Generator.getXt_generator_tbname())+"();\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t },\r\n");
			//明 细
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'明 细',\r\n");
			sb.append("\t\t\t\t\ttooltip:'明 细',\r\n");
//			sb.append("\t\t\t\t\tscope:this,\r\n");
			sb.append("\t\t\t\t\tglyph:0xf03b,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\tdetail"+uprepchar(xt_Generator.getXt_generator_tbname())+"();\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t },\r\n");
			sb.append("\t\t\t\t '-',\r\n");
			//导 出
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'导 出',\r\n");
			sb.append("\t\t\t\t\ttooltip:'导 出',\r\n");
//			sb.append("\t\t\t\t\tscope:this,\r\n");
			sb.append("\t\t\t\t\tglyph:0xf1c3,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\texport"+uprepchar(xt_Generator.getXt_generator_tbname())+"(grid,'../"+root_url+"/"+export_url+"');\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t },\r\n");
			//打印
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'打 印',\r\n");
			sb.append("\t\t\t\t\ttooltip:'打 印',\r\n");
//			sb.append("\t\t\t\t\tscope:this,\r\n");
			sb.append("\t\t\t\t\tglyph:0xf02f,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\tprinterGrid(grid);\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t },\r\n");
			sb.append("\t\t\t\t '-',\r\n");
			//全选
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'全 选',\r\n");
			sb.append("\t\t\t\t\ttooltip:'全 选',\r\n");
			sb.append("\t\t\t\t\tglyph:0xf046,\r\n");
			sb.append("\t\t\t\t\thandler:function(){selectAll(grid);}\r\n");
			sb.append("\t\t\t\t },\r\n");
			//反选
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'反 选',\r\n");
			sb.append("\t\t\t\t\ttooltip:'反 选',\r\n");
			sb.append("\t\t\t\t\tglyph:0xf096,\r\n");
			sb.append("\t\t\t\t\thandler:function(){unSelectAll(grid);}\r\n");
			sb.append("\t\t\t\t },\r\n");
			sb.append("\t\t\t\t '-',\r\n");
			//刷新
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'刷 新',\r\n");
			sb.append("\t\t\t\t\ttooltip:'刷 新',\r\n");
			sb.append("\t\t\t\t\tglyph:0xf021,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\tgrid.getStore().reload();\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t },\r\n");
			//查询
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'检 索',\r\n");
			sb.append("\t\t\t\t\ttooltip:'检 索',\r\n");
//			sb.append("\t\t\t\t\tscope:this,\r\n");
			sb.append("\t\t\t\t\tglyph:0xf002,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\tsearch();\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t },\r\n");
			//清空
			sb.append("\t\t\t\t {\r\n");
			sb.append("\t\t\t\t\ttext:'重 置',\r\n");
			sb.append("\t\t\t\t\ttooltip:'重 置',\r\n");
//			sb.append("\t\t\t\t\tscope:this,\r\n");
			sb.append("\t\t\t\t\tglyph:0xf014,\r\n");
			sb.append("\t\t\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\t\t\tsearchForm.reset();\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t }\r\n");
			sb.append("\t\t\t\t ]\r\n");
		sb.append("\t\t\t }\r\n");
		//TaBr结束
		sb.append("\t\t],\r\n");
		sb.append("\t\tbbar:getGridBBar(store),\r\n");
		//双击事件
		sb.append("\t\tlisteners:{\r\n");
		sb.append("\t\t\t'rowdblclick':function(grid, rowIndex, e){\r\n");
		sb.append("\t\t\t\tdetail"+uprepchar(xt_Generator.getXt_generator_tbname())+"();\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t});\r\n");
		//追加ViewPort渲染
		sb.append("\tExt.create('Ext.Viewport',{\r\n");
		sb.append("\t\tlayout:'border',\r\n");
		sb.append("\t\txtype:'viewport',\r\n");
		sb.append("\t\titems:[searchForm,grid]\r\n");
		sb.append("\t});\r\n");
		//调用右键
		sb.append("\t/**调用右键**/\r\n");
		sb.append("\tinitRight();\r\n");
		sb.append("\tstore.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});\r\n");
		return sb.toString();
	}
	public String createPageDel(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String del_url = "del"+uprepchar(xt_Generator.getXt_generator_tbname());
		StringBuffer sb = new StringBuffer();
		//追加删除方法
		sb.append("/**删除操作**/\r\n");
		sb.append("function del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		sb.append("\tvar model = grid.getSelectionModel();\r\n");
		sb.append("\tif(model.selected.length == 0){\r\n");
		sb.append("\t\tmsgTishi('请选择后在删除');\r\n");
		sb.append("\t\treturn;\r\n");
		sb.append("\t}\r\n");
		sb.append("\tvar "+getColumnKey(xt_Generator_Table_ColumnList)+";\r\n");
		sb.append("\tfor(var i = 0; i < model.selected.length; i++){\r\n");
		sb.append("\t\tif(null == "+getColumnKey(xt_Generator_Table_ColumnList)+"){\r\n");
		sb.append("\t\t\t"+getColumnKey(xt_Generator_Table_ColumnList)+"=model.selected.items[i].data."+getColumnKey(xt_Generator_Table_ColumnList)+";\r\n");
		sb.append("\t\t}else{\r\n");
		sb.append("\t\t\t"+getColumnKey(xt_Generator_Table_ColumnList)+"="+getColumnKey(xt_Generator_Table_ColumnList)+"+\",\"+model.selected.items[i].data."+getColumnKey(xt_Generator_Table_ColumnList)+";\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t}\r\n");
		sb.append("\tExt.Msg.confirm('提示','确定删除该行数据？',function(btn){\r\n");
		sb.append("\t\tif(btn == 'yes'){\r\n");
		sb.append("\t\t\tvar params = {"+getColumnKey(xt_Generator_Table_ColumnList)+":"+getColumnKey(xt_Generator_Table_ColumnList)+"};\r\n");
		sb.append("\t\t\tajaxRequest('../"+root_url+"/"+del_url+"',grid,params,'正在执行删除操作中！请稍后...');\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t});\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
	public String createPageCopy(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String copy_url = "copy"+uprepchar(xt_Generator.getXt_generator_tbname());
		StringBuffer sb = new StringBuffer();
		sb.append("/**复制一行并生成记录**/\r\n");
		sb.append("function copy"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		sb.append("\tvar record = grid.getSelectionModel().selected;\r\n");
		sb.append("\tif(record.length == 0){\r\n");
		sb.append("\t\tmsgTishi('请选择要复制的行！');\r\n");
		sb.append("\t\treturn;\r\n");
		sb.append("\t}\r\n");
		sb.append("\tExt.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){\r\n");
		sb.append("\t\tif(btn == 'yes'){\r\n");
		sb.append("\t\t\tvar params = {"+getColumnKey(xt_Generator_Table_ColumnList)+":record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+"};\r\n");
		sb.append("\t\t\tajaxRequest('../"+root_url+"/"+copy_url+"',grid,params,'正在执行复制一行并生成记录！请稍后...');\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t});\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
	public String createExport(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		StringBuffer sb = new StringBuffer();
		sb.append("/**导出**/\r\n");
		sb.append("function export"+uprepchar(xt_Generator.getXt_generator_tbname())+"(grid,url){\r\n");
		sb.append("\texportExcel(grid,url);\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
	public String createPageInitRight(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String export_url = "export"+uprepchar(xt_Generator.getXt_generator_tbname())+"";
		StringBuffer sb = new StringBuffer();
		sb.append("/**初始化右键**/\r\n");
		sb.append("function initRight(){\r\n");
		sb.append("\tvar contextmenu = new Ext.menu.Menu({\r\n");
		sb.append("\t\tid:'theContextMenu',\r\n");
		sb.append("\t\titems:[{\r\n");
		sb.append("\t\t\ttext:'添 加',\r\n");
		sb.append("\t\t\tglyph:0xf016,\r\n");
		sb.append("\t\t\tid:'add"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item',\r\n");
		sb.append("\t\t\thandler:function(){add"+uprepchar(xt_Generator.getXt_generator_tbname())+"();}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'编 辑',\r\n");
		sb.append("\t\t\tglyph:0xf044,\r\n");
		sb.append("\t\t\tid:'update"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item',\r\n");
		sb.append("\t\t\thandler:function(){update"+uprepchar(xt_Generator.getXt_generator_tbname())+"();}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'删 除',\r\n");
		sb.append("\t\t\tglyph:0xf014,\r\n");
		sb.append("\t\t\tid:'del"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item',\r\n");
		sb.append("\t\t\thandler:function(){del"+uprepchar(xt_Generator.getXt_generator_tbname())+"();}\r\n");
		sb.append("\t\t},'-',{\r\n");
		sb.append("\t\t\ttext:'复制一行并生成记录',\r\n");
		sb.append("\t\t\tglyph:0xf0ea,\r\n");
		sb.append("\t\t\tid:'copy"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item',\r\n");
		sb.append("\t\t\thandler:function(){copy"+uprepchar(xt_Generator.getXt_generator_tbname())+"();}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'明 细',\r\n");
		sb.append("\t\t\tglyph:0xf03b,\r\n");
		sb.append("\t\t\tid:'detail"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item',\r\n");
		sb.append("\t\t\thandler:function(){detail"+uprepchar(xt_Generator.getXt_generator_tbname())+"();}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'导 出',\r\n");
		sb.append("\t\t\tglyph:0xf1c3,\r\n");
		sb.append("\t\t\thandler:function(){\r\n");
		sb.append("\t\t\t\texport"+uprepchar(xt_Generator.getXt_generator_tbname())+"(grid,'../"+root_url+"/"+export_url+"');\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'打 印',\r\n");
		sb.append("\t\t\tglyph:0xf02f,\r\n");
		sb.append("\t\t\thandler:function(){printerGrid(grid);}\r\n");
		sb.append("\t\t},'-',{\r\n");
		sb.append("\t\t\ttext:'全 选',\r\n");
		sb.append("\t\t\tglyph:0xf046,\r\n");
		sb.append("\t\t\thandler:function(){selectAll(grid);}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'反 选',\r\n");
		sb.append("\t\t\tglyph:0xf096,\r\n");
		sb.append("\t\t\thandler:function(){unSelectAll(grid);}\r\n");
		sb.append("\t\t},'-',{\r\n");
		sb.append("\t\t\ttext:'刷 新',\r\n");
		sb.append("\t\t\tglyph:0xf021,\r\n");
		sb.append("\t\t\thandler:function(){refreshGrid(grid);}\r\n");
		sb.append("\t\t}]\r\n");
		sb.append("\t});\r\n");
//		sb.append("\tgrid.on('itemcontextmenu',function(view,record,item,index,e){ \r\n");
//		sb.append("\t\te.preventDefault(); \r\n");
//		sb.append("\t\tcontextmenu.showAt(e.getXY());\r\n");
//		sb.append("\t});\r\n");
		sb.append("\tinitrightgridcontextmenu(grid,contextmenu,['update"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item','del"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item','copy"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item','detail"+uprepchar(xt_Generator.getXt_generator_tbname())+"Item']);\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}
	/////////////////////////////////////1.生成列表List结束//////////////////////////////////
	
	
	
	
	
	
	/////////////////////////////////////2.生成添加Add开始//////////////////////////////////
	/**
	 * 生成添加页面JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageAdd(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		StringBuffer sb = new StringBuffer();
		sb.append(createPageContentAdd(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-add.js"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 创建添加JS内容区域
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageContentAdd(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String add_url = "add"+uprepchar(xt_Generator.getXt_generator_tbname());
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		StringBuffer sb = new StringBuffer();
		//添加定义
		sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinAdd;\r\n");
		sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAdd;\r\n");
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			//只有为表单方式一对多则定义该对象
			if(xt_Generator.getOne_to_many_type().equals("1")){
				sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet;\r\n");
				//定义子表fileset
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("var "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet;\r\n");
				}
			}
		}
		//添加方法
		//////////////////////生成Win模块开始/////////////////////////////
		sb.append("function add"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		sb.append("\tinit"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormAdd();\r\n");
		//子表操作init 开始
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			if(xt_Generator.getOne_to_many_type().equals("0")){
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("\tinit"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Grid(0);\r\n");
				}
			}
		}
		//子表操作init 结束
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinAdd = Ext.create('Ext.Window',{\r\n");
		sb.append("\t\tlayout:'fit',\r\n");
		//可变设置
		sb.append("\t\twidth:"+xt_Generator.getXt_generator_page_width()+",\r\n");
		sb.append("\t\theight:"+xt_Generator.getXt_generator_page_height()+",\r\n");
		sb.append("\t\tmaximizable:true,\r\n");
		sb.append("\t\tminimizable:true,\r\n");
		sb.append("\t\tanimateTarget:document.body,\r\n");
		sb.append("\t\tplain:true,\r\n");
		sb.append("\t\tmodal:true,\r\n");
		//可变设置
		sb.append("\t\ttitle:'添加信息',\r\n");
		sb.append("\t\tlisteners:{\r\n");
		sb.append("\t\t\tminimize:function(win,opts){\r\n");
		////////////重新解决window展开和收起操作开始
//		sb.append("\t\t\t\twin.collapse();\r\n");
		sb.append("\t\t\t\tif(!win.collapse()){\r\n");
		sb.append("\t\t\t\t\twin.collapse();\r\n");
		sb.append("\t\t\t\t}else{\r\n");
		sb.append("\t\t\t\t\twin.expand();\r\n");
		sb.append("\t\t\t\t}\r\n");
		////////////重新解决window展开和收起操作结束
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t},\r\n");
		sb.append("\t\titems:"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAdd,\r\n");
		sb.append("\t\tbuttons:[{\r\n");
		sb.append("\t\t\ttext:'保存',\r\n");
		sb.append("\t\t\titemId:'save',\r\n");
		sb.append("\t\t\thandler:function(button){\r\n");
		/////////////子表信息获取////////////////////
		if(xt_Generator.isIs_main_table() && xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.getOne_to_many_type().equals("0")){
			List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
				sb.append("\t\t\t\tvar "+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"JsonArray = [];\r\n");
				sb.append("\t\t\t\tfor(var i = 0; i < "+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid.getStore().getCount();i++){\r\n");
				sb.append("\t\t\t\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"JsonArray.push("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid.store.getAt(i).data); \r\n");
				sb.append("\t\t\t\t}\r\n");
				sb.append("\t\t\t\tvar "+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List = Ext.encode("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"JsonArray);\r\n");
				sb.append("\t\t\t\tExt.getCmp('"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List').setValue("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List);\r\n");
			}
		}
		sb.append("\t\t\t\tsubmitForm("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAdd,'../"+root_url+"/"+add_url+"',grid,"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinAdd,false,true);\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'关闭',\r\n");
		sb.append("\t\t\titemId:'close',\r\n");
		sb.append("\t\t\thandler:function(button){\r\n");
		sb.append("\t\t\t\tbutton.up('window').close();\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t}]\r\n");
		sb.append("\t});\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinAdd.show();\r\n");
		if("1".equals(xt_Generator.getXt_generator_page_max())){
			sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinAdd.maximize();\r\n");
		}
		//初始化附件右键
		sb.append("\t"+createAttachmentRight(xt_Generator, 1)+"\r\n");
		sb.append("}\r\n");
		//////////////////////生成Win模块结束/////////////////////////////
		
		//////////////////////生成Form模块开始/////////////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()&&xt_Generator.getOne_to_many_type().equals("1")){
			sb.append(createOneToManyUsingAddBaseForm(xt_Generator_Table_ColumnList, xt_Generator));
		}else{
			sb.append("function init"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormAdd(){\r\n");
			sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAdd = Ext.create('Ext.FormPanel',{\r\n");
			sb.append("\t\txtype:'form',\r\n");
			sb.append("\t\twaitMsgTarget:true,\r\n");
			sb.append("\t\tdefaultType:'textfield',\r\n");
			//是否支持滚动条
			if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
				sb.append("\t\tautoScroll:true,\r\n");
			}
			//禁用X轴滚动条
			sb.append("\t\t/**新方法使用开始**/\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\t/**新方法使用结束**/\r\n");
			sb.append("\t\tfieldDefaults:{\r\n");
			//默认标签宽度
			if(xt_Generator.getXt_generator_page_labelWidth()>=0){
				sb.append("\t\t\tlabelWidth:"+xt_Generator.getXt_generator_page_labelWidth()+",\r\n");
			}else{
				sb.append("\t\t\tlabelWidth:70,\r\n");
			}
			//默认表单标签对齐方式1向左2向右3向上
			if(xt_Generator.getXt_generator_page_labelAlign().equals("1")){
				sb.append("\t\t\tlabelAlign:'left',\r\n"); 
			}else if(xt_Generator.getXt_generator_page_labelAlign().equals("2")){
				sb.append("\t\t\tlabelAlign:'right',\r\n");
			}else if(xt_Generator.getXt_generator_page_labelAlign().equals("3")){
				sb.append("\t\t\tlabelAlign:'top',\r\n");
			}else{
				sb.append("\t\t\tlabelAlign:'left',\r\n"); 
			}
			sb.append("\t\t\tflex:1,\r\n"); 
			sb.append("\t\t\tmargin:'2 5 4 5'\r\n"); 
			sb.append("\t\t},\r\n"); 
			sb.append("\t\titems:[\r\n");
			List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
				if(null != column_comment && !"".equals(column_comment)){
					column_comment = column_comment.replaceAll("amp;", "");
				}
				if(!getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
					if(i == (xt_Generator_Table_ColumnList.size()-1)){
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
							sb.append("\t\t\tallowBlank:false,\r\n");
						}
						if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
						}
						if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
							if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							//子表操作 逗号封闭
							if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
								sb.append("\t\t},\r\n");
							}else{
								sb.append("\t\t}\r\n");
							}
						}else{
							//子表操作 逗号封闭
							if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
								sb.append("\t\t},\r\n");
							}else{
								sb.append("\t\t}\r\n");
							}
						}
					}else{
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
							sb.append("\t\t\tallowBlank:false,\r\n");
						}
						if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
						}
						if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
							if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t},\r\n");
						}
					}
				}
			}
			////////////////////子表Grid配置
			if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList= xt_Generator.getXt_Generator_TableMany_To_OneList();
				//遍历子表GridJson值元素
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
					sb.append("\t\t\txtype:'textfield',\r\n");
					sb.append("\t\t\thidden:true,\r\n");
					sb.append("\t\t\tid:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List',\r\n");
					sb.append("\t\t\tname:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List',\r\n");
					sb.append("\t\t},\r\n");
				}
				
				//遍历子表Grid元素
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					if(i == (xt_Generator_TableMany_To_OneList.size()-1)){
						sb.append("\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid\r\n");
					}else{
						sb.append("\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid,\r\n");
					}
				}
			}
			sb.append("\t\t]\r\n");
			//////////////////////生成Form模块结束/////////////////////////////
			sb.append("\t});\r\n");
			sb.append("}\r\n");
		}
		return sb.toString();
	}
	/////////////////////////////////////2.生成添加Add结束//////////////////////////////////
	
	/////////////////////////////////////3.生成修改Update开始//////////////////////////////////
	/**
	 * 生成修改页面JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageUpdate(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		//回显附件使用
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_Form_AttachmentList = new ArrayList<XtGeneratorTableColumnForm>();
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
				xt_Generator_Table_Column_Form_AttachmentList.add(xt_Generator_Table_Column_Form);
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append(createPageContentUpdate(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-update.js"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成修改页面JS内容
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageContentUpdate(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String obj_url = "get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById";
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		String update_url = "update"+uprepchar(xt_Generator.getXt_generator_tbname());
		StringBuffer sb = new StringBuffer();
		//添加定义
		sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinEdit;\r\n");
		sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit;\r\n");
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			//只有为表单方式一对多则定义该对象
			if(xt_Generator.getOne_to_many_type().equals("1")){
				sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet;\r\n");
				//定义子表fileset
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("var "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet;\r\n");
				}
			}
		}
		//添加方法
		//////////////////////生成Win模块开始/////////////////////////////
		sb.append("function update"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		//判断是否选择对象
		sb.append("\tvar record = grid.getSelectionModel().selected;\r\n");
		sb.append("\tif(record.length == 0){\r\n");
		sb.append("\t\tmsgTishi('请选择要修改的一项！');\r\n");
		sb.append("\t\treturn;\r\n");	
		sb.append("\t}\r\n");
		//子表操作init 开始
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			//只有为表单方式一对多则定义该对象
			if(xt_Generator.getOne_to_many_type().equals("0")){
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("\tinit"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Grid(record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
				}
			}
		}
		//子表操作init 结束
		sb.append("\tinit"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormEdit();\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinEdit = Ext.create('Ext.Window',{\r\n");
		sb.append("\t\tlayout:'fit',\r\n");
		//可变设置
		sb.append("\t\twidth:"+xt_Generator.getXt_generator_page_width()+",\r\n");
		sb.append("\t\theight:"+xt_Generator.getXt_generator_page_height()+",\r\n");
		sb.append("\t\tmaximizable:true,\r\n");
		sb.append("\t\tminimizable:true,\r\n");
		sb.append("\t\tanimateTarget:document.body,\r\n");
		sb.append("\t\tplain:true,\r\n");
		sb.append("\t\tmodal:true,\r\n");
		//可变设置
		sb.append("\t\ttitle:'编辑信息',\r\n");
		sb.append("\t\tlisteners:{\r\n");
		sb.append("\t\t\tminimize:function(win,opts){\r\n");
		////////////重新解决window展开和收起操作开始
//		sb.append("\t\t\t\twin.collapse();\r\n");
		sb.append("\t\t\t\tif(!win.collapse()){\r\n");
		sb.append("\t\t\t\t\twin.collapse();\r\n");
		sb.append("\t\t\t\t}else{\r\n");
		sb.append("\t\t\t\t\twin.expand();\r\n");
		sb.append("\t\t\t\t}\r\n");
		////////////重新解决window展开和收起操作结束
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t},\r\n");
		sb.append("\t\titems:"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit,\r\n");
		sb.append("\t\tbuttons:[{\r\n");
		sb.append("\t\t\ttext:'保存',\r\n");
		sb.append("\t\t\titemId:'save',\r\n");
		sb.append("\t\t\thandler:function(button){\r\n");
		/////////////子表信息获取////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			if(xt_Generator.getOne_to_many_type().equals("0")){
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("\t\t\t\tvar "+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"JsonArray = [];\r\n");
					sb.append("\t\t\t\tfor(var i = 0; i < "+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid.getStore().getCount();i++){\r\n");
					sb.append("\t\t\t\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"JsonArray.push("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid.store.getAt(i).data); \r\n");
					sb.append("\t\t\t\t}\r\n");
					sb.append("\t\t\t\tvar "+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List = Ext.encode("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"JsonArray);\r\n");
					sb.append("\t\t\t\tExt.getCmp('"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List').setValue("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List);\r\n");
				}
			}
		}
		sb.append("\t\t\t\tsubmitForm("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit,'../"+root_url+"/"+update_url+"',grid,"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinEdit,false,true);\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t},{\r\n");
		sb.append("\t\t\ttext:'关闭',\r\n");
		sb.append("\t\t\titemId:'close',\r\n");
		sb.append("\t\t\thandler:function(button){\r\n");
		sb.append("\t\t\t\tbutton.up('window').close();\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t}]\r\n");
		sb.append("\t});\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinEdit.show();\r\n");
		if("1".equals(xt_Generator.getXt_generator_page_max())){
			sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinEdit.maximize();\r\n");
		}
		//初始化附件右键
		sb.append("\t"+createAttachmentRight(xt_Generator, 1)+"\r\n");
		//附件回显
		sb.append(createAttachmentObject(xt_Generator));
		////////如果当前是一对多并且表单方式添加则加载回调方法
		if(xt_Generator.isIs_main_table() && xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.getOne_to_many_type().equals("1")){
			sb.append("\tloadFormDataCallBack("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit,'../"+root_url+"/"+obj_url+"?"+getColumnKey(xt_Generator_Table_ColumnList)+"='+ record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+",callFnUpdate);\r\n");
		}else{
			sb.append("\tloadFormData("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit,'../"+root_url+"/"+obj_url+"?"+getColumnKey(xt_Generator_Table_ColumnList)+"='+ record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
		}
		sb.append("}\r\n");
		//////////////////////生成Win模块结束/////////////////////////////
		
		//////////////////////生成Form模块开始/////////////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()&&xt_Generator.getOne_to_many_type().equals("1")){
			sb.append(createOneToManyUsingUpdateBaseForm(xt_Generator_Table_ColumnList, xt_Generator));
		}else{
			sb.append("function init"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormEdit(){\r\n");
			sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit = Ext.create('Ext.FormPanel',{\r\n");
			sb.append("\t\txtype:'form',\r\n");
			sb.append("\t\twaitMsgTarget:true,\r\n");
			sb.append("\t\tdefaultType:'textfield',\r\n");
			//是否支持滚动条
			if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
				sb.append("\t\tautoScroll:true,\r\n");
			}
			//禁用X轴滚动条
			sb.append("\t\t/**新方法使用开始**/\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\t/**新方法使用结束**/\r\n");
			sb.append("\t\tfieldDefaults:{\r\n");
			//标签宽度
			if(xt_Generator.getXt_generator_page_labelWidth()>=0){
				sb.append("\t\t\tlabelWidth:"+xt_Generator.getXt_generator_page_labelWidth()+",\r\n");
			}else{
				sb.append("\t\t\tlabelWidth:70,\r\n");
			}
			//表单标签对齐方式1向左2向右3向上
			if(xt_Generator.getXt_generator_page_labelAlign().equals("1")){
				sb.append("\t\t\tlabelAlign:'left',\r\n"); 
			}else if(xt_Generator.getXt_generator_page_labelAlign().equals("2")){
				sb.append("\t\t\tlabelAlign:'right',\r\n");
			}else if(xt_Generator.getXt_generator_page_labelAlign().equals("3")){
				sb.append("\t\t\tlabelAlign:'top',\r\n");
			}else{
				sb.append("\t\t\tlabelAlign:'left',\r\n"); 
			} 
			sb.append("\t\t\tflex:1,\r\n"); 
			sb.append("\t\t\tmargin:'2 5 4 5'\r\n"); 
			sb.append("\t\t},\r\n"); 
			//创建列字段 开始
			sb.append("\t\titems:[\r\n");
			List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
				if(null != column_comment && !"".equals(column_comment)){
					column_comment = column_comment.replaceAll("amp;", "");
				}
				if(i == (xt_Generator_Table_ColumnList.size()-1)){
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
						sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
						sb.append("\t\t\t\t\t**/\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}else{
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}
				}else{
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
						sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
						sb.append("\t\t\t\t\t**/\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						sb.append("\t\t},\r\n");
					}else{
						sb.append("\t\t},\r\n");
					}
				}
			}
			////////////////////子表Grid配置
			if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList= xt_Generator.getXt_Generator_TableMany_To_OneList();
				//遍历子表GridJson值元素
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
					sb.append("\t\t\txtype:'textfield',\r\n");
					sb.append("\t\t\thidden:true,\r\n");
					sb.append("\t\t\tid:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List',\r\n");
					sb.append("\t\t\tname:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List',\r\n");
					sb.append("\t\t},\r\n");
				}
				
				//遍历子表Grid元素
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					if(i == (xt_Generator_TableMany_To_OneList.size()-1)){
						sb.append("\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid\r\n");
					}else{
						sb.append("\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid,\r\n");
					}
				}
			}
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
			//////////////////////生成Form模块结束/////////////////////////////
		}
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()&&xt_Generator.getOne_to_many_type().equals("1")){
			
		}else{
			sb.append("}\r\n");
		}
		return sb.toString();
	}
	/////////////////////////////////////4.生成修改Update结束//////////////////////////////////
	
	/////////////////////////////////////3.生成明细Detail开始//////////////////////////////////
	/**
	 * 生成明细页面JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageDetail(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		StringBuffer sb = new StringBuffer();
		sb.append(createPageContentDetail(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-detail.js"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成明细页面JS内容
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageContentDetail(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String obj_url = "get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById";
		XtScriptDao xt_ScriptService = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		StringBuffer sb = new StringBuffer();
		//添加定义
		sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinDetail;\r\n");
		sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetail;\r\n");
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			//只有为表单方式一对多则定义该对象
			if(xt_Generator.getOne_to_many_type().equals("1")){
				sb.append("var "+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetailFieldSet;\r\n");
				//定义子表fileset
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("var "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet;\r\n");
				}
			}
		}
		//添加方法
		//////////////////////生成Win模块开始/////////////////////////////
		sb.append("function detail"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		//判断是否选择对象
		sb.append("\tvar record = grid.getSelectionModel().selected;\r\n");
		sb.append("\tif(record.length == 0){\r\n");
		sb.append("\t\tmsgTishi('请选择要查看明细的项！');\r\n");
		sb.append("\t\treturn;\r\n");	
		sb.append("\t}\r\n");
		
		sb.append("\tinit"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormDetail();\r\n");
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			//只有为表单方式一对多则定义该对象
			if(xt_Generator.getOne_to_many_type().equals("0")){
				//子表操作init 开始
				if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
					List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
					for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
						XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
						sb.append("\tinit"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Grid(record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
					}
				}
				//子表操作init 结束
			}
		}
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinDetail = Ext.create('Ext.Window',{\r\n");
		sb.append("\t\tlayout:'fit',\r\n");
		//可变设置
		sb.append("\t\twidth:"+xt_Generator.getXt_generator_page_width()+",\r\n");
		sb.append("\t\theight:"+xt_Generator.getXt_generator_page_height()+",\r\n");
		sb.append("\t\tmaximizable:true,\r\n");
		sb.append("\t\tminimizable:true,\r\n");
		sb.append("\t\tanimateTarget:document.body,\r\n");
		sb.append("\t\tplain:true,\r\n");
		sb.append("\t\tmodal:true,\r\n");
		//可变设置
		sb.append("\t\ttitle:'明细信息',\r\n");
		sb.append("\t\tlisteners:{\r\n");
		sb.append("\t\t\tminimize:function(win,opts){\r\n");
		////////////重新解决window展开和收起操作开始
//		sb.append("\t\t\t\twin.collapse();\r\n");
		sb.append("\t\t\t\tif(!win.collapse()){\r\n");
		sb.append("\t\t\t\t\twin.collapse();\r\n");
		sb.append("\t\t\t\t}else{\r\n");
		sb.append("\t\t\t\t\twin.expand();\r\n");
		sb.append("\t\t\t\t}\r\n");
		////////////重新解决window展开和收起操作结束
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t},\r\n");
		sb.append("\t\titems:"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetail,\r\n");
		sb.append("\t\tbuttons:[{\r\n");
		sb.append("\t\t\ttext:'关闭',\r\n");
		sb.append("\t\t\titemId:'close',\r\n");
		sb.append("\t\t\thandler:function(button){\r\n");
		sb.append("\t\t\t\tbutton.up('window').close();\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t}]\r\n");
		sb.append("\t});\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinDetail.show();\r\n");
		if("1".equals(xt_Generator.getXt_generator_page_max())){
			sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"WinDetail.maximize();\r\n");
		}
		//初始化附件右键
		sb.append("\t"+createAttachmentRight(xt_Generator, 2)+"\r\n");
		//附件回显
		sb.append(createAttachmentObject(xt_Generator));
		////////如果当前是一对多并且表单方式添加则加载回调方法
		if(xt_Generator.isIs_main_table() && xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.getOne_to_many_type().equals("1")){
			sb.append("\tloadFormDataCallBack("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetail,'../"+root_url+"/"+obj_url+"?"+getColumnKey(xt_Generator_Table_ColumnList)+"='+ record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+",callFnDetail);\r\n");
		}else{
			sb.append("\tloadFormData("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetail,'../"+root_url+"/"+obj_url+"?"+getColumnKey(xt_Generator_Table_ColumnList)+"='+ record.items[0].data."+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
		}
		sb.append("}\r\n");
		//////////////////////生成Win模块结束/////////////////////////////
		
		//////////////////////生成Form模块开始/////////////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()&&xt_Generator.getOne_to_many_type().equals("1")){
			sb.append(createOneToManyUsingDetailBaseForm(xt_Generator_Table_ColumnList, xt_Generator));
		}else{
			sb.append("function init"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormDetail(){\r\n");
			sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetail = Ext.create('Ext.FormPanel',{\r\n");
			sb.append("\t\txtype:'form',\r\n");
			sb.append("\t\twaitMsgTarget:true,\r\n");
			sb.append("\t\tdefaultType:'textfield',\r\n");
			//是否支持滚动条
			if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
				sb.append("\t\tautoScroll:true,\r\n");
			}
			//禁用X轴滚动条
			sb.append("\t\t/**新方法使用开始**/\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\t/**新方法使用结束**/\r\n");
			sb.append("\t\tfieldDefaults:{\r\n");
			//标签宽度
			if(xt_Generator.getXt_generator_page_labelWidth()>=0){
				sb.append("\t\t\tlabelWidth:"+xt_Generator.getXt_generator_page_labelWidth()+",\r\n");
			}else{
				sb.append("\t\t\tlabelWidth:70,\r\n");
			}
			//表单标签对齐方式1向左2向右3向上
			if(xt_Generator.getXt_generator_page_labelAlign().equals("1")){
				sb.append("\t\t\tlabelAlign:'left',\r\n"); 
			}else if(xt_Generator.getXt_generator_page_labelAlign().equals("2")){
				sb.append("\t\t\tlabelAlign:'right',\r\n");
			}else if(xt_Generator.getXt_generator_page_labelAlign().equals("3")){
				sb.append("\t\t\tlabelAlign:'top',\r\n");
			}else{
				sb.append("\t\t\tlabelAlign:'left',\r\n"); 
			} 
			sb.append("\t\t\tflex:1,\r\n"); 
			sb.append("\t\t\treadOnly:true,\r\n"); 
			sb.append("\t\t\tmargin:'2 5 4 5'\r\n"); 
			sb.append("\t\t},\r\n"); 
			//创建列字段 开始
			sb.append("\t\titems:[\r\n");
			List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
				if(null != column_comment && !"".equals(column_comment)){
					column_comment = column_comment.replaceAll("amp;", "");
				}
				if(i == (xt_Generator_Table_Column_FormList.size()-1)){
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptService.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}else{
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}
				}else{
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptService.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						sb.append("\t\t},\r\n");
					}else{
						sb.append("\t\t},\r\n");
					}
				}
			}
			////////////////////子表Grid配置
			if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
				List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList= xt_Generator.getXt_Generator_TableMany_To_OneList();
				//遍历子表GridJson值元素
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
					sb.append("\t\t\txtype:'textfield',\r\n");
					sb.append("\t\t\thidden:true,\r\n");
					sb.append("\t\t\tid:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List',\r\n");
					sb.append("\t\t\tname:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List',\r\n");
					sb.append("\t\t},\r\n");
				}
				
				//遍历子表Grid元素
				for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
					XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
					if(i == (xt_Generator_TableMany_To_OneList.size()-1)){
						sb.append("\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid\r\n");
					}else{
						sb.append("\t\t"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid,\r\n");
					}
				}
			}
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
			//////////////////////生成Form模块结束/////////////////////////////
		}
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()&&xt_Generator.getOne_to_many_type().equals("1")){
			
		}else{
			sb.append("}\r\n");
		}
		
		return sb.toString();
	}
	/////////////////////////////////////4.生成明细Detail结束//////////////////////////////////
	/**
	 * 创建查询操作
	 */
	private String createPageSearch(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String list_url = "get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition";
		StringBuffer sb = new StringBuffer();
		sb.append("/**查询操作**/\r\n");
		sb.append("function search(){\r\n");
		sb.append("\tinitSearch(store,'../"+root_url+"/"+list_url+"',searchForm);\r\n");
		/**不在采用该方法
		List<Xt_Generator_Search_Filed> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
			Xt_Generator_Search_Filed xt_generator_search_filed = xt_generator_search_filedList.get(i);
			sb.append("\tvar "+xt_generator_search_filed.getXt_generator_search_name()+" = Ext.getCmp(\""+xt_generator_search_filed.getXt_generator_search_name()+"\").getValue();\r\n");
		}
		sb.append("\tstore.load({\r\n");
		sb.append("\t\turl:'../"+root_url+"/"+list_url+"',\r\n");
		sb.append("\t\tparams:{\r\n");
		sb.append("\t\t\tstart:0,\r\n");
		if(!xt_generator_search_filedList.isEmpty() && xt_generator_search_filedList.size()>0){
			sb.append("\t\t\tlimit:getGridBBar(store).pageSize,\r\n");
		}else{
			sb.append("\t\t\tlimit:getGridBBar(store).pageSize\r\n");
		}
		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
			Xt_Generator_Search_Filed xt_generator_search_filed = xt_generator_search_filedList.get(i);
			if(xt_generator_search_filedList.size() == (i+1)){
				sb.append("\t\t\t"+xt_generator_search_filed.getXt_generator_search_name()+":"+xt_generator_search_filed.getXt_generator_search_name()+"\r\n");
			}else{
				sb.append("\t\t\t"+xt_generator_search_filed.getXt_generator_search_name()+":"+xt_generator_search_filed.getXt_generator_search_name()+",\r\n");
			}
		}
		sb.append("\t\t}\r\n");
		sb.append("\t});\r\n");
		**/
		sb.append("}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建附件回显（Extjs风格）
	 * @param xt_Generator
	 * @return
	 */
	public String createAttachmentObject(XtGenerator xt_Generator){
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			StringBuffer sb = new StringBuffer();
			//回显附件使用
			sb.append("\t/**配置附件回显方法开始**/\r\n");
			sb.append("\tvar params = {");
			String kv ="";
			String filed_name="";
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
					if(null != kv && !"".equals(kv)){
						kv = kv+"+','+"+"record.items[0].data."+xt_Generator_Table_Column_Form.getColumn_name();
						filed_name = filed_name+","+xt_Generator_Table_Column_Form.getColumn_name();
					}else{
						kv = "record.items[0].data."+xt_Generator_Table_Column_Form.getColumn_name();
						filed_name = xt_Generator_Table_Column_Form.getColumn_name();
					}
				}
			}
			sb.append("xt_attachment_id:"+kv+","+"field_name:'"+filed_name+"'");
			sb.append("};\r\n");
			sb.append("\tajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);\r\n");
			sb.append("\t/**配置附件回显方法结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 创建附件回显（Bootstrap风格）
	 * @param xt_Generator
	 * @return
	 */
	public static String createBAttachmentObject(XtGenerator xt_Generator){
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			StringBuffer sb = new StringBuffer();
			//回显附件使用
			sb.append("/**配置附件回显方法开始**/\r\n");
			sb.append("var params = {");
			String kv ="";
			String filed_name="";
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
					if(null != kv && !"".equals(kv)){
						kv = kv+"+','+"+"$('#"+xt_Generator_Table_Column_Form.getColumn_name()+"').val();";
						filed_name = filed_name+","+xt_Generator_Table_Column_Form.getColumn_name();
					}else{
						kv = "$('#"+xt_Generator_Table_Column_Form.getColumn_name()+"').val()";
						filed_name = xt_Generator_Table_Column_Form.getColumn_name();
					}
				}
			}
			sb.append("xt_attachment_id:"+kv+","+"field_name:'"+filed_name+"'");
			sb.append("};\r\n");
			sb.append("ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);\r\n");
			sb.append("/**配置附件回显方法结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 创建附件右键（Extjs风格）
	 * @param xt_Generator
	 * isUpAndDelete 1表示拥有上传和删除功能 即新增编辑页面使用2表示不拥有上传和删除功能 即明细页面使用
	 * @return
	 */
	public static String createAttachmentRight(XtGenerator xt_Generator,int isUpAndDelete){
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			//回显附件使用
			if(isUpAndDelete == 2){
				sb.append("/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/\r\n");
			}else if(isUpAndDelete == 1){
				sb.append("/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/\r\n");
			}
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
					if(isUpAndDelete == 2){
						sb.append("\tinitFileRight('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1,2);\r\n");
					}else if(isUpAndDelete == 1){
						sb.append("\tinitFileRight('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1,1);\r\n");
					}
				}
			}
			sb.append("\t/**初始化附件右键菜单结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 创建附件右键（Bootstrap风格）
	 * @param xt_Generator
	 * isUpAndDelete 1表示拥有上传和删除功能 即新增编辑页面使用2表示不拥有上传和删除功能 即明细页面使用
	 * @return
	 */
	public static String createAttachmentBRight(XtGenerator xt_Generator,int isUpAndDelete){
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			//回显附件使用
			if(isUpAndDelete == 2){
				sb.append("/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/\r\n");
			}else if(isUpAndDelete == 1){
				sb.append("/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/\r\n");
			}
			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
				if(null != xt_Generator_Table_Column_Form.getColumn_type() && !"".equals(xt_Generator_Table_Column_Form.getColumn_type()) && "5".equals(xt_Generator_Table_Column_Form.getColumn_type())){
					if(isUpAndDelete == 2){
						sb.append("initBFileRight('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',2);\r\n");
					}else if(isUpAndDelete == 1){
						sb.append("initBFileRight('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1);\r\n");
					}
				}
			}
			sb.append("/**初始化附件右键菜单结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
	
	/////////////////////////////以下一对多操作//////////////////////////////
	/**
	 * 一对多生成（列表方式）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToMany(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		//1定义grid、store
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			String root_url = lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Controller";
			String list_url = "get"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"ListByCondition";
			String grid = xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Grid";
			String store = xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"Store";
			sb.append("var "+grid+";\r\n");
			sb.append("var "+store+";\r\n");
			sb.append("/**初始化子表**/\r\n");
			sb.append("function init"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Grid("+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey()+"){\r\n");
			/////////////////////2生成主体开始
			sb.append("\t"+store+" = getGridJsonStore('../"+root_url+"/"+list_url+"?"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey()+" =' + "+getColumnKey(xt_Generator_Table_ColumnList)+",[]);\r\n");
			sb.append("\t"+grid+" = Ext.create('Ext.grid.Panel',{\r\n");
			sb.append("\t\tstore:"+store+"\r\n");
			sb.append("\t\trequires:[\r\n");
			sb.append("\t\t\t'Ext.grid.plugin.CellEditing',\r\n");
			sb.append("\t\t\t'Ext.form.field.Text',\r\n");
			sb.append("\t\t\t'Ext.form.field.Text',\r\n");
			sb.append("\t\t\t'Ext.form.field.TextArea',\r\n");
			sb.append("\t\t\t'Ext.toolbar.TextItem'\r\n");
			sb.append("\t\t],\r\n");
			sb.append("\t\tcolumnLines:true,\r\n");
			sb.append("\t\tmultiSelect:true,\r\n");
			sb.append("\t\tborder:true,\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\tregion:'center',\r\n");
			sb.append("\t\ttitle:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
			sb.append("\t\tplugins:{\r\n");
			sb.append("\t\t\tptype:'cellediting',\r\n");
			sb.append("\t\t\tclicksToEdit:1\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\tviewConfig:{\r\n");
			sb.append("\t\t\temptyText:'暂无数据',\r\n");
			sb.append("\t\t\tstripeRows:true\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\tloadMask:{\r\n");
			sb.append("\t\t\tmsg:'正在加载...'\r\n");
			sb.append("\t\t},\r\n");
			/////////////2-1遍历字段开始
			sb.append("\t\tcolumns:[\r\n");
			sb.append("\t\t\t{\r\n");
			sb.append("\t\t\t\theader:'序号',\r\n");
			sb.append("\t\t\t\twidth:77,\r\n");
			sb.append("\t\t\t\txtype:'rownumberer'\r\n");
			sb.append("\t\t\t},\r\n");	
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
			for(int j = 0; j < xt_Generator_Table_ColumnMany_To_OneList.size(); j++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(j);
				if(j == (xt_Generator_Table_ColumnMany_To_OneList.size()-1)){
					sb.append("\t\t\t{\r\n");
					sb.append("\t\t\t\theader:'"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT()+"',\r\n");
					sb.append("\t\t\t\tdataIndex:'"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
					sb.append("\t\t\t\tflex:1,\r\n");
					sb.append("\t\t\t\teditor:{\r\n");
					sb.append("\t\t\t\t\txtype:'textfield'\r\n");
					sb.append("\t\t\t\t}\r\n");
					sb.append("\t\t\t}\r\n");
				}else{
					sb.append("\t\t\t{\r\n");
					sb.append("\t\t\t\theader:'"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT()+"',\r\n");
					sb.append("\t\t\t\tdataIndex:'"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
					sb.append("\t\t\t\teditor:{\r\n");
					sb.append("\t\t\t\t\txtype:'textfield'\r\n");
					sb.append("\t\t\t\t}\r\n");
					sb.append("\t\t\t},\r\n");
				}
			}
			sb.append("\t\t],\r\n");
			/////////////2-1遍历字段结束
			sb.append("\t\tlisteners:{\r\n");
			sb.append("\t\t\tselectionChange:'selectionChange'\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\t//选中的记录发生变化过后的事件\r\n");
			sb.append("\t\tselectionChange:function(view, records){\r\n");
			sb.append("\t\t\t"+grid+".down('#del_button').setDisabled(!records.length);\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\tbbar:getGridBBar("+store+"),\r\n");
			sb.append("\t\ttbar:[{\r\n");
			sb.append("\t\t\ttext:'增 加',\r\n");
			sb.append("\t\t\ticon:addIcon,\r\n");
			sb.append("\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\tvar model = Ext.create("+grid+".getStore().model);\r\n");
			sb.append("\t\t\t\t"+grid+".getStore().insert(0, model);\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\t{\r\n");
			sb.append("\t\t\ttext:'删 除',\r\n");
			sb.append("\t\t\ticon:delIcon,\r\n");
			sb.append("\t\t\tid:'del_button',\r\n");
			sb.append("\t\t\thandler:function(){\r\n");
			sb.append("\t\t\t\tvar model = "+grid+".getSelectionModel();\r\n");
			sb.append("\t\t\t\tif(model.selected.length == 0){\r\n");
			sb.append("\t\t\t\t\tmsgTishi(\"请选择要删除的项\");\r\n");
			sb.append("\t\t\t\t\treturn;\r\n");
			sb.append("\t\t\t\t}\r\n");
			sb.append("\t\t\t\tExt.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn){\r\n");
			sb.append("\t\t\t\t\tif(btn == 'yes'){\r\n");
			sb.append("\t\t\t\t\t\t"+grid+".getStore().remove("+grid+".getSelectionModel().getSelection());\r\n");
			/**
			 * 不使用该方法
			sb.append("\t\t\t\t\t\t"+grid+".getStore().sync();\r\n");
			**/
			sb.append("\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t});\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}]\r\n");
			sb.append("\t})\r\n");
			sb.append("\t"+store+".load();\r\n");
			/////////////////////2生成主体结束		
			sb.append("}\r\n");
			
			String path = xt_Generator.getXt_generator_path();
			OutputStreamWriter out = null;
			try {
				out = new OutputStreamWriter(new FileOutputStream(path+lowAllChar_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + "-list.js"),"UTF-8");
				try {
					out.write(sb.toString());
				} catch (IOException e) {
					logger.error(e.getMessage());
					throw new ExceptionUtil(e.getMessage(),e.getCause());
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}finally{
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
					throw new ExceptionUtil(e.getMessage(),e.getCause());
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 一对多生成（表单方式——添加-基本信息）
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingAddBaseForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		//////////////////////生成Form模块开始/////////////////////////////
		///////创建基本信息FieldSet信息
		sb.append("function init"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormAdd(){\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet = Ext.create('Ext.form.FieldSet',{\r\n");
		//是否支持滚动条
		if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
			sb.append("\t\tautoScroll:true,\r\n");
		}
		//禁用X轴滚动条
		sb.append("\t\ttitle:'基本信息',\r\n");
		sb.append("\t\tscrollable:true,\r\n");
		sb.append("\t\tscrollable:'x',\r\n");
		sb.append("\t\tscrollable:'y',\r\n");
		sb.append("\t\titems:[\r\n");
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
			if(null != column_comment && !"".equals(column_comment)){
				column_comment = column_comment.replaceAll("amp;", "");
			}
			if(!getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
				if(i == (xt_Generator_Table_ColumnList.size()-1)){
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\tanchor:'80%',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
						sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
						sb.append("\t\t\t\t\t**/\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}else{
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}
				}else{
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\tanchor:'80%',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
						sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
						sb.append("\t\t\t\t\t**/\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						sb.append("\t\t},\r\n");
					}else{
						sb.append("\t\t},\r\n");
					}
				}
			}
		}
		/////////追加标识符新增和删除
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			//////////新增标记
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'numberfield',\r\n");
			sb.append("\t\t\thidden:true,\r\n");
			sb.append("\t\t\tvalue:'0',\r\n");
			sb.append("\t\t\titemId:'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber'\r\n");
			sb.append("\t\t},\r\n");
			//////////删除标记
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'textfield',\r\n");
			sb.append("\t\t\thidden:true,\r\n");
			sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',\r\n");
			sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag'\r\n");
			if((xt_Generator_TableMany_To_OneList.size()-1) == (i)){
				sb.append("\t\t}\r\n");
			}else{
				sb.append("\t\t},\r\n");
			}
		}
		
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		//添加子表fileset按钮
		sb.append(createOneToManyUsingAddFieldSetForm(xt_Generator_Table_ColumnList, xt_Generator));
		//////////////////////生成FormFieldSet模块结束/////////////////////////////
		//////////////////////基本form开始////////////////////////////////
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAdd = Ext.create('Ext.FormPanel',{\r\n");
		sb.append("\t\txtype:'form',\r\n");
		sb.append("\t\twaitMsgTarget:true,\r\n");
		sb.append("\t\tdefaultType:'textfield',\r\n");
		//是否支持滚动条
		if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
			sb.append("\t\tautoScroll:true,\r\n");
		}
		//禁用X轴滚动条
		sb.append("\t\t/**新方法使用开始**/\r\n");
		sb.append("\t\tscrollable:true,\r\n");
		sb.append("\t\tscrollable:'x',\r\n");
		sb.append("\t\tscrollable:'y',\r\n");
		sb.append("\t\t/**新方法使用结束**/\r\n");
		sb.append("\t\tfieldDefaults:{\r\n");
		//默认标签宽度
		if(xt_Generator.getXt_generator_page_labelWidth()>=0){
			sb.append("\t\t\tlabelWidth:"+xt_Generator.getXt_generator_page_labelWidth()+",\r\n");
		}else{
			sb.append("\t\t\tlabelWidth:70,\r\n");
		}
		//默认表单标签对齐方式1向左2向右3向上
		if(xt_Generator.getXt_generator_page_labelAlign().equals("1")){
			sb.append("\t\t\tlabelAlign:'left',\r\n"); 
		}else if(xt_Generator.getXt_generator_page_labelAlign().equals("2")){
			sb.append("\t\t\tlabelAlign:'right',\r\n");
		}else if(xt_Generator.getXt_generator_page_labelAlign().equals("3")){
			sb.append("\t\t\tlabelAlign:'top',\r\n");
		}else{
			sb.append("\t\t\tlabelAlign:'left',\r\n"); 
		}
		sb.append("\t\t\tflex:1,\r\n"); 
		sb.append("\t\t\tmargin:'2 5 4 5'\r\n"); 
		sb.append("\t\t},\r\n"); 
		sb.append("\t\titems:[\r\n");
		////////////////////追加基本FormFieldSet
		sb.append("\t\t\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet,\r\n");
		////////////////////追加子表Form///////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One= xt_Generator_TableMany_To_OneList.get(i);
				if((xt_Generator_TableMany_To_OneList.size()-1) == i){
					sb.append("\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet\r\n");
				}else{
					sb.append("\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet,\r\n");
				}
			}
		}
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		sb.append("}\r\n");
		//////////////////////生成Form模块结束/////////////////////////////
		sb.append(createOneToManyUsingAddForm(xt_Generator_Table_ColumnList, xt_Generator));
		return sb.toString();
	}
	/**
	 * 一对多生成（表单方式——添加子表sub）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingAddForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			sb.append("function add"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAdd(){\r\n");
			sb.append("\tvar numbers = gfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet,'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber');\r\n");
			sb.append("\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet,'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber',numbers+1);\r\n");
			sb.append("\t\r\n");
			//此处需要判断是否为top如果为top则需要添加top标识
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAdd = Ext.create('Ext.FormPanel',{\r\n");
			sb.append("\t\txtype:'form',\r\n");
			sb.append("\t\twaitMsgTarget:true,\r\n");
			sb.append("\t\tdefaultType:'textfield',\r\n");
			sb.append("\t\tautoScroll:true,\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\tfieldDefaults:{\r\n");
			sb.append("\t\t\tlabelWidth:70,\r\n");
			sb.append("\t\t\tlabelAlign:'left',\r\n");
			sb.append("\t\t\tflex:1,\r\n");
			sb.append("\t\t\tmargin:'2 5 4 5'\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\titems:[\r\n");
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
			for(int j = 0;j < xt_Generator_Table_ColumnMany_To_OneList.size();j++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(j);
				String column_comment=xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT();
				if(null != column_comment && !"".equals(column_comment)){
					column_comment = column_comment.replaceAll("amp;", "");
				}
				if(!getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
					if(j == (xt_Generator_Table_ColumnMany_To_OneList.size()-1)){
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							if(("0").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						///////////此处过滤主键为非必填
						if(!(getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).toLowerCase()).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toLowerCase())){
							if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
								sb.append("\t\t\tallowBlank:false,\r\n");
								sb.append("\t\t\thidden:true,\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH()+",\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_label_position() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position())){
							if(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getIsHidden() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden()) && "1".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						//过滤外键作为并将其隐藏
						if(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey().toUpperCase().equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toUpperCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\tanchor:'80%',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
//							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
//							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1)\",\r\n");
//							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t},\r\n");
						}
					}else{
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							if(("0").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						///////////此处过滤主键为非必填
						if(!(getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).toLowerCase()).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toLowerCase())){
							if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
								sb.append("\t\t\tallowBlank:false,\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH()+",\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_label_position() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position())){
							if(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getIsHidden() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden()) && "1".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						///////////此处过滤主键为隐藏
						if((getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).toLowerCase()).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toLowerCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						//过滤外键作为并将其隐藏
						if(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey().toUpperCase().equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toUpperCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\tanchor:'80%',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
//							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
//							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1)\",\r\n");
//							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t},\r\n");
						}
					}
				}
			}
			//添加删除一行记录按钮
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'button',\r\n");
			sb.append("\t\t\ttext:'删除',\r\n");
			sb.append("\t\t\tcls:'delBtn',\r\n");
			sb.append("\t\t\ticon:delIcon,\r\n");
			sb.append("\t\t\tstyle:'background:#368ECE;border-color:#126DAF;float:right;margin-bottom:10px',\r\n");
			sb.append("\t\t\tlabelAlign:'right',\r\n");
			sb.append("\t\t\thandler:function (){\r\n");
			sb.append("\t\t\t\tvar _panel = this.ownerCt;\r\n");
			sb.append("\t\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet.remove(_panel); \r\n");
			sb.append("\t\t\t\tvar "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag = gfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag');\r\n");
			sb.append("\t\t\t\tif(null == "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag || "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag == ''){\r\n");
			sb.append("\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',\",\"+numbers+\",\");\r\n");
			sb.append("\t\t\t\t}else{\r\n");
			sb.append("\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormAddFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag+numbers+',');\r\n");
			sb.append("\t\t\t\t}\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\t\t\t\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet,true);\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}\r\n");
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
			//////添加布局
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet.add("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet.items.getCount(),"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAdd);\r\n");
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet.afterLayout();\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet,true);\r\n");
			//初始化附件右键
			sb.append("\t"+createAttachmentOneToManyRight(xt_Generator_TableMany_To_One, 1)+"\r\n");
			sb.append("}\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 一对多生成（表单方式——添加-----FieldSet）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingAddFieldSetForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			//如果是最外层则需要加top
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAddFieldSet = Ext.create('Ext.form.FieldSet',{\r\n");
			sb.append("\t\tanchor:'100%',\r\n");
			sb.append("\t\ttitle:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
			sb.append("\t\titems:[{	\r\n");
			sb.append("\t\t\txtype:'button',\r\n");
			sb.append("\t\t\ttext:'添加"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
			sb.append("\t\t\tstyle:'float:left;margin-bottom:10px',\r\n");
			sb.append("\t\t\tlabelAlign:\"right\", \r\n");
			sb.append("\t\t\tlisteners:{\r\n");
			sb.append("\t\t\t\tclick:function(){\r\n");
			sb.append("\t\t\t\t\tadd"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormAdd();\r\n");
			sb.append("\t\t\t\t}\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}\r\n");
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
		}
		return sb.toString();
	}
	
	///////////////////////////////一对多生成（表单方式-修改-基本信息）
	
	/**
	 * 一对多生成（表单方式——修改-基本信息）
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingUpdateBaseForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		//////////////////////生成Form模块开始/////////////////////////////
		///////创建基本信息FieldSet信息
		sb.append("function init"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormEdit(){\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet = Ext.create('Ext.form.FieldSet',{\r\n");
		//是否支持滚动条
		if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
			sb.append("\t\tautoScroll:true,\r\n");
		}
		//禁用X轴滚动条
		sb.append("\t\ttitle:'基本信息',\r\n");
		sb.append("\t\tscrollable:true,\r\n");
		sb.append("\t\tscrollable:'x',\r\n");
		sb.append("\t\tscrollable:'y',\r\n");
		sb.append("\t\titems:[\r\n");
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
			if(null != column_comment && !"".equals(column_comment)){
				column_comment = column_comment.replaceAll("amp;", "");
			}
			if(i == (xt_Generator_Table_ColumnList.size()-1)){
				sb.append("\t\t{\r\n");
				sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
				if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文本框
						sb.append("\t\t\txtype:'textfield',\r\n");
					}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文本域
						sb.append("\t\t\txtype:'textareafield',\r\n");
					}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
		    			String xt_script_key = "";
		    			String xt_script_valuefield="";
		    			String xt_script_displayfield="";
		    			if(null != xt_script_id && !"".equals(xt_script_id)){
		    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
		        			xt_script_key = xtScript.getXt_script_key();
		        			xt_script_valuefield = xtScript.getXt_script_valuefield();
		        			xt_script_displayfield = xtScript.getXt_script_displayfield();
		    			}
						//下拉框
						sb.append("\t\t\txtype:'combo',\r\n");
						sb.append("\t\t\temptyText:'请选择',\r\n");
        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
        				sb.append("\t\t\tmode:'local',\r\n");
        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
        	            sb.append("\t\t\teditable:false,\r\n");
        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
					}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文件框
						//1添加隐含域即附件编号
						sb.append("\t\t\txtype:'textfield',\r\n");
						sb.append("\t\t\thidden:true,\r\n");
						sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					}else{
						//文本框
						sb.append("\t\t\txtype:'textfield',\r\n");
					}
				}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'datefield',\r\n");
					sb.append("\t\t\tformat:'Y-m-d',\r\n");
				}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'datetimefield',\r\n");
					sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
				}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'timefield',\r\n");
				}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'numberfield',\r\n");
					//设置默认值
					sb.append("\t\t\tvalue:'0',\r\n");
				}
				if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
					sb.append("\t\t\thidden:true,\r\n");
				}
				sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
				if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
					sb.append("\t\t\tallowBlank:false,\r\n");
				}
				if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
					sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
				}
				if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
					if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
						sb.append("\t\t\tlabelAlign:'top',\r\n");
					}
				}
				if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
					sb.append("\t\t\thidden:true,\r\n");
				}
				sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
				//如果是文件框 则添加文件框按钮
				if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
					sb.append("\t\t},\r\n");
					//文件框
					//2添加文件框上传按钮
					sb.append("\t\t{\r\n");
					sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
					sb.append("\t\t\tanchor:'80%',\r\n");
					sb.append("\t\t\txtype:'fieldset',\r\n");
					sb.append("\t\t\titems:{\r\n");
					sb.append("\t\t\t\txtype:'box', \r\n");
					sb.append("\t\t\t\twidth:80, \r\n");
					sb.append("\t\t\t\theight:60, \r\n");
					sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
					sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
					sb.append("\t\t\t\tautoEl:{\r\n");
					sb.append("\t\t\t\t\ttag:'img',\r\n");
					sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
					sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
					sb.append("\t\t\t\t\t**/\r\n");
					sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
					sb.append("\t\t\t\t}\r\n");
					sb.append("\t\t\t}\r\n");
					//子表操作 逗号封闭
					if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
						sb.append("\t\t},\r\n");
					}else{
						sb.append("\t\t}\r\n");
					}
				}else{
					//子表操作 逗号封闭
					if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
						sb.append("\t\t},\r\n");
					}else{
						sb.append("\t\t}\r\n");
					}
				}
			}else{
				sb.append("\t\t{\r\n");
				sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
				if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文本框
						sb.append("\t\t\txtype:'textfield',\r\n");
					}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文本域
						sb.append("\t\t\txtype:'textareafield',\r\n");
					}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
		    			String xt_script_key = "";
		    			String xt_script_valuefield="";
		    			String xt_script_displayfield="";
		    			if(null != xt_script_id && !"".equals(xt_script_id)){
		    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
		        			xt_script_key = xtScript.getXt_script_key();
		        			xt_script_valuefield = xtScript.getXt_script_valuefield();
		        			xt_script_displayfield = xtScript.getXt_script_displayfield();
		    			}
						//下拉框
						sb.append("\t\t\txtype:'combo',\r\n");
						sb.append("\t\t\temptyText:'请选择',\r\n");
        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
        				sb.append("\t\t\tmode:'local',\r\n");
        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
        	            sb.append("\t\t\teditable:false,\r\n");
        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
					}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文件框
						//1添加隐含域即附件编号
						sb.append("\t\t\txtype:'textfield',\r\n");
						sb.append("\t\t\thidden:true,\r\n");
						sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					}else{
						//文本框
						sb.append("\t\t\txtype:'textfield',\r\n");
					}
				}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'datefield',\r\n");
					sb.append("\t\t\tformat:'Y-m-d',\r\n");
				}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'datetimefield',\r\n");
					sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
				}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'timefield',\r\n");
				}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\txtype:'numberfield',\r\n");
					//设置默认值
					sb.append("\t\t\tvalue:'0',\r\n");
				}
				if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
					sb.append("\t\t\thidden:true,\r\n");
				}
				sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
				if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
					sb.append("\t\t\tallowBlank:false,\r\n");
				}
				if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
					sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
				}
				if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
					if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
						sb.append("\t\t\tlabelAlign:'top',\r\n");
					}
				}
				if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
					sb.append("\t\t\thidden:true,\r\n");
				}
				sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
				//如果是文件框 则添加文件框按钮
				if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
					sb.append("\t\t},\r\n");
					//文件框
					//2添加文件框上传按钮
					sb.append("\t\t{\r\n");
					sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
					sb.append("\t\t\tanchor:'80%',\r\n");
					sb.append("\t\t\txtype:'fieldset',\r\n");
					sb.append("\t\t\titems:{\r\n");
					sb.append("\t\t\t\txtype:'box', \r\n");
					sb.append("\t\t\t\twidth:80, \r\n");
					sb.append("\t\t\t\theight:60, \r\n");
					sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
					sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
					sb.append("\t\t\t\tautoEl:{\r\n");
					sb.append("\t\t\t\t\ttag:'img',\r\n");
					sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
					sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
					sb.append("\t\t\t\t\t**/\r\n");
					sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
					sb.append("\t\t\t\t}\r\n");
					sb.append("\t\t\t}\r\n");
					sb.append("\t\t},\r\n");
				}else{
					sb.append("\t\t},\r\n");
				}
			}
		}
		/////////追加标识符新增和删除
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			//////////新增标记
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'numberfield',\r\n");
			sb.append("\t\t\thidden:true,\r\n");
			sb.append("\t\t\tvalue:'0',\r\n");
			sb.append("\t\t\titemId:'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber'\r\n");
			sb.append("\t\t},\r\n");
			//////////删除标记
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'textfield',\r\n");
			sb.append("\t\t\thidden:true,\r\n");
			sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',\r\n");
			sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag'\r\n");
			if((xt_Generator_TableMany_To_OneList.size()-1) == (i)){
				sb.append("\t\t}\r\n");
			}else{
				sb.append("\t\t},\r\n");
			}
		}
		
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		//添加子表fileset按钮
		sb.append(createOneToManyUsingUpdateFieldSetForm(xt_Generator_Table_ColumnList, xt_Generator));
		//////////////////////生成FormFieldSet模块结束/////////////////////////////
		//////////////////////基本form开始////////////////////////////////
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEdit = Ext.create('Ext.FormPanel',{\r\n");
		sb.append("\t\txtype:'form',\r\n");
		sb.append("\t\twaitMsgTarget:true,\r\n");
		sb.append("\t\tdefaultType:'textfield',\r\n");
		//是否支持滚动条
		if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
			sb.append("\t\tautoScroll:true,\r\n");
		}
		//禁用X轴滚动条
		sb.append("\t\t/**新方法使用开始**/\r\n");
		sb.append("\t\tscrollable:true,\r\n");
		sb.append("\t\tscrollable:'x',\r\n");
		sb.append("\t\tscrollable:'y',\r\n");
		sb.append("\t\t/**新方法使用结束**/\r\n");
		sb.append("\t\tfieldDefaults:{\r\n");
		//默认标签宽度
		if(xt_Generator.getXt_generator_page_labelWidth()>=0){
			sb.append("\t\t\tlabelWidth:"+xt_Generator.getXt_generator_page_labelWidth()+",\r\n");
		}else{
			sb.append("\t\t\tlabelWidth:70,\r\n");
		}
		//默认表单标签对齐方式1向左2向右3向上
		if(xt_Generator.getXt_generator_page_labelAlign().equals("1")){
			sb.append("\t\t\tlabelAlign:'left',\r\n"); 
		}else if(xt_Generator.getXt_generator_page_labelAlign().equals("2")){
			sb.append("\t\t\tlabelAlign:'right',\r\n");
		}else if(xt_Generator.getXt_generator_page_labelAlign().equals("3")){
			sb.append("\t\t\tlabelAlign:'top',\r\n");
		}else{
			sb.append("\t\t\tlabelAlign:'left',\r\n"); 
		}
		sb.append("\t\t\tflex:1,\r\n"); 
		sb.append("\t\t\tmargin:'2 5 4 5'\r\n"); 
		sb.append("\t\t},\r\n"); 
		sb.append("\t\titems:[\r\n");
		////////////////////追加基本FormFieldSet
		sb.append("\t\t\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,\r\n");
		////////////////////追加子表Form///////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One= xt_Generator_TableMany_To_OneList.get(i);
				if((xt_Generator_TableMany_To_OneList.size()-1) == i){
					sb.append("\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet\r\n");
				}else{
					sb.append("\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet,\r\n");
				}
			}
		}
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		sb.append("}\r\n");
		//////////////////////生成Form模块结束/////////////////////////////
		//追加回调方法
		sb.append(createOneToManyUsingUpdateFormCallFn(xt_Generator_Table_ColumnList, xt_Generator));
		//追加创建子表form方法
		sb.append(createOneToManyUsingEditForm(xt_Generator_Table_ColumnList, xt_Generator));
		return sb.toString();
	}
	/**
	 * 一对多生成（表单方式——修改）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingEditForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			sb.append("function addUpdate"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit(data){\r\n");
			//定义删除子表URL
			String root_url = lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Controller";
			String del_url = "del"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());
			del_url="../"+root_url+"/"+del_url;
			sb.append("\tvar numbers = gfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber');\r\n");
			sb.append("\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber',numbers+1);\r\n");
			sb.append("\t\r\n");
			//此处需要判断是否为top如果为top则需要添加top标识
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit = Ext.create('Ext.FormPanel',{\r\n");
			sb.append("\t\txtype:'form',\r\n");
			sb.append("\t\twaitMsgTarget:true,\r\n");
			sb.append("\t\tdefaultType:'textfield',\r\n");
			sb.append("\t\tautoScroll:true,\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\tfieldDefaults:{\r\n");
			sb.append("\t\t\tlabelWidth:70,\r\n");
			sb.append("\t\t\tlabelAlign:'left',\r\n");
			sb.append("\t\t\tflex:1,\r\n");
			sb.append("\t\t\tmargin:'2 5 4 5'\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\titems:[\r\n");
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
			for(int j = 0;j < xt_Generator_Table_ColumnMany_To_OneList.size();j++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(j);
				String column_comment=xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT();
				if(null != column_comment && !"".equals(column_comment)){
					column_comment = column_comment.replaceAll("amp;", "");
				}
				if(!getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
					if(j == (xt_Generator_Table_ColumnMany_To_OneList.size()-1)){
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							if(("0").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						
						//////////判断是否主键如果子表中该字段是主键则隐藏
						if(getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						//过滤外键作为并将其隐藏
						if(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey().toUpperCase().equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toUpperCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						///////////此处过滤主键为非必填
						if(!(getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).toLowerCase()).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toLowerCase())){
							if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
								sb.append("\t\t\tallowBlank:false,\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH()+",\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_label_position() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position())){
							if(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getIsHidden() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden()) && "1".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\tanchor:'80%',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
//							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
//							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1)\",\r\n");
//							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t},\r\n");
						}
					}else{
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							if(("0").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						//////////判断是否主键如果子表中该字段是主键则隐藏
						if(getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						//过滤外键作为并将其隐藏
						if(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey().toUpperCase().equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toUpperCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						///////////此处过滤主键为非必填
						if(!(getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).toLowerCase()).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toLowerCase())){
							if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
								sb.append("\t\t\tallowBlank:false,\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH()+",\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_label_position() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position())){
							if(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getIsHidden() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden()) && "1".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\tanchor:'80%',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
//							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
//							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1)\",\r\n");
//							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t},\r\n");
						}
					}
				}
			}
			//添加删除一行记录按钮
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'button',\r\n");
			sb.append("\t\t\ttext:'删除',\r\n");
			sb.append("\t\t\tcls:'delBtn',\r\n");
			sb.append("\t\t\ticon:delIcon,\r\n");
			sb.append("\t\t\tstyle:'background:#368ECE;border-color:#126DAF;float:right;margin-bottom:10px',\r\n");
			sb.append("\t\t\tlabelAlign:'right',\r\n");
			sb.append("\t\t\thandler:function (){\r\n");
			sb.append("\t\t\t\tvar _panel = this.ownerCt;\r\n");
			sb.append("\t\t\t\tvar "+getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList)+" = gfiValue(_panel,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList)+"');\r\n");
			sb.append("\t\t\t\tif(null != "+getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList)+" && '' != "+getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList)+"){\r\n");
			sb.append("\t\t\t\t\tExt.Msg.confirm('提示','确定删除该行数据？',function(btn){\r\n");
			sb.append("\t\t\t\t\t\tif(btn == 'yes'){\r\n");
			sb.append("\t\t\t\t\t\t\tvar params = {"+getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList)+":"+getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList)+"};\r\n");
			sb.append("\t\t\t\t\t\t\tajaxRequestCallFn('"+del_url+"',null,params,'正在执行删除操作中！请稍后...',function(response, opts){\r\n");
			sb.append("\t\t\t\t\t\t\t\tvar obj=Ext.decode(response.responseText);\r\n");
			sb.append("\t\t\t\t\t\t\t\tif(obj.responseFlag){\r\n");
			sb.append("\t\t\t\t\t\t\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet.remove(_panel);\r\n");
			sb.append("\t\t\t\t\t\t\t\t\tvar "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag = gfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"EditFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag');\r\n");
			sb.append("\t\t\t\t\t\t\t\t\tif(null == "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag || "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag == ''){\r\n");
			sb.append("\t\t\t\t\t\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',\",\"+numbers+\",\");\r\n");
			sb.append("\t\t\t\t\t\t\t\t\t}else{\r\n");
			sb.append("\t\t\t\t\t\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag+numbers+',');\r\n");
			sb.append("\t\t\t\t\t\t\t\t\t}\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\t\t\t\t\t\t\t\t\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet,true);\r\n");
			sb.append("\t\t\t\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t\t\t\t});\r\n");
			sb.append("\t\t\t\t\t\t}\r\n");
			sb.append("\t\t\t\t\t});\r\n");
			sb.append("\t\t\t\t}else{\r\n");
			sb.append("\t\t\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet.remove(_panel);\r\n");
			sb.append("\t\t\t\t\tvar "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag = gfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag');\r\n");
			sb.append("\t\t\t\t\tif(null == "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag || "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag == ''){\r\n");
			sb.append("\t\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',\",\"+numbers+\",\");\r\n");
			sb.append("\t\t\t\t\t}else{\r\n");
			sb.append("\t\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormEditFieldSet,'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag+numbers+',');\r\n");
			sb.append("\t\t\t\t\t}\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\t\t\t\t\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet,true);\r\n");
			sb.append("\t\t\t\t}\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}\r\n");
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
			//////添加布局
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet.add("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet.items.getCount(),"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit);\r\n");
			/////////添加给子表单赋值开始///////////////
			sb.append("\tif(null != data){\r\n");
			sb.append("\t\t//获取表单中所有字段与值（key/value）\r\n");
			sb.append("\t\tvar "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditData = "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit.getForm().getFieldValues();\r\n");
			sb.append("\t\t//遍历表单中所有字段名称（key）\r\n");
			sb.append("\t\tfor(var "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditField in "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditData){\r\n");
			sb.append("\t\t\t//获取表单中所有字段名称（key）并且截取对象后面的字段 目的与实体类中字段名称一致\r\n");
			sb.append("\t\t\tvar "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditYField = "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditField.split(\".\")[1];\r\n");
			sb.append("\t\t\t//遍历服务器传递来的对象\r\n");
			sb.append("\t\t\tfor(var dataKey in data){\r\n");
			sb.append("\t\t\t\t//判断当前字段是否等于服务器传过来的字段\r\n");
			sb.append("\t\t\t\tif("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditYField == dataKey){\r\n");
			sb.append("\t\t\t\t\t//给表单每个字段赋值\r\n");
			sb.append("\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit,"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditField,data[dataKey]);\r\n");
			//追加附件回显
			sb.append(createAttachmentOneToManyObject(xt_Generator_TableMany_To_One, "Edit", 1));
			sb.append("\t\t\t\t}\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}\r\n");
			sb.append("\t}\r\n");
			/////////添加给子表单赋值结束///////////////
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet.afterLayout();\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet,true);\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet,true);\r\n");
			//初始化附件右键
			sb.append("\t"+createAttachmentOneToManyRight(xt_Generator_TableMany_To_One, 1)+"\r\n");
			sb.append("}\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 一对多生成（表单方式——修改-----FieldSet）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingUpdateFieldSetForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			//如果是最外层则需要加top
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEditFieldSet = Ext.create('Ext.form.FieldSet',{\r\n");
			sb.append("\t\tanchor:'100%',\r\n");
			sb.append("\t\ttitle:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
			sb.append("\t\titems:[{	\r\n");
			sb.append("\t\t\txtype:'button',\r\n");
			sb.append("\t\t\ttext:'添加"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
			sb.append("\t\t\tstyle:'float:left;margin-bottom:10px',\r\n");
			sb.append("\t\t\tlabelAlign:\"right\", \r\n");
			sb.append("\t\t\tlisteners:{\r\n");
			sb.append("\t\t\t\tclick:function(){\r\n");
			sb.append("\t\t\t\t\taddUpdate"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit(null);\r\n");
			sb.append("\t\t\t\t}\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}\r\n");
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 一对多生成加载表单并回调方法（表单方式——修改-----callFn）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingUpdateFormCallFn(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		sb.append("function callFnUpdate(form, action){\r\n");
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			sb.append("\tvar "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" = action.result.data."+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+";\r\n");
			sb.append("\tfor(var i = 0; i < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".length; i++){\r\n");
			sb.append("\t\taddUpdate"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormEdit("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"[i]);\r\n");
			sb.append("\t}\r\n");
		}
		sb.append("}\r\n");
		return sb.toString();
	}
	
	///////////////////////////////一对多生成（表单方式-明细-基本信息）
	/**
	 * 一对多生成（表单方式——详细-基本信息）
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingDetailBaseForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		//////////////////////生成Form模块开始/////////////////////////////
		///////创建基本信息FieldSet信息
		sb.append("function init"+uprepchar(xt_Generator.getXt_generator_tbname())+"FormDetail(){\r\n");
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetailFieldSet = Ext.create('Ext.form.FieldSet',{\r\n");
		//是否支持滚动条
		if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
			sb.append("\t\tautoScroll:true,\r\n");
		}
		//禁用X轴滚动条
		sb.append("\t\ttitle:'基本信息',\r\n");
		sb.append("\t\tscrollable:true,\r\n");
		sb.append("\t\tscrollable:'x',\r\n");
		sb.append("\t\tscrollable:'y',\r\n");
		sb.append("\t\titems:[\r\n");
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
			if(null != column_comment && !"".equals(column_comment)){
				column_comment = column_comment.replaceAll("amp;", "");
			}
			if(!getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
				if(i == (xt_Generator_Table_ColumnList.size()-1)){
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\tanchor:'80%',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
						sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
						sb.append("\t\t\t\t\t**/\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}else{
						//子表操作 逗号封闭
						if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
					}
				}else{
					sb.append("\t\t{\r\n");
					sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
					if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						if(("0").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}else if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文本域
							sb.append("\t\t\txtype:'textareafield',\r\n");
						}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							String xt_script_id = xt_Generator_Table_Column_Form.getXt_script_id();
			    			String xt_script_key = "";
			    			String xt_script_valuefield="";
			    			String xt_script_displayfield="";
			    			if(null != xt_script_id && !"".equals(xt_script_id)){
			    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
			        			xt_script_key = xtScript.getXt_script_key();
			        			xt_script_valuefield = xtScript.getXt_script_valuefield();
			        			xt_script_displayfield = xtScript.getXt_script_displayfield();
			    			}
							//下拉框
							sb.append("\t\t\txtype:'combo',\r\n");
							sb.append("\t\t\temptyText:'请选择',\r\n");
	        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
	        				sb.append("\t\t\tmode:'local',\r\n");
	        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
	        	            sb.append("\t\t\teditable:false,\r\n");
	        	            sb.append("\t\t\thiddenName:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
	        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
	        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
						}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
							//文件框
							//1添加隐含域即附件编号
							sb.append("\t\t\txtype:'textfield',\r\n");
							sb.append("\t\t\thidden:true,\r\n");
							sb.append("\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
						}else{
							//文本框
							sb.append("\t\t\txtype:'textfield',\r\n");
						}
					}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d',\r\n");
					}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'datetimefield',\r\n");
						sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
					}else if("time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'timefield',\r\n");
					}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
						sb.append("\t\t\txtype:'numberfield',\r\n");
						//设置默认值
						sb.append("\t\t\tvalue:'0',\r\n");
					}
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tname:'"+xt_Generator_Table_Column_Form.getColumn_name()+"',\r\n");
					if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
						sb.append("\t\t\tallowBlank:false,\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getCharacter_maximum_length() && !"".equals(xt_Generator_Table_Column_Form.getCharacter_maximum_length())){
						sb.append("\t\t\tmaxLength:"+xt_Generator_Table_Column_Form.getCharacter_maximum_length()+",\r\n");
					}
					if(null != xt_Generator_Table_Column_Form.getColumn_label_position() && !"".equals(xt_Generator_Table_Column_Form.getColumn_label_position())){
						if(xt_Generator_Table_Column_Form.getColumn_label_position().equals("1")){
							sb.append("\t\t\tlabelAlign:'top',\r\n");
						}
					}
					if(null != xt_Generator_Table_Column_Form.getIsHidden() && !"".equals(xt_Generator_Table_Column_Form.getIsHidden()) && "1".equals(xt_Generator_Table_Column_Form.getIsHidden())){
						sb.append("\t\t\thidden:true,\r\n");
					}
					sb.append("\t\t\tanchor:'"+xt_Generator_Table_Column_Form.getColumn_label_anchor()+"%'\r\n");
					//如果是文件框 则添加文件框按钮
					if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						sb.append("\t\t},\r\n");
						//文件框
						//2添加文件框上传按钮
						sb.append("\t\t{\r\n");
						sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
						sb.append("\t\t\tanchor:'80%',\r\n");
						sb.append("\t\t\txtype:'fieldset',\r\n");
						sb.append("\t\t\titems:{\r\n");
						sb.append("\t\t\t\txtype:'box', \r\n");
						sb.append("\t\t\t\twidth:80, \r\n");
						sb.append("\t\t\t\theight:60, \r\n");
						sb.append("\t\t\t\tid:'"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic', \r\n");
						sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
						sb.append("\t\t\t\tautoEl:{\r\n");
						sb.append("\t\t\t\t\ttag:'img',\r\n");
						sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
						sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_Column_Form.getColumn_name()+"','"+xt_Generator_Table_Column_Form.getColumn_name()+"_pic',1)\",\r\n");
						sb.append("\t\t\t\t\t**/\r\n");
						sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
						sb.append("\t\t\t\t}\r\n");
						sb.append("\t\t\t}\r\n");
						sb.append("\t\t},\r\n");
					}else{
						sb.append("\t\t},\r\n");
					}
				}
			}
		}
		/////////追加标识符新增和删除
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			//////////新增标记
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'numberfield',\r\n");
			sb.append("\t\t\thidden:true,\r\n");
			sb.append("\t\t\tvalue:'0',\r\n");
			sb.append("\t\t\titemId:'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber'\r\n");
			sb.append("\t\t},\r\n");
			//////////删除标记
			sb.append("\t\t{\r\n");
			sb.append("\t\t\txtype:'textfield',\r\n");
			sb.append("\t\t\thidden:true,\r\n");
			sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag',\r\n");
			sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag'\r\n");
			if((xt_Generator_TableMany_To_OneList.size()-1) == (i)){
				sb.append("\t\t}\r\n");
			}else{
				sb.append("\t\t},\r\n");
			}
		}
		
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		//添加子表fileset按钮
		sb.append(createOneToManyUsingDetailFieldSetForm(xt_Generator_Table_ColumnList, xt_Generator));
		//////////////////////生成FormFieldSet模块结束/////////////////////////////
		//////////////////////基本form开始////////////////////////////////
		sb.append("\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetail = Ext.create('Ext.FormPanel',{\r\n");
		sb.append("\t\txtype:'form',\r\n");
		sb.append("\t\twaitMsgTarget:true,\r\n");
		sb.append("\t\tdefaultType:'textfield',\r\n");
		//是否支持滚动条
		if(!"".equals(xt_Generator.getXt_generator_page_winScroll()) && "1".equals(xt_Generator.getXt_generator_page_winScroll())){
			sb.append("\t\tautoScroll:true,\r\n");
		}
		//禁用X轴滚动条
		sb.append("\t\t/**新方法使用开始**/\r\n");
		sb.append("\t\tscrollable:true,\r\n");
		sb.append("\t\tscrollable:'x',\r\n");
		sb.append("\t\tscrollable:'y',\r\n");
		sb.append("\t\t/**新方法使用结束**/\r\n");
		sb.append("\t\tfieldDefaults:{\r\n");
		//默认标签宽度
		if(xt_Generator.getXt_generator_page_labelWidth()>=0){
			sb.append("\t\t\tlabelWidth:"+xt_Generator.getXt_generator_page_labelWidth()+",\r\n");
		}else{
			sb.append("\t\t\tlabelWidth:70,\r\n");
		}
		//默认表单标签对齐方式1向左2向右3向上
		if(xt_Generator.getXt_generator_page_labelAlign().equals("1")){
			sb.append("\t\t\tlabelAlign:'left',\r\n"); 
		}else if(xt_Generator.getXt_generator_page_labelAlign().equals("2")){
			sb.append("\t\t\tlabelAlign:'right',\r\n");
		}else if(xt_Generator.getXt_generator_page_labelAlign().equals("3")){
			sb.append("\t\t\tlabelAlign:'top',\r\n");
		}else{
			sb.append("\t\t\tlabelAlign:'left',\r\n"); 
		}
		sb.append("\t\t\tflex:1,\r\n"); 
		sb.append("\t\t\treadOnly:true,\r\n"); 
		sb.append("\t\t\tmargin:'2 5 4 5'\r\n"); 
		sb.append("\t\t},\r\n"); 
		sb.append("\t\titems:[\r\n");
		////////////////////追加基本FormFieldSet
		sb.append("\t\t\t"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetailFieldSet,\r\n");
		////////////////////追加子表Form///////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One= xt_Generator_TableMany_To_OneList.get(i);
				if((xt_Generator_TableMany_To_OneList.size()-1) == i){
					sb.append("\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet\r\n");
				}else{
					sb.append("\t\t\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet,\r\n");
				}
			}
		}
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		sb.append("}\r\n");
		//////////////////////生成Form模块结束/////////////////////////////
		//追加回调方法
		sb.append(createOneToManyUsingDetailFormCallFn(xt_Generator_Table_ColumnList, xt_Generator));
		//追加创建子表form方法
		sb.append(createOneToManyUsingDetailForm(xt_Generator_Table_ColumnList, xt_Generator));
		return sb.toString();
	}
	/**
	 * 一对多生成（表单方式——详细）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingDetailForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		XtScriptDao xt_ScriptDao = (XtScriptDao)SpringUtil.getBean("xtScriptDao");
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			sb.append("function addDetail"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetail(data){\r\n");
			//定义删除子表URL
			String root_url = lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Controller";
			String del_url = "del"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());
			del_url="../"+root_url+"/"+del_url;
			sb.append("\tvar numbers = gfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetailFieldSet,'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber');\r\n");
			sb.append("\tsfiValue("+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"FormDetailFieldSet,'"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber',numbers+1);\r\n");
			sb.append("\t\r\n");
			//此处需要判断是否为top如果为top则需要添加top标识
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetail = Ext.create('Ext.FormPanel',{\r\n");
			sb.append("\t\txtype:'form',\r\n");
			sb.append("\t\twaitMsgTarget:true,\r\n");
			sb.append("\t\tdefaultType:'textfield',\r\n");
			sb.append("\t\tautoScroll:true,\r\n");
			sb.append("\t\tscrollable:true,\r\n");
			sb.append("\t\tscrollable:'x',\r\n");
			sb.append("\t\tscrollable:'y',\r\n");
			sb.append("\t\tfieldDefaults:{\r\n");
			sb.append("\t\t\tlabelWidth:70,\r\n");
			sb.append("\t\t\tlabelAlign:'left',\r\n");
			sb.append("\t\t\tflex:1,\r\n");
			sb.append("\t\t\treadOnly:true,\r\n"); 
			sb.append("\t\t\tmargin:'2 5 4 5'\r\n");
			sb.append("\t\t},\r\n");
			sb.append("\t\titems:[\r\n");
			List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
			for(int j = 0;j < xt_Generator_Table_ColumnMany_To_OneList.size();j++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(j);
				String column_comment=xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT();
				if(null != column_comment && !"".equals(column_comment)){
					column_comment = column_comment.replaceAll("amp;", "");
				}
				if(!getOneToManyColumnKeyUpOneChar(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
					if(j == (xt_Generator_Table_ColumnMany_To_OneList.size()-1)){
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							if(("0").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						
						//////////判断是否主键如果子表中该字段是主键则隐藏
						if(getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						//过滤外键作为并将其隐藏
						if(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey().toUpperCase().equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toUpperCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
							sb.append("\t\t\tallowBlank:false,\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH()+",\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_label_position() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position())){
							if(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getIsHidden() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden()) && "1".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\tanchor:'80%',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
//							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
//							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1)\",\r\n");
//							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t}\r\n");
						}else{
							sb.append("\t\t}\r\n");
						}
						//////////////闭区间
					}else{
						sb.append("\t\t{\r\n");
						sb.append("\t\t\tfieldLabel:'"+column_comment+"',\r\n");
						if("String".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							if(("0").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}else if(("1").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文本域
								sb.append("\t\t\txtype:'textareafield',\r\n");
							}else if(("3").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								String xt_script_id = xt_Generator_Table_ColumnMany_To_One.getXt_script_id();
				    			String xt_script_key = "";
				    			String xt_script_valuefield="";
				    			String xt_script_displayfield="";
				    			if(null != xt_script_id && !"".equals(xt_script_id)){
				    				XtScript xtScript = xt_ScriptDao.getXtScriptById(xt_script_id);
				        			xt_script_key = xtScript.getXt_script_key();
				        			xt_script_valuefield = xtScript.getXt_script_valuefield();
				        			xt_script_displayfield = xtScript.getXt_script_displayfield();
				    			}
								//下拉框
								sb.append("\t\t\txtype:'combo',\r\n");
								sb.append("\t\t\temptyText:'请选择',\r\n");
		        				sb.append("\t\t\tstore:"+xt_script_key+",\r\n");
		        				sb.append("\t\t\tmode:'local',\r\n");
		        	            sb.append("\t\t\ttriggerAction:'all',\r\n");
		        	            sb.append("\t\t\teditable:false,\r\n");
		        	            sb.append("\t\t\thiddenName:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
		        	            sb.append("\t\t\tvalueField:'"+xt_script_valuefield+"',\r\n");
		        	            sb.append("\t\t\tdisplayField:'"+xt_script_displayfield+"',\r\n");
							}else if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\txtype:'textfield',\r\n");
								sb.append("\t\t\thidden:true,\r\n");
								sb.append("\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
							}else{
								//文本框
								sb.append("\t\t\txtype:'textfield',\r\n");
							}
						}else if("Date".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d',\r\n");
						}else if("datetime".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'datetimefield',\r\n");
							sb.append("\t\t\tformat:'Y-m-d H:i:s',\r\n");
						}else if("time".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'timefield',\r\n");
						}else if("int".equals(sqlType2PageType(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE()))){
							sb.append("\t\t\txtype:'numberfield',\r\n");
							//设置默认值
							sb.append("\t\t\tvalue:'0',\r\n");
						}
						//////////判断是否主键如果子表中该字段是主键则隐藏
						if(getOneToManyColumnFormKey(xt_Generator_Table_ColumnMany_To_OneList).equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						//过滤外键作为并将其隐藏
						if(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey().toUpperCase().equals(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME().toUpperCase())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tname:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						sb.append("\t\t\titemId:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"',\r\n");
						if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
							sb.append("\t\t\tallowBlank:false,\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH())){
							sb.append("\t\t\tmaxLength:"+xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH()+",\r\n");
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_label_position() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position())){
							if(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position().equals("1")){
								sb.append("\t\t\tlabelAlign:'top',\r\n");
							}
						}
						if(null != xt_Generator_Table_ColumnMany_To_One.getIsHidden() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden()) && "1".equals(xt_Generator_Table_ColumnMany_To_One.getIsHidden())){
							sb.append("\t\t\thidden:true,\r\n");
						}
						sb.append("\t\t\tanchor:'"+xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor()+"%'\r\n");
						//如果是文件框 则添加文件框按钮
						if(("5").equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
							sb.append("\t\t},\r\n");
							//文件框
							//2添加文件框上传按钮
							sb.append("\t\t{\r\n");
							sb.append("\t\t\ttitle:'"+column_comment+"',\r\n");
							sb.append("\t\t\tanchor:'80%',\r\n");
							sb.append("\t\t\txtype:'fieldset',\r\n");
							sb.append("\t\t\titems:{\r\n");
							sb.append("\t\t\t\txtype:'box', \r\n");
							sb.append("\t\t\t\twidth:80, \r\n");
							sb.append("\t\t\t\theight:60, \r\n");
							sb.append("\t\t\t\tid:'"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic', \r\n");
							sb.append("\t\t\t\tmargin:'2 5 4 70', \r\n");
							sb.append("\t\t\t\tautoEl:{\r\n");
							sb.append("\t\t\t\t\ttag:'img',\r\n");
//							sb.append("\t\t\t\t\t/** 不采用右键时候直接用点击事件触发\r\n");
//							sb.append("\t\t\t\t\tonclick:\"optupload('"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1)\",\r\n");
//							sb.append("\t\t\t\t\t**/\r\n");
							sb.append("\t\t\t\t\tsrc:bsdefimg\r\n");
							sb.append("\t\t\t\t}\r\n");
							sb.append("\t\t\t}\r\n");
							sb.append("\t\t},\r\n");
						}else{
							sb.append("\t\t},\r\n");
						}
					}
				}
			}
			sb.append("\t\t]\r\n");
			sb.append("\t});\r\n");
			//////添加布局
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet.add("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet.items.getCount(),"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetail);\r\n");
			/////////添加给子表单赋值开始///////////////
			sb.append("\tif(null != data){\r\n");
			sb.append("\t\t//获取表单中所有字段与值（key/value）\r\n");
			sb.append("\t\tvar "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailData = "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetail.getForm().getFieldValues();\r\n");
			sb.append("\t\t//遍历表单中所有字段名称（key）\r\n");
			sb.append("\t\tfor(var "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailField in "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailData){\r\n");
			sb.append("\t\t\t//获取表单中所有字段名称（key）并且截取对象后面的字段 目的与实体类中字段名称一致\r\n");
			sb.append("\t\t\tvar "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailYField = "+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailField.split(\".\")[1];\r\n");
			sb.append("\t\t\t//遍历服务器传递来的对象\r\n");
			sb.append("\t\t\tfor(var dataKey in data){\r\n");
			sb.append("\t\t\t\t//判断当前字段是否等于服务器传过来的字段\r\n");
			sb.append("\t\t\t\tif("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailYField == dataKey){\r\n");
			sb.append("\t\t\t\t\t//给表单每个字段赋值\r\n");
			sb.append("\t\t\t\t\tsfiValue("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetail,"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailField,data[dataKey]);\r\n");
			//追加附件回显
			sb.append(createAttachmentOneToManyObject(xt_Generator_TableMany_To_One, "Detail", 1));
			sb.append("\t\t\t\t}\r\n");
			sb.append("\t\t\t}\r\n");
			sb.append("\t\t}\r\n");
			sb.append("\t}\r\n");
			/////////添加给子表单赋值结束///////////////
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet.afterLayout();\r\n");
			//追加重置序列即背景颜色奇数加背景色
			sb.append("\tresetTitle("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet,true);\r\n");
			//初始化附件右键
			sb.append("\t"+createAttachmentOneToManyRight(xt_Generator_TableMany_To_One, 2)+"\r\n");
			sb.append("}\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 一对多生成（表单方式——详细-----FieldSet）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingDetailFieldSetForm(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			//如果是最外层则需要加top
			sb.append("\t"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetailFieldSet = Ext.create('Ext.form.FieldSet',{\r\n");
			sb.append("\t\tanchor:'100%',\r\n");
			sb.append("\t\ttitle:'"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"',\r\n");
			sb.append("\t\titems:[]\r\n");
			sb.append("\t});\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 一对多生成加载表单并回调方法（表单方式——详细-----callFn）
	 * @param xt_Generator
	 * @return
	 */
	public String createOneToManyUsingDetailFormCallFn(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
		StringBuffer sb = new StringBuffer();
		sb.append("function callFnDetail(form, action){\r\n");
		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
			sb.append("\tvar "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" = action.result.data."+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+";\r\n");
			sb.append("\tfor(var i = 0; i < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".length; i++){\r\n");
			sb.append("\t\taddDetail"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormDetail("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"[i]);\r\n");
			sb.append("\t}\r\n");
		}
		sb.append("}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建一对多附件右键（Extjs风格）
	 * @param xt_Generator
	 * isUpAndDelete 1表示拥有上传和删除功能 即新增编辑页面使用2表示不拥有上传和删除功能 即明细页面使用
	 * @return
	 */
	public String createAttachmentOneToManyRight(XtGeneratorTableManyToOne xt_Generator_TableMany_To_One,int isUpAndDelete){
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
			XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
			if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			//回显附件使用
			if(isUpAndDelete == 2){
				sb.append("/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/\r\n");
			}else if(isUpAndDelete == 1){
				sb.append("/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/\r\n");
			}
			for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
				if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
					if(isUpAndDelete == 2){
						sb.append("\tinitFileRight('"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1,2);\r\n");
					}else if(isUpAndDelete == 1){
						sb.append("\tinitFileRight('"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"['+numbers+']."+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1,1);\r\n");
					}
				}
			}
			sb.append("\t/**初始化附件右键菜单结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
	
	
	/**
	 * 创建一对多附件右键（Bootstrap风格）
	 * @param xt_Generator
	 * isUpAndDelete 1表示拥有上传和删除功能 即新增编辑页面使用2表示不拥有上传和删除功能 即明细页面使用
	 * @return
	 */
	public static String createBAttachmentOneToManyRight(XtGeneratorTableManyToOne xt_Generator_TableMany_To_One,int isUpAndDelete){
		StringBuffer sb = new StringBuffer();
		List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
			XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
			if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			//回显附件使用
			if(isUpAndDelete == 2){
				sb.append("\t/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/\r\n");
			}else if(isUpAndDelete == 1){
				sb.append("\t/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/\r\n");
			}
			for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
				if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
					if(isUpAndDelete == 2){
						sb.append("\tinitBFileRight('"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_'+numbers+'_"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_'+numbers+'_"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',2);\r\n");
					}else if(isUpAndDelete == 1){
						sb.append("\tinitBFileRight('"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_'+numbers+'_"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"','"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_'+numbers+'_"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"_pic',1);\r\n");
					}
				}
			}
			sb.append("\t/**初始化附件右键菜单结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}


	/**
	 *  创建附件回显（Extjs风格）
	 * @param xt_Generator_TableMany_To_One
	 * @param addUpdateDetailType 添加Add 修改Edit 详细Detail
	 * @param isAddUpdateOrDetail新增编辑 还是明细
	 * @return
	 */
	public String createAttachmentOneToManyObject(XtGeneratorTableManyToOne xt_Generator_TableMany_To_One,String addUpdateDetailType,int isAddUpdateOrDetail){
		List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
			XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
			if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			StringBuffer sb = new StringBuffer();
			//回显附件使用
			sb.append("\t\t\t\t\t/**配置附件回显方法开始**/\r\n");
			for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
				if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
					sb.append("\t\t\t\t\tif("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Form"+addUpdateDetailType+"YField == '"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"'){\r\n");
					sb.append("\t\t\t\t\t\tvar params = {xt_attachment_id:data[dataKey],field_name:"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Form"+addUpdateDetailType+"Field};\r\n");
					sb.append("\t\t\t\t\t\tajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,"+isAddUpdateOrDetail+");\r\n");
					sb.append("\t\t\t\t\t};\r\n");
				}
			}
			sb.append("\t\t\t\t\t/**配置附件回显方法结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
	
	
	/**
	 *  创建附件回显（Bootstrap风格）
	 * @param xt_Generator_TableMany_To_One
	 * @param addUpdateDetailType 添加Add 修改Edit 详细Detail
	 * @param isAddUpdateOrDetail新增编辑 还是明细
	 * @return
	 */
	public String createBAttachmentOneToManyObject(XtGeneratorTableManyToOne xt_Generator_TableMany_To_One,String addUpdateDetailType,int isAddUpdateOrDetail){
		List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
		boolean isExistFile=false;
		for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
			XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
			if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
				isExistFile=true;
				break;
			}
		}
		if(isExistFile){
			StringBuffer sb = new StringBuffer();
			//回显附件使用
			sb.append("\t\t\t\t\t/**配置附件回显方法开始**/\r\n");
			for(int i = 0; i < xt_Generator_Table_ColumnMany_To_OneList.size(); i++){
				XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(i);
				if(null != xt_Generator_Table_ColumnMany_To_One.getColumn_type() && !"".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type()) && "5".equals(xt_Generator_Table_ColumnMany_To_One.getColumn_type())){
					sb.append("\t\t\t\t\tif("+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Form"+addUpdateDetailType+"YField == '"+xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME()+"'){\r\n");
					sb.append("\t\t\t\t\t\tvar params = {xt_attachment_id:data[dataKey],field_name:"+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Form"+addUpdateDetailType+"Field};\r\n");
					sb.append("\t\t\t\t\t\tajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,"+isAddUpdateOrDetail+");\r\n");
					sb.append("\t\t\t\t\t};\r\n");
				}
			}
			sb.append("\t\t\t\t\t/**配置附件回显方法结束**/\r\n");
			return sb.toString();
		}else{
			return "";
		}
	}
}
