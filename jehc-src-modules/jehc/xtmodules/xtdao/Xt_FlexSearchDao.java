package jehc.xtmodules.xtdao;

import jehc.xtmodules.xtmodel.Xt_Dbinfo;

/**
 * 查询工具
 * @author 邓纯杰
 *
 */
public interface Xt_FlexSearchDao {

	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchQuery(String sql,Object[]param,Xt_Dbinfo xt_Dbinfo);
	
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchListQuery(String sql, Object[] param,Xt_Dbinfo xt_Dbinfo);
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer executeUpdate(String sql, Object[] param,Xt_Dbinfo xt_Dbinfo);
}
