package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BOrder;

/**
* 基础订单 
* 2016-01-27 13:55:11  邓纯杰
*/
public interface BOrderDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BOrder> getBOrderListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_order_id 
	* @return
	*/
	public BOrder getBOrderById(String b_order_id);
	/**
	* 添加
	* @param b_order 
	* @return
	*/
	public int addBOrder(BOrder b_Order);
	/**
	* 修改
	* @param b_order 
	* @return
	*/
	public int updateBOrder(BOrder b_Order);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBOrder(Map<String,Object> condition);
	/**
	 * 更新支付类型
	 * @param b_Order
	 */
	public int updateBOrderType(BOrder b_Order);
}
