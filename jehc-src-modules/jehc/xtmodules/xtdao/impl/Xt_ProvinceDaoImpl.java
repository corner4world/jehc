package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_ProvinceDao;
import jehc.xtmodules.xtmodel.Xt_Province;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
@Repository("xt_ProvinceDao")
public class Xt_ProvinceDaoImpl  extends BaseDaoImpl implements Xt_ProvinceDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Province> getXtProvinceListByCondition(Map<String,Object> condition){
		return (List<Xt_Province>)this.getList("getXtProvinceListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_provinceID 
	* @return
	*/
	public Xt_Province getXtProvinceById(String xt_provinceID){
		return (Xt_Province)this.get("getXtProvinceById", xt_provinceID);
	}
	/**
	* 添加
	* @param xt_province 
	* @return
	*/
	public int addXtProvince(Xt_Province xt_Province){
		return this.add("addXtProvince", xt_Province);
	}
	/**
	* 修改
	* @param xt_province 
	* @return
	*/
	public int updateXtProvince(Xt_Province xt_Province){
		return this.update("updateXtProvince", xt_Province);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtProvince(Map<String,Object> condition){
		return this.del("delXtProvince", condition);
	}
}
