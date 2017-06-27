package jehc.xtmodules.xtcore.base;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.Xt_Dbinfo;
/**
 * 做自定义表单使用
 * @author邓纯杰
 *
 */
public class DBHelper {
	public static DataSource ds = null;
    public static Properties properties = null;
    static{
        InputStream is = DBHelper.class.getClassLoader().getResourceAsStream("jehc/xtmodules/xtcore/sources/jdbc.properties");
        properties = new Properties();
        try {
        	//properties.getProperty("charSet", System.getProperty("file.encoding"));
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件时出错了",e);
        }
    }
	/**
     * 得到数据源
     *
     */
    public void getDateSource(){
        if (ds == null) {
            synchronized (BasicDataSource.class) {
                if (ds == null) {
                    try {
                        ds= BasicDataSourceFactory.createDataSource(properties);
                    } catch (Exception e) {
                        throw new RuntimeException("创建数据源时出错了",e);
                    }
                }
            }
        }
    }
    /**
     * 返回数据库连接
     * @return connection对象
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            getDateSource();
            connection = ds.getConnection();            
        } catch (SQLException e) {
            throw new RuntimeException("得到链接时出错了",e);
        }
        return connection;
    }
    
    
	/**
     * 执行数据库的非查询操作
     * @param sql 要执行的Sql语句
     * @param param 参数列表
     * @return 受影响的行数
     */
    public int executeUpdate(String sql,Object[]param) {
        int num = 0;
        Connection connection = getConnection();
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement(sql);
            addParamters(param, pStatement);
            num = pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("执行非查询操作时出错了！",e);
        }finally{
        	closeAll(connection, pStatement, null);
        }
        return num;
    }
	/**
     * 为Sql语句的占位符赋值
     * @param param 参数列表
     * @param pStatement pStatement对象
     * @throws SQLException 
     */
    protected void addParamters(Object[] param, PreparedStatement pStatement)
            throws SQLException {
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                pStatement.setObject(i + 1, param[i]);
            }
        }
    }
    /**
     * 重写方法为了使用自定义表单 
     * 执行查询工作
     * @param sql 执行的Sql语句
     * @param param 参数列表
     * @param rp ResultParse接口对象
     * @return 对象的List集合 List对象为Map类型
     */
    @SuppressWarnings("unchecked")
    public <T> List executdQueryForMap(String sql,Object[]param,Class<T> clazz){
        List<Map<String, Object>> ls = null;
        Connection connection = getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            pStatement = connection.prepareStatement(sql);
            addParamters(param, pStatement);
            resultSet = pStatement.executeQuery();
            //获取结果集中的原始数据源
            ResultSetMetaData rsd = resultSet.getMetaData();
            int columnCount = rsd.getColumnCount();
            String[] columnName =new String [columnCount];
            ls = new ArrayList<Map<String, Object>>();
            while(resultSet.next()) {
            	Map<String, Object> map = new HashMap<String, Object>();
            	for(int i = 0; i < columnName.length; i++) {
                    columnName[i]=rsd.getColumnName(i+1).toString();
                    Object value = resultSet.getObject(columnName[i]);
                    map.put(columnName[i], value);
                }
            	ls.add(map);
            }
            return ls;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e.getCause());
        }finally{
            closeAll(connection, pStatement, resultSet);
        }
    }
    
    
    
    
	/**
     * 关闭数据库所有链接
     * @param connection    Connection链接对象
     * @param pStatement    pStatement链接对象
     * @param resultSet        resultSet对象
     */
    protected void closeAll(Connection connection,PreparedStatement pStatement,ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("关闭resultSet时出错了",e);
        }finally{
            try {
                if (pStatement != null) {
                    pStatement.close();                
                }
            } catch (SQLException e) {
                throw new RuntimeException("关闭pStatement时出错了！",e);
            }finally{
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("关闭connection链接时出错了！",e);
                }
            }
        }
    }    
    
    
    ///////////////////////////////////////自定义连接开始（用于flexsearch）//////////////////////////////////////
    /**
     * 获取连接（用于自定义查询如sql查询器）
     * @param xt_dbinfo_id
     * @return
     */
    public static Connection getConnection(Xt_Dbinfo xt_Dbinfo) {
        Connection conn = null;
        try {
        	String xt_dbinfoType = xt_Dbinfo.getXt_dbinfoType();
        	if(StringUtil.isEmpty(xt_dbinfoType)){
        		throw new ExceptionUtil("未能获取到数据库类型");
        	}
        	String dbName = xt_Dbinfo.getXt_dbinfoName();
        	String port = xt_Dbinfo.getXt_dbinfoPort();
        	String ip = xt_Dbinfo.getXt_dbinfoIp();
        	String driver = null;
        	String url = null;
        	String user = xt_Dbinfo.getXt_dbinfoUName();
        	String password = xt_Dbinfo.getXt_dbinfoPwd();
        	//1mysql
        	if(("mysql").equals(xt_dbinfoType)){
        		
        		driver = "com.mysql.jdbc.Driver"; //数据库驱动
                url = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useOldAliasMetadataBehavior=true&amp;useUnicode=true&amp;characterEncoding=utf8&allowMultiQueries=true";//数据库
        	
        	}else if(("sqlserver").equals(xt_dbinfoType)){
        		
        		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //数据库驱动
                url = "jdbc:sqlserver://"+ip+":"+port+";DatabaseName="+dbName;//数据库
                
        	}else if(("oracle").equals(xt_dbinfoType)){
        		
        		driver = "oracle.jdbc.driver.OracleDriver"; //数据库驱动
                url = "jdbc:oracle:thin:@"+ip+":"+port+"/"+dbName;//数据库
        	
        	}else if(("sybase").equals(xt_dbinfoType)){
        		
        		driver = "com.sybase.jdbc2.jdbc.SybDriver"; //数据库驱动
                url = "jdbc:sybase:Tds:"+ip+":"+port+"/"+dbName;//数据库
        	
        	}else if(("db2").equals(xt_dbinfoType)){
        		
        		driver = "com.ibm.db2.jdbc.net.DB2Driver"; //数据库驱动
                url = "jdbc:db2://"+ip+":"+port+"/"+dbName;//数据库
        	
        	}
            Class.forName(driver); //加载数据库驱动
            if (null == conn) {
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未能找到驱动",e);
        } catch (SQLException e) {
            throw new RuntimeException("获取sql连接出现异常",e);
        } catch (Exception e) {
        	throw new RuntimeException("获取连接出现异常",e);
        }
        return conn;
    }
    
    
    /**
     * 重写方法为了使用自定义表单 
     * 执行查询工作
     * @param sql 执行的Sql语句
     * @param param 参数列表
     * @param rp ResultParse接口对象
     * @return 对象的List集合 List对象为Object类型
     */
    @SuppressWarnings("unchecked")
    public <T> List executdQueryForObject(String sql,Object[]param,Class<T> clazz,Xt_Dbinfo xt_Dbinfo){
        List<Object> ls = null;
        Connection connection = getConnection(xt_Dbinfo);
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            pStatement = connection.prepareStatement(sql);
            addParamters(param, pStatement);
            resultSet = pStatement.executeQuery();
            //获取结果集中的原始数据源
            ResultSetMetaData rsd = resultSet.getMetaData();
            int columnCount = rsd.getColumnCount();
            String[] columnName =new String [columnCount];
            ls = new ArrayList<Object>();
            while(resultSet.next()) {
            	Map<String, Object> map = new HashMap<String, Object>();
            	for(int i = 0; i < columnName.length; i++) {
                    columnName[i]=rsd.getColumnName(i+1).toString();
                    Object value = resultSet.getObject(columnName[i]);
                    map.put(columnName[i], value);
                }
            	ls.add(map);
            }
            return ls;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e.getCause());
        }finally{
            closeAll(connection, pStatement, resultSet);
        }
    }
    
    /**
     * 重写方法为了使用自定义表单 
     * 执行查询工作
     * @param sql 执行的Sql语句
     * @param param 参数列表
     * @param rp ResultParse接口对象
     * @return 对象的List集合 List对象为Object类型
     */
    public <T> String executdQueryJosnForFlex(String sql,Object[]param,Class<T> clazz,Xt_Dbinfo xt_Dbinfo){
        Connection connection = getConnection(xt_Dbinfo);
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        StringBuffer jsonStr = new StringBuffer();
        try {
            pStatement = connection.prepareStatement(sql);
            addParamters(param, pStatement);
            resultSet = pStatement.executeQuery();
            //获取结果集中的原始数据源
            ResultSetMetaData rsd = resultSet.getMetaData();
            int columnCount = rsd.getColumnCount();
            String[] columnName =new String [columnCount];
            //JSON展现如下代码
            StringBuffer columModle = new StringBuffer();
            StringBuffer data = new StringBuffer();
            StringBuffer fieldsNames = new StringBuffer();
            
            //fieldsNames
            fieldsNames.append("'fieldsNames':[");
            columModle.append("'columModle':[");
            for(int i = 0; i < columnName.length; i++){
                if(i == (columnName.length-1)){
                	fieldsNames.append("{name:'"+rsd.getColumnName(i+1)+"'}");
                }else{
                	fieldsNames.append("{name:'"+rsd.getColumnName(i+1)+"'},");
                }
                String dataIndex = rsd.getColumnName(i+1);
                String header = rsd.getColumnLabel(i+1);
                if(i == (columnName.length-1)){
                	columModle.append("{'header':'"+header+"','dataIndex':'"+dataIndex+"',locked:true}");
                }else{
                	columModle.append("{'header':'"+header+"','dataIndex':'"+dataIndex+"',locked:true},");
                }
            }
            columModle.append("]");
            fieldsNames.append("]");
            //data
            data.append("'data':[");
            while(resultSet.next()){
            	data.append("{");
            	for(int i = 0; i < columnName.length; i++) {
                    columnName[i]=rsd.getColumnName(i+1).toString();
                    Object value = resultSet.getObject(columnName[i]);
                    if(i == (columnName.length-1)){
                    	data.append("'"+columnName[i]+"':'"+value+"'");
                    }else{
                    	data.append("'"+columnName[i]+"':'"+value+"',");
                    }
                }
            	if(resultSet.isLast()){
            		data.append("}");
            	}else{
            		data.append("},");
            	}
            }
            data.append("]");
            jsonStr.append("{success:true,");
            jsonStr.append(data+",");
            jsonStr.append(columModle+",");
            jsonStr.append(fieldsNames);
            jsonStr.append("}");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e.getCause());
        }finally{
            closeAll(connection, pStatement, resultSet);
        }
        return jsonStr.toString();
    }
    
    
    /**
     * 执行数据库的非查询操作
     * @param sql 要执行的Sql语句
     * @param param 参数列表
     * @return 受影响的行数
     */
    public int executeUpdateForFlex(String sql,Object[]param,Xt_Dbinfo xt_Dbinfo) {
        int num = 0;
        Connection connection = getConnection(xt_Dbinfo);
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement(sql);
            addParamters(param, pStatement);
            num = pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("执行非查询操作时出错了！",e);
        }finally{
        	closeAll(connection, pStatement, null);
        }
        return num;
    }
    ///////////////////////////////////////自定义连接结束（用于flexsearch）//////////////////////////////////////
}
