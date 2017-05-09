package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Province;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
public interface Xt_ProvinceService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Province> getXtProvinceListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_provinceID 
	* @return
	*/
	public Xt_Province getXtProvinceById(String xt_provinceID);
	/**
	* 添加
	* @param xt_province 
	* @return
	*/
	public int addXtProvince(Xt_Province xt_Province);
	/**
	* 修改
	* @param xt_province 
	* @return
	*/
	public int updateXtProvince(Xt_Province xt_Province);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtProvince(Map<String,Object> condition);
}
