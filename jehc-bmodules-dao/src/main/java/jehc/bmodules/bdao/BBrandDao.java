package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BBrand;

/**
* 基础-品牌 
* 2016-01-05 12:46:23  邓纯杰
*/
public interface BBrandDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BBrand> getBBrandListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_brand_id 
	* @return
	*/
	public BBrand getBBrandById(String b_brand_id);
	/**
	* 添加
	* @param b_brand 
	* @return
	*/
	public int addBBrand(BBrand b_Brand);
	/**
	* 修改
	* @param b_brand 
	* @return
	*/
	public int updateBBrand(BBrand b_Brand);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBBrand(Map<String,Object> condition);
}
