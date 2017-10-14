package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BSellerAccountDao;
import jehc.bmodules.bmodel.BSellerAccount;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础卖家账号 
* 2016-02-18 17:07:37  邓纯杰
*/
@Repository("bSellerAccountDao")
public class BSellerAccountDaoImpl  extends BaseDaoImpl implements BSellerAccountDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BSellerAccount> getBSellerAccountListByCondition(Map<String,Object> condition){
		return (List<BSellerAccount>)this.getList("getBSellerAccountListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_seller_account_id 
	* @return
	*/
	public BSellerAccount getBSellerAccountById(String b_seller_account_id){
		return (BSellerAccount)this.get("getBSellerAccountById", b_seller_account_id);
	}
	/**
	* 添加
	* @param b_seller_account 
	* @return
	*/
	public int addBSellerAccount(BSellerAccount b_Seller_Account){
		return this.add("addBSellerAccount", b_Seller_Account);
	}
	/**
	* 修改
	* @param b_seller_account 
	* @return
	*/
	public int updateBSellerAccount(BSellerAccount b_Seller_Account){
		return this.update("updateBSellerAccount", b_Seller_Account);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerAccount(Map<String,Object> condition){
		return this.del("delBSellerAccount", condition);
	}
}
