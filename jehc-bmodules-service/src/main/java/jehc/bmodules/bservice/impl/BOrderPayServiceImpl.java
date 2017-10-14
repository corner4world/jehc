package jehc.bmodules.bservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BOrderPayDao;
import jehc.bmodules.bmodel.BMemberAccountProprietary;
import jehc.bmodules.bmodel.BMemberAccountProprietaryHis;
import jehc.bmodules.bmodel.BOrder;
import jehc.bmodules.bmodel.BOrderPay;
import jehc.bmodules.bservice.BMemberAccountProprietaryHisService;
import jehc.bmodules.bservice.BMemberAccountProprietaryService;
import jehc.bmodules.bservice.BOrderDetailService;
import jehc.bmodules.bservice.BOrderPayService;
import jehc.bmodules.bservice.BOrderService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;

/**
* 基础订单支付 
* 2016-03-22 16:47:52  邓纯杰
*/
@Service("bOrderPayService")
public class BOrderPayServiceImpl extends BaseService implements BOrderPayService{
	@Autowired
	private BOrderPayDao bOrderPayDao;
	@Autowired
	private BOrderService bOrderService;
	@Autowired
	private BOrderDetailService bOrderDetailService;
	@Autowired
	private BMemberAccountProprietaryService bMemberAccountProprietaryService;
	@Autowired
	private BMemberAccountProprietaryHisService bMemberAccountProprietaryHisService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BOrderPay> getBOrderPayListByCondition(Map<String,Object> condition){
		try{
			return bOrderPayDao.getBOrderPayListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_order_pay_id 
	* @return
	*/
	public BOrderPay getBOrderPayById(String b_order_pay_id){
		try{
			return bOrderPayDao.getBOrderPayById(b_order_pay_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_order_pay 
	* @return
	*/
	public int addBOrderPay(BOrderPay b_Order_Pay){
		int i = 0;
		try {
			BOrder b_Order = bOrderService.getBOrderById(b_Order_Pay.getB_order_id());
			if(b_Order.getB_order_type().equals("1")){
				throw new ExceptionUtil("该订单已支付，不能再次支付");
			}
			//重新计算订单总价格
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_order_id", b_Order.getB_order_id());
			//订单总额
			double totalPrice = bOrderDetailService.getBOrderDetailTotalPriceByCondition(condition);
			//订单已支付总额
			double totalPayPrice = bOrderPayDao.getBOrderPayTotalPrice(condition);
			//订单实际应付金额
			double price = totalPrice-totalPayPrice;
			condition = new HashMap<String, Object>();
			condition.put("b_member_id", b_Order_Pay.getB_member_id());
			BMemberAccountProprietary b_Member_Account_Proprietary = bMemberAccountProprietaryService.getBMemberAccountProprietaryById(condition);
			if(0<(b_Order_Pay.getB_order_pay_money()) && (b_Order_Pay.getB_order_pay_money()) <price){
				b_Order.setB_order_type("2");
				b_Order_Pay.setB_order_pay_isall("1");
			}else if((b_Order_Pay.getB_order_pay_money()) == price){
				b_Order.setB_order_type("1");
				b_Order_Pay.setB_order_pay_isall("0");
			}else if((b_Order_Pay.getB_order_pay_money()) > price){
				b_Order.setB_order_type("1");
				b_Order_Pay.setB_order_pay_isall("0");
				//此时默认将多付的金额放入到专业账户中
				//如果专有账户没有创建则需先创建一下
				if(null != b_Member_Account_Proprietary && !"".equals(b_Member_Account_Proprietary)){
					b_Member_Account_Proprietary.setB_member_account_proprietary_money(b_Order_Pay.getB_order_pay_money()-price+b_Member_Account_Proprietary.getB_member_account_proprietary_money());
					bMemberAccountProprietaryService.updateBMemberAccountProprietary(b_Member_Account_Proprietary);
				}else{
					b_Member_Account_Proprietary = new BMemberAccountProprietary();
					b_Member_Account_Proprietary.setB_member_id(b_Order_Pay.getB_member_id());
					b_Member_Account_Proprietary.setB_member_account_proprietary_ctime(getSimpleDateFormat());
					b_Member_Account_Proprietary.setB_member_account_proprietary_id(UUID.toUUID());
					b_Member_Account_Proprietary.setB_member_account_proprietary_status("0");
					b_Member_Account_Proprietary.setB_member_account_proprietary_money((b_Order_Pay.getB_order_pay_money())-price);
					bMemberAccountProprietaryService.addBMemberAccountProprietary(b_Member_Account_Proprietary);
				}
				//充值日志
				BMemberAccountProprietaryHis b_Member_Account_Proprietary_His = new BMemberAccountProprietaryHis();
				b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_ctime(getSimpleDateFormat());
				b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_id(UUID.toUUID());
				b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_money(b_Order_Pay.getB_order_pay_money()-price);
				b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_type("2");
				b_Member_Account_Proprietary_His.setB_member_id(b_Order_Pay.getB_member_id());
				b_Member_Account_Proprietary_His.setB_member_account_proprietary_id(b_Member_Account_Proprietary.getB_member_account_proprietary_id());
				b_Member_Account_Proprietary_His.setB_member_account_proprietary_his_log("付款金额多于订单金额--系统自动将多余金额转入到会员专有账户");
				bMemberAccountProprietaryHisService.addBMemberAccountProprietaryHis(b_Member_Account_Proprietary_His);
			}else{
				b_Order.setB_order_type("0");
			}
			b_Order.setB_invoice_mtime(getSimpleDateFormat());
			bOrderService.updateBOrderType(b_Order);
			b_Order_Pay.setB_order_pay_ctime(getSimpleDateFormat());
			i = bOrderPayDao.addBOrderPay(b_Order_Pay);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_order_pay 
	* @return
	*/
	public int updateBOrderPay(BOrderPay b_Order_Pay){
		int i = 0;
		try {
			i = bOrderPayDao.updateBOrderPay(b_Order_Pay);
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
	public int delBOrderPay(Map<String,Object> condition){
		int i = 0;
		try {
			i = bOrderPayDao.delBOrderPay(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 计算订单已付总金额
	 * @param condition
	 * @return
	 */
	public double getBOrderPayTotalPrice(Map<String,Object> condition){
		try{
			return bOrderPayDao.getBOrderPayTotalPrice(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
