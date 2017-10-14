package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BMemberAccountDao;
import jehc.bmodules.bmodel.BMemberAccount;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础会员余额账户 
* 2016-03-24 20:30:14  邓纯杰
*/
@Repository("bMemberAccountDao")
public class BMemberAccountDaoImpl  extends BaseDaoImpl implements BMemberAccountDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BMemberAccount> getBMemberAccountListByCondition(Map<String,Object> condition){
		return (List<BMemberAccount>)this.getList("getBMemberAccountListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_member_account_id 
	* @return
	*/
	public BMemberAccount getBMemberAccountById(String b_member_account_id){
		return (BMemberAccount)this.get("getBMemberAccountById", b_member_account_id);
	}
	/**
	* 添加
	* @param b_member_account 
	* @return
	*/
	public int addBMemberAccount(BMemberAccount b_Member_Account){
		return this.add("addBMemberAccount", b_Member_Account);
	}
	/**
	* 修改
	* @param b_member_account 
	* @return
	*/
	public int updateBMemberAccount(BMemberAccount b_Member_Account){
		return this.update("updateBMemberAccount", b_Member_Account);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAccount(Map<String,Object> condition){
		return this.del("delBMemberAccount", condition);
	}
}
