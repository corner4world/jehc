package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BSellerContactsDao;
import jehc.bmodules.bmodel.BSellerContacts;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础卖家联系人 
* 2016-02-18 17:11:48  邓纯杰
*/
@Repository("bSellerContactsDao")
public class BSellerContactsDaoImpl  extends BaseDaoImpl implements BSellerContactsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BSellerContacts> getBSellerContactsListByCondition(Map<String,Object> condition){
		return (List<BSellerContacts>)this.getList("getBSellerContactsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_seller_contacts_id 
	* @return
	*/
	public BSellerContacts getBSellerContactsById(String b_seller_contacts_id){
		return (BSellerContacts)this.get("getBSellerContactsById", b_seller_contacts_id);
	}
	/**
	* 添加
	* @param b_seller_contacts 
	* @return
	*/
	public int addBSellerContacts(BSellerContacts b_Seller_Contacts){
		return this.add("addBSellerContacts", b_Seller_Contacts);
	}
	/**
	* 修改
	* @param b_seller_contacts 
	* @return
	*/
	public int updateBSellerContacts(BSellerContacts b_Seller_Contacts){
		return this.update("updateBSellerContacts", b_Seller_Contacts);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerContacts(Map<String,Object> condition){
		return this.del("delBSellerContacts", condition);
	}
}
