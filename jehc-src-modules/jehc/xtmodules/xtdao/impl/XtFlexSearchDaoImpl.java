package jehc.xtmodules.xtdao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.base.DBHelper;
import jehc.xtmodules.xtdao.XtFlexSearchDao;
import jehc.xtmodules.xtmodel.XtDbFun;
import jehc.xtmodules.xtmodel.XtDbProc;
import jehc.xtmodules.xtmodel.XtDbStructure;
import jehc.xtmodules.xtmodel.XtDbTableAttribute;
import jehc.xtmodules.xtmodel.XtDbTableIndex;
import jehc.xtmodules.xtmodel.XtDbTri;
import jehc.xtmodules.xtmodel.XtDbView;
import jehc.xtmodules.xtmodel.XtDbinfo;
import net.sf.json.JSONArray;
/**
 * 查询工具
 * @author 邓纯杰
 *
 */
public class XtFlexSearchDaoImpl implements XtFlexSearchDao {
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getXtFlexSearchQuery(String sql, Object[] param,XtDbinfo xt_Dbinfo) {
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
	public String getXtFlexSearchListQuery(String sql, Object[] param,XtDbinfo xt_Dbinfo) {
		DBHelper dbHelper = new DBHelper();
		return dbHelper.executdQueryJosnForFlex(sql, null, Object.class,xt_Dbinfo);
	}
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public Integer executeUpdate(String sql, Object[] param,XtDbinfo xt_Dbinfo){
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
	public List<XtDbTableAttribute> getXtDbTableAttributeForFlex(String sql,XtDbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		for(int j = 0; j < list.size(); j++){
			Map<String, Object> map = list.get(j);
		}
		return null;
	}
	/**
	 * 查询表索引
	 * @return
	 */
	public List<XtDbTableIndex> getXtDbTableIndexForFlex(String sql,XtDbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		return null;
	}
	
	/**
	 * 查询存储过程
	 * @return
	 */
	public  List<XtDbProc> getXtDbProcListForFlex(String sql,XtDbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		return null;
	}
	
	/**
	 * 查询函数
	 * @param sql
	 * @return
	 */
	public  List<XtDbFun> getXtDbFunListForFlex(String sql,XtDbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		return null;
	}
	
	/**
	 * 查询视图
	 * @param sql
	 * @return
	 */
	public  List<XtDbView> getXtDbViewListForFlex(String sql,XtDbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		return null;
	}
	
	/**
	 * 查询触发器
	 */
	public  List<XtDbTri> getXtDbTriListForFlex(String sql,XtDbinfo xt_Dbinfo){
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		return null;
	}
	
	/**
	 * 查询字段
	 * @param sql
	 * @param xt_Dbinfo
	 * @return
	 */
	public List<XtDbStructure> getXtDbStructureForFlex(String sql,XtDbinfo xt_Dbinfo){
		List<XtDbStructure> dbStructureList = new ArrayList<XtDbStructure>();
		DBHelper dbHelper = new DBHelper();
		List<Map<String, Object>> list = dbHelper.executdQueryForObject(sql, null, Object.class,xt_Dbinfo);
		for(int j = 0; j < list.size(); j++){
			Map<String, Object> map = list.get(j);
		}
		return dbStructureList;
	}
}
