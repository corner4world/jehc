package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BCartDao;
import jehc.bmodules.bmodel.BCart;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础购物车 
* 2016-01-27 13:36:04  邓纯杰
*/
@Repository("bCartDao")
public class BCartDaoImpl  extends BaseDaoImpl implements BCartDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BCart> getBCartListByCondition(Map<String,Object> condition){
		return (List<BCart>)this.getList("getBCartListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_cart_id 
	* @return
	*/
	public BCart getBCartById(String b_cart_id){
		return (BCart)this.get("getBCartById", b_cart_id);
	}
	/**
	* 添加
	* @param b_cart 
	* @return
	*/
	public int addBCart(BCart b_Cart){
		return this.add("addBCart", b_Cart);
	}
	/**
	* 修改
	* @param b_cart 
	* @return
	*/
	public int updateBCart(BCart b_Cart){
		return this.update("updateBCart", b_Cart);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCart(Map<String,Object> condition){
		return this.del("delBCart", condition);
	}
	/**
	 * 根据购物车编号获取购物车订单地址集合编号
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBCartOrderAddressIdByCondition(Map<String,Object> condition){
		return (List<String>)this.getList("getBCartOrderAddressIdByCondition",condition);
	}
	/**
	 * 根据购物车编号集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BCart> getBCartList(Map<String,Object> condition){
		return (List<BCart>)this.getList("getBCartList",condition);
	}
}
