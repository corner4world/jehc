package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_CityDao;
import jehc.xtmodules.xtmodel.Xt_City;

/**
* 共用城市表; InnoDB free: 7168 kB 
* 2015-07-27 19:57:27  邓纯杰
*/
@Repository("xt_CityDao")
public class Xt_CityDaoImpl  extends BaseDaoImpl implements Xt_CityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_City> getXtCityListByCondition(Map<String,Object> condition){
		return (List<Xt_City>)this.getList("getXtCityListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_cityID 
	* @return
	*/
	public Xt_City getXtCityById(String xt_cityID){
		return (Xt_City)this.get("getXtCityById", xt_cityID);
	}
	/**
	* 添加
	* @param xt_city 
	* @return
	*/
	public int addXtCity(Xt_City xt_City){
		return this.add("addXtCity", xt_City);
	}
	/**
	* 修改
	* @param xt_city 
	* @return
	*/
	public int updateXtCity(Xt_City xt_City){
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
