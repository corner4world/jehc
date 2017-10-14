package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BCartOrderAddressDao;
import jehc.bmodules.bmodel.BCartOrderAddress;
import jehc.bmodules.bservice.BCartOrderAddressService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础购物车订单常用配送地址 
* 2016-02-22 21:17:25  邓纯杰
*/
@Service("bCartOrderAddressService")
public class BCartOrderAddressServiceImpl extends BaseService implements BCartOrderAddressService{
	@Autowired
	private BCartOrderAddressDao bCartOrderAddressDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCartOrderAddress> getBCartOrderAddressListByCondition(Map<String,Object> condition){
		try{
			return bCartOrderAddressDao.getBCartOrderAddressListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_cart_order_address_id 
	* @return
	*/
	public BCartOrderAddress getBCartOrderAddressById(String b_cart_order_address_id){
		try{
			return bCartOrderAddressDao.getBCartOrderAddressById(b_cart_order_address_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_cart_order_address 
	* @return
	*/
	public int addBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address){
		int i = 0;
		try {
			i = bCartOrderAddressDao.addBCartOrderAddress(b_Cart_Order_Address);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_cart_order_address 
	* @return
	*/
	public int updateBCartOrderAddress(BCartOrderAddress b_Cart_Order_Address){
		int i = 0;
		try {
			i = bCartOrderAddressDao.updateBCartOrderAddress(b_Cart_Order_Address);
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
	public int delBCartOrderAddress(Map<String,Object> condition){
		int i = 0;
		try {
			i = bCartOrderAddressDao.delBCartOrderAddress(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
