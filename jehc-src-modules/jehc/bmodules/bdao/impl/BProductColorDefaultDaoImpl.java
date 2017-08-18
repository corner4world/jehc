package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BProductColorDefaultDao;
import jehc.bmodules.bmodel.BProductColorDefault;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础商品默认颜色 
* 2016-01-19 15:38:05  邓纯杰
*/
@Repository("bProductColorDefaultDao")
public class BProductColorDefaultDaoImpl  extends BaseDaoImpl implements BProductColorDefaultDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BProductColorDefault> getBProductColorDefaultListByCondition(Map<String,Object> condition){
		return (List<BProductColorDefault>)this.getList("getBProductColorDefaultListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_product_color_default_id 
	* @return
	*/
	public BProductColorDefault getBProductColorDefaultById(String b_product_color_default_id){
		return (BProductColorDefault)this.get("getBProductColorDefaultById", b_product_color_default_id);
	}
	/**
	* 添加
	* @param b_product_color_default 
	* @return
	*/
	public int addBProductColorDefault(BProductColorDefault b_Product_Color_Default){
		return this.add("addBProductColorDefault", b_Product_Color_Default);
	}
	/**
	* 修改
	* @param b_product_color_default 
	* @return
	*/
	public int updateBProductColorDefault(BProductColorDefault b_Product_Color_Default){
		return this.update("updateBProductColorDefault", b_Product_Color_Default);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProductColorDefault(Map<String,Object> condition){
		return this.del("delBProductColorDefault", condition);
	}
}
