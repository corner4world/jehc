package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BMemberAccountProprietaryDao;
import jehc.bmodules.bmodel.BMemberAccountProprietary;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础专有账户 
* 2016-03-24 20:33:38  邓纯杰
*/
@Repository("bMemberAccountProprietaryDao")
public class BMemberAccountProprietaryDaoImpl  extends BaseDaoImpl implements BMemberAccountProprietaryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BMemberAccountProprietary> getBMemberAccountProprietaryListByCondition(Map<String,Object> condition){
		return (List<BMemberAccountProprietary>)this.getList("getBMemberAccountProprietaryListByCondition",condition);
	}
	/**
	* 查询对象 会员编号 或者专有账户
	* @param condition 
	* @return
	*/
	public BMemberAccountProprietary getBMemberAccountProprietaryById(Map<String, Object> condition){
		return (BMemberAccountProprietary)this.get("getBMemberAccountProprietaryById", condition);
	}
	/**
	* 添加
	* @param b_member_account_proprietary 
	* @return
	*/
	public int addBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary){
		return this.add("addBMemberAccountProprietary", b_Member_Account_Proprietary);
	}
	/**
	* 修改
	* @param b_member_account_proprietary 
	* @return
	*/
	public int updateBMemberAccountProprietary(BMemberAccountProprietary b_Member_Account_Proprietary){
		return this.update("updateBMemberAccountProprietary", b_Member_Account_Proprietary);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAccountProprietary(Map<String,Object> condition){
		return this.del("delBMemberAccountProprietary", condition);
	}
}
