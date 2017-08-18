package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BMemberAccount;

/**
* 基础会员余额账户 
* 2016-03-24 20:30:14  邓纯杰
*/
public interface BMemberAccountDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAccount> getBMemberAccountListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_member_account_id 
	* @return
	*/
	public BMemberAccount getBMemberAccountById(String b_member_account_id);
	/**
	* 添加
	* @param b_member_account 
	* @return
	*/
	public int addBMemberAccount(BMemberAccount b_Member_Account);
	/**
	* 修改
	* @param b_member_account 
	* @return
	*/
	public int updateBMemberAccount(BMemberAccount b_Member_Account);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAccount(Map<String,Object> condition);
}
