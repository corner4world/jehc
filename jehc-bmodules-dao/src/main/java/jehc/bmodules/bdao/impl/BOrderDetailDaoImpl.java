package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BOrderDetailDao;
import jehc.bmodules.bmodel.BOrderDetail;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础订单详细 
* 2016-01-27 13:59:04  邓纯杰
*/
@Repository("bOrderDetailDao")
public class BOrderDetailDaoImpl  extends BaseDaoImpl implements BOrderDetailDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BOrderDetail> getBOrderDetailListByCondition(Map<String,Object> condition){
		return (List<BOrderDetail>)this.getList("getBOrderDetailListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_order_detail_id 
	* @return
	*/
	public BOrderDetail getBOrderDetailById(String b_order_detail_id){
		return (BOrderDetail)this.get("getBOrderDetailById", b_order_detail_id);
	}
	/**
	* 添加
	* @param b_order_detail 
	* @return
	*/
	public int addBOrderDetail(BOrderDetail b_Order_Detail){
		return this.add("addBOrderDetail", b_Order_Detail);
	}
	/**
	* 修改
	* @param b_order_detail 
	* @return
	*/
	public int updateBOrderDetail(BOrderDetail b_Order_Detail){
		return this.update("updateBOrderDetail", b_Order_Detail);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBOrderDetail(Map<String,Object> condition){
		return this.del("delBOrderDetail", condition);
	}
	/**
	 * 获取订单总金额
	 * @param condition
	 * @return
	 */
	public double getBOrderDetailTotalPriceByCondition(Map<String,Object> condition){
		return new Double(this.get("getBOrderDetailTotalPriceByCondition", condition).toString());
	}
}
