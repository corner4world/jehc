package jehc.xtmodules.xtcore.base;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.TypeException;
/**
 * BaseDao接口
 * @author邓纯杰
 *
 */
public interface BaseDao {

	/**
	 * 
	 * 查询所有数据.
	 * @param m 方法名
	 * @param obj 对象
	 * @throws TypeException
	 * @throws SQLException
	 * @throws Exception
	 * @return
	 */
	public List<?> getList(String m, Object obj);

	/**
	 * 查询指定页数大小.
	 * @param m 方法名
	 * @param obj 对象
	 * @param pageNo 页码
	 * @param pageSize 大小
	 * @return
	 */
	public List<?> getPageList(String m, Object obj, int pageNo, int pageSize);

	/**
	 * 查询返回对象.
	 * @param m 方法名
	 * @param obj 对象
	 * @return 返回对象
	 */
	public Object get(String m, Object obj);
	
	/**
	 * 插入数据.
	 * @param m 方法名
	 * @param obj 对象
	 * @throws TypeException
	 * @throws SQLException
	 * @throws Exception
	 */
	public int add(String m, Object obj);
	
	/**
	 * 更新数据方法.
	 * @param m 方法名
	 * @param obj 对象
	 * @throws TypeException
	 * @throws SQLException
	 * @throws Exception
	 */
	public int update(String m, Object obj);
	
	/**
	 * 删除数据方法.
	 * @param m 方法名
	 * @param obj 对象
	 */
	public int del(String m, Object obj);
	
	
	
	
//	/**
//	 * 获得sqlSession对象.
//	 * @return
//	 */
//	public SqlSession getSqlSession() throws TypeException, SQLException,Exception;
//
//
//	/**
//	 * 
//	 * 查询所有数据.
//	 * @param m 方法名
//	 * @param obj 对象
//	 * @throws TypeException
//	 * @throws SQLException
//	 * @throws Exception
//	 * @return
//	 */
//	public List<?> getList(String m, Object obj) throws TypeException,SQLException, Exception;
//
//	/**
//	 * 查询指定页数大小.
//	 * @param m 方法名
//	 * @param obj 对象
//	 * @param pageNo 页码
//	 * @param pageSize 大小
//	 * @return
//	 */
//	public List<?> getPageList(String m, Object obj, int pageNo, int pageSize)throws TypeException, SQLException, Exception;
//
//	/**
//	 * 查询返回对象.
//	 * @param m 方法名
//	 * @param obj 对象
//	 * @return 返回对象
//	 */
//	public Object getObject(String m, Object obj) throws TypeException,SQLException, Exception;
//	
//	/**
//	 * 插入数据.
//	 * @param m 方法名
//	 * @param obj 对象
//	 * @throws TypeException
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public void add(String m, Object obj) throws TypeException,SQLException, Exception;
//	
//	/**
//	 * 更新数据方法.
//	 * @param m 方法名
//	 * @param obj 对象
//	 * @throws TypeException
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public void update(String m, Object obj) throws TypeException,SQLException, Exception;
//	
//	/**
//	 * 删除数据方法.
//	 * @param m 方法名
//	 * @param obj 对象
//	 * @throws TypeException
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public void del(String m, Object obj) throws TypeException,SQLException, Exception;
}
