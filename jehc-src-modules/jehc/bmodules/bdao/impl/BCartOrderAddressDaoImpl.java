package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BCartOrderAddressDao;
import jehc.bmodules.bmodel.BCartOrderAddress;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础购物车订单常用配送地址 
* 2016-02-22 21:17:24  邓纯杰
*/
@Repository("bCartOrderAddressDao")
public class BCartOrderAddressDaoImpl  extends BaseDaoImpl implements BCartOrderAddressDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BCartOrderAddress> getBCartOrderAddressListByCondition(Map<String,Object> condition){
		return (List<BCartOrderAddress>)this.getList("getBCartOrderAddressListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_cart_order_address_id 
	* @return
	*/
	public BCartOrderAddress getBCartOrderAddressById(String b_cart_order_address_id){
		return (BCartOrderAddress)this.get("getBCartOrderAddressById", b_cart_order_address_id);
	}
	/**
	* 添加
	* @param b_cart_order_address 
	* @return
	*/
	public int addBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address){
		return this.add("addBCartOrderAddress", b_Cart_Order_Address);
	}
	/**
	* 修改
	* @param b_cart_order_address 
	* @return
	*/
	public int updateBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address){
		return this.update("updateBCartOrderAddress", b_Cart_Order_Address);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCartOrderAddress(Map<String,Object> condition){
		return this.del("delBCartOrderAddress", condition);
	}
}
