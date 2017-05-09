package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.base.DBHelper;
import jehc.xtmodules.xtdao.Xt_FlexSearchDao;
import net.sf.json.JSONArray;
/**
 * 查询工具
 * @author 邓纯杰
 *
 */
public class Xt_FlexSearchDaoImpl implements Xt_FlexSearchDao {
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getXtFlexSearchQuery(String sql, Object[] param) {
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> lmList = dbHelper.executdQueryForObject(sql, null, Object.class);
		JSONArray jsonArray = new JSONArray();
		for(int j = 0; j < lmList.size(); j++){
			Map<String, Object> map = lmList.get(j);
			jsonArray.add(map);
		}
		String jsonStr = jsonArray.toString();
		return jsonStr;
	}
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchListQuery(String sql, Object[] param) {
		DBHelper dbHelper = new DBHelper();
		return dbHelper.executdQueryJosnForObject(sql, null, Object.class);
	}
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer executeUpdate(String sql, Object[] param){
		DBHelper dbHelper = new DBHelper();
		return dbHelper.executeUpdate(sql, param);
	}
}
