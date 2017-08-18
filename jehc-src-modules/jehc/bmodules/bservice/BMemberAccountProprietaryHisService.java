package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BMemberAccountProprietaryHis;

/**
* 基础专有账户充值记录 
* 2016-03-24 20:48:25  邓纯杰
*/
public interface BMemberAccountProprietaryHisService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMemberAccountProprietaryHis> getBMemberAccountProprietaryHisListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_member_account_proprietary_his_id 
	* @return
	*/
	public BMemberAccountProprietaryHis getBMemberAccountProprietaryHisById(String b_member_account_proprietary_his_id);
	/**
	* 添加
	* @param b_member_account_proprietary_his 
	* @return
	*/
	public int addBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His);
	/**
	* 修改
	* @param b_member_account_proprietary_his 
	* @return
	*/
	public int updateBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAccountProprietaryHis(Map<String,Object> condition);
}
