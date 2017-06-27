package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.base.DBHelper;
import jehc.xtmodules.xtdao.Xt_FlexSearchDao;
import jehc.xtmodules.xtmodel.Xt_Db_Fun;
import jehc.xtmodules.xtmodel.Xt_Db_Proc;
import jehc.xtmodules.xtmodel.Xt_Db_Structure;
import jehc.xtmodules.xtmodel.Xt_Db_TableAttribute;
import jehc.xtmodules.xtmodel.Xt_Db_TableIndex;
import jehc.xtmodules.xtmodel.Xt_Db_Tri;
import jehc.xtmodules.xtmodel.Xt_Db_View;
import jehc.xtmodules.xtmodel.Xt_Dbinfo;
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
	public String getXtFlexSearchQuery(String sql, Object[] param,Xt_Dbinfo xt_Dbinfo) {
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> lmList = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
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
	public String getXtFlexSearchListQuery(String sql, Object[] param,Xt_Dbinfo xt_Dbinfo) {
		DBHelper dbHelper = new DBHelper();
		return dbHelper.executdQueryJosnForFlex(sql, null, Object.class,xt_Dbinfo);
	}
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer executeUpdate(String sql, Object[] param,Xt_Dbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		return dbHelper.executeUpdateForFlex(sql, param,xt_Dbinfo);
	}
	
	////////////////////////////为菜单服务///////////////////////
	/**
	 * 获取所有表
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<Xt_Db_TableAttribute> getXtDbTableAttributeForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
	/**
	 * 查询表索引
	 * @return
	 */
	public List<Xt_Db_TableIndex> getXtDbTableIndexForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
	
	/**
	 * 查询存储过程
	 * @return
	 */
	public  List<Xt_Db_Proc> getXtDbProcListForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
	
	/**
	 * 查询函数
	 * @param sql
	 * @return
	 */
	public  List<Xt_Db_Fun> getXtDbFunListForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
	
	/**
	 * 查询视图
	 * @param sql
	 * @return
	 */
	public  List<Xt_Db_View> getXtDbViewListForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
	
	/**
	 * 查询触发器
	 */
	public  List<Xt_Db_Tri> getXtDbTriListForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
	
	/**
	 * 查询字段
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<Xt_Db_Structure> getXtDbStructureForFlex(String sql,Xt_Dbinfo xt_Dbinfo){
		return null;
	}
}
