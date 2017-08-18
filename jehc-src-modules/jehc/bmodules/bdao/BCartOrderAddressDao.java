package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BCartOrderAddress;

/**
* 基础购物车订单常用配送地址 
* 2016-02-22 21:17:24  邓纯杰
*/
public interface BCartOrderAddressDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCartOrderAddress> getBCartOrderAddressListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_cart_order_address_id 
	* @return
	*/
	public BCartOrderAddress getBCartOrderAddressById(String b_cart_order_address_id);
	/**
	* 添加
	* @param b_cart_order_address 
	* @return
	*/
	public int addBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address);
	/**
	* 修改
	* @param b_cart_order_address 
	* @return
	*/
	public int updateBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCartOrderAddress(Map<String,Object> condition);
}
