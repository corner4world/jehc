package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BProductColorDefault;

/**
* 基础商品默认颜色 
* 2016-01-19 15:38:05  邓纯杰
*/
public interface BProductColorDefaultDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductColorDefault> getBProductColorDefaultListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_product_color_default_id 
	* @return
	*/
	public BProductColorDefault getBProductColorDefaultById(String b_product_color_default_id);
	/**
	* 添加
	* @param b_product_color_default 
	* @return
	*/
	public int addBProductColorDefault(BProductColorDefault b_Product_Color_Default);
	/**
	* 修改
	* @param b_product_color_default 
	* @return
	*/
	public int updateBProductColorDefault(BProductColorDefault b_Product_Color_Default);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductColorDefault(Map<String,Object> condition);
}
