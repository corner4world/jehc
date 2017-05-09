package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_District;

/**
* 共用地区表; InnoDB free: 6144 kB 
* 2015-07-27 20:08:27  邓纯杰
*/
public interface Xt_DistrictService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_District> getXtDistrictListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_districtID 
	* @return
	*/
	public Xt_District getXtDistrictById(String xt_districtID);
	/**
	* 添加
	* @param xt_district 
	* @return
	*/
	public int addXtDistrict(Xt_District xt_District);
	/**
	* 修改
	* @param xt_district 
	* @return
	*/
	public int updateXtDistrict(Xt_District xt_District);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDistrict(Map<String,Object> condition);
}
