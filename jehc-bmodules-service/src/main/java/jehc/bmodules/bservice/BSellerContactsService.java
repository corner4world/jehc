package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BSellerContacts;

/**
* 基础卖家联系人 
* 2016-02-18 17:11:48  邓纯杰
*/
public interface BSellerContactsService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerContacts> getBSellerContactsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_seller_contacts_id 
	* @return
	*/
	public BSellerContacts getBSellerContactsById(String b_seller_contacts_id);
	/**
	* 添加
	* @param b_seller_contacts 
	* @return
	*/
	public int addBSellerContacts(BSellerContacts b_Seller_Contacts);
	/**
	* 修改
	* @param b_seller_contacts 
	* @return
	*/
	public int updateBSellerContacts(BSellerContacts b_Seller_Contacts);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerContacts(Map<String,Object> condition);
}
