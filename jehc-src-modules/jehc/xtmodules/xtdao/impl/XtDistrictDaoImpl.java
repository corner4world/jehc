package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtDistrictDao;
import jehc.xtmodules.xtmodel.XtDistrict;

/**
* 共用地区表; InnoDB free: 6144 kB 
* 2015-07-27 20:08:27  邓纯杰
*/
@Repository("xtDistrictDao")
public class XtDistrictDaoImpl  extends BaseDaoImpl implements XtDistrictDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDistrict> getXtDistrictListByCondition(Map<String,Object> condition){
		return (List<XtDistrict>)this.getList("getXtDistrictListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_districtID 
	* @return
	*/
	public XtDistrict getXtDistrictById(String xt_districtID){
		return (XtDistrict)this.get("getXtDistrictById", xt_districtID);
	}
	/**
	* 添加
	* @param xt_district 
	* @return
	*/
	public int addXtDistrict(XtDistrict xt_District){
		return this.add("addXtDistrict", xt_District);
	}
	/**
	* 修改
	* @param xt_district 
	* @return
	*/
	public int updateXtDistrict(XtDistrict xt_District){
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
