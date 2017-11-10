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
import jehc.xtmodules.xtmodel.XtGeneratorTableColumnForm;
import jehc.xtmodules.xtmodel.XtScript;

/**
 * bootstrap风格方式生成编辑页面生成
 * 
 * @author 邓纯杰
 *
 */
public class GeneratorBootPageUpdate extends GeneratorUtil {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 生成编辑JSP及编辑JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	public void createPageAll(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		createPageBootUpdateJs(xt_Generator_Table_ColumnList, xt_Generator);
		createPageBootUpdateJsp(xt_Generator_Table_ColumnList, xt_Generator);
		//子表信息
//		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table() && xt_Generator.getOne_to_many_type().equals("0")){
//		}
	}
	//////////////////////1创建bootstrap 编辑//////////////////////////
	/**
	 * 1.1.1生成bootstrap页面----update.JS
	 * 
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootUpdateJs(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		sb.append(createPageBootUpdateJsContent(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path + lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-update.js"),"UTF-8");
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
	public String createPageBootUpdateJsContent(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		//追加返回方法开始
		sb.append("//返回r\n");
		sb.append("function goback(){\r\n");
		sb.append("\ttlocation(\"../"+root_url+"/load"+uprepchar(xt_Generator.getXt_generator_tbname())+"\");\r\n");
		sb.append("}\r\n");
		//追加返回方法结束
		
		//验证
		sb.append("$('#defaultForm').bootstrapValidator({\r\n");
		sb.append("\tmessage:'此值不是有效的'\r\n");
		sb.append("});\r\n");
		
//		通过jsp页面验证即可 也可采用js验证
//		//追加验证开始
//		sb.append("$('#defaultForm').bootstrapValidator({\r\n");
//		sb.append("\tmessage:'此值不是有效的',\r\n");
//		/*
//		sb.append("\tfeedbackIcons:{\r\n");
//		sb.append("\t\tvalid:'glyphicon glyphicon-ok',\r\n");
//		sb.append("\t\tinvalid:'glyphicon glyphicon-remove',\r\n");
//		sb.append("\t\tvalidating:'glyphicon glyphicon-refresh'\r\n");
//		sb.append("\t},\r\n");
//		*/
//		sb.append("\tfields:{\r\n");
//		//追加需要验证的字段开始
//		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
//		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()&&xt_Generator.getOne_to_many_type().equals("1")){
//			//一对多操作
////			sb.append(createOneToManyUsingUpdateBaseForm(xt_Generator_Table_ColumnList, xt_Generator));
//		}else{
//			//单表生成
//			for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
//				XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
//				//不为空验证
//				if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
//					sb.append("\t\t"+xt_Generator_Table_Column_Form.getColumn_name()+":{\r\n");
//					sb.append("\t\t\tvalidators:{\r\n");
//					sb.append("\t\t\t\tnotEmpty:{\r\n");
//					sb.append("\t\t\t\t\tmessage:'"+xt_Generator_Table_Column_Form.getColumn_comment()+"不能为空'\r\n");
//					if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
//						sb.append("\t\t\t\t},\r\n");
//					}else{
//						sb.append("\t\t\t\t}\r\n");
//					}
//					if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
//						sb.append("\t\t\t\tregexp:{\r\n");
//						sb.append("\t\t\t\t\tregexp:/^[\\d]+$/,\r\n");
//						sb.append("\t\t\t\t\tmessage:'"+xt_Generator_Table_Column_Form.getColumn_comment()+"只能为数字'\r\n");
//						sb.append("\t\t\t\t}\r\n");
//					}
//					sb.append("\t\t\t}\r\n");
//					if(i == (xt_Generator_Table_Column_FormList.size()-1)){
//						sb.append("\t\t}\r\n");
//					}else{
//						sb.append("\t\t},\r\n");
//					}
//				}
//			}
//		}
//		//追加需要验证的字段结束
//		sb.append("\t}\r\n");
//		sb.append("});\r\n");
//		//追加验证结束
//		
//		
		//追加保存方法
		sb.append("//保存\r\n");
		sb.append("function update"+uprepchar(xt_Generator.getXt_generator_tbname())+"(){\r\n");
		sb.append("\tsubmitBForm('defaultForm','../"+root_url+"/update"+uprepchar(xt_Generator.getXt_generator_tbname())+"','../"+root_url+"/load"+uprepchar(xt_Generator.getXt_generator_tbname())+"');\r\n");
		sb.append("}\r\n");
		
