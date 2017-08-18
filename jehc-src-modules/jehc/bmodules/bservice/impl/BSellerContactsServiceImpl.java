package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BSellerContactsDao;
import jehc.bmodules.bmodel.BSellerContacts;
import jehc.bmodules.bservice.BSellerContactsService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础卖家联系人 
* 2016-02-18 17:11:48  邓纯杰
*/
@Service("bSellerContactsService")
public class BSellerContactsServiceImpl extends BaseService implements BSellerContactsService{
	@Autowired
	private BSellerContactsDao bSellerContactsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerContacts> getBSellerContactsListByCondition(Map<String,Object> condition){
		try{
			return bSellerContactsDao.getBSellerContactsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_seller_contacts_id 
	* @return
	*/
	public BSellerContacts getBSellerContactsById(String b_seller_contacts_id){
		try{
			return bSellerContactsDao.getBSellerContactsById(b_seller_contacts_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_seller_contacts 
	* @return
	*/
	public int addBSellerContacts(BSellerContacts b_Seller_Contacts){
		int i = 0;
		try {
			i = bSellerContactsDao.addBSellerContacts(b_Seller_Contacts);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_seller_contacts 
	* @return
	*/
	public int updateBSellerContacts(BSellerContacts b_Seller_Contacts){
		int i = 0;
		try {
			i = bSellerContactsDao.updateBSellerContacts(b_Seller_Contacts);
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
	public int delBSellerContacts(Map<String,Object> condition){
		int i = 0;
		try {
			i = bSellerContactsDao.delBSellerContacts(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
