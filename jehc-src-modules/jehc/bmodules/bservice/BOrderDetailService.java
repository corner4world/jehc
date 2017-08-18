package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BOrderDetail;

/**
* 基础订单详细 
* 2016-01-27 13:59:04  邓纯杰
*/
public interface BOrderDetailService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BOrderDetail> getBOrderDetailListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_order_detail_id 
	* @return
	*/
	public BOrderDetail getBOrderDetailById(String b_order_detail_id);
	/**
	* 添加
	* @param b_order_detail 
	* @return
	*/
	public int addBOrderDetail(BOrderDetail b_Order_Detail);
	/**
	* 修改
	* @param b_order_detail 
	* @return
	*/
	public int updateBOrderDetail(BOrderDetail b_Order_Detail);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBOrderDetail(Map<String,Object> condition);
	/**
	 * 获取订单总金额
	 * @param condition
	 * @return
	 */
	public double getBOrderDetailTotalPriceByCondition(Map<String,Object> condition);
}
