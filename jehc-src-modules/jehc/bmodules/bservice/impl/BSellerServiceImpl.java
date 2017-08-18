package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BSellerDao;
import jehc.bmodules.bmodel.BSeller;
import jehc.bmodules.bservice.BSellerService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础卖家 
* 2016-01-08 22:54:00  邓纯杰
*/
@Service("bSellerService")
public class BSellerServiceImpl extends BaseService implements BSellerService{
	@Autowired
	private BSellerDao bSellerDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSeller> getBSellerListByCondition(Map<String,Object> condition){
		try{
			return bSellerDao.getBSellerListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_seller_id 
	* @return
	*/
	public BSeller getBSellerById(String b_seller_id){
		try{
			return bSellerDao.getBSellerById(b_seller_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_seller 
	* @return
	*/
	public int addBSeller(BSeller b_Seller){
		int i = 0;
		try {
			i = bSellerDao.addBSeller(b_Seller);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_seller 
	* @return
	*/
	public int updateBSeller(BSeller b_Seller){
		int i = 0;
		try {
			i = bSellerDao.updateBSeller(b_Seller);
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
	public int delBSeller(Map<String,Object> condition){
		int i = 0;
		try {
			i = bSellerDao.delBSeller(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
