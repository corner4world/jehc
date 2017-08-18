package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BMemberAddressDao;
import jehc.bmodules.bmodel.BMemberAddress;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础会员常用地址 
* 2016-02-22 16:44:23  邓纯杰
*/
@Repository("bMemberAddressDao")
public class BMemberAddressDaoImpl  extends BaseDaoImpl implements BMemberAddressDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BMemberAddress> getBMemberAddressListByCondition(Map<String,Object> condition){
		return (List<BMemberAddress>)this.getList("getBMemberAddressListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_member_address_id 
	* @return
	*/
	public BMemberAddress getBMemberAddressById(String b_member_address_id){
		return (BMemberAddress)this.get("getBMemberAddressById", b_member_address_id);
	}
	/**
	* 添加
	* @param b_member_address 
	* @return
	*/
	public int addBMemberAddress(BMemberAddress b_Member_Address){
		return this.add("addBMemberAddress", b_Member_Address);
	}
	/**
	* 修改
	* @param b_member_address 
	* @return
	*/
	public int updateBMemberAddress(BMemberAddress b_Member_Address){
		return this.update("updateBMemberAddress", b_Member_Address);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAddress(Map<String,Object> condition){
		return this.del("delBMemberAddress", condition);
	}
}
