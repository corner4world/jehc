package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BMemberAccountProprietaryHisDao;
import jehc.bmodules.bmodel.BMemberAccountProprietaryHis;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础专有账户充值记录 
* 2016-03-24 20:48:25  邓纯杰
*/
@Repository("bMemberAccountProprietaryHisDao")
public class BMemberAccountProprietaryHisDaoImpl  extends BaseDaoImpl implements BMemberAccountProprietaryHisDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BMemberAccountProprietaryHis> getBMemberAccountProprietaryHisListByCondition(Map<String,Object> condition){
		return (List<BMemberAccountProprietaryHis>)this.getList("getBMemberAccountProprietaryHisListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_member_account_proprietary_his_id 
	* @return
	*/
	public BMemberAccountProprietaryHis getBMemberAccountProprietaryHisById(String b_member_account_proprietary_his_id){
		return (BMemberAccountProprietaryHis)this.get("getBMemberAccountProprietaryHisById", b_member_account_proprietary_his_id);
	}
	/**
	* 添加
	* @param b_member_account_proprietary_his 
	* @return
	*/
	public int addBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His){
		return this.add("addBMemberAccountProprietaryHis", b_Member_Account_Proprietary_His);
	}
	/**
	* 修改
	* @param b_member_account_proprietary_his 
	* @return
	*/
	public int updateBMemberAccountProprietaryHis(BMemberAccountProprietaryHis b_Member_Account_Proprietary_His){
		return this.update("updateBMemberAccountProprietaryHis", b_Member_Account_Proprietary_His);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMemberAccountProprietaryHis(Map<String,Object> condition){
		return this.del("delBMemberAccountProprietaryHis", condition);
	}
}
