package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BProductImgDefaultDao;
import jehc.bmodules.bmodel.BProductImgDefault;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础商品默认图片 
* 2016-01-09 09:06:38  邓纯杰
*/
@Repository("bProductImgDefaultDao")
public class BProductImgDefaultDaoImpl  extends BaseDaoImpl implements BProductImgDefaultDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BProductImgDefault> getBProductImgDefaultListByCondition(Map<String,Object> condition){
		return (List<BProductImgDefault>)this.getList("getBProductImgDefaultListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_product_img_default_id 
	* @return
	*/
	public BProductImgDefault getBProductImgDefaultById(String b_product_img_default_id){
		return (BProductImgDefault)this.get("getBProductImgDefaultById", b_product_img_default_id);
	}
	/**
	* 添加
	* @param b_product_img_default 
	* @return
	*/
	public int addBProductImgDefault(BProductImgDefault b_Product_Img_Default){
		return this.add("addBProductImgDefault", b_Product_Img_Default);
	}
	/**
	* 修改
	* @param b_product_img_default 
	* @return
	*/
	public int updateBProductImgDefault(BProductImgDefault b_Product_Img_Default){
		return this.update("updateBProductImgDefault", b_Product_Img_Default);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductImgDefault(Map<String,Object> condition){
		return this.del("delBProductImgDefault", condition);
	}
}
