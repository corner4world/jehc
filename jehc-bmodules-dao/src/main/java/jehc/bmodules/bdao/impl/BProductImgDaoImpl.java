package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BProductImgDao;
import jehc.bmodules.bmodel.BProductImg;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础商品图片 
* 2016-07-07 20:50:43  邓纯杰
*/
@Repository("bProductImgDao")
public class BProductImgDaoImpl  extends BaseDaoImpl implements BProductImgDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BProductImg> getBProductImgListByCondition(Map<String,Object> condition){
		return (List<BProductImg>)this.getList("getBProductImgListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_product_img_id 
	* @return
	*/
	public BProductImg getBProductImgById(String b_product_img_id){
		return (BProductImg)this.get("getBProductImgById", b_product_img_id);
	}
	/**
	* 添加
	* @param b_product_img 
	* @return
	*/
	public int addBProductImg(BProductImg b_Product_Img){
		return this.add("addBProductImg", b_Product_Img);
	}
	/**
	* 修改
	* @param b_product_img 
	* @return
	*/
	public int updateBProductImg(BProductImg b_Product_Img){
		return this.update("updateBProductImg", b_Product_Img);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductImg(Map<String,Object> condition){
		return this.del("delBProductImg", condition);
	}
}
