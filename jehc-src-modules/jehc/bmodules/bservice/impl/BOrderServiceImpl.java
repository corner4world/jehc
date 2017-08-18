package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BOrderDao;
import jehc.bmodules.bmodel.BOrder;
import jehc.bmodules.bservice.BOrderService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础订单 
* 2016-01-27 13:55:11  邓纯杰
*/
@Service("bOrderService")
public class BOrderServiceImpl extends BaseService implements BOrderService{
	@Autowired
	private BOrderDao bOrderDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BOrder> getBOrderListByCondition(Map<String,Object> condition){
		try{
			return bOrderDao.getBOrderListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_order_id 
	* @return
	*/
	public BOrder getBOrderById(String b_order_id){
		try{
			return bOrderDao.getBOrderById(b_order_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_order 
	* @return
	*/
	public int addBOrder(BOrder b_Order){
		int i = 0;
		try {
			i = bOrderDao.addBOrder(b_Order);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_order 
	* @return
	*/
	public int updateBOrder(BOrder b_Order){
		int i = 0;
		try {
			i = bOrderDao.updateBOrder(b_Order);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBOrder(Map<String,Object> condition){
		int i = 0;
		try {
			i = bOrderDao.delBOrder(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 更新订单状态
	 * @param b_Order
	 */
	public int updateBOrderType(BOrder b_Order){
		int i = 0;
		try {
			i = bOrderDao.updateBOrderType(b_Order);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
		return i;
	}
}
