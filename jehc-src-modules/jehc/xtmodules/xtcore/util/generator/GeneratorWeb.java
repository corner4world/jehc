package jehc.xtmodules.xtcore.util.generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtmodel.Xt_Generator;
import jehc.xtmodules.xtmodel.Xt_Generator_Search_Filed;
import jehc.xtmodules.xtmodel.Xt_Generator_TableMany_To_One;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;

/**
 * 生成控制层代码
 * @author邓纯杰
 *
 */
public class GeneratorWeb extends GeneratorUtil{
	/**
	 * 创建控制层(Web)
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	 public String createWeb(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+xt_Generator.getXt_generator_web_package()+";\r\n");
    	//导入包名
    	sb.append("import java.util.List;\r\n");
    	sb.append("import java.util.HashMap;\r\n");
    	sb.append("import java.util.Map;\r\n");
    	/**
    	List<Xt_Generator_Search_Filed> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
    	if(!xt_generator_search_filedList.isEmpty() && xt_generator_search_filedList.size()>0){
    		sb.append("import java.io.UnsupportedEncodingException;\r\n");
    		sb.append("import java.net.URLDecoder;\r\n");
    	}**/
    	//判断是否为一对多 并且是主表 如果为一对多则导入实体
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("0")){
        		sb.append("import xtCore.util.JsonUtil;\r\n");
        	}
        	List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("import "+ xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_model_package()+"."+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+";\r\n");
        		sb.append("import "+ xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_service_package()+"."+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service;\r\n");
        	}
        }
    	sb.append("import javax.servlet.http.HttpServletRequest;\r\n");
    	sb.append("import javax.servlet.http.HttpServletResponse;\r\n");
    	sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
    	sb.append("import org.springframework.stereotype.Controller;\r\n");
    	sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
    	sb.append("import org.springframework.web.bind.annotation.RequestMethod;\r\n");
    	sb.append("import org.springframework.web.bind.annotation.ResponseBody;\r\n");
    	sb.append("import org.springframework.web.servlet.ModelAndView;\r\n");
    	//是否采用多例模式
    	if("2".equals(xt_Generator.getXt_generator_scope())){
    		sb.append("import org.springframework.context.annotation.Scope;\r\n");
    	}
    	if(xt_Generator.getXt_generator_page().equals("1")){
    		sb.append("import com.github.pagehelper.PageInfo;\r\n");
    	}
    	sb.append("import jehc.xtmodules.xtcore.base.BaseAction;\r\n");
    	sb.append("import jehc.xtmodules.xtcore.base.BaseSearch;\r\n");
    	
    	sb.append("import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;\r\n");
    	sb.append("import jehc.xtmodules.xtcore.util.UUID;\r\n");
    	
    	sb.append("import "+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+";\r\n");
    	sb.append("import "+xt_Generator.getXt_generator_service_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"Service;\r\n");
        sb.append("\r\n");
        //注释部分
        sb.append("/**\r\n");
        sb.append("* "+xt_Generator.getXt_generator_tbcomment()+" \r\n");
        sb.append("* "+sdf.format(new Date())+"  "+xt_Generator.getXt_userinfo_realName()+"\r\n");
        sb.append("*/");
        //Web类模块
        sb.append("\r\n@Controller\r\n");
        sb.append("@RequestMapping(\"/"+lowOneCharAll_(xt_Generator.getXt_generator_tbname())+"Controller\")");
        //是否采用多例模式
    	if("2".equals(xt_Generator.getXt_generator_scope())){
    		sb.append("\r\n@Scope(\"prototype\")");
    	}
        sb.append("\r\npublic class " + toUpperCase(xt_Generator.getXt_generator_tbname())+"Controller" + " extends BaseAction{\r\n");
        //注入Service接口
        sb.append("\t@Autowired\r\n");
        sb.append("\tprivate "+ toUpperCase(xt_Generator.getXt_generator_tbname())+"Service "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service;\r\n");
        //判断是否为一对多并且是主表 如果为一对多则注解子表接口
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("\t@Autowired\r\n");
        		sb.append("\tprivate "+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service;\r\n");
        	}
        }
        //WEB中方法
        //1.1载入初始化页面
        sb.append(createWebLoadPage(xt_Generator_Table_ColumnList, xt_Generator));
        //1.2分页
        sb.append(createWebList(xt_Generator_Table_ColumnList,xt_Generator));
        //1.3查询对象
        sb.append(createWebObj(xt_Generator_Table_ColumnList,xt_Generator));
        //1.4添加
        sb.append(createWebAdd(xt_Generator_Table_ColumnList,xt_Generator));
        //1.5修改
        sb.append(createWebUpdate(xt_Generator_Table_ColumnList,xt_Generator));
        //1.6删除
        sb.append(createWebeDel(xt_Generator_Table_ColumnList,xt_Generator));
        //1.7复制一行并生成记录
        sb.append(createWebCopy(xt_Generator_Table_ColumnList, xt_Generator));
        //1.8导出
        sb.append(createExport(xt_Generator_Table_ColumnList, xt_Generator));
        sb.append("}\r\n");
        String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + "Controller.java"),"UTF-8");
			try {
				out.write(sb.toString());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
    	return sb.toString();
	 }
	 
	 /**
	  * 创建加载页面
	  * @param xt_Generator_Table_ColumnList
	  * @param xt_Generator
	  * @return
	  */
	public String createWebLoadPage(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
		StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 载入初始化页面\r\n");
    	sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
        sb.append("\t@RequestMapping(value=\"/load"+uprepchar(xt_Generator.getXt_generator_tbname())+"\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
    	sb.append("\tpublic ModelAndView load"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+",HttpServletRequest request){\r\n");
    	sb.append("\t\treturn new ModelAndView(\"pc/"+xt_Generator.getXt_generator_page_package()+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"/"+lowAllChar_(xt_Generator.getXt_generator_tbname())+"-list"+"\");\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
	}
	/**
	 * 创建分页
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createWebList(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 加载初始化列表数据并分页\r\n");
    	sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        List<Xt_Generator_Search_Filed> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
        //添加方法模块
        sb.append("\t@ResponseBody\r\n");
        sb.append("\t@RequestMapping(value=\"/get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
    	sb.append("\tpublic String get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition(BaseSearch baseSearch,HttpServletRequest request){\r\n");
		sb.append("\t\tMap<String, Object> condition = baseSearch.convert();\r\n");
		if(xt_Generator.getXt_generator_page().equals("2")){
			//普通分页
			sb.append("\t\tcommonPager(condition,request);\r\n");
		}else{
			//物理分页
			sb.append("\t\tcommonHPager(condition,request);\r\n");
		}
		//查询模块
//    	if(!xt_generator_search_filedList.isEmpty() && xt_generator_search_filedList.size()>0){
//    		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
//    			Xt_Generator_Search_Filed xt_generator_search_filed = xt_generator_search_filedList.get(i);
//    			String xt_generator_search_name = xt_generator_search_filed.getXt_generator_search_name();
////    			sb.append("\t\tif(null != request.getParameter(\""+xt_generator_search_name+"\") && !\"\".equals(request.getParameter(\""+xt_generator_search_name+"\"))){\r\n");
//    			sb.append("\t\tcondition.put(\""+xt_generator_search_name+"\",request.getParameter(\""+xt_generator_search_name+"\"));\r\n");
////    			sb.append("\t\t}\r\n");
//    		}
//    	}
    	
    	//子表操作 通过外键查找
    	if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
    		sb.append("\t\tcondition.put(\""+xt_Generator.getFk()+"\",request.getParameter(\""+xt_Generator.getFk()+"\"));\r\n");
    	}
		
		sb.append("\t\tList<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> " +lowfristchar(xt_Generator.getXt_generator_tbname())+"List = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition(condition);\r\n");	
		if(xt_Generator.getXt_generator_page().equals("2")){
			sb.append("\t\tint total = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListCountByCondition(condition);\r\n");	
			sb.append("\t\toutPageStr("+lowfristchar(xt_Generator.getXt_generator_tbname())+"List,total);\r\n");	
		}else{
			sb.append("\t\tPageInfo<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> page = new PageInfo<"+toUpperCase(xt_Generator.getXt_generator_tbname())+">(" +lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");	
			sb.append("\t\treturn outPageStr(page,request);\r\n");	
		}
		sb.append("\t}\r\n");
		return sb.toString();
	}
	
	/**
	* 创建获取对象
	* @param xt_Generator_Table_ColumnList
	* @param xt_Generator
	* @return
	*/
	public String createWebObj(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 获取对象\r\n");
    	sb.append("\t* @param "+getColumnKey(xt_Generator_Table_ColumnList)+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        //添加方法模块
        sb.append("\t@ResponseBody\r\n");
        sb.append("\t@RequestMapping(value=\"/get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
        sb.append("\tpublic String get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById(String "+getColumnKey(xt_Generator_Table_ColumnList)+",HttpServletRequest request){\r\n");
		sb.append("\t\t"+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+" = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById("+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
		sb.append("\t\treturn outDataStr("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
		sb.append("\t}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建添加
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createWebAdd(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		if(xt_Generator.getIs_one_to_many().equals("1")){
			
		}else{//单表操作
			
		}
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 添加\r\n");
    	sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        //添加方法模块
        sb.append("\t@ResponseBody\r\n");
        sb.append("\t@RequestMapping(value=\"/add"+uprepchar(xt_Generator.getXt_generator_tbname())+"\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
        sb.append("\tpublic String add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+",HttpServletRequest request){\r\n");
        sb.append("\t\tint i = 0;\r\n");
		//判断对象是否存在
        sb.append("\t\tif(null != "+lowfristchar(xt_Generator.getXt_generator_tbname())+" && !\"\".equals("+lowfristchar(xt_Generator.getXt_generator_tbname())+")){\r\n");
        //设置主键
        sb.append("\t\t\t"+lowfristchar(xt_Generator.getXt_generator_tbname())+".set"+getColumnKeyUpOneChar(xt_Generator_Table_ColumnList)+"(UUID.toUUID());\r\n");
        //执行添加操作
        sb.append("\t\t\ti="+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
		sb.append("\t\t}\r\n");
		//判断是否操作成功
		sb.append("\t\tif(i>0){\r\n");
//		sb.append("\t\t\toutAudStr(true, \"添加信息成功\");\r\n");
		sb.append("\t\t\treturn outAudStr(true);\r\n");
		sb.append("\t\t}else{\r\n");
//		sb.append("\t\t\toutAudStr(false, \"添加信息失败\");\r\n");
		sb.append("\t\t\treturn outAudStr(false);\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建修改
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createWebUpdate(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 修改\r\n");
    	sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        //添加方法模块
        sb.append("\t@ResponseBody\r\n");
        sb.append("\t@RequestMapping(value=\"/update"+uprepchar(xt_Generator.getXt_generator_tbname())+"\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
        sb.append("\tpublic String update"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+",HttpServletRequest request){\r\n");
        sb.append("\t\tint i = 0;\r\n");
		//判断对象是否存在
        sb.append("\t\tif(null != "+lowfristchar(xt_Generator.getXt_generator_tbname())+" && !\"\".equals("+lowfristchar(xt_Generator.getXt_generator_tbname())+")){\r\n");
        //执行添加操作
        sb.append("\t\t\ti="+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.update"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
		sb.append("\t\t}\r\n");
		//判断是否操作成功
		sb.append("\t\tif(i>0){\r\n");
//		sb.append("\t\t\toutAudStr(true, \"修改信息成功\");\r\n");
		sb.append("\t\t\treturn outAudStr(true);\r\n");
		sb.append("\t\t}else{\r\n");
//		sb.append("\t\t\toutAudStr(false, \"修改信息失败\");\r\n");
		sb.append("\t\t\treturn outAudStr(false);\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建删除
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createWebeDel(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 删除\r\n");
    	sb.append("\t* @param "+getColumnKey(xt_Generator_Table_ColumnList)+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        //添加方法模块
        sb.append("\t@ResponseBody\r\n");
        sb.append("\t@RequestMapping(value=\"/del"+uprepchar(xt_Generator.getXt_generator_tbname())+"\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
        sb.append("\tpublic String del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(String "+getColumnKey(xt_Generator_Table_ColumnList)+",HttpServletRequest request){\r\n");
		sb.append("\t\tint i = 0;\r\n");
		//判断对象是否存在
        sb.append("\t\tif(null != "+getColumnKey(xt_Generator_Table_ColumnList)+" && !\"\".equals("+getColumnKey(xt_Generator_Table_ColumnList)+")){\r\n");
        //执行添加操作
        sb.append("\t\t\tMap<String, Object> condition = new HashMap<String, Object>();\r\n");
        sb.append("\t\t\tcondition.put(\""+getColumnKey(xt_Generator_Table_ColumnList)+"\","+getColumnKey(xt_Generator_Table_ColumnList)+".split(\",\"));\r\n");
        sb.append("\t\t\ti="+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(condition);\r\n");
		sb.append("\t\t}\r\n");
		//判断是否操作成功
		sb.append("\t\tif(i>0){\r\n");
//		sb.append("\t\t\toutAudStr(true, \"删除信息成功\");\r\n");
		sb.append("\t\t\treturn outAudStr(true);\r\n");
		sb.append("\t\t}else{\r\n");
//		sb.append("\t\t\toutAudStr(false, \"删除信息失败\");\r\n");
		sb.append("\t\t\treturn outAudStr(false);\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建复制一行并生成记录
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createWebCopy(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator) {
		StringBuffer sb = new StringBuffer();
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 复制一行并生成记录\r\n");
    	sb.append("\t* @param "+getColumnKey(xt_Generator_Table_ColumnList)+" \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        //添加方法模块
        sb.append("\t@ResponseBody\r\n");
        sb.append("\t@RequestMapping(value=\"/copy"+uprepchar(xt_Generator.getXt_generator_tbname())+"\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
        sb.append("\tpublic String copy"+uprepchar(xt_Generator.getXt_generator_tbname())+"(String "+getColumnKey(xt_Generator_Table_ColumnList)+",HttpServletRequest request){\r\n");
        sb.append("\t\tint i = 0;\r\n");
        sb.append("\t\t"+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+" = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById("+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
        //判断对象是否存在
        sb.append("\t\tif(null != "+lowfristchar(xt_Generator.getXt_generator_tbname())+" && !\"\".equals("+lowfristchar(xt_Generator.getXt_generator_tbname())+")){\r\n");
        //设置主键
        sb.append("\t\t\t"+lowfristchar(xt_Generator.getXt_generator_tbname())+".set"+getColumnKeyUpOneChar(xt_Generator_Table_ColumnList)+"(UUID.toUUID());\r\n");
        //判断是否为一对多 并且是主表 如果为一对多则导入实体
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
    		//设置从表
            List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("\t\t\tMap<String, Object> condition = new HashMap<String, Object>();\r\n");
        		sb.append("\t\t\tcondition.put(\""+getColumnKey(xt_Generator_Table_ColumnList)+"\", "+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
        		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.get"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"ListByCondition(condition);\r\n");
        		sb.append("\t\t\t"+lowfristchar(xt_Generator.getXt_generator_tbname())+".set"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List);\r\n");
        	}
        }
        //执行添加操作
        sb.append("\t\t\ti="+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service.add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
		sb.append("\t\t}\r\n");
		//判断是否操作成功
		sb.append("\t\tif(i>0){\r\n");
		sb.append("\t\t\treturn outAudStr(true);\r\n");
		sb.append("\t\t}else{\r\n");
		sb.append("\t\t\treturn outAudStr(false);\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t}\r\n");
		return sb.toString();
	}
	
	/**
	 * 创建导出
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 * @return
	 */
	public String createExport(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
		StringBuffer sb = new StringBuffer();
		//添加注释模块
		sb.append("\t/**\r\n");
    	sb.append("\t* 导出\r\n");
    	sb.append("\t* @param excleData \r\n");
    	sb.append("\t* @param excleHeader \r\n");
    	sb.append("\t* @param excleText \r\n");
    	sb.append("\t* @param request \r\n");
        sb.append("\t* @param request \r\n");
        sb.append("\t*/\r\n");
        //添加方法模块
        sb.append("\t@RequestMapping(value=\"/export"+uprepchar(xt_Generator.getXt_generator_tbname())+"\",method={RequestMethod.POST,RequestMethod.GET})\r\n");
        sb.append("\tpublic void export"+uprepchar(xt_Generator.getXt_generator_tbname())+"(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){\r\n");
        sb.append("\t\tExportExcel exportExcel = new ExportExcel();\r\n");
        sb.append("\t\texportExcel.exportExcel(excleData, excleHeader,excleText,response);\r\n");
		sb.append("\t}\r\n");
		return sb.toString();
	}
}
