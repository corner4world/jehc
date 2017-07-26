package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDbStructureDao;
import jehc.xtmodules.xtmodel.XtDbFun;
import jehc.xtmodules.xtmodel.XtDbProc;
import jehc.xtmodules.xtmodel.XtDbStructure;
import jehc.xtmodules.xtmodel.XtDbTableAttribute;
import jehc.xtmodules.xtmodel.XtDbTableIndex;
import jehc.xtmodules.xtmodel.XtDbTableSize;
import jehc.xtmodules.xtmodel.XtDbTri;
import jehc.xtmodules.xtmodel.XtDbView;
import jehc.xtmodules.xtservice.XtDbStructureService;
/**
 * 数据库表
 * @author 邓纯杰
 */
@Service("xtDbStructureService")
public class XtDbStructureServiceImpl extends BaseService implements XtDbStructureService{
	@Autowired
	private XtDbStructureDao xtDbStructureDao;
	/**
	 * 获取所有表属性
	 * @param condition
	 * @return
	 */
	public List<XtDbTableAttribute> getXtDbTableAttribute(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbTableAttribute(condition);
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
	public List<XtDbStructure> getXtDbStructureByCondition(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbStructureByCondition(condition);
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
	public XtDbStructure getTablePhrases(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getTablePhrases(condition);
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
	public List<XtDbTableIndex> getXtDbTableIndex(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbTableIndex(condition);
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
	public XtDbTableSize getXtDbTableSize(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbTableSize(condition);
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
			i = xtDbStructureDao.addXtDbTableIndex(condition);
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
			i = xtDbStructureDao.delXtDbTableIndex(condition);
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
	public List<XtDbProc> getXtDbProcList(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbProcList(condition);
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
	public List<XtDbFun> getXtDbFunList(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbFunList(condition);
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
	public List<XtDbView> getXtDbViewList(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbViewList(condition);
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
	public List<XtDbTri> getXtDbTriList(Map<String, Object> condition){
		try {
			return xtDbStructureDao.getXtDbTriList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
