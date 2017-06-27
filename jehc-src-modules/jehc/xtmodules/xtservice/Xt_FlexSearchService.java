package jehc.xtmodules.xtservice;

import java.util.List;

import jehc.xtmodules.xtmodel.Xt_Db_Fun;
import jehc.xtmodules.xtmodel.Xt_Db_Proc;
import jehc.xtmodules.xtmodel.Xt_Db_Structure;
import jehc.xtmodules.xtmodel.Xt_Db_TableAttribute;
import jehc.xtmodules.xtmodel.Xt_Db_TableIndex;
import jehc.xtmodules.xtmodel.Xt_Db_Tri;
import jehc.xtmodules.xtmodel.Xt_Db_View;

/**
 * 查询工具
 * @author 邓纯杰
 *
 */
public interface Xt_FlexSearchService {
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchQuery(String sql,Object[]param,String xt_dbinfo_id);
	
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchListQuery(String sql, Object[] param,String xt_dbinfo_id);
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public String executeUpdate(String sql, Object[] param,String xt_dbinfo_id);
	
	
	////////////////////////////为菜单服务///////////////////////
	/**
	 * 获取所有表
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<Xt_Db_TableAttribute> getXtDbTableAttributeForFlex(String xt_dbinfo_id);
	
	/**
	* 查询表索引
	* @return
	*/
	public List<Xt_Db_TableIndex> getXtDbTableIndexForFlex(String xt_dbinfo_id,String tableName);
	
	/**
	* 查询存储过程
	* @return
	*/
	public  List<Xt_Db_Proc> getXtDbProcListForFlex(String xt_dbinfo_id);
	
	/**
	* 查询函数
	* @param sql
	* @return
	*/
	public  List<Xt_Db_Fun> getXtDbFunListForFlex(String xt_dbinfo_id);
	
	/**
	* 查询视图
	* @param sql
	* @return
	*/
	public  List<Xt_Db_View> getXtDbViewListForFlex(String xt_dbinfo_id);
	
	/**
	* 查询触发器
	*/
	public  List<Xt_Db_Tri> getXtDbTriListForFlex(String xt_dbinfo_id);
	
	/**
	 * 查询字段
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<Xt_Db_Structure> getXtDbStructureForFlex(String xt_dbinfo_id,String tableName);
}
