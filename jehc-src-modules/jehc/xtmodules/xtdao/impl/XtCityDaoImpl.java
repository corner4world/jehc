package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtCityDao;
import jehc.xtmodules.xtmodel.XtCity;

/**
* 共用城市表; InnoDB free: 7168 kB 
* 2015-07-27 19:57:27  邓纯杰
*/
@Repository("xtCityDao")
public class XtCityDaoImpl  extends BaseDaoImpl implements XtCityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtCity> getXtCityListByCondition(Map<String,Object> condition){
		return (List<XtCity>)this.getList("getXtCityListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_cityID 
	* @return
	*/
	public XtCity getXtCityById(String xt_cityID){
		return (XtCity)this.get("getXtCityById", xt_cityID);
	}
	/**
	* 添加
	* @param xt_city 
	* @return
	*/
	public int addXtCity(XtCity xt_City){
		return this.add("addXtCity", xt_City);
	}
	/**
	* 修改
	* @param xt_city 
	* @return
	*/
	public int updateXtCity(XtCity xt_City){
		return this.update("updateXtCity", xt_City);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtCity(Map<String,Object> condition){
		return this.del("delXtCity", condition);
	}
}
