package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtDbDao;
import jehc.xtmodules.xtmodel.XtDb;
/**
 * 数据库备份
 * @author 邓纯杰
 *
 */
@Repository("xtDbDao")
public class XtDbDaoImpl extends BaseDaoImpl implements XtDbDao {
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDb> getXtDbListByCondition(Map<String,Object> condition){
		return (List<XtDb>)this.getList("getXtDbListByCondition", condition);
	}
	
	/**
	 * 新增一条数据
	 * @param xtDb
	 */
	public void addXtDb(XtDb xtDb){
		this.add("addXtDb", xtDb);
	}
}
