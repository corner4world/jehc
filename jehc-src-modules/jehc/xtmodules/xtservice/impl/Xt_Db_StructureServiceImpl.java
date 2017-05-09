package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Db_StructureDao;
import jehc.xtmodules.xtmodel.Xt_Db_Fun;
import jehc.xtmodules.xtmodel.Xt_Db_Proc;
import jehc.xtmodules.xtmodel.Xt_Db_Structure;
import jehc.xtmodules.xtmodel.Xt_Db_TableAttribute;
import jehc.xtmodules.xtmodel.Xt_Db_TableIndex;
import jehc.xtmodules.xtmodel.Xt_Db_TableSize;
import jehc.xtmodules.xtmodel.Xt_Db_Tri;
import jehc.xtmodules.xtmodel.Xt_Db_View;
import jehc.xtmodules.xtservice.Xt_Db_StructureService;
/**
 * 数据库表
 * @author 邓纯杰
 */
@Service("xt_Db_StructureService")
public class Xt_Db_StructureServiceImpl extends BaseService implements Xt_Db_StructureService{
	@Autowired
	private Xt_Db_StructureDao xt_Db_StructureDao;
	/**
	 * 获取所有表属性
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_TableAttribute> getXtDbTableAttribute(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbTableAttribute(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 获取数据库表结构
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Structure> getXtDbStructureByCondition(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbStructureByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 显示建表语句
	 * @param condition
	 * @return
	 */
	public Xt_Db_Structure getTablePhrases(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getTablePhrases(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 读取表中索引
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_TableIndex> getXtDbTableIndex(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbTableIndex(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 读取表大小
	 * @param condition
	 * @return
	 */
	public Xt_Db_TableSize getXtDbTableSize(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbTableSize(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 创建索引
	 * @param sql
	 */
	public int addXtDbTableIndex(Map<String, Object> condition){
		int i = 0;
		try {
			i = xt_Db_StructureDao.addXtDbTableIndex(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 删除索引
	 * @param sql
	 */
	public int delXtDbTableIndex(Map<String, Object> condition){
		int i = 0;
		try {
			i = xt_Db_StructureDao.delXtDbTableIndex(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 查询存储过程
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Proc> getXtDbProcList(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbProcList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询函数
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Fun> getXtDbFunList(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbFunList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询视图
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_View> getXtDbViewList(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbViewList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询触发器
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Tri> getXtDbTriList(Map<String, Object> condition){
		try {
			return xt_Db_StructureDao.getXtDbTriList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
