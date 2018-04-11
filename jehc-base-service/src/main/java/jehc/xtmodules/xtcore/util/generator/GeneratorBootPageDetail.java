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
import jehc.xtmodules.xtmodel.XtGeneratorTableColumnManyToOne;
import jehc.xtmodules.xtmodel.XtGeneratorTableManyToOne;
import jehc.xtmodules.xtmodel.XtScript;

/**
 * bootstrap风格方式生成明细页面生成
 * 
 * @author 邓纯杰
 *
 */
public class GeneratorBootPageDetail extends GeneratorUtil {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 生成明细JSP及明细JS
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	public void createPageAll(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator){
		createPageBootDetailJs(xt_Generator_Table_ColumnList, xt_Generator);
		createPageBootDetailJsp(xt_Generator_Table_ColumnList, xt_Generator);
		//子表信息
//		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table() && xt_Generator.getOne_to_many_type().equals("0")){
//		}
	}
	//////////////////////1创建bootstrap 明细//////////////////////////
	/**
	 * 1.1.1生成bootstrap页面----detail.JS
	 * 
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootDetailJs(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		sb.append(createPageBootDetailJsContent(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path + lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-detail.js"),"UTF-8");
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
	public String createPageBootDetailJsContent(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		String root_url = lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller";
		//追加返回方法开始
		sb.append("//返回r\n");
		sb.append("function goback(){\r\n");
		sb.append("\ttlocation(\"../"+root_url+"/load"+uprepchar(xt_Generator.getXt_generator_tbname())+"\");\r\n");
		sb.append("}\r\n");
		//追加返回方法结束
		
		//初始化附件右键（Bootstrap风格）
		sb.append(GeneratorPage.createAttachmentBRight(xt_Generator, 2)+"\r\n");
		//创建回显
		sb.append(GeneratorPage.createBAttachmentObject(xt_Generator));
		
		/////////////////////一对多操作//////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){//如果当前类型为一对多方式并且当前对象为主表 则获取其子表并遍历
			//操作子表
			List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
				List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList_ = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
				////////////////////////////////////////追加子表附件回显开始//////////////////////////////////
				sb.append(GeneratorPage.createBAttachmentOneToManyObject(xt_Generator_Table_ColumnMany_To_OneList_, xt_Generator, xt_Generator_TableMany_To_One, 2));
				////////////////////////////////////////追加子表附件回显结束//////////////////////////////////
			}
		}
		return sb.toString();
	}
	
	
	
	
	/**
	 * 1.2.1生成bootstrap页面----detail.JSP
	 * 
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createPageBootDetailJsp(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		sb.append(createPageBootDetailJspContent(xt_Generator_Table_ColumnList, xt_Generator));
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path + lowAllChar_(xt_Generator.getXt_generator_tbname()) + "-detail.jsp"),"UTF-8");
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
	public String createPageBootDetailJspContent(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
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
		sb.append("<title>"+xt_Generator.getXt_generator_tbcomment()+"详情页面</title>\r\n");
		sb.append("</head>\r\n");
		sb.append("<body>\r\n");
		sb.append("\t<div class=\"panel-body\">\r\n");
		sb.append("\t\t<div class=\"page-header\">\r\n");
		sb.append("\t\t\t<h4>"+xt_Generator.getXt_generator_tbcomment()+"详情</h4>\r\n");
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
						sb.append("\t\t\t\t\t<select class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请选择"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\"></select>\r\n");
					}else if(("5").equals(xt_Generator_Table_Column_Form.getColumn_type())){
						//文件框
						//1添加隐含域即附件编号
						sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" name=\""+column_name+"\" id=\""+column_name+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
						sb.append("\t\t\t\t\t<img src = \"../deng/images/default/add_d.png\" class=\"img\" width=\"96\"  height=\"96\"  id=\""+column_name+"_pic\">\r\n");
					}else{
						//文本框
						sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"text\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
					}
				}else if("Date".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type())) || "datetime".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type())) || "time".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					sb.append("\t\t\t\t\t<input class=\"form_datetime form-control\" name=\""+column_name+"\" "+required.toString()+" placeholder=\"请选择时间\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				}else if("int".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					//数字框
					sb.append("\t\t\t\t\t<input class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				}else if("BigDecimal".equals(sqlType2PageType(xt_Generator_Table_Column_Form.getData_type()))){
					//数字框
					sb.append("\t\t\t\t\t<input class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				}else{
					//文本框
					sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"text\" maxlength=\""+column_maxlength+"\" "+required.toString()+" name=\""+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+tbName+"."+column_name+" }\">\r\n");
				}
				//结束判断类型
				sb.append("\t\t\t\t</div>\r\n");
				sb.append("\t\t\t</div>\r\n");
			}
			//字段结束
		}
		//遍历字段结束
		
		/////////////////////一对多操作（主表中追加子表标识）//////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){//如果当前类型为一对多方式并且当前对象为主表 则获取其子表并遍历
			List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
				//当前子表单的form标识
				String remvoed_flag = lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag";
				String formNumber = lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"FormNumber";
				sb.append("\t\t\t<div class=\"form-group\" style=\"display:none;\">\r\n");
				sb.append("\t\t\t\t<div class=\"col-lg-6\">\r\n");
				sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" name=\""+remvoed_flag+"\" id=\""+remvoed_flag+"\" >\r\n");
				sb.append("\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" value=\"${fn:length("+lowfristchar(xt_Generator.getXt_generator_tbname())+"."+lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+") }\" name=\""+formNumber+"\" id=\""+formNumber+"\" >\r\n");
				sb.append("\t\t\t\t</div>\r\n");
				sb.append("\t\t\t</div>\r\n");
			}
		}
		
		//////////////////一对多子表字段遍历（初始化显示一个子表单）///////////////////////////
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){//如果当前类型为一对多方式并且当前对象为主表 则获取其子表并遍历
			//操作子表
			List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
				XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
				String upcharTableName = uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());//开头转大写的表名（类名）
				//获取当前子表单的
				lowOneCharAll_(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());
				String lowfristTableName = lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());
				sb.append("\t\t\t<!-- 一对多子表开始（"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"） -->\r\n");
				sb.append("\t\t\t<div class=\"page-header\">\r\n");
				sb.append("\t\t\t\t<h4>"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"</h4>\r\n");
				sb.append("\t\t\t</div>\r\n");
				List<XtGeneratorTableColumnManyToOne> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
				//追加子表字段信息
				sb.append("\t\t\t<div class=\"form_"+lowfristTableName+"\">\r\n");//标注子表中根目录名称 用于JS操作APPEND 开始
				
				//foreach遍历
				sb.append("\t\t\t<c:forEach var=\""+lowfristTableName+"\" items=\"${"+lowfristchar(xt_Generator.getXt_generator_tbname())+"."+lowfristTableName+" }\" varStatus=\""+lowfristTableName+"Status\">\r\n");
				sb.append("\t\t\t\t<div id=\"form_"+lowfristTableName+"_${"+lowfristTableName+"Status.index}\">\r\n");
				sb.append("\t\t\t<fieldset>\r\n");
				sb.append("\t\t\t<legend><h5>序号${"+lowfristTableName+"Status.index+1}</h5></legend>\r\n");
				//遍历子表字段开始
				for(int j = 0;j < xt_Generator_Table_ColumnMany_To_OneList.size();j++){
					XtGeneratorTableColumnManyToOne xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(j);
					String column_comment=xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT();
					String column_name= xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME();
					String column_maxlength = xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH();
					String column_hidden = xt_Generator_Table_ColumnMany_To_One.getIsHidden();
					String dataType = xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE();
					String columne_type = xt_Generator_Table_ColumnMany_To_One.getColumn_type();
					if(null != column_comment && !"".equals(column_comment)){
						column_comment = column_comment.replaceAll("amp;", "");
					}
					//验证
					StringBuffer required = new StringBuffer();
					if(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE().equals("NO")){
						required.append(" data-bv-notempty data-bv-notempty-message=\"请输入"+column_comment+"\" ");
					}
					if("int".equals(sqlType2PageType(dataType))){
						required.append(" data-bv-numeric data-bv-numeric-message=\""+column_comment+"为数字类型\" ");
					}
					//如果主键 则隐藏
					if(getColumnFormKey(xt_Generator_Table_Column_FormList).equals(column_name)){
						//新增页面 主键 直接过滤
						continue;
					}
					//字段开始（如果该字段隐藏 则div隐藏 并且字段类型为隐含域）
					if("1".equals(column_hidden)){
						sb.append("\t\t\t\t\t<div class=\"form-group\" style=\"display:none;\">\r\n");
						sb.append("\t\t\t\t\t\t<label class=\"col-lg-3 control-label\">"+column_comment+"</label>\r\n");
						sb.append("\t\t\t\t\t\t<div class=\"col-lg-6\">\r\n");
						sb.append("\t\t\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" "+required.toString()+" placeholder=\"请输入"+column_comment+"\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
						sb.append("\t\t\t\t\t\t</div>\r\n");
						sb.append("\t\t\t\t\t</div>\r\n");
					}else{
						sb.append("\t\t\t\t\t<div class=\"form-group\">\r\n");
						sb.append("\t\t\t\t\t\t<label class=\"col-lg-3 control-label\">"+column_comment+"</label>\r\n");
						sb.append("\t\t\t\t\t\t<div class=\"col-lg-6\">\r\n");
						//开始判断类型
						if("String".equals(sqlType2PageType(dataType))){
							if(("1").equals(columne_type)){
								//文本域
								sb.append("\t\t\t\t\t\t\t<textarea class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" placeholder=\"请输入"+column_comment+"\"> ${"+lowfristTableName+"."+column_name+" }</textarea>\r\n");
							}else if(("3").equals(columne_type)){
								//下拉框（暂时下拉框采用文本框）
								sb.append("\t\t\t\t\t\t\t<select class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" placeholder=\"请选择"+column_comment+"\" value=\"${"+lowfristTableName+"."+column_name+" }\"></select>\r\n");
							}else if(("5").equals(columne_type)){
								//文件框
								//1添加隐含域即附件编号
								sb.append("\t\t\t\t\t\t\t<input class=\"form-control\" type=\"hidden\" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
								sb.append("\t\t\t\t\t\t\t<img src = \"../deng/images/default/add_d.png\" class=\"img\" width=\"96\"  height=\"96\"  id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"_pic\">\r\n");
							}else{
								//文本框
								sb.append("\t\t\t\t\t\t\t<input class=\"form-control\" type=\"text\" maxlength=\""+column_maxlength+"\" "+required.toString()+" id=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
							}
						}else if("Date".equals(sqlType2PageType(dataType)) || "datetime".equals(sqlType2PageType(dataType)) || "time".equals(sqlType2PageType(dataType))){
							sb.append("\t\t\t\t\t\t\t<input class=\"form_datetime form-control\" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" "+required.toString()+" placeholder=\"请选择时间\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
						}else if("int".equals(sqlType2PageType(dataType))){
							//数字框
							sb.append("\t\t\t\t\t\t\t<input class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
						}else if("BigDecimal".equals(sqlType2PageType(dataType))){
							//数字框
							sb.append("\t\t\t\t\t\t\t<input class=\"form-control\" maxlength=\""+column_maxlength+"\" "+required.toString()+" id=\""+lowfristTableName+"_${"+lowfristTableName+"Status.index}_"+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
						}else{
							//文本框
							sb.append("\t\t\t\t\t\t\t<input class=\"form-control\" type=\"text\" maxlength=\""+column_maxlength+"\" "+required.toString()+" id=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" name=\""+lowfristTableName+"[${"+lowfristTableName+"Status.index}]."+column_name+"\" placeholder=\"请输入"+column_comment+"\" value=\"${"+lowfristTableName+"."+column_name+" }\">\r\n");
						}
						//结束判断类型
						sb.append("\t\t\t\t\t\t</div>\r\n");
						sb.append("\t\t\t\t\t</div>\r\n");
					}
				}
				//遍历子表字段结束
				sb.append("\t\t\t\t</fieldset>\r\n");
				sb.append("\t\t\t\t</div>\r\n");
				sb.append("\t\t\t\t</c:forEach>\r\n");
				sb.append("\t\t\t</div>\r\n");//标注子表中根目录名称 用于JS操作APPEND 结束
				sb.append("\t\t\t<!-- 一对多子表结束（"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"） -->\r\n");
			}
		}
		
		//追加按钮
		sb.append("\t\t\t<div class=\"form-group\">\r\n");
		sb.append("\t\t\t\t<label class=\"col-lg-3 control-label\"></label>\r\n");
		sb.append("\t\t\t\t<div class=\"col-lg-6\">\r\n");
		sb.append("\t\t\t\t\t<button type=\"button\" class=\"btn default\" onclick=\"goback()\">返回</button>\r\n");
		sb.append("\t\t\t\t</div>\r\n");
		sb.append("\t\t\t</div>\r\n");
		sb.append("\t\t</form>\r\n");
		sb.append("\t</div>\r\n");
		sb.append("</body>\r\n");
		
		if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
			sb.append("<script type=\"text/javascript\">\r\n");
			sb.append("\tvar "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Obj = '${"+lowfristchar(xt_Generator.getXt_generator_tbname())+"JSON}';\r\n");
			sb.append("\ttry{\r\n");
			sb.append("\t\t"+lowfristchar(xt_Generator.getXt_generator_tbname())+"Obj = eval(\"(\"+"+lowfristchar(xt_Generator.getXt_generator_tbname())+"Obj+\")\");\r\n");
			sb.append("\t}catch(e){\r\n");
			sb.append("\t}\r\n");
			sb.append("</script>\r\n");
		}
		//导入JS支持
		sb.append("<script type=\"text/javascript\" src=\"../view/pc/"+xt_Generator.getXt_generator_page_package()+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"-detail.js\"></script> \r\n");
		sb.append("</html>\r\n");
		/////////////////////////内容结束////////////////////////
		return sb.toString();
	}
}
