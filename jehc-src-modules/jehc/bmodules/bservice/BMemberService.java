package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BMember;

/**
* 基础会员 
* 2016-01-08 22:35:33  邓纯杰
*/
public interface BMemberService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BMember> getBMemberListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_member_id 
	* @return
	*/
	public BMember getBMemberById(String b_member_id);
	/**
	* 添加
	* @param b_member 
	* @return
	*/
	public int addBMember(BMember b_Member);
	/**
	* 修改
	* @param b_member 
	* @return
	*/
	public int updateBMember(BMember b_Member);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBMember(Map<String,Object> condition);
}
