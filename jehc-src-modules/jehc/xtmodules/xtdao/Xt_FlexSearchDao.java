package jehc.xtmodules.xtdao;


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
	public String getXtFlexSearchQuery(String sql,Object[]param);
	
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getXtFlexSearchListQuery(String sql, Object[] param);
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer executeUpdate(String sql, Object[] param);
}
