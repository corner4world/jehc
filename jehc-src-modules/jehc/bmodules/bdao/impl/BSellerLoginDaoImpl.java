package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BSellerLoginDao;
import jehc.bmodules.bmodel.BSellerLogin;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础卖家登陆账号 
* 2016-02-18 17:17:12  邓纯杰
*/
@Repository("bSellerLoginDao")
public class BSellerLoginDaoImpl  extends BaseDaoImpl implements BSellerLoginDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BSellerLogin> getBSellerLoginListByCondition(Map<String,Object> condition){
		return (List<BSellerLogin>)this.getList("getBSellerLoginListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_seller_login_id 
	* @return
	*/
	public BSellerLogin getBSellerLoginById(String b_seller_login_id){
		return (BSellerLogin)this.get("getBSellerLoginById", b_seller_login_id);
	}
	/**
	* 添加
	* @param b_seller_login 
	* @return
	*/
	public int addBSellerLogin(BSellerLogin b_Seller_Login){
		return this.add("addBSellerLogin", b_Seller_Login);
	}
	/**
	* 修改
	* @param b_seller_login 
	* @return
	*/
	public int updateBSellerLogin(BSellerLogin b_Seller_Login){
		return this.update("updateBSellerLogin", b_Seller_Login);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerLogin(Map<String,Object> condition){
		return this.del("delBSellerLogin", condition);
	}
	
	public BSellerLogin getBSellerLogin(String b_seller_id){
		return (BSellerLogin)this.get("getBSellerLogin", b_seller_id);
	}
}
