package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BMemberDao;
import jehc.bmodules.bmodel.BMember;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础会员 
* 2016-01-08 22:35:33  邓纯杰
*/
@Repository("bMemberDao")
public class BMemberDaoImpl  extends BaseDaoImpl implements BMemberDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BMember> getBMemberListByCondition(Map<String,Object> condition){
		return (List<BMember>)this.getList("getBMemberListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_member_id 
	* @return
	*/
	public BMember getBMemberById(String b_member_id){
		return (BMember)this.get("getBMemberById", b_member_id);
	}
	/**
	* 添加
	* @param b_member 
	* @return
	*/
	public int addBMember(BMember b_Member){
		return this.add("addBMember", b_Member);
	}
	/**
	* 修改
	* @param b_member 
	* @return
	*/
	public int updateBMember(BMember b_Member){
		return this.update("updateBMember", b_Member);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMember(Map<String,Object> condition){
		return this.del("delBMember", condition);
	}
}
