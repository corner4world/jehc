package jehc.bmodules.bservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BCartDao;
import jehc.bmodules.bdao.BCartDetailDao;
import jehc.bmodules.bmodel.BCart;
import jehc.bmodules.bmodel.BCartDetail;
import jehc.bmodules.bservice.BCartDetailService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础购物车明细 
* 2016-01-27 13:52:21  邓纯杰
*/
@Service("bCartDetailService")
public class BCartDetailServiceImpl extends BaseService implements BCartDetailService{
	@Autowired
	private BCartDetailDao bCartDetailDao;
	@Autowired
	private BCartDao bCartDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCartDetail> getBCartDetailListByCondition(Map<String,Object> condition){
		try{
			return bCartDetailDao.getBCartDetailListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_cart_detail_id 
	* @return
	*/
	public BCartDetail getBCartDetailById(String b_cart_detail_id){
		try{
			return bCartDetailDao.getBCartDetailById(b_cart_detail_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_cart_detail 
	* @return
	*/
	public int addBCartDetail(BCartDetail b_Cart_Detail){
		int i = 0;
		try {
			i = bCartDetailDao.addBCartDetail(b_Cart_Detail);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_cart_detail 
	* @return
	*/
	public int updateBCartDetail(BCartDetail b_Cart_Detail){
		int i = 0;
		try {
			i = bCartDetailDao.updateBCartDetail(b_Cart_Detail);
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
	public int delBCartDetail(Map<String,Object> condition){
		int i = 0;
		try {
			bCartDetailDao.delBCartDetail(condition);
			List<BCartDetail> b_Cart_DetailList = bCartDetailDao.getBCartDetailList(condition);
			for(int j = 0; j < b_Cart_DetailList.size(); j++){
				BCartDetail b_Cart_Detail = b_Cart_DetailList.get(j);
				condition = new HashMap<String, Object>();
				condition.put("b_cart_id", b_Cart_Detail.getB_cart_id().split(","));
				List<BCart> b_CartList = bCartDao.getBCartList(condition);
				BCart b_Cart = b_CartList.get(0);
				b_Cart.setB_cart_total_number(b_Cart.getB_cart_total_number()-b_Cart_Detail.getB_cart_detail_number());
				b_Cart.setB_cart_total_price(b_Cart.getB_cart_total_price()-b_Cart_Detail.getB_cart_detail_total_price());
				bCartDao.updateBCart(b_Cart);
			}
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据购物车编号删除
	 * @param condition
	 */
	public int delBCartDetailByBCartId(Map<String,Object> condition){
		int i = 0;
		try {
			i = bCartDetailDao.delBCartDetailByBCartId(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据购物车明细编号获取购物车集合编号并去重
	 * @param condition
	 * @return
	 */
	public List<String> getBCartIdByCondition(Map<String,Object> condition){
		try{
			return bCartDetailDao.getBCartIdByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 根据购物车明细编号查找集合
	 * @param condition
	 * @return
	 */
	public List<BCartDetail> getBCartDetailList(Map<String,Object> condition){
		try{
			return bCartDetailDao.getBCartDetailList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
