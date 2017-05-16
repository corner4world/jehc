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
import jehc.xtmodules.xtmodel.Xt_Generator;
import jehc.xtmodules.xtmodel.Xt_Generator_TableMany_To_One;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;

/**
 * 生成业务层代码(包括Service,ServiceImpl)
 * @author邓纯杰
 *
 */
public class GeneratorService extends GeneratorUtil{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 创建所有数据层相关(Service,ServiceImpl)
	 * @param xt_Generator_Table_ColumnList
	 * @param xt_Generator
	 */
	 public void createServiceAll(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
		//Service接口
    	createService(xt_Generator_Table_ColumnList, xt_Generator);
    	//Service实现类
    	createServiceImpl(xt_Generator_Table_ColumnList, xt_Generator);
     }
    
    //////////////////////////////////////1.生成业务层接口(Service)部分开始//////////////////////////////////////////
    public String createService(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+xt_Generator.getXt_generator_service_package()+";\r\n");
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
        sb.append("\r\npublic interface " + toUpperCase(xt_Generator.getXt_generator_tbname())+"Service" + "{\r\n");
        //接口中方法
        //1.1分页
        sb.append(createServiceList(xt_Generator_Table_ColumnList,xt_Generator));
        if(xt_Generator.getXt_generator_page().equals("2")){
        	//1.2统计
            sb.append(createServiceListCount(xt_Generator_Table_ColumnList,xt_Generator));
        }
        //1.3查询对象
        sb.append(createServiceObj(xt_Generator_Table_ColumnList,xt_Generator));
        //1.4添加
        sb.append(createServiceAdd(xt_Generator_Table_ColumnList,xt_Generator));
        //1.5修改
        sb.append(createServiceUpdate(xt_Generator_Table_ColumnList,xt_Generator));
        //1.6修改（根据动态条件）
        sb.append(createServiceUpdateBySelective(xt_Generator_Table_ColumnList,xt_Generator));
        //1.7删除
        sb.append(createServiceDel(xt_Generator_Table_ColumnList,xt_Generator));
        //1.8批量添加、修改
        if(xt_Generator.getXt_generator_batch_add_update().equals("1") && xt_Generator.isIs_main_table()){
        	sb.append(createServiceAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createServiceUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	//批量修改（根据动态条件）
        	sb.append(createServiceUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        }
        //如果是一对多子表类型 并且当前表为子表 则必须生成批量添加 批量修改两个模块
		if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
			sb.append(createServiceAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createServiceUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	//批量修改（根据动态条件）
        	sb.append(createServiceUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        	//同时生成根据外键删除
        	sb.append(createServiceDelByForeignKey(xt_Generator_Table_ColumnList, xt_Generator));
		}
        sb.append("}\r\n");
        String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + "Service.java"),"UTF-8");
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
     * 1.1分页
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceList(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.2统计
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceListCount(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.3查询对象
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceObj(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.4添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceAdd(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.5修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceUpdate(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.6修改（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceUpdateBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.7删除
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceDel(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.8添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceAddBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.9修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceUpdateBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.10修改（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceUpdateBatchBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
     * 1.11根据外键删除
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceDelByForeignKey(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 根据外键删除\r\n");
    	sb.append("\t* @param "+xt_Generator.getFk()+"\r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey(String "+xt_Generator.getFk()+");\r\n");
    	return sb.toString();
    }
    //////////////////////////////////////1.生成业务层接口(Service)部分结束//////////////////////////////////////////
    
    
    
    
    //////////////////////////////////////2.生成业务层实现类(ServiceImpl)部分开始//////////////////////////////////////////
    public String createServiceImpl(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+xt_Generator.getXt_generator_service_package()+".impl;\r\n");
    	//导入包名
    	sb.append("import java.util.List;\r\n");
    	sb.append("import java.util.Map;\r\n");
    	sb.append("import jehc.xtmodules.xtcore.base.BaseService;\r\n");//导入BaseService类
    	sb.append("import jehc.xtmodules.xtcore.util.ExceptionUtil;\r\n");//导入ExceptionUtil类
    	sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
    	sb.append("import org.springframework.stereotype.Service;\r\n");
    	//判断是否为一对多 并且是主表 如果为一对多则导入实体
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("0")){
        		sb.append("import jehc.xtmodules.xtcore.util.JsonUtil;\r\n");
        	}
        	List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("import "+ xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_model_package()+"."+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+";\r\n");
        		sb.append("import "+ xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_service_package()+"."+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service;\r\n");
        	}
        	if(xt_Generator.getOne_to_many_type().equals("1")){
        		sb.append("import java.util.HashMap;\r\n");
        	}
        	sb.append("import java.util.ArrayList;\r\n");//导入ArrayList类
        	sb.append("import jehc.xtmodules.xtcore.allutils.StringUtil;\r\n");//导入StringUitl类
        }
    	sb.append("import "+xt_Generator.getXt_generator_service_package()+"."+toUpperCase(xt_Generator.getXt_generator_tbname())+"Service;\r\n");
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
        sb.append("\r\n@Service(\""+lowfristchar(xt_Generator.getXt_generator_tbname())+"Service\")");
        //add实现类中继承BaseService
        sb.append("\r\npublic class " + toUpperCase(xt_Generator.getXt_generator_tbname())+"ServiceImpl"+" extends BaseService implements " +toUpperCase(xt_Generator.getXt_generator_tbname())+"Service"+"{\r\n");
        //注入Dao接口
        sb.append("\t@Autowired\r\n");
        sb.append("\tprivate "+toUpperCase(xt_Generator.getXt_generator_tbname())+"Dao "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao;\r\n");
        //判断是否为一对多并且是主表 如果为一对多则注解子表接口
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("\t@Autowired\r\n");
        		sb.append("\tprivate "+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service;\r\n");
        	}
        }
        
        //接口中方法
        //2.1分页
        sb.append(createServiceImplList(xt_Generator_Table_ColumnList,xt_Generator));
        if(xt_Generator.getXt_generator_page().equals("2")){
        	//2.2统计
            sb.append(createServiceImplListCount(xt_Generator_Table_ColumnList,xt_Generator));
        }
        //2.3查询对象
        sb.append(createServiceImplObj(xt_Generator_Table_ColumnList,xt_Generator));
        //2.4添加
        sb.append(createServiceImplAdd(xt_Generator_Table_ColumnList,xt_Generator));
        //2.5修改
        sb.append(createServiceImplUpdate(xt_Generator_Table_ColumnList,xt_Generator));
        //2.6修改（根据动态条件） 
        sb.append(createServiceImplUpdateBySelective(xt_Generator_Table_ColumnList,xt_Generator));
        //2.7删除
        sb.append(createServiceImplDel(xt_Generator_Table_ColumnList,xt_Generator));
        //2.8批量添加、修改
        if(xt_Generator.getXt_generator_batch_add_update().equals("1") && xt_Generator.isIs_main_table()){
        	sb.append(createServiceImplAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createServiceImplUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createServiceImplUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        }
        //如果是一对多子表类型 并且当前表为子表 则必须生成批量添加 批量修改两个模块
		if(xt_Generator.getIs_one_to_many().equals("1") && !xt_Generator.isIs_main_table()){
			sb.append(createServiceImplAddBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createServiceImplUpdateBatch(xt_Generator_Table_ColumnList, xt_Generator));
        	sb.append(createServiceImplUpdateBatchBySelective(xt_Generator_Table_ColumnList, xt_Generator));
        	//同时生成根据外键删除
        	sb.append(createServiceImplDelByForeignKey(xt_Generator_Table_ColumnList, xt_Generator));
		}
        sb.append("}\r\n");
        String path = xt_Generator.getXt_generator_path();
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(path+toUpperCase(xt_Generator.getXt_generator_tbname()) + "ServiceImpl.java"),"UTF-8");
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
     * 2.1分页
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplList(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 分页\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition(Map<String,Object> condition){\r\n");
    	//执行方法
    	sb.append("\t\ttry{\r\n");
    	sb.append("\t\t\treturn "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListByCondition(condition)"+";\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.2统计
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplListCount(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
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
    	sb.append("\t\ttry{\r\n");
    	sb.append("\t\t\treturn "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ListCountByCondition(condition)"+";\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.3查询对象
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplObj(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 查询对象\r\n");
        sb.append("\t* @param "+getColumnKey(xt_Generator_Table_ColumnList)+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic "+toUpperCase(xt_Generator.getXt_generator_tbname())+" get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById(String "+getColumnKey(xt_Generator_Table_ColumnList)+"){\r\n");
    	sb.append("\t\ttry{\r\n");
    	sb.append("\t\t\t"+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+" = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.get"+uprepchar(xt_Generator.getXt_generator_tbname())+"ById("+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
    	/////////操作子集合
    	//判断是否为一对多 并且是主表 如果为一对多则导入实体
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
    		if(xt_Generator.getOne_to_many_type().equals("1")){
    			sb.append("\t\t\tMap<String, Object> condition = new HashMap<String, Object>();\r\n");
    			List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
    			for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
    				Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
    				sb.append("\t\t\tcondition.put(\""+(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"\", "+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
    				sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" = "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.get"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"ListByCondition(condition);\r\n");
    				sb.append("\t\t\t"+lowfristchar(xt_Generator.getXt_generator_tbname())+".set"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+");\r\n");
    			}
    		}
    	}
    	sb.append("\t\t\treturn "+lowfristchar(xt_Generator.getXt_generator_tbname())+";\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.4添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplAdd(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 添加\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+"){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.add"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	//判断是否为一对多 如果为一对多则调用子表批量操作
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("1")){
        		//表单添加方式
        		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"TempList = "+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"();\r\n");
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		sb.append("\t\t\tfor(int j = 0; j < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"TempList.size(); j++){\r\n");
            		sb.append("\t\t\t\tif("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag().indexOf(\",\"+j+\",\") == -1){\r\n");
            		//设置外键
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"TempList.get(j).set"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"());\r\n");
            		//设置主键
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"TempList.get(j).set"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"(toUUID());\r\n");
            		//将合法的集合放入即将要操作的集合中
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.add("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"TempList.get(j)"+");\r\n");
            		sb.append("\t\t\t\t}\r\n");
            		sb.append("\t\t\t}\r\n");
            		sb.append("\t\t\tif(!"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.isEmpty()&&"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.size()>0){\r\n");
            		sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.addBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List);\r\n");
            		sb.append("\t\t\t}\r\n");
            	}
        	}else{
        		/////////////列表添加方式
        		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = JsonUtil.toList("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List(), "+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".class);\r\n");
            		sb.append("\t\t\tfor(int j = 0; j < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.size(); j++){\r\n");
            		//对子表主键设置UUID
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).set"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"(toUUID());\r\n");
                	//对子表外键设置值
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).set"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+getColumnKeyUpOneChar(xt_Generator_Table_ColumnList)+"());\r\n");
                	sb.append("\t\t\t}\r\n");
                	sb.append("\t\t\tif(!"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List.isEmpty()&&"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"List.size()>0){\r\n");
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.addBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List);\r\n");
                	sb.append("\t\t\t}\r\n");
            	}
        	}
        }
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.5修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplUpdate(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 修改\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int update"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+"){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.update"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	//判断是否为一对多 如果为一对多则调用子表批量操作 有主键则修改 无主键则添加 添加时候记得将外键及UUID设置进去
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("1")){
        		//表单添加方式
        		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		//获取子表集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = "+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"();\r\n");
            		//定义子表需添加集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		//定义子表需修改集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		//循环子表集合
            		sb.append("\t\t\tfor(int j = 0; j < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.size(); j++){\r\n");
            		sb.append("\t\t\t\tif("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag().indexOf(\",\"+j+\",\") == -1){\r\n");
            		//设置外键
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).set"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"());\r\n");
            		///////////////判断该集合的主键是否存在 如果存在则将其加入到修改集合中否则加入到添加集合中并且设置主键用于新增
            		sb.append("\t\t\t\t\tif(StringUtil.isEmpty("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).get"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"())){\r\n");
            		//设置主键
            		sb.append("\t\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).set"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"(toUUID());\r\n");
            		//将合法的集合放入即将要操作的集合中
            		sb.append("\t\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.add("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j)"+");\r\n");
            		sb.append("\t\t\t\t\t}else{\r\n");
            		sb.append("\t\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.add("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j)"+");\r\n");
            		sb.append("\t\t\t\t\t}\r\n");
            		sb.append("\t\t\t\t}\r\n");
            		sb.append("\t\t\t}\r\n");
            		//操作数据库
            		sb.append("\t\t\tif(!"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.isEmpty()&&"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.size()>0){\r\n");
            		sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.addBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList);\r\n");
            		sb.append("\t\t\t}\r\n");
            		sb.append("\t\t\tif(!"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.isEmpty()&&"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.size()>0){\r\n");
            		sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.updateBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList);\r\n");
            		sb.append("\t\t\t}\r\n");
            	}
        	}else{
        		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = JsonUtil.toList("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List(), "+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".class);\r\n");
            		//定义新增集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		//定义修改集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		sb.append("\t\t\tfor(int j = 0; j < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.size(); j++){\r\n");
            		sb.append("\t\t\t\t"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" = "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j);\r\n");
            		sb.append("\t\t\t\tif(StringUtils.isEmpty("+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".get"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"())){\r\n");
            		sb.append("\t\t\t\t\t"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".set"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"(toUUID());\r\n");
            		sb.append("\t\t\t\t\t"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".set"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+getColumnKeyUpOneChar(xt_Generator_Table_ColumnList)+"())\r\n");
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.add("+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+");\r\n");
            		sb.append("\t\t\t\t}else{\r\n");
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.add("+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+");\r\n");
            		sb.append("\t\t\t\t}\r\n");
                	sb.append("\t\t\t}\r\n");
                	sb.append("\t\t\tif(!"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"AddList.isEmpty()&&"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"AddList.size()>0){\r\n");
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.addBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList);\r\n");
                	sb.append("\t\t\t}\r\n");
            		sb.append("\t\t\tif(!"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"UpdateList.isEmpty()&&"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"UpdateList.size()>0){\r\n");
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.updateBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList);\r\n");
                	sb.append("\t\t\t}\r\n");
            	}
        	}
        }
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    
    /**
     * 2.6修改（根据动态条件） 
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplUpdateBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 修改（根据动态条件）\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+" \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int update"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective("+toUpperCase(xt_Generator.getXt_generator_tbname())+" "+lowfristchar(xt_Generator.getXt_generator_tbname())+"){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.update"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective("+lowfristchar(xt_Generator.getXt_generator_tbname())+");\r\n");
    	//判断是否为一对多 如果为一对多则调用子表批量操作 有主键则修改 无主键则添加 添加时候记得将外键及UUID设置进去
        if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
        	if(xt_Generator.getOne_to_many_type().equals("1")){
        		//表单添加方式
        		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		//获取子表集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = "+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"();\r\n");
            		//定义子表需添加集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		//定义子表需修改集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		//循环子表集合
            		sb.append("\t\t\tfor(int j = 0; j < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.size(); j++){\r\n");
            		sb.append("\t\t\t\tif("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"_removed_flag().indexOf(\",\"+j+\",\") == -1){\r\n");
            		//设置外键
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).set"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"());\r\n");
            		///////////////判断该集合的主键是否存在 如果存在则将其加入到修改集合中否则加入到添加集合中并且设置主键用于新增
            		sb.append("\t\t\t\t\tif(StringUtil.isEmpty("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).get"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"())){\r\n");
            		//设置主键
            		sb.append("\t\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j).set"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"(toUUID());\r\n");
            		//将合法的集合放入即将要操作的集合中
            		sb.append("\t\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.add("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j)"+");\r\n");
            		sb.append("\t\t\t\t\t}else{\r\n");
            		sb.append("\t\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.add("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j)"+");\r\n");
            		sb.append("\t\t\t\t\t}\r\n");
            		sb.append("\t\t\t\t}\r\n");
            		sb.append("\t\t\t}\r\n");
            		//操作数据库
            		sb.append("\t\t\tif(!"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.isEmpty()&&"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.size()>0){\r\n");
            		sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.addBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList);\r\n");
            		sb.append("\t\t\t}\r\n");
            		sb.append("\t\t\tif(!"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.isEmpty()&&"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.size()>0){\r\n");
            		sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.updateBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"BySelective("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList);\r\n");
            		sb.append("\t\t\t}\r\n");
            	}
        	}else{
        		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
            	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
            		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List = JsonUtil.toList("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List(), "+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".class);\r\n");
            		//定义新增集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		//定义修改集合
            		sb.append("\t\t\tList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"> "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList = new ArrayList<"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+">();\r\n");
            		sb.append("\t\t\tfor(int j = 0; j < "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.size(); j++){\r\n");
            		sb.append("\t\t\t\t"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+" = "+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"List.get(j);\r\n");
            		sb.append("\t\t\t\tif(StringUtils.isEmpty("+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".get"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"())){\r\n");
            		sb.append("\t\t\t\t\t"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".set"+getOneToManyColumnKeyUpOneChar(xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList())+"(toUUID());\r\n");
            		sb.append("\t\t\t\t\t"+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+".set"+initcap(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+".get"+getColumnKeyUpOneChar(xt_Generator_Table_ColumnList)+"())\r\n");
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList.add("+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+");\r\n");
            		sb.append("\t\t\t\t}else{\r\n");
            		sb.append("\t\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList.add("+toUpperCase(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+");\r\n");
            		sb.append("\t\t\t\t}\r\n");
                	sb.append("\t\t\t}\r\n");
                	sb.append("\t\t\tif(!"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"AddList.isEmpty()&&"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"AddList.size()>0){\r\n");
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.addBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"AddList);\r\n");
                	sb.append("\t\t\t}\r\n");
            		sb.append("\t\t\tif(!"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"UpdateList.isEmpty()&&"+xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name()+"UpdateList.size()>0){\r\n");
                	sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.updateBatch"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"BySelective("+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"UpdateList);\r\n");
                	sb.append("\t\t\t}\r\n");
            	}
        	}
        }
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    
    /**
     * 2.7删除
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplDel(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 删除\r\n");
        sb.append("\t* @param condition \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(Map<String,Object> condition){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.del"+uprepchar(xt_Generator.getXt_generator_tbname())+"(condition);\r\n");
    	/////////删除子表
    	if(xt_Generator.getIs_one_to_many().equals("1") && xt_Generator.isIs_main_table()){
    		List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
    		sb.append("\t\t\tString[] "+getColumnKey(xt_Generator_Table_ColumnList)+"List= (String[])condition.get(\""+getColumnKey(xt_Generator_Table_ColumnList)+"\");\r\n");
    		sb.append("\t\t\tfor(String "+getColumnKey(xt_Generator_Table_ColumnList)+":"+getColumnKey(xt_Generator_Table_ColumnList)+"List){\r\n");
        	for(int i = 0; i < xt_Generator_TableMany_To_OneList.size(); i++){
        		Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(i);
        		sb.append("\t\t\t\t"+lowfristchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"Service.del"+uprepchar(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name())+"ByForeignKey("+getColumnKey(xt_Generator_Table_ColumnList)+");\r\n");
        	}
    		sb.append("\t\t\t}\r\n");
    	}
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.8批量添加
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplAddBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量添加\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int addBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.addBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.9批量修改
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplUpdateBatch(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量修改\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"("+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.10批量修改（根据动态条件）
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplUpdateBatchBySelective(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 批量修改（根据动态条件）\r\n");
        sb.append("\t* @param "+xt_Generator.getXt_generator_tbname()+"List \r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective(List<"+toUpperCase(xt_Generator.getXt_generator_tbname())+"> "+lowfristchar(xt_Generator.getXt_generator_tbname())+"List){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.updateBatch"+uprepchar(xt_Generator.getXt_generator_tbname())+"BySelective("+lowfristchar(xt_Generator.getXt_generator_tbname())+"List);\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    /**
     * 2.11根据外键删除
     * @param xt_Generator_Table_ColumnList
     * @param xt_Generator
     * @return
     */
    public String createServiceImplDelByForeignKey(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	StringBuffer sb = new StringBuffer();
    	//添加注释
    	sb.append("\t/**\r\n");
    	sb.append("\t* 根据外键删除方法\r\n");
    	sb.append("\t* @param "+xt_Generator.getFk()+"\r\n");
        sb.append("\t* @return\r\n");
        sb.append("\t*/\r\n");
        //添加方法
    	sb.append("\tpublic int del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey(String "+xt_Generator.getFk()+"){\r\n");
    	//定义返回值
    	sb.append("\t\tint i = 0;\r\n");
    	//定义cry catch模块
    	sb.append("\t\ttry {\r\n");
    	sb.append("\t\t\ti = "+lowfristchar(xt_Generator.getXt_generator_tbname())+"Dao.del"+uprepchar(xt_Generator.getXt_generator_tbname())+"ByForeignKey("+xt_Generator.getFk()+");\r\n");
    	sb.append("\t\t} catch (Exception e) {\r\n");
    	sb.append("\t\t\ti = 0;\r\n");
    	sb.append("\t\t\t/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/\r\n");
    	sb.append("\t\t\tthrow new ExceptionUtil(e.getMessage(),e.getCause());\r\n");
    	sb.append("\t\t}\r\n");
    	sb.append("\t\treturn i;\r\n");
    	sb.append("\t}\r\n");
    	return sb.toString();
    }
    //////////////////////////////////////2.生成业务层实现类(ServiceImpl)部分结束//////////////////////////////////////////
}
