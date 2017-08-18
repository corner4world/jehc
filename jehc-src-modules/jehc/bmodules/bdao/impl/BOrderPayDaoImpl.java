package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BOrderPayDao;
import jehc.bmodules.bmodel.BOrderPay;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础订单支付 
* 2016-03-22 16:47:52  邓纯杰
*/
@Repository("bOrderPayDao")
public class BOrderPayDaoImpl  extends BaseDaoImpl implements BOrderPayDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BOrderPay> getBOrderPayListByCondition(Map<String,Object> condition){
		return (List<BOrderPay>)this.getList("getBOrderPayListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_order_pay_id 
	* @return
	*/
	public BOrderPay getBOrderPayById(String b_order_pay_id){
		return (BOrderPay)this.get("getBOrderPayById", b_order_pay_id);
	}
	/**
	* 添加
	* @param b_order_pay 
	* @return
	*/
	public int addBOrderPay(BOrderPay b_Order_Pay){
		return this.add("addBOrderPay", b_Order_Pay);
	}
	/**
	* 修改
	* @param b_order_pay 
	* @return
	*/
	public int updateBOrderPay(BOrderPay b_Order_Pay){
		return this.update("updateBOrderPay", b_Order_Pay);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBOrderPay(Map<String,Object> condition){
		return this.del("delBOrderPay", condition);
	}
	/**
	 * 计算订单已付总金额
	 * @param condition
	 * @return
	 */
	public double getBOrderPayTotalPrice(Map<String,Object> condition){
		return new Double(this.get("getBOrderPayTotalPrice",condition).toString());
	}
}