		//追加初始化日历选择器（需要做判断 判断查询条件中存在日期类型）
		boolean existDateType = false;
		List<XtGeneratorTableColumnForm> xtGeneratorTableColumnFormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		for(XtGeneratorTableColumnForm filed:xtGeneratorTableColumnFormList){
			if("Date".equals(sqlType2PageType(filed.getData_type())) || "datetime".equals(sqlType2PageType(filed.getData_type())) || "time".equals(sqlType2PageType(filed.getData_type()))){
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
		//初始化附件右键（Bootstrap风格）
		sb.append(GeneratorPage.createAttachmentBRight(xt_Generator, 1)+"\r\n");
		//创建回显
		sb.append(GeneratorPage.createBAttachmentObject(xt_Generator));
		return sb.toString();
	}
	
	
	
	
	/**
	 * 1.2.1生成bootstrap页面----update.JSP
	 * 
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootUpdateJsp(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		sb.append(createPageBootUpdateJspContent(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path + lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-update.jsp"),"UTF-8");
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
	public String createPageBootUpdateJspContent(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
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
		sb.append("<title>"+xt_Generator.getXt_generator_tbcomment()+"编辑页面</title>\r\n");
		sb.append("</head>\r\n");
		sb.append("<body>\r\n");
		sb.append("\t<div class=\"panel-body\">\r\n");
		sb.append("\t\t<div class=\"page-header\">\r\n");
		sb.append("\t\t\t<h4>编辑"+xt_Generator.getXt_generator_tbcomment()+"</h4>\r\n");
		sb.append("\t\t</div>\r\n");
		sb.append("\t\t<form class=\"form-horizontal\" id=\"defaultForm\" method=\"post\">\r\n");
		//遍历字段开始
		List<XtGeneratorTableColumnForm> xt_Generator_Table_Column_FormList = xt_Generator.getXt_Generator_Table_Column_FormList();
		String tbName = lowfristchar(xt_Generator.getXt_generator_tbname());
		for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
			XtGeneratorTableColumnForm xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
			String column_comment=xt_Generator_Table_Column_Form.getColumn_comment();
			String column_name= xt_Generator_Table_Column_Form.getColumn_name();
			String column_maxlength = xt_Generator_Table_Column_Form.getCharacter_maximum_length();
			String column_hidden = xt_Generator_Table_Column_Form.getIsHidden();
			if(null != column_comment && !"".equals(column_comment)){
				column_comment = column_comment.replaceAll("amp;", "");
			}
			
			//验证
			StringBuffer required = new StringBuffer();
			if(xt_Generator_Table_Column_Form.getIs_nullable().equals("NO")){
				required.append(" data-bv-notempty data-bv-notempty-message=\"请输入"+column_comment+"\" ");
			}
			if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
				required.append(" data-bv-numeric data-bv-numeric-message=\""+column_comment+"为数字类型\" ");
			}
			
			//如果主键 则隐藏
			if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(xt_Generator_Table_Column_Form.getColumn_name())){
				sb.append("\t\t\t<div class=\"form-group\" style=\"display:none;\">\r\n");
				sb.append("\t\t\t\t<label class=\"col-lg-3 control-label\">"+column_comment+"</label>\r\n");
				sb.append("\t\t\t\t<div class=\"col-lg-6\">\r\n");
				sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" name=\""+column_name+"\"  placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				sb.append("\t\t\t\t</div>\r\n");
				sb.append("\t\t\t</div>\r\n");
				continue;
			}
			//字段开始（如果该字段隐藏 则div隐藏 并且字段类型为隐含域）
			if("1".equals(column_hidden)){
				sb.append("\t\t\t<div class=\"form-group\" style=\"display:none;\">\r\n");
				sb.append("\t\t\t\t<label class=\"col-lg-3 control-label\">"+column_comment+"</label>\r\n");
				sb.append("\t\t\t\t<div class=\"col-lg-6\">\r\n");
				sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" name=\""+column_name+"\" "+required.toString()+" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				sb.append("\t\t\t\t</div>\r\n");
				sb.append("\t\t\t</div>\r\n");
			}else{
				sb.append("\t\t\t<div class=\"form-group\">\r\n");
				sb.append("\t\t\t\t<label class=\"col-lg-3 control-label\">"+column_comment+"</label>\r\n");
				sb.append("\t\t\t\t<div class=\"col-lg-6\">\r\n");
				//开始判断类型
				if("String".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					if(("1").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文本域
						sb.append("\t\t\t\t\t<textarea class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\">${"+tbName+"."+column_name+" }</textarea>\r\n");
					}else if(("3").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//下拉框（暂时下拉框采用文本框）
						sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"text\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
					}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文件框
						//1添加隐含域即附件编号
						sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" name=\""+column_name+"\" id=\""+column_name+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
						sb.append("\t\t\t\t\t<img src = \"../deng/images/default/add_d.png\" class=\"img\" id=\""+column_name+"_pic\">\r\n");
					}else{
						//文本框
						sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"text\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
					}
				}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type())) || "datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type())) || "time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\t\t\t<input class=\"form_datetime form-control\" name=\""+column_name+"\" "+required.toString()+" placeholder=\"请选择时间\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					//数字框
					sb.append("\t\t\t\t\t<input class=\"form-control\" maxlength=\""+column_maxlength+"\" value=\"0\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				}
				//结束判断类型
				sb.append("\t\t\t\t</div>\r\n");
				sb.append("\t\t\t</div>\r\n");
			}
			//字段结束
		}
		//遍历字段结束
		
		//追加按钮
		sb.append("\t\t\t<div class=\"form-group\">\r\n");
		sb.append("\t\t\t\t<label class=\"col-lg-3 control-label\"></label>\r\n");
		sb.append("\t\t\t\t<div class=\"col-lg-6\">\r\n");
		sb.append("\t\t\t\t\t<button type=\"button\" class=\"btn green\" onclick=\"update"+uprepchar(xt_Generator.getXt_generator_tbname())+"()\">保存</button>\r\n");
		sb.append("\t\t\t\t\t<button type=\"button\" class=\"btn default\" onclick=\"goback()\">返回</button>\r\n");
		sb.append("\t\t\t\t</div>\r\n");
		sb.append("\t\t\t</div>\r\n");
		sb.append("\t\t</form>\r\n");
		sb.append("\t</div>\r\n");
		sb.append("</body>\r\n");
		//导入JS支持
		sb.append("<script type=\"text/javascript\" src=\"../view/pc/"+xt_Generator.getXt_generator_page_package()+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"-update.js\"></script> \r\n");
		sb.append("</html>\r\n");
		/////////////////////////内容结束////////////////////////
		return sb.toString();
	}
}
