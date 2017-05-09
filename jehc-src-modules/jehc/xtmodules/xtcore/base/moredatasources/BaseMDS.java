package jehc.xtmodules.xtcore.base.moredatasources;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeException;
import org.mybatis.spring.support.SqlSessionDaoSupport;
/**
 * 多数据源情况下使用 即继承该BaseMDS即可
 * @author 邓纯杰
 *
 */
public class BaseMDS extends SqlSessionDaoSupport{
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
	public List<?> getList(String m, Object obj){
		List<?> list = this.getSqlSession().selectList(m, obj);  
        return list;  
	}
	
	/**
	 * 查询返回对象.
	 * @param m 方法名
	 * @param obj 对象
	 * @return 返回对象
	 */
	public Object get(String m, Object obj){
		Object object = this.getSqlSession().selectOne(m, obj);  
        return object;  
	}

	/**
	 * 查询指定页数大小.
	 * @param m 方法名
	 * @param obj 对象
	 * @param pageNo 页码
	 * @param pageSize 大小
	 * @return
	 */
	public List<?> getPageList(String m, Object obj, int pageNo, int pageSize){
		List<?> list = this.getSqlSession().selectList(m, obj, new RowBounds((pageNo-1)*pageSize, pageSize));  
        return list;  
	}
	
	/**
	 * 插入数据.
	 * @param m 方法名
	 * @param obj 对象
	 * @throws TypeException
	 * @throws SQLException
	 * @throws Exception
	 */
	public int add(String m, Object obj){
		return this.getSqlSession().insert(m, obj);  
	}
	
	/**
	 * 更新数据方法.
	 * @param m 方法名
	 * @param obj 对象
	 * @throws TypeException
	 * @throws SQLException
	 * @throws Exception
	 */
	public int update(String m, Object obj){
		return this.getSqlSession().update(m, obj);  
	}
	
	/**
	 * 删除数据方法.
	 * @param m 方法名
	 * @param obj 对象
	 * @throws TypeException
	 * @throws SQLException
	 * @throws Exception
	 */
	public int del(String m, Object obj){
		return this.getSqlSession().delete(m, obj);   
	}
}
