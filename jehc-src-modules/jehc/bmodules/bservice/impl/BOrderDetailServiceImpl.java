package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BOrderDetailDao;
import jehc.bmodules.bmodel.BOrderDetail;
import jehc.bmodules.bservice.BOrderDetailService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础订单详细 
* 2016-01-27 13:59:04  邓纯杰
*/
@Service("bOrderDetailService")
public class BOrderDetailServiceImpl extends BaseService implements BOrderDetailService{
	@Autowired
	private BOrderDetailDao bOrderDetailDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BOrderDetail> getBOrderDetailListByCondition(Map<String,Object> condition){
		try{
			return bOrderDetailDao.getBOrderDetailListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_order_detail_id 
	* @return
	*/
	public BOrderDetail getBOrderDetailById(String b_order_detail_id){
		try{
			return bOrderDetailDao.getBOrderDetailById(b_order_detail_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_order_detail 
	* @return
	*/
	public int addBOrderDetail(BOrderDetail b_Order_Detail){
		int i = 0;
		try {
			i = bOrderDetailDao.addBOrderDetail(b_Order_Detail);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_order_detail 
	* @return
	*/
	public int updateBOrderDetail(BOrderDetail b_Order_Detail){
		int i = 0;
		try {
			i = bOrderDetailDao.updateBOrderDetail(b_Order_Detail);
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
	public int delBOrderDetail(Map<String,Object> condition){
		int i = 0;
		try {
			i = bOrderDetailDao.delBOrderDetail(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 获取订单总金额
	 * @param condition
	 * @return
	 */
	public double getBOrderDetailTotalPriceByCondition(Map<String,Object> condition){
		try{
			return bOrderDetailDao.getBOrderDetailTotalPriceByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
