package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BProductColor;

/**
* 基础商品商户所选颜色 
* 2016-07-02 16:54:11  邓纯杰
*/
public interface BProductColorService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductColor> getBProductColorListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_product_color_id 
	* @return
	*/
	public BProductColor getBProductColorById(String b_product_color_id);
	/**
	* 添加
	* @param b_product_color 
	* @return
	*/
	public int addBProductColor(BProductColor b_Product_Color);
	/**
	* 修改
	* @param b_product_color 
	* @return
	*/
	public int updateBProductColor(BProductColor b_Product_Color);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductColor(Map<String,Object> condition);
}
