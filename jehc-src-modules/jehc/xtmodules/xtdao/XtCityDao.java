package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtCity;

/**
* 共用城市表; InnoDB free: 7168 kB 
* 2015-07-27 19:57:27  邓纯杰
*/
public interface XtCityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtCity> getXtCityListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_cityID 
	* @return
	*/
	public XtCity getXtCityById(String xt_cityID);
	/**
	* 添加
	* @param xt_city 
	* @return
	*/
	public int addXtCity(XtCity xt_City);
	/**
	* 修改
	* @param xt_city 
	* @return
	*/
	public int updateXtCity(XtCity xt_City);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtCity(Map<String,Object> condition);
}
