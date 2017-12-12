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

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.XtGenerator;
import jehc.xtmodules.xtmodel.XtGeneratorTableManyToOne;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;

/**
 * 代码生成支持类
 * @author邓纯杰
 *
 */
public class GeneratorModel extends GeneratorUtil{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
     * 功能：生成实体类主体代码
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    public int createModel(List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList,XtGenerator xt_Generator) {
    	int result = 0;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        sb.append("package "+xt_Generator.getXt_generator_model_package()+";\r\n");
        //判断是否导入工具包
        /**
        if(f_util){
            sb.append("import java.util.Date;\r\n");
        }
        if(f_sql){
            sb.append("import java.sql.*;\r\n");
        }
        sb.append(sqlDatePage(xt_Generator_Table_ColumnList));
        **/
        if(xt_Generator.getXt_generator_extendF().equals("1")){
        	sb.append("import jehc.xtmodules.xtcore.base.BaseEntity;\r\n");
        }
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import java.util.Date;\r\n");
        //判断是否为一对多 并且是主表 如果为一对多则导入实体
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("import "+ xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_model_package()+"."+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+";\r\n");
        		sb.append("import java.util.List;\r\n");
        	}
        }
	    
        sb.append("\r\n");
        //注释部分
        sb.append("/**\r\n");
        sb.append("* "+xt_Generator.getXt_generator_tbname()+" "+xt_Generator.getXt_generator_tbcomment()+" \r\n");
        sb.append("* "+sdf.format(new Date())+"  邓纯杰\r\n");
        sb.append("*/");
        //sb.append("*/ \r\n");
        //实体部分
        if(xt_Generator.getXt_generator_extendF().equals("1")){
        	sb.append("\r\npublic class " + toUpperCase(xt_Generator.getXt_generator_tbname()) + " extends BaseEntity implements Serializable{\r\n");
        }else{
        	sb.append("\r\npublic class " + toUpperCase(xt_Generator.getXt_generator_tbname()) + " implements Serializable{\r\n");
        }
        //sb.append("\r\n\r\npublic class " + toUpperCase(xt_Generator.getXt_generator_tbname()) + "{\r\n");
        sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
        //当前生成类型为一对多操作并且为主表则向属性中追加子表属性字符串即可
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("0")){
        		List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		XtGeneratorTableColumn xt_Generator_Table_Column = new XtGeneratorTableColumn();
    				xt_Generator_Table_Column.setCOLUMN_COMMENT(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh());
    				xt_Generator_Table_Column.setCOLUMN_NAME(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());
    				xt_Generator_Table_Column.setDATA_TYPE("string");
    				xt_Generator_Table_ColumnList.add(xt_Generator_Table_Column);
            	}
        	}
        }
        //实体属性
        createColumnAttributes(xt_Generator,sb,xt_Generator_Table_ColumnList);
        //实体get和set方法
        createColumenMethod(xt_Generator,sb,xt_Generator_Table_ColumnList);
        sb.append("}\r\n");
        /**
        FileWriter fw;
        PrintWriter pw = null;
		try {
			String path = xt_Generator.getXt_generator_path();
			fw = new FileWriter(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + ".java");
			pw = new PrintWriter(fw);
	        pw.println(sb.toString());
	        result = 1;
		} catch (IOException e) {
			result = 0;
			e.printStackTrace();
		}finally{
			 pw.flush();
		     pw.close();
		}
		**/
		String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + ".java"),"UTF-8");
			try {
				out.write(sb.toString());
				result = 1;
			} catch (IOException e) {
				result = 0;
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		} catch (UnsupportedEncodingException e) {
			result = 0;
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		} catch (FileNotFoundException e) {
			result = 0;
			logger.error(e.getMessage());
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				result = 0;
				logger.error(e.getMessage());
				throw new ExceptionUtil(e.getMessage(),e.getCause());
			}
		}
        return result;
    }
     
    /**
     * 生成所有属性
     * @param sb
     * @param xt_Generator_Table_ColumnList
     */
    public void createColumnAttributes(XtGenerator xt_Generator,StringBuffer sb,List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList) {
        for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
        	XtGeneratorTableColumn xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
        	sb.append("\tprivate " + sqlType2JavaType(xt_Generator_Table_Column.getDATA_TYPE()) + " " + xt_Generator_Table_Column.getCOLUMN_NAME()+";/**"+xt_Generator_Table_Column.getCOLUMN_COMMENT()+"**/" + "\r\n");
        }
        List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        //当前生成类型为一对多操作并且为主表则向属性中追加子表属性字符串即可
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("1")){
        		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        			//追加一对多集合属性
            		sb.append("\tprivate List<" + toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + "> " + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+";/**"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"**/" + "\r\n");
            		//追加一对多移除标识
            		sb.append("\tprivate String " + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag;/**"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh()+"移除标识**/" + "\r\n");
        		}
        	}
        }
    }
 
    /**
     * 生成所有方法
     * @param sb
     * @param xt_Generator_Table_ColumnList
     */
    public void createColumenMethod(XtGenerator xt_Generator,StringBuffer sb,List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList){
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
        	XtGeneratorTableColumn xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
        	 sb.append("\tpublic void set" + initcap(xt_Generator_Table_Column.getCOLUMN_NAME()) + "(" + sqlType2JavaType(xt_Generator_Table_Column.getDATA_TYPE()) + " " + xt_Generator_Table_Column.getCOLUMN_NAME() + "){\r\n");
             sb.append("\t\tthis." + xt_Generator_Table_Column.getCOLUMN_NAME() + "=" + xt_Generator_Table_Column.getCOLUMN_NAME() + ";\r\n");
             sb.append("\t}\r\n");
             sb.append("\tpublic " + sqlType2JavaType(xt_Generator_Table_Column.getDATA_TYPE()) + " get" + initcap(xt_Generator_Table_Column.getCOLUMN_NAME()) + "(){\r\n");
             sb.append("\t\treturn " + xt_Generator_Table_Column.getCOLUMN_NAME() + ";\r\n");
             sb.append("\t}\r\n");
    	}
    	List<XtGeneratorTableManyToOne> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        //当前生成类型为一对多操作并且为主表则向属性中追加子表属性字符串即可
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("1")){
        		for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        			XtGeneratorTableManyToOne xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		//追加一对多集合属性
            		sb.append("\tpublic void set"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + "(" + "List<" + toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + ">" + " " + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + "){\r\n");
	                sb.append("\t\tthis." + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + "=" + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + ";\r\n");
	                sb.append("\t}\r\n");
	                sb.append("\tpublic " + "List<" + toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + ">" + " get" + toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + "" + "(){\r\n");
	                sb.append("\t\treturn " + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()) + ";\r\n");
	                sb.append("\t}\r\n");
	                //追加一对多移除标识
	                sb.append("\tpublic void set" + toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag" + "(String " + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag" + "){\r\n");
	                sb.append("\t\tthis." + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag" + "=" + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag" + ";\r\n");
	                sb.append("\t}\r\n");
	                sb.append("\tpublic String get" + toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag" + "(){\r\n");
	                sb.append("\t\treturn " + lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag" + ";\r\n");
	                sb.append("\t}\r\n");
        		}
        	}
        }
    }
     
    
    ////////////////测试//////////////////
    public static void main(String[]args){
    	//测试生成实体部分
    	String str = "xtuserinfo_";
    	StringBuffer sb = new StringBuffer();
    	String[] strList = str.split("_");
    	if(null != strList){
    		for(int i =  0; i < strList.length; i++){
    			//此时数据库表结构规则如:xt_userinfo,xtuserinfo等等
    			if(strList[i].length() > 0){
    				//需要转换成大写的字母
        			String tempStr1 = strList[i].substring(0,1);
        			//需要转换不需要转换的字母
        			String tempStr2 = strList[i].substring(1,strList[i].length());
        			tempStr1.toUpperCase();
        			sb.append(tempStr1.toUpperCase());
        			sb.append(tempStr2);
        			if((i+1)==strList.length){
        				
        			}else{
        				sb.append("_");
        			}
    			}else{
    				//此时数据库表结构规则如:_xt_userinfo,xt_userinfo_,_xt_userinfo_,xtuserinfo_,_xtuserinfo,_xtuserinfo_等等
        			sb.append("_");
    			}
    		}
    	}
    	String lastchar = str.substring(str.length()-1, str.length());
    	if("_".equals(lastchar)){
    		sb.append("_");
    	}
//    	System.out.println(sb.toString());
    }
}
