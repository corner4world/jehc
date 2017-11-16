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

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.XtGenerator;
import jehc.xtmodules.xtmodel.XtGeneratorGridColumn;
import jehc.xtmodules.xtmodel.XtGeneratorSearchFiled;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;

/**
 * bootstrap风格方式生成列表生成
 * 
 * @author 邓纯杰
 *
 */
public class GeneratorBootPageList extends GeneratorUtil {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 生成列表JSP及列表JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	public void createPageAll(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		createPageBootListJs(xt_Generator_Table_ColumnList, xt_Generator);
		createPageBootListJsp(xt_Generator_Table_ColumnList, xt_Generator);
		//子表信息
//		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table() && xt_Generator.getOne_to_many_type().equals("0")){
//		}
	}
	
	//////////////////////1创建bootstrap 列表//////////////////////////
	/**
	 * 1.1.1生成bootstrap页面----list.JS
	 * 
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootListJs(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		sb.append(createPageBootListJsContent(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path + lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-list.js"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(), e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(), e.getCause());
			}
		}
		return sb.toString();
	}

	
	/**
	 * 1.1.2创建列表JS内容区域
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootListJsContent(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String list_url = "get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition";
		sb.append("var grid;\r\n");
		sb.append("$(document).ready(function() {\r\n");
		sb.append("\t/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题\r\n");
		sb.append("\tvar opt = {\r\n");
		sb.append("\t\tsearchformId:'searchForm'\r\n");
		sb.append("\t};\r\n");
		
		sb.append("\tvar options = DataTablesPaging.pagingOptions({\r\n");
		sb.append("\t\tajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../"+root_url+"/"+list_url+"',opt);},//渲染数据\r\n");
		sb.append("\t\t\t//在第一位置追加序列号\r\n");
		sb.append("\t\t\tfnRowCallback:function(nRow, aData, iDisplayIndex){\r\n");
		sb.append("\t\t\t\tjQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  \r\n");
		sb.append("\t\t\t\treturn nRow;\r\n");
		sb.append("\t\t},\r\n");
		
		sb.append("\t\torder:[],//取消默认排序查询,否则复选框一列会出现小箭头\r\n");
		sb.append("\t\t//列表表头字段\r\n");
		sb.append("\t\tcolums:[\r\n");
		//字段显示开始
		sb.append("\t\t\t{\r\n");
		sb.append("\t\t\t\tsClass:\"text-center\",\r\n");
		sb.append("\t\t\t\twidth:\"50px\",\r\n");
		sb.append("\t\t\t\tdata:\""+getColumnKey(xt_Generator_Table_ColumnList)+"\",\r\n");
		sb.append("\t\t\t\trender:function (data, type, full, meta) {\r\n");
		sb.append("\t\t\t\t\treturn '<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input type=\"checkbox\" name=\"checkId\" class=\"checkchild \" value=\"' + data + '\" /><span></span></label>';\r\n");
		sb.append("\t\t\t\t},\r\n");
		sb.append("\t\t\t\tbSortable:false\r\n");
		sb.append("\t\t\t},\r\n");
		List<XtGeneratorGridColumn> xt_Generator_Grid_ColumnList = xt_Generator.getXt_Generator_Grid_ColumnList();
		for(int i = 0; i < xt_Generator_Grid_ColumnList.size(); i++){
			XtGeneratorGridColumn xt_Generator_Grid_Column = xt_Generator_Grid_ColumnList.get(i);
			sb.append("\t\t\t{\r\n");
			sb.append("\t\t\t\tdata:'"+xt_Generator_Grid_Column.getXt_generator_grid_column_name()+"'\r\n");
			sb.append("\t\t\t},\r\n");
		}
		sb.append("\t\t\t{\r\n");
		sb.append("\t\t\t\tdata:\""+getColumnKey(xt_Generator_Table_ColumnList)+"\",\r\n");
		sb.append("\t\t\t\twidth:\"150px\",\r\n");
		sb.append("\t\t\t\trender:function(data, type, row, meta) {\r\n");
		sb.append("\t\t\t\t\treturn \"<a href=\\\"javascript:to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Detail('\"+ data +\"')\\\"><span class='glyphicon glyphicon-eye-open'></span></a>\";\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t}\r\n");
		//字段显示结束
		sb.append("\t\t]\r\n");
		sb.append("\t});\r\n");
		sb.append("\tgrid=$('#datatables').dataTable(options);\r\n");
		sb.append("\t//实现全选反选\r\n");
		sb.append("\tdocheckboxall('checkall','checkchild');\r\n");
		sb.append("\t//实现单击行选中\r\n");
		sb.append("\tclickrowselected('datatables');\r\n");
		sb.append("});\r\n");
		
		//追加新增方法
		sb.append("//新增\r\n");
		sb.append("function to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Add(){\r\n");
		sb.append("\ttlocation('../"+root_url+"/to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Add');\r\n");
		sb.append("}\r\n");
		
		//追加修改方法
		sb.append("//修改\r\n");
		sb.append("function to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Update(){\r\n");
		sb.append("\tif($(\".checkchild:checked\").length != 1){\r\n");
		sb.append("\t\ttoastrBoot(4,\"选择数据非法\");\r\n");
		sb.append("\t\treturn;\r\n");
		sb.append("\t}\r\n");
		sb.append("\tvar id = $(\".checkchild:checked\").val();\r\n");
		sb.append("\ttlocation(\"../"+root_url+"/to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Update?"+getColumnKey(xt_Generator_Table_ColumnList)+"=\"+id);\r\n");
		sb.append("}\r\n");
		
		//追加详细方法
		sb.append("//详情\r\n");
		sb.append("function to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Detail(id){\r\n");
		sb.append("\ttlocation(\"../"+root_url+"/to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Detail?"+getColumnKey(xt_Generator_Table_ColumnList)+"=\"+id);\r\n");
		sb.append("}\r\n");
		
		//追加删除方法
		sb.append("//删除\r\n");
		sb.append("function del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		sb.append("\tif(returncheckedLength('checkchild') <= 0){\r\n");
		sb.append("\t\ttoastrBoot(4,\"请选择要删除的数据\");\r\n");
		sb.append("\t\treturn;\r\n");
		sb.append("\t}\r\n");
		sb.append("\tmsgTishCallFnBoot(\"确定要删除所选择的数据？\",function(){\r\n");
		sb.append("\t\tvar id = returncheckIds('checkId').join(\",\");\r\n");
		sb.append("\t\tvar params = {"+getColumnKey(xt_Generator_Table_ColumnList)+":id};\r\n");
		sb.append("\t\tajaxBReq('../"+root_url+"/del"+uprepchar(xt_Generator.getXt_generator_tbname())+"',params,['datatables']);\r\n");
		sb.append("\t})\r\n");
		sb.append("}\r\n");
		
		//追加初始化日历选择器（需要做判断 判断查询条件中存在日期类型）
		boolean existDateType = false;
		List<XtGeneratorSearchFiled> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
		for(XtGeneratorSearchFiled xtGeneratorSearchFiled:xt_generator_search_filedList){
			if("4".equals(xtGeneratorSearchFiled.getXt_generator_search_type())){
				existDateType = true;
				break;
			}
		}
		if(existDateType){
			sb.append("//初始化日期选择器\r\n");
			sb.append("$(document).ready(function(){\r\n");
			sb.append("\tdatetimeInit();\r\n");
			sb.append("});\r\n");
		}
		return sb.toString();
	}
	
	
	
	
	/**
	 * 1.2.1生成bootstrap页面----list.JSP
	 * 
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootListJsp(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		sb.append(createPageBootListJspContent(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path + lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-list.jsp"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(), e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(), e.getCause());
			}
		}
		return sb.toString();
	}

	
	/**
	 * 1.2.2创建列表JSP内容区域
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootListJspContent(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		String list_url = "get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition";
		List<XtGeneratorSearchFiled> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
		List<String> xt_script_idList = new ArrayList<String>();
		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
			XtGeneratorSearchFiled xt_generator_search_filed = xt_generator_search_filedList.get(i);
			String xt_script_id = xt_generator_search_filed.getXt_script_id();
			if(null != xt_script_id && !"".equals(xt_script_id)){
				xt_script_idList.add(xt_script_id);
			}
		}
		sb.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\r\n");
		sb.append("<%@ include file=\"/deng/include/includeboot.jsp\"%>\r\n");
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		sb.append("<html>\r\n");
		sb.append("<head>\r\n");
		sb.append("<meta charset=\"UTF-8\">\r\n");
		sb.append("<title>"+xt_Generator.getXt_generator_tbcomment()+"</title>\r\n");
		sb.append("</head>\r\n");
		
		sb.append("<body>\r\n");
		//查询开始
		sb.append("\t<div class=\"panel panel-default\">\r\n");
		sb.append("\t\t<fieldset>\r\n");
		sb.append("\t\t\t<legend>查询区域</legend>\r\n");
		sb.append("\t\t\t<form method=\"POST\" id=\"searchForm\" class=\"form-inline\">\r\n");
		//查询区域内容开始
		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
			XtGeneratorSearchFiled xt_generator_search_filed = xt_generator_search_filedList.get(i);
			String xt_generator_search_name = xt_generator_search_filed.getXt_generator_search_name();
			String xt_generator_search_label = xt_generator_search_filed.getXt_generator_search_label();
			String xt_generator_search_type = xt_generator_search_filed.getXt_generator_search_type();
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
			
			sb.append("\t\t\t\t<div class=\"form-group\">\r\n");
			sb.append("\t\t\t\t\t<label>"+xt_generator_search_label+"</label>\r\n");
			if("0".equals(xt_generator_search_type) || "2".equals(xt_generator_search_type)){
				sb.append("\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\""+xt_generator_search_name+"\" placeholder=\"请输入"+xt_generator_search_label+"\">\r\n");
			}else if("1".equals(xt_generator_search_type)){
				sb.append("\t\t\t\t\t<textarea class=\"form-control\" name=\""+xt_generator_search_name+"\" placeholder=\"请输入"+xt_generator_search_label+"\"></textarea>\r\n");
			}else if("3".equals(xt_generator_search_type)){
				sb.append("\t\t\t\t\t<select type=\"text\" class=\"form-control\" name=\""+xt_generator_search_name+"\" placeholder=\"请选择\">\r\n");
			}else if("4".equals(xt_generator_search_type)){
				sb.append("\t\t\t\t\t<div class=\"input-group\">\r\n");
				sb.append("\t\t\t\t\t\t<input type=\"text\" class=\"form_datetime form-control\" placeholder=\"起始时间\" name=\""+xt_generator_search_name+"_st\" />\r\n");
				sb.append("\t\t\t\t\t\t<span class=\"input-group-addon\">至</span>\r\n");
				sb.append("\t\t\t\t\t\t<input type=\"text\" class=\"form_datetime form-control\" placeholder=\"结束时间\" name=\""+xt_generator_search_name+"_et\" />\r\n");
				sb.append("\t\t\t\t\t</div>\r\n");
			}
			sb.append("\t\t\t\t</div>\r\n");
		}
		//查询区域内容结束
		sb.append("\t\t\t</form>\r\n");
		sb.append("\t\t\t<div class=\"form-group\" style=\"margin-left: 35px;margin-top: 25px;\">\r\n");
		sb.append("\t\t\t\t<button class=\"btn btn-primary\" onclick=\"search('datatables')\">\r\n");
		sb.append("\t\t\t\t\t<i class=\"glyphicon glyphicon-search\"></i>&nbsp;检索\r\n");
		sb.append("\t\t\t\t</button>\r\n");
		sb.append("\t\t\t\t<button class=\"btn btn-default\" onclick=\"resetAll();\">重置</button>\r\n");
		sb.append("\t\t\t</div>\r\n");
		sb.append("\t\t</fieldset>\r\n");
		sb.append("\t</div>\r\n");
		//查询结束
		
		/////////////////////////内容开始////////////////////////
		//按钮开始
		sb.append("\t<div class=\"panel-body\">\r\n");
		sb.append("\t\t<div class=\"btn-group pull-right\" style=\"margin-right: 20px;\">\r\n");
		sb.append("\t\t\t<button class=\"btn btn-default\" onclick=\"to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Add()\">\r\n");
		sb.append("\t\t\t\t<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>新增\r\n");
		sb.append("\t\t\t</button>\r\n");
		sb.append("\t\t\t<button class=\"btn btn-default\" onclick=\"to"+uprepchar(xt_Generator.getXt_generator_tbname())+"Update()\">\r\n");
		sb.append("\t\t\t\t<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span>修改\r\n");
		sb.append("\t\t\t</button>\r\n");
		sb.append("\t\t\t<button class=\"btn btn-default\" onclick=\"del"+uprepchar(xt_Generator.getXt_generator_tbname())+"()\">\r\n");
		sb.append("\t\t\t\t<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>删除\r\n");
		sb.append("\t\t\t</button>\r\n");
		sb.append("\t\t\t<button class=\"btn btn-default\" onclick=\"search('datatables')\">\r\n");
		sb.append("\t\t\t\t<span class=\"glyphicon glyphicon-refresh\" aria-hidden=\"true\"></span>刷新\r\n");
		sb.append("\t\t\t</button>\r\n");
		sb.append("\t\t</div>\r\n");
		//按钮结束
		sb.append("\t\t<table id=\"datatables\" class=\"table table-bordered table-striped table-hover\">\r\n");
		sb.append("\t\t\t<thead>\r\n");
		sb.append("\t\t\t\t<tr>\r\n");
		sb.append("\t\t\t\t\t<th><label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input type=\"checkbox\" class=\"checkall\" /><span></span></label></th>\r\n");//复选框
		//遍历字段开始
		List<XtGeneratorGridColumn> xt_Generator_Grid_ColumnList = xt_Generator.getXt_Generator_Grid_ColumnList();
		for(int i = 0; i < xt_Generator_Grid_ColumnList.size(); i++){
			XtGeneratorGridColumn xt_Generator_Grid_Column = xt_Generator_Grid_ColumnList.get(i);
			sb.append("\t\t\t\t\t<th>"+xt_Generator_Grid_Column.getXt_generator_grid_column_label()+"</th>\r\n");
		}
		//遍历字段结束
		sb.append("\t\t\t\t\t<th>操作</th>\r\n");
		sb.append("\t\t\t\t</tr>\r\n");
		sb.append("\t\t\t</thead>\r\n");
		sb.append("\t\t</table>\r\n");
		sb.append("\t</div>\r\n");
		sb.append("</body>\r\n");
		//导入JS支持
		sb.append("<script type=\"text/javascript\" src=\"../view/pc/"+xt_Generator.getXt_generator_page_package()+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"-list.js\"></script> \r\n");
		sb.append("</html>\r\n");
		/////////////////////////内容结束////////////////////////
		return sb.toString();
	}
}
