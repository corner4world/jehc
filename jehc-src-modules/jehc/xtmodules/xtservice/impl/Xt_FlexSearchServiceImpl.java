package jehc.xtmodules.xtservice.impl;

import java.util.List;

import org.hsqldb.lib.StringUtil;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.springutil.GetApplicationContext;
import jehc.xtmodules.xtdao.Xt_DbinfoDao;
import jehc.xtmodules.xtdao.Xt_FlexSearchDao;
import jehc.xtmodules.xtdao.impl.Xt_FlexSearchDaoImpl;
import jehc.xtmodules.xtmodel.Xt_Db_Fun;
import jehc.xtmodules.xtmodel.Xt_Db_Proc;
import jehc.xtmodules.xtmodel.Xt_Db_Structure;
import jehc.xtmodules.xtmodel.Xt_Db_TableAttribute;
import jehc.xtmodules.xtmodel.Xt_Db_TableIndex;
import jehc.xtmodules.xtmodel.Xt_Db_Tri;
import jehc.xtmodules.xtmodel.Xt_Db_View;
import jehc.xtmodules.xtmodel.Xt_Dbinfo;
import jehc.xtmodules.xtservice.Xt_FlexSearchService;
/**
 * 查询工具
 * @author 邓纯杰
 *
 */
public class Xt_FlexSearchServiceImpl extends BaseService implements Xt_FlexSearchService {
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchQuery(String sql,Object[]param,String xt_dbinfo_id){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtFlexSearchQuery(sql, param,xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchListQuery(String sql, Object[] param,String xt_dbinfo_id){
		try {
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			return xt_FlexSearchDao.getXtFlexSearchListQuery(sql, param,xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public String executeUpdate(String sql, Object[] param,String xt_dbinfo_id){
		StringBuffer fieldsNames = new StringBuffer();
		StringBuffer jsonStr = new StringBuffer();
		StringBuffer columModle = new StringBuffer();
        StringBuffer data = new StringBuffer();
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			int result = xt_FlexSearchDao.executeUpdate(sql, param,xt_Dbinfo);
            //fieldsNames
            fieldsNames.append("'fieldsNames':[");
            columModle.append("'columModle':[");
            fieldsNames.append("{name:'result'},");
            String dataIndex = "result";
            String header = "result";
            columModle.append("{'header':'"+header+"','dataIndex':'"+dataIndex+"',flex:1},");
            columModle.append("]");
            fieldsNames.append("]");
            //data
            data.append("'data':[");
            data.append("{");
        	data.append("'result':'执行的影响行数<font color=red>"+result+"</font>条'");
        	data.append("}");
            data.append("]");
            jsonStr.append("{success:true,");
            jsonStr.append(data+",");
            jsonStr.append(columModle+",");
            jsonStr.append(fieldsNames);
            jsonStr.append("}");
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return jsonStr.toString();
	}
	
	////////////////////////////为菜单服务///////////////////////
	/**
	 * 获取所有表
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<Xt_Db_TableAttribute> getXtDbTableAttributeForFlex(String xt_dbinfo_id){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbTableAttributeForFlex(backSql(xt_Dbinfo,6,null), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询表索引
	* @return
	*/
	public List<Xt_Db_TableIndex> getXtDbTableIndexForFlex(String xt_dbinfo_id,String tableName){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbTableIndexForFlex(backSql(xt_Dbinfo,2,tableName), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	* 查询存储过程
	* @return
	*/
	public  List<Xt_Db_Proc> getXtDbProcListForFlex(String xt_dbinfo_id){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbProcListForFlex(backSql(xt_Dbinfo,5,null), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	* 查询函数
	* @param sql
	* @return
	*/
	public  List<Xt_Db_Fun> getXtDbFunListForFlex(String xt_dbinfo_id){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbFunListForFlex(backSql(xt_Dbinfo,3,null), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	* 查询视图
	* @param sql
	* @return
	*/
	public  List<Xt_Db_View> getXtDbViewListForFlex(String xt_dbinfo_id){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbViewListForFlex(backSql(xt_Dbinfo,1,null), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	* 查询触发器
	*/
	public  List<Xt_Db_Tri> getXtDbTriListForFlex(String xt_dbinfo_id){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbTriListForFlex(backSql(xt_Dbinfo,4,null), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	
	/**
	 * 查询字段
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<Xt_Db_Structure> getXtDbStructureForFlex(String xt_dbinfo_id,String tableName){
		try {
			Xt_DbinfoDao xt_DbinfoDao = (Xt_DbinfoDao)GetApplicationContext.getBean("xt_DbinfoDao");
			Xt_Dbinfo xt_Dbinfo = xt_DbinfoDao.getXtDbinfoById(xt_dbinfo_id);
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtDbStructureForFlex(backSql(xt_Dbinfo,7,tableName), xt_Dbinfo);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 返回sql语句
	 * @param xt_Dbinfo
	 * @param flag 1视图 2索引  3函数 4触发器  5存储过程 6表 7字段
	 * @return
	 */
	public String backSql(Xt_Dbinfo xt_Dbinfo,int flag,String tableName){
		String sql = "";
		String dbType = xt_Dbinfo.getXt_dbinfoType();
		String dbName = xt_Dbinfo.getXt_dbinfoName();
		if(StringUtil.isEmpty(dbType)){
			throw new ExceptionUtil("未能获取到数据库类型");
		}
		if("mysql".equals(dbType)){
			if(flag == 1){
				sql = "SELECT * FROM information_schema.tables WHERE table_schema="+dbName+" AND table_type='view'";
			}else if(flag == 2){
				if(StringUtil.isEmpty(tableName)){
					throw new ExceptionUtil("未能获取到数据库表名");
				}
				sql = "show index from "+tableName;
			}else if(flag == 3){
				sql = "SELECT * FROM mysql.proc WHERE db = "+dbName+" AND `type` = 'FUNCTION'";
			}else if(flag == 4){
				sql = "SHOW TRIGGERS FROM "+dbName;
			}else if(flag == 5){
				sql = "SELECT * FROM mysql.proc WHERE db = "+dbName+" AND `type` = 'PROCEDURE'";
			}else if(flag == 6){
				sql = "SHOW TABLE STATUS FROM "+dbName;
			}else if(flag == 7){
				sql = "SHOW FULL FIELDS FROM "+tableName;
			}
		}else if("sqlserver".equals(dbType)){
			if(flag == 1){
				sql = "";
			}else if(flag == 2){
				sql = "";
			}else if(flag == 3){
				sql = "";
			}else if(flag == 4){
				sql = "";
			}else if(flag == 5){
				sql = "";
			}else if(flag == 6){
				sql = "";
			}
		}else if("oracle".equals(dbType)){
			if(flag == 1){
				sql = "";
			}else if(flag == 2){
				sql = "";
			}else if(flag == 3){
				sql = "";
			}else if(flag == 4){
				sql = "";
			}else if(flag == 5){
				sql = "";
			}else if(flag == 6){
				sql = "";
			}
		}else if("sybase".equals(dbType)){
			if(flag == 1){
				sql = "";
			}else if(flag == 2){
				sql = "";
			}else if(flag == 3){
				sql = "";
			}else if(flag == 4){
				sql = "";
			}else if(flag == 5){
				sql = "";
			}else if(flag == 6){
				sql = "";
			}
		}else if("db2".equals(dbType)){
			if(flag == 1){
				sql = "";
			}else if(flag == 2){
				sql = "";
			}else if(flag == 3){
				sql = "";
			}else if(flag == 4){
				sql = "";
			}else if(flag == 5){
				sql = "";
			}else if(flag == 6){
				sql = "";
			}
		}
		return sql;
	}
}
