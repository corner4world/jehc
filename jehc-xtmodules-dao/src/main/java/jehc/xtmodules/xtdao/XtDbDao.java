package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtDb;

/**
 * 数据库备份
 * @author 邓纯杰
 *
 */
public interface XtDbDao {
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDb> getXtDbListByCondition(Map<String,Object> condition);
	
	/**
	 * 新增一条数据
	 * @param xtDb
	 */
	public void addXtDb(XtDb xtDb);
}
