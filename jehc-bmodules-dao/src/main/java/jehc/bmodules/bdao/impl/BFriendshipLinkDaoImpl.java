package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BFriendshipLinkDao;
import jehc.bmodules.bmodel.BFriendshipLink;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础友情链接 
* 2016-01-10 12:35:06  邓纯杰
*/
@Repository("bFriendshipLinkDao")
public class BFriendshipLinkDaoImpl  extends BaseDaoImpl implements BFriendshipLinkDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BFriendshipLink> getBFriendshipLinkListByCondition(Map<String,Object> condition){
		return (List<BFriendshipLink>)this.getList("getBFriendshipLinkListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_friendship_link_id 
	* @return
	*/
	public BFriendshipLink getBFriendshipLinkById(String b_friendship_link_id){
		return (BFriendshipLink)this.get("getBFriendshipLinkById", b_friendship_link_id);
	}
	/**
	* 添加
	* @param b_friendship_link 
	* @return
	*/
	public int addBFriendshipLink(BFriendshipLink b_Friendship_Link){
		return this.add("addBFriendshipLink", b_Friendship_Link);
	}
	/**
	* 修改
	* @param b_friendship_link 
	* @return
	*/
	public int updateBFriendshipLink(BFriendshipLink b_Friendship_Link){
		return this.update("updateBFriendshipLink", b_Friendship_Link);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBFriendshipLink(Map<String,Object> condition){
		return this.del("delBFriendshipLink", condition);
	}
}
