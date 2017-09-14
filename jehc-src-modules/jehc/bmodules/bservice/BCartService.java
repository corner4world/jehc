package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BCart;
import jehc.bmodules.bmodel.BCartDetail;
import jehc.bmodules.bmodel.BCartOrderAddress;

/**
* 基础购物车 
* 2016-01-27 13:36:04  邓纯杰
*/
public interface BCartService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCart> getBCartListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_cart_id 
	* @return
	*/
	public BCart getBCartById(String b_cart_id);
	/**
	* 添加
	* @param b_cart 
	* @return
	*/
	public int addBCart(BCart b_Cart,List<BCartDetail> b_Cart_DetailList,BCartOrderAddress b_Cart_Order_Address);
	/**
	* 修改
	* @param b_cart 
	* @return
	*/
	public int updateBCart(BCart b_Cart,List<BCartDetail> b_Cart_DetailList,BCartOrderAddress b_Cart_Order_Address);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCart(Map<String,Object> condition);
	/**
	 * 根据购物车编号获取购物车订单地址集合编号
	 * @param condition
	 * @return
	 */
	public List<String> getBCartOrderAddressIdByCondition(Map<String,Object> condition);
	/**
	 * 根据购物车编号集合
	 * @param condition
	 * @return
	 */
	public List<BCart> getBCartList(Map<String,Object> condition);
	
	/**
	 * 单个购物车转订单转换
	 * @param b_cart_id
	 * @return
	 */
	public int singleBCartTBOrderPoulators(String b_cart_id);
	/**
	 * 批量购物车转订单转换
	 * @param b_cart_id
	 * @return
	 */
	public int batchBCartTBOrderPoulators(String b_cart_id);
}
