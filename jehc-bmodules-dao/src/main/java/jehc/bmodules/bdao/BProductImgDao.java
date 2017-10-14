package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BProductImg;

/**
* 基础商品图片 
* 2016-07-07 20:50:43  邓纯杰
*/
public interface BProductImgDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BProductImg> getBProductImgListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_product_img_id 
	* @return
	*/
	public BProductImg getBProductImgById(String b_product_img_id);
	/**
	* 添加
	* @param b_product_img 
	* @return
	*/
	public int addBProductImg(BProductImg b_Product_Img);
	/**
	* 修改
	* @param b_product_img 
	* @return
	*/
	public int updateBProductImg(BProductImg b_Product_Img);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductImg(Map<String,Object> condition);
}
