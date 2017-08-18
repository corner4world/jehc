package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BSellerLoginDao;
import jehc.bmodules.bmodel.BSellerLogin;
import jehc.bmodules.bservice.BSellerLoginService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础卖家登陆账号 
* 2016-02-18 17:17:12  邓纯杰
*/
@Service("bSellerLoginService")
public class BSellerLoginServiceImpl extends BaseService implements BSellerLoginService{
	@Autowired
	private BSellerLoginDao bSellerLoginDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerLogin> getBSellerLoginListByCondition(Map<String,Object> condition){
		try{
			return bSellerLoginDao.getBSellerLoginListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_seller_login_id 
	* @return
	*/
	public BSellerLogin getBSellerLoginById(String b_seller_login_id){
		try{
			return bSellerLoginDao.getBSellerLoginById(b_seller_login_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_seller_login 
	* @return
	*/
	public int addBSellerLogin(BSellerLogin b_Seller_Login){
		int i = 0;
		try {
			i = bSellerLoginDao.addBSellerLogin(b_Seller_Login);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_seller_login 
	* @return
	*/
	public int updateBSellerLogin(BSellerLogin b_Seller_Login){
		int i = 0;
		try {
			i = bSellerLoginDao.updateBSellerLogin(b_Seller_Login);
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
	public int delBSellerLogin(Map<String,Object> condition){
		int i = 0;
		try {
			i = bSellerLoginDao.delBSellerLogin(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	public BSellerLogin getBSellerLogin(String b_seller_id){
		try{
			return bSellerLoginDao.getBSellerLogin(b_seller_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
