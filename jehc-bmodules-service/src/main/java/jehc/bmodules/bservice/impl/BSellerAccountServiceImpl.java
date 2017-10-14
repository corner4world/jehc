package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BSellerAccountDao;
import jehc.bmodules.bmodel.BSellerAccount;
import jehc.bmodules.bservice.BSellerAccountService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础卖家账号 
* 2016-02-18 17:07:37  邓纯杰
*/
@Service("bSellerAccountService")
public class BSellerAccountServiceImpl extends BaseService implements BSellerAccountService{
	@Autowired
	private BSellerAccountDao bSellerAccountDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerAccount> getBSellerAccountListByCondition(Map<String,Object> condition){
		try{
			return bSellerAccountDao.getBSellerAccountListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_seller_account_id 
	* @return
	*/
	public BSellerAccount getBSellerAccountById(String b_seller_account_id){
		try{
			return bSellerAccountDao.getBSellerAccountById(b_seller_account_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_seller_account 
	* @return
	*/
	public int addBSellerAccount(BSellerAccount b_Seller_Account){
		int i = 0;
		try {
			i = bSellerAccountDao.addBSellerAccount(b_Seller_Account);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_seller_account 
	* @return
	*/
	public int updateBSellerAccount(BSellerAccount b_Seller_Account){
		int i = 0;
		try {
			i = bSellerAccountDao.updateBSellerAccount(b_Seller_Account);
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
	public int delBSellerAccount(Map<String,Object> condition){
		int i = 0;
		try {
			i = bSellerAccountDao.delBSellerAccount(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
