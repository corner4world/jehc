package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BSellerAccount;

/**
* 基础卖家账号 
* 2016-02-18 17:07:37  邓纯杰
*/
public interface BSellerAccountService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerAccount> getBSellerAccountListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_seller_account_id 
	* @return
	*/
	public BSellerAccount getBSellerAccountById(String b_seller_account_id);
	/**
	* 添加
	* @param b_seller_account 
	* @return
	*/
	public int addBSellerAccount(BSellerAccount b_Seller_Account);
	/**
	* 修改
	* @param b_seller_account 
	* @return
	*/
	public int updateBSellerAccount(BSellerAccount b_Seller_Account);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerAccount(Map<String,Object> condition);
}
