package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BSellerLogin;

/**
* 基础卖家登陆账号 
* 2016-02-18 17:17:12  邓纯杰
*/
public interface BSellerLoginService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerLogin> getBSellerLoginListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_seller_login_id 
	* @return
	*/
	public BSellerLogin getBSellerLoginById(String b_seller_login_id);
	/**
	* 添加
	* @param b_seller_login 
	* @return
	*/
	public int addBSellerLogin(BSellerLogin b_Seller_Login);
	/**
	* 修改
	* @param b_seller_login 
	* @return
	*/
	public int updateBSellerLogin(BSellerLogin b_Seller_Login);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerLogin(Map<String,Object> condition);
	
	public BSellerLogin getBSellerLogin(String b_seller_id);
}
