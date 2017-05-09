package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

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
public interface Xt_Db_StructureDao {
	
	/**
	 * 获取所有表属性
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_TableAttribute> getXtDbTableAttribute(Map<String, Object> condition);
	/**
	 * 获取数据库表结构
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Structure> getXtDbStructureByCondition(Map<String, Object> condition);
	
	/**
	 * 显示建表语句
	 * @param condition
	 * @return
	 */
	public Xt_Db_Structure getTablePhrases(Map<String, Object> condition);
	
	/**
	 * 读取表中索引
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_TableIndex> getXtDbTableIndex(Map<String, Object> condition);
	
	/**
	 * 读取表大小
	 * @param condition
	 * @return
	 */
	public Xt_Db_TableSize getXtDbTableSize(Map<String, Object> condition);
	
	/**
	 * 创建索引
	 * @param sql
	 */
	public int addXtDbTableIndex(Map<String, Object> condition);
	
	/**
	 * 删除索引
	 * @param sql
	 */
	public int delXtDbTableIndex(Map<String, Object> condition);
	

	/**
	 * 查询存储过程
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Proc> getXtDbProcList(Map<String, Object> condition);
	
	/**
	 * 查询函数
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Fun> getXtDbFunList(Map<String, Object> condition);
	
	/**
	 * 查询视图
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_View> getXtDbViewList(Map<String, Object> condition);
	
	/**
	 * 查询触发器
	 * @param condition
	 * @return
	 */
	public List<Xt_Db_Tri> getXtDbTriList(Map<String, Object> condition);
}
