package jehc.bmodules.bservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BCartDao;
import jehc.bmodules.bmodel.BCart;
import jehc.bmodules.bmodel.BCartDetail;
import jehc.bmodules.bmodel.BCartOrderAddress;
import jehc.bmodules.bmodel.BOrder;
import jehc.bmodules.bmodel.BOrderDetail;
import jehc.bmodules.bservice.BCartService;
import jehc.bmodules.bservice.BCartDetailService;
import jehc.bmodules.bservice.BCartOrderAddressService;
import jehc.bmodules.bservice.BOrderService;
import jehc.bmodules.bservice.BOrderDetailService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;

/**
* 基础购物车 
* 2016-01-27 13:36:04  邓纯杰
*/
@Service("bCartService")
public class BCartServiceImpl extends BaseService implements BCartService{
	@Autowired
	private BCartDao bCartDao;
	@Autowired
	private BCartDetailService bCartDetailService;
	@Autowired
	private BCartOrderAddressService bCartOrderAddressService;
	
	@Autowired
	private BOrderService bOrderService;
	@Autowired
	private BOrderDetailService bOrderDetailService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCart> getBCartListByCondition(Map<String,Object> condition){
		try{
			return bCartDao.getBCartListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_cart_id 
	* @return
	*/
	public BCart getBCartById(String b_cart_id){
		try{
			return bCartDao.getBCartById(b_cart_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_cart 
	* @return
	*/
	public int addBCart(BCart b_Cart,List<BCartDetail> b_Cart_DetailList,BCartOrderAddress b_Cart_Order_Address){
		int i = 0;
		try {
			bCartOrderAddressService.addBCartOrderAddress(b_Cart_Order_Address);
			b_Cart.setB_cart_order_address_id(b_Cart_Order_Address.getB_cart_order_address_id());
			bCartDao.addBCart(b_Cart);
			for(int j = 0; j < b_Cart_DetailList.size(); j++){
				bCartDetailService.addBCartDetail(b_Cart_DetailList.get(j));
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
	* 修改
	* @param b_cart 
	* @return
	*/
	public int updateBCart(BCart b_Cart,List<BCartDetail> b_Cart_DetailList,BCartOrderAddress b_Cart_Order_Address){
		int i = 0;
		try {
			bCartOrderAddressService.updateBCartOrderAddress(b_Cart_Order_Address);
			bCartDao.updateBCart(b_Cart);
			for(int j = 0; j < b_Cart_DetailList.size(); j++){
				b_Cart_DetailList.get(j).setB_cart_id(b_Cart.getB_cart_id());
				if(null != b_Cart_DetailList.get(j) && !"".equals(b_Cart_DetailList.get(j)) && null != b_Cart_DetailList.get(j).getB_cart_detail_id() && !"" .equals(b_Cart_DetailList.get(j).getB_cart_detail_id())){
					b_Cart_DetailList.get(j).setB_cart_detail_mtime(CommonUtils.getSimpleDateFormat());
					bCartDetailService.updateBCartDetail(b_Cart_DetailList.get(j));
				}else{
					b_Cart_DetailList.get(j).setB_cart_detail_id(UUID.toUUID());
					b_Cart_DetailList.get(j).setB_cart_detail_ctime(CommonUtils.getSimpleDateFormat());
					bCartDetailService.addBCartDetail(b_Cart_DetailList.get(j));
				}
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
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCart(Map<String,Object> condition){
		int i = 0;
		try {
			List<String> b_cart_order_address_idList = bCartDao.getBCartOrderAddressIdByCondition(condition);
			String b_cart_order_address_id ="";
			for(int j =0; j < b_cart_order_address_idList.size();j++){
				if(null != b_cart_order_address_id && !"".equals(b_cart_order_address_id)){
					b_cart_order_address_id = b_cart_order_address_id+","+b_cart_order_address_idList.get(j);
				}else{
					b_cart_order_address_id = b_cart_order_address_idList.get(j);
				}
			}
			bCartDao.delBCart(condition);
			bCartDetailService.delBCartDetailByBCartId(condition);
			condition = new HashMap<String, Object>();
			condition.put("b_cart_order_address_id",b_cart_order_address_id.split(","));
			i = bCartOrderAddressService.delBCartOrderAddress(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据购物车编号获取购物车订单地址集合编号
	 * @param condition
	 * @return
	 */
	public List<String> getBCartOrderAddressIdByCondition(Map<String,Object> condition){
		try{
			return bCartDao.getBCartOrderAddressIdByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 根据购物车编号集合
	 * @param condition
	 * @return
	 */
	public List<BCart> getBCartList(Map<String,Object> condition){
		try{
			return bCartDao.getBCartList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 单个购物车转订单转换
	 * @param b_cart_id
	 * @return
	 */
	public int singleBCartTBOrderPoulators(String b_cart_id){
		int i = 0;
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_cart_id", b_cart_id);
			BOrder b_order = new BOrder();
			BCart b_cart = bCartDao.getBCartById(b_cart_id);
			List<BCartDetail> b_Cart_DetailList = bCartDetailService.getBCartDetailListByCondition(condition);
			BCartOrderAddress b_Cart_Order_Address = bCartOrderAddressService.getBCartOrderAddressById(b_cart.getB_cart_order_address_id());
			b_Cart_Order_Address.setB_cart_order_address_id(UUID.toUUID());
			b_order.setB_cart_order_address_id(b_cart.getB_cart_order_address_id());
			b_order.setB_order_name("购物车转订单");
			b_order.setB_invoice_id(b_cart.getB_invoice_id());
			b_order.setB_order_ctime(CommonUtils.getSimpleDateFormat());
			b_order.setB_order_from(b_cart.getB_cart_from());
			b_order.setB_order_id(UUID.toUUID());
			b_order.setB_order_key(b_cart.getB_cart_orderkey());
			b_order.setB_order_remark(b_cart.getB_cart_remark());
			b_order.setB_order_sessionid(b_cart.getB_cart_sessionid());
			b_order.setB_order_status("0");/**正常订单**/
			b_order.setB_order_total_number(b_cart.getB_cart_total_number());
			b_order.setB_order_total_price(b_cart.getB_cart_total_price());
			b_order.setB_cart_order_address_id(b_Cart_Order_Address.getB_cart_order_address_id());
			b_order.setB_member_id(b_cart.getB_member_id());
			b_order.setB_order_type("0");/**待付订单**/
			bCartOrderAddressService.addBCartOrderAddress(b_Cart_Order_Address);
			bOrderService.addBOrder(b_order);
			for(int j = 0; j < b_Cart_DetailList.size(); j++){
				BCartDetail b_Cart_Detail = b_Cart_DetailList.get(j);
				BOrderDetail b_Order_Detail = new BOrderDetail();
				b_Order_Detail.setB_order_detail_id(UUID.toUUID());
				b_Order_Detail.setB_order_detail_ctime(CommonUtils.getSimpleDateFormat());
				b_Order_Detail.setB_order_detail_discount(b_Cart_Detail.getB_cart_detail_discount());
				b_Order_Detail.setB_order_detail_number(b_Cart_Detail.getB_cart_detail_number());
				b_Order_Detail.setB_order_detail_price(b_Cart_Detail.getB_cart_detail_price());
				b_Order_Detail.setB_product_id(b_Cart_Detail.getB_product_id());
				b_Order_Detail.setB_seller_id(b_Cart_Detail.getB_seller_id());
				b_Order_Detail.setB_order_id(b_order.getB_order_id());
				bOrderDetailService.addBOrderDetail(b_Order_Detail);
			}
			condition.put("b_cart_id", b_cart_id.split(","));
			//删除
			delBCart(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 批量购物车转订单转换
	 * @param b_cart_id
	 * @return
	 */
	public int batchBCartTBOrderPoulators(String b_cart_id){
		int i = 0;
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_cart_id", b_cart_id.split(","));
			List<BCart> bCartList = bCartDao.getBCartListByCondition(condition);
			for(BCart b_cart:bCartList){
				BOrder b_order = new BOrder();
				condition.put("b_cart_id", b_cart.getB_cart_id());
				List<BCartDetail> b_Cart_DetailList = bCartDetailService.getBCartDetailListByCondition(condition);
				BCartOrderAddress b_Cart_Order_Address = bCartOrderAddressService.getBCartOrderAddressById(b_cart.getB_cart_order_address_id());
				b_Cart_Order_Address.setB_cart_order_address_id(UUID.toUUID());
				b_order.setB_cart_order_address_id(b_cart.getB_cart_order_address_id());
				b_order.setB_order_name("购物车转订单");
				b_order.setB_invoice_id(b_cart.getB_invoice_id());
				b_order.setB_order_ctime(CommonUtils.getSimpleDateFormat());
				b_order.setB_order_from(b_cart.getB_cart_from());
				b_order.setB_order_id(UUID.toUUID());
				b_order.setB_order_key(b_cart.getB_cart_orderkey());
				b_order.setB_order_remark(b_cart.getB_cart_remark());
				b_order.setB_order_sessionid(b_cart.getB_cart_sessionid());
				b_order.setB_order_status("0");/**正常订单**/
				b_order.setB_order_total_number(b_cart.getB_cart_total_number());
				b_order.setB_order_total_price(b_cart.getB_cart_total_price());
				b_order.setB_cart_order_address_id(b_Cart_Order_Address.getB_cart_order_address_id());
				b_order.setB_member_id(b_cart.getB_member_id());
				b_order.setB_order_type("0");/**待付订单**/
				bCartOrderAddressService.addBCartOrderAddress(b_Cart_Order_Address);
				bOrderService.addBOrder(b_order);
				for(int j = 0; j < b_Cart_DetailList.size(); j++){
					BCartDetail b_Cart_Detail = b_Cart_DetailList.get(j);
					BOrderDetail b_Order_Detail = new BOrderDetail();
					b_Order_Detail.setB_order_detail_id(UUID.toUUID());
					b_Order_Detail.setB_order_detail_ctime(CommonUtils.getSimpleDateFormat());
					b_Order_Detail.setB_order_detail_discount(b_Cart_Detail.getB_cart_detail_discount());
					b_Order_Detail.setB_order_detail_number(b_Cart_Detail.getB_cart_detail_number());
					b_Order_Detail.setB_order_detail_price(b_Cart_Detail.getB_cart_detail_price());
					b_Order_Detail.setB_product_id(b_Cart_Detail.getB_product_id());
					b_Order_Detail.setB_seller_id(b_Cart_Detail.getB_seller_id());
					b_Order_Detail.setB_order_id(b_order.getB_order_id());
					bOrderDetailService.addBOrderDetail(b_Order_Detail);
				}
			}
			condition.put("b_cart_id", b_cart_id.split(","));
			//删除
			delBCart(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
