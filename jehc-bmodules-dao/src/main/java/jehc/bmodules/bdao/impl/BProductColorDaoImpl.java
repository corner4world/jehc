package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BProductColorDao;
import jehc.bmodules.bmodel.BProductColor;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础商品商户所选颜色 
* 2016-07-02 16:54:11  邓纯杰
*/
@Repository("bProductColorDao")
public class BProductColorDaoImpl  extends BaseDaoImpl implements BProductColorDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BProductColor> getBProductColorListByCondition(Map<String,Object> condition){
		return (List<BProductColor>)this.getList("getBProductColorListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_product_color_id 
	* @return
	*/
	public BProductColor getBProductColorById(String b_product_color_id){
		return (BProductColor)this.get("getBProductColorById", b_product_color_id);
	}
	/**
	* 添加
	* @param b_product_color 
	* @return
	*/
	public int addBProductColor(BProductColor b_Product_Color){
		return this.add("addBProductColor", b_Product_Color);
	}
	/**
	* 修改
	* @param b_product_color 
	* @return
	*/
	public int updateBProductColor(BProductColor b_Product_Color){
		return this.update("updateBProductColor", b_Product_Color);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductColor(Map<String,Object> condition){
		return this.del("delBProductColor", condition);
	}
}
