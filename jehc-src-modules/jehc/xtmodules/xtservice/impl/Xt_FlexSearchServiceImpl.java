package jehc.xtmodules.xtservice.impl;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_FlexSearchDao;
import jehc.xtmodules.xtdao.impl.Xt_FlexSearchDaoImpl;
import jehc.xtmodules.xtservice.Xt_FlexSearchService;
/**
 * 查询工具
 * @author 邓纯杰
 *
 */
public class Xt_FlexSearchServiceImpl extends BaseService implements Xt_FlexSearchService {
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchQuery(String sql,Object[]param){
		try {
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtFlexSearchQuery(sql, param);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询返回结果集
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getXtFlexSearchListQuery(String sql, Object[] param){
		try {
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			return xt_FlexSearchDao.getXtFlexSearchListQuery(sql, param);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 执行非查询结果集操作语句
	 * @param sql
	 * @param param
	 * @return
	 */
	public String executeUpdate(String sql, Object[] param){
		StringBuffer fieldsNames = new StringBuffer();
		StringBuffer jsonStr = new StringBuffer();
		StringBuffer columModle = new StringBuffer();
        StringBuffer data = new StringBuffer();
		try {
			Xt_FlexSearchDao xt_FlexSearchDao = new Xt_FlexSearchDaoImpl();
			int result = xt_FlexSearchDao.executeUpdate(sql, param);
            //fieldsNames
            fieldsNames.append("'fieldsNames':[");
            columModle.append("'columModle':[");
            fieldsNames.append("{name:'result'},");
            String dataIndex = "result";
            String header = "result";
            columModle.append("{'header':'"+header+"','dataIndex':'"+dataIndex+"',flex:1},");
            columModle.append("]");
            fieldsNames.append("]");
            //data
            data.append("'data':[");
            data.append("{");
        	data.append("'result':'执行的影响行数<font color=red>"+result+"</font>条'");
        	data.append("}");
            data.append("]");
            jsonStr.append("{success:true,");
            jsonStr.append(data+",");
            jsonStr.append(columModle+",");
            jsonStr.append(fieldsNames);
            jsonStr.append("}");
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return jsonStr.toString();
	}
}
