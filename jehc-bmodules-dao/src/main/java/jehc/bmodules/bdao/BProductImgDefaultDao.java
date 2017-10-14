package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BProductImgDefault;

/**
* 基础商品默认图片 
* 2016-01-09 09:06:38  邓纯杰
*/
public interface BProductImgDefaultDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductImgDefault> getBProductImgDefaultListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_product_img_default_id 
	* @return
	*/
	public BProductImgDefault getBProductImgDefaultById(String b_product_img_default_id);
	/**
	* 添加
	* @param b_product_img_default 
	* @return
	*/
	public int addBProductImgDefault(BProductImgDefault b_Product_Img_Default);
	/**
	* 修改
	* @param b_product_img_default 
	* @return
	*/
	public int updateBProductImgDefault(BProductImgDefault b_Product_Img_Default);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductImgDefault(Map<String,Object> condition);
}
