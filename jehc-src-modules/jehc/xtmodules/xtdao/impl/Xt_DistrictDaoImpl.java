package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_DistrictDao;
import jehc.xtmodules.xtmodel.Xt_District;

/**
* 共用地区表; InnoDB free: 6144 kB 
* 2015-07-27 20:08:27  邓纯杰
*/
@Repository("xt_DistrictDao")
public class Xt_DistrictDaoImpl  extends BaseDaoImpl implements Xt_DistrictDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_District> getXtDistrictListByCondition(Map<String,Object> condition){
		return (List<Xt_District>)this.getList("getXtDistrictListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_districtID 
	* @return
	*/
	public Xt_District getXtDistrictById(String xt_districtID){
		return (Xt_District)this.get("getXtDistrictById", xt_districtID);
	}
	/**
	* 添加
	* @param xt_district 
	* @return
	*/
	public int addXtDistrict(Xt_District xt_District){
		return this.add("addXtDistrict", xt_District);
	}
	/**
	* 修改
	* @param xt_district 
	* @return
	*/
	public int updateXtDistrict(Xt_District xt_District){
		return this.update("updateXtDistrict", xt_District);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDistrict(Map<String,Object> condition){
		return this.del("delXtDistrict", condition);
	}
}
