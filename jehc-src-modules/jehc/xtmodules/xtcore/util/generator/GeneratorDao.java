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
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;

/**
 * 生成数据层代码(包括接口Dao,实现类DaoImpl,Mybatis等代码)
 * @author邓纯杰
 *
 */
public class GeneratorDao extends GeneratorUtil{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 创建所有数据层相关(Mybatis,Dao,DaoImpl)
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	 public void createDaoAll(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	//MyBatis
		createMyBatis(xt_Generator_Table_ColumnList, xt_Generator);
		//Dao接口
    	createDao(xt_Generator_Table_ColumnList, xt_Generator);
    	//Dao实现类
    	createDaoImpl(xt_Generator_Table_ColumnList, xt_Generator);
     }
	//////////////////////////////////////1.生成MyBatis部分开始//////////////////////////////////////////
	/**
     * 创建MyBatis主体
     */
    public String createMyBatis(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//拼接XML头部模块
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
    	sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \r\n\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
    	//拼接NameSpace模块
    	sb.append("<mapper namespace=\""+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"\">");
    	if(null != xt_Generator.getXt_generator_isuse_rediscache() && !"".equals(xt_Generator.getXt_generator_isuse_rediscache()) && xt_Generator.getXt_generator_isuse_rediscache().equals("1")){
    		sb.append("\r\n\t<!-- 启用缓存 -->\r\n\t<cache type=\"xtCore.cache.LoggingRedisCache\" /> \r\n");
    	}
    	//拼接查询列表模块
    	sb.append(createMyBatisList(xt_Generator_Table_ColumnList, xt_Generator));
    	if(xt_Generator.getXt_generator_page().equals("2")){
    		//拼接统计模块 普通分页使用
        	sb.append(createMyBatisListCount(xt_Generator_Table_ColumnList, xt_Generator));
    	}
    	//拼接查询对象模块
    	sb.append(createMyBatisObj(xt_Generator_Table_ColumnList, xt_Generator));
    	//拼接添加对象模块
    	sb.append(createMyBatisAdd(xt_Generator_Table_ColumnList, xt_Generator));
    	//拼接修改对象模块
    	sb.append(createMyBatisUpdate(xt_Generator_Table_ColumnList, xt_Generator));
    	//拼接修改对象模块（根据动态条件）
    	sb.append(createMyBatisUpdateBySelective(xt_Generator_Table_ColumnList, xt_Generator));
    	//拼接删除对象模块
    	sb.append(createMyBatisDel(xt_Generator_Table_ColumnList, xt_Generator));
    	//拼装批量添加、批量修改模块
    	if(xt_Generator.getXt_generator_batch_add_update().equals("1")&& xt_Generator.isIs_main_table()){
    		sb.append(createMyBatisAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
    		sb.append(createMyBatisUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
    		//根据动态条件修改
    		sb.append(createMyBatisUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
    	}
    	//如果是一对多子表类型 并且当前表为子表 则必须生成批量添加 批量修改两个模块
		if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
			sb.append(createMyBatisAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
    		sb.append(createMyBatisUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
    		//根据动态条件修改
    		sb.append(createMyBatisUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
    		//同时生成根据外键删除
    		sb.append(createMyBatisDelByForeignKey(xt_Generator_Table_ColumnList, xt_Generator));
		}
    	sb.append("</mapper>");
    	String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + ".xml"),"UTF-8");
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
     * 创建MyBatis文件中集合方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisList(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--初始化分页-->");
    	//追加标签开始
    	sb.append("\r\n\t<select id=\"get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition\" resultType=\""+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"\" parameterType=\"map\">");
    	//追加SELECT开头
    	sb.append("\r\n\t\tSELECT\r\n");
    	//追加COLUMN列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(i == xt_Generator_Table_ColumnList.size()-1){
    			if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("datetime")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%Y-%m-%d %H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("date")){
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("time")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}else{
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}
    		}else{
    			if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("datetime")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%Y-%m-%d %H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("date")){
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("time")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}else{
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}
    		}
    	}
		//追加FROM
    	sb.append("\t\tFROM \r\n\t\t\t"+xt_Generator.getXt_generator_tbname()+"\r\n");
		//追加条件模块
    	sb.append("\t\tWHERE 1=1\r\n");
    	if(xt_Generator.isIs_main_table()){
    		List<Xt_Generator_Search_Filed> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
        	if(!xt_generator_search_filedList.isEmpty() && xt_generator_search_filedList.size()>0){
        		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
        			Xt_Generator_Search_Filed xt_generator_search_filed = xt_generator_search_filedList.get(i);
        			String xt_generator_search_name = xt_generator_search_filed.getXt_generator_search_name();
        			String xt_generator_search_flag = xt_generator_search_filed.getXt_generator_search_flag();
        			String search_type = xt_generator_search_filed.getXt_generator_search_type();
        			if("2".equals(search_type)){//特殊处理数字框
        				sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"_cs and "+xt_generator_search_name+"_cs != '' and "+xt_generator_search_name+" != null\">\r\n");
        				sb.append("\t\t\t<choose>\r\n");
        				sb.append("\t\t\t\t<!-- 等于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 1\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" = #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 大于等于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 2\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &gt;= #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 小于等于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 3\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &lt;= #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 大于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 4\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &gt; #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 小于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 5\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &lt; #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t</choose>\r\n");
        				sb.append("\t\t</if>\r\n");
        			}else if("4".equals(search_type)){//特殊处理日期查询
        				sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"_st and "+xt_generator_search_name+"_st != ''\">\r\n");
        				sb.append("\t\t\tAND "+xt_generator_search_name+" &gt;=STR_TO_DATE(#{"+xt_generator_search_name+"_st},'%Y-%m-%d')\r\n");
            			sb.append("\t\t</if>\r\n");
            			sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"_et and "+xt_generator_search_name+"_et != ''\">\r\n");
        				sb.append("\t\t\tAND "+xt_generator_search_name+" &lt;=STR_TO_DATE(#{"+xt_generator_search_name+"_et},'%Y-%m-%d')\r\n");
            			sb.append("\t\t</if>\r\n");
        			}else{
        				sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"\">\r\n");
            			if("0".equals(xt_generator_search_flag)){
            				//模糊查询
            				sb.append("\t\t\tAND instr("+xt_generator_search_name+",#{"+xt_generator_search_name+"})\r\n");
            			}else if("1".equals(xt_generator_search_flag)){
            				//精确查找
            				sb.append("\t\t\tAND "+xt_generator_search_name+" = #{"+xt_generator_search_name+"}\r\n");
            			}
            			sb.append("\t\t</if>\r\n");
        			}
        		}
        	}
    	}
    	//操作子表 通过外键查询集合
    	if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
    		sb.append("\t\t<if test = \"null != "+xt_Generator.getFk()+"\">\r\n");
    		sb.append("\t\t\tAND "+xt_Generator.getFk()+" = #{"+xt_Generator.getFk()+"}\r\n");
    		sb.append("\t\t</if>\r\n");
    	}
    	//分页模块
    	if(xt_Generator.getXt_generator_page().equals("2")){
    		//普通分页
        	sb.append("\t\tLIMIT #{offset},#{pageSize}\r\n");
    	}else{
    		//物理分页
    	}
    	//追加标签结束
    	sb.append("\t</select>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中统计方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisListCount(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--初始化分页统计-->");
    	//追加标签开始
    	sb.append("\r\n\t<select id=\"get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListCountByCondition\" resultType=\"Integer\" parameterType=\"map\">");
    	//追加SELECT开头
    	sb.append("\r\n \t\tSELECT \r\n");
    	//追加统计
    	sb.append("	\t\tCOUNT(0)\r\n");
		//追加FROM
    	sb.append("\t\tFROM \r\n\t\t\t"+xt_Generator.getXt_generator_tbname()+"\r\n");
		//追加条件模块
    	sb.append("\t\tWHERE 1=1 \r\n");
    	if(xt_Generator.isIs_main_table()){
    		List<Xt_Generator_Search_Filed> xt_generator_search_filedList = xt_Generator.getXt_generator_search_filedList();
        	if(!xt_generator_search_filedList.isEmpty() && xt_generator_search_filedList.size()>0){
        		for(int i = 0; i < xt_generator_search_filedList.size(); i++){
        			Xt_Generator_Search_Filed xt_generator_search_filed = xt_generator_search_filedList.get(i);
        			String xt_generator_search_name = xt_generator_search_filed.getXt_generator_search_name();
        			String xt_generator_search_flag = xt_generator_search_filed.getXt_generator_search_flag();
        			String search_type = xt_generator_search_filed.getXt_generator_search_type();
        			if("2".equals(search_type)){//特殊处理数字框
        				sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"_cs and "+xt_generator_search_name+"_cs != '' and "+xt_generator_search_name+" != null\">\r\n");
        				sb.append("\t\t\t<choose>\r\n");
        				sb.append("\t\t\t\t<!-- 等于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 1\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" = #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 大于等于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 2\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &gt;= #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 小于等于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 3\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &lt;= #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 大于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 4\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &gt; #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t\t<!-- 小于 -->\r\n");
        				sb.append("\t\t\t\t<when test=\""+xt_generator_search_name+"_cs == 5\">\r\n");
        				sb.append("\t\t\t\t\tAND "+xt_generator_search_name+" &lt; #{"+xt_generator_search_name+"}\r\n");
        				sb.append("\t\t\t\t</when>\r\n");
        				sb.append("\t\t\t</choose>\r\n");
        				sb.append("\t\t</if>\r\n");
        			}else if("4".equals(search_type)){//特殊处理日期查询
        				sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"_st and "+xt_generator_search_name+"_st != ''\">\r\n");
        				sb.append("\t\t\tAND "+xt_generator_search_name+" &gt;=STR_TO_DATE(#{"+xt_generator_search_name+"_st},'%Y-%m-%d')\r\n");
            			sb.append("\t\t</if>\r\n");
            			sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"_et and "+xt_generator_search_name+"_et != ''\">\r\n");
        				sb.append("\t\t\tAND "+xt_generator_search_name+" &lt;=STR_TO_DATE(#{"+xt_generator_search_name+"_et},'%Y-%m-%d')\r\n");
            			sb.append("\t\t</if>\r\n");
        			}else{
        				sb.append("\t\t<if test=\"null != "+xt_generator_search_name+"\">\r\n");
            			if("0".equals(xt_generator_search_flag)){
            				//模糊查询
            				sb.append("\t\t\tAND instr("+xt_generator_search_name+",#{"+xt_generator_search_name+"})\r\n");
            			}else if("1".equals(xt_generator_search_flag)){
            				//精确查找
            				sb.append("\t\t\tAND "+xt_generator_search_name+" = #{"+xt_generator_search_name+"}\r\n");
            			}
            			sb.append("\t\t</if>\r\n");
        			}
        		}
        	}
    	}
    	//追加标签结束
    	sb.append("\t</select>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中查询对象方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisObj(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--查询对象-->");
    	//追加标签开始
    	sb.append("\r\n\t<select id=\"get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById\" resultType=\""+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"\" parameterType=\"string\">");
    	//追加SELECT开头
    	sb.append("\r\n\t\tSELECT\r\n");
    	//追加COLUMN列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(i == xt_Generator_Table_ColumnList.size()-1){
    			if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("datetime")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%Y-%m-%d %H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("date")){
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("time")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}else{
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    			}
    		}else{
    			if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("datetime")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%Y-%m-%d %H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("date")){
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}else if(xt_Generator_Table_Column.getDATA_TYPE().equalsIgnoreCase("time")){
    				sb.append("\t\t\tDATE_FORMAT(`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,'%H:%i:%s') AS `"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}else{
    				sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    			}
    		}
    	}
		//追加FROM
    	sb.append("\t\tFROM \r\n\t\t\t"+xt_Generator.getXt_generator_tbname()+"\r\n");
		//追加条件模块
    	sb.append("\t\tWHERE "+getColumnKey(xt_Generator_Table_ColumnList)+"=#{"+getColumnKey(xt_Generator_Table_ColumnList)+"}\r\n");
    	//追加标签结束
    	sb.append("\t</select>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中添加方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisAdd(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--添加-->");
    	//追加标签开始
    	sb.append("\r\n\t<insert id=\"add"+uprepchar(xt_Generator.getXt_generator_tbname())+"\" parameterType=\""+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"\">");
    	//追加INSERT语句
    	sb.append("\r\n\t\tINSERT INTO\r\n");
    	sb.append("\t\t\t"+xt_Generator.getXt_generator_tbname()+"\r\n\t\t\t(\r\n");
    	//追加列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(i == xt_Generator_Table_ColumnList.size()-1){
    			sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    		}else{
    			sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    		}
    	}
    	sb.append("\t\t\t)\r\n");
    	sb.append("\t\t\tVALUES\r\n");
    	sb.append("\t\t\t(\r\n");
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(i == xt_Generator_Table_ColumnList.size()-1){
    			sb.append("\t\t\t#{"+xt_Generator_Table_Column.getCOLUMN_NAME()+"}\r\n");
    		}else{
    			sb.append("\t\t\t#{"+xt_Generator_Table_Column.getCOLUMN_NAME()+"},\r\n");
    		}
    	}
    	sb.append("\t\t\t)\r\n");
    	sb.append("\t</insert>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中修改方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisUpdate(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--修改-->");
    	//追加标签开始
    	sb.append("\r\n\t<update id=\"update"+uprepchar(xt_Generator.getXt_generator_tbname())+"\" parameterType=\""+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"\">");
    	//追加UPDATE语句
    	sb.append("\r\n\t\tUPDATE\r\n");
    	sb.append("\t\t\t"+xt_Generator.getXt_generator_tbname()+"");
    	sb.append("\r\n\t\tSET\r\n");
    	//追加列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(!getColumnKey(xt_Generator_Table_ColumnList).equals(xt_Generator_Table_Column.getCOLUMN_NAME())){
	    		if(i == xt_Generator_Table_ColumnList.size()-1){
	    			sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"` = #{"+xt_Generator_Table_Column.getCOLUMN_NAME()+"}\r\n");
	    		}else{
	    			sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"` = #{"+xt_Generator_Table_Column.getCOLUMN_NAME()+"},\r\n");
	    		}
    		}
    	}
    	//追加WHERE条件
    	sb.append("\t\tWHERE "+getColumnKey(xt_Generator_Table_ColumnList)+"=#{"+getColumnKey(xt_Generator_Table_ColumnList)+"}\r\n");
    	sb.append("\t</update>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中修改方法（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisUpdateBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--修改（根据动态条件）-->");
    	//追加标签开始
    	sb.append("\r\n\t<update id=\"update"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective\" parameterType=\""+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"\">");
    	//追加UPDATE语句
    	sb.append("\r\n\t\tUPDATE\r\n");
    	sb.append("\t\t\t"+xt_Generator.getXt_generator_tbname()+"");
    	sb.append("\r\n\t\t<set>\r\n");
    	//追加列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(!getColumnKey(xt_Generator_Table_ColumnList).equals(xt_Generator_Table_Column.getCOLUMN_NAME())){
    			sb.append("\t\t\t<if test=\""+xt_Generator_Table_Column.getCOLUMN_NAME()+" != null\">\r\n");
    			sb.append("\t\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"` = #{"+xt_Generator_Table_Column.getCOLUMN_NAME()+"},\r\n");
    			sb.append("\t\t\t</if>\r\n");
    		}
    	}
    	sb.append("\t\t</set>\r\n");
    	//追加WHERE条件
    	sb.append("\t\tWHERE "+getColumnKey(xt_Generator_Table_ColumnList)+"=#{"+getColumnKey(xt_Generator_Table_ColumnList)+"}\r\n");
    	sb.append("\t</update>\r\n");
    	return sb.toString();
    }
    /**
     * 创建MyBatis文件中删除方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisDel(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--删除-->");
    	//追加标签开始
    	sb.append("\r\n\t<delete id=\"del"+uprepchar(xt_Generator.getXt_generator_tbname())+"\" parameterType=\"map\">");
    	//追加DELETE语句
    	sb.append("\r\n\t\tDELETE FROM "+xt_Generator.getXt_generator_tbname()+" WHERE "+getColumnKey(xt_Generator_Table_ColumnList)+" IN");
    	//追加foreach
    	sb.append("\r\n\t\t<foreach item=\"item\" index=\"index\" collection=\""+getColumnKey(xt_Generator_Table_ColumnList)+"\" open=\"(\" separator=\",\" close=\")\">");
    	sb.append("\r\n\t\t\t#{item}");
    	sb.append("\r\n\t\t</foreach>\r\n");
    	//追加标签结束
    	sb.append("\t</delete>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中批量添加方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisAddBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--批量添加-->");
    	//追加标签开始
    	sb.append("\r\n\t<insert id=\"addBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"\" parameterType=\"list\">");
    	//追加INSERT语句
    	sb.append("\r\n\t\tINSERT INTO\r\n");
    	sb.append("\t\t\t"+xt_Generator.getXt_generator_tbname()+"\r\n\t\t\t(\r\n");
    	//追加列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(i == xt_Generator_Table_ColumnList.size()-1){
    			sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`\r\n");
    		}else{
    			sb.append("\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"`,\r\n");
    		}
    	}
    	sb.append("\t\t\t)\r\n");
    	sb.append("\t\t\tVALUES\r\n");
    	sb.append("\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\r\n");
    	sb.append("\t\t\t(\r\n");
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(i == xt_Generator_Table_ColumnList.size()-1){
    			sb.append("\t\t\t#{item."+xt_Generator_Table_Column.getCOLUMN_NAME()+"}\r\n");
    		}else{
    			sb.append("\t\t\t#{item."+xt_Generator_Table_Column.getCOLUMN_NAME()+"},\r\n");
    		}
    	}
    	sb.append("\t\t\t)\r\n");
    	sb.append("\t\t</foreach>\r\n");
    	sb.append("\t</insert>\r\n");
    	return sb.toString();
    }
    
    /**
     * 创建MyBatis文件中批量修改方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisUpdateBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--批量修改-->");
    	//追加标签开始
    	sb.append("\r\n\t<update id=\"updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"\" parameterType=\"list\">");
    	sb.append("\r\n\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"\" close=\"\" separator=\";\">\r\n"); 
    	//追加UPDATE语句
    	sb.append("\t\t\tUPDATE\r\n");
    	sb.append("\t\t\t\t"+xt_Generator.getXt_generator_tbname()+"");
    	sb.append("\r\n\t\t\tSET\r\n");
    	//追加列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(!getColumnKey(xt_Generator_Table_ColumnList).equals(xt_Generator_Table_Column.getCOLUMN_NAME())){
    			if(i == xt_Generator_Table_ColumnList.size()-1){
        			sb.append("\t\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"` = #{item."+xt_Generator_Table_Column.getCOLUMN_NAME()+"}\r\n");
        		}else{
        			sb.append("\t\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"` = #{item."+xt_Generator_Table_Column.getCOLUMN_NAME()+"},\r\n");
        		}
    		}
    	}
    	//追加WHERE条件
    	sb.append("\t\t\tWHERE "+getColumnKey(xt_Generator_Table_ColumnList)+"=#{item."+getColumnKey(xt_Generator_Table_ColumnList)+"}\r\n");
    	sb.append("\t\t</foreach>\r\n");
    	sb.append("\t</update>\r\n");
    	return sb.toString();
    	
    	
    }
    /**
     * 创建MyBatis文件中批量修改方法（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisUpdateBatchBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--批量修改（根据动态条件）-->");
    	//追加标签开始
    	sb.append("\r\n\t<update id=\"updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective\" parameterType=\"list\">");
    	sb.append("\r\n\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"\" close=\"\" separator=\";\">\r\n"); 
    	//追加UPDATE语句
    	sb.append("\t\t\tUPDATE\r\n");
    	sb.append("\t\t\t\t"+xt_Generator.getXt_generator_tbname()+"");
    	sb.append("\r\n\t\t\t<set>\r\n");
    	//追加列
    	for(int i = 0; i < xt_Generator_Table_ColumnList.size(); i++){
    		Xt_Generator_Table_Column xt_Generator_Table_Column = xt_Generator_Table_ColumnList.get(i);
    		if(!getColumnKey(xt_Generator_Table_ColumnList).equals(xt_Generator_Table_Column.getCOLUMN_NAME())){
    			sb.append("\t\t\t\t<if test=\""+xt_Generator_Table_Column.getCOLUMN_NAME()+" != null\">\r\n");
    			sb.append("\t\t\t\t\t`"+xt_Generator_Table_Column.getCOLUMN_NAME()+"` = #{item."+xt_Generator_Table_Column.getCOLUMN_NAME()+"},\r\n");
    			sb.append("\t\t\t\t</if>\r\n");
    		}
    	}
    	sb.append("\t\t\t</set>\r\n");
    	//追加WHERE条件
    	sb.append("\t\t\tWHERE "+getColumnKey(xt_Generator_Table_ColumnList)+"=#{item."+getColumnKey(xt_Generator_Table_ColumnList)+"}\r\n");
    	sb.append("\t\t</foreach>\r\n");
    	sb.append("\t</update>\r\n");
    	return sb.toString();
    }
    /**
     * 创建MyBatis文件中根据外键删除方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createMyBatisDelByForeignKey(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//追加注释
    	sb.append("\r\n\t<!--根据外键删除-->");
    	//追加标签开始
    	sb.append("\r\n\t<delete id=\"del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey\" parameterType=\"string\">");
    	//追加DELETE语句
    	sb.append("\r\n\t\tDELETE FROM "+xt_Generator.getXt_generator_tbname()+" WHERE "+xt_Generator.getFk()+" = #{"+xt_Generator.getFk()+"}\r\n");
    	//追加标签结束
    	sb.append("\t</delete>\r\n");
    	return sb.toString();
    }
    //////////////////////////////////////1.生成MyBatis部分结束//////////////////////////////////////////
    
    
    //////////////////////////////////////2.生成数据层接口(Dao)部分开始//////////////////////////////////////////
    public String createDao(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+xt_Generator.getXt_generator_dao_package()+";\r\n");
    	//导入包名
    	sb.append("import java.util.List;\r\n");
    	sb.append("import java.util.Map;\r\n");
    	sb.append("import "+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+";\r\n");
        sb.append("\r\n");
        //注释部分
        sb.append("/**\r\n");
        sb.append("* "+xt_Generator.getXt_generator_tbcomment()+" \r\n");
        sb.append("* "+sdf.format(new Date())+"  "+xt_Generator.getXt_userinfo_realName()+"\r\n");
        sb.append("*/");
        //接口模块
        sb.append("\r\npublic interface " + toUpperCase(xt_Generator.getXt_generator_tbname())+"Dao" + "{\r\n");
        //接口中方法
        //2.1分页
        sb.append(createDaoList(xt_Generator_Table_ColumnList,xt_Generator));
        if(xt_Generator.getXt_generator_page().equals("2")){
	        //2.2统计
	        sb.append(createDaoListCount(xt_Generator_Table_ColumnList,xt_Generator));
        }
        //2.3查询对象
        sb.append(createDaoObj(xt_Generator_Table_ColumnList,xt_Generator));
        //2.4添加
        sb.append(createDaoAdd(xt_Generator_Table_ColumnList,xt_Generator));
        //2.5修改
        sb.append(createDaoUpdate(xt_Generator_Table_ColumnList,xt_Generator));
        //2.6修改（根据动态条件） 
        sb.append(createDaoUpdateBySelective(xt_Generator_Table_ColumnList,xt_Generator));
        //2.7删除
        sb.append(createDaoDel(xt_Generator_Table_ColumnList,xt_Generator));
        //2.8批量添加、修改
        if(xt_Generator.getXt_generator_batch_add_update().equals("1") && xt_Generator.isIs_main_table()){
        	sb.append(createDaoAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        }
        //如果是一对多子表类型 并且当前表为子表 则必须生成批量添加 批量修改两个模块
		if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
			sb.append(createDaoAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        	//同时生成根据外键删除
        	sb.append(createDaoDelByForeignKey(xt_Generator_Table_ColumnList, xt_Generator));
		}
        sb.append("}\r\n");
        String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + "Dao.java"),"UTF-8");
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
     * 2.1分页
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoList(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 分页\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition(Map<String,Object> condition);\r\n");
    	return sb.toString();
    }
    /**
     * 2.2统计
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoListCount(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 统计\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListCountByCondition(Map<String,Object> condition);\r\n");
    	return sb.toString();
    }
    /**
     * 2.3查询对象
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoObj(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 查询对象\r\n");
        sb.append("\t* @param "+getColumnKey(xt_Generator_Table_ColumnList)+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic "+toUpperCase(xt_Generator.getXt_generator_tbname())+" get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById(String "+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
    	return sb.toString();
    }
    /**
     * 2.4添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoAdd(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 添加\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	return sb.toString();
    }
    /**
     * 2.5修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoUpdate(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 修改\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int update"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	return sb.toString();
    }
    /**
     * 2.6修改（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoUpdateBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 修改（根据动态条件）\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int update"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	return sb.toString();
    }
    /**
     * 2.7删除
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoDel(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 删除\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(Map<String,Object> condition);\r\n");
    	return sb.toString();
    }
    /**
     * 2.8批量添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoAddBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量添加\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int addBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	return sb.toString();
    }
    /**
     * 2.9批量修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoUpdateBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量修改\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	return sb.toString();
    }
    
    /**
     * 2.10批量修改（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoUpdateBatchBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量修改（根据动态条件）\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	return sb.toString();
    }
    
    /**
     * 2.11根据外键删除方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoDelByForeignKey(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 根据外键删除方法\r\n");
    	sb.append("\t* @param "+xt_Generator.getFk()+"\r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey(String "+xt_Generator.getFk()+");\r\n");
    	return sb.toString();
    }
    //////////////////////////////////////2.生成数据层接口(Dao)部分结束//////////////////////////////////////////
    
    
    
    
    //////////////////////////////////////3.生成数据层实现类(DaoImpl)部分开始//////////////////////////////////////////
    public String createDaoImpl(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+xt_Generator.getXt_generator_dao_package()+".impl;\r\n");
    	//导入包名
    	sb.append("import java.util.List;\r\n");
    	sb.append("import java.util.Map;\r\n");
    	sb.append("import org.springframework.stereotype.Repository;\r\n");
    	sb.append("import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;\r\n");
    	sb.append("import "+xt_Generator.getXt_generator_dao_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"Dao;\r\n");
    	sb.append("import "+xt_Generator.getXt_generator_model_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+";\r\n");
        sb.append("\r\n");
        //注释部分
        sb.append("/**\r\n");
        sb.append("* "+xt_Generator.getXt_generator_tbcomment()+" \r\n");
        sb.append("* "+sdf.format(new Date())+"  "+xt_Generator.getXt_userinfo_realName()+"\r\n");
        sb.append("*/");
        //接口实现类模块
        //注解
        sb.append("\r\n@Repository(\""+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao\")");
        sb.append("\r\npublic class " + toUpperCase(xt_Generator.getXt_generator_tbname())+"DaoImpl "+" extends BaseDaoImpl implements " +toUpperCase(xt_Generator.getXt_generator_tbname())+"Dao"+"{\r\n");
        //接口中方法
        //3.1分页
        sb.append(createDaoImplList(xt_Generator_Table_ColumnList,xt_Generator));
        if(xt_Generator.getXt_generator_page().equals("2")){
	        //3.2统计
	        sb.append(createDaoImplListCount(xt_Generator_Table_ColumnList,xt_Generator));
        }
        //3.3查询对象
        sb.append(createDaoImplObj(xt_Generator_Table_ColumnList,xt_Generator));
        //3.4添加
        sb.append(createDaoImplAdd(xt_Generator_Table_ColumnList,xt_Generator));
        //3.5修改
        sb.append(createDaoImplUpdate(xt_Generator_Table_ColumnList,xt_Generator));
        //3.6修改（根据动态条件）
        sb.append(createDaoImplUpdateBySelective(xt_Generator_Table_ColumnList,xt_Generator));
        //3.7删除
        sb.append(createDaoImplDel(xt_Generator_Table_ColumnList,xt_Generator));
        //3.8批量添加、修改
        if(xt_Generator.getXt_generator_batch_add_update().equals("1") && xt_Generator.isIs_main_table()){
        	sb.append(createDaoImplAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoImplUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoImplUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        }
        //如果是一对多子表类型 并且当前表为子表 则必须生成批量添加 批量修改两个模块
		if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
			sb.append(createDaoImplAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoImplUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createDaoImplUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        	//同时生成根据外键删除
        	sb.append(createDaoImplDelByForeignKey(xt_Generator_Table_ColumnList, xt_Generator));
		}
        sb.append("}\r\n");
        String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + "DaoImpl.java"),"UTF-8");
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
     * 3.1分页
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplList(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 分页\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
        sb.append("\t@SuppressWarnings(\"unchecked\")\r\n");
    	sb.append("\tpublic List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition(Map<String,Object> condition){\r\n");
    	//执行方法
    	sb.append("\t\treturn (List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+">)this.getList(\"get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition\",condition);\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.2统计
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplListCount(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 统计\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListCountByCondition(Map<String,Object> condition){\r\n");
    	//执行方法
    	sb.append("\t\treturn new Integer(this.get(\"get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListCountByCondition\",condition).toString());\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.3查询对象
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplObj(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 查询对象\r\n");
        sb.append("\t* @param "+getColumnKey(xt_Generator_Table_ColumnList)+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic "+toUpperCase(xt_Generator.getXt_generator_tbname())+" get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById(String "+getColumnKey(xt_Generator_Table_ColumnList)+"){\r\n");
    	sb.append("\t\treturn ("+toUpperCase(xt_Generator.getXt_generator_tbname())+")this.get(\"get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById\", "+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.4添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplAdd(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 添加\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+"){\r\n");
    	sb.append("\t\treturn this.add(\"add"+uprepchar(xt_Generator.getXt_generator_tbname())+"\", "+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.5修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplUpdate(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 修改\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //修改方法
    	sb.append("\tpublic int update"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+"){\r\n");
    	sb.append("\t\treturn this.update(\"update"+uprepchar(xt_Generator.getXt_generator_tbname())+"\", "+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.6修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplUpdateBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 修改（根据动态条件）\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //修改方法（根据动态条件）
    	sb.append("\tpublic int update"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+"){\r\n");
    	sb.append("\t\treturn this.update(\"update"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective\", "+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.7删除
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplDel(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 删除\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(Map<String,Object> condition){\r\n");
    	sb.append("\t\treturn this.del(\"del"+uprepchar(xt_Generator.getXt_generator_tbname())+"\", condition);\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    
    /**
     * 3.8批量添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplAddBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量添加\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int addBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List){\r\n");
    	sb.append("\t\treturn this.add(\"addBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"\", "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    
    /**
     * 3.9批量修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplUpdateBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量修改\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List){\r\n");
    	sb.append("\t\treturn this.update(\"updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"\", "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    
    /**
     * 3.10批量修改（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplUpdateBatchBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量修改（根据动态条件）\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List){\r\n");
    	sb.append("\t\treturn this.update(\"updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective\", "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 3.11根据外键删除方法
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createDaoImplDelByForeignKey(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 根据外键删除方法\r\n");
        sb.append("\t* @param "+xt_Generator.getFk()+"\r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey(String "+xt_Generator.getFk()+"){\r\n");
    	sb.append("\t\treturn this.del(\"del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey\", "+xt_Generator.getFk()+");\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    //////////////////////////////////////3.生成数据层实现类(DaoImpl)部分结束//////////////////////////////////////////
}
