package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BFriendshipLink;

/**
* 基础友情链接 
* 2016-01-10 12:35:06  邓纯杰
*/
public interface BFriendshipLinkService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BFriendshipLink> getBFriendshipLinkListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_friendship_link_id 
	* @return
	*/
	public BFriendshipLink getBFriendshipLinkById(String b_friendship_link_id);
	/**
	* 添加
	* @param b_friendship_link 
	* @return
	*/
	public int addBFriendshipLink(BFriendshipLink b_Friendship_Link);
	/**
	* 修改
	* @param b_friendship_link 
	* @return
	*/
	public int updateBFriendshipLink(BFriendshipLink b_Friendship_Link);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBFriendshipLink(Map<String,Object> condition);
}
