package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Db_StructureDao;
import jehc.xtmodules.xtmodel.Xt_Db_Fun;
import jehc.xtmodules.xtmodel.Xt_Db_Proc;
import jehc.xtmodules.xtmodel.Xt_Db_Structure;
import jehc.xtmodules.xtmodel.Xt_Db_TableAttribute;
import jehc.xtmodules.xtmodel.Xt_Db_TableIndex;
import jehc.xtmodules.xtmodel.Xt_Db_TableSize;
import jehc.xtmodules.xtmodel.Xt_Db_Tri;
import jehc.xtmodules.xtmodel.Xt_Db_View;
/**
 * 数据库表
 * @author 邓纯杰
 */
@Repository("xt_Db_StructureDao")
public class Xt_Db_StructureDaoImpl extends BaseDaoImpl implements Xt_Db_StructureDao{
	/**
	 * 获取所有表属性
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_TableAttribute> getXtDbTableAttribute(Map<String, Object> condition){
		return (List<Xt_Db_TableAttribute>)this.getList("getXtDbTableAttribute", condition);
	}
	/**
	 * 获取数据库表结构
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_Structure> getXtDbStructureByCondition(Map<String, Object> condition){
		return (List<Xt_Db_Structure>)this.getList("getXtDbStructureByCondition", condition);
	}
	
	/**
	 * 显示建表语句
	 * @param condition
	 * @return
	 */
	public Xt_Db_Structure getTablePhrases(Map<String, Object> condition){
		return (Xt_Db_Structure)this.get("getTablePhrases", condition);
	}
	
	/**
	 * 读取表中索引
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_TableIndex> getXtDbTableIndex(Map<String, Object> condition){
		return (List<Xt_Db_TableIndex>)this.getList("getXtDbTableIndex", condition);
	}
	
	/**
	 * 读取表大小
	 * @param condition
	 * @return
	 */
	public Xt_Db_TableSize getXtDbTableSize(Map<String, Object> condition){
		return (Xt_Db_TableSize)this.get("getXtDbTableSize", condition);
	}
	
	/**
	 * 创建索引
	 * @param sql
	 */
	public int addXtDbTableIndex(Map<String, Object> condition){
		return this.update("addXtDbTableIndex", condition);
	}
	
	/**
	 * 删除索引
	 * @param sql
	 */
	public int delXtDbTableIndex(Map<String, Object> condition){
		return this.update("delXtDbTableIndex", condition);
	}
	
	/**
	 * 查询存储过程
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_Proc> getXtDbProcList(Map<String, Object> condition){
		return (List<Xt_Db_Proc>)this.getList("getXtDbProcList", condition);
	}
	
	/**
	 * 查询函数
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_Fun> getXtDbFunList(Map<String, Object> condition){
		return (List<Xt_Db_Fun>)this.getList("getXtDbFunList", condition);
	}
	
	/**
	 * 查询视图
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_View> getXtDbViewList(Map<String, Object> condition){
		return (List<Xt_Db_View>)this.getList("getXtDbViewList", condition);
	}
	
	/**
	 * 查询触发器
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Db_Tri> getXtDbTriList(Map<String, Object> condition){
		return (List<Xt_Db_Tri>)this.getList("getXtDbTriList", condition);
	}
}
