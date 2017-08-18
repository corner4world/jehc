package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BMemberAccountProprietary;

/**
* 基础专有账户 
* 2016-03-24 20:33:37  邓纯杰
*/
public interface BMemberAccountProprietaryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAccountProprietary> getBMemberAccountProprietaryListByCondition(Map<String,Object> condition);
	/**
	* 查询对象 会员编号 或者专有账户编号
	* @param condition 
	* @return
	*/
	public BMemberAccountProprietary getBMemberAccountProprietaryById(Map<String, Object> condition);
	/**
	* 添加
	* @param b_member_account_proprietary 
	* @return
	*/
	public int addBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary);
	/**
	* 修改
	* @param b_member_account_proprietary 
	* @return
	*/
	public int updateBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAccountProprietary(Map<String,Object> condition);
}
