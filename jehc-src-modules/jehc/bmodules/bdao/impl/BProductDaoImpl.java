package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BProductDao;
import jehc.bmodules.bmodel.BProduct;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础_产品 
* 2016-01-08 21:03:47  邓纯杰
*/
@Repository("bProductDao")
public class BProductDaoImpl  extends BaseDaoImpl implements BProductDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BProduct> getBProductListByCondition(Map<String,Object> condition){
		return (List<BProduct>)this.getList("getBProductListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_product_id 
	* @return
	*/
	public BProduct getBProductById(String b_product_id){
		return (BProduct)this.get("getBProductById", b_product_id);
	}
	/**
	* 添加
	* @param b_product 
	* @return
	*/
	public int addBProduct(BProduct b_Product){
		return this.add("addBProduct", b_Product);
	}
	/**
	* 修改
	* @param b_product 
	* @return
	*/
	public int updateBProduct(BProduct b_Product){
		return this.update("updateBProduct", b_Product);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBProduct(Map<String,Object> condition){
		return this.del("delBProduct", condition);
	}
}
