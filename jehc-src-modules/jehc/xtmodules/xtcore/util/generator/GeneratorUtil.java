package jehc.xtmodules.xtcore.util.generator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_ColumnMany_To_One;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column_Form;

/**
 * 代码生成支持类共用
 * @author邓纯杰
 *
 */
public class GeneratorUtil {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean f_util = false;//是否需要导入包java.util.*
    public boolean f_sql = false;//是否需要导入包java.sql.*
    /**
     * 将输入字符串的首字母改成大写
     * 在字段中使用
     * @param str
     * @return
     */
    public String initcap(String str) {
        char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }
        return new String(ch);
    }
    /**
     * 将字符中"_"后面第一个字母转换成大写
     * 在类名上使用和类文件
     * @param str
     * @return
     */
    public String toUpperCase(String str){
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
    	return sb.toString();
    }
    
    /**
     * 将字符中"_"后面第一个字母转换成大写并且去除所有"_"
     * 在类名上使用和类文件
     * @param str
     * @return
     */
    public String uprepchar(String str){
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
    	if(null != sb.toString() && !"".equals(sb.toString())){
    		return sb.toString().replaceAll("_", "");
    	}else{
    		return null;
    	}
    } 
    
    /**
     * 转换第一个字母为小写
     * @param str
     * @return
     */
    public String lowfristchar(String str){
    	str = toUpperCase(str);
    	StringBuffer sb = new StringBuffer();
    	if(null != str && !"".equals(str)){
    		sb.append(str.substring(0,1).toLowerCase());
    		sb.append(str.substring(1,str.length()));
    	}
    	return sb.toString();
    }
    
    /**
     * 去除所有"_" 并将第一个字符转换成小写
     * @param str
     * @return
     */
    public String lowOneCharAll_(String str){
    	str = uprepchar(str);
    	StringBuffer sb = new StringBuffer();
    	if(null != str && !"".equals(str)){
    		sb.append(str.substring(0,1).toLowerCase());
    		sb.append(str.substring(1,str.length()));
    	}
    	return sb.toString();
    }
    
    /**
     * 替换所有"_"为"-" 并将所有字符转换成小写
     * @param str
     * @return
     */
    public String lowAllChar_(String str){
    	StringBuffer sb = new StringBuffer();
    	if(null != str && !"".equals(str)){
    		str = str.replaceAll("_", "-");
    		str = str.toLowerCase();
    	}
    	sb.append(str);
    	return sb.toString();
    }
    
    /**
     * 功能：获得列的数据类型
     * @param sqlType
     * @return
     */
    public String sqlType2JavaType(String sqlType) {
//        if(sqlType.equalsIgnoreCase("bit")){
//            return "boolean";
//        }else if(sqlType.equalsIgnoreCase("tinyint")){
//            return "byte";
//        }else if(sqlType.equalsIgnoreCase("smallint")){
//            return "short";
//        }else if(sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INTEGER")){
//            return "int";
//        }else if(sqlType.equalsIgnoreCase("bigint")){
//            return "long";
//        }else if(sqlType.equalsIgnoreCase("float")){
//            return "float";
//        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
//                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
//                || sqlType.equalsIgnoreCase("smallmoney")){
//            return "double";
//        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
//                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
//                || sqlType.equalsIgnoreCase("text")){
//            return "String";
//        }else if(sqlType.equalsIgnoreCase("datetime")||sqlType.equalsIgnoreCase("date")){
//            return "Date";
//        }else if(sqlType.equalsIgnoreCase("image")){
//            return "Blod";
//        }
    	if(sqlType.equalsIgnoreCase("bit")){
            return "boolean";
        }else if(sqlType.equalsIgnoreCase("tinyint")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("smallint")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INTEGER")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("bigint")){
            return "long";
        }else if(sqlType.equalsIgnoreCase("float")){
            return "float";
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
                || sqlType.equalsIgnoreCase("smallmoney") ||sqlType.equalsIgnoreCase("double")){
            return "double";
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("longtext")
                || sqlType.equalsIgnoreCase("mediumtext") || sqlType.equalsIgnoreCase("longblob")
                || sqlType.equalsIgnoreCase("tinytext") || sqlType.equalsIgnoreCase("blob")
                || sqlType.equalsIgnoreCase("mediumblob") || sqlType.equalsIgnoreCase("set")
                || sqlType.equalsIgnoreCase("binary") || sqlType.equalsIgnoreCase("varbinary")
                //枚举
                || sqlType.equalsIgnoreCase("enum")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("datetime")||sqlType.equalsIgnoreCase("date")||sqlType.equalsIgnoreCase("time")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("image")){
            return "String";
        }else{
        	return "String";
        }
    }
    
    /**
     * 筛选主键
     * @param xt_Generator_Table_ColumnList
     * @return
     */
    public String getColumnKey(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList){
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if("PRI".equals(xt_Generator_Table_Column.getCOLUMN_KEY())){
    			return xt_Generator_Table_Column.getCOLUMN_NAME();
    		}
    	}
    	throw new ExceptionUtil("xt_Generator_Table_ColumnList---未能筛选出主键");
    }
    
    /**
     * 筛选表单中主键 新方法 单表操作
     * @param xt_Generator_Table_Column_FormList
     * @return
     */
    public String getColumnFormKey(List<Xt_Generator_Table_Column_Form> xt_Generator_Table_Column_FormList){
    	for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
    		Xt_Generator_Table_Column_Form xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
    		if("PRI".equals(xt_Generator_Table_Column_Form.getColumn_key())){
    			return xt_Generator_Table_Column_Form.getColumn_name();
    		}
    	}
    	throw new ExceptionUtil("xt_Generator_Table_Column_FormList----getColumnFormKey-----筛选表单中主键 新方法 单表操作");
    }
    
    /**
     * 筛选主键并将第一个字符转换成大写 单表操作
     * @param xt_Generator_Table_ColumnList
     * @return
     */
    public String getColumnKeyUpOneChar(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList){
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if("PRI".equals(xt_Generator_Table_Column.getCOLUMN_KEY())){
    			String key = xt_Generator_Table_Column.getCOLUMN_NAME();
    			sb.append(key.substring(0, 1).toUpperCase());
    			sb.append(key.substring(1, key.length()));
    			return sb.toString();
    		}
    	}
    	throw new ExceptionUtil("xt_Generator_Table_ColumnList--getColumnKeyUpOneChar--- 筛选主键并将第一个字符转换成大写 单表操作");
    }
    
    /**
     * 筛选表单中主键 新方法 一对多表操作
     * @param xt_Generator_Table_Column_FormList
     * @return
     */
    public String getOneToManyColumnFormKey(List<Xt_Generator_Table_ColumnMany_To_One> xt_Generator_Table_Column_FormList){
    	for(int i = 0; i < xt_Generator_Table_Column_FormList.size(); i++){
    		Xt_Generator_Table_ColumnMany_To_One xt_Generator_Table_Column_Form = xt_Generator_Table_Column_FormList.get(i);
    		if("PRI".equals(xt_Generator_Table_Column_Form.getCOLUMN_KEY())){
    			return xt_Generator_Table_Column_Form.getCOLUMN_NAME();
    		}
    	}
    	throw new ExceptionUtil("xt_Generator_Table_Column_FormList----getOneToManyColumnFormKey-----筛选表单中主键 新方法 一对多表操作");
    }
    
    /**
     * 筛选主键并将第一个字符转换成大写 一对多表操作
     * @param xt_Generator_Table_ColumnList
     * @return
     */
    public String getOneToManyColumnKeyUpOneChar(List<Xt_Generator_Table_ColumnMany_To_One> xt_Generator_Table_ColumnList){
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_ColumnMany_To_One xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if("PRI".equals(xt_Generator_Table_Column.getCOLUMN_KEY())){
    			String key = xt_Generator_Table_Column.getCOLUMN_NAME();
    			sb.append(key.substring(0, 1).toUpperCase());
    			sb.append(key.substring(1, key.length()));
    			return sb.toString();
    		}
    	}
    	throw new ExceptionUtil("xt_Generator_Table_ColumnList----getOneToManyColumnKeyUpOneChar-----筛选主键并将第一个字符转换成大写 一对多表操作");
    }
    
    /**
     * 功能：获得列的数据类型(用于前端操作)
     * @param sqlType
     * @return
     */
    public String sqlType2PageType(String sqlType) {
        if(sqlType.equalsIgnoreCase("bit")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("tinyint")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("smallint")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INTEGER")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("bigint")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("float")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
                || sqlType.equalsIgnoreCase("smallmoney")){
            return "int";
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("longtext")
                || sqlType.equalsIgnoreCase("mediumtext") || sqlType.equalsIgnoreCase("longblob")
                || sqlType.equalsIgnoreCase("tinytext") || sqlType.equalsIgnoreCase("blob")
                || sqlType.equalsIgnoreCase("mediumblob") || sqlType.equalsIgnoreCase("set")
                || sqlType.equalsIgnoreCase("binary") || sqlType.equalsIgnoreCase("varbinary")
                //枚举
                || sqlType.equalsIgnoreCase("enum")){
            return "String";
        }else if(sqlType.equalsIgnoreCase("date")){
            return "Date";
        }else if(sqlType.equalsIgnoreCase("datetime")){
            return "datetime";
        }else if(sqlType.equalsIgnoreCase("time")){
            return "time";
        }else if(sqlType.equalsIgnoreCase("image")){
            return "String";
        }
        return "String";
    }
    
    /**
     * 判断列字段中是否存在日期，如果存在则导入包名导
     * @param xt_Generator_Table_ColumnList
     * @return
     */
    public String sqlDatePage(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList){
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("datetime")||xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("date")){
    			return "import java.util.Date;\r\n";
    		}
    	}
    	throw new ExceptionUtil("xt_Generator_Table_ColumnList----sqlDatePage-----如果存在则导入包名导");
    }
    ////////////////测试//////////////////
    public static void main(String[]args){
    	
    }
}
