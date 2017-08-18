package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BOrderDao;
import jehc.bmodules.bmodel.BOrder;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础订单 
* 2016-01-27 13:55:11  邓纯杰
*/
@Repository("bOrderDao")
public class BOrderDaoImpl  extends BaseDaoImpl implements BOrderDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BOrder> getBOrderListByCondition(Map<String,Object> condition){
		return (List<BOrder>)this.getList("getBOrderListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_order_id 
	* @return
	*/
	public BOrder getBOrderById(String b_order_id){
		return (BOrder)this.get("getBOrderById", b_order_id);
	}
	/**
	* 添加
	* @param b_order 
	* @return
	*/
	public int addBOrder(BOrder b_Order){
		return this.add("addBOrder", b_Order);
	}
	/**
	* 修改
	* @param b_order 
	* @return
	*/
	public int updateBOrder(BOrder b_Order){
		return this.update("updateBOrder", b_Order);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBOrder(Map<String,Object> condition){
		return this.del("delBOrder", condition);
	}
	/**
	 * 更新订单类型
	 * @param b_Order
	 */
	public int updateBOrderType(BOrder b_Order){
		return this.update("updateBOrderType", b_Order);
	}
}
