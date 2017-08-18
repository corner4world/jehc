package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BMemberAddress;

/**
* 基础会员常用地址 
* 2016-02-22 16:44:23  邓纯杰
*/
public interface BMemberAddressDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAddress> getBMemberAddressListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_member_address_id 
	* @return
	*/
	public BMemberAddress getBMemberAddressById(String b_member_address_id);
	/**
	* 添加
	* @param b_member_address 
	* @return
	*/
	public int addBMemberAddress(BMemberAddress b_Member_Address);
	/**
	* 修改
	* @param b_member_address 
	* @return
	*/
	public int updateBMemberAddress(BMemberAddress b_Member_Address);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAddress(Map<String,Object> condition);
}
