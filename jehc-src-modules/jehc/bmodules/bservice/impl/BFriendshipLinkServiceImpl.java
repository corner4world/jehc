package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BFriendshipLinkDao;
import jehc.bmodules.bmodel.BFriendshipLink;
import jehc.bmodules.bservice.BFriendshipLinkService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础友情链接 
* 2016-01-10 12:35:06  邓纯杰
*/
@Service("bFriendshipLinkService")
public class BFriendshipLinkServiceImpl extends BaseService implements BFriendshipLinkService{
	@Autowired
	private BFriendshipLinkDao bFriendshipLinkDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BFriendshipLink> getBFriendshipLinkListByCondition(Map<String,Object> condition){
		try{
			return bFriendshipLinkDao.getBFriendshipLinkListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_friendship_link_id 
	* @return
	*/
	public BFriendshipLink getBFriendshipLinkById(String b_friendship_link_id){
		try{
			return bFriendshipLinkDao.getBFriendshipLinkById(b_friendship_link_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_friendship_link 
	* @return
	*/
	public int addBFriendshipLink(BFriendshipLink b_Friendship_Link){
		int i = 0;
		try {
			i = bFriendshipLinkDao.addBFriendshipLink(b_Friendship_Link);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_friendship_link 
	* @return
	*/
	public int updateBFriendshipLink(BFriendshipLink b_Friendship_Link){
		int i = 0;
		try {
			i = bFriendshipLinkDao.updateBFriendshipLink(b_Friendship_Link);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBFriendshipLink(Map<String,Object> condition){
		int i = 0;
		try {
			i = bFriendshipLinkDao.delBFriendshipLink(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
