package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BProduct;

/**
* 基础_产品 
* 2016-01-08 21:03:47  邓纯杰
*/
public interface BProductService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProduct> getBProductListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_product_id 
	* @return
	*/
	public BProduct getBProductById(String b_product_id);
	/**
	* 添加
	* @param b_product 
	* @return
	*/
	public int addBProduct(BProduct b_Product);
	/**
	* 修改
	* @param b_product 
	* @return
	*/
	public int updateBProduct(BProduct b_Product);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProduct(Map<String,Object> condition);
}
